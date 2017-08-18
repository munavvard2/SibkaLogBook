package tk.munavvar.sibkalogbook;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListLogFragment extends Fragment {


    public ListLogFragment() {
        // Required empty public constructor
    }

    LogDbHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView =  inflater.inflate(R.layout.fragment_list_log, container, false);


        Button btnaddnew = (Button) rootView.findViewById(R.id.add_new_log_button);
        btnaddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.maincontent , new AddLogFragment()).commit();
            }
        });

        dbHelper = new LogDbHelper(getContext());


        final Cursor cursor = dbHelper.getAllLog();
        String [] columns = new String[] {
                LogDbHelper.LOG_COLUMN_ID,
                LogDbHelper.LOG_COLUMN_LOGDATA,
                LogDbHelper.LOG_COLUMN_LOGDATE
        };
        int [] widgets = new int[] {
                R.id.single_list_id,
                R.id.single_list_log_data,
                R.id.single_list_date
        };

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.single_list_log_history,
                cursor, columns, widgets, 0);
        ListView lv = (ListView) rootView.findViewById(R.id.list_history);
        lv.setAdapter(cursorAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return rootView;
    }

}
