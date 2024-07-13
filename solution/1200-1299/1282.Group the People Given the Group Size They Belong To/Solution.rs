impl Solution {
    pub fn group_the_people(group_sizes: Vec<i32>) -> Vec<Vec<i32>> {
        let n: usize = group_sizes.len();
        let mut g: Vec<Vec<usize>> = vec![Vec::new(); n + 1];

        for (i, &size) in group_sizes.iter().enumerate() {
            g[size as usize].push(i);
        }

        let mut ans: Vec<Vec<i32>> = Vec::new();
        for (i, v) in g.into_iter().enumerate() {
            for j in (0..v.len()).step_by(i.max(1)) {
                ans.push(
                    v[j..(j + i).min(v.len())]
                        .iter()
                        .map(|&x| x as i32)
                        .collect(),
                );
            }
        }

        ans
    }
}
