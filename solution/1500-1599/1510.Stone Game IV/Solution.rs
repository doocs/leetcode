impl Solution {
    pub fn winner_square_game(n: i32) -> bool {
        let mut f = vec![-1; (n + 1) as usize];

        fn dfs(i: i32, f: &mut Vec<i8>) -> bool {
            if i <= 0 {
                return false;
            }

            let idx = i as usize;
            if f[idx] != -1 {
                return f[idx] == 1;
            }

            let k = (i as f64).sqrt() as i32;
            for j in 1..=k {
                if !dfs(i - j * j, f) {
                    f[idx] = 1;
                    return true;
                }
            }

            f[idx] = 0;
            false
        }

        dfs(n, &mut f)
    }
}
