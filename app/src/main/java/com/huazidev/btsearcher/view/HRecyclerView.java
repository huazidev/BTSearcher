package com.huazidev.btsearcher.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.huazidev.btsearcher.R;

import java.util.List;

/**
 * @author hua on 2017/12/8.
 */
public class HRecyclerView extends FrameLayout {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private OnRefreshLoadListener onRefreshLoadListener;

    private HRecyclerAdapter mAdapter;

    public HRecyclerView(@NonNull Context context) {
        super(context);
        initView();
    }

    public HRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        parseAttrs(attrs);
        initView();
    }

    private void parseAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HRecyclerView);
        try {

        } finally {
            typedArray.recycle();
        }
    }

    private void initView() {
        if (isInEditMode()) {
            return;
        }
        LayoutInflater.from(getContext()).inflate(R.layout.hua_recycler_view, this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.hua_refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.hua_recycler_view);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onRefreshLoadListener != null) {
                    onRefreshLoadListener.onRefresh();
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();

                if (lastVisibleItemPosition == totalItemCount - 1) {
                    if (onRefreshLoadListener != null) {
                        onRefreshLoadListener.onLoadMore();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    public void setAdapter(@NonNull RecyclerView.Adapter adapter) {
        mAdapter = new HRecyclerAdapter(adapter);
        recyclerView.setAdapter(mAdapter);
    }

    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    public void setRefreshLoadListener(OnRefreshLoadListener listener) {
        this.onRefreshLoadListener = listener;
    }

    public interface OnRefreshLoadListener {
        void onRefresh();

        void onLoadMore();
    }

    private static class HRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public static final int FOOTER_TYPE = Integer.MIN_VALUE;

        private RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;

        public HRecyclerAdapter(@NonNull RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
            this.adapter = adapter;
        }

        @Override
        public int getItemCount() {
            return adapter.getItemCount() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1) {
                return FOOTER_TYPE;
            } else {
                return adapter.getItemViewType(position);
            }
        }

        @Override
        public long getItemId(int position) {
            if (getItemViewType(position) == FOOTER_TYPE) {
                return super.getItemId(position);
            } else {
                return adapter.getItemId(position);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == FOOTER_TYPE) {
                return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hua_recycler_footer, parent, false));
            } else {
                return adapter.createViewHolder(parent, viewType);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder.getItemViewType() == FOOTER_TYPE) {
                FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            } else {
                adapter.bindViewHolder(holder, position);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
            if (holder.getItemViewType() != FOOTER_TYPE) {
                adapter.onBindViewHolder(holder, position, payloads);
            }
        }

        @Override
        public void onViewRecycled(RecyclerView.ViewHolder holder) {
            if (holder.getItemViewType() != FOOTER_TYPE) {
                adapter.onViewRecycled(holder);
            }
        }

        @Override
        public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
            if (holder.getItemViewType() != FOOTER_TYPE) {
                adapter.onViewAttachedToWindow(holder);
            }
        }

        @Override
        public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
            if (holder.getItemViewType() != FOOTER_TYPE) {
                adapter.onViewDetachedFromWindow(holder);
            }
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            adapter.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            adapter.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
            adapter.registerAdapterDataObserver(observer);
        }

        @Override
        public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
            adapter.unregisterAdapterDataObserver(observer);
        }

        public static class FooterViewHolder extends RecyclerView.ViewHolder {
            public FooterViewHolder(View itemView) {
                super(itemView);
            }
        }

    }

}
