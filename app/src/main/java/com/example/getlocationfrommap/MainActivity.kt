package com.example.getlocationfrommap

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // 設定地圖初始位置(南蘇丹-朱巴)
        val defaultLocation = LatLng( 4.855216720307577, 31.57959156419089)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 15f))

        // 設定點擊地圖的事件
        mMap.setOnMapClickListener(object :GoogleMap.OnMapClickListener {
            override fun onMapClick(latlng : LatLng) {
                addMarkerAndMoveCamera(latlng)
                getAddressFromLatLng(latlng)
            }
        })
    }

    private fun addMarkerAndMoveCamera(latLng: LatLng) {
        mMap.clear()  // 清除之前的標記
        mMap.addMarker(MarkerOptions().position(latLng).title("Marker at $latLng"))
    }

    private fun getAddressFromLatLng(latLng: LatLng) {
        val geocoder = Geocoder(this, Locale.getDefault())

        findViewById<TextView>(R.id.latitudeTextView).setText(latLng.latitude.toString())
        findViewById<TextView>(R.id.longitudeTextView).setText(latLng.longitude.toString())
        try {
            val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0].getAddressLine(0)
                findViewById<TextView>(R.id.addressTextView).setText(address)
            } else {
                findViewById<TextView>(R.id.addressTextView).setText("未找到地址")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            findViewById<TextView>(R.id.addressTextView).setText("無法獲取地址: ${e.message}")
        }
    }

}