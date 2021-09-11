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

public class ClientAdapter extends ArrayAdapter <Client> {
    private ArrayList<Client> listeClients;
    private Context context;
    private int viewRes;
    private Resources res;

    public ClientAdapter(Context context, int viewResId, ArrayList<Client> listeClients) {
        super(context,viewResId,listeClients);
        this.listeClients = listeClients;
        this.context = context;
        this.viewRes = viewResId;
        this.res = context.getResources();
    }

    @Override
    public int getCount() {
        return listeClients.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.
                    LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }
        final Client client = listeClients.get(position);
        if (client != null) {
            final TextView textViewNom = (TextView) view.findViewById(R.id.txtvNom);
            final TextView textViewPrenom = (TextView) view.findViewById(R.id.txtvPrenom);
            String nom = res.getString(R.string.nom_client) + " "+client.getNomClient();
            String prenom = res.getString(R.string.prenom_client)+ " " + client.getPrenomClient();
            textViewNom.setText(nom);
            textViewPrenom.setText(prenom);
        }
        return view;
    }
}
