package com.fulldive.reader.utilities;

import android.content.Context;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * This class enables to support TLSv1.2 for SSLEngine below Android API Level 20
 * it's necessary to provide RSS Feeds from LifeHacker without SSLHandshake exceptions
 * @link https://stackoverflow.com/a/26586324/9214917
 */

public class SecurityProvider {

    private Context context;

    public SecurityProvider(final Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void initSSLFactory() {
        try {
            ProviderInstaller.installIfNeeded(context);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLSv1.2");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sslContext.init(null, null, null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        SSLEngine engine = sslContext.createSSLEngine();
    }

}
