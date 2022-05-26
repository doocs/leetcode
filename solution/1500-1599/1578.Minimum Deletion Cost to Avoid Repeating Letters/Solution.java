class Solution {
    public int minCost(String s, int[] cost) {
        int res = 0;
        char[] word = s.toCharArray();
        for(int i = 0;i < word.length;i++){
            char c = word[i];
            int max = cost[i];
            int sum = max;
            while(i + 1 < word.length && word[i + 1] == c){
                sum += cost[++i];
                max = Math.max(max,cost[i]);
            }
            if(sum != max){
                res += sum - max;
            }
        }
        return res;
    }
}