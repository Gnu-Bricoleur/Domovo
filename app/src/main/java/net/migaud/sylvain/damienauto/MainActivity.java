package net.migaud.sylvain.damienauto;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {



    Button button[] = new Button[100];
    int etatBoutons[] = new int[100];
    String URLServeur;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Intent intent = new Intent(MainActivity.this, groupe_volets.class);
                startActivity(intent);
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar myToolbar = (Toolbar) findViewById(R.id.menu);
        //setSupportActionBar(myToolbar);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();//Tres sale ;) faudrai mettre dans un thread
        StrictMode.setThreadPolicy(policy);

        verifyStoragePermissions(this);






        File myDir = new File(Environment.getExternalStorageDirectory() + File.separator + "Domovo"); //pour créer le repertoire dans lequel on va mettre notre fichier
        Boolean success=true;
        if (!myDir.exists()) {
            success = myDir.mkdir(); //On crée le répertoire (s'il n'existe pas!!)
        }
       /*
        String content = "hello world";
        File file;
        FileOutputStream outputStream;
        try {
            verifyStoragePermissions(this);
            file = new File(Environment.getExternalStoragePublicDirectory(null), "/Domovo/JeSuisLa.txt");

            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.close();
            System.out.println ("YEEEEESSSSSSSSSSS !!!!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println ("Aaaaaaarrghhhh !!!!!!!!!!!!");
        }
        */

        try {
            URLServeur = lireUrl(Environment.getExternalStorageDirectory() + File.separator+ "Domovo"+ File.separator+"adresseserveur.txt");
        }
        catch(Exception e) {

            URLServeur = "www.migaud.net/dam.php";
        }

        final Context context2 = getApplicationContext();
        final CharSequence text2 = URLServeur;
        final int duration2 = Toast.LENGTH_SHORT;
        Toast toast2 = Toast.makeText(context2, text2, duration2);
        toast2.show();



        // ########################################################################################### Salon Est

        button[1] = findViewById(R.id.button_id1);
        button[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_est", "0");
            }
        });

        button[2] = findViewById(R.id.button_id2);
        button[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_est", "17");
            }
        });

        button[3] = findViewById(R.id.button_id3);
        button[3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_est", "35");
            }
        });

        button[4] = findViewById(R.id.button_id4);
        button[4].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_est", "60");
            }
        });

        button[5] = findViewById(R.id.button_id5);
        button[5].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_est", "81");
            }
        });

        button[6] = findViewById(R.id.button_id6);
        button[6].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_est", "100");
            }
        });


        // ########################################################################################### Salon Sud


        button[11] = findViewById(R.id.button_id11);
        button[11].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_sud", "0");
            }
        });

        button[12] = findViewById(R.id.button_id12);
        button[12].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_sud", "17");
            }
        });

        button[13] = findViewById(R.id.button_id13);
        button[13].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_sud", "35");
            }
        });

        button[14] = findViewById(R.id.button_id14);
        button[14].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_sud", "60");
            }
        });

        button[15] = findViewById(R.id.button_id15);
        button[15].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_sud", "81");
            }
        });

        button[16] = findViewById(R.id.button_id16);
        button[16].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("salon_sud", "100");
            }
        });





        // ########################################################################################### Balcon


        button[21] = findViewById(R.id.button_id21);
        button[21].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("balcon", "0");
            }
        });

        button[22] = findViewById(R.id.button_id22);
        button[22].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("balcon", "17");
            }
        });

        button[23] = findViewById(R.id.button_id23);
        button[23].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("balcon", "35");
            }
        });

        button[24] = findViewById(R.id.button_id24);
        button[24].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("balcon", "60");
            }
        });

        button[25] = findViewById(R.id.button_id25);
        button[25].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("balcon", "81");
            }
        });

        button[26] = findViewById(R.id.button_id26);
        button[26].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("balcon", "100");
            }
        });



        // ########################################################################################### Chambre Est


        button[31] = findViewById(R.id.button_id31);
        button[31].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_est", "0");
            }
        });

        button[32] = findViewById(R.id.button_id32);
        button[32].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_est", "17");
            }
        });

        button[33] = findViewById(R.id.button_id33);
        button[33].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_est", "35");
            }
        });

        button[34] = findViewById(R.id.button_id34);
        button[34].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_est", "60");
            }
        });

        button[35] = findViewById(R.id.button_id35);
        button[35].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_est", "81");
            }
        });

        button[36] = findViewById(R.id.button_id36);
        button[36].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_est", "100");
            }
        });



        // ########################################################################################### Chambre Sud









        button[41] = findViewById(R.id.button_id41);
        button[41].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_sud", "0");
            }
        });

        button[42] = findViewById(R.id.button_id42);
        button[42].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_sud", "17");
            }
        });

        button[43] = findViewById(R.id.button_id43);
        button[43].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_sud", "35");
            }
        });

        button[44] = findViewById(R.id.button_id44);
        button[44].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_sud", "60");
            }
        });

        button[45] = findViewById(R.id.button_id45);
        button[45].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_sud", "81");
            }
        });

        button[46] = findViewById(R.id.button_id46);
        button[46].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch1_sud", "100");
            }
        });




        // ########################################################################################### Bureau




        button[51] = findViewById(R.id.button_id51);
        button[51].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch2", "0");
            }
        });

        button[52] = findViewById(R.id.button_id52);
        button[52].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch2", "17");
            }
        });

        button[53] = findViewById(R.id.button_id53);
        button[53].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch2", "35");
            }
        });

        button[54] = findViewById(R.id.button_id54);
        button[54].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch2", "60");
            }
        });

        button[55] = findViewById(R.id.button_id55);
        button[55].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch2", "81");
            }
        });

        button[56] = findViewById(R.id.button_id56);
        button[56].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch2", "100");
            }
        });





        // ########################################################################################### Chambre d'amis






        button[61] = findViewById(R.id.button_id61);
        button[61].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch3", "0");
            }
        });


        button[62] = findViewById(R.id.button_id62);
        button[62].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch3", "17");
            }
        });


        button[63] = findViewById(R.id.button_id63);
        button[63].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch3", "35");
            }
        });


        button[64] = findViewById(R.id.button_id64);
        button[64].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch3", "60");
            }
        });


        button[65] = findViewById(R.id.button_id65);
        button[65].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch3", "81");
            }
        });


        button[66] = findViewById(R.id.button_id66);
        button[66].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                rocketWeb("ch3", "100");
            }
        });




        //mise en evidence de boutons representant les etats en cours


        for(int i = 0; i < etatBoutons.length; i++) {
            etatBoutons[i] = 0;
        }
        etatBoutons = parseEtats(etatBoutons);


        colorieLesBoutons(etatBoutons);


        //Check regulierement si jamais quelqu'un a modifie la position des volets sur une autre plateforme
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(new Runnable() {
                 public void run() {
                    // call service
                     etatBoutons = parseEtats(etatBoutons);
                     colorieLesBoutons(etatBoutons);
                 }
              }, 0, 1, TimeUnit.MINUTES);



    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public static String convertStreamToString2(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
          sb.append(line);
        }
        reader.close();
        return sb.toString();
    }

    public static String lireUrl (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString2(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }



    public void colorieLesBoutons (int[] etatBoutons){

        //button[66].setBackgroundColor(Color.GREEN);
        for(int i = 0; i < 7 ; i++)//nbr de blocs
        {
            for(int j = 1; j < 7 ; j++)//nbr de boutons
            {
                if (etatBoutons[i*10 + j] == 1)
                {
                    button[i*10 + j].setEnabled(false);
                    //button[i*10 + j].setBackgroundColor(Color.GREEN);
                }
                else
                {
                    button[i*10 + j].setEnabled(true);
                }
            }
        }
    }


    public void griseBoutons(int numBloc){
        ScheduledExecutorService scheduler1 = Executors.newScheduledThreadPool(1);
        for(int j = 1; j < 7 ; j++)//nbr de boutons
            {
                    button[numBloc*10 + j].setEnabled(false);
            }

            new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // call your function
                colorieLesBoutons(etatBoutons);
            }
        }, 30000);





    }


    public void rocketWeb(String nom, String etat){
        String correspondances[] = {"salon_est","salon_sud","balcon","ch1_est", "ch1_sud", "ch2", "ch3"};
        String pourcents[] = {"0","17","35","60","81","100"};
        final Context context = getApplicationContext();
        final CharSequence text = "Envoi de la requête ...";
        final int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        try{
            URL url = new URL(URLServeur + "&commande_volet&nom=" + nom +"&etat=" + etat);
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //On etabli le num du bouton
                int i = Arrays.asList(correspondances).indexOf(nom);
                int numBouton = i*10 + Arrays.asList(pourcents).indexOf(etat) +1;
                //on nettoie les 1 du bloc de bouton
                for(int j = 1; j < 7 ; j++)//nbr de boutons
                {
                    etatBoutons[i*10 + j] = 0;
                }
                // on place le 1
                etatBoutons[numBouton] = 1;
                //on met en couleur
                colorieLesBoutons(etatBoutons);

/*                //Pour Test
                final Context context3 = getApplicationContext();
                final CharSequence text3 = String.valueOf(numBouton);
                final int duration3 = Toast.LENGTH_SHORT;
                Toast toast3 = Toast.makeText(context3, text3, duration3);
                toast3.show();
*/
                griseBoutons(i);

                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    //readStream(in);
                } finally {
                    urlConnection.disconnect();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }
        catch(MalformedURLException ex){
            //do exception handling here
        }
    }



    public int[] parseEtats(int[] etatBoutons){
        final Context context = getApplicationContext();
        final CharSequence text = "Recherche de l'état actuel des boutons ...";
        final int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        try{
            URL url = new URL(URLServeur + "&infos_volets");
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    //readStream(in);
                    String x = convertStreamToString(in);

                    String sub[] = new String[10];

                    sub[0] = x.substring(x.indexOf(";salon_est;") + 11, x.indexOf(";salon_est;") + 12 );//isole etat salon est
                    sub[1] = x.substring(x.indexOf(";salon_sud;") + 11, x.indexOf(";salon_sud;") + 12 );
                    sub[2] = x.substring(x.indexOf(";balcon;") + 8, x.indexOf(";balcon;") + 9 );
                    sub[3] = x.substring(x.indexOf(";ch1_est;") + 9, x.indexOf(";ch1_est;") + 10 );
                    sub[4] = x.substring(x.indexOf(";ch1_sud;") + 9, x.indexOf(";ch1_sud;") + 10 );
                    sub[5] = x.substring(x.indexOf(";ch2;") + 5, x.indexOf(";ch2;") + 6 );
                    sub[6] = x.substring(x.indexOf(";ch3;") + 5, x.indexOf(";ch3;") + 6 );
                      //POUR TEST
                        final Context context1 = getApplicationContext();
                        final CharSequence text1 = sub[0] + " " + sub[1] + " " + sub[2] + " " + sub[3] + " " + sub[4] + " " + sub[5] + " " + sub[6];
                        final int duration1 = Toast.LENGTH_LONG;
                        Toast toast1 = Toast.makeText(context1, text1, duration1);
                        toast1.show();

                    for(int i = 0; i < 7 ; i++)//nbr de blocs
                    {
                        for(int j = 1; j < 7 ; j++)//nbr de boutons
                        {
                            etatBoutons[i*10 + j] = quelEstLaCouleurDeMonBouton(j-1, sub[i]);   //Pourquoi diable un -1 ?
                        }
                    }




                } finally {
                    urlConnection.disconnect();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }
        catch(MalformedURLException ex){
            //do exception handling here
        }
        return etatBoutons;
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


    public int quelEstLaCouleurDeMonBouton(int bouton, String substring)
    {
        if (substring != null) {
            switch (bouton) {
                case 0:
                    if (substring.equals("0")) {
                        return 1;
                    } else {
                        return 0;
                    }
                case 1:
                    if (substring.equals("1")) {
                        return 1;
                    } else {
                        return 0;
                    }
                case 2:
                    if (substring.equals("2")) {
                        return 1;
                    } else {
                        return 0;
                    }
                case 3:
                    if (substring.equals("3")) {
                        return 1;
                    } else {
                        return 0;
                    }
                case 4:
                    if (substring.equals("4")) {
                        return 1;
                    } else {
                        return 0;
                    }
                case 5:
                    if (substring.equals("5")) {
                        return 1;
                    } else {
                        return 0;
                    }
            }
        }
        return 0;
    }




}



