impl Solution {
    pub fn reordered_power_of2(n: i32) -> bool {
        fn f(mut x: i32) -> [u8; 10] {
            let mut cnt = [0u8; 10];
            while x > 0 {
                cnt[(x % 10) as usize] += 1;
                x /= 10;
            }
            cnt
        }

        let target = f(n);
        let mut i = 1i32;
        while i <= 1_000_000_000 {
            if target == f(i) {
                return true;
            }
            i <<= 1;
        }
        false
    }
}
