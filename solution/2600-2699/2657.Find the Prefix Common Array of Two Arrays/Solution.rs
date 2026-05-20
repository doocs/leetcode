impl Solution {
    pub fn find_the_prefix_common_array(a: Vec<i32>, b: Vec<i32>) -> Vec<i32> {
        let n = a.len();
        let mut ans = vec![0; n];
        let mut cnt1 = vec![0; n + 1];
        let mut cnt2 = vec![0; n + 1];
        for i in 0..n {
            cnt1[a[i] as usize] += 1;
            cnt2[b[i] as usize] += 1;
            for j in 1..=n {
                ans[i] += std::cmp::min(cnt1[j], cnt2[j]);
            }
        }
        ans
    }
}
