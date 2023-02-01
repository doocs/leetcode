impl Solution {
    pub fn number_of_beams(bank: Vec<String>) -> i32 {
        let mut last = 0;
        let mut ans = 0;
        for r in bank.iter() {
            let mut t = 0;
            for &v in r.as_bytes() {
                if v == b'1' {
                    t += 1;
                }
            }
            if t != 0 {
                ans += last * t;
                last = t;
            }
        }
        ans
    }
}
