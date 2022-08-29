impl Solution {
    pub fn shuffle(nums: Vec<i32>, n: i32) -> Vec<i32> {
        let n = n as usize;
        let mut res = Vec::new();
        for i in 0..n {
            res.push(nums[i]);
            res.push(nums[n + i]);
        }
        res
    }
}
