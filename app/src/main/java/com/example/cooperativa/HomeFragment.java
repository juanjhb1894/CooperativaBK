package com.example.cooperativa;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cooperativa.adapters.SliderAdapter;
import com.example.cooperativa.utils.SliderData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    View rootView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    FloatingActionButton fabSucursales, fabProductos, fabPreguntas;
    SliderView sliderView;
    TextView tvMyName;
    View inflatedLayout;
    FrameLayout container;
    ImageView imgCloseProfile;
    EditText etProfileAddress, etProfileCity, etProfilePhone, etProfileEmail;
    Button btnUpdateProfile;
    boolean profileShown;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fabSucursales = (FloatingActionButton) rootView.findViewById(R.id.fabSucursales);
        fabProductos = (FloatingActionButton) rootView.findViewById(R.id.fabProductos);
        fabPreguntas = (FloatingActionButton) rootView.findViewById(R.id.fabPreguntas);
        sliderView = (SliderView) rootView.findViewById(R.id.slider);
        tvMyName = (TextView) rootView.findViewById(R.id.tvMyName);
        container = (FrameLayout) rootView.findViewById(R.id.flProfileContainer);

        fabPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!profileShown)
                {
                    startActivity(new Intent(getActivity(), FAQActivity.class));
                }
            }
        });

        fabProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!profileShown) {
                    ///startActivity(new Intent(getActivity(), ProductsActivity.class));
                }
            }
        });

        tvMyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!profileShown)
                {
                    profileShown = true;
                    inflatedLayout = getLayoutInflater().inflate(R.layout.user_profile, null, false);
                    Animation slide_up = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_up);
                    inflatedLayout.setAnimation(slide_up);
                    container.addView(inflatedLayout);

                    imgCloseProfile = (ImageView) inflatedLayout.findViewById(R.id.imgCloseProfile);
                    etProfileAddress = (EditText) inflatedLayout.findViewById(R.id.etProfileAddress);
                    etProfileCity = (EditText) inflatedLayout.findViewById(R.id.etProfileCity);
                    etProfilePhone = (EditText) inflatedLayout.findViewById(R.id.etProfilePhone);
                    etProfileEmail= (EditText) inflatedLayout.findViewById(R.id.etProfileEmail);
                    btnUpdateProfile = (Button) inflatedLayout.findViewById(R.id.btnUpdateProfile);

                    imgCloseProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            container.removeAllViews();
                            profileShown = false;
                        }
                    });

                    btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText(getString(R.string.done))
                                    .setContentText(getString(R.string.updated_info))
                                    .setConfirmText(getString(R.string.okey))
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.cancel();
                                        }
                                    })
                                    .show();
                        }
                    });
                }
                else
                {

                }

            }
        });

        fabSucursales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!profileShown) {
                    startActivity(new Intent(getActivity(), MapsActivity.class));
                }
            }
        });

        String[] links = {"https://i0.wp.com/coopnaseju.com.do/wp-content/uploads/2022/08/Logotipo-Asamblea-PNG_Mesa-de-trabajo-1-e1659889420325.png",
                "https://s1.slideshowes.com/store/data/000179939_1-01e7b81359f2d8168ac691617f9db939.png",
                "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png"};

        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        for(int i = 0; i <  links.length; i++)
        {
            sliderDataArrayList.add(new SliderData(links[i]));
        }

        SliderAdapter adapter = new SliderAdapter(getActivity(), sliderDataArrayList);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }
}