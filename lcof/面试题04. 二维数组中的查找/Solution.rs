impl Solution {
    pub fn find_number_in2_d_array(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let len_y = matrix.len();
        if len_y == 0 {
            return false;
        }
        let len_x = matrix[0].len();
        if len_x == 0 {
            return false;
        }

        let mut x = len_x - 1;
        let mut y = 0;
        while y < len_y {
            match target.cmp(&matrix[y][x]) {
                std::cmp::Ordering::Greater => y += 1,
                std::cmp::Ordering::Equal => return true,
                std::cmp::Ordering::Less => match x {
                    0 => return false,
                    _ => x -= 1,
                },
            }
        }
        false
    }
}
