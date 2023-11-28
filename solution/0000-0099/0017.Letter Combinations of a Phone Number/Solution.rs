impl Solution {
    pub fn letter_combinations(digits: String) -> Vec<String> {
        let mut ans: Vec<String> = Vec::new();
        if digits.is_empty() {
            return ans;
        }
        ans.push("".to_string());
        let d = ["abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"];
        for i in digits.chars() {
            let s = &d[((i as u8) - b'2') as usize];
            let mut t: Vec<String> = Vec::new();
            for a in &ans {
                for b in s.chars() {
                    t.push(format!("{}{}", a, b));
                }
            }
            ans = t;
        }
        ans
    }
}
