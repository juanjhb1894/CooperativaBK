package com.example.cooperativa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooperativa.R;
import com.example.cooperativa.models.ProductsModel;

import java.util.List;

public class ProductRecyclerViewAdapter  extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<ProductsModel> lstModelProducts;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public ProductRecyclerViewAdapter(Context context, List<ProductsModel> products) {
        this.mInflater = LayoutInflater.from(context);
        this.lstModelProducts = products;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }
    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        /*
            0 - Inactivo
            1 - Disponible
            2 - Solicitado
            3 - Rechazado
            4 - Pendiente
         */

        ProductsModel producto= lstModelProducts.get(position);
        holder.tvProduct.setText(producto.getTipo());
        holder.tvDetails.setText(producto.getFecha());
        holder.tvAmount.setText(producto.getMonto());

        switch (producto.getEstado())
        {
            case 1:
                holder.tvStatus.setText(context.getString(R.string.available));
                break;
            case 2:
                holder.tvStatus.setText(context.getString(R.string.requested));
                break;
            case 3:
                holder.tvStatus.setText(context.getString(R.string.rejected));
                break;
            case 4:
                holder.tvStatus.setText(context.getString(R.string.waiting));
                break;
        }

        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (producto.getEstado())
                {
                    case 1:
                        //Mostrar detalles del producto disponible
                        break;
                    case 2:
                        //Mostrar detalles de la solicitud
                        break;
                    case 3:
                        //Mostrar detalles del producto
                        break;
                    case 4:
                        //Mostrar detalles del producto utilizado
                        break;
                }
            }
        });

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return lstModelProducts.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvAmount, tvStatus, tvProduct, tvDetails;
        Button btnDetails;

        ViewHolder(View itemView) {
            super(itemView);

            tvProduct = (TextView) itemView.findViewById(R.id.productTipe);
            tvDetails = (TextView) itemView.findViewById(R.id.productDetail);
            tvAmount = (TextView) itemView.findViewById(R.id.productAmount);
            tvStatus = (TextView) itemView.findViewById(R.id.productStatus);
            btnDetails = (Button) itemView.findViewById(R.id.btnProductDetail);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public ProductsModel getItem(int id) {
        return lstModelProducts.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
