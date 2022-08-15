impl Solution {
    pub fn max_score(s: String) -> i32 {
        let n = s.len();
        let mut res = 0;
        let mut score = 0;
        let bs = s.as_bytes();
        if bs[0] == b'0' {
            score += 1;
        }
        for i in 1..n {
            if bs[i] == b'1' {
                score += 1;
            }
        }
        res = res.max(score);
        for i in 1..n - 1 {
            if bs[i] == b'0' {
                score += 1;
            } else if bs[i] == b'1' {
                score -= 1;
            }
            res = res.max(score);
        }
        res
    }
}
