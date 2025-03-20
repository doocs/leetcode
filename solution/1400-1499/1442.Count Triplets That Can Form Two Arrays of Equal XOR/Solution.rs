impl Solution {
    pub fn count_triplets(arr: Vec<i32>) -> i32 {
        let mut ans = 0;
        let n = arr.len();

        for i in 0..n {
            let mut s = arr[i];
            for k in (i + 1)..n {
                s ^= arr[k];
                if s == 0 {
                    ans += (k - i) as i32;
                }
            }
        }

        ans
    }
}
