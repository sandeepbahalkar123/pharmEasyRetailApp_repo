package com.scorg.farmaeasy.model.responseModel.daybook;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

    @SerializedName("dayBookList")
    @Expose
    private ArrayList<DayBookList> dayBookList = new ArrayList<>();
    @SerializedName("openingBal")
    @Expose
    private OpeningBal openingBal;
    @SerializedName("closingBal")
    @Expose
    private ClosingBal closingBal;
    @SerializedName("total")
    @Expose
    private Total total;
    public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
            ;

    protected Data(Parcel in) {
        in.readList(this.dayBookList, (com.scorg.farmaeasy.model.responseModel.daybook.DayBookList.class.getClassLoader()));
        this.openingBal = ((OpeningBal) in.readValue((OpeningBal.class.getClassLoader())));
        this.closingBal = ((ClosingBal) in.readValue((ClosingBal.class.getClassLoader())));
        this.total = ((Total) in.readValue((Total.class.getClassLoader())));
    }

    public Data() {
    }

    public ArrayList<DayBookList> getDayBookList() {
        return dayBookList;
    }

    public void setDayBookList(ArrayList<DayBookList> dayBookList) {
        this.dayBookList = dayBookList;
    }

    public OpeningBal getOpeningBal() {
        return openingBal;
    }

    public void setOpeningBal(OpeningBal openingBal) {
        this.openingBal = openingBal;
    }

    public ClosingBal getClosingBal() {
        return closingBal;
    }

    public void setClosingBal(ClosingBal closingBal) {
        this.closingBal = closingBal;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(dayBookList);
        dest.writeValue(openingBal);
        dest.writeValue(closingBal);
        dest.writeValue(total);
    }

    public int describeContents() {
        return 0;
    }

}