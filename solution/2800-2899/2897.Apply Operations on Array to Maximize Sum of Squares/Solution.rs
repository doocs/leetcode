impl Solution {
    pub fn max_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mut cnt = [0; 31];
        for &x in &nums {
            for (i, c) in cnt.iter_mut().enumerate() {
                *c += (x >> i) & 1;
            }
        }
        let mut ans = 0_i64;
        for _ in 0..k {
            let mut x = 0_i64;
            for (i, c) in cnt.iter_mut().enumerate() {
                if *c > 0 {
                    x |= 1_i64 << i;
                    *c -= 1;
                }
            }
            ans = (ans + x * x) % 1_000_000_007;
        }
        ans as i32
    }
}
