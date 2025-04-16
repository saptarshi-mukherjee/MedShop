package com.MedShop.quick_com.Strategies.StringNormalization;

public class BasicNormalization implements StringNormalizationStrategy {
    @Override
    public String normalize(String s) {
        s=s.toLowerCase();
        int n=s.length(), i, start=-1, end=-1;
        StringBuilder result=new StringBuilder();
        for(i=0;i<n;i++) {
            if(s.charAt(i)>=97 && s.charAt(i)<=122) {
                start=i;
                break;
            }
        }
        for(i=n-1;i>=0;i--) {
            if(s.charAt(i)>=97 && s.charAt(i)<=122) {
                end=i;
                break;
            }
        }
        for(i=start;i<=end;i++) {
            if(s.charAt(i)==' ' && s.charAt(i-1)==' ')
                continue;
            result.append(s.charAt(i));
        }
        return result.toString();
    }
}
