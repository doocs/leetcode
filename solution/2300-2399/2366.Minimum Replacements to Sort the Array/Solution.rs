impl Solution {
    #[allow(dead_code)]
    pub fn minimum_replacement(nums: Vec<i32>) -> i64 {
        if nums.len() == 1 {
            return 0;
        }

        let n = nums.len();
        let mut max = *nums.last().unwrap();
        let mut ret = 0;

        for i in (0..=n - 2).rev() {
            if nums[i] <= max {
                max = nums[i];
                continue;
            }
            // Otherwise make the substitution
            let k = (nums[i] + max - 1) / max;
            ret += (k - 1) as i64;
            // Update the max value, which should be the minimum among the substitution
            max = nums[i] / k;
        }

        ret
    }
}