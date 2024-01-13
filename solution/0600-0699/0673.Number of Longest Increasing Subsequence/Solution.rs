impl Solution {
    pub fn find_number_of_lis(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;
        let mut mx = 0;
        let mut f = vec![1; n];
        let mut cnt = vec![1; n];
        for i in 0..n {
            for j in 0..i {
                if nums[j] < nums[i] {
                    if f[i] < f[j] + 1 {
                        f[i] = f[j] + 1;
                        cnt[i] = cnt[j];
                    } else if f[i] == f[j] + 1 {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if mx < f[i] {
                mx = f[i];
                ans = cnt[i];
            } else if mx == f[i] {
                ans += cnt[i];
            }
        }
        ans
    }
}
