impl Solution {
    pub fn max_power(stations: Vec<i32>, r: i32, k: i32) -> i64 {
        let n = stations.len();
        let mut d = vec![0i64; n + 2];
        for i in 0..n {
            let left = i.saturating_sub(r as usize);
            let right = (i + r as usize).min(n - 1);
            d[left] += stations[i] as i64;
            d[right + 1] -= stations[i] as i64;
        }

        let mut s = vec![0i64; n + 1];
        s[0] = d[0];
        for i in 1..=n {
            s[i] = s[i - 1] + d[i];
        }

        let check = |x: i64, mut k: i64| -> bool {
            let mut d = vec![0i64; n + 2];
            let mut t = 0i64;
            for i in 0..n {
                t += d[i];
                let dist = x - (s[i] + t);
                if dist > 0 {
                    if k < dist {
                        return false;
                    }
                    k -= dist;
                    let j = (i + r as usize).min(n - 1);
                    let left = j.saturating_sub(r as usize);
                    let right = (j + r as usize).min(n - 1);
                    d[left] += dist;
                    d[right + 1] -= dist;
                    t += dist;
                }
            }
            true
        };

        let (mut left, mut right) = (0i64, 1_000_000_000_000i64);
        while left < right {
            let mid = (left + right + 1) >> 1;
            if check(mid, k as i64) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        left
    }
}
