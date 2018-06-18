package com.test.mercadolibre.mercadotest.abstraction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic Collection Adapter
 * Extend this class to implement a generic collection adapter to use with RecyclerViews and ViewHolder Pattern
 *
 * @author Roldan Barreto
 * @version 0.0.0
 */
@SuppressWarnings("unused")
public abstract class CollectionAdapter<T, H extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<H> {

    /**
     * Data
     */
    protected List<T> _dataSource;
    private int itemViewCacheSize = 10;
    /**
     * Context & callBacks
     */
    private Context _context;
    private OnItemClickListener<T, H> _clickListener;
    private CollectionChangeListener<T> _collectionChangeListener;
    private OnScrollEndListener _onScrollEndedListener;
    /**
     * Layout id to inflate view resource
     */
    private int _layoutId;

    /**
     * Default constructor
     *
     * @param context    android Context
     * @param dataSource List<T> Collection to render
     * @param layoutId   int layout resource id to inflate
     */
    protected CollectionAdapter(Context context, List<T> dataSource, int layoutId) {
        _context = context;
        _dataSource = dataSource;
        _layoutId = layoutId;
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(), null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return newHolderInstance(view);
    }

    @Override
    public void onBindViewHolder(final H holder, int position) {
        int lastItemPosition = getItemCount() - 1;
        if (lastItemPosition == position && (lastItemPosition >= itemViewCacheSize - 1) &&
                _onScrollEndedListener != null) {
            _onScrollEndedListener.onScrollEnd(getItemCount(), position);
        }
        bindHolder(holder, getItem(position), position);
        if (_clickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    _clickListener.onItemClick(getItem(position), (H) holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return _dataSource == null ? 0 : _dataSource.size();
    }

    /**
     * Get android context
     *
     * @return android Context instance object
     */
    public Context getContext() {
        return _context;
    }

    /**
     * Get layout resource id
     *
     * @return layout id to inflate
     */
    public int getLayoutId() {
        return _layoutId;
    }

    /**
     * Get T item value at specific position
     *
     * @param position int position
     * @return T instance object
     */
    protected T getItem(int position) {
        if (position >= _dataSource.size())
            return null;
        return _dataSource.get(position);
    }

    /**
     * Set on item click listener callback
     *
     * @param listener OnItemClickListener<T>
     */
    public void setOnItemClickListener(OnItemClickListener<T, H> listener) {
        _clickListener = listener;
    }

    public void setItemViewCacheSize(int itemViewCacheSize) {
        this.itemViewCacheSize = itemViewCacheSize;
    }

    /**
     * Set collection change listener callback
     *
     * @param listener CollectionChangeListener<T>
     */
    public void setCollectionChangeListener(CollectionChangeListener<T> listener) {
        _collectionChangeListener = listener;
    }

    public void setOnScrollEndedListener(OnScrollEndListener onScrollEndedListener) {
        _onScrollEndedListener = onScrollEndedListener;
    }

    public List<T> getCollection() {
        return _dataSource != null ? _dataSource : new ArrayList<T>();
    }

    /**
     * Insert a new item at last available position
     *
     * @param item T object model
     */
    public void insert(T item) {
        if (_dataSource == null)
            _dataSource = new ArrayList<T>();
        _dataSource.add(item);
        notifyItemInserted(_dataSource.size() - 1);
        if (_collectionChangeListener != null)
            _collectionChangeListener.onChange(_dataSource);
    }

    /**
     * Insert an Item at specific position given
     *
     * @param item     T items
     * @param position int position to insert
     */
    public void insert(T item, int position) {
        if (_dataSource == null)
            _dataSource = new ArrayList<T>();
        _dataSource.add(position, item);
        notifyItemInserted(position);
        if (_collectionChangeListener != null)
            _collectionChangeListener.onChange(_dataSource);
    }

    /**
     * Delete an item by index position
     *
     * @param position int index position
     */
    public void delete(int position) {
        if (_dataSource == null || _dataSource.isEmpty())
            return;
        if (position >= _dataSource.size())
            return;
        _dataSource.remove(position);
        notifyItemRemoved(position);
        if (_collectionChangeListener != null)
            _collectionChangeListener.onChange(_dataSource);
    }

    /**
     * Update object at specific index position
     *
     * @param item     T item to set at given index
     * @param position int position to update
     */
    public void update(T item, int position) {
        if (_dataSource == null || _dataSource.isEmpty())
            return;
        if (position >= _dataSource.size())
            return;
        _dataSource.set(position, item);
        notifyItemChanged(position);
        if (_collectionChangeListener != null)
            _collectionChangeListener.onChange(_dataSource);
    }

    /**
     * Insert a whole collection at last available position
     *
     * @param items Collection to insert
     */
    public void insertAll(List<T> items) {
        if (items == null || items.isEmpty())
            return;
        if (_dataSource == null)
            _dataSource = new ArrayList<T>();
        int lastAvailablePosition = _dataSource.size();
        _dataSource.addAll(items);
        notifyItemRangeInserted(lastAvailablePosition, items.size() - 1);
        if (_collectionChangeListener != null)
            _collectionChangeListener.onChange(_dataSource);
    }

    /**
     * Insert a whole collection at specific position given
     *
     * @param items    Collection to insert
     * @param position int index position to insert
     */
    public void insertAll(List<T> items, int position) {
        if (items == null || items.isEmpty())
            return;
        if (_dataSource == null)
            _dataSource = new ArrayList<T>();
        if (position >= _dataSource.size())
            return;
        _dataSource.addAll(position, items);
        notifyItemRangeInserted(position, _dataSource.size() - 1);
        if (_collectionChangeListener != null)
            _collectionChangeListener.onChange(_dataSource);
    }

    /**
     * Change data source collection to iterate over
     *
     * @param dataSource Collection
     */
    public void changeDataSource(List<T> dataSource) {
        _dataSource = dataSource;
        notifyDataSetChanged();
    }

    /**
     * Implement this function to get a new instance of a view holder object
     *
     * @param rowView Layout view instantiated
     * @return H RecyclerView.ViewHolder instance object
     */
    protected abstract H newHolderInstance(View rowView);

    /**
     * Implement this function to bind a view holder
     *
     * @param holder   H holder to render
     * @param item     T item value to render
     * @param position Int position to render
     */
    protected abstract void bindHolder(H holder, T item, int position);

    /**
     * Child interface to implement a custom item click callback
     *
     * @param <T>
     */
    public interface OnItemClickListener<T, H extends RecyclerView.ViewHolder> {

        void onItemClick(T item, H holder, int position);
    }

    /**
     * Child interface to implement a custom collection change callback
     *
     * @param <T>
     */
    public interface CollectionChangeListener<T> {

        void onChange(List<T> dataSource);
    }

    public interface OnScrollEndListener {

        void onScrollEnd(int size, int position);
    }
}
