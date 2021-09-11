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

public class InteretAdapter extends ArrayAdapter<Epargne> {
    private ArrayList<Epargne> listeComptes;
    private Context context;
    private int viewRes;
    private Resources res;

    public InteretAdapter(Context context, int viewResId, ArrayList<Epargne> listeComptes) {
        super(context, viewResId, listeComptes);
        this.listeComptes = listeComptes;
        this.context = context;
        this.viewRes = viewResId;
        this.res = context.getResources();
    }

    @Override
    public int getCount() {
        return listeComptes.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.
                    LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }
        final Epargne compte = listeComptes.get(position);
        if (compte != null) {
            final TextView numeroInteret = (TextView) view.findViewById(R.id.txtvCompteInteret);
            final TextView txtvSoldeAvant = (TextView) view.findViewById(R.id.txtvSoldeAvant);
            float soldeCompteAvant = compte.getSoldeCompte();
            txtvSoldeAvant.setText(res.getString(R.string.solde_compte_avant) + " " + soldeCompteAvant);
            String numeroCompteEpargne = res.getString(R.string.numero_compte) + " " + compte.getNumeroCompte();
            numeroInteret.setText(numeroCompteEpargne);

            final TextView txtvSoldeApres = (TextView) view.findViewById(R.id.txtvSoldeApres);
            float soldeCompteApres = compte.getSoldeCompte() + compte.paiementInterets();
            txtvSoldeApres.setText(res.getString(R.string.solde_compte_apres) + " " + soldeCompteApres);
        }
        return view;
    }

}
