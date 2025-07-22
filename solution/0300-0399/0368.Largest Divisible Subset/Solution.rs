impl Solution {
    pub fn largest_divisible_subset(nums: Vec<i32>) -> Vec<i32> {
        let mut nums = nums;
        nums.sort();

        let n = nums.len();
        let mut f = vec![1; n];
        let mut k = 0;

        for i in 0..n {
            for j in 0..i {
                if nums[i] % nums[j] == 0 {
                    f[i] = f[i].max(f[j] + 1);
                }
            }
            if f[k] < f[i] {
                k = i;
            }
        }

        let mut m = f[k];
        let mut ans = Vec::new();

        for i in (0..=k).rev() {
            if nums[k] % nums[i] == 0 && f[i] == m {
                ans.push(nums[i]);
                k = i;
                m -= 1;
            }
        }

        ans
    }
}
