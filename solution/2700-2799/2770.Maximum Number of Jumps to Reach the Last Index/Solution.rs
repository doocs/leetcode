impl Solution {
    pub fn maximum_jumps(nums: Vec<i32>, target: i32) -> i32 {
        let n = nums.len();
        let mut f = vec![-1; n];

        fn dfs(i: usize, nums: &Vec<i32>, target: i32, f: &mut Vec<i32>) -> i32 {
            if i == nums.len() - 1 {
                return 0;
            }
            if f[i] != -1 {
                return f[i];
            }
            f[i] = -(1 << 30);
            for j in i + 1..nums.len() {
                if (nums[i] - nums[j]).abs() <= target {
                    f[i] = f[i].max(1 + dfs(j, nums, target, f));
                }
            }
            f[i]
        }

        let ans = dfs(0, &nums, target, &mut f);
        if ans < 0 { -1 } else { ans }
    }
}
