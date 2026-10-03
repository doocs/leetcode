impl Solution {
    pub fn max_value(mut events: Vec<Vec<i32>>, k: i32) -> i32 {
        events.sort_by_key(|e| e[0]);
        let n = events.len();
        let kk = k as usize;
        let mut f = vec![vec![0; kk + 1]; n + 1];
        for i in (0..n).rev() {
            let ed = events[i][1];
            let val = events[i][2];
            let p = search(&events, ed, i + 1, n);
            for c in 0..=kk {
                f[i][c] = f[i + 1][c];
                if c > 0 {
                    f[i][c] = f[i][c].max(f[p][c - 1] + val);
                }
            }
        }
        f[0][kk]
    }
}

fn search(events: &Vec<Vec<i32>>, x: i32, lo: usize, n: usize) -> usize {
    let mut l = lo;
    let mut r = n;
    while l < r {
        let mid = (l + r) / 2;
        if events[mid][0] > x {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    l
}
