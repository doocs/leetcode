impl Solution {
    pub fn min_speed_on_time(dist: Vec<i32>, hour: f64) -> i32 {
        let n = dist.len();

        let check = |speed| {
            let mut cur = 0.;
            for (i, &d) in dist.iter().enumerate() {
                if i == n - 1 {
                    cur += d as f64 / speed as f64;
                } else {
                    cur += (d as f64 / speed as f64).ceil();
                }
            }
            cur <= hour
        };

        let mut left = 1;
        let mut right = 1e7 as i32;
        while left < right {
            let mid = left + (right - left) / 2;
            if !check(mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if check(left) {
            return left;
        }
        -1
    }
}
