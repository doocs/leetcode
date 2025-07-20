impl Solution {
    pub fn max_value(mut events: Vec<Vec<i32>>, k: i32) -> i32 {
        events.sort_by_key(|e| e[0]);
        let n = events.len();
        let mut f = vec![vec![0; (k + 1) as usize]; n];

        fn dfs(i: usize, k: i32, events: &Vec<Vec<i32>>, f: &mut Vec<Vec<i32>>, n: usize) -> i32 {
            if i >= n || k <= 0 {
                return 0;
            }
            if f[i][k as usize] != 0 {
                return f[i][k as usize];
            }
            let j = search(events, events[i][1], i + 1, n);
            let ans = dfs(i + 1, k, events, f, n).max(dfs(j, k - 1, events, f, n) + events[i][2]);
            f[i][k as usize] = ans;
            ans
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

        dfs(0, k, &events, &mut f, n)
    }
}
