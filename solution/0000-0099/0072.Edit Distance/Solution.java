class Solution {
    public int minDistance(String word1, String word2) {
        return edit(word1.length(),word2.length(),word1,word2,new int[word1.length()+1][word2.length()+1]);
    }
    private int edit(int l, int r, String w1, String w2, int[][] dp){
        if(l==0) return r;
        if(r==0) return l;
        if(dp[l][r]!=0) return dp[l][r];
        int min = (w1.charAt(l-1)!=w2.charAt(r-1)) ?
                Math.min(edit(l-1,r-1,w1,w2,dp)+1,Math.min(edit(l-1,r,w1,w2,dp)+1,edit(l,r-1,w1,w2,dp)+1))
                : edit(l - 1, r - 1, w1, w2, dp);
        dp[l][r] = min;
        return min;
    }
}