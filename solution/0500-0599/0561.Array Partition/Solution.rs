impl Solution {
    pub fn array_pair_sum(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len();
        let mut i = 0;
        let mut res = 0;
        while i < n {
            res += nums[i];
            i += 2;
        }
        res
    }
}
