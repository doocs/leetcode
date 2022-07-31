class Solution {
    public int minimumOperations(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            if (v > 0) {
                s.add(v);
            }
        }
        return s.size();
    }
}