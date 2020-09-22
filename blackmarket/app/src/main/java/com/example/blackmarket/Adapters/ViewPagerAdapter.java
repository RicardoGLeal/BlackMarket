package com.example.blackmarket.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.blackmarket.R;
import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    String [] images ={"https://ganapromo.com/wp-content/uploads/2017/02/best-buy-compu-de-tus-sue%C3%B1os.jpg",
            "https://i2.wp.com/codigoespagueti.com/wp-content/uploads/2019/06/project-scarlet.png?resize=1080%2C600&quality=90&ssl=1",
            "https://as00.epimg.net/meristation/imagenes/2018/10/30/noticias/1540932746_964484_1540932792_noticia_normal.jpg"};


    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_layout, null);
        ImageView imageView = view.findViewById(R.id.imageView);

        Picasso.get()
                .load(images[position])
                .into(imageView);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
