impl Solution {
    pub fn is_monotonic(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut isOrder = false;
        let mut isDecs = false;
        for i in 1..n {
            let pre = nums[i - 1];
            let cur = nums[i];
            if pre < cur {
                isOrder = true;
            } else if pre > cur {
                isDecs = true;
            }
            if isOrder && isDecs {
                return false;
            }
        }
        true
    }
}
