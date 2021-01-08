package omgimbot.app.sidangapps.features.mhs.dashboard;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.AdapterSliderBanner;
import omgimbot.app.sidangapps.features.ModelSliderBanner;
import omgimbot.app.sidangapps.features.admin.mhs.tracerStudi.TracerStudiActivity;
import omgimbot.app.sidangapps.features.dosen.dashboard.DashboardDosenActivity;
import omgimbot.app.sidangapps.features.mhs.judul.JudulMhsActivity;
import omgimbot.app.sidangapps.features.mhs.kompre.KompreMhsActivity;
import omgimbot.app.sidangapps.features.mhs.kompre.SuratTugasMhsActivity;
import omgimbot.app.sidangapps.features.mhs.munaqosah.MunaqosahMhsActivity;
import omgimbot.app.sidangapps.features.mhs.pengumuman.PengumumanActivity;
import omgimbot.app.sidangapps.features.mhs.sempro.SemproMhsActivity;
import omgimbot.app.sidangapps.features.webview.PortalInformasiActivity;
import omgimbot.app.sidangapps.ui.DrawerHeader;
import omgimbot.app.sidangapps.ui.DrawerMenuItem;

import static omgimbot.app.sidangapps.App.getContext;

public class DashboardMhsActivity extends AppCompatActivity {
    @BindView(R.id.drawerView)
    PlaceHolderView mDrawerView;
    @BindView(R.id.mainMenu)
    ImageButton mainMenuDashboard;
    @BindView(R.id.toolbar)
    Toolbar toolbarMain;
    @BindView(R.id.mCardTracer)
    CardView mCardTracer;
    @BindView(R.id.mCardJudul)
    CardView mCardJudul;
    @BindView(R.id.mCardSempro)
    CardView mCardSempro;
    @BindView(R.id.mCardMunaqosah)
    CardView mCardMunaqosah;
    @BindView(R.id.mCardkompre)
    CardView mCardkompre;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mTogle;
    ImageView banners;
    ViewPager viewPager;
    LinearLayout indicatorDot;
    AdapterSliderBanner adapter;
    List<ModelSliderBanner> models;
    private int dotsCount;
    private ImageView[] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_mhs);
        ButterKnife.bind(this);
        this.initViews();
        banners = findViewById(R.id.banner);
        viewPager = findViewById(R.id.viewPager);
        indicatorDot = findViewById(R.id.bannerDot);
        models = new ArrayList<>();
        models.add(new ModelSliderBanner(R.drawable.content1));
        models.add(new ModelSliderBanner(R.drawable.content2));

        adapter = new AdapterSliderBanner(models, this);
        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            indicatorDot.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.nonactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

        viewPager.setAdapter(adapter);
        viewPager.setPadding(55, 0, 55, 0);

    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if (this != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (viewPager.getCurrentItem() == 0) {
                                viewPager.setCurrentItem(1);
                            } else if (viewPager.getCurrentItem() == 1) {
                                viewPager.setCurrentItem(2);
                            } else if (viewPager.getCurrentItem() == 2) {
                                viewPager.setCurrentItem(3);
                            } else if (viewPager.getCurrentItem() == 3) {
                                viewPager.setCurrentItem(4);
                            } else {
                                viewPager.setCurrentItem(0);
                            }
                        } catch (Exception e) {

                        }
                    }
                });
            }
        }
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

    @OnClick(R.id.mCardJudul)
    void goToJudul() {
        startActivity(new Intent(this , JudulMhsActivity.class));
        finish();
    }

    @OnClick(R.id.mCardSempro)
    void goToSempro() {
        startActivity(new Intent(this , SemproMhsActivity.class));
        finish();
    }

    @OnClick(R.id.mCardMunaqosah)
    void goToMunaqosah() {
        startActivity(new Intent(this , MunaqosahMhsActivity.class));
        finish();
    }

    @OnClick(R.id.mCardkompre)
    void goTokompre() {
        startActivity(new Intent(this , SuratTugasMhsActivity.class));
        finish();
    }

    @OnClick(R.id.mCardPengumuman)
    void pengumuman() {
        startActivity(new Intent(this , PengumumanActivity.class));
        finish();
    }

    @OnClick(R.id.mCardTracer)
    void goToTracer() {
        startActivity(new Intent(this , TracerStudiActivity.class));
        finish();
    }

    @OnClick(R.id.cardInfoBeasiswa)
    void cardInfoBeasiswa() {
        Intent i = new Intent(this, PortalInformasiActivity.class);
        i.putExtra("key" , "mhs");
        i.putExtra("uri" , "fakultas");
        startActivity(i);
        finish();
    }

    @OnClick(R.id.cardPortalInformasi)
    void cardPortalInformasi() {
        Intent i = new Intent(this, PortalInformasiActivity.class);
        i.putExtra("key" , "mhs");
        i.putExtra("uri" , "portal");
        startActivity(i);
        finish();
    }
}
