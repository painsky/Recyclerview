package gzs.example.zty.testrecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by zty on 15-8-28.
 */
public class SimpleA extends RecyclerView.Adapter <MyViewHolder>{
    private Context context;
    private List<String> mdatas;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    protected interface OnItemClickListener{
    void onItemClick(View view,int position);
    void onItemlongClick(View view,int position);
}
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener= listener;
    }
    public SimpleA(Context context,List<String> mdatas) {
        this.context=context;
        this.mdatas=mdatas;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.item_single,viewGroup,false);
        MyViewHolder  myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv.setText(mdatas.get(i));
        if (onItemClickListener!=null){
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=myViewHolder.getLayoutPosition();
                onItemClickListener.onItemClick(myViewHolder.itemView,pos);
            }
        });
        myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos=myViewHolder.getLayoutPosition();
                onItemClickListener.onItemlongClick(myViewHolder.itemView,pos);
                return false;
            }
        });


        }


    }
    public void addData(int pos){
        mdatas.add(pos,"new one");
        notifyItemInserted(pos);

    }
    public void removeData(int pos){
        mdatas.remove(pos);
        notifyItemRemoved(pos);

    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }
}
class  MyViewHolder extends RecyclerView.ViewHolder{
    TextView tv;
    public MyViewHolder(View itemView) {
        super(itemView);
        tv= (TextView) itemView.findViewById(R.id.text_view);
    }
}