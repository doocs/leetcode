impl Solution {
    pub fn min_length(s: String) -> i32 {
        let mut ans: Vec<u8> = Vec::new();

        for c in s.bytes() {
            if let Some(last) = ans.last() {
                if *last == b'A' && c == b'B' {
                    ans.pop();
                } else if *last == b'C' && c == b'D' {
                    ans.pop();
                } else {
                    ans.push(c);
                }
            } else {
                ans.push(c);
            }
        }

        ans.len() as i32
    }
}