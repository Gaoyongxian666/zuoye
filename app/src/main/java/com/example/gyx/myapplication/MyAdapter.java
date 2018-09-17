package com.example.gyx.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by gyx on 2018/4/29.
 */

public class MyAdapter extends ArrayAdapter<Person> {
    private TextView textView;
    private ImageView imageView;
    private List<Person> mPersons;
    private Context mcontext;
    private LruCache<String, BitmapDrawable> mCache;




    public MyAdapter(Context context,int resource, List<Person> persons) {
        super(context,resource,persons);
        mPersons = persons;
        mcontext=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item, parent,false);
            view = convertView;
            convertView.setTag(view);

        }else {
            view = (View) convertView.getTag();
        }
        textView=view.findViewById(R.id.name);
        imageView=view.findViewById(R.id.touxiang);
        textView.setText(mPersons.get(position).getName());
        imageView.setImageResource(mPersons.get(position).getImage());
//        imageView.setImageURI(Uri.parse(mPersons.get(position).getUrl()));
//        String url=mPersons.get(position).getUrl();
//        BitmapDrawable drawable = getBitmapFromMemoryCache(url);
//        if (drawable != null) {
//            imageView.setImageDrawable(drawable);
//        } else {
//            BitmapWorkerTask task = new BitmapWorkerTask(imageView);
//            task.execute(url);
//        }


        //不能返回null
        return view;
    }
    /**
     * 将一张图片存储到LruCache中。
     *
     * @param key
     *            LruCache的键，这里传入图片的URL地址。
     * @param drawable
     *            LruCache的值，这里传入从网络上下载的BitmapDrawable对象。
     */
    private void addBitmapToMemoryCache(String key, BitmapDrawable drawable) {
        if (getBitmapFromMemoryCache(key) == null) {
            mCache.put(key, drawable);
        }
    }

    /**
     * 从LruCache中获取一张图片，如果不存在就返回null。
     *
     * @param key
     *            LruCache的键，这里传入图片的URL地址。
     * @return 对应传入键的BitmapDrawable对象，或者null。
     */

    private BitmapDrawable getBitmapFromMemoryCache(String key) {
        return mCache.get(key);
    }

    /**
     * 异步下载图片的任务。
     */

    class BitmapWorkerTask extends AsyncTask<String, Void, BitmapDrawable> {

        private ImageView mImageView;

        public BitmapWorkerTask(ImageView imageView) {
            mImageView = imageView;
        }

        @Override
        protected BitmapDrawable doInBackground(String... params) {
            String imageUrl = params[0];
            // 在后台开始下载图片
            Bitmap bitmap = downloadBitmap(imageUrl);
            BitmapDrawable drawable = new BitmapDrawable(mcontext.getResources(), bitmap);
            addBitmapToMemoryCache(imageUrl, drawable);
            return drawable;
        }

        @Override
        protected void onPostExecute(BitmapDrawable drawable) {
            if (mImageView != null && drawable != null) {
                mImageView.setImageDrawable(drawable);
            }
        }

        /**
         * 建立HTTP请求，并获取Bitmap对象。
         *
         * @param imageUrl
         *            图片的URL地址
         * @return 解析后的Bitmap对象
         */
        private Bitmap downloadBitmap(String imageUrl) {
            Bitmap bitmap = null;
            HttpURLConnection con = null;
            try {
                URL url = new URL(imageUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5 * 1000);
                con.setReadTimeout(10 * 1000);
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            return bitmap;
        }

    }

}


