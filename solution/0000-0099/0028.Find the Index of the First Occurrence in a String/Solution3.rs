impl Solution {
    pub fn str_str(haystack: String, needle: String) -> i32 {
        let haystack = haystack.as_bytes();
        let needle = needle.as_bytes();
        let n = haystack.len();
        let m = needle.len();
        let mut nxt = vec![0; m];
        let mut j = 0;
        for i in 1..m {
            while j > 0 && needle[i] != needle[j] {
                j = nxt[j - 1];
            }
            if needle[i] == needle[j] {
                j += 1;
            }
            nxt[i] = j;
        }
        j = 0;
        for i in 0..n {
            while j > 0 && haystack[i] != needle[j] {
                j = nxt[j - 1];
            }
            if haystack[i] == needle[j] {
                j += 1;
            }
            if j == m {
                return (i - m + 1) as i32;
            }
        }
        -1
    }
}
