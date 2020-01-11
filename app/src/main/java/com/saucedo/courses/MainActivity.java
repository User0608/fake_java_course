package com.saucedo.courses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyListAdapter.ItemClickListener, MessageDialog.OnClickDialogButton {
    private Toolbar myToolbar;
    private MyListAdapter myAdapter;
    private RecyclerView myRecyclerView;
    private ArrayList<Course> data;
    private NavigationView myNavigationView;

    public static final String CHANNEL_ID = "noti";
    public static final int notificationId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setData();
        this.initialize();
    }

    private void initialize() {
        this.myNavigationView = findViewById(R.id.my_navigation_view);
        this.myNavigationView.setNavigationItemSelectedListener(this.navigationItemSelectedListener);

        this.myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(this.myToolbar);
        this.myRecyclerView = findViewById(R.id.my_recyclerView);
        this.myAdapter = new MyListAdapter(data, this);
        this.myRecyclerView.setAdapter(this.myAdapter);
        this.myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setData() {
        this.data = new ArrayList<>();
        this.data.add(new Course("Java Environment", "Start", "07/01/2020", "20 minutes", "1 day left!"));
        this.data.add(new Course("Java Basic Syntax", "Start", "01/04/2020", "20 minutes", "1 day left!"));
        this.data.add(new Course("Comments in Java", "Syntax", "05/01/2020", "10 minutes", "1 day left!"));
        this.data.add(new Course("Data Types in Java", "Data", "02/03/2020", "50 minutes", "1 day left!"));
        this.data.add(new Course("Variables in Java", "Data", "07/01/2020", "40 minutes", "1 day left!"));
        this.data.add(new Course("KeyWords in Java", "Symtax", "07/01/2020", "50 minutes", "1 day left!"));
        this.data.add(new Course("Operators in Java", "Operations", "07/01/2020", "50 minutes", "1 day left!"));
        this.data.add(new Course("Loops in java", "Loops", "07/01/2020", "50 minutes", "1 day left!"));
        this.data.add(new Course("Hello World", "Listas y colas", "07/01/2020", "50 minutes", "1 day left!"));
        this.data.add(new Course("Java Environment", "Start", "07/01/2020", "20 minutes", "1 day left!"));
        this.data.add(new Course("Java Basic Syntax", "Start", "01/04/2020", "20 minutes", "1 day left!"));
        this.data.add(new Course("Comments in Java", "Syntax", "05/01/2020", "10 minutes", "1 day left!"));
        this.data.add(new Course("Data Types in Java", "Data", "02/03/2020", "50 minutes", "1 day left!"));
        this.data.add(new Course("Variables in Java", "Data", "07/01/2020", "40 minutes", "1 day left!"));
        this.data.add(new Course("KeyWords in Java", "Symtax", "07/01/2020", "50 minutes", "1 day left!"));
        this.data.add(new Course("Operators in Java", "Operations", "07/01/2020", "50 minutes", "1 day left!"));
        this.data.add(new Course("Loops in java", "Loops", "07/01/2020", "50 minutes", "1 day left!"));
        this.data.add(new Course("Hello World", "Listas y colas", "07/01/2020", "50 minutes", "1 day left!"));
    }

    NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.nav_menu_home:
                    showsms("Home");
                    return true;
                case R.id.nav_menu_favorite:
                    showsms("Favorite");
                    return true;
                case R.id.nav_menu_history:
                    showsms("History");
                    return true;
                case R.id.nav_menu_share:
                    showsms("Share");
                    return true;
                case R.id.nav_menu_send:
                    showsms("Send");
                    return true;
                case R.id.nav_menu_about:

                    return true;
                default:
                    return false;
            }
        }
    };

    @Override
    public void onItemClickResumeButton(View view, int position) {

        MessageDialog messageDialog = new MessageDialog(data.get(position));
        messageDialog.show(getSupportFragmentManager(), "NoticeDialogFragment");

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "noti";
            String description = "sali";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onItemClickAlertButton(View view, int position) {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_error_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_date_range_black_24dp))
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
// notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builder.build());
        this.showsms("alert = " + position);

    }

    private void showsms(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void positiveButton() {
        this.showsms("positive");
    }

    @Override
    public void negativeButton() {
        this.showsms("Negative");

    }
}
