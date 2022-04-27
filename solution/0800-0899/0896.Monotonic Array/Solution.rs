impl Solution {
    pub fn is_monotonic(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut is_order = false;
        let mut is_decs = false;
        for i in 1..n {
            let pre = nums[i - 1];
            let cur = nums[i];
            if pre < cur {
                is_order = true;
            } else if pre > cur {
                is_decs = true;
            }
            if is_order && is_decs {
                return false;
            }
        }
        true
    }
}
