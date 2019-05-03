package com.example.mvvmtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_SENDER = 1;
    private final int TYPE_NICKNAME = 2;

    private List<Message> items;
    private Context context;

    @Inject
    Preferences sharedPreferences;

    public ChatAdapter(Context context, List<Message> items) {
        ApiController.getAppComponent().inject(this);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).isSender(sharedPreferences.getIdUser()) ? TYPE_SENDER : TYPE_NICKNAME;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_SENDER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_chat_sender, parent, false);
            return new SenderVH(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_chat_nickname, parent, false);
            return new NicknameVH(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_SENDER:
                ((SenderVH) holder).bind(items.get(position));
                break;
            case TYPE_NICKNAME:
                ((NicknameVH) holder).bind(items.get(position));
                break;
        }
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

    public void addTyping() {
        items.add(new Message(-1, 1, ""));
        notifyDataSetChanged();
    }

    public void removeTyping() {

    }

    protected class SenderVH extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewChatSender)
        protected TextView tvSender;

        @BindView(R.id.checkedImageView)
        protected ImageView circularImageView;

        private SenderVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Message message) {
            tvSender.setText(message.getBody());
        }
    }

    protected class NicknameVH extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewChatNickname)
        protected TextView tvNickname;

        @BindView(R.id.circleImageViewChatSender)
        protected CircularImageView checkedImageView;

        private NicknameVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Message message) {
            tvNickname.setText(message.getBody());
        }
    }

}
