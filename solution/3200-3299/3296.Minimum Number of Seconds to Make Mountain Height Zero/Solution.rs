impl Solution {
    pub fn min_number_of_seconds(mountain_height: i32, worker_times: Vec<i32>) -> i64 {
        let mut l: i64 = 1;
        let mut r: i64 = 10_i64.pow(16);

        let check = |t: i64| -> bool {
            let mut h: i64 = 0;
            for &wt in &worker_times {
                let wt = wt as f64;
                let t_f = t as f64;
                let val = ((t_f * 2.0 / wt + 0.25).sqrt() - 0.5).floor() as i64;
                h += val;
                if h >= mountain_height as i64 {
                    return true;
                }
            }
            h >= mountain_height as i64
        };

        while l < r {
            let mid = (l + r) >> 1;
            if check(mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        l
    }
}
