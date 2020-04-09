class Solution {
    public boolean isNumber(String s) {
        if(null==s || 0==s.length()) return false;
        int start=0,end=s.length()-1;
        char[] c=s.toCharArray();
        while(start<=end && ' '==c[start]) start++;
        while(end>=start && ' '==c[end] ) end--;
        if(start>end) return false;
        if('+'==c[start] || '-'==c[start]) start++;
        boolean hasNum=false;
        boolean hasDot=false;
        boolean hasE=false;
        while(start<=end){
            if(c[start]>='0' && c[start]<='9') hasNum = true;
            else if(c[start]=='e'){
                if(hasE || !hasNum) return false;
                hasE=true;
                hasNum=false;
            }
            else if(c[start]=='.'){
                if(hasDot || hasE) return false;
                hasDot=true;
            }
            else if('+'==c[start] || '-'==c[start]){
                if(c[start-1]!='e') return false;
            }else return false;
            start++;
        }
        return hasNum;
    }
}