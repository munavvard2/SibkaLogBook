package tk.munavvar.sibkalogbook;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddLogFragment extends Fragment {


    public AddLogFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_add_log, container, false);

        final EditText edtData = (EditText) rootView.findViewById(R.id.add_log_data);
        Button btnAddData = (Button) rootView.findViewById(R.id.add_log_button);
        Button btnShowLogData = (Button) rootView.findViewById(R.id.show_history_log_button);

        btnShowLogData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.maincontent , new ListLogFragment()).commit();

            }
        });

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = edtData.getText().toString();
                try{
                    LogDbHelper logDbHelper = new LogDbHelper(getContext());
                    boolean flag = logDbHelper.insertLog(data);
                    if(flag){
                        Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                        edtData.clearFocus();
                        edtData.setText("");
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    }
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Error!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return rootView;
    }

}
