impl Solution {
    pub fn find_unsorted_subarray(nums: Vec<i32>) -> i32 {
        let inf = 1 << 30;
        let n = nums.len();
        let mut l = -1;
        let mut r = -1;
        let mut mi = inf;
        let mut mx = -inf;

        for i in 0..n {
            if mx > nums[i] {
                r = i as i32;
            } else {
                mx = nums[i];
            }

            if mi < nums[n - i - 1] {
                l = (n - i - 1) as i32;
            } else {
                mi = nums[n - i - 1];
            }
        }

        if r == -1 {
            0
        } else {
            r - l + 1
        }
    }
}