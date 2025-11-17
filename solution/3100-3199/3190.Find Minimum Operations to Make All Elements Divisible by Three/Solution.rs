impl Solution {
    pub fn minimum_operations(nums: Vec<i32>) -> i32 {
        nums.iter().filter(|&&x| x % 3 != 0).count() as i32
    }
}
