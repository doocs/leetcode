use std::collections::HashSet;
impl Solution {
    pub fn num_jewels_in_stones(jewels: String, stones: String) -> i32 {
        let mut s = jewels.as_bytes().iter().collect::<HashSet<&u8>>();
        let mut ans = 0;
        for c in stones.as_bytes() {
            if s.contains(c) {
                ans += 1;
            }
        }
        ans
    }
}
