impl Solution {
    pub fn max_turbulence_size(arr: Vec<i32>) -> i32 {
        let mut ans = 1;
        let mut f = 1;
        let mut g = 1;

        for i in 1..arr.len() {
            let ff = if arr[i - 1] < arr[i] { g + 1 } else { 1 };
            let gg = if arr[i - 1] > arr[i] { f + 1 } else { 1 };
            f = ff;
            g = gg;
            ans = ans.max(f.max(g));
        }

        ans
    }
}
