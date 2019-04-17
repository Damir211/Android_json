package kz.damir211.followers;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView; // Иконка погоды
    private TextView textView; // Компонент для данных погоды

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);

        JSONFollowersTask task = new JSONFollowersTask(); // Создание потока определения погоды
        task.execute(); // Активация потока
    }

    // Кнопка "Обновить"
    public void onClick(View view) {
        new JSONFollowersTask().execute(); // Создание и активация потока определения погоды
    }

    // Класс отдельного асинхронного потока
    private class JSONFollowersTask extends AsyncTask<String, Void, followers> {

        // Тут реализуем фоновую асинхронную загрузку данных, требующих много времени
        @Override
        protected followers doInBackground(String... params) {
            return FollowersBuilder.buildFollowers();
        }
        // ----------------------------------------------------------------------------

        // Тут реализуем что нужно сделать после окончания загрузки данных
        @Override
        protected void onPostExecute(final followers followers) {
            super.onPostExecute(followers);

            // Устанавливаем картинку погоды
            imageView.post(new Runnable() { //  Используем синхронизацию с UI
                @Override
                public void run() {
                    // Если есть считанная иконка с web
                    if (followers.getIconData() != null) {
                        imageView.setImageBitmap(followers.getIconData()); // Установка иконки
                    } else {
                        imageView.setImageResource(R.mipmap.ic_launcher); // Установка своей иконки при ошибке
                    }
                    imageView.invalidate(); // Принудительная прорисовка картинки на всякий случай
                }
            });

            // Выдаем данные о погоде в компонент
            textView.post(new Runnable() { //  с использованием синхронизации UI
                @Override
                public void run() {
                    textView.setText("");
                    if (followers.getLogname(0) != null) {
                        textView.append(getString(R.string.title)+":\n\n");
                        for(int i = 0; i < 199;i++) {
                            if(followers.getLogname(i)!=null) {
                                textView.append("Login: " + followers.getLogname(i) + "\n");
                                textView.append("URL: " + followers.getLogurl(i) + "\n\n");
                            }else{
                                break;
                            }
                        }
                    } else {
                        textView.append("Нет данных!" + "\n");
                        textView.append("Проверьте доступность Интернета");
                    }
                }
            });

        }
        // ------------------------------------------------------------------------------------

    }

}
