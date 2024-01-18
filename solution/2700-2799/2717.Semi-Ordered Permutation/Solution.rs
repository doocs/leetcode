impl Solution {
    pub fn semi_ordered_permutation(nums: Vec<i32>) -> i32 {
        let mut i = 0;
        let mut j = 0;
        let mut n = nums.len();

        for idx in 0..n {
            if nums[idx] == 1 {
                i = idx;
            }
            if nums[idx] == (n as i32) {
                j = idx;
            }
        }

        let mut ans = i - 1 + n - j;
        if i > j {
            ans = i - 1 + n - j - 1;
        }

        ans as i32
    }
}
