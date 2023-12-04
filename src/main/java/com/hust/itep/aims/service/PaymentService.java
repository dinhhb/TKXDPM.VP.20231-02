//package com.hust.itep.aims.service;
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//public class PaymentService {
//    public static void main(String[] args) {
//        try {
//            // Thông tin cần thay đổi tùy theo tài khoản và hóa đơn cụ thể
//            String returnUrl = "https://your-website.com/return";
//            String amount = "100000"; // Số tiền thanh toán
//            String orderInfo = "Order 123"; // Thông tin đơn hàng
//
//            // Thông tin API và khóa bí mật của bạn từ VNPAY
//            String vnpUrl = "http://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
//            String tmnCode = "2QXUI4J4"; // Mã website tại VNPAY
//            String secretKey = "YOUR_SECRET_KEY"; // Khóa bí mật tại VNPAY
//
//            // Tạo URL connection
//            URL url = new URL(vnpUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//            // Tạo tham số thanh toán
//            Map<String, String> params = new HashMap<>();
//            params.put("vnp_TmnCode", tmnCode);
//            params.put("vnp_Amount", amount);
//            params.put("vnp_Command", "pay");
//            params.put("vnp_CreateDate", getCurrentDateTime());
//            params.put("vnp_CurrCode", "VND");
//            params.put("vnp_IpAddr", "127.0.0.1");
//            params.put("vnp_Locale", "vn");
//            params.put("vnp_OrderInfo", orderInfo);
//            params.put("vnp_ReturnUrl", returnUrl);
//            params.put("vnp_TxnRef", "A123XYZ"); // Mã giao dịch duy nhất
//            params.put("vnp_Version", "2.0.0");
//
//            // Tạo chuỗi xác thực và thêm vào tham số
//            String vnp_SecureHash = generateVnPaySecureHash(params, secretKey);
//            params.put("vnp_SecureHashType", "MD5");
//            params.put("vnp_SecureHash", vnp_SecureHash);
//
//            // Gửi dữ liệu thanh toán
//            connection.setDoOutput(true);
//            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//            out.writeBytes(getParamsString(params));
//            out.flush();
//            out.close();
//
//            // Đọc phản hồi từ VNPAY
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            // In phản hồi từ VNPAY
//            System.out.println("VNPAY Response: " + response.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Phương thức tạo chuỗi tham số từ Map
//    private static String getParamsString(Map<String, String> params) {
//        StringBuilder result = new StringBuilder();
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            result.append(entry.getKey());
//            result.append("=");
//            result.append(entry.getValue());
//            result.append("&");
//        }
//        return result.toString().substring(0, result.length() - 1);
//    }
//
//    // Phương thức tạo chuỗi xác thực từ Map và khóa bí mật
//    private static String generateVnPaySecureHash(Map<String, String> params, String secretKey) {
//        StringBuilder data = new StringBuilder();
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            data.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//        }
//        data.append("vnp_SecureHashType=MD5").append("&").append("vnp_SecureHashSecret=").append(secretKey);
//        return md5(data.toString());
//    }
//
//    // Phương thức tính giá trị MD5 của chuỗi đầu vào
//    private static String md5(String input) {
//        try {
//            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
//            byte[] array = md.digest(input.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for (byte b : array) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (java.security.NoSuchAlgorithmException e) {
//            return null;
//        }
//    }
//
//    // Phương thức lấy thời gian hiện tại dưới định dạng yyyymmddhhmmss
//    private static String getCurrentDateTime() {
//        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
//        return sdf.format(new java.util.Date());
//    }
//}
