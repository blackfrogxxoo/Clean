package me.shaoown.clean;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.shaoown.clean.base.IAction1Request;
import me.shaoown.clean.base.IRequest;
import me.shaoown.clean.base.IUi;
import me.shaoown.clean.rx.RxBus;

public class MainActivity extends AppCompatActivity implements IUi<IRequest> {
    private static final String TAG = "MainActivity";

    private StringListClient client;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> myAdapter;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);
        client = ClientSingletonFactory.INSTANCE.getStringListClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        client.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        client.unregister();
    }

    public void helloWorld(View view) {
        IAction1Request request = new StringListRequest(o -> ((MyAdapter) myAdapter).setData(o));
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.i(TAG, "run: request");
                request(request);
            }
        };
        timer.schedule(task, 0, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void request(IRequest data) {
        RxBus.INSTANCE.post(data);
    }

    private static class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final String TAG = "MyAdapter";

        private final Context context;
        private List<String> data;

        private MyAdapter(Context context) {
            this.context = context;
            data = new ArrayList<>();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(View.inflate(context, R.layout.layout_item, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyHolder) holder).getTvItem().setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public void setData(List<String> data) {
            Log.i(TAG, "setData");
            this.data = data;
            notifyDataSetChanged();
        }
    }

    private static class MyHolder extends RecyclerView.ViewHolder {
        private final TextView tvItem;
        public MyHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
        }

        public TextView getTvItem() {
            return tvItem;
        }
    }
}
