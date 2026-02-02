impl Solution {
    pub fn minimum_abs_difference(mut arr: Vec<i32>) -> Vec<Vec<i32>> {
        arr.sort();
        let n = arr.len();

        let mut mi = i32::MAX;
        for i in 1..n {
            mi = mi.min(arr[i] - arr[i - 1]);
        }

        let mut ans = Vec::new();
        for i in 1..n {
            if arr[i] - arr[i - 1] == mi {
                ans.push(vec![arr[i - 1], arr[i]]);
            }
        }

        ans
    }
}
