package org.appdynamics.handpover.rest;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Created by michi on 29.10.16.
 */
@SuppressWarnings("WeakerAccess")
public class SslClientHelper {
    @SuppressWarnings("WeakerAccess")
    public static class SSLUtil {
        protected static SSLContext getInsecureSSLContext()
                throws KeyManagementException, NoSuchAlgorithmException {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(
                                final java.security.cert.X509Certificate[] arg0, final String arg1)
                                throws CertificateException {
                        }

                        public void checkServerTrusted(
                                final java.security.cert.X509Certificate[] arg0, final String arg1)
                                throws CertificateException {
                        }

                    }
            };

            final SSLContext sslcontext = SSLContext.getInstance("SSL");
            sslcontext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            return sslcontext;
        }
    }
}
