impl Solution {
    pub fn max_two_events(mut events: Vec<Vec<i32>>) -> i32 {
        events.sort_by(|a, b| a[0].cmp(&b[0]));

        let n: usize = events.len();
        let mut f: Vec<i32> = vec![0; n + 1];

        for i in (0..n).rev() {
            f[i] = f[i + 1].max(events[i][2]);
        }

        let mut ans: i32 = 0;

        for e in &events {
            let mut v: i32 = e[2];

            let mut left: usize = 0;
            let mut right: usize = n;
            while left < right {
                let mid = (left + right) >> 1;
                if events[mid][0] > e[1] {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            if left < n {
                v += f[left];
            }

            ans = ans.max(v);
        }

        ans
    }
}
