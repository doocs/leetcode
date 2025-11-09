impl Solution {
    pub fn find_maximal_uncovered_ranges(n: i32, mut ranges: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        ranges.sort_by_key(|x| x[0]);
        let mut last = -1;
        let mut ans = Vec::new();
        for range in ranges {
            let l = range[0];
            let r = range[1];
            if last + 1 < l {
                ans.push(vec![last + 1, l - 1]);
            }
            last = last.max(r);
        }
        if last + 1 < n {
            ans.push(vec![last + 1, n - 1]);
        }
        ans
    }
}
