/**
 * Your NumMatrix object will be instantiated and called as such:
 * let obj = NumMatrix::new(matrix);
 * let ret_1: i32 = obj.sum_region(row1, col1, row2, col2);
 */

struct NumMatrix {
    // Of size (N + 1) * (M + 1)
    prefix_vec: Vec<Vec<i32>>,
    n: usize,
    m: usize,
    is_initialized: bool,
    ref_vec: Vec<Vec<i32>>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NumMatrix {
    fn new(matrix: Vec<Vec<i32>>) -> Self {
        NumMatrix {
            prefix_vec: vec![vec![0; matrix[0].len() + 1]; matrix.len() + 1],
            n: matrix.len(),
            m: matrix[0].len(),
            is_initialized: false,
            ref_vec: matrix,
        }
    }

    fn sum_region(&mut self, row1: i32, col1: i32, row2: i32, col2: i32) -> i32 {
        if !self.is_initialized {
            self.initialize_prefix_vec();
        }
        // Since i32 will let `rustc` complain, just make it happy
        let row1: usize = row1 as usize;
        let col1: usize = col1 as usize;
        let row2: usize = row2 as usize;
        let col2: usize = col2 as usize;
        // Return the value in O(1)
        self.prefix_vec[row2 + 1][col2 + 1]
            - self.prefix_vec[row2 + 1][col1]
            - self.prefix_vec[row1][col2 + 1]
            + self.prefix_vec[row1][col1]
    }

    fn initialize_prefix_vec(&mut self) {
        // Initialize the prefix sum vector
        for i in 0..self.n {
            for j in 0..self.m {
                self.prefix_vec[i + 1][j + 1] =
                    self.prefix_vec[i][j + 1] + self.prefix_vec[i + 1][j] - self.prefix_vec[i][j]
                        + self.ref_vec[i][j];
            }
        }
        self.is_initialized = true;
    }
}
