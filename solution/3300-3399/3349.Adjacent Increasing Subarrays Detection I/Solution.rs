impl Solution {
    pub fn has_increasing_subarrays(nums: Vec<i32>, k: i32) -> bool {
        let n = nums.len();
        let (mut mx, mut pre, mut cur) = (0, 0, 0);

        for i in 0..n {
            cur += 1;
            if i == n - 1 || nums[i] >= nums[i + 1] {
                mx = mx.max(cur / 2).max(pre.min(cur));
                pre = cur;
                cur = 0;
            }
        }

        mx >= k
    }
}
