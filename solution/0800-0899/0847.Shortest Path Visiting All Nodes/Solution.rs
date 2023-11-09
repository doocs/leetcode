use std::collections::VecDeque;

impl Solution {
    #[allow(dead_code)]
    pub fn shortest_path_length(graph: Vec<Vec<i32>>) -> i32 {
        let n = graph.len();
        let mut vis = vec![vec![false; 1 << n]; n];
        let mut q = VecDeque::new();

        // Initialize the queue
        for i in 0..n {
            q.push_back(((i, 1 << i), 0));
            vis[i][1 << i] = true;
        }

        // Begin BFS
        while !q.is_empty() {
            let ((i, st), count) = q.pop_front().unwrap();
            if st == (1 << n) - 1 {
                return count;
            }
            // If the path has not been visited
            for j in &graph[i] {
                let nst = st | (1 << *j);
                if !vis[*j as usize][nst] {
                    q.push_back(((*j as usize, nst), count + 1));
                    vis[*j as usize][nst] = true;
                }
            }
        }

        -1
    }
}
