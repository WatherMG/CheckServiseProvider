package com.drwat.checkserviceprovider;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Region {
    private String name;
    private String code;
    private ServiceProvider[] serviceProviders;

    public Region(String name, String code, ServiceProvider[] serviceProviders) {
        this.name = name;
        this.code = code;
        this.serviceProviders = serviceProviders;
    }

    public static List<Region> getRegionList(Context context) {
        List<Region> list = new ArrayList<>();
        list.add(new Region(context.getString(R.string.country), "38",
                new ServiceProvider[]{ServiceProvider.Kiyevstar,
                        ServiceProvider.Vodafone,
                        ServiceProvider.Lifecell,
                        ServiceProvider.Urktelecom,
                        ServiceProvider.Trimob,
                        ServiceProvider.Peoplenet,
                        ServiceProvider.InterTelecom}));
        list.add(new Region(context.getString(R.string.kharkov), "38",
                new ServiceProvider[]{ServiceProvider.InterTelecomKharkov,
                        ServiceProvider.UrktelecomKharkov,
                        ServiceProvider.DatagroupKharkov}));
        list.add(new Region(context.getString(R.string.lvov), "38",
                new ServiceProvider[]{ServiceProvider.InterTelecomLvov,
                        ServiceProvider.UrktelecomLvov,
                        ServiceProvider.DatagroupLvov}));
        list.add(new Region(context.getString(R.string.sip_telefony), "38",
                new ServiceProvider[]{ServiceProvider.DatagroupSIP,
                        ServiceProvider.AtlantisTelecomSIP,
                        ServiceProvider.KiyvstarSIP,
                        ServiceProvider.T_R_ComunicationSIP,
                        ServiceProvider.UrktelecomSIP,
                        ServiceProvider.VeltonTelecomSIP,
                        ServiceProvider.VodafoneSIP,
                        ServiceProvider.Lincom_3000SIP}));
        return list;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public ServiceProvider[] getServiceProviders() {
        return serviceProviders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        return code.equals(region.code) && name.equals(region.name) && Arrays.equals(serviceProviders, region.getServiceProviders());

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + Arrays.hashCode(serviceProviders);
        return result;
    }

    @Override
    public String toString() {
        return "Region{" + "name='" + name + '\'' + ", code='" + code + '\'' + ", serviceProviders=" + Arrays.toString(serviceProviders) + "}";
    }
}
