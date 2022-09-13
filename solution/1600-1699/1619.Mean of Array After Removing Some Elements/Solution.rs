impl Solution {
    pub fn trim_mean(mut arr: Vec<i32>) -> f64 {
        arr.sort();
        let n = arr.len();
        let count = (n as f64 * 0.05).floor() as usize;
        let mut sum = 0;
        for i in count..n - count {
            sum += arr[i];
        }
        sum as f64 / (n as f64 * 0.9)
    }
}
