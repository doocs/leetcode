impl Solution {
    fn get_next(mut n: i32) -> i32 {
        let mut res = 0;
        while n != 0 {
            res += (n % 10).pow(2);
            n /= 10;
        }
        res
    }

    pub fn is_happy(n: i32) -> bool {
        let mut slow = n;
        let mut fast = Self::get_next(n);
        while slow != fast {
            slow = Self::get_next(slow);
            fast = Self::get_next(Self::get_next(fast));
        }
        slow == 1
    }
}
