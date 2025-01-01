impl Solution {
    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let n = nums.len();
        let mut ans = Vec::new();
        for mask in 0..(1 << n) {
            let mut t = Vec::new();
            for i in 0..n {
                if (mask >> i) & 1 == 1 {
                    t.push(nums[i]);
                }
            }
            ans.push(t);
        }
        ans
    }
}
