impl Solution {
    fn dfs(start: usize, end: usize, max_val: i32, postorder: &Vec<i32>) -> bool {
        if start >= end {
            return true;
        }
        let root_val = postorder[end - 1];
        for i in (start..end).rev() {
            let val = postorder[i];
            if val > max_val {
                return false;
            }
            if val < root_val {
                return Self::dfs(start, i, root_val, postorder)
                    && Self::dfs(i + 1, end - 1, max_val, postorder);
            }
        }
        Self::dfs(start, end - 1, max_val, postorder)
    }

    pub fn verify_postorder(postorder: Vec<i32>) -> bool {
        Self::dfs(0, postorder.len(), i32::MAX, &postorder)
    }
}
