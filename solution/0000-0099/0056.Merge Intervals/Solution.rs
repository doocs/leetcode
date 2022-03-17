impl Solution {
    pub fn merge(mut intervals: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        intervals.sort_unstable_by(|a, b| a[0].cmp(&b[0]));
        let n = intervals.len();
        let mut res = vec![];
        let mut i = 0;
        while i < n {
            let l = intervals[i][0];
            let mut r = intervals[i][1];
            i += 1;
            while i < n && r >= intervals[i][0] {
                r = r.max(intervals[i][1]);
                i += 1;
            }
            res.push(vec![l, r]);
        }
        res
    }
}
