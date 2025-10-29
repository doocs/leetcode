impl Solution {
    pub fn min_flips(target: String) -> i32 {
        let mut ans = 0;
        for c in target.chars() {
            let bit = (c as u8 - b'0') as i32;
            if ans % 2 != bit {
                ans += 1;
            }
        }
        ans
    }
}
