package com.example.yeohf.loginsystem.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yeohf.loginsystem.Entity.Rental;
import com.example.yeohf.loginsystem.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RentalListAdapter extends ArrayAdapter<Rental> {

    private Activity context;
    private List<Rental> rentalList;
    private ArrayList<Rental> tempRentalList;
    public RentalListAdapter(Activity context, List<Rental> rentalList) {
        super(context,R.layout.rental_layout, rentalList);
        this.context=context;
        this.rentalList= rentalList;
        this.tempRentalList = new ArrayList<>();
        this.tempRentalList.addAll(rentalList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.rental_layout,null,true);

        TextView rentaltitle = listViewItem.findViewById(R.id.lblListingTitle);
        TextView rentalListingType = listViewItem.findViewById(R.id.lblListingType);
        TextView rentalprice = listViewItem.findViewById(R.id.lblListingPrice);
        TextView rentalflatType = listViewItem.findViewById(R.id.lblFlatType);
        TextView rentalZone = listViewItem.findViewById(R.id.lblListingZone);
        TextView rentalModel = listViewItem.findViewById(R.id.lblListingModel);
        TextView rentalStorey = listViewItem.findViewById(R.id.lblStorey);
        ImageView rentalPic = listViewItem.findViewById(R.id.imageViewRental);

        Rental rental= rentalList.get(position);
        rentaltitle.setText(rental.getTitle());
        rentalListingType.setText(rental.getListingType());
        rentalprice.setText("$" + rental.getPrice());
        rentalflatType.setText(rental.getType());
        rentalZone.setText(rental.getZone());
        rentalModel.setText(rental.getModel());
        rentalStorey.setText("Lv." + rental.getStorey());
        Picasso.with(getContext()).load(rental.getImagePath()).into(rentalPic);

        return listViewItem;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        rentalList.clear();
        if (charText.length() == 0) {
            rentalList.addAll(tempRentalList);
        } else {
            for (Rental rent : tempRentalList) {
                if (rent.getTitle().toLowerCase().contains(charText) ||
                        rent.getAddress().toLowerCase().contains(charText)) {
                    rentalList.add(rent);
                }
            }
        }
        notifyDataSetChanged();
    }

}
