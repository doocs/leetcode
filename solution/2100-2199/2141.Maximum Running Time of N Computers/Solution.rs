impl Solution {
    pub fn max_run_time(n: i32, batteries: Vec<i32>) -> i64 {
        let n = n as i64;
        let mut l: i64 = 0;
        let mut r: i64 = batteries.iter().map(|&x| x as i64).sum();

        while l < r {
            let mid = (l + r + 1) >> 1;
            let mut s: i64 = 0;

            for &x in &batteries {
                let v = x as i64;
                s += if v < mid { v } else { mid };
            }

            if s >= n * mid {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        l
    }
}
