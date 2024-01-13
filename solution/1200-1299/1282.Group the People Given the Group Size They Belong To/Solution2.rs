impl Solution {
    #[allow(dead_code)]
    pub fn group_the_people(group_sizes: Vec<i32>) -> Vec<Vec<i32>> {
        let n = group_sizes.len();
        let mut g = vec![vec![]; n + 1];
        let mut ret = vec![];

        for i in 0..n {
            g[group_sizes[i] as usize].push(i as i32);
            if g[group_sizes[i] as usize].len() == (group_sizes[i] as usize) {
                ret.push(g[group_sizes[i] as usize].clone());
                g[group_sizes[i] as usize].clear();
            }
        }

        ret
    }
}
