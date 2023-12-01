use std::collections::VecDeque;

impl Solution {
    pub fn moves_to_stamp(stamp: String, target: String) -> Vec<i32> {
        let m = stamp.len();
        let n = target.len();

        let mut indeg: Vec<usize> = vec![m; n - m + 1];
        let mut g: Vec<Vec<usize>> = vec![Vec::new(); n];
        let mut q: VecDeque<usize> = VecDeque::new();

        for i in 0..n - m + 1 {
            for j in 0..m {
                if
                    target
                        .chars()
                        .nth(i + j)
                        .unwrap() == stamp.chars().nth(j).unwrap()
                {
                    indeg[i] -= 1;
                    if indeg[i] == 0 {
                        q.push_back(i);
                    }
                } else {
                    g[i + j].push(i);
                }
            }
        }

        let mut ans: Vec<i32> = Vec::new();
        let mut vis: Vec<bool> = vec![false; n];

        while let Some(i) = q.pop_front() {
            ans.push(i as i32);

            for j in 0..m {
                if !vis[i + j] {
                    vis[i + j] = true;

                    for &k in g[i + j].iter() {
                        indeg[k] -= 1;
                        if indeg[k] == 0 {
                            q.push_back(k);
                        }
                    }
                }
            }
        }

        if vis.iter().all(|&v| v) {
            ans.reverse();
            ans
        } else {
            Vec::new()
        }
    }
}
