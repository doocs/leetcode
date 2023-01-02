impl Solution {
    pub fn count_digits(num: i32) -> i32 {
        let mut ans = 0;
        let mut cur = num;
        while cur != 0 {
            if num % (cur % 10) == 0 {
                ans += 1;
            }
            cur /= 10;
        }
        ans
    }
}
