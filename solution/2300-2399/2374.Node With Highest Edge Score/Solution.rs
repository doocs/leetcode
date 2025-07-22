impl Solution {
    pub fn edge_score(edges: Vec<i32>) -> i32 {
        let n = edges.len();
        let mut cnt = vec![0_i64; n];
        let mut ans = 0;

        for (i, &j) in edges.iter().enumerate() {
            let j = j as usize;
            cnt[j] += i as i64;
            if cnt[ans] < cnt[j] || (cnt[ans] == cnt[j] && j < ans) {
                ans = j;
            }
        }

        ans as i32
    }
}
