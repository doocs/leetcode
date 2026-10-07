impl Solution {
    pub fn verify_postorder(postorder: Vec<i32>) -> bool {
        let n = postorder.len() as i32;
        let mut stk = vec![(0, n - 1)];
        while let Some((l, r)) = stk.pop() {
            if l >= r {
                continue;
            }
            let v = postorder[r as usize];
            let mut i = l;
            while i < r && postorder[i as usize] < v {
                i += 1;
            }
            for j in i..r {
                if postorder[j as usize] < v {
                    return false;
                }
            }
            stk.push((i, r - 1));
            stk.push((l, i - 1));
        }
        true
    }
}
