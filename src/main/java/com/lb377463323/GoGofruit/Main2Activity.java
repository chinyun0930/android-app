package com.lb377463323.GoGofruit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class Main2Activity extends AppCompatActivity {

    public static final int STATUS_CODE = 0;
    public static final int STATUS_CODE1 = 0;
    ImageView imageView;
    TextView tv1;
    public static final String answer = "answer";
    String str;

    //@Override

    /*令返回鍵無效 直接回主畫面
    public void onBackPressed() {
        moveTaskToBack(true);
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = (ImageView) findViewById(R.id.image_view);
        ImageButton click = (ImageButton)findViewById(R.id.btn);
        ImageButton cancel = (ImageButton)findViewById(R.id.cl);
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM+"/CameraV1"); //Gets information about a said directory on your device - currently downloads
        File imgFileOrig =  new File(directory, "IMG_123.jpg");
        Bitmap captureImage = BitmapFactory.decodeFile(imgFileOrig.getAbsolutePath());
        imageView.setImageBitmap(captureImage);
        //Sets Up OnClick Listener For Button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        click.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Main2Activity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Main2Activity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            STATUS_CODE);
                } else {
                    Thread t = new thread();
                    t.start();
                    try {
                        t.sleep(9000);

                    } catch (InterruptedException e) {}
                    System.out.println(str);
                    Intent intent = new Intent();
                    intent.putExtra(answer,str);
                    intent.setClass(Main2Activity.this, show.class);
                    startActivity(intent);
                }

            }
        });
    }
    @Override
    //More permission Granting code

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STATUS_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // TODO run your code
                } else {
                    // TODO show warning
                }
            }
        }
    }
    /*public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }*/

    class thread extends Thread {
        public void run() {

            send sendcode = new send();
            sendcode.execute();

        }
    }
    class send extends AsyncTask<Void,Void,Void> {
        Socket s; //Socket Variable
        @Override
        protected Void doInBackground(Void...params){
            try {
                s = new Socket("120.126.151.184",8000); //Connects to IP address - enter your IP here
                File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM+"/CameraV1"); //Gets information about a said directory on your device - currently downloads



                //RESIZE IMAGE
                File imgFileOrig =  new File(directory, "IMG_123.jpg"); ; //change "getPic()" for whatever you need to open the image file.
                Bitmap b = BitmapFactory.decodeFile(imgFileOrig.getAbsolutePath());
                int origWidth = b.getWidth();
                int origHeight = b.getHeight();

                final int destWidth = 540;//or the width you need

                final int destHeight = 960 ;
                Bitmap b2 = Bitmap.createScaledBitmap(b, destWidth, destHeight, false);
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                b2.compress(Bitmap.CompressFormat.JPEG,70 , outStream);
                File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM+"/CameraV1")
                        + File.separator  + "test1.jpg");
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(outStream.toByteArray());
                fo.close();
                File photoPath = new File(directory, "test1.jpg"); //Define your image name I used png but other formats should also work - make sure to specify file extension on server
                InputStream input = new FileInputStream(photoPath.getAbsolutePath()); //Gets the true path of your image

                try {
                    try {
                        //Reads bytes (all together)
                        int bytesRead;

                        while ((bytesRead = input.read()) != -1) {
                            s.getOutputStream().write(bytesRead); //Writes bytes to output stream
                        }
                        InputStream in=s.getInputStream();
                        byte[] rebyte = new byte[100];
                        in.read(rebyte);
                        str= new String(new String(rebyte));
                        System.out.println(str);
                    } finally {
                        //Flushes and closes socket
                        s.getOutputStream().flush();

                    }
                } finally {
                    input.close();
                    s.close();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}