impl Solution {
    pub fn find132pattern(nums: Vec<i32>) -> bool {
        let n = nums.len();
        if n < 3 {
            return false;
        }
        let mut last = i32::MIN;
        let mut stack = vec![];
        for i in (0..n).rev() {
            if nums[i] < last {
                return true;
            }
            while !stack.is_empty() && stack.last().unwrap() < &nums[i] {
                last = stack.pop().unwrap();
            }
            stack.push(nums[i])
        }
        false
    }
}
