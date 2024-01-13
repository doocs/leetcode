impl Solution {
    pub fn generate_parenthesis(n: i32) -> Vec<String> {
        let mut dp: Vec<Vec<String>> = vec![vec![]; n as usize + 1];

        // Initialize the dp vector
        dp[0].push(String::from(""));
        dp[1].push(String::from("()"));

        // Begin the actual dp process
        for i in 2..=n as usize {
            for j in 0..i as usize {
                let dp_c = dp.clone();
                let first_half = &dp_c[j];
                let second_half = &dp_c[i - j - 1];

                for f in first_half {
                    for s in second_half {
                        let f_c = f.clone();
                        let cur_str = f_c + "(" + &*s + ")";
                        dp[i].push(cur_str);
                    }
                }
            }
        }

        dp[n as usize].clone()
    }
}
