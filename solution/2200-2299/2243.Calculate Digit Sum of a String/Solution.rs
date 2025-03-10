impl Solution {
    pub fn digit_sum(s: String, k: i32) -> String {
        let mut s = s;
        let k = k as usize;
        while s.len() > k {
            let mut t = Vec::new();
            for chunk in s.as_bytes().chunks(k) {
                let sum: i32 = chunk.iter().map(|&c| (c - b'0') as i32).sum();
                t.push(sum.to_string());
            }
            s = t.join("");
        }
        s
    }
}
