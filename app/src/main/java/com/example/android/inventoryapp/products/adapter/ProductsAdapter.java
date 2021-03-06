package com.example.android.inventoryapp.products.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.inventoryapp.R;
import com.example.android.inventoryapp.products.ProductsFragment;
import com.example.android.inventoryapp.products.model.Product;

import java.util.List;
import java.util.Locale;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by goransi on 1.7.2016..
 */
public class ProductsAdapter extends BaseAdapter {

    private List<Product> mProducts;
    private ProductsFragment.ProductItemListener mItemListener;


    public ProductsAdapter(List<Product> products, ProductsFragment.ProductItemListener itemListener) {
        this.mProducts = products;
        this.mItemListener = itemListener;
    }

    public void replaceData(List<Product> products) {
        setList(products);
        notifyDataSetChanged();
    }

    private void setList(List<Product> products) {
        mProducts = checkNotNull(products);
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Product getItem(int i) {
        return mProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            rowView = inflater.inflate(R.layout.product_item, viewGroup, false);
        }

        final Product product = getItem(i);

        RelativeLayout relativeLayout = (RelativeLayout) rowView.findViewById(R.id.list_item_product_row);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.showDetailProducts(product);
            }
        });
        ImageView mProductImage = (ImageView) rowView.findViewById(R.id.product_image);
        mProductImage.setImageBitmap(BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length));

        TextView productNameTV = (TextView) rowView.findViewById(R.id.product_name);
        TextView productQuantityTV = (TextView) rowView.findViewById(R.id.product_quantity);

        productNameTV.setText(product.getProduct());
        productQuantityTV.setText(String.format(Locale.US,"%d",product.getQuantity()));

        Button button = (Button) rowView.findViewById(R.id.product_button);
        button.setText(String.format(Locale.US,"+ %.2f $",product.getPrice()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.onProductClick(product);
            }
        });

        return rowView;
    }
}
