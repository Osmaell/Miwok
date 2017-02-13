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
    private String mMiwokTranslate;

    /* Tradução Miwok para uma palavra */
    private String mDefaultTranslate;

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private int mAudioResourceId;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String defaultTranslate, String miwokTranslate, int audioResourceId) {
        this.mDefaultTranslate = defaultTranslate;
        this.mMiwokTranslate = miwokTranslate;
        this.mAudioResourceId = audioResourceId;
    }

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

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}