package com.example.skhuapp.manager;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.Header;

import android.content.Context;

import com.example.skhuapp.MyApplication;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class NetworkManager {
	private static NetworkManager instance;

	public static NetworkManager getInstnace() {
		if (instance == null) {
			instance = new NetworkManager();
		}
		return instance;
	}

	AsyncHttpClient client;

	private NetworkManager() { // 쿠키 저장소: 로그인 상태 관리 유지하는데 사용함.
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);
			MySSLSocketFactory socketFactory = new MySSLSocketFactory(
					trustStore);
			socketFactory
					.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			client = new AsyncHttpClient();
			client.setSSLSocketFactory(socketFactory);
			client.setCookieStore(new PersistentCookieStore(MyApplication
					.getContext()));
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block/
			e.printStackTrace();
		}
		// client.setTimeout(30000);
	}

	public interface OnResultListener<T> { // 요청시 응답하는 리스너
		public void onSuccess(T result);

		public void onFail(int code);
	}

	public static final String SERVER = "http://54.65.60.82";

	public static final String URL_GET_FREEBOARD_LIST = SERVER + "/story/1";

	public void getFreeboardList(Context context,
			final OnResultListener<ServerFreeBoardData> listener) {
		client.get(context, URL_GET_FREEBOARD_LIST,
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String responseString) {
						Gson gson = new Gson();
						ServerFreeBoardData result = gson.fromJson(
								responseString, ServerFreeBoardData.class);
						listener.onSuccess(result);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							String responseString, Throwable throwable) {
						listener.onFail(statusCode);
					}
				});
	}

	public static final String URL_POST_STORY = SERVER + "/story";

	public void postStory(Context context, String name, String content,
			String type, final OnResultListener<SendLetterResult> listener) {
		RequestParams params = new RequestParams();
		params.put("name", name);
		params.put("content", content);
		params.put("type", type);
		client.post(context, URL_POST_STORY, params,
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String responseString) {
						Gson gson = new Gson();
						SendLetterResult result = gson.fromJson(responseString,
								SendLetterResult.class);
						listener.onSuccess(result);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							String responseString, Throwable throwable) {
						listener.onFail(statusCode);
					}
				});
	}
	
	public static final String URL_POST_COMMENT = SERVER+"/reply";
	
	public void postComment(Context context, String name, String content, String story_id, final OnResultListener<SendCommentResult> listener){
		RequestParams params = new RequestParams();
		params.put("name", name);
		params.put("content", content);
		params.put("story_id", story_id);
		client.post(context, URL_POST_COMMENT,params,new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				Gson gson = new Gson();
				SendCommentResult result = gson.fromJson(responseString,
						SendCommentResult.class);
				listener.onSuccess(result);				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});
	}

	public static final String URL_GET_COMMENT = SERVER + "/reply/%s";

	public void getCommentList(Context context, String story_id,
			final OnResultListener<GetCommentResult> listener) {
		String url = String.format(URL_GET_COMMENT, story_id);
		client.get(context, url, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				Gson gson = new Gson();
				GetCommentResult result = gson.fromJson(responseString,
						GetCommentResult.class);
				listener.onSuccess(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
			}
		});

	}

}
