impl Solution {
    pub fn minimum_one_bit_operations(mut n: i32) -> i32 {
        let mut ans = 0;
        while n > 0 {
            ans ^= n;
            n >>= 1;
        }
        ans
    }
}
