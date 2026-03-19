impl Solution {
    pub fn has_alternating_bits(mut n: i32) -> bool {
        let mut prev: i32 = -1;

        while n != 0 {
            let curr = n & 1;
            if prev == curr {
                return false;
            }
            prev = curr;
            n >>= 1;
        }

        true
    }
}
