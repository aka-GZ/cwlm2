package com.cwlm.capacitylock.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.cwlm.capacitylock.R;
import com.cwlm.capacitylock.base.BaseActivity;
import com.cwlm.capacitylock.base.MyApplication;
import com.cwlm.capacitylock.finals.InterfaceFinals;
import com.cwlm.capacitylock.model.BaseModel;
import com.cwlm.capacitylock.model.GetAllStopPlaceModel;
import com.cwlm.capacitylock.model.SweepNumberModel;
import com.cwlm.capacitylock.obj.GetAllStopPlaceObj;
import com.cwlm.capacitylock.obj.SweepNumberObj;
import com.cwlm.capacitylock.pay.PayActivity;
import com.cwlm.capacitylock.service.MyOrientationListener;
import com.cwlm.capacitylock.ui.percenter.BindCarNumbleActivity;
import com.cwlm.capacitylock.ui.percenter.MyLockActivity;
import com.cwlm.capacitylock.ui.percenter.PersonInfoCenterActivity;
import com.cwlm.capacitylock.ui.scan.ParkOrderDetailActivity;
import com.cwlm.capacitylock.ui.zxing.activity.CaptureActivity;
import com.cwlm.capacitylock.utils.DrivingRouteOverlay;
import com.cwlm.capacitylock.utils.MyDialog;
import com.cwlm.capacitylock.utils.MyUtils;
import com.cwlm.capacitylock.utils.OverlayManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by akawok on 2017-05-11.
 */
public class MainActivity extends BaseActivity implements BDLocationListener, View.OnClickListener {

    MapView mMapView;
    BaiduMap mBaiduMap;
    LinearLayout scan_code, main_ll_appointment;
    public LocationClient mLocationClient = null;//定位服务
    public MyOrientationListener myOrientationListener = null;

    TextView main_appointment, main_parkname, main_parkaddress, main_spareparknumber, main_allparknumber, main_stopprice, main_km;
    RelativeLayout main_refresh, position, main_road_condition, main_service;

    private LatLng mylocation;//中心点坐标

    private Double start_latitude, start_longitude;
    private Double end_latitude = 114.406006;
    private Double end_longitude = 30.512074;
    private float mCurrentAccracy, mXDirection;
    //是否进页面首次定位
    private int isFirstLoc = 0;

    String StopPlaceId = null;
    List<GetAllStopPlaceObj> list = new ArrayList<GetAllStopPlaceObj>();


    public MainActivity() {
        super(R.layout.activity_main);

    }

    @Override
    public void getData() {
        getDataFromNet(InterfaceFinals.getAllStopPlace, true);
    }

