package gzs.example.zty.testrecycleview;

/**
 * Created by zty on 15-8-28.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 15-8-28.
 */
public class SimpleB extends RecyclerView.Adapter <MViewHolder>{
    private Context context;
    private List<String> mdatas;
    private LayoutInflater inflater;
    private List<Integer> mH;
    private OnItemClickListener onItemClickListener;
    protected interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemlongClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener= listener;
    }
    public SimpleB(Context context,List<String> mdatas) {
        this.context=context;
        this.mdatas=mdatas;
        mH=new ArrayList<Integer>();
        for (int i=0;i<mdatas.size();i++){
            mH.add(i, (int) (100+Math.random()*300));
        }
        inflater=LayoutInflater.from(context);

    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.item_single,viewGroup,false);
        MViewHolder  myViewHolder=new MViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MViewHolder myViewHolder, int i) {
        ViewGroup.LayoutParams layoutParams=myViewHolder.itemView.getLayoutParams();
        layoutParams.height=mH.get(i);
        myViewHolder.itemView.setLayoutParams(layoutParams);
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
class  MViewHolder extends RecyclerView.ViewHolder{
    TextView tv;
    public MViewHolder(View itemView) {
        super(itemView);
        tv= (TextView) itemView.findViewById(R.id.text_view);
    }
}