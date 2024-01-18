impl Solution {
    pub fn find_the_difference(s: String, t: String) -> char {
        let s = s.as_bytes();
        let t = t.as_bytes();
        let n = s.len();
        let mut count = [0; 26];
        for i in 0..n {
            count[(s[i] - b'a') as usize] += 1;
            count[(t[i] - b'a') as usize] -= 1;
        }
        count[(t[n] - b'a') as usize] -= 1;
        char::from(
            b'a' +
                (
                    count
                        .iter()
                        .position(|&v| v != 0)
                        .unwrap() as u8
                )
        )
    }
}
