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
 * {@link WordAdapter} é uma {@link ArrayAdapter} que fornece o layout para cada
 * item da lista baseado em uma fonte de dados, que é uma lista de objetos
 * {@link Word}.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    /** ID de recurso para a cor de fundo para a lista de words (palavras) */
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

        // checa se uma view existente está sendo usada, de outra forma infla a view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Obtém o objeto {@link Word} localizado na posição da lista
        Word currentWord = getItem(position);

        // Encontra o TextView no layout list_item.xml com o ID miwok_text_view.
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        // Obtém o miwok translation da objeto currentWord e define o texto
        // passado em Miwok TextView
        numberTextView.setText(currentWord.getMiwokTranslate());

        // Encontra o TextView no layout list_item.xml com o ID default_text_view.
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);

        // Obtém o default translation da objeto currentWord e define o texto passado
        // em Miwok TextView
        defaultTextView.setText(currentWord.getDefaultTranslate());

        // Encontra a ImageView no layout list_item.xml com o ID image.
        ImageView imageView = (ImageView)  listItemView.findViewById(R.id.image_miwok);

        // Checa se uma imagem é forneceida pelo objeto word ou não
        if (currentWord.hasImage()) {
            // Se uma imagem é disponível, exibe a imagem fornecida baseado no resource ID
            imageView.setImageResource(currentWord.getmImageResourceId());
            // Certifique-se de que a view é visível
            imageView.setVisibility(View.VISIBLE);
        } else {
            // De outra forma esconde o ImagemView (definindo a visibilidade para GONE)
            imageView.setVisibility(View.GONE);
        }

        // Definindo o tema da cor para os itens da lista
        View textContainer = listItemView.findViewById(R.id.text_container);

        // Achar a cor para a qual o recurso de ID mapeia
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        // Definindo a cor de plano de fundo para o textContainer
        textContainer.setBackgroundResource(mColorResourceId);

        // Retorna todo o layout do item da lista (Contendo 2 TextViews) para que possa
        // ser mostrado o ListView
        return listItemView;
    }

}