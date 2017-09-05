package an.devhp.ui.fragment.select.android.media;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

import an.devhp.R;
import an.devhp.manager.FragmentIds;
import an.devhp.ui.adapter.SimpleStringListAdapter;
import an.devhp.ui.fragment.SimpleListFragment;
import an.devhp.ui.listener.ListItemClickListener;
import an.devhp.util.LsUtil;
import an.devhp.widget.PlayCtrlView;
import butterknife.BindView;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-04 14:07
 * @version: 1.0
 */

public class ShowExoPlayerFragment extends SimpleListFragment {

    public static final String VIDEO_PATH = "https://storage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%20Hangin'%20with%20the%20Google%20Search%20Bar.mp4";
    public static final String AUDIO_PATH = "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3";
    Uri mPlayerUri;

    @BindView(R.id.exo_view)
    SimpleExoPlayerView mExoView;

    @BindView(R.id.play_ctrl_v)
    PlayCtrlView mVPlayCtrl;

    SimpleExoPlayer mPlayer;

    public static ShowExoPlayerFragment newInstance() {
        return new ShowExoPlayerFragment();
    }

    @Override
    public String getTitle() {
        return "Exo Player";
    }

    @Override
    public int getSimpleFragmentId() {
        return FragmentIds.SHOW_EXO_PLAYER_MEDIA;
    }

    @Override
    protected boolean addFragmentList() {
        return false;
    }

    @Override
    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_media, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> list = new ArrayList<>();
        LsUtil.add(list, "在线播放视频", "在线播放音频");
        SimpleStringListAdapter adapter = new SimpleStringListAdapter(mActivity, list);
        setAdapter(adapter);
        adapter.setOnItemClickListener(new ListItemClickListener() {
            @Override
            public <T> void onListItemClick(List<T> dataList, int position) {
                T t = LsUtil.getLsElement(dataList, position);
                if (t instanceof String) {
                    if ("在线播放视频".equals(t)) {
                        if(mPlayer!=null){
                            mPlayer.stop();
                        }
                        initPlayer(true);
                        mPlayerUri = Uri.parse(VIDEO_PATH);
                        playOnLineVideo();
                    } else if ("在线播放音频".equals(t)) {
                        if(mPlayer!=null){
                            mPlayer.stop();
                        }
                        initPlayer(false);
                        mPlayerUri = Uri.parse(AUDIO_PATH);
                        playOnLineVideo();
                    }
                }
            }
        });

    }

    private void playOnLineVideo() {
        //    Measure bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter1 = new DefaultBandwidthMeter();
        //    Produces data source instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mActivity, Util.getUserAgent(mActivity, "AnDevHelper"), bandwidthMeter1);
        //    Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        //    This is the Media source representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource(mPlayerUri, dataSourceFactory, extractorsFactory, null, null);
        //    Prepare the mPlayer with the source.
        mPlayer.prepare(videoSource);

        mPlayer.addListener(mEventListener);
        mPlayer.setPlayWhenReady(true);
    }

    @NonNull
    private void initPlayer(boolean playVideo) {
        //    1. Create a default TrackSelector
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        //    2. Create the mPlayer
        mPlayer = ExoPlayerFactory.newSimpleInstance(mActivity, trackSelector);

        //    3. Bind the mPlayer to the view
        mExoView.setPlayer(playVideo ? mPlayer : null);
        mExoView.setVisibility(playVideo ? View.VISIBLE : View.GONE);

        mVPlayCtrl.setPlayer(playVideo ? null : mPlayer);
        mVPlayCtrl.setVisibility(playVideo ? View.GONE : View.VISIBLE);
    }

    private ExoPlayer.EventListener mEventListener = new ExoPlayer.EventListener() {
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
            Log.e("dev", "onTimelineChanged");
        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            Log.e("dev", "onTracksChanged");
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
            Log.e("dev", "onLoadingChanged");
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            Log.e("dev", "onPlayerStateChanged");
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
            Log.e("dev", "onPlayerError");
        }

        @Override
        public void onPositionDiscontinuity() {
            Log.e("dev", "onPositionDiscontinuity");
        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Log.e("dev", "onPlaybackParametersChanged");
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPlayer != null) {
            mPlayer.release();
        }
    }
}
