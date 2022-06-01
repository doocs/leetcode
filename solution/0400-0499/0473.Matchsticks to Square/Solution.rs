impl Solution {
    pub fn makesquare(matchsticks: Vec<i32>) -> bool {
        let mut matchsticks = matchsticks;

        fn dfs(matchsticks: &Vec<i32>, edges: &mut [i32; 4], u: usize, x: i32) -> bool {
            if u == matchsticks.len() {
                return true;
            }
            for i in 0..4 {
                if i > 0 && edges[i - 1] == edges[i] {
                    continue;
                }
                edges[i] += matchsticks[u];
                if edges[i] <= x && dfs(matchsticks, edges, u + 1, x) {
                    return true;
                }
                edges[i] -= matchsticks[u];
            }
            false
        }

        let sum: i32 = matchsticks.iter().sum();
        if sum % 4 != 0 {
            return false;
        }
        matchsticks.sort_by(|x, y| y.cmp(x));
        let mut edges = [0; 4];

        dfs(&matchsticks, &mut edges, 0, sum / 4)
    }
}
