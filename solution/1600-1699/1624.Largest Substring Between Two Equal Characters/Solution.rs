impl Solution {
    pub fn max_length_between_equal_characters(s: String) -> i32 {
        let s = s.as_bytes();
        let mut d = [-1; 26];
        let mut ans = -1;
        for i in 0..s.len() {
            let j = (s[i] - b'a') as usize;
            if d[j] == -1 {
                d[j] = i as i32;
            } else {
                ans = ans.max(i as i32 - d[j] - 1);
            }
        }
        ans
    }
}
