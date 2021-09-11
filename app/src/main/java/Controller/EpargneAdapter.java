package Controller;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.guichet_automatique.R;

import java.util.ArrayList;

public class EpargneAdapter extends ArrayAdapter<Epargne> {

    private ArrayList<Epargne> epargneList;
    private Context context;
    private int viewRes;
    private Resources res;

    public EpargneAdapter(Context context, int textViewResourceId, ArrayList<Epargne> epargne){
        super(context, textViewResourceId, epargne);
        this.epargneList = epargne;
        this.context = context;
        this.viewRes = textViewResourceId;
        this.res = context.getResources();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.
                    LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }
        final Epargne epargne = epargneList.get(position);
        if (epargne != null) {
            final TextView numCompte = (TextView) view.findViewById(R.id.txtvNumCompte);
            final TextView soldeEpargne =(TextView)view.findViewById(R.id.txtvMontantEpargne);

            String varNumCompte =res.getString(R.string.numero_compte)+"  "+epargne.getNumeroCompte();
            numCompte.setText(varNumCompte);

            String varSoldeEpargne =res.getString(R.string.soldeEpargne)+"  "+epargne.getSoldeCompte();
            soldeEpargne.setText(varSoldeEpargne);
        }
        return view;

    }
}