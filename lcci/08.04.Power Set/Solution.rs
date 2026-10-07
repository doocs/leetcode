impl Solution {
    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let n = nums.len();
        let mut res: Vec<Vec<i32>> = vec![vec![]];
        for i in 0..n {
            let m = res.len();
            for j in 0..m {
                let mut subset = res[j].clone();
                subset.push(nums[i]);
                res.push(subset);
            }
        }
        res
    }
}
