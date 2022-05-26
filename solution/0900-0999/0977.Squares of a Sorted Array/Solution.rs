impl Solution {
    pub fn sorted_squares(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut l = 0;
        let mut r = n - 1;
        let mut res = vec![0; n];
        for i in (0..n).rev() {
            let a = nums[l] * nums[l];
            let b = nums[r] * nums[r];
            if a < b {
                res[i] = b;
                r -= 1;
            } else {
                res[i] = a;
                l += 1;
            }
        }
        res
    }
}
