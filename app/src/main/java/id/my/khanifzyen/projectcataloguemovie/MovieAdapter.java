package id.my.khanifzyen.projectcataloguemovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by khanif on 09/01/18.
 */

public class MovieAdapter extends BaseAdapter {
    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems item){
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    @Override
    public int getCount() {
        if (mData == null ) return 0;
        return mData.size();
    }

    @Override
    public MovieItems getItem(int position) { return mData.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_list,null);
            holder.tvMovieTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvMovieOverview = (TextView) convertView.findViewById(R.id.tv_overview);
            holder.tvIdMovie = (TextView) convertView.findViewById(R.id.tv_id);
            holder.tvMovieReleaseDate = (TextView) convertView.findViewById(R.id.tv_release_date);
            holder.imgPoster = (ImageView) convertView.findViewById(R.id.img_poster);
            holder.tvPoster = (TextView) convertView.findViewById(R.id.tv_poster);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.tvMovieTitle.setText(mData.get(position).getMovieTitle());
        holder.tvMovieOverview.setText(mData.get(position).getMovieOverview());
        holder.tvMovieReleaseDate.setText(mData.get(position).getMovieReleaseDate());
        Integer idMovie = mData.get(position).getIdMovie();
        holder.tvIdMovie.setText(idMovie.toString());
        Glide.with(context).load("http://image.tmdb.org/t/p/w185/"+mData.get(position).getMoviePoster()).into(holder.imgPoster);
        holder.tvPoster.setText(mData.get(position).getMoviePoster());
        return convertView;
    }

    private class ViewHolder {
        ImageView imgPoster;
        TextView tvMovieTitle;
        TextView tvMovieOverview;
        TextView tvMovieReleaseDate;
        TextView tvIdMovie;
        TextView tvPoster;
    }
}
