package com.example.to_do_list;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.to_do_list.Backend.Adapters.CategoryAdapter;
import com.example.to_do_list.Backend.Adapters.CustomAdapter;
import com.example.to_do_list.Backend.Models.Task;
import com.example.to_do_list.Backend.ToDoDatabaseManager;
import com.example.to_do_list.ui.Acitivities.AddTaskActivity;
import com.example.to_do_list.ui.Acitivities.EditTaskActivity;
import com.example.to_do_list.ui.NotificationChannel.NotificationReciever;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_do_list.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements Serializable {
    private AppBarConfiguration mAppBarConfiguration;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_DEFAULT_TASKS_ADDED = "defaultTasksAdded";
    private static final int REQUEST_CODE_EDIT_TASK = 1;
    private static final String TAG = "nikh";
    private static final int REQUEST_CODE_ADD_TASK = 2;
    RecyclerView recyclerView,recyclerView3;

    CategoryAdapter categoryAdapter;
    EditText quickTask;
    public boolean isShowOnlyNotCompleted=true;
    CustomAdapter customAdapter;
    LinearLayout nothingJustChill;
    ImageButton addQuickTask;
    private ActivityMainBinding binding;
    Toolbar toolbar;
    FloatingActionButton addDetailedTask;
    public  ToDoDatabaseManager toDoDatabaseManager;
    public ArrayList<Task> tasks = new ArrayList<Task>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        nothingJustChill=findViewById(R.id.nothingJustChill);
        toDoDatabaseManager =new ToDoDatabaseManager(this);
        addDetailedTask=findViewById(R.id.addDetailedTask);
        toDoDatabaseManager.open();
        tasks = (ArrayList<Task>) toDoDatabaseManager.fetchAllTask();
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean defaultTasksAdded = preferences.getBoolean(KEY_DEFAULT_TASKS_ADDED, false);
        if (!defaultTasksAdded) {
            toDoDatabaseManager.defaultTaskForExample();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(KEY_DEFAULT_TASKS_ADDED, true);
            editor.apply();
        }

        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);;
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter = new CustomAdapter(this,toDoDatabaseManager.getAllTaskNamesOf(isShowOnlyNotCompleted,"Default"), this,toDoDatabaseManager.getAllTaskIds(isShowOnlyNotCompleted));
        recyclerView.setAdapter(customAdapter);
        recyclerView3=findViewById(R.id.recyclerView3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter= new CategoryAdapter(this,toDoDatabaseManager.getAllTaskCategory(), this);
        recyclerView3.setAdapter(categoryAdapter);
        DrawerLayout drawer = binding.drawerLayout;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(toolbar.getOverflowIcon()).setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        Drawable navigationIcon = toolbar.getNavigationIcon();
        if (navigationIcon != null) {
            navigationIcon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        }
        quickTask = findViewById(R.id.quickTask);
        addQuickTask = findViewById(R.id.addQuickTask);
        addQuickTask.setOnClickListener(v->{
            toDoDatabaseManager.addTask(quickTask.getText().toString(),0,"Default");
            refreshAllTask();
        });
        quickTask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                if(!s.toString().isEmpty()){
                    addQuickTask.setVisibility(View.VISIBLE);
                }
                else{
                    addQuickTask.setVisibility(View.GONE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        addDetailedTask=findViewById(R.id.addDetailedTask);
        addDetailedTask.setOnClickListener(v->{
            createNewDetailedTask();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.isShowOnlyNotCompleted) {
                    isShowOnlyNotCompleted=!isShowOnlyNotCompleted;
            if(!isShowOnlyNotCompleted){
                item.setTitle("Show Uncomplete Task");
            }
            else{
                item.setTitle("Show All Task");
            }
                    if(isShowOnlyNotCompleted){
                        Toast.makeText(MainActivity.this, "Showing Uncompleted Task", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Showing All Task", Toast.LENGTH_SHORT).show();
                    }
                    refreshAllTask();
                    return true;
        }
        if(id==R.id.deleteAllTask){
            new AlertDialog.Builder(this,R.style.CustomAlertDialog)
                    .setTitle("Are You Sure ?")
                    .setMessage("Delete All Task")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            toDoDatabaseManager.deleteAllTask();
                            refreshAllCategory();
                            refreshAllTask();
                            if (getSupportActionBar() != null) {
                                getSupportActionBar().setTitle("Just Chill");
                            }
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(R.drawable.delete)
                    .show();
            refreshAllTask();
            refreshAllCategory();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        VideoView videoView = findViewById(R.id.videoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bg); // Use your video path
        videoView.setVideoURI(videoUri);
        videoView.setOnPreparedListener(mp -> {
            mp.setLooping(true); // Loop the video
            videoView.start();
        });
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    void createNewDetailedTask(){
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_TASK);
    }
    public void swtichToEditTaskActivity(int id){
        Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
        intent.putExtra("id",id);
        startActivityForResult(intent, REQUEST_CODE_EDIT_TASK);
    }
    public void refreshAllTask(){
        recyclerView.setAdapter(new CustomAdapter(this,toDoDatabaseManager.getAllTaskNamesOf(isShowOnlyNotCompleted,"Default"),this,toDoDatabaseManager.getAllTaskIds(isShowOnlyNotCompleted)));
    }
    public void refreshAllCategory(){
        categoryAdapter= new CategoryAdapter(this,toDoDatabaseManager.getAllTaskCategory(), this);
        recyclerView3.setAdapter(categoryAdapter);
    }
    public  void changeTaskCategory(String taskCategory){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(taskCategory);
        }
        recyclerView.setAdapter(new CustomAdapter(this,toDoDatabaseManager.fetchAllTaskNameOf(taskCategory,isShowOnlyNotCompleted),this,toDoDatabaseManager.getAllTaskIds(isShowOnlyNotCompleted)));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EDIT_TASK ||requestCode==REQUEST_CODE_ADD_TASK && resultCode == RESULT_OK) {
            if(requestCode==REQUEST_CODE_EDIT_TASK ){
                String taskCategory =data.getStringExtra("taskCategory");
                Log.d(TAG, "onActivityResult: "+taskCategory);
                getSupportActionBar().setTitle(taskCategory);
                recyclerView.setAdapter(new CustomAdapter(this,toDoDatabaseManager.getAllTaskNamesOf(isShowOnlyNotCompleted,taskCategory),this,toDoDatabaseManager.getAllTaskIds(isShowOnlyNotCompleted)));
                refreshAllCategory();
            }
            else{
            refreshAllTask();
            refreshAllCategory();
            }
        }
    }
    public void nothingToDo(boolean isNothingToDo){
        nothingJustChill=findViewById(R.id.nothingJustChill);
        if(isNothingToDo){
            nothingJustChill.setVisibility(View.VISIBLE);
        }
        else{
            nothingJustChill.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        toDoDatabaseManager.close();
    }
}