impl Solution {
    pub fn minimize_xor(num1: i32, mut num2: i32) -> i32 {
        let mut cnt = 0;
        while num2 > 0 {
            num2 -= num2 & -num2;
            cnt += 1;
        }
        let mut x = 0;
        let mut c = cnt;
        for i in (0..=30).rev() {
            if c > 0 && (num1 >> i) & 1 == 1 {
                x |= 1 << i;
                c -= 1;
            }
        }
        for i in 0..=30 {
            if c == 0 {
                break;
            }
            if ((num1 >> i) & 1) == 0 {
                x |= 1 << i;
                c -= 1;
            }
        }
        x
    }
}
