impl Solution {
    #[allow(dead_code)]
    pub fn is_bipartite(graph: Vec<Vec<i32>>) -> bool {
        let mut graph = graph;
        let n = graph.len();
        let mut color_vec: Vec<usize> = vec![0; n];
        for i in 0..n {
            if color_vec[i] == 0 && !Self::traverse(i, 1, &mut color_vec, &mut graph) {
                return false;
            }
        }
        true
    }

    #[allow(dead_code)]
    fn traverse(
        v: usize,
        color: usize,
        color_vec: &mut Vec<usize>,
        graph: &mut Vec<Vec<i32>>
    ) -> bool {
        color_vec[v] = color;
        for n in graph[v].clone() {
            if color_vec[n as usize] == 0 {
                // This node hasn't been colored
                if !Self::traverse(n as usize, 3 - color, color_vec, graph) {
                    return false;
                }
            } else if color_vec[n as usize] == color {
                // The color is the same
                return false;
            }
        }
        true
    }
}
