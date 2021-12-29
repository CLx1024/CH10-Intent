package tw.tcnr13.m1001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class M1001 extends AppCompatActivity implements View.OnClickListener {

    private Button b001,b002,b003, b004, b005, b006, b007;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableStrictMode(this);

        setContentView(R.layout.m1001);
        setupViewComponent();
    }

    //**********************************************************
    private void enableStrictMode(Context context) {  // 只要會用到記憶體???都要加這段???
        //-------------抓取遠端資料庫設定執行續------------------------------
        StrictMode.setThreadPolicy(new
                StrictMode.ThreadPolicy.Builder().
                detectDiskReads().
                detectDiskWrites().
                detectNetwork().
                penaltyLog().
                build());
        StrictMode.setVmPolicy(
                new
                        StrictMode.
                                VmPolicy.
                                Builder().
                        detectLeakedSqlLiteObjects().
                        penaltyLog().
                        penaltyDeath().
                        build());
    }
    //**********************************************************


    private void setupViewComponent() {
        b001 = (Button)findViewById(R.id.m1001_b001);
        b001.setOnClickListener(this);
        b002 = (Button)findViewById(R.id.m1001_b002);
        b002.setOnClickListener(this);
        b003 = (Button)findViewById(R.id.m1001_b003);
        b003.setOnClickListener(this);
        b004 = (Button)findViewById(R.id.m1001_b004);
        b004.setOnClickListener(this);
        b005 = (Button)findViewById(R.id.m1001_b005);
        b005.setOnClickListener(this);
        b006 = (Button)findViewById(R.id.m1001_b006);
        b006.setOnClickListener(this);
        b007 = (Button)findViewById(R.id.m1001_b007);
        b007.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Uri uri;
        Intent it;
        switch(v.getId()){
            case R.id.m1001_b001:
                uri = Uri.parse("http://google.com");
                it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            break;
            case R.id.m1001_b002:  // 兩種寫法 上面是寫死經緯度的 下面是用變數傳進來的
                uri = Uri.parse("geo:25.014254,121.463788");
                it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
//其他 geo URI 範例
//geo:latitude,longitude
//geo:latitude,longitude?z=zoom
//geo:0,0?q=my+street+address
//geo:0,0?q=business+near+city
//google.streetview:cbll=lat,lng&cbp=1,yaw,,pitch,zoom&mz=mapZoom
            break;
            case R.id.m1001_b003:
                double startLat = 24.172127, startLng = 120.610313, endLat = 25.014254, endLng = 121.463788;
                uri = Uri.parse("http://maps.google.com/maps?f=d&saddr="+startLat+"%20"+startLng+"&daddr="+endLat+"%20"+endLng+"&hl=tw");
                it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
//where startLat, startLng, endLat, endLng are a long with 6 decimals like: 50.123456
                uri = Uri.parse("http://maps.google.com/maps?f=d&saddr=起點位置&daddr=終點位置&hl=tw");
                break;
            case R.id.m1001_b004:
                it = new Intent(Intent.ACTION_VIEW);
                uri = Uri.parse("/res/raw/good9.mp3");

               // uri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath());
                it.setDataAndType(uri, "audio/mp3");
                startActivity(it);

                uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
                it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            break;
            case R.id.m1001_b005:
                uri = Uri.parse("tel:0800000123");
                it = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(it);
            break;

            case R.id.m1001_b006:
            break;
            case R.id.m1001_b007:
            break;

        }




    }
}