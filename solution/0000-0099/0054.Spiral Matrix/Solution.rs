impl Solution {
    pub fn spiral_order(matrix: Vec<Vec<i32>>) -> Vec<i32> {
        let mut x1 = 0;
        let mut y1 = 0;
        let mut x2 = matrix.len() - 1;
        let mut y2 = matrix[0].len() - 1;
        let mut result = vec![];

        while x1 <= x2 && y1 <= y2 {
            for j in y1..=y2 {
                result.push(matrix[x1][j]);
            }
            for i in x1 + 1..=x2 {
                result.push(matrix[i][y2]);
            }
            if x1 < x2 && y1 < y2 {
                for j in (y1..y2).rev() {
                    result.push(matrix[x2][j]);
                }
                for i in (x1 + 1..x2).rev() {
                    result.push(matrix[i][y1]);
                }
            }
            x1 += 1;
            y1 += 1;
            if x2 != 0 {
                x2 -= 1;
            }
            if y2 != 0 {
                y2 -= 1;
            }
        }
        return result;
    }
}
