package com.example.lojavirtual;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private ProductDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Lista de Produtos");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter();
        recyclerViewProducts.setAdapter(productAdapter);

        database = ProductDatabase.getInstance(this);

        loadProducts();
    }

    private void loadProducts() {
        new Thread(() -> {
            List<Product> products = database.productDao().getAllProducts();
            runOnUiThread(() -> productAdapter.setProducts(products));
        }).start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
