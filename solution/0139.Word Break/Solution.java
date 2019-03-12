class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int wordMaxLen = Integer.MIN_VALUE;
        for (String word : wordDict){
            wordMaxLen = Math.max(wordMaxLen, word.length());
        }
        Set<String> wordSet = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for(int i = 1; i <= len; i++){
            for(int l = 1; l <= wordMaxLen && i - l >= 0; l++){
                if(dp[i-l] && wordSet.contains(s.substring(i-l, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}