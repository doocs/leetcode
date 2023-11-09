use std::collections::VecDeque;

impl Solution {
    #[allow(dead_code)]
    pub fn can_finish(num_course: i32, prerequisites: Vec<Vec<i32>>) -> bool {
        let num_course = num_course as usize;
        // The graph representation
        let mut graph: Vec<Vec<i32>> = vec![vec![]; num_course];
        // Record the in degree for each node
        let mut in_degree_vec: Vec<i32> = vec![0; num_course];
        let mut q: VecDeque<usize> = VecDeque::new();
        let mut count = 0;

        // Initialize the graph & in degree vector
        for p in &prerequisites {
            let (from, to) = (p[0], p[1]);
            graph[from as usize].push(to);
            in_degree_vec[to as usize] += 1;
        }

        // Enqueue the first batch of nodes with in degree 0
        for i in 0..num_course {
            if in_degree_vec[i] == 0 {
                q.push_back(i);
            }
        }

        // Begin the traverse & update through the graph
        while !q.is_empty() {
            // Get the current node index
            let index = q.front().unwrap().clone();
            // This course can be finished
            count += 1;
            q.pop_front();
            for i in &graph[index] {
                // Update the in degree for the current node
                in_degree_vec[*i as usize] -= 1;
                // See if can be enqueued
                if in_degree_vec[*i as usize] == 0 {
                    q.push_back(*i as usize);
                }
            }
        }

        count == num_course
    }
}
