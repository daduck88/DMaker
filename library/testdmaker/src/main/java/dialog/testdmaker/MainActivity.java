package dialog.testdmaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dmaker.DMaker;
import com.dmaker.DialogType;
import com.dmaker.DMakerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        DMaker maker = new DMaker(this);
        final int id = view.getId();
        switch (id){
            case R.id.empty:
                break;
            case R.id.message:
                maker.setTitle("TITLE");
                maker.setDialogType(DialogType.MESSAGE);
                maker.setContent("MESSAGE");
                break;
            case R.id.input:
                maker.setTitle("TITLE");
                maker.setDialogType(DialogType.INPUT);
                maker.setHint("INPUT");
                break;
            case R.id.list_multiple:
                maker.setDialogType(DialogType.LIST_MULTIPLE);
                maker.setContentList(getResources().getStringArray(R.array.list));
                break;
            case R.id.list_single:
                maker.setDialogType(DialogType.LIST_SINGLE);
                maker.setContentList(getResources().getStringArray(R.array.list));
                maker.setImageList(getResources().getStringArray(R.array.list_images));
                maker.setListClickListener(new DMakerFragment.OnItemListClickListener() {
                    @Override
                    public void onItemClicked(int position) {

                        Toast.makeText(MainActivity.this, "POSITION: " + (position + 1), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.date:
                maker.setTitle("TITLE");
                maker.setDialogType(DialogType.DATE);
                break;
            case R.id.time:
                maker.setDialogType(DialogType.TIME);
                break;
            case R.id.custom_view:
                maker.setDialogType(DialogType.CUSTOM_VIEW);
                maker.setCustomViewId(R.layout.activity_main);
                break;

        }
        maker.setShowNegative(true);
        maker.setShowPositive(true);
        maker.setPositiveListener(new DMakerFragment.OnButtonClickedListener() {
            @Override
            public void onButtonClicked(DMakerFragment dialog) {
                boolean dissmiss = true;
                switch (id){
                    case R.id.message:

                        break;
                    case R.id.input:
                        String result = dialog.getResult();
                        if(result.isEmpty()){
                            dissmiss = false;
                        }
                        Toast.makeText(MainActivity.this, "RESULT: " + result, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.list_multiple:
                        break;
                    case R.id.list_single:
                        break;
                    case R.id.date:
                        break;
                    case R.id.time:
                        break;
                    case R.id.custom_view:
                        Toast.makeText(MainActivity.this, "RESULT: " + dialog.getContentView().findViewById(R.id.custom_view), Toast.LENGTH_SHORT).show();
                        break;
                }
                if(dissmiss){
                    dialog.dismissAllowingStateLoss();
                }
            }
        });
        maker.show();
    }
}
