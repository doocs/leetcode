impl Solution {
    fn dfs(nums: &Vec<i32>, paths: &mut Vec<i32>, res: &mut Vec<Vec<i32>>) {
        if paths.len() == nums.len() {
            res.push(paths.clone());
            return;
        }
        for i in nums.iter() {
            if !paths.contains(i) {
                paths.push(*i);
                Self::dfs(nums, paths, res);
                paths.pop();
            }
        }
    }

    pub fn permute(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut res = vec![];
        Self::dfs(&nums, &mut vec![], &mut res);
        res
    }
}
