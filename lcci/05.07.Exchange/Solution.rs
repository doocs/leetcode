impl Solution {
    pub fn exchange_bits(mut num: i32) -> i32 {
        let mut res = 0;
        let mut i = 0;
        while num != 0 {
            let a = num & 1;
            num >>= 1;
            let b = num & 1;
            num >>= 1;
            res |= a << i + 1;
            res |= b << i;
            i += 2;
        }
        res
    }
}
