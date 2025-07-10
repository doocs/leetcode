impl Solution {
    pub fn max_value(mut events: Vec<Vec<i32>>, k: i32) -> i32 {
        events.sort_by_key(|e| e[1]);
        let n = events.len();
        let mut f = vec![vec![0; (k + 1) as usize]; n + 1];

        for i in 1..=n {
            let st = events[i - 1][0];
            let val = events[i - 1][2];
            let p = search(&events, st, i - 1);
            for j in 1..=k as usize {
                f[i][j] = f[i - 1][j].max(f[p][j - 1] + val);
            }
        }

        f[n][k as usize]
    }
}

fn search(events: &Vec<Vec<i32>>, x: i32, hi: usize) -> usize {
    let mut l = 0;
    let mut r = hi;
    while l < r {
        let mid = (l + r) / 2;
        if events[mid][1] >= x {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    l
}
