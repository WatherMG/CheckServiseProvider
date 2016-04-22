package com.drwat.checkserviceprovider;

public enum ServiceProvider {
    Kiyevstar("КИЕВСТАР", "^(06(7|8)|09(6|7|8)|039)[0-9]{0,7}", Mask.MASK_1),
    Vodafone("VODAFONE", "^(095|099|066|050)[0-9]{0,7}", Mask.MASK_2),
    Lifecell("LIFECELL", "^(0(6|7|9)3)[0-9]{0,7}", Mask.MASK_3),
    Urktelecom("УКРТЕЛЕКОМ", "^(090)[0-9]{0,7}", Mask.MASK_1),
    Trimob("ТРИМОБ", "^(091)[0-9]{0,7}", Mask.MASK_1),
    Peoplenet("PEOPLENET", "^(092)[0-9]{0,7}", Mask.MASK_1),
    InterTelecom("ИНТЕРТЕЛЕКОМ", "^(094)[0-9]{0,7}", Mask.MASK_1),
    /*Харьков*/
    InterTelecomKharkov("ИНТЕРТЕЛЕКОМ",
            "^(057)(750[6-9])[0-9]{0,3}" +
                    "|(057)(768[2-4])[0-9]{0,3}" +
                    "|(057)(751[0-7])[0-9]{0,3}" +
                    "|(057)(752[6-9])[0-9]{0,3}" +
                    "|(057)(756[0-2])[0-9]{0,3}" +
                    "|(057)(756[4-9])[0-9]{0,3}" +
                    "|(057)(757[2-3])[0-9]{0,3}" +
                    "|(057)(759[4-8])[0-9]{0,3}" +
                    "|(057)(762[6-9])[0-9]{0,3}" +
                    "|(057)(754[8-9])[0-9]{0,3}" +
                    "|(057)(758[2-4])[0-9]{0,3}" +
                    "|(057)(7589)[0-9]{0,3}" +
                    "|(057)(7547)[0-9]{0,3}" +
                    "|(057)(7576)[0-9]{0,3}" +
                    "|(057)(7579)[0-9]{0,3}" +
                    "|(057)(7623)[0-9]{0,3}" +
                    "|(057)(7689)[0-9]{0,3}" +
                    "|(057)(78[0-2])[0-9]{0,4}" +
                    "|(057)(755)[0-9]{0,4}" +
                    "|(057)(764)[0-9]{0,4}"
            , Mask.MASK_4),
    UrktelecomKharkov("УКРТЕЛЕКОМ",
            "^(057)(765)[0-9]{0,4}" +
                    "|(057)(756)[0-9]{0,4}" +
                    "|(057)(78[3-5])[0-9]{0,4}" +
                    "|(057)(750[0-2])[0-9]{0,3}" +
                    "|(057)(768[0|1|[5-7]])[0-9]{0,3}"
            , Mask.MASK_4),
    DatagroupKharkov("ДАТАГРУПП",
            "^(057)(766)[0-9]{0,4}" +
                    "|(057)(757)[0-9]{0,4}" +
                    "|(057)(78[6-9])[0-9]{0,4}" +
                    "|(057)(750[3-5])[0-9]{0,3}" +
                    "|(057)(768[8-9])[0-9]{0,3}", Mask.MASK_4),
    /*Львов*/
    InterTelecomLvov("ИНТЕРТЕЛЕКОМ",
            "^(032)(246[5-9])[0-9]{0,3}" +
                    "|(032)(254[2-5])[0-9]{0,3}" +
                    "|(032)(254[7-9])[0-9]{0,3}" +
                    "|(032)(256[6-8])[0-9]{0,3}" +
                    "|(032)(298[0-5])[0-9]{0,3}" +
                    "|(032)(299[0-8])[0-9]{0,3}" +
                    "|(032)(2201)[0-9]{0,3}" +
                    "|(032)(243)[0-9]{0,4}" +
                    "|(032)(247)[0-9]{0,4}" +
                    "|(032)(253)[0-9]{0,4}" +
                    "|(032)(257)[0-9]{0,4}"
            , Mask.MASK_3),
    UrktelecomLvov("УКРТЕЛЕКОМ",
            "^(032)(246[0-2])[0-9]{0,3}" +
                    "|(032)(254[0-1])[0-9]{0,3}" +
                    "|(032)(256[0-5])[0-9]{0,3}" +
                    "|(032)(298[6-8])[0-9]{0,3}" +
                    "|(032)(2999)[0-9]{0,3}"
            , Mask.MASK_2),
    DatagroupLvov("ДАТАГРУПП",
            "^(032)(246[3-4])[0-9]{0,3}" +
                    "|(032)(2546)[0-9]{0,3}" +
                    "|(032)(2569)[0-9]{0,3}" +
                    "|(032)(2989)[0-9]{0,3}" +
                    "|(032)(220[2-9])[0-9]{0,3}", Mask.MASK_1),
    DatagroupSIP("ДАТАГРУПП", "^(0891)[0-9]{0,6}", Mask.MASK_1),
    UrktelecomSIP("УКРТЕЛЕКОМ", "^(0892)[0-9]{0,6}", Mask.MASK_1),
    T_R_ComunicationSIP("Т.Р.КОМЬЮНИКЕЙШН", "^(0893)[0-9]{0,6}", Mask.MASK_1),
    AtlantisTelecomSIP("АТЛАНТИС ТЕЛЕКОМ", "^(0894)[0-9]{0,6}", Mask.MASK_1),
    Lincom_3000SIP("ЛИНКОМ-3000", "^(0895)[0-9]{0,6}", Mask.MASK_1),
    VodafoneSIP("VODAFONE", "^(0896)[0-9]{0,6}", Mask.MASK_1),
    KiyvstarSIP("КИЕВСТАР", "^(0897)[0-9]{0,6}", Mask.MASK_1),
    VeltonTelecomSIP("ВЕЛТОН.ТЕЛЕКОМ", "^(0899)[0-9]{0,6}", Mask.MASK_1);


    private String name;
    private String code;
    private String mask;

    ServiceProvider(String name, String code, String mask) {
        this.name = name;
        this.code = code;
        this.mask = mask;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getMask() {
        return mask;
    }
}
