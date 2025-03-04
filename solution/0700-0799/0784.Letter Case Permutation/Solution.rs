impl Solution {
    pub fn letter_case_permutation(s: String) -> Vec<String> {
        fn dfs(i: usize, t: &mut Vec<char>, ans: &mut Vec<String>) {
            if i >= t.len() {
                ans.push(t.iter().collect());
                return;
            }
            dfs(i + 1, t, ans);
            if t[i].is_alphabetic() {
                t[i] = (t[i] as u8 ^ 32) as char;
                dfs(i + 1, t, ans);
            }
        }

        let mut t: Vec<char> = s.chars().collect();
        let mut ans = Vec::new();
        dfs(0, &mut t, &mut ans);
        ans
    }
}
