impl Solution {
    pub fn max_subarrays(n: i32, conflicting_pairs: Vec<Vec<i32>>) -> i64 {
        let mut g: Vec<Vec<i32>> = vec![vec![]; (n + 1) as usize];
        for pair in conflicting_pairs {
            let mut a = pair[0];
            let mut b = pair[1];
            if a > b {
                std::mem::swap(&mut a, &mut b);
            }
            g[a as usize].push(b);
        }

        let mut cnt: Vec<i64> = vec![0; (n + 2) as usize];
        let mut ans = 0i64;
        let mut add = 0i64;
        let mut b1 = n + 1;
        let mut b2 = n + 1;

        for a in (1..=n).rev() {
            for &b in &g[a as usize] {
                if b < b1 {
                    b2 = b1;
                    b1 = b;
                } else if b < b2 {
                    b2 = b;
                }
            }
            ans += (b1 - a) as i64;
            cnt[b1 as usize] += (b2 - b1) as i64;
            add = std::cmp::max(add, cnt[b1 as usize]);
        }

        ans += add;
        ans
    }
}
