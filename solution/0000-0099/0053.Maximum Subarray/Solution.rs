impl Solution {
    pub fn max_sub_array(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = nums[0];
        let mut sum = nums[0];
        for i in 1..n {
            let num = nums[i];
            sum = num.max(sum + num);
            res = res.max(sum);
        }
        res
    }
}
