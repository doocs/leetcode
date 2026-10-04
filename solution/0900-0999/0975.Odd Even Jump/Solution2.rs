use std::collections::BTreeMap;

impl Solution {
    pub fn odd_even_jumps(arr: Vec<i32>) -> i32 {
        let n = arr.len();
        let mut g = vec![[-1, -1]; n];
        let mut tm: BTreeMap<i32, usize> = BTreeMap::new();

        for i in (0..n).rev() {
            if let Some((_, &v)) = tm.range(arr[i]..).next() {
                g[i][1] = v as i32;
            }
            if let Some((_, &v)) = tm.range(..=arr[i]).next_back() {
                g[i][0] = v as i32;
            }
            tm.insert(arr[i], i);
        }

        let mut f = vec![[false, false]; n];
        f[n - 1] = [true, true];
        for i in (0..n - 1).rev() {
            for k in 0..2 {
                let j = g[i][k];
                if j != -1 {
                    f[i][k] = f[j as usize][k ^ 1];
                }
            }
        }

        f.iter().filter(|row| row[1]).count() as i32
    }
}
