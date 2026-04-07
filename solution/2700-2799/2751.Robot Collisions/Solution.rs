impl Solution {
    pub fn survived_robots_healths(
        positions: Vec<i32>,
        mut healths: Vec<i32>,
        directions: String
    ) -> Vec<i32> {
        let n = positions.len();
        let mut idx: Vec<usize> = (0..n).collect();

        idx.sort_by_key(|&i| positions[i]);

        let dirs = directions.as_bytes();
        let mut stk: Vec<usize> = Vec::new();

        for &i in &idx {
            if dirs[i] == b'R' {
                stk.push(i);
                continue;
            }

            while let Some(&j) = stk.last() {
                if healths[i] == 0 {
                    break;
                }

                if healths[j] > healths[i] {
                    healths[j] -= 1;
                    healths[i] = 0;
                    break;
                } else if healths[j] < healths[i] {
                    healths[i] -= 1;
                    healths[j] = 0;
                    stk.pop();
                } else {
                    healths[i] = 0;
                    healths[j] = 0;
                    stk.pop();
                    break;
                }
            }
        }

        healths.into_iter().filter(|&h| h > 0).collect()
    }
}
