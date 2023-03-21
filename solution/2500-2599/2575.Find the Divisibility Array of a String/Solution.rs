impl Solution {
    pub fn divisibility_array(word: String, m: i32) -> Vec<i32> {
        let m = m as i64;
        let mut x = 0i64;
        word.as_bytes()
            .iter()
            .map(|&c| {
                x = (x * 10 + i64::from(c - b'0')) % m;
                if x == 0 {
                    1
                } else {
                    0
                }
            })
            .collect()
    }
}
