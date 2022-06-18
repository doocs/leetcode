impl Solution {
    fn dfs(mut i: usize, t: &mut Vec<i32>, res: &mut Vec<Vec<i32>>, nums: &Vec<i32>) {
        let n = nums.len();
        if i == n {
            res.push(t.clone());
            return;
        }
        t.push(nums[i]);
        Self::dfs(i + 1, t, res, nums);
        let num = t.pop().unwrap();
        while i < n && num == nums[i] {
            i += 1;
        }
        Self::dfs(i, t, res, nums);
    }

    pub fn subsets_with_dup(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        nums.sort();
        let mut res = Vec::new();
        Self::dfs(0, &mut Vec::new(), &mut res, &nums);
        res
    }
}
