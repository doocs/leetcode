impl Solution {
    pub fn num_subseq(mut nums: Vec<i32>, target: i32) -> i32 {
        nums.sort();
        const MOD: i32 = 1_000_000_007;
        let n = nums.len();
        let mut f = vec![1; n + 1];
        for i in 1..=n {
            f[i] = (f[i - 1] * 2) % MOD;
        }
        let mut ans = 0;
        for i in 0..n {
            if nums[i] * 2 > target {
                break;
            }
            let mut l = i + 1;
            let mut r = n;
            while l < r {
                let m = (l + r) / 2;
                if nums[m] > target - nums[i] {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            let j = l - 1;
            ans = (ans + f[j - i]) % MOD;
        }
        ans
    }
}
