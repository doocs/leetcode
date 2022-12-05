impl Solution {
    fn dfs(i: usize, mut ans: i32, g: &Vec<Vec<(usize, i32)>>, vis: &mut Vec<bool>) -> i32 {
        if vis[i] {
            return ans;
        }
        vis[i] = true;
        for (j, v) in g[i].iter() {
            ans = ans.min(*v.min(&Self::dfs(*j, ans, g, vis)));
        }
        ans
    }

    pub fn min_score(n: i32, roads: Vec<Vec<i32>>) -> i32 {
        let n = n as usize;
        let mut vis = vec![false; n + 1];
        let mut g = vec![Vec::new(); n + 1];
        for road in roads.iter() {
            let a = road[0] as usize;
            let b = road[1] as usize;
            let v = road[2];
            g[a].push((b, v));
            g[b].push((a, v));
        }
        Self::dfs(1, i32::MAX, &g, &mut vis)
    }
}
