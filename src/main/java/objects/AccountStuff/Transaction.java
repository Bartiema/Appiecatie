package objects.AccountStuff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Transaction implements Comparable<Transaction> {
    enum Type {
        DEBUG,
        BIER_GEPAKT,
        MIS_BIER,
        KRAT_GEKOCHT,
        MIS_KRAT,
        BIER_VERLIES,
    }

    private Type type;
    private Date date;
    private int pre;
    private int post;

    public Transaction(int type, int pre, int post){
        this.type = Type.values()[type];
        this.date = new Date();
        this.pre = pre;
        this.post = post;
    }

    public Type getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public int getPre() {
        return pre;
    }

    public int getPost() {
        return post;
    }

    @Override
    public int compareTo(Transaction o) {
        return this.date.compareTo(o.getDate());
    }
}
