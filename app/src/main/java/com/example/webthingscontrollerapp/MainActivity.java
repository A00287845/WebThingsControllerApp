package com.example.webthingscontrollerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ThingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewModel = new ViewModelProvider(this).get(ThingViewModel.class);

        viewModel.getIsLoading().observe(this, isLoading -> {
            // Show loading indicator if isLoading is true
        });

        viewModel.getThings().observe(this, things -> {
            // Update UI with the list of things
        });

        viewModel.loadThings();
//
//        String jsonOneObjectTest;
//        {
//            jsonOneObjectTest = " {\n" +
//                    "        \"title\": \"SenseHat\",\n" +
//                    "        \"@context\": \"https://webthings.io/schemas\",\n" +
//                    "        \"@type\": [\n" +
//                    "            \"TemperatureSensor\"\n" +
//                    "        ],\n" +
//                    "        \"description\": \"Expose SenseHat sensors\",\n" +
//                    "        \"href\": \"/things/sense-hat\",\n" +
//                    "        \"properties\": {\n" +
//                    "            \"humidity\": {\n" +
//                    "                \"name\": \"humidity\",\n" +
//                    "                \"value\": 0,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"NumberProperty\",\n" +
//                    "                \"label\": \"Humidity\",\n" +
//                    "                \"type\": \"integer\",\n" +
//                    "                \"unit\": \"%\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Humidity\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/humidity\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"pressure\": {\n" +
//                    "                \"name\": \"pressure\",\n" +
//                    "                \"value\": 0,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"NumberProperty\",\n" +
//                    "                \"label\": \"Pressure\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"type\": \"number\",\n" +
//                    "                \"unit\": \"hPa\",\n" +
//                    "                \"title\": \"Pressure\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/pressure\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"temperature\": {\n" +
//                    "                \"name\": \"temperature\",\n" +
//                    "                \"value\": 0,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"TemperatureProperty\",\n" +
//                    "                \"label\": \"Temperature\",\n" +
//                    "                \"type\": \"number\",\n" +
//                    "                \"unit\": \"degrees celsius\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Temperature\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/temperature\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"down\": {\n" +
//                    "                \"name\": \"down\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"PushedProperty\",\n" +
//                    "                \"label\": \"Down\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Down\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/down\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"left\": {\n" +
//                    "                \"name\": \"left\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"PushedProperty\",\n" +
//                    "                \"label\": \"Left\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Left\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/left\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"right\": {\n" +
//                    "                \"name\": \"right\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"PushedProperty\",\n" +
//                    "                \"label\": \"Right\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Right\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/right\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"up\": {\n" +
//                    "                \"name\": \"up\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"PushedProperty\",\n" +
//                    "                \"label\": \"Up\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Up\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/up\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        \"actions\": {},\n" +
//                    "        \"events\": {},\n" +
//                    "        \"links\": [\n" +
//                    "            {\n" +
//                    "                \"rel\": \"properties\",\n" +
//                    "                \"href\": \"/things/sense-hat/properties\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"actions\",\n" +
//                    "                \"href\": \"/things/sense-hat/actions\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"events\",\n" +
//                    "                \"href\": \"/things/sense-hat/events\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"alternate\",\n" +
//                    "                \"mediaType\": \"text/html\",\n" +
//                    "                \"href\": \"https://github.com/rzr/sense-hat-webthing\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"alternate\",\n" +
//                    "                \"href\": \"wss://a00287845.webthings.io/things/sense-hat\"\n" +
//                    "            }\n" +
//                    "        ],\n" +
//                    "        \"layoutIndex\": 0,\n" +
//                    "        \"selectedCapability\": \"TemperatureSensor\",\n" +
//                    "        \"iconHref\": null,\n" +
//                    "        \"id\": \"https://a00287845.webthings.io/things/sense-hat\",\n" +
//                    "        \"base\": \"https://a00287845.webthings.io/\",\n" +
//                    "        \"securityDefinitions\": {\n" +
//                    "            \"oauth2_sc\": {\n" +
//                    "                \"scheme\": \"oauth2\",\n" +
//                    "                \"flow\": \"code\",\n" +
//                    "                \"authorization\": \"https://a00287845.webthings.io/oauth/authorize\",\n" +
//                    "                \"token\": \"https://a00287845.webthings.io/oauth/token\",\n" +
//                    "                \"scopes\": [\n" +
//                    "                    \"/things/sense-hat:readwrite\",\n" +
//                    "                    \"/things/sense-hat\",\n" +
//                    "                    \"/things:readwrite\",\n" +
//                    "                    \"/things\"\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        \"security\": \"oauth2_sc\"\n" +
//                    "    }";
//        }
//
//        Gson gson = new Gson();
//
//        Thing thingTest = gson.fromJson(jsonOneObjectTest, Thing.class);
//
//        System.out.println("TEST " + thingTest.toString());
//
//        String allThings;
//        {
//            allThings = "[\n" +
//                    "    {\n" +
//                    "        \"title\": \"SenseHat\",\n" +
//                    "        \"@context\": \"https://webthings.io/schemas\",\n" +
//                    "        \"@type\": [\n" +
//                    "            \"TemperatureSensor\"\n" +
//                    "        ],\n" +
//                    "        \"description\": \"Expose SenseHat sensors\",\n" +
//                    "        \"href\": \"/things/sense-hat\",\n" +
//                    "        \"properties\": {\n" +
//                    "            \"humidity\": {\n" +
//                    "                \"name\": \"humidity\",\n" +
//                    "                \"value\": 0,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"NumberProperty\",\n" +
//                    "                \"label\": \"Humidity\",\n" +
//                    "                \"type\": \"integer\",\n" +
//                    "                \"unit\": \"%\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Humidity\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/humidity\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"pressure\": {\n" +
//                    "                \"name\": \"pressure\",\n" +
//                    "                \"value\": 0,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"NumberProperty\",\n" +
//                    "                \"label\": \"Pressure\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"type\": \"number\",\n" +
//                    "                \"unit\": \"hPa\",\n" +
//                    "                \"title\": \"Pressure\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/pressure\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"temperature\": {\n" +
//                    "                \"name\": \"temperature\",\n" +
//                    "                \"value\": 0,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"TemperatureProperty\",\n" +
//                    "                \"label\": \"Temperature\",\n" +
//                    "                \"type\": \"number\",\n" +
//                    "                \"unit\": \"degrees celsius\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Temperature\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/temperature\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"down\": {\n" +
//                    "                \"name\": \"down\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"PushedProperty\",\n" +
//                    "                \"label\": \"Down\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Down\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/down\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"left\": {\n" +
//                    "                \"name\": \"left\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"PushedProperty\",\n" +
//                    "                \"label\": \"Left\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Left\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/left\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"right\": {\n" +
//                    "                \"name\": \"right\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"PushedProperty\",\n" +
//                    "                \"label\": \"Right\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Right\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/right\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"up\": {\n" +
//                    "                \"name\": \"up\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"PushedProperty\",\n" +
//                    "                \"label\": \"Up\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Up\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat/properties/up\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        \"actions\": {},\n" +
//                    "        \"events\": {},\n" +
//                    "        \"links\": [\n" +
//                    "            {\n" +
//                    "                \"rel\": \"properties\",\n" +
//                    "                \"href\": \"/things/sense-hat/properties\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"actions\",\n" +
//                    "                \"href\": \"/things/sense-hat/actions\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"events\",\n" +
//                    "                \"href\": \"/things/sense-hat/events\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"alternate\",\n" +
//                    "                \"mediaType\": \"text/html\",\n" +
//                    "                \"href\": \"https://github.com/rzr/sense-hat-webthing\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"alternate\",\n" +
//                    "                \"href\": \"wss://a00287845.webthings.io/things/sense-hat\"\n" +
//                    "            }\n" +
//                    "        ],\n" +
//                    "        \"layoutIndex\": 0,\n" +
//                    "        \"selectedCapability\": \"TemperatureSensor\",\n" +
//                    "        \"iconHref\": null,\n" +
//                    "        \"id\": \"https://a00287845.webthings.io/things/sense-hat\",\n" +
//                    "        \"base\": \"https://a00287845.webthings.io/\",\n" +
//                    "        \"securityDefinitions\": {\n" +
//                    "            \"oauth2_sc\": {\n" +
//                    "                \"scheme\": \"oauth2\",\n" +
//                    "                \"flow\": \"code\",\n" +
//                    "                \"authorization\": \"https://a00287845.webthings.io/oauth/authorize\",\n" +
//                    "                \"token\": \"https://a00287845.webthings.io/oauth/token\",\n" +
//                    "                \"scopes\": [\n" +
//                    "                    \"/things/sense-hat:readwrite\",\n" +
//                    "                    \"/things/sense-hat\",\n" +
//                    "                    \"/things:readwrite\",\n" +
//                    "                    \"/things\"\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        \"security\": \"oauth2_sc\"\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "        \"title\": \"SenseHatImu\",\n" +
//                    "        \"@context\": \"https://webthings.io/schemas\",\n" +
//                    "        \"@type\": [\n" +
//                    "            \"MultiLevelSensor\"\n" +
//                    "        ],\n" +
//                    "        \"description\": \"Expose SenseHat IMU sensors\",\n" +
//                    "        \"href\": \"/things/sense-hat-imu\",\n" +
//                    "        \"properties\": {\n" +
//                    "            \"yaw\": {\n" +
//                    "                \"name\": \"yaw\",\n" +
//                    "                \"value\": 0,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"LevelProperty\",\n" +
//                    "                \"label\": \"Yaw\",\n" +
//                    "                \"type\": \"number\",\n" +
//                    "                \"description\": \"Yaw Angle (North)\",\n" +
//                    "                \"unit\": \"º\",\n" +
//                    "                \"minimum\": -180,\n" +
//                    "                \"maximum\": 180,\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Yaw\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-imu/properties/yaw\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"pitch\": {\n" +
//                    "                \"name\": \"pitch\",\n" +
//                    "                \"value\": 0,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"LevelProperty\",\n" +
//                    "                \"label\": \"Pitch\",\n" +
//                    "                \"type\": \"number\",\n" +
//                    "                \"description\": \"Pitch Angle\",\n" +
//                    "                \"unit\": \"º\",\n" +
//                    "                \"minimum\": -180,\n" +
//                    "                \"maximum\": 180,\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Pitch\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-imu/properties/pitch\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"roll\": {\n" +
//                    "                \"name\": \"roll\",\n" +
//                    "                \"value\": 0,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"LevelProperty\",\n" +
//                    "                \"label\": \"Roll\",\n" +
//                    "                \"type\": \"number\",\n" +
//                    "                \"description\": \"Roll Angle\",\n" +
//                    "                \"unit\": \"º\",\n" +
//                    "                \"minimum\": -180,\n" +
//                    "                \"maximum\": 180,\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"title\": \"Roll\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-imu/properties/roll\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"compass\": {\n" +
//                    "                \"name\": \"compass\",\n" +
//                    "                \"value\": true,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"OnOffProperty\",\n" +
//                    "                \"label\": \"Compass\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": false,\n" +
//                    "                \"title\": \"Compass\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-imu/properties/compass\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"gyro\": {\n" +
//                    "                \"name\": \"gyro\",\n" +
//                    "                \"value\": true,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"OnOffProperty\",\n" +
//                    "                \"label\": \"Gyro\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": false,\n" +
//                    "                \"title\": \"Gyro\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-imu/properties/gyro\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"accel\": {\n" +
//                    "                \"name\": \"accel\",\n" +
//                    "                \"value\": true,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"OnOffProperty\",\n" +
//                    "                \"label\": \"Accel\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": false,\n" +
//                    "                \"title\": \"Accel\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-imu/properties/accel\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        \"actions\": {},\n" +
//                    "        \"events\": {},\n" +
//                    "        \"links\": [\n" +
//                    "            {\n" +
//                    "                \"rel\": \"properties\",\n" +
//                    "                \"href\": \"/things/sense-hat-imu/properties\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"actions\",\n" +
//                    "                \"href\": \"/things/sense-hat-imu/actions\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"events\",\n" +
//                    "                \"href\": \"/things/sense-hat-imu/events\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"alternate\",\n" +
//                    "                \"mediaType\": \"text/html\",\n" +
//                    "                \"href\": \"https://github.com/rzr/sense-hat-webthing\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"alternate\",\n" +
//                    "                \"href\": \"wss://a00287845.webthings.io/things/sense-hat-imu\"\n" +
//                    "            }\n" +
//                    "        ],\n" +
//                    "        \"layoutIndex\": 1,\n" +
//                    "        \"selectedCapability\": \"MultiLevelSensor\",\n" +
//                    "        \"iconHref\": null,\n" +
//                    "        \"id\": \"https://a00287845.webthings.io/things/sense-hat-imu\",\n" +
//                    "        \"base\": \"https://a00287845.webthings.io/\",\n" +
//                    "        \"securityDefinitions\": {\n" +
//                    "            \"oauth2_sc\": {\n" +
//                    "                \"scheme\": \"oauth2\",\n" +
//                    "                \"flow\": \"code\",\n" +
//                    "                \"authorization\": \"https://a00287845.webthings.io/oauth/authorize\",\n" +
//                    "                \"token\": \"https://a00287845.webthings.io/oauth/token\",\n" +
//                    "                \"scopes\": [\n" +
//                    "                    \"/things/sense-hat-imu:readwrite\",\n" +
//                    "                    \"/things/sense-hat-imu\",\n" +
//                    "                    \"/things:readwrite\",\n" +
//                    "                    \"/things\"\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        \"security\": \"oauth2_sc\"\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "        \"title\": \"SenseHatLight\",\n" +
//                    "        \"@context\": \"https://webthings.io/schemas\",\n" +
//                    "        \"@type\": [\n" +
//                    "            \"ColorControl\",\n" +
//                    "            \"Light\",\n" +
//                    "            \"OnOffSwitch\"\n" +
//                    "        ],\n" +
//                    "        \"description\": \"Expose SenseHat actuators\",\n" +
//                    "        \"href\": \"/things/sense-hat-light\",\n" +
//                    "        \"properties\": {\n" +
//                    "            \"character\": {\n" +
//                    "                \"name\": \"character\",\n" +
//                    "                \"value\": \"\",\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"StringProperty\",\n" +
//                    "                \"label\": \"Character\",\n" +
//                    "                \"type\": \"string\",\n" +
//                    "                \"title\": \"Character\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-light/properties/character\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"message\": {\n" +
//                    "                \"name\": \"message\",\n" +
//                    "                \"value\": \"\",\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"StringProperty\",\n" +
//                    "                \"label\": \"Message\",\n" +
//                    "                \"type\": \"string\",\n" +
//                    "                \"title\": \"Message\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-light/properties/message\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"color\": {\n" +
//                    "                \"name\": \"color\",\n" +
//                    "                \"value\": \"#ffffff\",\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"ColorProperty\",\n" +
//                    "                \"label\": \"Color\",\n" +
//                    "                \"type\": \"string\",\n" +
//                    "                \"readOnly\": false,\n" +
//                    "                \"title\": \"Color\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-light/properties/color\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"on\": {\n" +
//                    "                \"name\": \"on\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"OnOffProperty\",\n" +
//                    "                \"label\": \"Switch\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"readOnly\": false,\n" +
//                    "                \"title\": \"Switch\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-light/properties/on\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"rotation\": {\n" +
//                    "                \"name\": \"rotation\",\n" +
//                    "                \"value\": 0,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"NumberProperty\",\n" +
//                    "                \"label\": \"Rotation\",\n" +
//                    "                \"type\": \"integer\",\n" +
//                    "                \"description\": \"Rotation of LED matrix\",\n" +
//                    "                \"unit\": \"degrees\",\n" +
//                    "                \"enum\": [\n" +
//                    "                    0,\n" +
//                    "                    90,\n" +
//                    "                    180,\n" +
//                    "                    270\n" +
//                    "                ],\n" +
//                    "                \"title\": \"Rotation\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-light/properties/rotation\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"dim\": {\n" +
//                    "                \"name\": \"dim\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"@type\": \"BooleanProperty\",\n" +
//                    "                \"label\": \"Dim\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"description\": \"Low light for dark environement\",\n" +
//                    "                \"title\": \"Dim\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/sense-hat-light/properties/dim\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        \"actions\": {},\n" +
//                    "        \"events\": {},\n" +
//                    "        \"links\": [\n" +
//                    "            {\n" +
//                    "                \"rel\": \"properties\",\n" +
//                    "                \"href\": \"/things/sense-hat-light/properties\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"actions\",\n" +
//                    "                \"href\": \"/things/sense-hat-light/actions\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"events\",\n" +
//                    "                \"href\": \"/things/sense-hat-light/events\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"alternate\",\n" +
//                    "                \"mediaType\": \"text/html\",\n" +
//                    "                \"href\": \"https://github.com/rzr/sense-hat-webthing\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"alternate\",\n" +
//                    "                \"href\": \"wss://a00287845.webthings.io/things/sense-hat-light\"\n" +
//                    "            }\n" +
//                    "        ],\n" +
//                    "        \"layoutIndex\": 2,\n" +
//                    "        \"selectedCapability\": \"Light\",\n" +
//                    "        \"iconHref\": null,\n" +
//                    "        \"id\": \"https://a00287845.webthings.io/things/sense-hat-light\",\n" +
//                    "        \"base\": \"https://a00287845.webthings.io/\",\n" +
//                    "        \"securityDefinitions\": {\n" +
//                    "            \"oauth2_sc\": {\n" +
//                    "                \"scheme\": \"oauth2\",\n" +
//                    "                \"flow\": \"code\",\n" +
//                    "                \"authorization\": \"https://a00287845.webthings.io/oauth/authorize\",\n" +
//                    "                \"token\": \"https://a00287845.webthings.io/oauth/token\",\n" +
//                    "                \"scopes\": [\n" +
//                    "                    \"/things/sense-hat-light:readwrite\",\n" +
//                    "                    \"/things/sense-hat-light\",\n" +
//                    "                    \"/things:readwrite\",\n" +
//                    "                    \"/things\"\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        \"security\": \"oauth2_sc\"\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "        \"title\": \"Back door TV\",\n" +
//                    "        \"@context\": \"https://webthings.io/schemas\",\n" +
//                    "        \"@type\": [\n" +
//                    "            \"OnOffSwitch\"\n" +
//                    "        ],\n" +
//                    "        \"description\": \"Chromecast\",\n" +
//                    "        \"href\": \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local.\",\n" +
//                    "        \"properties\": {\n" +
//                    "            \"volume\": {\n" +
//                    "                \"name\": \"volume\",\n" +
//                    "                \"value\": 100,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"title\": \"Volume\",\n" +
//                    "                \"type\": \"integer\",\n" +
//                    "                \"@type\": \"LevelProperty\",\n" +
//                    "                \"unit\": \"percent\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local./properties/volume\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"on\": {\n" +
//                    "                \"name\": \"on\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"title\": \"On/Off\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"@type\": \"OnOffProperty\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local./properties/on\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"playing\": {\n" +
//                    "                \"name\": \"playing\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"title\": \"Play/Pause\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"@type\": \"BooleanProperty\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local./properties/playing\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"muted\": {\n" +
//                    "                \"name\": \"muted\",\n" +
//                    "                \"value\": false,\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"title\": \"Muted\",\n" +
//                    "                \"type\": \"boolean\",\n" +
//                    "                \"@type\": \"BooleanProperty\",\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local./properties/muted\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            },\n" +
//                    "            \"app\": {\n" +
//                    "                \"name\": \"app\",\n" +
//                    "                \"value\": \"Backdrop\",\n" +
//                    "                \"visible\": true,\n" +
//                    "                \"title\": \"App\",\n" +
//                    "                \"type\": \"string\",\n" +
//                    "                \"readOnly\": true,\n" +
//                    "                \"links\": [\n" +
//                    "                    {\n" +
//                    "                        \"rel\": \"property\",\n" +
//                    "                        \"href\": \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local./properties/app\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        \"actions\": {},\n" +
//                    "        \"events\": {},\n" +
//                    "        \"links\": [\n" +
//                    "            {\n" +
//                    "                \"rel\": \"properties\",\n" +
//                    "                \"href\": \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local./properties\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"actions\",\n" +
//                    "                \"href\": \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local./actions\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"events\",\n" +
//                    "                \"href\": \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local./events\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"alternate\",\n" +
//                    "                \"mediaType\": \"text/html\",\n" +
//                    "                \"href\": \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local.\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "                \"rel\": \"alternate\",\n" +
//                    "                \"href\": \"wss://a00287845.webthings.io/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local.\"\n" +
//                    "            }\n" +
//                    "        ],\n" +
//                    "        \"layoutIndex\": 3,\n" +
//                    "        \"selectedCapability\": \"OnOffSwitch\",\n" +
//                    "        \"iconHref\": null,\n" +
//                    "        \"id\": \"https://a00287845.webthings.io/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local.\",\n" +
//                    "        \"base\": \"https://a00287845.webthings.io/\",\n" +
//                    "        \"securityDefinitions\": {\n" +
//                    "            \"oauth2_sc\": {\n" +
//                    "                \"scheme\": \"oauth2\",\n" +
//                    "                \"flow\": \"code\",\n" +
//                    "                \"authorization\": \"https://a00287845.webthings.io/oauth/authorize\",\n" +
//                    "                \"token\": \"https://a00287845.webthings.io/oauth/token\",\n" +
//                    "                \"scopes\": [\n" +
//                    "                    \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local.:readwrite\",\n" +
//                    "                    \"/things/Chromecast-b9be99ed2e1a310230a9efb1a8cbf6a9._googlecast._tcp.local.\",\n" +
//                    "                    \"/things:readwrite\",\n" +
//                    "                    \"/things\"\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        \"security\": \"oauth2_sc\"\n" +
//                    "    }\n" +
//                    "]";
//
//        }
//
//        Type listOfThingsType = new TypeToken<List<Thing>>(){}.getType();
//        List<Thing> things = gson.fromJson(allThings, listOfThingsType);
//        for (Thing thing : things) {
//            System.out.println(thing.getTitle());
//        }
    }
}