impl Solution {
    pub fn max_distance(arrays: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        let mut mi = arrays[0][0];
        let mut mx = arrays[0][arrays[0].len() - 1];

        for i in 1..arrays.len() {
            let arr = &arrays[i];
            let a = (arr[0] - mx).abs();
            let b = (arr[arr.len() - 1] - mi).abs();
            ans = ans.max(a).max(b);

            mi = mi.min(arr[0]);
            mx = mx.max(arr[arr.len() - 1]);
        }

        ans
    }
}
