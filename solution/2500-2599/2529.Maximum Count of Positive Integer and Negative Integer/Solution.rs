impl Solution {
    pub fn maximum_count(nums: Vec<i32>) -> i32 {
        let mut count = [0, 0];
        for &num in nums.iter() {
            if num < 0 {
                count[0] += 1;
            } else if num > 0 {
                count[1] += 1;
            }
        }
        *count.iter().max().unwrap()
    }
}
