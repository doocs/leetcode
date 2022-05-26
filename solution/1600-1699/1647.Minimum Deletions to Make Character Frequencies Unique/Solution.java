class Solution {
    public int minDeletions(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        Arrays.sort(counter);
        int ans = 0;
        for (int i = 24; i >= 0; --i) {
            while (counter[i] >= counter[i + 1] && counter[i] > 0) {
                --counter[i];
                ++ans;
            }
        }
        return ans;
    }
}