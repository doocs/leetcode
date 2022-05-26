class Solution {
    public int findLucky(int[] arr) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int num : arr) {
            mp.put(num, mp.getOrDefault(num, 0) + 1);
        }
        int ans = -1;
        for (int num : arr) {
            if (num == mp.get(num) && ans < num) {
                ans = num;
            }
        }
        return ans;
    }
}