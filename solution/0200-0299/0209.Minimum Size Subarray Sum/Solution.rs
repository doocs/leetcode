impl Solution {
    pub fn min_sub_array_len(target: i32, nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = n + 1;
        let mut sum = 0;
        let mut i = 0;
        for j in 0..n {
            sum += nums[j];

            while sum >= target {
                res = res.min(j - i + 1);
                sum -= nums[i];
                i += 1;
            }
        }
        if res == n + 1 {
            return 0;
        }
        res as i32
    }
}
