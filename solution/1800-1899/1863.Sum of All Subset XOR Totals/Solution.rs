impl Solution {
    pub fn subset_xor_sum(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;

        for i in 0..(1 << n) {
            let mut s = 0;
            for j in 0..n {
                if ((i >> j) & 1) == 1 {
                    s ^= nums[j];
                }
            }
            ans += s;
        }

        ans
    }
}
