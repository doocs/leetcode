impl Solution {
    pub fn find_min_fibonacci_numbers(mut k: i32) -> i32 {
        let mut a = 1;
        let mut b = 1;
        while b <= k {
            let c = a + b;
            a = b;
            b = c;
        }

        let mut ans = 0;
        while k > 0 {
            if k >= b {
                k -= b;
                ans += 1;
            }
            let c = b - a;
            b = a;
            a = c;
        }
        ans
    }
}
