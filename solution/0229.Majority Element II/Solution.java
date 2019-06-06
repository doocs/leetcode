class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int[] candidate = new int[2];
        int[] cnt = new int[2];
        for (int num : nums) {
            if (num == candidate[0]) {
                ++cnt[0];
            } else if (num == candidate[1]) {
                ++cnt[1];
            } else if (cnt[0] == 0) {
                candidate[0] = num;
                cnt[0] = 1;
            } else if (cnt[1] == 0) {
                candidate[1] = num;
                cnt[1] = 1;
            } else {
                --cnt[0];
                --cnt[1];
            }
        }
        Arrays.fill(cnt, 0);
        for (int num : nums) {
            if (num == candidate[0]) {
                ++cnt[0];
            } else if (num == candidate[1]) {
                ++cnt[1];
            }
        }
        List<Integer> res = new ArrayList<>();
        if (cnt[0] > nums.length / 3) {
            res.add(candidate[0]);
        }
        if (cnt[1] > nums.length / 3) {
            res.add(candidate[1]);
        }
        return res;
    }
}
