impl Solution {
    pub fn find_max_consecutive_ones(nums: Vec<i32>) -> i32 {
        let mut res = 0;
        let mut count = 0;
        for num in nums {
            if num == 0 {
                res = res.max(count);
                count = 0;
            } else {
                count += 1;
            }
        }
        res.max(count)
    }
}
