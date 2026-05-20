impl Solution {
    pub fn find_the_prefix_common_array(a: Vec<i32>, b: Vec<i32>) -> Vec<i32> {
        let n = a.len();
        let mut ans = vec![0; n];
        let mut vis = vec![1; n + 1];
        let mut s = 0;
        for i in 0..n {
            vis[a[i] as usize] ^= 1;
            s += vis[a[i] as usize];
            vis[b[i] as usize] ^= 1;
            s += vis[b[i] as usize];
            ans[i] = s;
        }
        ans
    }
}
