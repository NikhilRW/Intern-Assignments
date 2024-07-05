package com.example.to_do_list.Backend.Adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.to_do_list.Backend.Models.Task;
import com.example.to_do_list.MainActivity;
import com.example.to_do_list.R;
import java.util.ArrayList;
public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private final Context context;
    private final ArrayList<String> arr;
    private final ArrayList<Integer> arr2;
    int isCompleted;
    private final MainActivity mainActivity;
    public CustomAdapter(Context context, ArrayList<String> arr, MainActivity mainActivity,ArrayList<Integer> arr2) {
        this.context = context;
        this.arr = arr;
        this.mainActivity = mainActivity;
        mainActivity.nothingToDo(arr.isEmpty());
        this.arr2=arr2;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_layout, parent, false);
        return new CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position){
        holder.checkBox.setChecked(mainActivity.toDoDatabaseManager.getTaskDetails(arr2.get(position)).getIsCompleted() == 1);
        holder.textView.setText(arr.get(position));
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isCompleted=isChecked?1:0;
            mainActivity.toDoDatabaseManager.setIsCompleted(arr2.get(position),isCompleted);
            if (isChecked&&mainActivity.isShowOnlyNotCompleted) {
                removeItemWithAnimation(position);
            }
        });
        holder.linearLayout.setOnClickListener(v -> {
            mainActivity.swtichToEditTaskActivity(arr2.get(position));
        });
    }
    public void removeItemWithAnimation(int position) {
        new Handler().postDelayed(() -> {
            arr.remove(position);
            arr2.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, arr.size());
        }, 400); // 300ms delay
    }
    @Override
    public int getItemCount() {
        return arr.size();
    }
}
