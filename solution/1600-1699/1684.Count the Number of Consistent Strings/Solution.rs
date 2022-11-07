impl Solution {
    fn helper(s: &String) -> i32 {
        let mut res = 0;
        for c in s.as_bytes().iter() {
            res |= 1 << (c - b'a') as i32;
        }
        res
    }

    pub fn count_consistent_strings(allowed: String, words: Vec<String>) -> i32 {
        let mask = Self::helper(&allowed);
        let mut ans = 0;
        for word in words.iter() {
            if (mask | Self::helper(word)) == mask {
                ans += 1;
            }
        }
        ans
    }
}
