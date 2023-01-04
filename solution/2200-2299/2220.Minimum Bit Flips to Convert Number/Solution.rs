impl Solution {
    pub fn min_bit_flips(start: i32, goal: i32) -> i32 {
        let mut tmp = start ^ goal;
        let mut ans = 0;
        while tmp != 0 {
            ans += tmp & 1;
            tmp >>= 1;
        }
        ans
    }
}
