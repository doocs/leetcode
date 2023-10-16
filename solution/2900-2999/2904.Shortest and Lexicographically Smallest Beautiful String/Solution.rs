impl Solution {
    pub fn shortest_beautiful_substring(s: String, k: i32) -> String {
        let s_chars: Vec<char> = s.chars().collect();
        let mut i = 0;
        let mut j = 0;
        let mut cnt = 0;
        let mut ans = String::new();
        let n = s.len();

        while j < n {
            if s_chars[j] == '1' {
                cnt += 1;
            }

            while cnt > k || (i < j && s_chars[i] == '0') {
                if s_chars[i] == '1' {
                    cnt -= 1;
                }
                i += 1;
            }

            j += 1;

            if cnt == k && (ans.is_empty() || j - i < ans.len() || (j - i == ans.len() && &s[i..j] < &ans)) {
                ans = s_chars[i..j].iter().collect();
            }
        }

        ans
    }
}