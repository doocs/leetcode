use std::cmp::Ordering;
impl Solution {
    pub fn array_sign(nums: Vec<i32>) -> i32 {
        let mut res = 1;
        for num in nums.iter() {
            match num.cmp(&0) {
                Ordering::Equal => return 0,
                Ordering::Less => res *= -1,
                Ordering::Greater => {}
            }
        }
        res
    }
}
