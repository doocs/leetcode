use std::collections::{HashSet, VecDeque};

const BOUNDARY: i32 = 1_000_000;
const MAX: usize = 20000;

impl Solution {
    pub fn is_escape_possible(blocked: Vec<Vec<i32>>, source: Vec<i32>, target: Vec<i32>) -> bool {
        let mut block = HashSet::with_capacity(blocked.len());
        for b in blocked.iter() {
            block.insert((b[0], b[1]));
        }
        bfs(&block, &source, &target) && bfs(&block, &target, &source)
    }
}

fn bfs(block: &HashSet<(i32, i32)>, source: &Vec<i32>, target: &Vec<i32>) -> bool {
    let dir = vec![(-1, 0), (1, 0), (0, -1), (0, 1)];

    let mut queue = VecDeque::new();
    let mut vis = HashSet::new();
    queue.push_back((source[0], source[1]));
    vis.insert((source[0], source[1]));

    while !queue.is_empty() && vis.len() < MAX {
        let (x, y) = queue.pop_front().unwrap();
        if x == target[0] && y == target[1] {
            return true;
        }
        for (dx, dy) in dir.iter() {
            let (nx, ny) = (x + dx, y + dy);
            if nx < 0
                || nx >= BOUNDARY
                || ny < 0
                || ny >= BOUNDARY
                || vis.contains(&(nx, ny))
                || block.contains(&(nx, ny))
            {
                continue;
            }
            queue.push_back((nx, ny));
            vis.insert((nx, ny));
        }
    }

    vis.len() >= MAX
}
