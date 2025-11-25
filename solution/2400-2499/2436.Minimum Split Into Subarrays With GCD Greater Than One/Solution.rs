impl Solution {
    pub fn minimum_splits(nums: Vec<i32>) -> i32 {
        let mut ans = 1;
        let mut g = 0;
        for &x in &nums {
            g = Self::gcd(g, x);
            if g == 1 {
                ans += 1;
                g = x;
            }
        }
        ans
    }

    fn gcd(a: i32, b: i32) -> i32 {
        if b == 0 {
            a
        } else {
            Self::gcd(b, a % b)
        }
    }
}
