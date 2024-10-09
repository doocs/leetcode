impl Solution {
    pub fn maximum_sum(arr: Vec<i32>) -> i32 {
        let n = arr.len();
        let mut left = vec![0; n];
        let mut right = vec![0; n];
        let mut s = 0;
        for i in 0..n {
            s = (s.max(0)) + arr[i];
            left[i] = s;
        }
        s = 0;
        for i in (0..n).rev() {
            s = (s.max(0)) + arr[i];
            right[i] = s;
        }
        let mut ans = *left.iter().max().unwrap();
        for i in 1..n - 1 {
            ans = ans.max(left[i - 1] + right[i + 1]);
        }
        ans
    }
}
