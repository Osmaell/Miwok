package osmael.example.com.miwok.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import osmael.example.com.miwok.R;
import osmael.example.com.miwok.model.Word;

/**
 * Created by root on 09/02/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    /**
     * Cria um novo objeto {@link WordAdapter}.
     *
     * @param context é o contexto corrente (ex. Activity) onde o adaptador está sendo criado.
     * @param words é a lista de {@link Word}s a ser exibido.
     * @param colorResourceId é o ID de recurso para a cor de fundo (background color) para
     *                        essa lista de words (palavras).
     */
    public WordAdapter(Context context, List<Word> words, int colorResourceId) {
        super(context, 0, words);
        this.mColorResourceId = colorResourceId;
    }

    /**
     * Fornece uma view para algum tipo de AdapterView (ListView, GridView, etc.)
     *
     * @param position A posição na lista que este layout deve representar.
     * @param convertView A view reciclada que precisa ser repovoada.
     * @param parent O ViewGroup parente que é usado para inflar.
     *
     * @return Uma view para a posição no AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView numberTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        numberTextView.setText(currentWord.getMiwokTranslate());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslate());

        ImageView imageView = (ImageView)  listItemView.findViewById(R.id.image_miwok);

        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getmImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        // Definindo o tema da cor para os itens da lista
        View textContainer = listItemView.findViewById(R.id.text_container);

        // Achar a cor para a qual o recurso de ID mapeia
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        // Definindo a cor de plano de fundo para o textContainer
        textContainer.setBackgroundResource(mColorResourceId);

        return listItemView;
    }

}