use std::cmp::Ordering;

impl Solution {
    pub fn two_sum(numbers: Vec<i32>, target: i32) -> Vec<i32> {
        let n = numbers.len();
        let mut l = 0;
        let mut r = n - 1;
        loop {
            match (numbers[l] + numbers[r]).cmp(&target) {
                Ordering::Less => l += 1,
                Ordering::Greater => r -= 1,
                Ordering::Equal => break,
            }
        }
        vec![l as i32 + 1, r as i32 + 1]
    }
}
