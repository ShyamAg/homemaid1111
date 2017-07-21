package fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.rsi.homemaid.HomeActivity;
import com.rsi.homemaid.LoginActivity;
import com.rsi.homemaid.MaidListActivity;
import com.rsi.homemaid.R;

import java.util.List;

import adapter.GridViewHomeAdapter;
import bean.CategoryDataClass;
import bean.CategoryDetail;
import bean.Login;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tools.AppSharedPreference;
import tools.HelperMethods;


public class HomeFragment extends BaseFragment {

    private SliderLayout mDemoSlider;
    private GridViewWithHeaderAndFooter grid;
    private GridViewHomeAdapter gridViewHomeAdapter;
    private List<CategoryDetail> categoryDetails;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mDemoSlider = (SliderLayout) rootView.findViewById(R.id.slider);
        grid = (GridViewWithHeaderAndFooter) rootView.findViewById(R.id.grid);
        grid.setVerticalScrollBarEnabled(false);

        Call<CategoryDataClass> call = apiService.getCategories();
        mProgressDialog.show();
        call.enqueue(new Callback<CategoryDataClass>() {
            @Override
            public void onResponse(Call<CategoryDataClass> call, Response<CategoryDataClass> response) {

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if (response.body().getStatus().equals("success")){
                    categoryDetails = response.body().getCategoryDetails();
                    gridViewHomeAdapter = new GridViewHomeAdapter(getActivity(), response.body().getCategoryDetails());
                    grid.setAdapter(gridViewHomeAdapter);
                }

            }
            @Override
            public void onFailure(Call<CategoryDataClass> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                    showCustomToast(grid, getResources().getString(R.string.err_message_retrofit));
            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent mIntent = new Intent(getActivity(), MaidListActivity.class);
                mIntent.putExtra("CatId", categoryDetails.get(position).getId() );
                startActivity(mIntent);
            }
        });

        addAdvertisementBanner();

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }


    private void addAdvertisementBanner(){
        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }
}
