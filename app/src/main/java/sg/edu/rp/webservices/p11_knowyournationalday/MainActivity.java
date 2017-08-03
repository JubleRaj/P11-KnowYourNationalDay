package sg.edu.rp.webservices.p11_knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.phoneNumber;
import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    ListView lvFacts;
    ArrayList<String> alValues = new ArrayList<String>();
    ArrayAdapter<String> aaValues;
    String items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout passPhrase =
                (LinearLayout) inflater.inflate(R.layout.passphrase, null);
        final EditText etPassphrase = (EditText) passPhrase
                .findViewById(R.id.editTextPassPhrase);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Login")
                .setCancelable(false)
                .setView(passPhrase)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if( etPassphrase.getText().toString().equals("738964")){
                            lvFacts = (ListView)findViewById(R.id.lvFacts);

                            alValues.add("Singapore National Day is on 9 Aug");
                            alValues.add("Singapore is 52 years old");
                            alValues.add("Theme is #OneNationTogether");
                            aaValues = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, alValues);
                            lvFacts.setAdapter(aaValues);
                            aaValues.notifyDataSetChanged();
                        } else {
                            finish();
                        }
                        //Toast.makeText(MainActivity.this, "You had entered " +
                                //etPassphrase.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Tally against the respective action item clicked
        //  and implement the appropriate action
        if (item.getItemId() == R.id.action_quit) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit?")
                    // Set text for the positive button and the corresponding
                    //  OnClickListener when it is clicked
                    .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    // Set text for the negative button and the corresponding
                    //  OnClickListener when it is clicked
                    .setNegativeButton("Not Really", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            // Create the AlertDialog object and return it
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else if (item.getItemId() == R.id.actionSend) {

            String [] list = new String[] { "SMS", "EMAIL" };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    // Set the list of items easily by just supplying an
                    //  array of the items
                    .setItems(list, new DialogInterface.OnClickListener() {
                        // The parameter "which" is the item index
                        // clicked, starting from 0
                        public void onClick(DialogInterface dialog, int which) {

                            for(int i = 0; i<alValues.size(); i++){
                                items += alValues.get(i) + "\n";
                            }
                            if (which == 0) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
                                intent.putExtra("sms_body", items);
                                startActivity(intent);
                            } else if (which == 1) {
                                // ACTION_SEND is used to indicate sending text
                                Intent email = new Intent(Intent.ACTION_SEND);
                                // Put essentials like email address, subject & body text
                                email.putExtra(Intent.EXTRA_SUBJECT,
                                        "Test Email from C347");
                                email.putExtra(Intent.EXTRA_TEXT, items);
                                // This MIME type indicates email
                                email.setType("message/rfc822");
                                // createChooser shows user a list of app that can handle
                                // this MIME type, which is, email
                                startActivity(Intent.createChooser(email,
                                        "Choose an Email client :"));
                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else if (item.getItemId() == R.id.actionQuiz) {
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout quizphrase =
                    (LinearLayout) inflater.inflate(R.layout.quizphrase, null);
            final RadioGroup rgDay = (RadioGroup) quizphrase.findViewById(R.id.rgDay);
            final RadioGroup rgOld = (RadioGroup) quizphrase.findViewById(R.id.rgOld);
            final RadioGroup rgTheme = (RadioGroup) quizphrase.findViewById(R.id.rgTheme);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Test Yourself!")
                    .setView(quizphrase)
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            int score = 0;
                            int selectedButtonId = rgDay.getCheckedRadioButtonId();
                            // Get the radio button object from the Id we had gotten above
                            RadioButton rb = (RadioButton)findViewById(selectedButtonId);
                            String selectedAns = rb.getText().toString();
                            if(selectedAns.equals("Yes")){
                                score += 1;
                            }

                            int selectedButtonId2 = rgOld.getCheckedRadioButtonId();
                            // Get the radio button object from the Id we had gotten above
                            RadioButton rb2 = (RadioButton)findViewById(selectedButtonId2);
                            String selectedAns2 = rb2.getText().toString();
                            if(selectedAns2.equals("Yes")){
                                score += 1;
                            }

                            int selectedButtonId3 = rgTheme.getCheckedRadioButtonId();
                            // Get the radio button object from the Id we had gotten above
                            RadioButton rb3 = (RadioButton)findViewById(selectedButtonId3);
                            String selectedAns3 = rb3.getText().toString();
                            if(selectedAns3.equals("Yes")){
                                score += 1;
                            }

                            Toast.makeText(MainActivity.this, "Your score for quiz" + score, Toast.LENGTH_LONG).show();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

}
