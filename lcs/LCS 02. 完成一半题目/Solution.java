class Solution {
    public int halfQuestions(int[] questions) {
        int[] cnt = new int[1010];
        for (int x : questions) {
            ++cnt[x];
        }
        Arrays.sort(cnt);
        int ans = 0;
        int n = questions.length >> 1;
        for (int i = cnt.length - 1; n > 0; --i) {
            ++ans;
            n -= cnt[i];
        }
        return ans;
    }
}