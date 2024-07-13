impl Solution {
    pub fn row_and_maximum_ones(mat: Vec<Vec<i32>>) -> Vec<i32> {
        let mut ans = vec![0, 0];

        for (i, row) in mat.iter().enumerate() {
            let cnt = row.iter().filter(|&v| *v == 1).count() as i32;
            if ans[1] < cnt {
                ans[0] = i as i32;
                ans[1] = cnt;
            }
        }

        ans
    }
}
