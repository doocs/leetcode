class Solution {
    public int halfQuestions(int[] questions) {
        int[] counter = new int[1010];
        for (int e : questions) {
            ++counter[e];
        }
        int n = questions.length >> 1;
        Arrays.sort(counter);
        int res = 0;
        for (int i = counter.length - 1; i >= 0; --i) {
            ++res;
            if (counter[i] >= n) {
                return res;
            }
            n -= counter[i];
        }
        return res;
    }
}