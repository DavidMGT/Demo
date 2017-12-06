package cn.rongcloud.aochuang.ui.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import cn.rongcloud.aochuang.R;
import cn.rongcloud.aochuang.databinding.FragmentWebBinding;
import cn.rongcloud.aochuang.utils.LogUtils;
import io.rong.imkit.fragment.UriFragment;


/**
 * @author Xu
 */

public class AppWebFragment extends UriFragment {

    private FragmentWebBinding binding;
    // private String originurl = "http://192.227.228.215:9999/api/v2/table/2501?s";
    private String originurl = "http://www.yy.com/54880976/54880976?tempId=16777217?f=70050";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web, container, false);
        return binding.getRoot();
    }


    private void initWebView() {
        LogUtils.d("initWebView执行了");
//        binding.swiprefresh.setListener(this);
        binding.webView.loadUrl(originurl);
        binding.webView.getSettings().setSaveFormData(true);
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap favicon) {
                super.onPageStarted(webView, s, favicon);
            }

            @Override
            public void onLoadResource(WebView webView, String s) {
                super.onLoadResource(webView, s);
                //  LogUtils.d("加载的进度是" + webView.getProgress());
                binding.setProgress(webView.getProgress());
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                LogUtils.d("现在的地址是" + binding.webView.getUrl());
                binding.setProgress(100);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.webView.stopLoading();
        binding.webView.removeAllViews();
        binding.webView.destroy();
    }

    @Override
    protected void initFragment(Uri uri) {
        originurl = uri.toString();
        initWebView();
    }
}
