use std::collections::HashSet;
impl Solution {
    fn get_next(mut n: i32) -> i32 {
        let mut res = 0;
        while n != 0 {
            res += (n % 10).pow(2);
            n /= 10;
        }
        res
    }

    pub fn is_happy(mut n: i32) -> bool {
        let mut set = HashSet::new();
        while n != 1 {
            let next = Self::get_next(n);
            if set.contains(&next) {
                return false;
            }
            set.insert(next);
            n = next;
        }
        true
    }
}
