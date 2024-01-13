use std::collections::VecDeque;
use std::collections::HashMap;

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
        if x == 0 && y == 0 {
            return 0;
        }
        // Otherwise, let's shift <X, Y> from [-300, 300] -> [0, 600]
        let x = x + 300;
        let y = y + 300;
        let mut ret = -1;
        // Initialize the two hash map, used to track if a node has been visited
        let mut map_to: HashMap<i32, i32> = HashMap::new();
        let mut map_from: HashMap<i32, i32> = HashMap::new();
        // Input the original status
        map_to.insert(601 * 300 + 300, 0);
        map_from.insert(601 * x + y, 0);
        let mut q_to: VecDeque<(i32, i32)> = VecDeque::new();
        let mut q_from: VecDeque<(i32, i32)> = VecDeque::new();
        // Initialize the two queue
        q_to.push_back((300, 300));
        q_from.push_back((x, y));

        while !q_to.is_empty() && !q_from.is_empty() {
            let step = if q_to.len() < q_from.len() {
                Self::extend(&mut map_to, &mut map_from, &mut q_to)
            } else {
                Self::extend(&mut map_from, &mut map_to, &mut q_from)
            };
            if step != -1 {
                ret = step;
                break;
            }
        }

        ret
    }

    #[allow(dead_code)]
    fn extend(
        map_to: &mut HashMap<i32, i32>,
        map_from: &mut HashMap<i32, i32>,
        cur_q: &mut VecDeque<(i32, i32)>
    ) -> i32 {
        let n = cur_q.len();
        for _ in 0..n {
            let (i, j) = cur_q.front().unwrap().clone();
            cur_q.pop_front();
            // The cur_step here must exist
            let cur_step = map_to
                .get(&(601 * i + j))
                .unwrap()
                .clone();
            for (dx, dy) in DIR {
                let x = i + dx;
                let y = j + dy;
                // Check if this node has been visited
                if map_to.contains_key(&(601 * x + y)) {
                    // Just ignore this node
                    continue;
                }
                // Check if this node has been visited by the other side
                if map_from.contains_key(&(601 * x + y)) {
                    // We found the node
                    return (
                        cur_step +
                        1 +
                        map_from
                            .get(&(601 * x + y))
                            .unwrap()
                            .clone()
                    );
                }
                // Otherwise, update map_to and push the new node to queue
                map_to.insert(601 * x + y, cur_step + 1);
                cur_q.push_back((x, y));
            }
        }
        -1
    }
}
