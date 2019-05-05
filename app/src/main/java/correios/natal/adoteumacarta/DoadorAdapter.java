package correios.natal.adoteumacarta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DoadorAdapter extends ArrayAdapter<Doador> {

    private ArrayList<Doador> doadores;

    public DoadorAdapter(@NonNull Context context, @NonNull ArrayList<Doador> doadores) {
        super(context, 0,doadores);
        this.doadores = doadores;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Doador doador = doadores.get(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_doador,null);

        TextView textViewNome = convertView.findViewById(R.id.txvNomeDoador);
        TextView textViewCell = convertView.findViewById(R.id.txvCellDoador);
        TextView textViewGift = convertView.findViewById(R.id.txvGiftDoador);
        TextView textViewStatus = convertView.findViewById(R.id.txvStatusDoador);

        textViewNome.setText(doador.getNome());
        textViewCell.setText(doador.getCell());
        textViewGift.setText(doador.getGift());
        textViewStatus.setText(doador.getStatus());

        return  convertView;
    }
}
