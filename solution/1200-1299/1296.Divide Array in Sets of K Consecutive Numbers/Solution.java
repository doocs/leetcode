class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        Arrays.sort(nums);
        for (int v : nums) {
            if (cnt.containsKey(v)) {
                for (int x = v; x < v + k; ++x) {
                    if (!cnt.containsKey(x)) {
                        return false;
                    }
                    cnt.put(x, cnt.get(x) - 1);
                    if (cnt.get(x) == 0) {
                        cnt.remove(x);
                    }
                }
            }
        }
        return true;
    }
}