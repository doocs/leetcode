impl Solution {
    pub fn largest_triangle_area(points: Vec<Vec<i32>>) -> f64 {
        let mut ans: f64 = 0.0;
        for point1 in &points {
            let (x1, y1) = (point1[0], point1[1]);
            for point2 in &points {
                let (x2, y2) = (point2[0], point2[1]);
                for point3 in &points {
                    let (x3, y3) = (point3[0], point3[1]);
                    let u1 = x2 - x1;
                    let v1 = y2 - y1;
                    let u2 = x3 - x1;
                    let v2 = y3 - y1;
                    let t = ((u1 * v2 - u2 * v1) as f64).abs() / 2.0;
                    ans = ans.max(t);
                }
            }
        }
        ans
    }
}
