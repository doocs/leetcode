class Solution {
    public int countBinarySubstrings(String s) {
		     int[] group = new int[s.length()];
		     int k = 0;
		     Arrays.fill(group , 0);
		     group[0] = 1;
		     for(int i = 1;i < s.length();i++) {
		    	   if(s.charAt(i) == s.charAt(i-1))
		    		   group[k]++;
		    	   else
		    		   group[++k] = 1;
		     }
		     int ans = 0;
		     for(int i = 1;i < s.length() && group[i] != 0;i++) {
		    	   ans += Math.min(group[i-1], group[i]);
		     }
		     return ans;
    }
}
