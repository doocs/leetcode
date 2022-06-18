impl Solution {
    fn dfs(i: usize, t: &mut Vec<i32>, res: &mut Vec<Vec<i32>>, nums: &Vec<i32>) {
        if i == nums.len() {
            res.push(t.clone());
            return;
        }
        Self::dfs(i + 1, t, res, nums);
        t.push(nums[i]);
        Self::dfs(i + 1, t, res, nums);
        t.pop();
    }

    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut res = Vec::new();
        Self::dfs(0, &mut Vec::new(), &mut res, &nums);
        res
    }
}
