class Solution {
    private List<Integer>[] pos = new List[2];
    private int[] nums;

    public int minSwaps(int[] nums) {
        this.nums = nums;
        Arrays.setAll(pos, k -> new ArrayList<>());
        for (int i = 0; i < nums.length; ++i) {
            pos[nums[i] & 1].add(i);
        }
        if (Math.abs(pos[0].size() - pos[1].size()) > 1) {
            return -1;
        }
        if (pos[0].size() > pos[1].size()) {
            return calc(0);
        }
        if (pos[0].size() < pos[1].size()) {
            return calc(1);
        }
        return Math.min(calc(0), calc(1));
    }

    private int calc(int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += Math.abs(pos[k].get(i / 2) - i);
        }
        return res;
    }
}