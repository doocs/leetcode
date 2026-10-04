use std::collections::HashMap;

impl Solution {
    pub fn kill_process(pid: Vec<i32>, ppid: Vec<i32>, kill: i32) -> Vec<i32> {
        let mut g: HashMap<i32, Vec<i32>> = HashMap::new();
        let n = pid.len();
        for i in 0..n {
            g.entry(ppid[i]).or_insert(Vec::new()).push(pid[i]);
        }
        let mut ans = Vec::new();
        let mut stk = vec![kill];
        while let Some(i) = stk.pop() {
            ans.push(i);
            if let Some(children) = g.get(&i) {
                for &j in children.iter().rev() {
                    stk.push(j);
                }
            }
        }
        ans
    }
}
