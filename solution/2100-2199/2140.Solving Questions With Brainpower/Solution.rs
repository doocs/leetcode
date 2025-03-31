impl Solution {
    pub fn most_points(questions: Vec<Vec<i32>>) -> i64 {
        let n = questions.len();
        let mut f = vec![-1; n];

        fn dfs(i: usize, questions: &Vec<Vec<i32>>, f: &mut Vec<i64>) -> i64 {
            if i >= questions.len() {
                return 0;
            }
            if f[i] != -1 {
                return f[i];
            }
            let p = questions[i][0] as i64;
            let b = questions[i][1] as usize;
            f[i] = (p + dfs(i + b + 1, questions, f)).max(dfs(i + 1, questions, f));
            f[i]
        }

        dfs(0, &questions, &mut f)
    }
}
