impl Solution {
    pub fn row_and_maximum_ones(mat: Vec<Vec<i32>>) -> Vec<i32> {
        let mut ans = vec![0, 0];
        for (i, row) in mat.iter().enumerate() {
            let cnt = row.iter().sum();
            if ans[1] < cnt {
                ans = vec![i as i32, cnt];
            }
        }
        ans
    }
}
