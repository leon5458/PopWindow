package com.hly.popwindow;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    private LinearLayout ll_list_default;
    private TextView ll_list_default_txt;
    private ImageView ll_list_default_icon;

    private LinearLayout ll_list_brand;
    private TextView list_brand_txt;
    private ImageView ll_list_brand_icon;

    private LinearLayout list_list_type;
    private TextView list_list_type_txt;
    private ImageView list_list_type_icon;

    private RecyclerView recyclerView;
    private FmmAdapter<String> popAdapter, popAdapter1, popAdapter2;
    private List<String> popList, popList1, popList2;
    private PopupWindow popupWindow;
    private String currentDefault, currentBrand,currentType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
        ll_list_default = findViewById(R.id.ll_list_default);
        ll_list_default_txt = findViewById(R.id.ll_list_default_txt);
        ll_list_default_icon = findViewById(R.id.ll_list_default_icon);

        ll_list_brand = findViewById(R.id.ll_list_brand);
        list_brand_txt = findViewById(R.id.list_brand_txt);
        ll_list_brand_icon = findViewById(R.id.ll_list_brand_icon);

        list_list_type = findViewById(R.id.list_list_type);
        list_list_type_txt = findViewById(R.id.list_list_type_txt);
        list_list_type_icon = findViewById(R.id.list_list_type_icon);


        ll_list_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_list_default_txt.setTextColor(Color.parseColor("#00d8a0"));
                ll_list_default_icon.setImageResource(R.mipmap.screen_icon_selected);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(popAdapter);
                popupWindow.showAsDropDown(ll_list_default);
            }
        });

        ll_list_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_brand_txt.setTextColor(Color.parseColor("#00d8a0"));
                ll_list_brand_icon.setImageResource(R.mipmap.screen_icon_selected);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(popAdapter1);
                popupWindow.showAsDropDown(ll_list_default);
            }
        });

        list_list_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_list_type_txt.setTextColor(Color.parseColor("#00d8a0"));
                list_list_type_icon.setImageResource(R.mipmap.screen_icon_selected);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(popAdapter2);
                popupWindow.showAsDropDown(ll_list_default);
            }
        });
    }

    private void InitView() {
        popList = new ArrayList<>();
        popList.add("默认排序");
        popList.add("价格最高");
        popList.add("价格最低");

        popList1 = new ArrayList<>();
        popList1.add("全部");
        popList1.add("自营");
        popList1.add("非自营");

        popList2 = new ArrayList<>();
        popList2.add("全部");
        popList2.add("三只松鼠");
        popList2.add("百草味");
        popList2.add("良品铺子");
        popList2.add("好想你");

        View contentView = getLayoutInflater().inflate(R.layout.popwin_supplier_list, null);
        popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

        recyclerView = contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popAdapter = new FmmAdapter<>(R.layout.item_listview_popwin, popList);
        popAdapter.setOnCallBackData(new FmmAdapter.OnCallBackData<String>() {
            @Override
            public void convertView(BaseViewHolder holder, String item) {
                ((TextView) holder.getView(R.id.listview_popwind_tv)).setText(item);
                if (ll_list_default_txt.getText().toString().trim().equals(item)) {
                    ((TextView) holder.getView(R.id.listview_popwind_tv)).setTextColor(Color.parseColor("#00d8a0"));
                    holder.getView(R.id.iv_select_icon).setVisibility(View.VISIBLE);
                } else {
                    ((TextView) holder.getView(R.id.listview_popwind_tv)).setTextColor(Color.parseColor("#333333"));
                    holder.getView(R.id.iv_select_icon).setVisibility(View.GONE);
                }
            }
        });

        popAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //后期自己做请求处理
                popupWindow.dismiss();
                currentDefault = popList.get(position);
                ll_list_default_txt.setText(currentDefault);
            }
        });

        popAdapter1 = new FmmAdapter<>(R.layout.item_listview_popwin, popList1);
        popAdapter1.setOnCallBackData(new FmmAdapter.OnCallBackData<String>() {
            @Override
            public void convertView(BaseViewHolder holder, String item) {
                ((TextView) holder.getView(R.id.listview_popwind_tv)).setText(item);
                if (ll_list_default_txt.getText().toString().trim().equals(item)) {
                    ((TextView) holder.getView(R.id.listview_popwind_tv)).setTextColor(Color.parseColor("#00d8a0"));
                    holder.getView(R.id.iv_select_icon).setVisibility(View.VISIBLE);
                } else {
                    ((TextView) holder.getView(R.id.listview_popwind_tv)).setTextColor(Color.parseColor("#333333"));
                    holder.getView(R.id.iv_select_icon).setVisibility(View.GONE);
                }
            }
        });

        popAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //后期自己做请求处理
                popupWindow.dismiss();
                currentBrand = popList1.get(position);
                list_brand_txt.setText(currentBrand);
            }
        });


        popAdapter2 = new FmmAdapter<>(R.layout.item_listview_popwin, popList2);
        popAdapter2.setOnCallBackData(new FmmAdapter.OnCallBackData<String>() {
            @Override
            public void convertView(BaseViewHolder holder, String item) {
                ((TextView) holder.getView(R.id.listview_popwind_tv)).setText(item);
                if (list_list_type_txt.getText().toString().trim().equals(item)) {
                    ((TextView) holder.getView(R.id.listview_popwind_tv)).setTextColor(Color.parseColor("#00d8a0"));
                    holder.getView(R.id.iv_select_icon).setVisibility(View.VISIBLE);
                } else {
                    ((TextView) holder.getView(R.id.listview_popwind_tv)).setTextColor(Color.parseColor("#333333"));
                    holder.getView(R.id.iv_select_icon).setVisibility(View.GONE);
                }
            }
        });

        popAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //后期自己做请求处理
                popupWindow.dismiss();
                currentType = popList2.get(position);
                list_list_type_txt.setText(currentType);
            }
        });



    }
}
