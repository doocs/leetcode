impl Solution {
    pub fn assign_tasks(servers: Vec<i32>, tasks: Vec<i32>) -> Vec<i32> {
        use std::cmp::Reverse;
        use std::collections::BinaryHeap;

        let mut idle: BinaryHeap<Reverse<(i32, usize)>> = BinaryHeap::new();
        for (i, &x) in servers.iter().enumerate() {
            idle.push(Reverse((x, i)));
        }
        let mut busy: BinaryHeap<Reverse<(i64, i32, usize)>> = BinaryHeap::new();
        let mut ans = vec![0; tasks.len()];
        for (j, &t) in tasks.iter().enumerate() {
            let t = i64::from(t);
            while let Some(&Reverse((w, s, i))) = busy.peek() {
                if w > j as i64 {
                    break;
                }
                busy.pop();
                idle.push(Reverse((s, i)));
            }
            if let Some(Reverse((s, i))) = idle.pop() {
                ans[j] = i as i32;
                busy.push(Reverse((j as i64 + t, s, i)));
            } else {
                let Reverse((w, s, i)) = busy.pop().unwrap();
                ans[j] = i as i32;
                busy.push(Reverse((w + t, s, i)));
            }
        }
        ans
    }
}
