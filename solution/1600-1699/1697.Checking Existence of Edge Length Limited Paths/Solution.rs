impl Solution {
    #[allow(dead_code)]
    pub fn distance_limited_paths_exist(
        n: i32,
        edge_list: Vec<Vec<i32>>,
        queries: Vec<Vec<i32>>
    ) -> Vec<bool> {
        let mut disjoint_set: Vec<usize> = vec![0; n as usize];
        let mut ans_vec: Vec<bool> = vec![false; queries.len()];
        let mut q_vec: Vec<usize> = vec![0; queries.len()];

        // Initialize the set
        for i in 0..n {
            disjoint_set[i as usize] = i as usize;
        }

        // Initialize the q_vec
        for i in 0..queries.len() {
            q_vec[i] = i;
        }

        // Sort the q_vec based on the query limit, from the lowest to highest
        q_vec.sort_by(|i, j| queries[*i][2].cmp(&queries[*j][2]));

        // Sort the edge_list based on the edge weight, from the lowest to highest
        let mut edge_list = edge_list.clone();
        edge_list.sort_by(|i, j| i[2].cmp(&j[2]));

        let mut edge_idx: usize = 0;
        for q_idx in &q_vec {
            let s = queries[*q_idx][0] as usize;
            let d = queries[*q_idx][1] as usize;
            let limit = queries[*q_idx][2];
            // Construct the disjoint set
            while edge_idx < edge_list.len() && edge_list[edge_idx][2] < limit {
                Solution::union(
                    edge_list[edge_idx][0] as usize,
                    edge_list[edge_idx][1] as usize,
                    &mut disjoint_set
                );
                edge_idx += 1;
            }
            // If the parents of s & d are the same, this query should be `true`
            // Otherwise, the current query is `false`
            ans_vec[*q_idx] = Solution::check_valid(s, d, &mut disjoint_set);
        }

        ans_vec
    }

    #[allow(dead_code)]
    pub fn find(x: usize, d_set: &mut Vec<usize>) -> usize {
        if d_set[x] != x {
            d_set[x] = Solution::find(d_set[x], d_set);
        }
        return d_set[x];
    }

    #[allow(dead_code)]
    pub fn union(s: usize, d: usize, d_set: &mut Vec<usize>) {
        let p_s = Solution::find(s, d_set);
        let p_d = Solution::find(d, d_set);
        d_set[p_s] = p_d;
    }

    #[allow(dead_code)]
    pub fn check_valid(s: usize, d: usize, d_set: &mut Vec<usize>) -> bool {
        let p_s = Solution::find(s, d_set);
        let p_d = Solution::find(d, d_set);
        p_s == p_d
    }
}
