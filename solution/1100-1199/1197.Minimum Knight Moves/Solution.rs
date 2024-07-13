use std::collections::VecDeque;

const DIR: [(i32, i32); 8] = [
    (-2, 1),
    (2, 1),
    (-1, 2),
    (1, 2),
    (2, -1),
    (-2, -1),
    (1, -2),
    (-1, -2),
];

impl Solution {
    #[allow(dead_code)]
    pub fn min_knight_moves(x: i32, y: i32) -> i32 {
        // The original x, y are from [-300, 300]
        // Let's shift them to [0, 600]
        let x: i32 = x + 300;
        let y: i32 = y + 300;
        let mut ret = -1;
        let mut vis: Vec<Vec<bool>> = vec![vec![false; 618]; 618];
        // <X, Y, Current Steps>
        let mut q: VecDeque<(i32, i32, i32)> = VecDeque::new();

        q.push_back((300, 300, 0));

        while !q.is_empty() {
            let (i, j, s) = q.front().unwrap().clone();
            q.pop_front();
            if i == x && j == y {
                ret = s;
                break;
            }
            Self::enqueue(&mut vis, &mut q, i, j, s);
        }

        ret
    }

    #[allow(dead_code)]
    fn enqueue(
        vis: &mut Vec<Vec<bool>>,
        q: &mut VecDeque<(i32, i32, i32)>,
        i: i32,
        j: i32,
        cur_step: i32,
    ) {
        let next_step = cur_step + 1;
        for (dx, dy) in DIR {
            let x = i + dx;
            let y = j + dy;
            if Self::check_bounds(x, y) || vis[x as usize][y as usize] {
                // This <X, Y> pair is either out of bound, or has been visited before
                // Just ignore this pair
                continue;
            }
            // Otherwise, add the pair to the queue
            // Also remember to update the vis vector
            vis[x as usize][y as usize] = true;
            q.push_back((x, y, next_step));
        }
    }

    #[allow(dead_code)]
    fn check_bounds(i: i32, j: i32) -> bool {
        i < 0 || i > 600 || j < 0 || j > 600
    }
}
