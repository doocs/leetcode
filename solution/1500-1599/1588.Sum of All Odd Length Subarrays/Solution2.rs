impl Solution {
    pub fn sum_odd_length_subarrays(arr: Vec<i32>) -> i32 {
        let mut ans = arr[0];
        let mut f = arr[0];
        let mut g = 0;
        for i in 1..arr.len() {
            let ff = g + arr[i] * ((i as i32) / 2 + 1);
            let gg = f + arr[i] * (((i + 1) as i32) / 2);
            f = ff;
            g = gg;
            ans += f;
        }
        ans
    }
}
