package osmael.example.com.miwok.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import osmael.example.com.miwok.R;
import osmael.example.com.miwok.model.Word;

/**
 * Created by root on 09/02/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Context context, List<Word> words) {
        super(context, 0, words);
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

        return listItemView;
    }

}