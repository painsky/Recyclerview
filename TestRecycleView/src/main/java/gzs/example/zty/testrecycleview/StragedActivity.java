package gzs.example.zty.testrecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class StragedActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> madtas;
    private SimpleB simpleB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        simpleB=new SimpleB(this,madtas);
        recyclerView.setAdapter(simpleB);
        simpleB.setOnItemClickListener(new SimpleB.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemlongClick(View view, int position) {
            simpleB.removeData(position);
            }
        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
    }

    private void initView() {
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
    }
    private void initData() {
        madtas=new ArrayList<String>();
        for(int i='A';i<'z';i++){
            madtas.add(""+(char)i);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       switch(id){
           case R.id.action_Listview:
               recyclerView.setLayoutManager(new LinearLayoutManager(this));
               break;
           case R.id.action_Gridview:
               recyclerView.setLayoutManager(new GridLayoutManager(this,3));
               break;
           case R.id.action_Gridview_hor:
               recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
               break;
           case R.id.action_Starg:
               break;
       }

        return super.onOptionsItemSelected(item);
    }
}
