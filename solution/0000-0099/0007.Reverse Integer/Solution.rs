impl Solution {
    pub fn reverse(mut x: i32) -> i32 {
        let mut ans = 0;
        while x != 0 {
            if ans < i32::MIN / 10 || ans > i32::MAX / 10 {
                return 0;
            }
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        ans
    }
}
