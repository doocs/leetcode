impl Solution {
    fn dfs(i: usize, count: i32, candidates: &Vec<i32>, t: &mut Vec<i32>, res: &mut Vec<Vec<i32>>) {
        if count < 0 {
            return;
        }
        if count == 0 {
            res.push(t.clone());
            return;
        }
        for j in i..candidates.len() {
            if j > i && candidates[j] == candidates[j - 1] {
                continue;
            }
            let num = candidates[j];
            t.push(num);
            Self::dfs(j + 1, count - num, candidates, t, res);
            t.pop();
        }
    }

    pub fn combination_sum2(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        candidates.sort();
        let mut res = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut res);
        res
    }
}
