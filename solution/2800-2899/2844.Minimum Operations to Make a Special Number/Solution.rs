impl Solution {
    pub fn minimum_operations(num: String) -> i32 {
        let n = num.len();
        let bytes = num.as_bytes();

        let mut memo = vec![vec![-1; 25]; n];

        fn dfs(i: usize, k: usize, n: usize, bytes: &[u8], memo: &mut Vec<Vec<i32>>) -> i32 {
            if i == n {
                return if k == 0 { 0 } else { n as i32 };
            }

            if memo[i][k] != -1 {
                return memo[i][k];
            }

            // delete current digit
            let mut res = dfs(i + 1, k, n, bytes, memo) + 1;

            // keep current digit
            let digit = (bytes[i] - b'0') as usize;
            let nk = (k * 10 + digit) % 25;
            res = res.min(dfs(i + 1, nk, n, bytes, memo));

            memo[i][k] = res;
            res
        }

        dfs(0, 0, n, &bytes, &mut memo)
    }
}
