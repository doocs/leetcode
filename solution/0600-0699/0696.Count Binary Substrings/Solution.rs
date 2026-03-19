impl Solution {
    pub fn count_binary_substrings(s: String) -> i32 {
        let bytes = s.as_bytes();
        let n: usize = bytes.len();

        let mut ans: i32 = 0;
        let mut i: usize = 0;
        let mut pre: i32 = 0;

        while i < n {
            let mut j: usize = i + 1;
            while j < n && bytes[j] == bytes[i] {
                j += 1;
            }
            let cur: i32 = (j - i) as i32;
            ans += pre.min(cur);
            pre = cur;
            i = j;
        }

        ans
    }
}
