impl Solution {
    pub fn num_of_subarrays(arr: Vec<i32>) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let mut cnt = [1, 0];
        let mut ans = 0;
        let mut s = 0;
        for &x in arr.iter() {
            s += x;
            ans = (ans + cnt[((s & 1) ^ 1) as usize]) % MOD;
            cnt[(s & 1) as usize] += 1;
        }
        ans
    }
}
