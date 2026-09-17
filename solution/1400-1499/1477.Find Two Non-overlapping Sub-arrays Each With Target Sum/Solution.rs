use std::collections::HashMap;

impl Solution {
    pub fn min_sum_of_lengths(arr: Vec<i32>, target: i32) -> i32 {
        let mut d = HashMap::new();
        d.insert(0, 0);
        let n = arr.len();
        let inf = 1 << 30;
        let mut f = vec![0; n + 1];
        f[0] = inf;
        let mut s = 0;
        let mut ans = inf;
        for i in 1..=n {
            s += arr[i - 1];
            f[i] = f[i - 1];
            if let Some(&j) = d.get(&(s - target)) {
                f[i] = f[i].min((i - j) as i32);
                ans = ans.min(f[j] + (i - j) as i32);
            }
            d.insert(s, i);
        }
        if ans > n as i32 {
            -1
        } else {
            ans
        }
    }
}
