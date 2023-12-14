impl Solution {
    pub fn find_peak_grid(mat: Vec<Vec<i32>>) -> Vec<i32> {
        let mut l: usize = 0;
        let mut r: usize = mat.len() - 1;
        while l < r {
            let mid: usize = (l + r) >> 1;
            let j: usize = mat[mid]
                .iter()
                .position(|&x| x == *mat[mid].iter().max().unwrap())
                .unwrap();
            if mat[mid][j] > mat[mid + 1][j] {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        let j: usize = mat[l]
            .iter()
            .position(|&x| x == *mat[l].iter().max().unwrap())
            .unwrap();
        vec![l as i32, j as i32]
    }
}
