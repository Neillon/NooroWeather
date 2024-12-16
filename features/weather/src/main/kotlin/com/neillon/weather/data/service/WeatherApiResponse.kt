package com.neillon.weather.data.service

import com.google.gson.annotations.SerializedName

data class WeatherApiResponse(
    @SerializedName("location") var location: Location,
    @SerializedName("current") var current: Current
)

data class Location(
    @SerializedName("name") var name: String,
    @SerializedName("region") var region: String,
    @SerializedName("country") var country: String,
    @SerializedName("lat") var lat: Double,
    @SerializedName("lon") var lon: Double,
    @SerializedName("tz_id") var tzId: String,
    @SerializedName("localtime_epoch") var localtimeEpoch: Int,
    @SerializedName("localtime") var localtime: String
)

data class Condition(
    @SerializedName("text") var text: String,
    @SerializedName("icon") var icon: String,
    @SerializedName("code") var code: Int
)

data class Current(
    @SerializedName("last_updated_epoch") var lastUpdatedEpoch: Int,
    @SerializedName("last_updated") var lastUpdated: String,
    @SerializedName("temp_c") var tempC: Double,
    @SerializedName("temp_f") var tempF: Double,
    @SerializedName("is_day") var isDay: Int,
    @SerializedName("condition") var condition: Condition,
    @SerializedName("wind_mph") var windMph: Double,
    @SerializedName("wind_kph") var windKph: Double,
    @SerializedName("wind_degree") var windDegree: Int,
    @SerializedName("wind_dir") var windDir: String,
    @SerializedName("pressure_mb") var pressureMb: Int,
    @SerializedName("pressure_in") var pressureIn: Double,
    @SerializedName("precip_mm") var precipMm: Int,
    @SerializedName("precip_in") var precipIn: Int,
    @SerializedName("humidity") var humidity: Int,
    @SerializedName("cloud") var cloud: Int,
    @SerializedName("feelslike_c") var feelslikeC: Double,
    @SerializedName("feelslike_f") var feelslikeF: Double,
    @SerializedName("windchill_c") var windchillC: Double,
    @SerializedName("windchill_f") var windchillF: Int,
    @SerializedName("heatindex_c") var heatindexC: Double,
    @SerializedName("heatindex_f") var heatindexF: Int,
    @SerializedName("dewpoint_c") var dewpointC: Double,
    @SerializedName("dewpoint_f") var dewpointF: Int,
    @SerializedName("vis_km") var visKm: Int,
    @SerializedName("vis_miles") var visMiles: Int,
    @SerializedName("uv") var uv: Double,
    @SerializedName("gust_mph") var gustMph: Double,
    @SerializedName("gust_kph") var gustKph: Double
)