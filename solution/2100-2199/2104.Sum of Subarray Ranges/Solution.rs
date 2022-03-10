impl Solution {
    pub fn sub_array_ranges(nums: Vec<i32>) -> i64 {
        let n = nums.len();
        let mut res: i64 = 0;
        for i in 1..n {
            let mut min = nums[i - 1];
            let mut max = nums[i - 1];
            for j in i..n {
                min = min.min(nums[j]);
                max = max.max(nums[j]);
                res += (max - min) as i64;
            }
        }
        res
    }
}
