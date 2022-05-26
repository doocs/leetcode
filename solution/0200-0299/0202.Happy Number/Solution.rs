impl Solution {
    pub fn is_happy(n: i32) -> bool {
        let get_next = |mut n: i32| {
            let mut res = 0;
            while n != 0 {
                res += (n % 10).pow(2);
                n /= 10;
            }
            res
        };
        let mut slow = n;
        let mut fast = get_next(n);
        while slow != fast {
            slow = get_next(slow);
            fast = get_next(get_next(fast));
        }
        slow == 1
    }
}
