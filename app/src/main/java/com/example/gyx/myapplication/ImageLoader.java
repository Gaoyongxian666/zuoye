//package com.example.gyx.myapplication;
//
//import android.graphics.Bitmap;
//import android.os.Handler;
//import android.os.Message;
//import android.widget.ImageView;
//
///**
// * Created by gyx on 2018/4/30.
// */
//public class ImageLoader {
//
//    private ImageView mImageView;
//
////用于异步更新UI
//
//    private Handler handler =new Handler(){
//
//        @Override
//        public void handleMessage(Message msg) {
//
//            super.handleMessage(msg);
//
//            mImageView.setImageBitmap((Bitmap)msg.obj);
//
//        }
//
//    };
//
////访问网络图片并提醒主线程更新UI
//
//    public void showImageFormURL(ImageView imageView,String urlString){
//
////获取ListView中的imageView
//
//        mImageView=imageView;
//
////封装过的OkHttp访问网络图片方法
//
//        HttpUtil.DownloadImage(urlString, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//
//            public void onResponse(Call call, Response response) throws IOException {
//
////将取得的字符流转化为Bitmap
//
//                InputStream is =response.body().byteStream();
//
//                Bitmap bitmap=BitmapFactory.decodeStream(is);
//
//                Log.e(TAG, "onResponse: "+bitmap);
//
////将Bitmap以message形式传递给handler更新UI
//
//                Message message = Message.obtain();
//
//                message.obj =bitmap;
//
//                handler.sendMessage(message);
//
//            }
//
//        });
//
//    }
//
//