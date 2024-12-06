impl Solution {
    pub fn bitwise_complement(mut n: i32) -> i32 {
        if n == 0 {
            return 1;
        }
        let mut ans = 0;
        let mut i = 0;
        while n != 0 {
            ans |= ((n & 1) ^ 1) << i;
            n >>= 1;
            i += 1;
        }
        ans
    }
}
