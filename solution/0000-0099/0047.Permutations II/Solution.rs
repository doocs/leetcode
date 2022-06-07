use std::collections::HashSet;
impl Solution {
    fn dfs(i: usize, nums: &mut Vec<i32>, res: &mut Vec<Vec<i32>>) {
        let n = nums.len();
        if i == n {
            res.push(nums.clone());
            return;
        }
        let mut set = HashSet::new();
        for j in i..n {
            if set.contains(&nums[j]) {
                continue;
            }
            set.insert(nums[j]);
            nums.swap(i, j);
            Self::dfs(i + 1, nums, res);
            nums.swap(i, j);
        }
    }

    pub fn permute_unique(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut res = vec![];
        Self::dfs(0, &mut nums, &mut res);
        res
    }
}
