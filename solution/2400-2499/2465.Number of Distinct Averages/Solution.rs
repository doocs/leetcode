impl Solution {
    pub fn distinct_averages(nums: Vec<i32>) -> i32 {
        let mut nums = nums;
        nums.sort();
        let n = nums.len();
        let mut cnt = vec![0; 201];
        let mut ans = 0;

        for i in 0..n >> 1 {
            let x = (nums[i] + nums[n - i - 1]) as usize;
            cnt[x] += 1;

            if cnt[x] == 1 {
                ans += 1;
            }
        }

        ans
    }
}
