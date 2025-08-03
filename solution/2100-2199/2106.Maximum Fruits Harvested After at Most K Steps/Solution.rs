impl Solution {
    pub fn max_total_fruits(fruits: Vec<Vec<i32>>, start_pos: i32, k: i32) -> i32 {
        let mut ans = 0;
        let mut s = 0;
        let mut i = 0;
        for j in 0..fruits.len() {
            let pj = fruits[j][0];
            let fj = fruits[j][1];
            s += fj;
            while i <= j
                && pj - fruits[i][0]
                    + std::cmp::min((start_pos - fruits[i][0]).abs(), (start_pos - pj).abs())
                    > k
            {
                s -= fruits[i][1];
                i += 1;
            }
            ans = ans.max(s)
        }
        ans
    }
}
