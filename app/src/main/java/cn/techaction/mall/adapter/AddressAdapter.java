package cn.techaction.mall.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import cn.techaction.mall.R;
import cn.techaction.mall.listener.OnItemClickListener;
import cn.techaction.mall.pojo.Address;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder>{
    private Context context;
    private List<Address> mData;

    private OnItemClickListener onItemClickListener;
    private OnAddrOptListener onAddrOptListener;

    public AddressAdapter(Context context, List<Address> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_addr_list_item,null,false);
        return new AddressViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnAddrOptListener(OnAddrOptListener onAddrOptListener) {
        this.onAddrOptListener = onAddrOptListener;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Address addr = mData.get(position);
        holder.name.setText(addr.getName());
        holder.mobile.setText(addr.getMobile());
        StringBuffer sb = new StringBuffer();
        if(!TextUtils.isEmpty(addr.getProvince())){
            sb.append(addr.getProvince()).append(" ");
        }
        if(!TextUtils.isEmpty(addr.getCity())){
            sb.append(addr.getCity()).append(" ");
        }
        if(!TextUtils.isEmpty(addr.getDistrict())){
            sb.append(addr.getDistrict()).append(" ");
        }
        if(!TextUtils.isEmpty(addr.getAddr())){
            sb.append(addr.getAddr()).append(" ");
        }
        holder.addr_detail.setText(sb.toString());
        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onAddrOptListener!=null){
                    onAddrOptListener.deleteItem(v,position);
                }
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class AddressViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public TextView name;
        public TextView mobile;
        public TextView addr_detail;
        public TextView btn_del;
        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = (TextView)itemView.findViewById(R.id.name);
            mobile = (TextView)itemView.findViewById(R.id.mobile);
            addr_detail = (TextView)itemView.findViewById(R.id.addr_detail);
            btn_del = (TextView)itemView.findViewById(R.id.btn_del);

        }
    }

    public interface OnAddrOptListener{
        //删除
        public void deleteItem(View v,int pos);
    }
}
