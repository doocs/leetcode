impl Solution {
    pub fn max_jumps(arr: Vec<i32>, d: i32) -> i32 {
        fn dfs(i: usize, n: usize, d: usize, arr: &[i32], f: &mut Vec<i32>) -> i32 {
            if f[i] != 0 {
                return f[i];
            }
            let mut ans = 1;
            let mut j = (i as isize) - 1;
            while j >= 0 {
                if i - (j as usize) > d || arr[j as usize] >= arr[i] {
                    break;
                }
                ans = ans.max(1 + dfs(j as usize, n, d, arr, f));
                j -= 1;
            }
            j = (i as isize) + 1;
            while (j as usize) < n {
                if j as usize - i > d || arr[j as usize] >= arr[i] {
                    break;
                }
                ans = ans.max(1 + dfs(j as usize, n, d, arr, f));
                j += 1;
            }
            f[i] = ans;
            ans
        }
        let n = arr.len();
        let d = d as usize;
        let mut f = vec![0; n];
        let mut ans = 0;
        for i in 0..n {
            ans = ans.max(dfs(i, n, d, &arr, &mut f));
        }
        ans
    }
}
