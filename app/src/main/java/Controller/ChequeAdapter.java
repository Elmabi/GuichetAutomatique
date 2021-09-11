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

public class ChequeAdapter extends ArrayAdapter <Cheque> {
    private ArrayList<Cheque> listeCheques;
    private Context context;
    private int viewRes;
    private Resources res;

    public ChequeAdapter(Context context, int viewResId, ArrayList<Cheque> listeComptes) {
        super(context,viewResId,listeComptes);
        this.listeCheques = listeComptes;
        this.context = context;
        this.viewRes = viewResId;
        this.res = context.getResources();
    }

    @Override
    public int getCount() {
        return listeCheques.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.
                    LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }
        final Compte compte = listeCheques.get(position);
        if (compte != null) {
            final TextView textViewNumero = (TextView) view.findViewById(R.id.txtvNumero);
            final TextView textViewSolde = (TextView) view.findViewById(R.id.txtvSolde);
            String numero = res.getString(R.string.numero_compte) + " " + compte.getNumeroCompte();
            float solde = compte.getSoldeCompte();
            textViewNumero.setText(numero);
            textViewSolde.setText(res.getString(R.string.solde_compte)+ " " + solde);
        }
        return view;
    }
}
