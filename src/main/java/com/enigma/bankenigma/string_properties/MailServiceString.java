package com.enigma.bankenigma.string_properties;

public class MailServiceString {

    public static final String OTP_SUBJECT = "Aktivasi Akun Enigma Bank E-Payment";
    public static final String OTP_MESSAGE_BODY =
            "Dear Pelanggan Enigma Bank " +
                    "\n" +
                    "\n" +
                    "Berikut adalah link untuk registrasi Enigma E-Payment. " +
                    "mohon diperhatikan bahwa link akan kadaluarsa dalam waktu 5 menit" +
                    "\n" +
                    "\n" +
                    "%s " +
                    "\n" +
                    "terima kasih telah menggunakan layanan kami";
    public static final String OTP_LINK_BODY = "http://localhost:8081/authenticate/%s";
}