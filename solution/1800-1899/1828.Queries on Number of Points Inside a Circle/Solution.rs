impl Solution {
    pub fn count_points(points: Vec<Vec<i32>>, queries: Vec<Vec<i32>>) -> Vec<i32> {
        queries
            .iter()
            .map(|v| {
                let cx = v[0];
                let cy = v[1];
                let r = v[2].pow(2);
                let mut count = 0;
                for p in points.iter() {
                    if ((p[0] - cx).pow(2) + (p[1] - cy).pow(2)) <= r {
                        count += 1;
                    }
                }
                count
            })
            .collect()
    }
}
