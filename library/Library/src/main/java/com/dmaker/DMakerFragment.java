package com.dmaker;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dmaker.view.DMakerContainerView;


public class DMakerFragment extends DialogFragment {

    private TextView tVTitle;
    private DMakerContainerView vContainer;
    private View contentView;
    private View vButtonsContainer;
    private TextView tVButtonPositive;
    private TextView tVButtonNegative;
    private DMaker dmaker;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_maker, container, false);
        initViews(v);
        initListeners();
        return v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        return dialog;
    }

    protected void initVars() {
    }

    protected void initViews(View v) {

        tVTitle = (TextView) v.findViewById(R.id.tVDMakerTitle);

        vContainer = (DMakerContainerView) v.findViewById(R.id.vDMakerContainer);
        vButtonsContainer = v.findViewById(R.id.lLDMakerButtonsContainer);
        tVButtonPositive = (TextView) v.findViewById(R.id.tVDMakerButtonPositive);
        tVButtonNegative = (TextView) v.findViewById(R.id.tVDMakerButtonNegative);

        if (getTitle() != null && getDialogType() != DialogType.DATE && getDialogType() != DialogType.TIME)
            tVTitle.setText(getTitle());
        else tVTitle.setVisibility(View.GONE);

        contentView = new View(getActivity());
        ContextThemeWrapper contextWrapper = new ContextThemeWrapper(getActivity(), R.style.DialogTheme);
        boolean isMultiple = false;
        switch (getDialogType()) {
            case MESSAGE:
                contentView = new TextView(contextWrapper);
                ((TextView) contentView).setText(getContent() == null ? getString(R.string.dialog_marker_empty_content) : getContent());
                break;
            case INPUT:
                contentView = new TextInputLayout(contextWrapper);
                AppCompatEditText eText = new AppCompatEditText(contextWrapper);
                eText.setHint(getHint());
                eText.setSingleLine();
                ((TextInputLayout) contentView).addView(eText);
                break;
            case LIST_MULTIPLE:
                isMultiple = true;
            case LIST_SINGLE:
                contentView = new RecyclerView(contextWrapper);
//                ((ListView) contentView).setTextAppearance(getActivity(), R.style.HoppenDialogTheme);
                ((RecyclerView) contentView).setLayoutManager(new LinearLayoutManager(getActivity()));
                DMakerAdapter adapter;
                if (getImageList() != null) {
                    adapter = new DMakerAdapter(getContentList(), getImageList(), isMultiple, this);
                } else if (getImageListRes() != null) {
                    adapter = new DMakerAdapter(getContentList(), getImageListRes(), isMultiple, this);
                } else {
                    adapter = new DMakerAdapter(getContentList(), isMultiple, this);
                }
                if (getItemListClickListener() != null) {
                    adapter.setItemListener(getItemListClickListener());
                }
                ((RecyclerView) contentView).setAdapter(adapter);

                break;
//            case LIST_SINGLE_CHOICE:
//                contentView = new ListView(contextWrapper);
//                ((ListView)contentView).setAdapter(new ArrayAdapter<String>(
//                        contextWrapper,
//                        R.layout.item_simple_list_single_choice,
//                        contentList));
//                ((ListView)contentView).setCacheColorHint(Color.WHITE);
//                ((ListView)contentView).setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//                ((ListView)contentView).setItemChecked(0,true);
//                break;
            case DATE:
                vContainer.setPadding(0, 0, 0, 0);
                vContainer.setIsSquare(false);
                contentView = new DatePicker(contextWrapper);
                ((DatePicker) contentView).setCalendarViewShown(false);
//                ((DatePicker)contentView).getCalendarView().getDe
//                if(contentDateMin != null){
//                    ((DatePicker)contentView).setMinDate(contentDateMin.withMinuteOfHour(0).withHourOfDay(0).getMillis());
//                }
                break;
            case TIME:
                vContainer.setPadding(0, 0, 0, 0);
                vContainer.setIsSquare(false);
                contentView = new TimePicker(contextWrapper);
//                if(contentDate != null) {
//                    ((TimePicker)contentView).setCurrentHour(contentDate.getHourOfDay());
//                    ((TimePicker)contentView).setCurrentMinute(contentDate.getMinuteOfHour());
//                }
                break;
            case CUSTOM_VIEW:
                contentView = LayoutInflater.from(vContainer.getContext()).inflate(dmaker.getCustomViewId(), vContainer, false);
        }

        contentView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        vContainer.addView(contentView);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        v.measure(metrics.widthPixels, metrics.heightPixels);

        if (isShowNegative() || getNegativeText() != null) {
            dmaker.setShowNegative(true);
            if (getNegativeText() != null) {
                tVButtonNegative.setText(getNegativeText());
            }
            if (getColorNegative() != -1) {
                tVButtonNegative.setTextColor(getResources().getColorStateList(getColorNegative()));
            }
        } else {
            tVButtonNegative.setVisibility(View.GONE);
        }

        if (isShowPositive() || getPositiveText() != null) {
            dmaker.setShowPositive(true);
            if (getPositiveText() != null) {
                tVButtonPositive.setText(getPositiveText());
            }
            if (getColorPositive() != -1) {
                tVButtonPositive.setTextColor(getResources().getColorStateList(getColorPositive()));
            }
        } else {
            tVButtonPositive.setVisibility(View.GONE);
        }

        if (!isShowPositive() && !isShowNegative()) {
            vButtonsContainer.setVisibility(View.GONE);
        }
    }

    protected void initListeners() {
        tVButtonPositive.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getPositiveListener() != null) {
                            getPositiveListener().onButtonClicked(DMakerFragment.this);
                            if (getDialogType() != DialogType.INPUT)
                                DMakerFragment.this.dismissAllowingStateLoss();
                        } else {
                            DMakerFragment.this.dismissAllowingStateLoss();
                        }
                    }

                });

        tVButtonNegative.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getNegativeListener() != null) {
                            getNegativeListener().onButtonClicked(DMakerFragment.this);
                            DMakerFragment.this.dismissAllowingStateLoss();
                        } else {
                            DMakerFragment.this.dismissAllowingStateLoss();
                        }
                    }
                });

