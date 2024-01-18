impl Solution {
    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let n = nums.len();
        let mut res: Vec<Vec<i32>> = vec![vec![]];
        for i in 0..n {
            for j in 0..res.len() {
                res.push(vec![..res[j].clone(), vec![nums[i]]].concat());
            }
        }
        res
    }
}
