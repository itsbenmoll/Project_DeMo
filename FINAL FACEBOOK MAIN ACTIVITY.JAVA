package com.psamolisgmail.www.demo;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;



import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookContentProvider;
import com.facebook.FacebookDialog;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.Utility;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.ResponseConnControl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.Arrays;
import java.util.HashMap;


public class MainActivity extends Activity implements OnClickListener{

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView username;
    private EditText input;
    private TextView post_to_fb;
    private Button post;
    private Button finished;
    //private ImageView user_picture;

    //private TextView post;
    //private EditText input;
    //private Button post;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FacebookSdk.sdkInitialize(this.getApplicationContext());

        setContentView(R.layout.activity_main);
        username = (TextView) findViewById(R.id.username);
        //user_picture=(ImageView)findViewById(R.id.user_picture);



        //input=(EditText)findViewById(R.id.input);
        post = (Button) findViewById(R.id.post);
        input = (EditText) findViewById(R.id.input);
        post_to_fb = (TextView) findViewById(R.id.post_to_fb);



        //post=(TextView)findViewById(R.id.post);
        //loginButton=(LoginButton)findViewById(R.id.login_button);
        post.setOnClickListener(this);
        finished = (Button) findViewById(R.id.confirm);
        finished.setOnClickListener(new OnClickListener() {
            @Override
            //If clicked, call the finish method.
            public void onClick(View v) {
                //Finish ends the current activity and goes back to the activity that called this one (Main in this case)
                Intent intent = new Intent(MainActivity.this, Main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }


        @Override
        public void onClick(View view){


        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
        LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                accessToken.getPermissions();
                Profile profile = Profile.getCurrentProfile();


                if (profile != null) {

                    username.setText("Thank you " + profile.getName()+ " for posting your question. Be sure to like our page and share our questions with your friends.");

                    //URL img_value=null;
                    //try {
                        //img_value = new URL("http://graph.facebook.com/" + profile.getId() + "/picture?type=large");
                        //try {
                            //Bitmap mIcon1=BitmapFactory.decodeStream(img_value.openConnection().getInputStream());
                            //user_picture.setImageBitmap(mIcon1);
                        //}
                        //catch(IOException e2){
                            //e2.printStackTrace();
                        //}

                    //}
                    //catch(MalformedURLException e1){
                        //e1.printStackTrace();
                    //}




                    String question = input.getText().toString();


                    //String s = "Hello";


                        Bundle params = new Bundle();
                        params.putString("message", question);
                        new GraphRequest(accessToken, "1833617650196965/feed", params, HttpMethod.POST,

                                new GraphRequest.Callback() {
                                    public void onCompleted(GraphResponse response) {
                                        input.setVisibility(View.GONE);
                                        post_to_fb.setVisibility(View.GONE);
                                        post.setVisibility(View.GONE);
                                        finished.setVisibility(View.VISIBLE);//"actions"
                                    }
                                }
                        ).executeAsync();



                    //GraphRequest request=GraphRequest.newMeRequest(accessToken,
                    //new GraphRequest.GraphJSONObjectCallback() {
                    //@Override
                    //public void onCompleted(JSONObject object, GraphResponse response) {

                    //try {

                    //post.setText(object.getString("message"));
                    //}
                    //catch (JSONException e){
                    //}
                    //}
                    //}
                    //);

                    //Bundle params=new Bundle();
                    //params.getString("fields", "message");
                    //request.setAccessToken(accessToken);
                    //request.setGraphPath("1605207813058879/feed");
                    //request.setParameters(params);
                    //request.setHttpMethod(HttpMethod.GET);
                    //request.setCallback();
                    //request.executeAsync();

                    //1605207 and so on is the DEMO page id !!!
                    //Bundle params=new Bundle();
                    //params.getString("fields","message");
                    //new GraphRequest(accessToken,"1605207813058879/feed",params,HttpMethod.GET,
                    //new GraphRequest.Callback(){
                    //public void onCompleted(GraphResponse response){

                    //try {


                    //}
                    //catch (JSONException e){

                    //}

                    //}
                    //}
                    //).executeAsync();

                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_main,container,false);
        loginButton=(LoginButton)view.findViewById(R.id.login_button);
        return view;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume(){
        super.onResume();
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause(){
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);



    }




}
