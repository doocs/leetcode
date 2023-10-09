impl Solution {
    pub fn find_order(num_courses: i32, prerequisites: Vec<Vec<i32>>) -> Vec<i32> {
        let n = num_courses as usize;
        let mut adjacency = vec![vec![]; n];
        let mut entry = vec![0; n];
        // init
        for iter in prerequisites.iter() {
            let (a, b) = (iter[0], iter[1]);
            adjacency[b as usize].push(a);
            entry[a as usize] += 1;
        }
        // construct deque & reslut
        let mut deque = std::collections::VecDeque::new();
        for index in 0..n {
            if entry[index] == 0 {
                deque.push_back(index);
            }
        }
        let mut result = vec![];
        // bfs
        while !deque.is_empty() {
            let head = deque.pop_front().unwrap();
            result.push(head as i32);
            // update degree of entry
            for &out_entry in adjacency[head].iter() {
                entry[out_entry as usize] -= 1;
                if entry[out_entry as usize] == 0 {
                    deque.push_back(out_entry as usize);
                }
            }
        }
        if result.len() == n {
            result
        } else {
            vec![]
        }
    }
}
