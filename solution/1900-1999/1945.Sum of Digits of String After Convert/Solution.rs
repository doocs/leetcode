impl Solution {
    pub fn get_lucky(s: String, k: i32) -> i32 {
        let mut ans = String::new();
        for c in s.as_bytes() {
            ans.push_str(&(c - b'a' + 1).to_string());
        }
        for _ in 0..k {
            let mut t = 0;
            for c in ans.as_bytes() {
                t += (c - b'0') as i32;
            }
            ans = t.to_string();
        }
        ans.parse().unwrap()
    }
}
