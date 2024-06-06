impl Solution {
    pub fn find_substring_in_wrapround_string(s: String) -> i32 {
        let idx = |c: u8| -> usize { (c - b'a') as usize };
        let mut f = vec![0; 26];
        let n = s.len();
        let s = s.as_bytes();
        let mut k = 0;
        for i in 0..n {
            let j = idx(s[i]);
            if i > 0 && ((j as i32) - (idx(s[i - 1]) as i32) + 26) % 26 == 1 {
                k += 1;
            } else {
                k = 1;
            }
            f[j] = f[j].max(k);
        }

        f.iter().sum()
    }
}
