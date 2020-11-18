package com.example.myapplication;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    Context context;
    RelativeLayout gridviewdata;
    ArrayList<ProductModel> productData;
    ProductAdapter productAdapter;
    ProductModel productModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        //getviews
        gridView = findViewById(R.id.gridview);
        gridviewdata = (RelativeLayout) findViewById(R.id.gridviewdata);
        productData = new ArrayList<>();

        //add Countries Data
        populateProductData();
        gridView.setOnItemLongClickListener(new ItemLongClickRemove());
        productAdapter = new ProductAdapter(context,productData);
        gridView.setAdapter(productAdapter);
        registerForContextMenu(gridView);
        gridView = findViewById(R.id.gridview);
        gridView.setOnItemLongClickListener(new ItemLongClickRemove());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(context,productData.get(position).getNamedh(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name",productData.get(position).getNamedh());
                intent.putExtra("image",productData.get(position).getImages());
                intent.putExtra("gia",productData.get(position).getGiadh());
                startActivity(intent);
            }
        });

    }
    private void populateProductData() {
        //product1
        productModel = new ProductModel();
        productModel.setId(1);
        productModel.setNamedh("Balo nam thời trang cá tính 4U BL101 Đen");
        productModel.setImages(R.drawable.bl1);
        productModel.setGiadh("475.000 VND");
        productData.add(productModel);

        //product2
        productModel = new ProductModel();
        productModel.setId(2);
        productModel.setNamedh("Balo thời trang Libag Li-0120 màu đen");
        productModel.setImages(R.drawable.bl2);
        productModel.setGiadh("349.000 VND");
        productData.add(productModel);

        //product3
        productModel = new ProductModel();
        productModel.setId(3);
        productModel.setNamedh("Áo khoác nam The Cosmo ALAN JACKET ");
        productModel.setImages(R.drawable.bl3);
        productModel.setGiadh("399.000 VND");
        productData.add(productModel);

        //product4
        productModel = new ProductModel();
        productModel.setId(4);
        productModel.setNamedh("Áo thun nam tay ngắn Emspo cổ tròn Trắng");
        productModel.setImages(R.drawable.bl4);
        productModel.setGiadh("142.450 VND");
        productData.add(productModel);

        //product5
        productModel = new ProductModel();
        productModel.setId(5);
        productModel.setNamedh("Áo khoác nam Chelle có mũ túi ngực đơn sắc");
        productModel.setImages(R.drawable.bl5);
        productModel.setGiadh("455.000 VND");
        productData.add(productModel);

        //product6
        productModel = new ProductModel();
        productModel.setId(6);
        productModel.setNamedh("Basas Mono-Black - High Top all Black");
        productModel.setImages(R.drawable.qa6);
        productModel.setGiadh("490.000 VND");
        productData.add(productModel);
    }
    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Bạn có muốn xóa sản phẩm này?");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // xóa sp đang nhấn giữ
                    productData.remove(position);
                    //cập nhật lại gridview
                    productAdapter.notifyDataSetChanged();
                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }
}