impl Solution {
    pub fn separate_squares(squares: Vec<Vec<i32>>) -> f64 {
        let mut s: f64 = 0.0;

        let mut l: f64 = 0.0;
        let mut r: f64 = 0.0;

        for a in squares.iter() {
            let len = a[2] as f64;
            s += len * len;
            r = r.max((a[1] + a[2]) as f64);
        }

        let check = |y1: f64| -> bool {
            let mut t: f64 = 0.0;
            for a in squares.iter() {
                let y = a[1] as f64;
                let l = a[2] as f64;
                if y < y1 {
                    let h = l.min(y1 - y);
                    t += l * h;
                }
            }
            t >= s / 2.0
        };

        const EPS: f64 = 1e-5;
        while r - l > EPS {
            let mid = (l + r) / 2.0;
            if check(mid) {
                r = mid;
            } else {
                l = mid;
            }
        }
        r
    }
}
