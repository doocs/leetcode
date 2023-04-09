impl Solution {
    pub fn find132pattern(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut vk = i32::MIN;
        let mut stk = vec![];
        for i in (0..n).rev() {
            if nums[i] < vk {
                return true;
            }
            while !stk.is_empty() && stk.last().unwrap() < &nums[i] {
                vk = stk.pop().unwrap();
            }
            stk.push(nums[i])
        }
        false
    }
}
