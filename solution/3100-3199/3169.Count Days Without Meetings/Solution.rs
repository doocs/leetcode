impl Solution {
    pub fn count_days(days: i32, mut meetings: Vec<Vec<i32>>) -> i32 {
        meetings.sort_by_key(|m| m[0]);
        let mut ans = 0;
        let mut last = 0;

        for e in meetings {
            let st = e[0];
            let ed = e[1];
            if last < st {
                ans += st - last - 1;
            }
            last = last.max(ed);
        }

        ans + (days - last)
    }
}
