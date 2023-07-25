impl Solution {
    #[allow(dead_code)]
    pub fn can_jump(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut i: usize = 0;

        while i < n {
            if nums[i] as usize + i >= n - 1 {
                break;
            }
            let mut j: usize = 1;
            let mut max_step = 0;
            let mut next_i: usize = 0;
            // Get the next max step
            while j <= nums[i] as usize {
                if (i + j) as i32 + nums[i + j] >= max_step {
                    max_step = (i + j) as i32 + nums[i + j];
                    next_i = i + j;
                }
                j += 1;
            }
            if max_step == 0 {
                // No further max step
                return false;
            }
            // Otherwise, update `i` to `next_i`
            i = next_i;
        }

        true
    }
}