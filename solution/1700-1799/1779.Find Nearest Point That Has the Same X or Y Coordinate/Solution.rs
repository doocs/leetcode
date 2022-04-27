impl Solution {
    pub fn nearest_valid_point(x: i32, y: i32, points: Vec<Vec<i32>>) -> i32 {
        let n = points.len();
        let mut min_dif = i32::MAX;
        let mut res = -1;
        for i in 0..n {
            let (p_x, p_y) = (points[i][0], points[i][1]);
            if p_x != x && p_y != y {
                continue;
            }
            let dif = (p_x - x).abs() + (p_y - y).abs();
            if dif < min_dif {
                min_dif = dif;
                res = i as i32;
            }
        }
        res
    }
}
