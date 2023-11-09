impl Solution {
    #[allow(dead_code)]
    pub fn combination_sum3(k: i32, n: i32) -> Vec<Vec<i32>> {
        let mut ret = Vec::new();
        let mut candidates = (1..=9).collect();
        let mut cur_vec = Vec::new();
        Self::dfs(n, k, 0, 0, &mut cur_vec, &mut candidates, &mut ret);
        ret
    }

    #[allow(dead_code)]
    fn dfs(
        target: i32,
        length: i32,
        cur_index: usize,
        cur_sum: i32,
        cur_vec: &mut Vec<i32>,
        candidates: &Vec<i32>,
        ans: &mut Vec<Vec<i32>>
    ) {
        if cur_sum > target || cur_vec.len() > (length as usize) {
            // No answer for this
            return;
        }
        if cur_sum == target && cur_vec.len() == (length as usize) {
            // We get an answer
            ans.push(cur_vec.clone());
            return;
        }
        for i in cur_index..candidates.len() {
            cur_vec.push(candidates[i]);
            Self::dfs(target, length, i + 1, cur_sum + candidates[i], cur_vec, candidates, ans);
            cur_vec.pop().unwrap();
        }
    }
}
