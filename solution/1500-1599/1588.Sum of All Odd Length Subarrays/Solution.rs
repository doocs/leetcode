impl Solution {
    pub fn sum_odd_length_subarrays(arr: Vec<i32>) -> i32 {
        let n = arr.len();
        let mut f = vec![0; n];
        let mut g = vec![0; n];
        let mut ans = arr[0];
        f[0] = arr[0];
        for i in 1..n {
            f[i] = g[i - 1] + arr[i] * ((i as i32) / 2 + 1);
            g[i] = f[i - 1] + arr[i] * (((i + 1) as i32) / 2);
            ans += f[i];
        }
        ans
    }
}
