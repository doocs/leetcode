impl Solution {
    pub fn max_sum_distinct_triplet(x: Vec<i32>, y: Vec<i32>) -> i32 {
        let n = x.len();
        let mut arr: Vec<(i32, i32)> = (0..n).map(|i| (x[i], y[i])).collect();
        arr.sort_by(|a, b| b.1.cmp(&a.1));
        let mut vis = std::collections::HashSet::new();
        let mut ans = 0;
        for (a, b) in arr {
            if vis.insert(a) {
                ans += b;
                if vis.len() == 3 {
                    return ans;
                }
            }
        }
        -1
    }
}
