impl Solution {
    fn dfs(mut i: usize, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>, nums: &Vec<i32>) {
        ans.push(t.clone());
        while i < nums.len() {
            t.push(nums[i]);
            i += 1;
            Self::dfs(i, t, ans, nums);
            t.pop();
        }
    }

    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut ans = Vec::new();
        let mut t = Vec::new();
        Self::dfs(0, &mut t, &mut ans, &nums);
        ans
    }
}
