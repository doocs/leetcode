impl Solution {
    pub fn max_dot_product(nums1: Vec<i32>, nums2: Vec<i32>) -> i32 {
        let m = nums1.len();
        let n = nums2.len();
        let mut f = vec![vec![i32::MIN; n + 1]; m + 1];

        for i in 1..=m {
            for j in 1..=n {
                let v = nums1[i - 1] * nums2[j - 1];
                f[i][j] = f[i][j].max(f[i - 1][j]).max(f[i][j - 1]);
                f[i][j] = f[i][j].max(f[i - 1][j - 1].max(0) + v);
            }
        }

        f[m][n]
    }
}
