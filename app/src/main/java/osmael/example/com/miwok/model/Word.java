package osmael.example.com.miwok.model;

/**
 * Created by root on 09/02/17.
 */

/**
 * {@link Word} representa um vocabulário de palavras
 * que o usuário quer aprender.
 *
 * Contêm uma traduação padrão (defaultTranslate) e
 * uma tradução referente ao idioma Miwok (miwokTranslate)
 * para cada palavra.
 *
 */
public class Word {

    /** Tradução padrão para uma palavra (word) */
    private String mMiwokTranslate;

    /** Tradução Miwok para uma palavra (word) */
    private String mDefaultTranslate;

    /** ID do recurso de imagem para uma palavra (word) */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** ID do recurso de áudio para uma palavra (word) */
    private int mAudioResourceId;

    /** Valor constante que representa uma imagem que não está provida para Word */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Criando um novo objeto.
     *
     * @param defaultTranslate é uma word (palavra) em que o usuário está familiariazado, neste
     *                         exemplo tomamos como padrão o idioma inglês.
     * @param miwokTranslate é uma word (palavra) no idioma Miwok.
     * @param audioResourceId é o ID do recurso do arquivo de áudio associado a essa classe (Word).
     */
    public Word(String defaultTranslate, String miwokTranslate, int audioResourceId) {
        this.mDefaultTranslate = defaultTranslate;
        this.mMiwokTranslate = miwokTranslate;
        this.mAudioResourceId = audioResourceId;
    }

    /**
     * Criando um novo objeto.
     *
     * @param defaultTranslate é uma word (palavra) em que o usuário está familiariazado, neste
     *                         exemplo tomamos como padrão o idioma inglês.
     * @param miwokTranslate é uma word (palavra) no idioma Miwok.
     * @param imageResourceId é o ID do drawable para a imagem associada a essa classe (Word).
     * @param audioResourceId é o ID do recurso do arquivo de áudio associado a essa classe (Word).
     */
    public Word(String defaultTranslate, String miwokTranslate, int imageResourceId, int audioResourceId) {
        this.mDefaultTranslate = defaultTranslate;
        this.mMiwokTranslate = miwokTranslate;
        this.mImageResourceId = imageResourceId;
        this.mAudioResourceId = audioResourceId;
    }

    public String getMiwokTranslate() {
        return mMiwokTranslate;
    }

    public String getDefaultTranslate() {
        return mDefaultTranslate;
    }

    /**
     * @return o ID do recurso da imagem de word.
     */
    public int getmImageResourceId() {
        return mImageResourceId;
    }

    /**
     * @return o ID do recurso de áudio de word.
     */
    public int getmAudioResourceId() {
        return mAudioResourceId;
    }

    /**
     * Retorna se há ou não uma imagem para esta palavra
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}