    @Override
    public void onSuccess(BaseModel resModel) {
        int infCode = resModel.getInfCode();
        switch (infCode) {
            case InterfaceFinals.getAllStopPlace:
                list.clear();
                list.addAll(((GetAllStopPlaceModel) resModel).getObject());
                for (int i = 0; i < list.size(); i++) {
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("id", i);
                    AddBaiduMarker(list.get(i).getLatitude(), list.get(i).getLongitude(), mBundle);
                }

                mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        try {
                            //获得marker中的数据
                            int id = marker.getExtraInfo().getInt("id");

                            //设置两个点,绘制路线
                            BNRoutePlanNode sNode = new BNRoutePlanNode(start_longitude, start_latitude, "", null, BNRoutePlanNode.CoordinateType.BD09LL);      //新建两个坐标点
                            BNRoutePlanNode eNode = new BNRoutePlanNode(Double.parseDouble(list.get(id).getLongitude()), Double.parseDouble(list.get(id).getLatitude()), "", null, BNRoutePlanNode.CoordinateType.BD09LL);
                            searchRoute(sNode, eNode);
                            main_ll_appointment.setVisibility(View.VISIBLE);

                            GetAllStopPlaceObj obj = list.get(id);
                            main_parkname.setText(obj.getName());
                            main_parkaddress.setText(obj.getParkAddress());
                            main_spareparknumber.setText(obj.getSpareParkNumber());
                            main_allparknumber.setText(" / " + obj.getAllParkNumber());
                            main_stopprice.setText(obj.getStopPrice());

                            StopPlaceId = obj.getStopPlaceId();



                        } catch (Exception e) {
                            e.printStackTrace();
                            showToast("请重新选择");
                        }
                        return true;
                    }
                });
                break;
            case InterfaceFinals.getSweepNumber:
                SweepNumberObj obj = ((SweepNumberModel) resModel).getMap();
                if (obj.getSweepNumber() <= 3) {
                    final Dialog dialog = new Dialog(MainActivity.this, R.style.mydialog);
                    dialog.setContentView(R.layout.dialog);
                    dialog.show();
                    dialog.findViewById(R.id.dialog_skip).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            startActivity(CaptureActivity.class);
                        }
                    });
                    dialog.findViewById(R.id.dialog_go).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            startActivity(BindCarNumbleActivity.class);
                        }
                    });
                } else {
                    final Dialog dialog = new Dialog(MainActivity.this, R.style.mydialog);
                    dialog.setContentView(R.layout.dialog);
                    dialog.show();
                    dialog.findViewById(R.id.dialog_skip).setVisibility(View.GONE);
                    TextView tv = (TextView) dialog.findViewById(R.id.dialog_text);
                    tv.setText("   您已体验三次,请绑定车牌号后继续使用哦~");
                    dialog.findViewById(R.id.dialog_go).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            startActivity(BindCarNumbleActivity.class);
                        }
                    });

                }

                break;
        }
    }


    @Override
    public void initView() {

        tv_title.setText("车位联盟");
        iv_left.setImageResource(R.mipmap.main_user);
        iv_left.setOnClickListener(this);
        iv_right.setVisibility(View.INVISIBLE);
        iv_right.setImageResource(R.mipmap.search);
        iv_right.setOnClickListener(this);

        main_ll_appointment = (LinearLayout) findViewById(R.id.main_ll_appointment);
        main_appointment = (TextView) findViewById(R.id.main_appointment);
        main_parkname = (TextView) findViewById(R.id.main_parkname);
        main_parkaddress = (TextView) findViewById(R.id.main_parkaddress);
        main_spareparknumber = (TextView) findViewById(R.id.main_spareparknumber);
        main_allparknumber = (TextView) findViewById(R.id.main_allparknumber);
        main_stopprice = (TextView) findViewById(R.id.main_stopprice);
        main_km = (TextView) findViewById(R.id.main_km);

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(this);
        //注册监听函数

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        main_refresh = (RelativeLayout) findViewById(R.id.main_refresh);
        main_road_condition = (RelativeLayout) findViewById(R.id.main_road_condition);
        main_service = (RelativeLayout) findViewById(R.id.main_service);
        position = (RelativeLayout) findViewById(R.id.position);
        main_refresh.setOnClickListener(this);
        main_road_condition.setOnClickListener(this);
        main_service.setOnClickListener(this);
        position.setOnClickListener(this);
        main_appointment.setOnClickListener(this);

        scan_code = (LinearLayout) findViewById(R.id.scan_code);
        scan_code.setOnClickListener(this);


        // 隐藏百度的LOGO
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        // 不显示地图上比例尺
        mMapView.showScaleControl(false);
        // 不显示地图缩放控件（按钮控制栏）
        mMapView.showZoomControls(false);


        mBaiduMap = mMapView.getMap();

        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);


        float f = mBaiduMap.getMaxZoomLevel();//19.0 最小比例尺
