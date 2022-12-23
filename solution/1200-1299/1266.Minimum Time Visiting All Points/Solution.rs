impl Solution {
    pub fn min_time_to_visit_all_points(points: Vec<Vec<i32>>) -> i32 {
        let n = points.len();
        let mut ans = 0;
        for i in 1..n {
            let x = (points[i - 1][0] - points[i][0]).abs();
            let y = (points[i - 1][1] - points[i][1]).abs();
            ans += x.max(y);
        }
        ans
    }
}
