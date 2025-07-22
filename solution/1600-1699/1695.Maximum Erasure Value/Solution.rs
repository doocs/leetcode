impl Solution {
    pub fn maximum_unique_subarray(nums: Vec<i32>) -> i32 {
        let m = *nums.iter().max().unwrap() as usize;
        let mut d = vec![0; m + 1];
        let n = nums.len();

        let mut s = vec![0; n + 1];
        for i in 0..n {
            s[i + 1] = s[i] + nums[i];
        }

        let mut ans = 0;
        let mut j = 0;
        for (i, &v) in nums.iter().enumerate().map(|(i, v)| (i + 1, v)) {
            j = j.max(d[v as usize]);
            ans = ans.max(s[i] - s[j]);
            d[v as usize] = i;
        }

        ans
    }
}
