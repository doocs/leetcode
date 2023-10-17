impl Solution {
    pub fn sum_of_multiples(n: i32) -> i32 {
        let mut ans = 0;

        for x in 1..=n {
            if x % 3 == 0 || x % 5 == 0 || x % 7 == 0 {
                ans += x;
            }
        }

        ans
    }
}