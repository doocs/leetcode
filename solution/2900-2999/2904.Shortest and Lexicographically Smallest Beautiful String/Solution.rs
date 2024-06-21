impl Solution {
    pub fn shortest_beautiful_substring(s: String, k: i32) -> String {
        let n = s.len();
        let mut ans = String::new();

        for i in 0..n {
            for j in i + (k as usize)..=n {
                let t = &s[i..j];
                if (t.matches('1').count() as i32) == k
                    && (ans.is_empty() || j - i < ans.len() || (j - i == ans.len() && t < &ans))
                {
                    ans = t.to_string();
                }
            }
        }
        ans
    }
}
