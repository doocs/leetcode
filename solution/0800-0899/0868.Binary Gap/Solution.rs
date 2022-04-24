impl Solution {
    pub fn binary_gap(mut n: i32) -> i32 {
        let mut res = 0;
        let mut i = 0;
        let mut j = -1;
        while n != 0 {
            if n & 1 == 1 {
                if j != -1 {
                    res = res.max(i - j);
                }
                j = i;
            }
            n >>= 1;
            i += 1;
        }
        res
    }
}
