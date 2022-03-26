impl Solution {
    pub fn count_hill_valley(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = 0;
        let mut prev = nums[0];
        for i in 1..n - 1 {
            let num = nums[i];
            let next = nums[i + 1];
            if num == next {
                continue;
            }
            if num > prev && num > next || num < prev && num < next {
                res += 1;
            }
            prev = num;
        }
        res
    }
}
