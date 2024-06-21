use std::collections::{HashSet, VecDeque};

impl Solution {
    pub fn min_mutation(start_gene: String, end_gene: String, bank: Vec<String>) -> i32 {
        let mut q = VecDeque::new();
        q.push_back((start_gene.clone(), 0));
        let mut vis = HashSet::new();
        vis.insert(start_gene);

        while let Some((gene, depth)) = q.pop_front() {
            if gene == end_gene {
                return depth;
            }
            for next in &bank {
                let mut c = 2;
                for k in 0..8 {
                    if gene.as_bytes()[k] != next.as_bytes()[k] {
                        c -= 1;
                    }
                    if c == 0 {
                        break;
                    }
                }
                if c > 0 && !vis.contains(next) {
                    vis.insert(next.clone());
                    q.push_back((next.clone(), depth + 1));
                }
            }
        }
        -1
    }
}
