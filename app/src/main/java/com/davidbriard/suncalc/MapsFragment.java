package com.davidbriard.suncalc;

import android.Manifest;
import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.davidbriard.suncalc.databinding.FragmentMagicHoursBinding;
import com.davidbriard.suncalc.databinding.FragmentMapsBinding;
import com.davidbriard.suncalc.databinding.FragmentRiseAndSetBinding;
import com.davidbriard.suncalc.databinding.FragmentSunAndMoonBinding;
import com.davidbriard.suncalc.databinding.FragmentTwilightsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import java.util.Calendar;
import java.util.Date;

public class MapsFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener
{
    private static final int FINE_LOCATION_PERMISSION_REQUEST = 1;
    private static final int COARSE_LOCATION_PERMISSION_REQUEST = 2;
    private static final int CONNECTION_RESOLUTION_REQUEST = 3;

    private MapsViewModel mViewModel;
    private GoogleMap mMap;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_maps, container, false);

        FragmentMapsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_maps, container, false);
        mViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);
        binding.setMapsViewModel(mViewModel);
        View view = binding.getRoot();
        return view;

     /*   MapsFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.martian_data, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        binding.setMarsdata(data);
        return view;*/

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);
        // TODO: Use the ViewModel

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);


        final Button button1 = (Button) getView().findViewById(R.id.date_button);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                final DateTime date = mViewModel.getDate();

                DatePickerDialog dlg = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //Calendar newDate = Calendar.getInstance();
                        //newDate.set(year, monthOfYear, dayOfMonth);
                        //activitydate.setText(dateFormatter.format(newDate.getTime()));

                        DateTime newDate = new DateTime(year, monthOfYear, dayOfMonth, date.getHourOfDay(), date.getMinuteOfHour(), date.getSecondOfMinute());
                        mViewModel.setDate(newDate);
                    }

                }, date.getYear(), date.getMonthOfYear(), date.getDayOfMonth());

                dlg.show();


                // dialogfragment.show(getFragmentManager(), "Theme 1");


            }
        });


        // Create the observer which updates the UI.
       //final Observer<Date> nameObserver = new Observer<Date>() {
       //    @Override
       //    public void onChanged(@Nullable Date date) {
       //        button1.setText(date.toString());
       //    }
       //};

       //// Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
       //mViewModel.getDate().observe(this, nameObserver);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
/*
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    FINE_LOCATION_PERMISSION_REQUEST);

            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);*/
    }

    private void findLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    FINE_LOCATION_PERMISSION_REQUEST);
        } else {
           /* mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            LatLng myLat = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLat));*/
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case FINE_LOCATION_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    findLocation();
                }
            }
        }
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getContext(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    /*
     * Nested types
     */

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


    public static class MagicHoursFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_DATE = "date";
    private static final String ARG_LOCATION = "location";
    private MapsViewModel mViewModel;

    public MagicHoursFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MagicHoursFragment newInstance(MapsViewModel viewModel) {
        MagicHoursFragment fragment = new MagicHoursFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, viewModel.getDate());
        args.putParcelable(ARG_LOCATION, viewModel.getLocation());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentMagicHoursBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_magic_hours, container, false);

        //mViewModel = ViewModelProviders.of(getActivity()).get(MapsViewModel.class);

        binding.setMapsViewModel(mViewModel);
        View view = binding.getRoot();
        return view;
        }
    }

    public static class TwilightsFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private MapsViewModel mViewModel;

        public TwilightsFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static TwilightsFragment newInstance(MapsViewModel viewModel) {
            TwilightsFragment fragment = new TwilightsFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            FragmentTwilightsBinding binding = DataBindingUtil.inflate(inflater,
                    R.layout.fragment_twilights, container, false);
            binding.setMapsViewModel(mViewModel);
            View view = binding.getRoot();
            return view;
        }
    }

    public static class SunAndMoonFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private MapsViewModel mViewModel;

        public SunAndMoonFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SunAndMoonFragment newInstance(MapsViewModel viewModel) {
            SunAndMoonFragment fragment = new SunAndMoonFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            FragmentSunAndMoonBinding binding = DataBindingUtil.inflate(inflater,
                    R.layout.fragment_sun_and_moon, container, false);
            binding.setMapsViewModel(mViewModel);
            View view = binding.getRoot();
            return view;
        }
    }

    public static class RiseAndSetFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private MapsViewModel mViewModel;

        public RiseAndSetFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static RiseAndSetFragment newInstance(MapsViewModel viewModel) {
            RiseAndSetFragment fragment = new RiseAndSetFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            FragmentRiseAndSetBinding binding = DataBindingUtil.inflate(inflater,
                    R.layout.fragment_rise_and_set, container, false);
            binding.setMapsViewModel(mViewModel);
            View view = binding.getRoot();
            return view;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fragment = null;
            if (position == 0) {
                fragment = RiseAndSetFragment.newInstance(mViewModel);
                ((RiseAndSetFragment) fragment).mViewModel = mViewModel;
            }
            else if (position == 1) {
                fragment = SunAndMoonFragment.newInstance(mViewModel);
                ((SunAndMoonFragment) fragment).mViewModel = mViewModel;
            }
            else if (position == 2) {
                fragment = TwilightsFragment.newInstance(mViewModel);
                ((TwilightsFragment) fragment).mViewModel = mViewModel;
            }
            else if (position == 3) {
                fragment = MagicHoursFragment.newInstance(mViewModel);
                ((MagicHoursFragment) fragment).mViewModel = mViewModel;
            }
            else
                fragment = PlaceholderFragment.newInstance(position + 1);
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }
    }
}
