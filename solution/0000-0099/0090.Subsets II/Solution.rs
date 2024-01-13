impl Solution {
    pub fn subsets_with_dup(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut nums = nums;
        nums.sort();
        let mut ans = Vec::new();
        let mut t = Vec::new();

        fn dfs(i: usize, nums: &Vec<i32>, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
            if i >= nums.len() {
                ans.push(t.clone());
                return;
            }
            t.push(nums[i]);
            dfs(i + 1, nums, t, ans);
            t.pop();
            let mut i = i;
            while i + 1 < nums.len() && nums[i + 1] == nums[i] {
                i += 1;
            }
            dfs(i + 1, nums, t, ans);
        }

        dfs(0, &nums, &mut t, &mut ans);
        ans
    }
}
