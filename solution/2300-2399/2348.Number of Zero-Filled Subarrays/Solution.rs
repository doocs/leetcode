impl Solution {
    pub fn zero_filled_subarray(nums: Vec<i32>) -> i64 {
        let mut ans: i64 = 0;
        let mut cnt: i64 = 0;
        for x in nums {
            if x == 0 {
                cnt += 1;
                ans += cnt;
            } else {
                cnt = 0;
            }
        }
        ans
    }
}
