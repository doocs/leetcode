impl Solution {
    fn dfs(i: usize, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>, nums: &Vec<i32>) {
        if i == nums.len() {
            ans.push(t.clone());
            return;
        }
        Self::dfs(i + 1, t, ans, nums);
        t.push(nums[i]);
        Self::dfs(i + 1, t, ans, nums);
        t.pop();
    }

    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut ans = Vec::new();
        Self::dfs(0, &mut Vec::new(), &mut ans, &nums);
        ans
    }
}
