impl Solution {
    pub fn left_right_difference(nums: Vec<i32>) -> Vec<i32> {
        let mut left = 0;
        let mut right: i32 = nums.iter().sum();
        let mut ans = vec![];

        for &x in &nums {
            right -= x;
            ans.push((left - right).abs());
            left += x;
        }

        ans
    }
}
