impl Solution {
    pub fn sum_of_multiples(n: i32) -> i32 {
        (1..=n).filter(|&x| (x % 3 == 0 || x % 5 == 0 || x % 7 == 0)).sum()
    }
}
