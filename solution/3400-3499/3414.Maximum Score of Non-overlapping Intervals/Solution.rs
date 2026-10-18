impl Solution {
    pub fn maximum_weight(intervals: Vec<Vec<i32>>) -> Vec<i32> {
        let n = intervals.len();
        let mut arr: Vec<[i32; 4]> = intervals
            .iter()
            .enumerate()
            .map(|(i, e)| [e[0], e[1], e[2], i as i32])
            .collect();
        arr.sort_unstable();
        let mut nxt = vec![0; n];
        for i in 0..n {
            let mut l = i + 1;
            let mut r = n;
            while l < r {
                let mid = (l + r) >> 1;
                if arr[mid][0] > arr[i][1] {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            nxt[i] = l;
        }
        let mut f = vec![[0i64; 5]; n + 1];
        let mut g: Vec<[Vec<i32>; 5]> = vec![Default::default(); n + 1];
        for i in (0..n).rev() {
            for k in 1..5 {
                let s1 = f[i + 1][k];
                let a1 = g[i + 1][k].clone();
                let mut a2 = g[nxt[i]][k - 1].clone();
                let x = arr[i][3];
                let j = a2.partition_point(|&v| v < x);
                a2.insert(j, x);
                let s2 = f[nxt[i]][k - 1] + arr[i][2] as i64;
                if s2 > s1 || (s2 == s1 && a2 < a1) {
                    f[i][k] = s2;
                    g[i][k] = a2;
                } else {
                    f[i][k] = s1;
                    g[i][k] = a1;
                }
            }
        }
        g[0][4].clone()
    }
}
