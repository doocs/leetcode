use std::collections::HashMap;

impl Solution {
    #[allow(dead_code)]
    pub fn can_cross(stones: Vec<i32>) -> bool {
        let n = stones.len();
        let mut record = vec![vec![-1; n]; n];
        let mut pos = HashMap::new();
        for (i, &s) in stones.iter().enumerate() {
            pos.insert(s, i);
        }

        Self::dfs(&mut record, 0, 0, n, &pos, &stones)
    }

    #[allow(dead_code)]
    fn dfs(
        record: &mut Vec<Vec<i32>>,
        i: usize,
        k: usize,
        n: usize,
        pos: &HashMap<i32, usize>,
        stones: &Vec<i32>
    ) -> bool {
        if i == n - 1 {
            return true;
        }

        if record[i][k] != -1 {
            return record[i][k] == 1;
        }

        let k = k as i32;
        for j in k - 1..=k + 1 {
            if
                j > 0 &&
                pos.contains_key(&(stones[i] + j)) &&
                Self::dfs(record, pos[&(stones[i] + j)], j as usize, n, pos, stones)
            {
                record[i][k as usize] = 1;
                return true;
            }
        }

        record[i][k as usize] = 0;
        false
    }
}
