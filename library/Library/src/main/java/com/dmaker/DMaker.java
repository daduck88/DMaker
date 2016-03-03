package com.dmaker;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Daduck on 2/26/16.
 */
public class DMaker {
    private final AppCompatActivity mContext;
    private int customViewId;

    private DialogType dialogType;
    private String title;

    private String content;
    private int resContent = -1;
    private String hint;
    private int resHint = -1;
    private String[] contentList;
    private String positiveText;
    private String negativeText;
    private boolean showPositive;
    private boolean showNegative;

    private DMakerFragment.OnButtonClickedListener PositiveListener;
    private DMakerFragment.OnButtonClickedListener negativeListener;
    private DMakerFragment.OnItemListClickListener listClickListener;
    private int negativeColor = -1;
    private int positiveColor = -1;
    private String[] imageList;
    private int[] imageListRes;

    public DMaker(Context context) {
        if(context == null){
            throw new NullPointerException(                                                                                                                                                                                               "context == null");
        }
        if(!(context instanceof AppCompatActivity)){
            throw new IllegalArgumentException("android.view.WindowManager$BadTokenException:Unable to add window");
        }
        this.mContext = (AppCompatActivity) context;
    }

    public DialogType getDialogType() {
        return dialogType == null ? DialogType.MESSAGE : dialogType;
    }

    public DMaker setDialogType(DialogType dialogType) {
        this.dialogType = dialogType;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public DMaker setContent(String content) {
        this.content = content;
        return this;
    }

    public int getResContent() {
        return resContent;
    }

    public void setResContent(int resContent) {
        this.resContent = resContent;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getResHint() {
        return resHint;
    }

    public void setResHint(int resHint) {
        this.resHint = resHint;
    }

    public String[] getContentList() {
        return contentList;
    }

    public void setContentList(String[] contentList) {
        this.contentList = contentList;
    }

    public String getPositiveText() {
        return positiveText;
    }

    public DMaker setPositiveText(String positiveText) {
        this.positiveText = positiveText;
        return this;
    }

    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }

    public boolean isShowPositive() {
        return showPositive;
    }

    public DMaker setShowPositive(boolean showPositive) {
        this.showPositive = showPositive;
        return this;
    }

    public boolean isShowNegative() {
        return showNegative;
    }

    public DMaker setShowNegative(boolean showNegative) {
        this.showNegative = showNegative;
        return this;
    }

    public DMakerFragment.OnButtonClickedListener getPositiveListener() {
        return PositiveListener;
    }

    public DMaker setPositiveListener(DMakerFragment.OnButtonClickedListener positiveListener) {
        this.PositiveListener = positiveListener;
        return this;
    }

    public DMakerFragment.OnButtonClickedListener getNegativeListener() {
        return negativeListener;
    }

    public void setNegativeListener(DMakerFragment.OnButtonClickedListener negativeListener) {
        this.negativeListener = negativeListener;
    }

    public DMakerFragment.OnItemListClickListener getListClickListener() {
        return listClickListener;
    }

    public void setListClickListener(DMakerFragment.OnItemListClickListener listClickListener) {
        this.listClickListener = listClickListener;}

    public int getNegativeColor() {
        return negativeColor;
    }

    public void setNegativeColor(int negativeColor) {
        this.negativeColor = negativeColor;
    }

    public int getPositiveColor() {
        return positiveColor;
    }

    public void setPositiveColor(int positiveColor) {
        this.positiveColor = positiveColor;
    }

    public String[] getImageList() {
        return imageList;
    }

    public void setImageList(String[] imageList) {
        this.imageList = imageList;
    }

    public int[] getImageListRes() {
        return imageListRes;
    }

    public void setImageListRes(int[] imageListRes) {
        this.imageListRes = imageListRes;
    }

    public int getCustomViewId() {
        return customViewId;
    }

    public void setCustomViewId(int customViewId) {
        this.customViewId = customViewId;
    }

    public void show() {
        build().show(mContext.getSupportFragmentManager(), DMaker.class.getSimpleName());
    }

    public DMakerFragment build(){
        DMakerFragment f = new DMakerFragment();
        f.setDmaker(this);
        return f;
    }
}
