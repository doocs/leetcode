impl Solution {
    pub fn sort_threats(mut threats: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        threats.sort_by(|a, b| {
            let score1 = 2i64 * a[1] as i64 + a[2] as i64;
            let score2 = 2i64 * b[1] as i64 + b[2] as i64;
            if score1 == score2 {
                a[0].cmp(&b[0])
            } else {
                score2.cmp(&score1)
            }
        });
        threats
    }
}
