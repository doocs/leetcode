impl Solution {
    pub fn lexicographically_smallest_array(nums: Vec<i32>, limit: i32) -> Vec<i32> {
        let n = nums.len();
        let mut idx: Vec<usize> = (0..n).collect();

        idx.sort_by_key(|&i| nums[i]);

        let mut ans = vec![0; n];

        let mut i = 0;
        while i < n {
            let mut j = i + 1;
            while j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit {
                j += 1;
            }

            let mut t = idx[i..j].to_vec();
            t.sort();

            for k in i..j {
                ans[t[k - i]] = nums[idx[k]];
            }

            i = j;
        }

        ans
    }
}