//        if(dialogType == DialogType.LIST_SINGLE){
//            ((ListView)contentView).setOnItemClickListener(
//                    new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                            if(itemListClickListener != null) {
//                                itemListClickListener.onItemClicked(PickerDialogFragment.this, view, position);
//                            }
//                            else {
//                                PickerDialogFragment.this.dismissAllowingStateLoss();
//                            }
//                        }
//                    });
//        }
    }

    public interface OnButtonClickedListener {
        public void onButtonClicked(DMakerFragment dialog);
    }

    public interface OnItemListClickListener {
        public void onItemClicked(int position);
    }

    public void setDmaker(DMaker dmaker) {
        this.dmaker = dmaker;
    }

    private String getContent() {
        return dmaker.getContent();
    }

    public String getResult() {
        String result;
        switch (getDialogType()) {
            case INPUT:
                result =  ((TextInputLayout) contentView).getEditText().getText().toString();
                break;
            default:
                result = "Result not avalible for the current DialogType";
        }
        return result;
    }

//    public int getSelectedPosition(){
//        switch (dialogType){
//            case LIST_SINGLE_CHOICE:
//                SparseBooleanArray selectedItem;
//                selectedItem = ((ListView)contentView).getCheckedItemPositions();
//                for(int i = 0; i < ((ListView)contentView).getAdapter().getCount(); i++){
//                    if (selectedItem.get(i)) {
//                        return i;
//                    }
//                }
//                return ((ListView)contentView).getSelectedItemPosition();
//        }
//        return -1;
//    }

//    public DateTime getDateTime(){
//        return new DateTime(((DatePicker) contentView).getYear(), ((DatePicker) contentView).getMonth() + 1, ((DatePicker) contentView).getDayOfMonth(), 0, 0);
//    }

    public int getTimeHour() {
        return ((TimePicker) contentView).getCurrentHour();
    }

    public int getTimeMinute() {
        return ((TimePicker) contentView).getCurrentMinute();
    }

    public View getContentView() {
        return contentView;
    }

    public int show(FragmentTransaction transaction, String tag, boolean allowStateLoss) {
        transaction.add(this, tag);
        int response = allowStateLoss ? transaction.commitAllowingStateLoss() : transaction.commit();
        return response;
    }

    static int id = 1;

    // Returns a valid id that isn't in use
    @SuppressWarnings("ResourceType")
    public int findId() {
        View v = contentView.findViewById(id);
        while (v != null) {
            v = contentView.findViewById(++id);
        }
        return id++;
    }

    public void setError(int resError) {
        ((EditText) contentView).setError(getString(resError));
    }

    public DialogType getDialogType() {
        return dmaker.getDialogType();
    }

    public String getTitle() {
        return dmaker.getTitle();
    }

    public String getHint() {
        return dmaker.getHint();
    }

    public String[] getContentList() {
        return dmaker.getContentList();
    }

    public String[] getImageList() {
        return dmaker.getImageList();
    }

    public int[] getImageListRes() {
        return dmaker.getImageListRes();
    }

    public String getNegativeText() {
        return dmaker.getNegativeText();
    }

    public String getPositiveText() {
        return dmaker.getPositiveText();
    }

    public boolean isShowPositive() {
        return dmaker.isShowPositive();
    }

    public boolean isShowNegative() {
        return dmaker.isShowNegative();
    }

    public int getColorPositive() {
        return dmaker.getPositiveColor();
    }

    public int getColorNegative() {
        return dmaker.getNegativeColor();
    }

    public OnButtonClickedListener getPositiveListener() {
        return dmaker.getPositiveListener();
    }

    public OnButtonClickedListener getNegativeListener() {
        return dmaker.getNegativeListener();
    }

    public OnItemListClickListener getItemListClickListener() {
        return dmaker.getListClickListener();
    }
}
