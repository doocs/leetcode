impl Solution {
    pub fn max_score(card_points: Vec<i32>, k: i32) -> i32 {
        let n = card_points.len();
        let k = k as usize;
        let mut s: i32 = card_points[n - k..].iter().sum();
        let mut ans: i32 = s;
        for i in 0..k {
            s += card_points[i] - card_points[n - k + i];
            ans = ans.max(s);
        }
        ans
    }
}
