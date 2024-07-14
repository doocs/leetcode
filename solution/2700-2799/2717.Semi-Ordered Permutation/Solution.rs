impl Solution {
    pub fn semi_ordered_permutation(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let (mut i, mut j) = (0, 0);

        for k in 0..n {
            if nums[k] == 1 {
                i = k;
            }
            if nums[k] == (n as i32) {
                j = k;
            }
        }

        let k = if i < j { 1 } else { 2 };
        (i + n - j - k) as i32
    }
}
