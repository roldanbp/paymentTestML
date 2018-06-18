package com.test.mercadolibre.mercadotest.core.util.connection;

import android.os.Build;
import android.support.annotation.NonNull;

import com.test.mercadolibre.mercadotest.core.util.TLS12SocketFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseService {

    private static final String URL = "https://api.mercadopago.com/";
    private static final String PROTOCOL_SSL = "TLSv1.2";
    private static BaseService ourInstance = new BaseService();
    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit, retrofitAuth;
    private static OkHttpClient.Builder httpClient = getHttpClientEnableTLS12();

    public static BaseService getInstance() {
        return ourInstance;
    }

    private BaseService() {
        retrofit = builder.build();
        retrofitAuth = retrofit;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public <T> T getService(Class<T> tClass) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT || Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT_WATCH) {
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(tClass);
    }

    public <T> T auth(Class<T> tClass, String authToken) {
        AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);
        httpClient.addInterceptor(interceptor);
        builder.client(httpClient.build());
        retrofitAuth = builder.build();
        return retrofitAuth.create(tClass);
    }


    private class AuthenticationInterceptor implements Interceptor {

        private String authToken;

        AuthenticationInterceptor(String token) {
            this.authToken = token;
        }

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request original = chain.request();

            Request.Builder builder = original.newBuilder()
                    .header("Authorization", authToken);

            Request request = builder.build();


            return chain.proceed(request);
        }
    }

    static OkHttpClient.Builder getHttpClientEnableTLS12() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT || Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT_WATCH) {
            try {
                OkHttpClient.Builder client = new OkHttpClient.Builder();
                SSLContext sslContext = SSLContext.getInstance(PROTOCOL_SSL);
                sslContext.init(null, null, null);
                client.sslSocketFactory(new TLS12SocketFactory(sslContext.getSocketFactory()));

                ConnectionSpec connectionSpec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();

                List specs = new ArrayList();
                specs.add(connectionSpec);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);
                return client.connectionSpecs(specs);
            } catch (Exception e) {
                return new OkHttpClient.Builder();
            }
        } else {
            return new OkHttpClient.Builder();
        }
    }
}

