impl Solution {
    pub fn find_the_difference(s: String, t: String) -> char {
        let s = s.as_bytes();
        let t = t.as_bytes();
        let n = s.len();
        let mut count = [0; 26];
        for i in 0..n {
            count[(s[i] - b'a') as usize] -= 1;
            count[(t[i] - b'a') as usize] += 1;
        }
        let mut res = *t.last().unwrap();
        for i in 0..26 {
            if count[i] == 1 {
                res = (i as u8) + b'a';
                break;
            }
        }
        char::from(res)
    }
}
