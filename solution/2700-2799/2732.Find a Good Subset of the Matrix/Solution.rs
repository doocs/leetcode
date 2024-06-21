use std::collections::HashMap;

impl Solution {
    pub fn good_subsetof_binary_matrix(grid: Vec<Vec<i32>>) -> Vec<i32> {
        let mut g: HashMap<i32, i32> = HashMap::new();
        for (i, row) in grid.iter().enumerate() {
            let mut mask = 0;
            for (j, &x) in row.iter().enumerate() {
                mask |= x << j;
            }
            if mask == 0 {
                return vec![i as i32];
            }
            g.insert(mask, i as i32);
        }

        for (&a, &i) in g.iter() {
            for (&b, &j) in g.iter() {
                if (a & b) == 0 {
                    return vec![i.min(j), i.max(j)];
                }
            }
        }

        vec![]
    }
}
