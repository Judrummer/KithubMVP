package com.github.judrummer.kithubmvp

import android.app.Application
import android.util.Log
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.interceptors.cUrlLoggingRequestInterceptor

/**
 * Created by judrummer on 25/3/2560.
 */


class KithubMVPApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            FuelManager.instance.apply {
                addRequestInterceptor(cUrlLoggingRequestInterceptor())
                addRequestInterceptor { next: (Request) -> Request ->
                    { req: Request ->
                        Log.d("FUELDEBUG", "Request $req")
                        next(req)
                    }
                }
                addResponseInterceptor { next: (Request, Response) -> Response ->
                    { req: Request, res: Response ->
                        Log.d("FUELDEBUG", "Response $res")
                        next(req, res)
                    }
                }
            }
        }
    }

}