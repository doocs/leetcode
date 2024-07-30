impl Solution {
    pub fn min_rectangles_to_cover_points(mut points: Vec<Vec<i32>>, w: i32) -> i32 {
        points.sort_by(|a, b| a[0].cmp(&b[0]));
        let mut ans = 0;
        let mut x1 = -(1 << 30);
        for p in points {
            let x = p[0];
            if x1 + w < x {
                x1 = x;
                ans += 1;
            }
        }
        ans
    }
}
