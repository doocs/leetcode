impl Solution {
    pub fn difference_of_sums(n: i32, m: i32) -> i32 {
        let mut ans = 0;
        for i in 1..=n {
            if i % m != 0 {
                ans += i;
            } else {
                ans -= i;
            }
        }
        ans
    }
}
