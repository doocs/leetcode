impl Solution {
    pub fn min_operations(queries: Vec<Vec<i32>>) -> i64 {
        let f = |x: i64| -> i64 {
            let mut res: i64 = 0;
            let mut p: i64 = 1;
            let mut i: i64 = 1;
            while p <= x {
                let cnt = std::cmp::min(p * 4 - 1, x) - p + 1;
                res += cnt * i;
                i += 1;
                p *= 4;
            }
            res
        };

        let mut ans: i64 = 0;
        for q in queries {
            let l = q[0] as i64;
            let r = q[1] as i64;
            let s = f(r) - f(l - 1);
            let mx = f(r) - f(r - 1);
            ans += std::cmp::max((s + 1) / 2, mx);
        }
        ans
    }
}
