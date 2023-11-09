impl Solution {
    #[allow(dead_code)]
    pub fn is_interleave(s1: String, s2: String, s3: String) -> bool {
        let n = s1.len();
        let m = s2.len();

        if s1.len() + s2.len() != s3.len() {
            return false;
        }

        let mut record = vec![vec![-1; m + 1]; n + 1];

        Self::dfs(
            &mut record,
            n,
            m,
            0,
            0,
            &s1.chars().collect(),
            &s2.chars().collect(),
            &s3.chars().collect()
        )
    }

    #[allow(dead_code)]
    fn dfs(
        record: &mut Vec<Vec<i32>>,
        n: usize,
        m: usize,
        i: usize,
        j: usize,
        s1: &Vec<char>,
        s2: &Vec<char>,
        s3: &Vec<char>
    ) -> bool {
        if i >= n && j >= m {
            return true;
        }

        if record[i][j] != -1 {
            return record[i][j] == 1;
        }

        // Set the initial value
        record[i][j] = 0;
        let k = i + j;

        // Let's try `s1` first
        if i < n && s1[i] == s3[k] && Self::dfs(record, n, m, i + 1, j, s1, s2, s3) {
            record[i][j] = 1;
        }

        // If the first approach does not succeed, let's then try `s2`
        if
            record[i][j] == 0 &&
            j < m &&
            s2[j] == s3[k] &&
            Self::dfs(record, n, m, i, j + 1, s1, s2, s3)
        {
            record[i][j] = 1;
        }

        record[i][j] == 1
    }
}
