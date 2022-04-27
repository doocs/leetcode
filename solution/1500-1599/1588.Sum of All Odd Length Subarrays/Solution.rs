impl Solution {
    pub fn sum_odd_length_subarrays(arr: Vec<i32>) -> i32 {
        let n = arr.len();
        let mut res = 0;
        let mut i = 1;
        while i <= n {
            let mut sum: i32 = arr[0..i].iter().sum();
            res += sum;
            for j in i..n {
                sum -= arr[j - i];
                sum += arr[j];
                res += sum;
            }
            i += 2;
        }
        res
    }
}
