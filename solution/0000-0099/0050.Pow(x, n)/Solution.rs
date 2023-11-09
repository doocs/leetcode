impl Solution {
    #[allow(dead_code)]
    pub fn my_pow(x: f64, n: i32) -> f64 {
        let mut x = x;
        let n = n as i64;
        if n >= 0 {
            Self::quick_pow(&mut x, n)
        } else {
            1.0 / Self::quick_pow(&mut x, -n)
        }
    }

    #[allow(dead_code)]
    fn quick_pow(x: &mut f64, mut n: i64) -> f64 {
        // `n` should greater or equal to zero
        let mut ret = 1.0;
        while n != 0 {
            if (n & 0x1) == 1 {
                ret *= *x;
            }
            *x *= *x;
            n >>= 1;
        }
        ret
    }
}
