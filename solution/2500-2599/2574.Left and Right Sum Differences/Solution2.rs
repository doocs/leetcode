impl Solution {
    pub fn left_right_difference(nums: Vec<i32>) -> Vec<i32> {
        let mut ans = vec![];

        for i in 0..nums.len() {
            let mut left = 0;
            for j in 0..i {
                left += nums[j];
            }

            let mut right = 0;
            for k in i + 1..nums.len() {
                right += nums[k];
            }

            ans.push((left - right).abs());
        }

        ans
    }
}
