impl Solution {
    pub fn same_end_substring_count(s: String, queries: Vec<Vec<i32>>) -> Vec<i32> {
        let n = s.len();
        let mut cnt: Vec<Vec<i32>> = vec![vec![0; n + 1]; 26];
        for j in 1..=n {
            for i in 0..26 {
                cnt[i][j] = cnt[i][j - 1];
            }
            cnt[(s.as_bytes()[j - 1] as usize) - (b'a' as usize)][j] += 1;
        }
        let mut ans: Vec<i32> = Vec::new();
        for q in queries.iter() {
            let l = q[0] as usize;
            let r = q[1] as usize;
            let mut t = (r - l + 1) as i32;
            for i in 0..26 {
                let x = cnt[i][r + 1] - cnt[i][l];
                t += (x * (x - 1)) / 2;
            }
            ans.push(t);
        }
        ans
    }
}
