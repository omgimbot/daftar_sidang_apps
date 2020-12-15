package omgimbot.app.sidangapps.features.admin.dashboard;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.mindorks.placeholderview.PlaceHolderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.admin.dosen.inputdosen.InputDosenActivity;
import omgimbot.app.sidangapps.features.admin.mhs.MahasiswaAdminActivity;
import omgimbot.app.sidangapps.ui.DrawerHeader;
import omgimbot.app.sidangapps.ui.DrawerMenuItem;

public class DashboardAdminActivity extends AppCompatActivity {

    @BindView(R.id.drawerView)
    PlaceHolderView mDrawerView;
    @BindView(R.id.mainMenu)
    ImageButton mainMenuDashboard;
    @BindView(R.id.toolbar)
    Toolbar toolbarMain;

    @BindView(R.id.btnDosen)
    Button btnDosen;
    @BindView(R.id.btnMhs)
    Button btnMhs;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mTogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        ButterKnife.bind(this);
        this.initViews();

        btnMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardAdminActivity.this,MahasiswaAdminActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardAdminActivity.this, InputDosenActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


    public void initViews(){
        this.setupDrawer();
        drawer = findViewById(R.id.dashboard);
        mTogle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.setDrawerListener(mTogle);
        mTogle.syncState();
        ((AppCompatActivity)this).setSupportActionBar(toolbarMain);
        ((AppCompatActivity)this).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void setupDrawer() {
        mDrawerView
                .addView(new DrawerHeader(this))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_ABOUT))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_RESETPASSWORD))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_LOGOUT));

        mainMenuDashboard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (!drawer.isDrawerOpen(Gravity.END)) drawer.openDrawer(Gravity.END);
                else drawer.closeDrawer(Gravity.START);
            }
        });
    }
}