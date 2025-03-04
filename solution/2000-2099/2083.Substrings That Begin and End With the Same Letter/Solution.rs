impl Solution {
    pub fn number_of_substrings(s: String) -> i64 {
        let mut cnt = [0; 26];
        let mut ans = 0_i64;
        for c in s.chars() {
            let idx = (c as u8 - b'a') as usize;
            cnt[idx] += 1;
            ans += cnt[idx];
        }
        ans
    }
}
