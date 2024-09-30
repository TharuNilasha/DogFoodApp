package com.example.dogfoodapp.ui.Video;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.dogfoodapp.databinding.FragmentVideoBinding;

public class VideoFragment extends Fragment {


    private FragmentVideoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        // Binding setup
        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        WebView webView = binding.webView;
        String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/BuhrHclfW8o?si=r4N6XZ_bFQ9xYrs0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView.loadData(video, "text/html", "utf-8");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        WebView webView1 = binding.webView1;
        String video1 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MhUgn7WFZrg?si=iD74usk47SpQIVzz\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView1.loadData(video1, "text/html", "utf-8");
        WebSettings webSettings1 = webView1.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        webView1.setWebChromeClient(new WebChromeClient());

        WebView webView2 = binding.webView2;
        String video2 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0_ojdnjL7lk?si=ky1YkzGmv9DgZEoC\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView2.loadData(video2, "text/html", "utf-8");
        WebSettings webSettings2 = webView2.getSettings();
        webSettings2.setJavaScriptEnabled(true);
        webView2.setWebChromeClient(new WebChromeClient());

        WebView webView3 = binding.webView3;
        String video3 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/jGSgrC4jrOE?si=9vAr8T-yH0L-aLGv\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView3.loadData(video3, "text/html", "utf-8");
        WebSettings webSettings3 = webView3.getSettings();
        webSettings3.setJavaScriptEnabled(true);
        webView3.setWebChromeClient(new WebChromeClient());

        WebView webView4 = binding.webView4;
        String video4 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/c-dyuQm2zUk?si=vEGkKsdWXvDQr4TZ\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView4.loadData(video4, "text/html", "utf-8");
        WebSettings webSettings4 = webView4.getSettings();
        webSettings4.setJavaScriptEnabled(true);
        webView4.setWebChromeClient(new WebChromeClient());

        WebView webView5 = binding.webView5;
        String video5 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/j_LZmPbT_Ng?si=Ij8wMEUyr3tkL1eI\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView5.loadData(video5, "text/html", "utf-8");
        WebSettings webSettings5 = webView5.getSettings();
        webSettings5.setJavaScriptEnabled(true);
        webView5.setWebChromeClient(new WebChromeClient());

        WebView webView6 = binding.webView6;
        String video6 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vxe2FhIHSVM?si=RVNTxiUGdaM4xEDN\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView6.loadData(video6, "text/html", "utf-8");
        WebSettings webSettings6 = webView6.getSettings();
        webSettings6.setJavaScriptEnabled(true);
        webView6.setWebChromeClient(new WebChromeClient());

        WebView webView7 = binding.webView7;
        String video7 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/nKUR5zx2nB8?si=3YqLvC8MDXcKBkpL\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView7.loadData(video7, "text/html", "utf-8");
        WebSettings webSettings7 = webView7.getSettings();
        webSettings7.setJavaScriptEnabled(true);
        webView7.setWebChromeClient(new WebChromeClient());


        WebView webView8 = binding.webView8;
        String video8 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/dYTSYzWppLo?si=n90RgqaiGHwNO47_\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView8.loadData(video8, "text/html", "utf-8");
        WebSettings webSettings8 = webView8.getSettings();
        webSettings8.setJavaScriptEnabled(true);
        webView8.setWebChromeClient(new WebChromeClient());

        WebView webView9 = binding.webView9;
        String video9 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vkHotmVJEGY?si=nYt5nhW7LDXapTp5\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView9.loadData(video9, "text/html", "utf-8");
        WebSettings webSettings9 = webView9.getSettings();
        webSettings9.setJavaScriptEnabled(true);
        webView9.setWebChromeClient(new WebChromeClient());


        WebView webView10 = binding.webView10;
        String video10 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/fvsZ-bJjjzM?si=Mst9c8KxoTDY6GmT\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView10.loadData(video10, "text/html", "utf-8");
        WebSettings webSettings10 = webView10.getSettings();
        webSettings10.setJavaScriptEnabled(true);
        webView10.setWebChromeClient(new WebChromeClient());

        WebView webView11 = binding.webView11;
        String video11 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qwKMf_5pU_Y?si=FxZNlMZPs-FQnkew\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView11.loadData(video11, "text/html", "utf-8");
        WebSettings webSettings11 = webView11.getSettings();
        webSettings11.setJavaScriptEnabled(true);
        webView11.setWebChromeClient(new WebChromeClient());

        WebView webView12 = binding.webView12;
        String video12 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/yNBUa96TFTs?si=0Xb8fkBaYQcTMXSF\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView12.loadData(video12, "text/html", "utf-8");
        WebSettings webSettings12 = webView12.getSettings();
        webSettings12.setJavaScriptEnabled(true);
        webView12.setWebChromeClient(new WebChromeClient());

        WebView webView13 = binding.webView13;
        String video13 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/j7UHbY5FRvw?si=tak_pbKS_D1WODj2\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView13.loadData(video13, "text/html", "utf-8");
        WebSettings webSettings13 = webView13.getSettings();
        webSettings13.setJavaScriptEnabled(true);
        webView13.setWebChromeClient(new WebChromeClient());


        WebView webView14 = binding.webView14;
        String video14 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/FerrV-eWUCE?si=c-yfb0b8tiV9nyPu\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView14.loadData(video14, "text/html", "utf-8");
        WebSettings webSettings14 = webView14.getSettings();
        webSettings14.setJavaScriptEnabled(true);
        webView14.setWebChromeClient(new WebChromeClient());

        WebView webView15 = binding.webView15;
        String video15 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/d8WG8Oj9WmY?si=IIv2xKkLmysVqwrG\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView15.loadData(video15, "text/html", "utf-8");
        WebSettings webSettings15 = webView15.getSettings();
        webSettings15.setJavaScriptEnabled(true);
        webView15.setWebChromeClient(new WebChromeClient());


        WebView webView16 = binding.webView16;
        String video16 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/FsWg4x6XOyw?si=L1iV2ivf6tcJi1Tp\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView16.loadData(video16, "text/html", "utf-8");
        WebSettings webSettings16 = webView16.getSettings();
        webSettings16.setJavaScriptEnabled(true);
        webView16.setWebChromeClient(new WebChromeClient());



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}