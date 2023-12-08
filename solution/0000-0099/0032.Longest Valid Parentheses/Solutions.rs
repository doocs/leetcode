impl Solution {
    pub fn longest_valid_parentheses(s: String) -> i32 {
        let mut ans = 0;
        let mut f = vec![0; s.len() + 1];
        for i in 2..=s.len() {
            if s.chars().nth(i - 1).unwrap() == ')' {
                if s.chars().nth(i - 2).unwrap() == '(' {
                    f[i] = f[i - 2] + 2;
                } else if i as i32 - f[i - 1] - 1 > 0
                    && s.chars().nth(i - f[i - 1] as usize - 2).unwrap() == '('
                {
                    f[i] = f[i - 1] + 2 + f[i - f[i - 1] as usize - 2];
                }
                ans = ans.max(f[i])
            }
        }
        ans
    }
}

