impl Solution {
    pub fn count_subarrays(nums: Vec<i32>, min_k: i32, max_k: i32) -> i64 {
        let mut ans: i64 = 0;
        let mut j1: i64 = -1;
        let mut j2: i64 = -1;
        let mut k: i64 = -1;
        for (i, &v) in nums.iter().enumerate() {
            let i = i as i64;
            if v < min_k || v > max_k {
                k = i;
            }
            if v == min_k {
                j1 = i;
            }
            if v == max_k {
                j2 = i;
            }
            let m = j1.min(j2);
            if m > k {
                ans += m - k;
            }
        }
        ans
    }
}
