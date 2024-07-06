impl Solution {
    pub fn min_speed_on_time(dist: Vec<i32>, hour: f64) -> i32 {
        if dist.len() as f64 > hour.ceil() {
            return -1;
        }
        const M: i32 = 10_000_000;
        let (mut l, mut r) = (1, M + 1);
        let n = dist.len();
        let check = |v: i32| -> bool {
            let mut s = 0.0;
            for i in 0..n {
                let t = dist[i] as f64 / v as f64;
                s += if i == n - 1 { t } else { t.ceil() };
            }
            s <= hour
        };
        while l < r {
            let mid = (l + r) / 2;
            if check(mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if l > M {
            -1
        } else {
            l
        }
    }
}
