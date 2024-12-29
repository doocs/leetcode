impl Solution {
    pub fn rank_teams(votes: Vec<String>) -> String {
        let m = votes[0].len();
        let mut cnt = vec![vec![0; m + 1]; 26];

        for vote in &votes {
            for (i, ch) in vote.chars().enumerate() {
                cnt[(ch as u8 - b'A') as usize][i] += 1;
            }
        }

        let mut s: Vec<char> = votes[0].chars().collect();

        s.sort_by(|&a, &b| {
            let i = (a as u8 - b'A') as usize;
            let j = (b as u8 - b'A') as usize;

            for k in 0..m {
                if cnt[i][k] != cnt[j][k] {
                    return cnt[j][k].cmp(&cnt[i][k]);
                }
            }
            a.cmp(&b)
        });

        s.into_iter().collect()
    }
}
