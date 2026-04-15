package com.example.lojavirtual;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editTextName, editTextCode, editTextPrice, editTextQuantity;
    private Button buttonSave, buttonViewList;
    private ProductDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = ProductDatabase.getInstance(this);

        editTextName = findViewById(R.id.editTextName);
        editTextCode = findViewById(R.id.editTextCode);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        buttonSave = findViewById(R.id.buttonSave);
        buttonViewList = findViewById(R.id.buttonViewList);

        buttonSave.setOnClickListener(v -> saveProduct());

        buttonViewList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
            startActivity(intent);
        });
    }

    private void saveProduct() {
        String name = editTextName.getText().toString().trim();
        String code = editTextCode.getText().toString().trim();
        String priceStr = editTextPrice.getText().toString().trim();
        String quantityStr = editTextQuantity.getText().toString().trim();

        if (name.isEmpty() || code.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Preço inválido (deve ser maior que 0)", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Quantidade inválida (deve ser um inteiro positivo)", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = new Product(name, code, price, quantity);

        new Thread(() -> {
            database.productDao().insert(product);
            runOnUiThread(() -> {
                Toast.makeText(MainActivity.this, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
                clearFields();
            });
        }).start();
    }

    private void clearFields() {
        editTextName.setText("");
        editTextCode.setText("");
        editTextPrice.setText("");
        editTextQuantity.setText("");
        editTextName.requestFocus();
    }
}
