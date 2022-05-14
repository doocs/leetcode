impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let mut prev = 0;
        for &num in nums.iter() {
            if prev != num {
                return prev;
            }
            prev += 1;
        }
        prev
    }
}
