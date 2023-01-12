impl Solution {
    pub fn sum_base(mut n: i32, k: i32) -> i32 {
        let mut ans = 0;
        while n != 0 {
            ans += n % k;
            n /= k;
        }
        ans
    }
}
