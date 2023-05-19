package com.example.cooperativa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cooperativa.adapters.HitorialPrestamosAdapter;
import com.example.cooperativa.models.HistorialPrestamosModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistorialPrestamosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistorialPrestamosFragment extends Fragment {

    View rootView;
    ListView lstHistorialPrestamos;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistorialPrestamosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistorialPrestamosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistorialPrestamosFragment newInstance(String param1, String param2) {
        HistorialPrestamosFragment fragment = new HistorialPrestamosFragment();
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
        rootView = inflater.inflate(R.layout.fragment_historial_prestamos, container, false);
        return  rootView;
    }
    ArrayList<HistorialPrestamosModel> dataModels;
    HitorialPrestamosAdapter adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lstHistorialPrestamos = (ListView) rootView.findViewById(R.id.lstHistorialPrestamos);

        dataModels= new ArrayList<>();

        dataModels.add(new HistorialPrestamosModel("Expreso", "1", "20000","September 23, 2008"));
        dataModels.add(new HistorialPrestamosModel("Normal", "2", "1000000","February 9, 2009"));
        dataModels.add(new HistorialPrestamosModel("Enseres", "4", "50000","April 27, 2009"));
        dataModels.add(new HistorialPrestamosModel("Expreso", "1", "20000","September 23, 2008"));
        dataModels.add(new HistorialPrestamosModel("Normal", "3", "1000000","February 9, 2009"));
        dataModels.add(new HistorialPrestamosModel("Enseres", "3", "50000","April 27, 2009"));
        dataModels.add(new HistorialPrestamosModel("Expreso", "1", "20000","September 23, 2008"));
        dataModels.add(new HistorialPrestamosModel("Normal", "4", "1500000","February 9, 2009"));
        dataModels.add(new HistorialPrestamosModel("Enseres", "2", "50000","April 27, 2009"));

        adapter= new HitorialPrestamosAdapter(dataModels, getActivity().getApplicationContext());
        lstHistorialPrestamos.setAdapter(adapter);

        lstHistorialPrestamos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HistorialPrestamosModel dataModel= dataModels.get(position);
                /*
                Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                 */
            }
        });

    }
}