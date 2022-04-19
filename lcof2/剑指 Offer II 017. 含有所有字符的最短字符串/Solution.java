class Solution {
    public String minWindow(String s, String t) {
        String res = "";
        int n = s.length(), m = t.length();
        if (n<m) return res;
        int hit=0;
        int left=0;
        int[] arrays = new int[26+26];
        //建立数组
        for (int i=0; i<m; i++)
            changeArray(t.charAt(i), arrays, false);

        for (int i=0; i<n; i++){
            changeArray(s.charAt(i), arrays, true);

            if (getArrOne(s.charAt(i), arrays)<=0) hit++;//命中

            while (hit==m && getArrOne(s.charAt(left), arrays)>0)
                changeArray(s.charAt(left++), arrays, false);

            if (hit==m)
                if (res.equals("")) res = s.substring(left, i+1);
                else res = res.length()<i-left+1? res:s.substring(left, i+1);

        }

        return res;
    }
    void changeArray(char one, int[] array, boolean flag){
        if (flag){
            if (one >= 'a') array[one-'a']++;
            else array[one-'A'+26]++;
        }else {
            if (one >= 'a') array[one-'a']--;
            else array[one-'A'+26]--;
        }
    }
    int getArrOne(char one, int[] array){
        if (one >= 'a') return array[one-'a'];
        else return array[one-'A'+26];
    }
}
