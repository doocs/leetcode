impl Solution {
    pub fn count_prime_set_bits(left: i32, right: i32) -> i32 {
        let primes = [2, 3, 5, 7, 11, 13, 17, 19];
        let mut ans = 0;

        for i in left..=right {
            let bits = i.count_ones() as i32;
            if primes.contains(&bits) {
                ans += 1;
            }
        }

        ans
    }
}
