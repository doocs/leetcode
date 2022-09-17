impl Solution {
    pub fn max_length_between_equal_characters(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut pos = [-1; 26];
        let mut res = -1;
        for i in 0..n {
            let j = (s[i] - b'a') as usize;
            let i = i as i32;
            if pos[j] == -1 {
                pos[j] = i;
            } else {
                res = res.max(i - pos[j] - 1);
            }
        }
        res
    }
}
