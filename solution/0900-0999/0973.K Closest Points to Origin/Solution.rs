impl Solution {
    pub fn k_closest(mut points: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        points.sort_by(|a, b| {
            let dist_a = f64::hypot(a[0] as f64, a[1] as f64);
            let dist_b = f64::hypot(b[0] as f64, b[1] as f64);
            dist_a.partial_cmp(&dist_b).unwrap()
        });
        points.into_iter().take(k as usize).collect()
    }
}
