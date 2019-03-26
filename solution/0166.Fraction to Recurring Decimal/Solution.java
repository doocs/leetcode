class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        boolean minus = numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0;
        HashMap<Long, Integer> remains = new HashMap<>(16);
        List<Long> resList = new ArrayList<>();
        long n;
        if(numerator > 0) n = numerator;
        else if(numerator > Integer.MIN_VALUE) n = -numerator;
        else n = Integer.MAX_VALUE + 1L;
        long d;
        if(denominator > 0) d = denominator;
        else if(denominator > Integer.MIN_VALUE) d = -denominator;
        else d = Integer.MAX_VALUE + 1L;
        long r = n % d;
        int index = 0 , loopPos = -1;
        while(r != 0){
            if(remains.containsKey(r)){
                loopPos = remains.get(r);
                break;
            }
            remains.put(r, ++index);
            resList.add(Math.abs(n / d));
            n = r;
            n *= 10;
            r = n % d;
        }
        resList.add(Math.abs(n / d));
        StringBuilder res = new StringBuilder();
        if(minus) res.append("-");
        for(int i = 0; i < resList.size(); i++){
            if(i == 1) res.append(".");
            if(loopPos == i) res.append("(");
            res.append(resList.get(i));
        }
        if(loopPos != -1) res.append(")");
        return res.toString();
    }
}