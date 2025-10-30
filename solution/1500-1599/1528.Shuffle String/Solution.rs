impl Solution {
    pub fn restore_string(s: String, indices: Vec<i32>) -> String {
        let n = s.len();
        let mut ans = vec![' '; n];
        let chars: Vec<char> = s.chars().collect();
        for i in 0..n {
            ans[indices[i] as usize] = chars[i];
        }
        ans.iter().collect()
    }
}
