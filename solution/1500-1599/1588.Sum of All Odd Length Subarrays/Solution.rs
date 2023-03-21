impl Solution {
    pub fn sum_odd_length_subarrays(arr: Vec<i32>) -> i32 {
        let n = arr.len();
        let mut ans = 0;
        for i in 0..n {
            let mut s = 0;
            for j in i..n {
                s += arr[j];
                if (j - i + 1) % 2 == 1 {
                    ans += s;
                }
            }
        }
        ans
    }
}