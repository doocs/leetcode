impl Solution {
    pub fn binary_gap(mut n: i32) -> i32 {
        let mut ans = 0;
        let mut pre = 100;
        let mut cur = 0;
        while n != 0 {
            if n % 2 == 1 {
                ans = ans.max(cur - pre);
                pre = cur;
            }
            cur += 1;
            n >>= 1;
        }
        ans
    }
}
