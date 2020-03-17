class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] res = Arrays.copyOf(T, T.length);
        for (int i = 0, length = T.length; i < length; i++) {
            int temp = T[i], j = i + 1;
            while (j < length && temp >= res[j]) {
                j ++;
            }
            res[i] = j == length ? 0 : j - i;
        }
        return res;
    }
}