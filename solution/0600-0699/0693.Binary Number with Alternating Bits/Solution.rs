impl Solution {
    pub fn has_alternating_bits(mut n: i32) -> bool {
        let u = n & 3;
        if u != 1 && u != 2 {
            return false;
        }
        while n != 0 {
            if (n & 3) != u {
                return false;
            }
            n >>= 2;
        }
        true
    }
}
