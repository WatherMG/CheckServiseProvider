package com.drwat.checkserviceprovider;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberEditTextView extends EditText {

    private OnChangeCurrentRegionListener onChangeCurrentRegionListener;
    private OnChangeCurrentServiceProviderListener onChangeCurrentServiceProviderListener;
    private List<Region> regionList = null;
    private Region currentRegion = null;
    private ServiceProvider currentProvider = null;
    private boolean useMask = true;
    private CustomTextWatcher customTextWatcher;

    public PhoneNumberEditTextView(Context context) {
        super(context);
        init(context);
    }

    public PhoneNumberEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PhoneNumberEditTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void setOnChangeCurrentRegionListener(OnChangeCurrentRegionListener listener) {
        this.onChangeCurrentRegionListener = listener;
    }

    public void setChangeCurrentServiceProviderListener(OnChangeCurrentServiceProviderListener listener) {
        this.onChangeCurrentServiceProviderListener = listener;
    }

    private void init(Context context) {
        regionList = Region.getRegionList(context);
        customTextWatcher = new CustomTextWatcher();
        addTextChangedListener(customTextWatcher);
    }

    public void setUseMask(boolean flag) {
        this.useMask = flag;
    }

    private void formatText(String str) {
        if (!useMask) {
            return;
        }
        if (currentProvider == null) {
            setText(str, str.length());
            return;
        }
        if (currentRegion != null && currentProvider.getName() != null) {
            StringBuilder stringBuilder = new StringBuilder("+ " + currentRegion.getCode().replaceAll("[0-9]", "x") + " " + currentProvider.getMask());
            for (int i = 0; i < str.length(); i++) {
                int pos = stringBuilder.indexOf("x");
                if (pos != -1) {
                    stringBuilder.replace(pos, pos + 1, String.valueOf(str.charAt(i)));
                }
            }
            int selectedPosition = stringBuilder.lastIndexOf(String.valueOf(str.charAt(str.length() - 1)));
            setText(stringBuilder.toString(), selectedPosition + 1);
        }
    }

    private void setText(String text, int selectedPosition) {
        removeTextChangedListener(customTextWatcher);
        setText(text);
        setSelection(selectedPosition);
        addTextChangedListener(customTextWatcher);
    }

    private void findRegion(String str) { //поиск региона
        if (currentRegion != null) {// если не пустой
            if (str.startsWith(currentRegion.getCode())) {//и если содержит DEF-код
                findRegion(currentRegion, str.substring(currentRegion.getCode().length()));//поиск региона
                return;//возвращаем значение
            } else {
                currentRegion = null;// нет решиона
                if (onChangeCurrentRegionListener != null) {
                    onChangeCurrentRegionListener.onChange(null);// слушателю ввода присваеваем пустую ссылку
                }
            }
        }

        List<Region> tempRegionList = new ArrayList<>();//временный список

        for (Region item : regionList) {//добавляем регионы во временный список
            if (str.startsWith(item.getCode())) {
                if (!tempRegionList.contains(item)) {
                    tempRegionList.add(item);
                }
            }
        }

        if (tempRegionList.size() > 1) {//если содержит во временном списке больше 1 елемента
            currentRegion = findCurrentRegionByOperator(tempRegionList, str);//поиск  региона по оператопу
        } else if (tempRegionList.size() == 1) {//если содерит 1 елемент
            currentRegion = tempRegionList.get(0);
            findRegion(currentRegion, str.substring(currentRegion.getCode().length()));//поиск региона по DEF-коду
        }

        if (currentRegion != null) {
            if (onChangeCurrentRegionListener != null) {
                onChangeCurrentRegionListener.onChange(currentRegion);
            }
        }
    }

    private Region findCurrentRegionByOperator(List<Region> list, String str) {
        for (Region region : list) {
            for (ServiceProvider provider : region.getServiceProviders()) {
                String number = str.substring(region.getCode().length());
                if (number.matches(provider.getCode())) {
                    currentProvider = provider;
                    if (onChangeCurrentServiceProviderListener != null) {
                        onChangeCurrentServiceProviderListener.onChange(currentProvider);
                    }
                    return region;
                }
            }
        }
        return null;
    }

    private void findRegion(Region region, String str) {
        if (currentProvider != null) {
            if (str.matches(currentProvider.getCode())) {
                return;
            } else {
                currentProvider = null;
                if (onChangeCurrentServiceProviderListener != null) {
                    onChangeCurrentServiceProviderListener.onChange(null);
                }
            }
        }

        for (ServiceProvider item : region.getServiceProviders()) {
            if (str.matches(item.getCode())) {
                currentProvider = item;
                if (onChangeCurrentServiceProviderListener != null) {
                    onChangeCurrentServiceProviderListener.onChange(item);
                }
                return;
            }
        }
    }

    private String clearText(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        return text.replaceAll("[^0-9]", "");
    }

    public interface OnChangeCurrentRegionListener {
        void onChange(Region region);
    }

    public interface OnChangeCurrentServiceProviderListener {
        void onChange(ServiceProvider provider);
    }

    private class CustomTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String clearText = clearText(s.toString());
            findRegion(clearText);
            formatText(clearText);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}
