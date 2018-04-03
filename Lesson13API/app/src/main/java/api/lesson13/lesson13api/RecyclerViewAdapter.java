package api.lesson13.lesson13api;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import api.lesson13.lesson13api.model.Artist;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 06.03.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Artist> mArtistList;
    Context mContext;

    public RecyclerViewAdapter(Context context, List<Artist> artistList) {
        mArtistList = artistList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mArtist = mArtistList.get(position);
        holder.mArtistNameTextView.setText(mArtistList.get(position).getName());
        holder.mArtistListenersCountTextView.setText(String.format("%s%s", holder.mView.getResources().getString(R.string.listeners_prefix), mArtistList.get(position).getStats().getListeners()));
        if (!mArtistList.get(position).getImage().get(2).getText().equals("")) {
            Picasso.with(holder.mView.getContext()).load(mArtistList.get(position).getImage().get(2).getText()).into(holder.mArtistImageView);
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable getArtistInfo = new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection httpURLConnection = null;
                        String request = new StringBuilder(holder.mView.getContext().getResources().getString(R.string.api_root))
                                .append("?method=artist.getinfo&artist=")
                                .append(holder.mArtist.getName())
                                .append("&api_key=")
                                .append(holder.mView.getResources().getString(R.string.api_key))
                                .append("&format=json").toString();
                        try {
                            URL url = new URL(request);
                            httpURLConnection = (HttpURLConnection) url.openConnection();
                            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                            StringBuilder stringBuilder = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null) {
                                stringBuilder.append(line);
                            }
                            reader.close();
                            Log.d(TAG, "run: " + stringBuilder);
                            holder.mJSONObject = new JSONObject(stringBuilder.toString());

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                        }
                    }
                };
                Thread getInfoThread = new Thread(getArtistInfo);
                getInfoThread.start();
                try {
                    getInfoThread.join();
                    if (holder.mArtist.getUrl() != null || !holder.mArtist.getUrl().equals("")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(holder.mArtist.getUrl()));
                        mContext.startActivity(intent);
                    } else {
                        Toast.makeText(mContext, mContext.getString(R.string.cant_open_url_exeption), Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArtistList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Artist mArtist;
        View mView;
        ImageView mArtistImageView;
        TextView mArtistNameTextView;
        TextView mArtistListenersCountTextView;
        JSONObject mJSONObject;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mArtistImageView = itemView.findViewById(R.id.artist_circle_image_view);
            mArtistNameTextView = itemView.findViewById(R.id.artist_name_text_view);
            mArtistListenersCountTextView = itemView.findViewById(R.id.artist_listeners_count_text_view);
        }
    }
}
