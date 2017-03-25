package com.github.judrummer.kithubmvp.extension

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

/**
 * Created by judrummer on 26/3/2560.
 */

inline fun <reified T : Any> Request.responseGson(noinline handler: (Request, Response, Result<T, FuelError>) -> Unit) = responseObject(object : ResponseDeserializable<T> {
    override fun deserialize(reader: Reader): T {
        val typeToken = object : TypeToken<T>() {}.type
        return Gson().fromJson(reader, typeToken)
    }
}, handler)