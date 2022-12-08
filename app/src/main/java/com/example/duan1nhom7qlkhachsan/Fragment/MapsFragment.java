package com.example.duan1nhom7qlkhachsan.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1nhom7qlkhachsan.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsFragment extends Fragment {
    private String address;

    //truyền data từ ngoài vào
    public static MapsFragment newInstance(String address) {
        MapsFragment mapsFragment = new MapsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("address", address);
        mapsFragment.setArguments(bundle);
        return mapsFragment;
    }
    //nhận data
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            address = getArguments().getString("address");
        }
    }

    //hàm này đẻ hiển thị map len
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng FpolyHCM = new LatLng(10.853690, 106.627342);
            googleMap.addMarker(new MarkerOptions().position(FpolyHCM).title("Savila Hotel"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(FpolyHCM));
            try {
                //dang chuyen  từ address sang latlong
                Geocoder geocoder = new Geocoder(getActivity().getApplicationContext());
                //Geocoder là 1 thư viện gg giúp chuyển từ địa chỉ sang kinh đọ (latitude) vĩ độ (Longtitude)
                List<Address> list = geocoder.getFromLocationName(address, 5);
                Address location = list.get(0);//lấy cái đầu tiên

                LatLng diachi = new LatLng(location.getLatitude(), location.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(diachi).title(address));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(diachi));
            } catch (Exception e) {

            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}