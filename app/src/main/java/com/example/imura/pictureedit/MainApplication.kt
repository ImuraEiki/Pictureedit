package com.example.imura.pictureedit

import android.app.Application

import com.adobe.creativesdk.foundation.AdobeCSDKFoundation
import com.adobe.creativesdk.foundation.auth.IAdobeAuthClientCredentials

/**
 * Created by imura on 2017/10/18.
 */

class MainApplication : Application(), IAdobeAuthClientCredentials {

    override fun onCreate() {
        super.onCreate()
        AdobeCSDKFoundation.initializeCSDKFoundation(applicationContext)
    }

    override fun getClientID(): String {
        return CREATIVE_SDK_CLIENT_ID
    }

    override fun getClientSecret(): String {
        return CREATIVE_SDK_CLIENT_SECRET
    }

    override fun getAdditionalScopesList(): Array<String> {
        return CREATIVE_SDK_SCOPES
    }

    override fun getRedirectURI(): String {
        return CREATIVE_SDK_REDIRECT_URI
    }

    companion object {

        /* Be sure to fill in the two strings below. */
        private val CREATIVE_SDK_CLIENT_ID = BuildConfig.CREATIVE_SDK_CLIENT_ID
        private val CREATIVE_SDK_CLIENT_SECRET = BuildConfig.CREATIVE_SDK_CLIENT_SECRET
        private val CREATIVE_SDK_REDIRECT_URI = "<YOUR_REDIRECT_URI_HERE>"
        private val CREATIVE_SDK_SCOPES = arrayOf("email", "profile", "address")
    }
}

