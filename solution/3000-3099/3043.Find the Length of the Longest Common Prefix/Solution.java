class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> s = new HashSet<>();
        for (int x : arr1) {
            for (; x > 0; x /= 10) {
                s.add(x);
            }
        }
        int ans = 0;
        for (int x : arr2) {
            for (; x > 0; x /= 10) {
                if (s.contains(x)) {
                    ans = Math.max(ans, String.valueOf(x).length());
                    break;
                }
            }
        }
        return ans;
    }
}