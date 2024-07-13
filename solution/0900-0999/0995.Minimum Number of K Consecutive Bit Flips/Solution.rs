impl Solution {
    pub fn min_k_bit_flips(nums: Vec<i32>, k: i32) -> i32 {
        let n = nums.len();
        let mut d = vec![0; n + 1];
        let mut ans = 0;
        let mut s = 0;
        for i in 0..n {
            s += d[i];
            if s % 2 == nums[i] {
                if i + (k as usize) > n {
                    return -1;
                }
                d[i] += 1;
                d[i + (k as usize)] -= 1;
                s += 1;
                ans += 1;
            }
        }
        ans
    }
}
