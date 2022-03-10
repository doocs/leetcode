class Solution {
    public int halfQuestions(int[] questions) {
        int[] counter = new int[1010];
        for (int q : questions) {
            ++counter[q];
        }
        Arrays.sort(counter);
        int ans = 0;
        int n = questions.length >> 1;
        for (int i = counter.length - 1; n > 0; --i) {
            ++ans;
            n -= counter[i];
        }
        return ans;
    }
}