package osmael.example.com.miwok.model;

/**
 * Created by root on 09/02/17.
 */

/**
 * {@link Word} representa um vocabulário de palavras quer
 * para aprender.
 *
 * Contêm uma traduação padrão (defaultTranslate) e
 * uma tradução referente ao idioma Miwok (miwokTranslate)
 * para cada palavra.
 *
 */
public class Word {

    /* Tradução padrão para uma palavra */
    private String miwokTranslate;

    /* Tradução Miwok para uma palavra */
    private String defaultTranslate;

    public Word(String defaultTranslate, String miwokTranslate) {
        this.defaultTranslate = defaultTranslate;
        this.miwokTranslate = miwokTranslate;
    }

    public String getMiwokTranslate() {
        return miwokTranslate;
    }

    public String getDefaultTranslate() {
        return defaultTranslate;
    }

}