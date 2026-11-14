use std::collections::HashMap;

impl Solution {
    pub fn longest_special_path(
        edges: Vec<Vec<i32>>,
        nums: Vec<i32>,
    ) -> Vec<i32> {
        let n = nums.len();

        struct Frame {
            node: usize,
            parent: usize,
            left: usize,
            depth: usize,
            weight: i64,
            old: usize,
            exit: bool,
        }

        let mut graph = vec![Vec::<(usize, i64)>::new(); n];

        for e in &edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            let w = e[2] as i64;

            graph[u].push((v, w));
            graph[v].push((u, w));
        }

        let mut last = HashMap::<i32, usize>::new();
        let mut prefix = vec![0i64; n + 1];

        let mut stack = vec![Frame {
            node: 0,
            parent: usize::MAX,
            left: 0,
            depth: 0,
            weight: 0,
            old: 0,
            exit: false,
        }];

        let mut best_length = 0i64;
        let mut best_nodes = 1usize;

        while let Some(f) = stack.pop() {
            let u = f.node;

            if f.exit {
                if f.old == 0 {
                    last.remove(&nums[u]);
                } else {
                    last.insert(nums[u], f.old);
                }
                continue;
            }

            prefix[f.depth + 1] = prefix[f.depth] + f.weight;

            let old = *last.get(&nums[u]).unwrap_or(&0);
            let left = f.left.max(old);

            last.insert(nums[u], f.depth + 1);

            let length = prefix[f.depth + 1] - prefix[left + 1];
            let nodes = f.depth + 1 - left;

            if length > best_length
                || (length == best_length && nodes < best_nodes)
            {
                best_length = length;
                best_nodes = nodes;
            }

            stack.push(Frame {
                node: u,
                parent: f.parent,
                left,
                depth: f.depth,
                weight: 0,
                old,
                exit: true,
            });

            for &(v, w) in graph[u].iter().rev() {
                if v != f.parent {
                    stack.push(Frame {
                        node: v,
                        parent: u,
                        left,
                        depth: f.depth + 1,
                        weight: w,
                        old: 0,
                        exit: false,
                    });
                }
            }
        }

        vec![best_length as i32, best_nodes as i32]
    }
}
