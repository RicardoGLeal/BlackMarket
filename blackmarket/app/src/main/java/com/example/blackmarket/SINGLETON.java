package com.example.blackmarket;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SINGLETON {

    private static SINGLETON mInstance;
    private RequestQueue requestQueue;
    private static Context context;

    private SINGLETON(Context mctx){
        context = mctx;
        requestQueue = getRequestQueue();
    }

    public static synchronized SINGLETON getInstance(Context mctx){
        if(mInstance == null){
            mInstance = new SINGLETON(mctx);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    public <T>void addToRequestque(Request<T> request){
        requestQueue.add(request);
    }


}
