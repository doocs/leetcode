impl Solution {
    pub fn str_str(haystack: String, needle: String) -> i32 {
        let n = haystack.len();
        let m = needle.len();
        if m > n {
            return -1;
        }
        for i in 0..=n - m {
            if &haystack[i..i + m] == needle {
                return i as i32;
            }
        }
        -1
    }
}
