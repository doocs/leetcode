impl Solution {
    pub fn find_kth_bit(n: i32, k: i32) -> char {
        fn dfs(n: i32, k: i32) -> i32 {
            if k == 1 {
                return 0;
            }
            if (k & (k - 1)) == 0 {
                return 1;
            }
            let m: i32 = 1 << n;
            if k * 2 < m - 1 {
                dfs(n - 1, k)
            } else {
                dfs(n - 1, m - k) ^ 1
            }
        }

        if dfs(n, k) == 0 { '0' } else { '1' }
    }
}
