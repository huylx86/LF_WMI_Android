package com.wmi.finedu.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.wmi.finedu.R;
import com.wmi.finedu.adapters.VideoAdapter;
import com.wmi.finedu.models.VideoModel;
import com.wmi.finedu.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VideoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView mTvVideoTitle, mTvVideoDescription, mTvVideoDuration;
    private View mShareThis;
    private RecyclerView mRvVideosNextComing;

    private VideoModel video1, video2, video3, video4, video5;
    private VideoModel videoActived;
    private List<VideoModel> lstVideosNextComing, lstVideoOthersNextComing;
    private VideoAdapter mVideoAdapter;
    private YouTubePlayer mYoutubePlayer;

    public VideoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        initVideoData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        mTvVideoTitle = (TextView)view.findViewById(R.id.tv_video_title);
        mTvVideoDescription = (TextView)view.findViewById(R.id.tv_video_description);
        mTvVideoDuration = (TextView)view.findViewById(R.id.tv_video_duration);
        mShareThis = view.findViewById(R.id.ln_share);
        mRvVideosNextComing = (RecyclerView)view.findViewById(R.id.rv_videos);

        mRvVideosNextComing.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvVideosNextComing.setNestedScrollingEnabled(false);
        mRvVideosNextComing.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        videoActived = video1;
        lstVideosNextComing = new ArrayList<>();
        lstVideosNextComing.add(video1);
        lstVideosNextComing.add(video2);
        lstVideosNextComing.add(video3);
        lstVideosNextComing.add(video4);
        lstVideosNextComing.add(video5);

        lstVideoOthersNextComing = new ArrayList<>();
        lstVideoOthersNextComing.addAll(lstVideosNextComing);
        lstVideoOthersNextComing.remove(videoActived);

        mVideoAdapter = new VideoAdapter(lstVideoOthersNextComing, mHandlerVideoSelected);
        mRvVideosNextComing.setAdapter(mVideoAdapter);

        mShareThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareLink();
            }
        });

        initPlayer();
        popularUI();

        return view;
    }

    private void initVideoData()
    {
        video1 = new VideoModel();
        video1.index = 0;
        video1.photo = R.drawable.home_1;
        video1.title = "Understanding Risk and Return";
        video1.description = "In this video, we look at the fundamental principles underlying the relation between risk and return in investment. Understanding how they work and your tolerance to risk can help you make investment decisions that best meet your financial needs and goals!";
        video1.linkFull = "https://www.youtube.com/watch?v=4KGvoy_Ke9Y";
        video1.linkId = "4KGvoy_Ke9Y";
        video1.duration = "5 min watch";

        video2 = new VideoModel();
        video2.index = 1;
        video2.photo = R.drawable.home_2;
        video2.title = "Portfolio Diversification";
        video2.description = "What are the benefits of portfolio diversification? Find out from this video how portfolio diversification can work to reduce risk for the same level of returns";
        video2.linkFull = "https://www.youtube.com/watch?v=3jTyZl25Llo";
        video2.linkId = "3jTyZl25Llo";
        video2.duration = "5 min watch";

        video3 = new VideoModel();
        video3.index = 2;
        video3.photo = R.drawable.home_3;
        video3.title = "What Are Bonds";
        video3.description = "This video offers an essential coverage on the fundamentals of bonds and provides foundational knowledge of bond investments to a new investor.";
        video3.linkFull = "https://www.youtube.com/watch?v=h8fe7P0X3sA";
        video3.linkId = "5xBg18TMiLs";
        video3.duration = "4 min watch";

        video4 = new VideoModel();
        video4.index = 3;
        video4.photo = R.drawable.home_4;
        video4.title = "Key Risks In Bond Investment";
        video4.description = "It is important for investors to be aware of risks in any form of investments. This video will introduce you to key risks in bond investments.";
        video4.linkFull = "https://www.youtube.com/watch?v=GIfJ2xmoHJI";
        video4.linkId = "GIfJ2xmoHJI";
        video4.duration = "5 min watch";

        video5 = new VideoModel();
        video5.index = 4;
        video5.photo = R.drawable.home_5;
        video5.title = "Bond Markets";
        video5.description = "Where are the bond markets? This video provides a simple guide on how to purchase bonds.";
        video5.linkFull = "https://www.youtube.com/watch?v=5xBg18TMiLs";
        video5.linkId = "5xBg18TMiLs";
        video5.duration = "3 min watch";
    }

    private void popularUI()
    {
        mTvVideoTitle.setText(videoActived.title);
        mTvVideoDescription.setText(videoActived.description);
        mTvVideoDuration.setText(videoActived.duration);
        if(mYoutubePlayer != null){
            mYoutubePlayer.cueVideo(videoActived.linkId);
        }
    }

    private void initPlayer()
    {
        FragmentManager fm = getActivity().getFragmentManager();
        String tag = YouTubePlayerFragment.class.getSimpleName();
        YouTubePlayerFragment playerFragment = (YouTubePlayerFragment) fm.findFragmentByTag(tag);
        if (playerFragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            playerFragment = YouTubePlayerFragment.newInstance();
            ft.add(R.id.content, playerFragment, tag);
            ft.commit();
        }

        playerFragment.initialize(Constants.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                mYoutubePlayer = youTubePlayer;
                mYoutubePlayer.cueVideo(videoActived.linkId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                Toast.makeText(YouTubePlayerFragmentActivity.this, "Error while initializing YouTubePlayer.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void shareLink()
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, videoActived.linkFull);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share)));
    }

    private Handler mHandlerVideoSelected = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int index = msg.arg1;
            videoActived = lstVideosNextComing.get(index);
            lstVideoOthersNextComing.clear();
            lstVideoOthersNextComing.addAll(lstVideosNextComing);
            lstVideoOthersNextComing.remove(index);
            mVideoAdapter.updateVideos(lstVideoOthersNextComing);
            popularUI();
        }
    };

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
