impl Solution {
    pub fn num_of_subarrays(arr: Vec<i32>) -> i32 {
        let mut cnt = [1, 0];
        let mut ans = 0;
        let mut s = 0;
        const MOD: i32 = 1_000_000_007;
        for &x in arr.iter() {
            s = (s + x) % 2;
            ans = (ans + cnt[(s ^ 1) as usize]) % MOD;
            cnt[s as usize] += 1;
        }
        ans
    }
}
