package com.moviles.fragments;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.moviles.proyecto1.R;

public class LoginFragment extends Fragment {
	
	private String username = new String();
	private TextView userInfoTextView;
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.activity_login, container, false);
	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    authButton.setReadPermissions(Arrays.asList("user_likes"));
	    userInfoTextView = (TextView) view.findViewById(R.id.userInfoTextView);

	    return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    Session session = Session.getActiveSession();
	    if (session != null &&
	            (session.isOpened() || session.isClosed()) ) {
	         onSessionStateChange(session, session.getState(), null);
	     }
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
	
	
	
	private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
		if (state.isOpened()) {
	        userInfoTextView.setVisibility(View.VISIBLE);
	        new Request(
	        		session,
	        		"/me",
	        		null,
	        		HttpMethod.GET,
	        		new Request.Callback() {
						
						@Override
						public void onCompleted(Response response) {
							// TODO Auto-generated method stub
							username = response.getGraphObject().getProperty("username").toString();
						}
					}
	        		).executeAsync();
	        
	        /* make the API call */
	        new Request(
	            session,
	            "/me/likes/620088464728477",
	            null,
	            HttpMethod.GET,
	            new Request.Callback() {
	                @Override
					public void onCompleted(Response response) {
	                	String respuesta = response.getGraphObject().getProperty("data").toString();
	                	String resultado = new String();
	                    if(!respuesta.equals("[]")){
	                    	Log.v("",respuesta);
	                    	resultado = "T-" + username;
	                    }
	                    else{
	                    	resultado = "F-" + username;
	                    }
	                    generarQR(resultado);
	                    session.closeAndClearTokenInformation();
	                }
	            }
	        ).executeAsync();
	    } else if (state.isClosed()) {
	        userInfoTextView.setVisibility(View.INVISIBLE);
	    }
	}

	protected void generarQR(String resultado) {
		
		Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
    	intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
    	intent.putExtra("ENCODE_DATA", resultado);//aqui va el texto
    	startActivity(intent);
		
	}

}
