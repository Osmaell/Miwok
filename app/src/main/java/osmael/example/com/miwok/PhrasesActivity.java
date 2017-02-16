package osmael.example.com.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import osmael.example.com.miwok.adapters.WordAdapter;
import osmael.example.com.miwok.model.Word;

public class PhrasesActivity extends AppCompatActivity {

    /** Lida com a reprodução de todos os arquivos de áudio */
    private MediaPlayer mMediaPlayer;

    /** Lida com o foco do áudio durante a reprodução de  arquivo de som */
    private AudioManager mAudioManager;

    /**
     * Esse listerner é acionado quando o {@link MediaPlayer} terminou de
     * reproduzir o arquivo de áudio.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Agora que esse áudio terminou de tocar, libera os recursos do media player.
            releaseMediaPlayer();
        }

    };

    /**
     * Esse listener é acionado sempre que o foco de áudio muda.
     * (ex. nós ganhamos ou perdemos o foco do áudio por cause de outro app ou dispositivo).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {

        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                // AUDIOFOCUS_LOSS_TRANSIENT significa que perdemos o foco do áudio por
                // um curto período de tempo. AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK significa
                // que a nossa aplicação tem permissão para continuar tocando, mas com um
                // volume de som baixo. Trataremos ambos os casos da mesma forma porque o
                // nosso aplicativo está reproduzindo arquivos de som curtos.

                // pausa a reprodução e reinicia o leitor para o início do arquivo. Dessa maneira,
                // vamos poder reproduzir a palavra desde o ínicio quando retornamos a reprodução.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // AUDIOFOCUS_GAIN significa que recuperamos o foco e podemos retornar a
                // reprodução.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // AUDIOFOCUS_LOSS significa que nós perdemos o foco do áudio e paramos a
                // reprodução e limpamos os recursos.
                releaseMediaPlayer();
            }

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Criando e configurando o {@link AudioManager} para solicitar o foco do áudio
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Criando uma lista de words (palavras)
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        // Criando um {@link WordAdapter}, cuja fonte de dados é uma {@link Word}s. O adapter
        // sabe como criar uma list_item (item de lista) para cada item na lista.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        // Encontra um objeto {@link ListView} na hierarquia de view de {@link Activity}.
        // Deve haver um {@link ListView} com uma view que tenha um ID chamado list, que é
        // declarado no arquivo de layout word_list.xml.
        ListView listView = (ListView) findViewById(R.id.list);

        // Faz o {@link ListView} usar o {@link WordAdapter} criado acima, então
        // o {@link ListView} exibirá itens de lista para cada {@link Word} na lista.
        listView.setAdapter(adapter);

        // Define um listener para tocar o áudio quando um item da lista for clicado
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Libera o media player se ele já existe, porque nós estamos preste
                // a tocar um arquivo de áudio diferente.
                releaseMediaPlayer();

                // Obtem um {@link Word} na posição que o usuário clicou
                Word word = words.get(position);

                // Requisita um foco de áudio assim, para reproduzir o arquivo de áudio. O
                // aplicativo precisa tocar um curto arquivo de áudio, então iremos requisitar
                // o foco do áudio com uma quantia curta de tempo com AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(
                        mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Nós temos um foco de áudio agora

                    // Criando e configurando {@link MediaPlayer} para o recurso de áudio
                    // associado com a palavra (word) atual
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmAudioResourceId());

                    // Ínicia o arquivo de áudio
                    mMediaPlayer.start();

                    // Configura um listener em um media player, com isso nós podemos parar
                    // e liberar o media player uma vez que o som tenha finalizado sua
                    // reprodução.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }

        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Quando a activity é parada, liberamos os recursos do media player porque
        // não queremos está tocando mais sons.
        releaseMediaPlayer();
    }

    /**
     * Limpa o media player liberando seus recursos.
     */
    private void releaseMediaPlayer() {

        // Se o media player não for nulo, então ele pode está tocando um som.
        if (mMediaPlayer != null) {

            // Indepentemente do estado atual do media player, liberamos seus recursos
            // porque não precisamos mais deles.
            mMediaPlayer.release();

            // Define o media player novamente para nulo. Para o nosso código,
            // decidimos que a configuração do media player para nulo é uma caminho
            // fácil para dizer que o media player não está configurado para tocar
            // um arquivo de áudio no momento.
            mMediaPlayer = null;

            // Independente de termos sido concebidos ou não com um foco de áudio, abandona ele.
            // Isso também cancela o registro de um onAudioFocusChangeListener, para que não
            // obtenhamos mais callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }

    }

}