package com.example.mvvmtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvvmtest.R;
import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.manager.Preferences;
import com.example.mvvmtest.model.Message;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchVH> {

    private List<Message> items;
    private Context context;

    @Inject
    Preferences sharedPreferences;

    public MatchAdapter(Context context, List<Message> items) {
        ApiController.getAppComponent().inject(this);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MatchAdapter.MatchVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_chat_sender, parent, false);
        return new MatchVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.MatchVH holder, int position) {
        holder.bind(null);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void addItems(List<Message> messages) {
        this.items.addAll(messages);
        notifyDataSetChanged();
    }

    public void addMessage(Message message) {
        items.add(message);
        notifyDataSetChanged();
    }

    protected class MatchVH extends RecyclerView.ViewHolder {

        @BindView(R.id.nameTextView)
        protected TextView nameTV;

        @BindView(R.id.bodyTextView)
        protected TextView bodyTV;

        @BindView(R.id.userImageView)
        protected CircularImageView userIV;

        private MatchVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Message message) {
            //tvSender.setText(message.getBody());
        }
    }

}
