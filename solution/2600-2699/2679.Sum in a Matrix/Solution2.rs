impl Solution {
    pub fn matrix_sum(nums: Vec<Vec<i32>>) -> i32 {
        let mut nums = nums.clone();
        for row in nums.iter_mut() {
            row.sort();
        }

        let mut ans = 0;
        for j in 0..nums[0].len() {
            let mut mx = 0;
            for row in &nums {
                mx = mx.max(row[j]);
            }
            ans += mx;
        }

        ans
    }
}
