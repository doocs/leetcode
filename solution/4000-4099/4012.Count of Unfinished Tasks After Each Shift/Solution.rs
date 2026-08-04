impl Solution {
    pub fn count_tasks(tasks: Vec<i32>, shifts: Vec<i32>) -> Vec<i32> {
        let m = tasks.len();
        let n = shifts.len();

        let mut s = vec![0i64; m + 1];
        for i in 0..m {
            s[i + 1] = s[i] + tasks[i] as i64;
        }

        let mut ans = vec![0i32; n];

        let mut i = 0usize;
        let mut cur = 0i64;

        for j in 0..n {
            if (shifts[j] as i64) < tasks[i] as i64 - cur {
                cur += shifts[j] as i64;
                ans[j] = (m - i) as i32;
            } else {
                let t = shifts[j] as i64 - (tasks[i] as i64 - cur);

                if t >= s[m] - s[i + 1] {
                    i = 0;
                    cur = 0;
                } else {
                    let mut l = i + 1;
                    let mut r = m;

                    while l < r {
                        let mid = (l + r) >> 1;
                        if t < s[mid + 1] - s[i + 1] {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }

                    cur = t - (s[l] - s[i + 1]);
                    i = l;
                    ans[j] = (m - i) as i32;
                }
            }
        }

        ans
    }
}
