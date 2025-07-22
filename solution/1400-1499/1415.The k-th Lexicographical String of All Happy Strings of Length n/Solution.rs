impl Solution {
    pub fn get_happy_string(n: i32, k: i32) -> String {
        let mut ans = Vec::new();
        let mut s = String::new();
        let mut k = k;

        fn dfs(n: i32, s: &mut String, ans: &mut Vec<String>, k: &mut i32) {
            if s.len() == n as usize {
                ans.push(s.clone());
                return;
            }
            if ans.len() >= *k as usize {
                return;
            }
            for c in "abc".chars() {
                if s.is_empty() || s.chars().last() != Some(c) {
                    s.push(c);
                    dfs(n, s, ans, k);
                    s.pop();
                }
            }
        }

        dfs(n, &mut s, &mut ans, &mut k);
        if ans.len() < k as usize {
            "".to_string()
        } else {
            ans[(k - 1) as usize].clone()
        }
    }
}