// float m = mBaiduMap.getMinZoomLevel();//3.0 最大比例尺
//        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(mylocation, f-2);//设置到100米的大小

        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化 (比例尺大小：19.0 最小比例尺/3.0 最大比例尺)
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(f);
        //改变地图状态
        mBaiduMap.setMapStatus(msu);
        mLocationClient.start();


        initLocation();

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                main_ll_appointment.setVisibility(View.GONE);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });

    }


    /**
     * 回跳activity（新版本逻辑不使用）
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bundle mBundle = data.getExtras();
            if (mBundle != null) {
                String scanResult = mBundle.getString("result");
                showToast("扫描的结果是：" + scanResult);
            }
        }
    }

    public void AddBaiduMarker(String latitude, String longitude, Bundle mBundle) {
        //设置固定标记
        //定义Maker坐标点39.963175, 116.400244
        LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        //构建Marker图标
        BitmapDescriptor bit = BitmapDescriptorFactory.fromResource(R.mipmap.rk_blue);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .extraInfo(mBundle)
                .icon(bit);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

////////////////////////设置点击获取坐标标记//////////////////////////////////////////////////////////
//        // 设置marker图标
//        final BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.rk);
//        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
//
//            @Override
//            public boolean onMapPoiClick(MapPoi arg0) {
//                // TODO Auto-generated method stub
//                return false;
//            }
//
//            //此方法就是点击地图监听
//            @Override
//            public void onMapClick(LatLng latLng) {
//                //获取经纬度
//                double latitude = latLng.latitude;
//                double longitude = latLng.longitude;
//                System.out.println("latitude=" + latitude + ",longitude=" + longitude);
//                //先清除图层
//                mBaiduMap.clear();
//                // 定义Maker坐标点
//                LatLng point = new LatLng(latitude, longitude);
//                // 构建MarkerOption，用于在地图上添加Marker
//                MarkerOptions options = new MarkerOptions().position(point).icon(bitmap);
//                // 在地图上添加Marker，并显示
//                mBaiduMap.addOverlay(options);
//                //实例化一个地理编码查询对象
//                GeoCoder geoCoder = GeoCoder.newInstance();
//                //设置反地理编码位置坐标
//                ReverseGeoCodeOption op = new ReverseGeoCodeOption();
//                op.location(latLng);
//                //发起反地理编码请求(经纬度->地址信息)
//                geoCoder.reverseGeoCode(op);
//                geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
//
//                    @Override
//                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
//                        //获取点击的坐标地址
//                        String address = arg0.getAddress();
//
//                        Toast.makeText(MainActivity.this, address, Toast.LENGTH_SHORT).show();
//                        Log.e("address=", address);
//                    }
//
//                    @Override
//                    public void onGetGeoCodeResult(GeoCodeResult arg0) {
//
//                    }
//                });
//            }
//        });
///////////////////////////////////////////////////////////////////////////////////////////////////

    Boolean BaiduHeatMapEnabled = false; //是否开启热力图层
    Boolean TrafficEnabled = false; //是否开启交通图

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:

                startActivity(PersonInfoCenterActivity.class);

                break;
            case R.id.iv_right:

                startActivity(LoginActivity.class);

                break;
            case R.id.main_refresh:   //刷新停车场信息

                getData();

                break;
            case R.id.position:     //重新定位

                reLocation();

                break;
            case R.id.main_road_condition:    //实时路况

                //开启交通图
                if (TrafficEnabled) {
                    mBaiduMap.setTrafficEnabled(false);
                    TrafficEnabled = false;
                } else {
                    mBaiduMap.setTrafficEnabled(true);
                    TrafficEnabled = true;
                }

//                //热力图层
//                if(BaiduHeatMapEnabled){
//                    mBaiduMap.setBaiduHeatMapEnabled(false);
//                    BaiduHeatMapEnabled = false;
//                }else{
//                    mBaiduMap.setBaiduHeatMapEnabled(true);
//                    BaiduHeatMapEnabled = true;
//                }

                break;
            case R.id.main_service:   //客服

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ServiceWebView.class);
                intent.putExtra("LoadUrl", InterfaceFinals.service);
                startActivity(intent);

                break;
            case R.id.main_appointment:  //预订车位

                //判断用户是否登录
                if (!MyUtils.isLogin(MainActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {
                    if (StopPlaceId != null) {
                        startActivity(ParkOrderDetailActivity.class, StopPlaceId);
                    } else {
                        showToast("请刷新停车场后重新选择");
                    }
                }
//                showToast("为了更好体验，正在升级中，敬请期待！");

                break;
            case R.id.scan_code:    // 扫码
                //判断用户是否登录
                if (!MyUtils.isLogin(MainActivity.this)) {
                    showToast("请先登录!");
                    startActivity(LoginActivity.class);
                } else {

                    if (user.getCarNumber() == null || "".equals(user.getCarNumber())) {
                        getDataFromNet(InterfaceFinals.getSweepNumber, user.getUserId());
                    } else {
                        startActivity(CaptureActivity.class);
                    }
                }

                break;

        }

    }


    private OverlayManager routeOverlay = null;

    /**
     * 计算两点之间推荐路径
     */
    private void searchRoute(BNRoutePlanNode sNode, BNRoutePlanNode eNode) {

        BNRoutePlanNode bp1 = sNode;
        BNRoutePlanNode bp2 = eNode;


        RoutePlanSearch search = RoutePlanSearch.newInstance();        //百度的搜索路线的类
        DrivingRoutePlanOption drivingRoutePlanOption = new DrivingRoutePlanOption();
        //起始坐标和终点坐标
        PlanNode startPlanNode = PlanNode.withLocation(new LatLng(bp1.getLatitude(), bp1.getLongitude()));  // lat  long
        PlanNode endPlanNode = PlanNode.withLocation(new LatLng(bp2.getLatitude(), bp2.getLongitude()));
        drivingRoutePlanOption.from(startPlanNode);
        drivingRoutePlanOption.to(endPlanNode);
        search.drivingSearch(drivingRoutePlanOption);


        search.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {   //搜索完成的回调
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {   //步行路线
            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {     //驾车路线
//                if (drivingRouteResult.getRouteLines() == null) {
//                    showToast("算路失败");
//                    return;
//                }
//                int duration = drivingRouteResult.getRouteLines().get(0).getDistance();
//                showToast("距离是:" + duration + "米");

                if (drivingRouteResult == null || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    showToast("抱歉，未找到结果");
                }
                if (drivingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                    // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                    drivingRouteResult.getSuggestAddrInfo();
                    return;
                }

                int duration = drivingRouteResult.getRouteLines().get(0).getDistance();
                double km = Math.round(((double) duration) / 10) / 100.0;

                main_km.setText(km + "");
                showToast("距离是:" + km + "km");

                if (drivingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    if (routeOverlay != null) {
                        routeOverlay.removeFromMap();
                        routeOverlay.zoomToSpan();
                    }
//                    nodeIndex = -1;

//                    RouteLine route = null;
                    if (drivingRouteResult.getRouteLines().size() >= 1) {
//                        route = drivingRouteResult.getRouteLines().get(0); //获取推荐的第一条数据
                        DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
                        routeOverlay = overlay;
                        overlay.setData(drivingRouteResult.getRouteLines().get(0)); //获取推荐的第一条数据
//                        int a = drivingRouteResult.getRouteLines().get(0).getDistance();
//                        distance.setText(String.valueOf(a / 1000));
//                        int b = drivingRouteResult.getRouteLines().get(0).getDuration();
//                        spendTime.setText(String.valueOf(b / 60));
                        overlay.addToMap();
                        overlay.zoomToSpan();
                    } else {
                        Log.d("route result", "结果数<0");
                        return;
                    }


                }
            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }

        });
    }


    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {
        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
//            if (useDefaultIcon) {
//                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
//            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
//            if (useDefaultIcon) {
//                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
//            }
            return null;
        }
    }

    /**
     * 初始化定位监听
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(1000);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);


    }

    /**
     * 初始化方向传感器
     */

    private void initOritationListener() {
        myOrientationListener = new MyOrientationListener(getApplicationContext());
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mXDirection = x;

                // 构造定位数据
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(mCurrentAccracy)
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(mXDirection)
                        .latitude(start_latitude)
                        .longitude(start_longitude).build();
                // 设置定位数据
                mBaiduMap.setMyLocationData(locData);
//                // 设置自定义图标
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.aoi);
                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
                mBaiduMap.setMyLocationConfiguration(config);

            }
        });
    }

    public void reLocation() {
        //isFirstLoc = true;
        /**
         * 地图移动到我的位置,此处可以重新发定位请求，然后定位；
         * 直接拿最近一次经纬度，如果长时间没有定位成功，可能会显示效果不好
         */
        LatLng ll = new LatLng(start_latitude, start_longitude);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(u);
        Toast.makeText(this, "正在定位中...", Toast.LENGTH_SHORT).show();

    }

    /*
    * 定位LocationClient的监听回掉方法
    * */
    public void onReceiveLocation(BDLocation location) {

        // map view 销毁后不在处理新接收的位置
        if (location == null || mMapView == null) {
            return;
        }

        //MyApplication.setLatitude(String.valueOf(location.getLatitude()));
        // MyApplication.setLongitude(String.valueOf(location.getLongitude()));
        start_latitude = location.getLatitude();
        start_longitude = location.getLongitude();
        mCurrentAccracy = location.getRadius();
        mXDirection = location.getDirection();


        if (isFirstLoc == 0) {
            isFirstLoc = -1;


            Log.e("-------------", "开启方向传感器");
            //初始化方向传感器
            initOritationListener();
            //开启方向传感器
            myOrientationListener.start();
            //设定中心点坐标
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude()
            );
            mylocation = ll;
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(ll, 15);
            mBaiduMap.animateMapStatus(mapStatusUpdate);
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }


    private long firstTime = 0;

    /**
     * 双击退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {
                    //两次按键小于2秒时，退出应用
                    MyApplication.getInstance().onTerminate();
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onStart() {

        // 开启图层定位
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }

        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        // mMapView.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();
        // 关闭图层定位
        mBaiduMap.setMyLocationEnabled(false);
        //停止定位
        mLocationClient.stop();

        // 关闭方向传感器
        myOrientationListener.stop();
    }

    //  public static List<Activity> activityList = new LinkedList<Activity>();
    //ublic static final String ROUTE_PLAN_NODE = "routePlanNode";

}
