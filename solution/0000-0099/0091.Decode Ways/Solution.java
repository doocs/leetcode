class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        if (len == 0) return 0;
        int current = s.charAt(0) == '0' ? 0 : 1;
        int last = 1;
        for (int i = 1; i < len; i++) {
            int tmp = current;
            if(s.charAt(i) == '0'){
                if(s.charAt(i-1) == '1' || s.charAt(i-1) == '2') current = last;
                else return 0;
            }else if(s.charAt(i-1) == '1' || s.charAt(i-1) == '2' && s.charAt(i) <= '6') {
                current += last;
            }
            last = tmp;
        }
        return current;
    }
}