package com.muhaiminurabir.realm_android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muhaiminurabir.realm_android.MODEL.Name_List;

import java.util.List;

public class Name_List_Adapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Object> name_list;
    public Name_List_Adapter(List<Object>list){
        this.name_list=list;
    }

    public class Name_List_Adapter_View extends RecyclerView.ViewHolder {
        public TextView first_name,last_name;
        public Name_List_Adapter_View(View view) {
            super(view);
            first_name = view.findViewById(R.id.first_name);
            last_name=view.findViewById(R.id.last_name);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.name_layout, parent, false);

        return new Name_List_Adapter_View(itemView);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        Name_List nameList = (Name_List) name_list.get(position);
        Name_List_Adapter_View name_view = (Name_List_Adapter_View) holder;
        name_view.first_name.setText(nameList.getFirst_name());
        name_view.last_name.setText(nameList.getLast_name());
    }
    @Override
    public int getItemCount() {
        return name_list.size();
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
