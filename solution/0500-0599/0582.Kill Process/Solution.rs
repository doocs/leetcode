use std::collections::HashMap;

impl Solution {
    pub fn kill_process(pid: Vec<i32>, ppid: Vec<i32>, kill: i32) -> Vec<i32> {
        let mut g: HashMap<i32, Vec<i32>> = HashMap::new();
        let mut ans: Vec<i32> = Vec::new();

        let n = pid.len();
        for i in 0..n {
            g.entry(ppid[i]).or_insert(Vec::new()).push(pid[i]);
        }

        Self::dfs(&mut ans, &g, kill);
        ans
    }

    fn dfs(ans: &mut Vec<i32>, g: &HashMap<i32, Vec<i32>>, i: i32) {
        ans.push(i);
        if let Some(children) = g.get(&i) {
            for &j in children {
                Self::dfs(ans, g, j);
            }
        }
    }
}
