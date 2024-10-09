impl Solution {
    pub fn partition_string(s: String) -> i32 {
        let mut ans = 1;
        let mut mask = 0;
        for x in s.chars().map(|c| (c as u8 - b'a') as u32) {
            if mask >> x & 1 == 1 {
                ans += 1;
                mask = 0;
            }
            mask |= 1 << x;
        }
        ans
    }
}
