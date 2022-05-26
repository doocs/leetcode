impl Solution {
    fn dfs(nums: &Vec<i32>, res: &mut Vec<Vec<i32>>, i: usize, base: &mut Vec<i32>) {
        let n = nums.len();
        if i == n {
            return;
        }
        for j in i..n {
            base.push(nums[j]);
            res.push(base.clone());
            Self::dfs(nums, res, j + 1, base);
            base.pop();
        }
    }

    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut base = vec![];
        let mut res = vec![vec![]];
        Self::dfs(&nums, &mut res, 0, &mut base);
        res
    }
}
