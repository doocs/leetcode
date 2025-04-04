impl Solution {
    pub fn most_points(questions: Vec<Vec<i32>>) -> i64 {
        let n = questions.len();
        let mut f = vec![0; n + 1];
        for i in (0..n).rev() {
            let p = questions[i][0] as i64;
            let b = questions[i][1] as usize;
            let j = i + b + 1;
            f[i] = f[i + 1].max(p + if j > n { 0 } else { f[j] });
        }
        f[0]
    }
}
