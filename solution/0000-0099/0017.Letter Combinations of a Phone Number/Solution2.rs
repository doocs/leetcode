impl Solution {
    pub fn letter_combinations(digits: String) -> Vec<String> {
        let d = ["abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"];
        let mut ans = Vec::new();
        let mut t = String::new();
        if digits.is_empty() {
            return ans;
        }
        Solution::dfs(&digits, &d, &mut t, &mut ans, 0);
        ans
    }

    fn dfs(digits: &String, d: &[&str; 8], t: &mut String, ans: &mut Vec<String>, i: usize) {
        if i >= digits.len() {
            ans.push(t.clone());
            return;
        }
        let s = d[((digits.chars().nth(i).unwrap() as u8) - b'2') as usize];
        for c in s.chars() {
            t.push(c);
            Solution::dfs(digits, d, t, ans, i + 1);
            t.pop();
        }
    }
}
