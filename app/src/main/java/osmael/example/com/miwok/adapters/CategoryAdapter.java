package osmael.example.com.miwok.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import osmael.example.com.miwok.R;
import osmael.example.com.miwok.fragments.ColorsFragment;
import osmael.example.com.miwok.fragments.FamilyFragment;
import osmael.example.com.miwok.fragments.NumbersFragment;
import osmael.example.com.miwok.fragments.PhrasesFragment;
import osmael.example.com.miwok.model.Word;

/**
 * {@link CategoryAdapter} é um {@link FragmentPagerAdapter} que fornece
 * o layout para cada item da lista baseado em uma fonte de dados na qual
 * é uma lista de objetos {@link Word}.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    /** Contexto do aplicativo */
    private Context mContext;

    /**
     * Cria um novo objeto {@link CategoryAdapter}
     *
     * @param context é o contexto do aplicativo.
     * @param fm é o gerenciador de fragment que irá manter cada estado do fragment
     *           no adapter através de swipes.
     */
    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    /**
     * Retorna o total de número de páginas.
     */
    @Override
    public int getCount() {
        return 4;
    }

    /**
     * Retorna o {@link Fragment} que deve ser exibido para o respectivo número da página.
     */
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1) {
            return new FamilyFragment();
        } else if (position == 2) {
            return new ColorsFragment();
        } else {
            return new PhrasesFragment();
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return mContext.getString(R.string.category_numbers);
        } else if (position == 1) {
            return mContext.getString(R.string.category_family);
        } else if (position == 2) {
            return mContext.getString(R.string.category_colors);
        } else {
            return mContext.getString(R.string.category_phrases);
        }

    }

}