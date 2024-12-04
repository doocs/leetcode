impl Solution {
    pub fn smallest_equal(nums: Vec<i32>) -> i32 {
        for (i, &x) in nums.iter().enumerate() {
            if i % 10 == x as usize {
                return i as i32;
            }
        }
        -1
    }
}
