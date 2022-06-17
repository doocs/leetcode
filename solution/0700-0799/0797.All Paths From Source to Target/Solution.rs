impl Solution {
    fn dfs(i: usize, path: &mut Vec<i32>, res: &mut Vec<Vec<i32>>, graph: &Vec<Vec<i32>>) {
        path.push(i as i32);
        if i == graph.len() - 1 {
            res.push(path.clone());
        }
        for j in graph[i].iter() {
            Self::dfs(*j as usize, path, res, graph)
        }
        path.pop();
    }

    pub fn all_paths_source_target(graph: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut res = Vec::new();
        Self::dfs(0, &mut vec![], &mut res, &graph);
        res
    }
}
