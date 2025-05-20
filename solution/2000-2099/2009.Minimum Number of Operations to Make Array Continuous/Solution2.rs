impl Solution {
    pub fn min_operations(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len();
        if n == 0 {
            return 0;
        }
        let mut m = 1usize;
        for i in 1..n {
            if nums[i] != nums[i - 1] {
                nums[m] = nums[i];
                m += 1;
            }
        }
        let mut ans = n as i32;
        let mut j = 0usize;
        for i in 0..m {
            while j < m && nums[j] - nums[i] <= n as i32 - 1 {
                j += 1;
            }
            ans = ans.min(n as i32 - (j as i32 - i as i32));
        }
        ans
    }
}
