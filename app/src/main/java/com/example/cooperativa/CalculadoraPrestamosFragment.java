package com.example.cooperativa;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculadoraPrestamosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculadoraPrestamosFragment extends Fragment {

    View rootView;
    SeekBar sbPlazo;
    TextView textView4, tvCuota;
    Spinner spnTipoPrenstamo, spnTasa;
    String strTipoPrestamo;
    ArrayAdapter<String> adapter;
    String[] arrayTasa;
    EditText etMonto;
    int periodo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculadoraPrestamosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrestamosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculadoraPrestamosFragment newInstance(String param1, String param2) {
        CalculadoraPrestamosFragment fragment = new CalculadoraPrestamosFragment();
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
        rootView =  inflater.inflate(R.layout.fragment_calculadora_prestamo, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sbPlazo = (SeekBar) rootView.findViewById(R.id.sbPlazo);
        textView4 = (TextView) rootView.findViewById(R.id.textView4);
        spnTipoPrenstamo = (Spinner) rootView.findViewById(R.id.spnTipoPrenstamo);
        tvCuota = (TextView) rootView.findViewById(R.id.tvCuota);
        etMonto = (EditText)rootView.findViewById(R.id.etMonto);
        spnTasa= (Spinner) rootView.findViewById(R.id.spnTasa);

        etMonto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcularCuotas();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        sbPlazo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String updatedProgress = "";
                periodo = i;
                updatedProgress = i + " " + getString(R.string.meses);
                textView4.setText(updatedProgress);

                calcularCuotas();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        List<String> list = Arrays.asList(getResources().getStringArray(R.array.arrPrestamos));
        spnTipoPrenstamo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ///Toast.makeText(getActivity(), list.get(i), Toast.LENGTH_SHORT).show();
                strTipoPrestamo = list.get(i);
                periodo = i;
                switch (i)
                {
                    case 0:
                        spnTasa.setAdapter(null);
                        arrayTasa = new String[] {"5"};
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayTasa);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTasa.setAdapter(adapter);
                        break;

                    case 1:
                        spnTasa.setAdapter(null);
                        arrayTasa = new String[] {"8"};
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayTasa);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTasa.setAdapter(adapter);
                        break;

                    case 2:
                        spnTasa.setAdapter(null);
                        arrayTasa = new String[] {"10"};
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayTasa);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTasa.setAdapter(adapter);
                        break;

                    case 3:
                        spnTasa.setAdapter(null);
                        arrayTasa = new String[] {"5"};
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayTasa);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTasa.setAdapter(adapter);
                        break;

                    case 4:
                        spnTasa.setAdapter(null);
                        arrayTasa = new String[] {"5"};
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayTasa);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTasa.setAdapter(adapter);
                        break;

                    case 5:
                        spnTasa.setAdapter(null);
                        arrayTasa = new String[] {"8"};
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayTasa);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTasa.setAdapter(adapter);
                        break;

                    case 6:
                        spnTasa.setAdapter(null);
                        arrayTasa = new String[] {"5"};
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayTasa);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTasa.setAdapter(adapter);
                        break;

                    case 7:
                        spnTasa.setAdapter(null);
                        arrayTasa = new String[] {"10"};
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayTasa);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTasa.setAdapter(adapter);
                        break;

                    case 8:
                        spnTasa.setAdapter(null);
                        arrayTasa = new String[] {"5"};
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayTasa);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTasa.setAdapter(adapter);
                        break;

                    case 9:
                        spnTasa.setAdapter(null);
                        arrayTasa = new String[] {"5"};
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayTasa);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTasa.setAdapter(adapter);
                        break;
                }

                calcularCuotas();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spnTasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calcularCuotas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void calcularCuotas()
    {
        if( periodo>0 &&
                !etMonto.getText().toString().equals("") &&
                !etMonto.getText().toString().replace("","").equals(""))
        {
            double interes = (Double.parseDouble(spnTasa.getSelectedItem().toString())/100);
            double C = Double.parseDouble(etMonto.getText().toString());
            double prestamo = C/((1-(Math.pow((1/(1+interes)), periodo)))/interes);
            tvCuota.setText(String.format("%.02f", prestamo));
        }
        else
        {
            tvCuota.setText(getString(R.string.infinito));
        }
    }
}