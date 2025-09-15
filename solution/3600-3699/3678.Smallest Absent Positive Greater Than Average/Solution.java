class Solution {
    public int smallestAbsent(int[] nums) {
        Set<Integer> s = new HashSet<>();
        int sum = 0;
        for (int x : nums) {
            s.add(x);
            sum += x;
        }
        int ans = Math.max(1, sum / nums.length + 1);
        while (s.contains(ans)) {
            ++ans;
        }
        return ans;
    }
}
