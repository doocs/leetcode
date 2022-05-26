use std::collections::HashSet;
use std::collections::VecDeque;

const DIRS: [[i32; 2]; 4] = [[-1, 0], [1, 0], [0, -1], [0, 1]];

impl Solution {
    pub fn cut_off_tree(forest: Vec<Vec<i32>>) -> i32 {
        let (row, col) = (forest.len() as i32, forest[0].len() as i32);

        let bfs = |start: i32, end: i32| -> i32 {
            let mut queue = VecDeque::new();
            let mut vis = HashSet::new();
            queue.push_back(start);
            vis.insert(start);
            let mut step = 0;
            while !queue.is_empty() {
                let n = queue.len();
                for _ in 0..n {
                    let state = queue.pop_front().unwrap();
                    if state == end {
                        return step;
                    }
                    for k in 0..4 {
                        let x = state / col + DIRS[k][0];
                        let y = state % col + DIRS[k][1];
                        let nxt = x * col + y;
                        if x >= 0
                            && x < row
                            && y >= 0
                            && y < col
                            && forest[x as usize][y as usize] != 0
                            && !vis.contains(&nxt)
                        {
                            queue.push_back(nxt);
                            vis.insert(nxt);
                        }
                    }
                }
                step += 1;
            }
            -1
        };

        let mut trees = Vec::new();
        for i in 0..row {
            for j in 0..col {
                let height = forest[i as usize][j as usize];
                if height > 1 {
                    trees.push((height, i * col + j));
                }
            }
        }
        trees.sort();

        let (mut ans, mut start) = (0, 0);
        for t in &trees {
            let end = t.1;
            let step = bfs(start, end);
            if step == -1 {
                return -1;
            }
            ans += step;
            start = end;
        }
        ans
    }
}
