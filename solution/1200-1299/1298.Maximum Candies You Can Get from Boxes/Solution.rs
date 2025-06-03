use std::collections::{HashSet, VecDeque};

impl Solution {
    pub fn max_candies(
        mut status: Vec<i32>,
        candies: Vec<i32>,
        keys: Vec<Vec<i32>>,
        contained_boxes: Vec<Vec<i32>>,
        initial_boxes: Vec<i32>,
    ) -> i32 {
        let mut q: VecDeque<i32> = VecDeque::new();
        let mut has: HashSet<i32> = HashSet::new();
        let mut took: HashSet<i32> = HashSet::new();
        let mut ans = 0;

        for &box_ in &initial_boxes {
            has.insert(box_);
            if status[box_ as usize] == 1 {
                q.push_back(box_);
                took.insert(box_);
                ans += candies[box_ as usize];
            }
        }

        while let Some(box_) = q.pop_front() {
            for &k in &keys[box_ as usize] {
                if status[k as usize] == 0 {
                    status[k as usize] = 1;
                    if has.contains(&k) && !took.contains(&k) {
                        q.push_back(k);
                        took.insert(k);
                        ans += candies[k as usize];
                    }
                }
            }

            for &b in &contained_boxes[box_ as usize] {
                has.insert(b);
                if status[b as usize] == 1 && !took.contains(&b) {
                    q.push_back(b);
                    took.insert(b);
                    ans += candies[b as usize];
                }
            }
        }

        ans
    }
}
