impl Solution {
    pub fn largest_perimeter(mut nums: Vec<i32>) -> i32 {
        let n = nums.len();
        nums.sort_unstable_by(|a, b| b.cmp(&a));
        for i in 2..n {
            let (a, b, c) = (nums[i - 2], nums[i - 1], nums[i]);
            if a < b + c {
                return a + b + c;
            }
        }
        0
    }
}
