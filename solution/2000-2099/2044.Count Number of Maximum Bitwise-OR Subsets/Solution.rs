impl Solution {
    fn dfs(nums: &Vec<i32>, i: usize, sum: i32) -> (i32, i32) {
        let n = nums.len();
        let mut max = i32::MIN;
        let mut res = 0;
        for j in i..n {
            let num = sum | nums[j];
            if num >= max {
                if num > max {
                    max = num;
                    res = 0;
                }
                res += 1;
            }
            let (r_max, r_res) = Self::dfs(nums, j + 1, num);
            if r_max >= max {
                if r_max > max {
                    max = r_max;
                    res = 0;
                }
                res += r_res;
            }
        }
        (max, res)
    }

    pub fn count_max_or_subsets(nums: Vec<i32>) -> i32 {
        Self::dfs(&nums, 0, 0).1
    }
}
