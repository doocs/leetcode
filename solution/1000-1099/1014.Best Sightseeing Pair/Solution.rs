impl Solution {
    pub fn max_score_sightseeing_pair(values: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        for (j, &x) in values.iter().enumerate() {
            ans = ans.max(mx + x - j as i32);
            mx = mx.max(x + j as i32);
        }
        ans
    }
}
