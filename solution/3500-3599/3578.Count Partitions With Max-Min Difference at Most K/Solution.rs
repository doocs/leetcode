use std::collections::BTreeMap;

impl Solution {
    pub fn count_partitions(nums: Vec<i32>, k: i32) -> i32 {
        const mod_val: i32 = 1_000_000_007;
        let n = nums.len();
        let mut f = vec![0; n + 1];
        let mut g = vec![0; n + 1];
        f[0] = 1;
        g[0] = 1;
        let mut sl = BTreeMap::new();
        let mut l = 1;
        for r in 1..=n {
            let x = nums[r - 1];
            *sl.entry(x).or_insert(0) += 1;
            while sl.keys().last().unwrap() - sl.keys().next().unwrap() > k {
                let val = nums[l - 1];
                if let Some(cnt) = sl.get_mut(&val) {
                    *cnt -= 1;
                    if *cnt == 0 {
                        sl.remove(&val);
                    }
                }
                l += 1;
            }
            f[r] = (g[r - 1] - if l >= 2 { g[l - 2] } else { 0 } + mod_val) % mod_val;
            g[r] = (g[r - 1] + f[r]) % mod_val;
        }
        f[n]
    }
}
