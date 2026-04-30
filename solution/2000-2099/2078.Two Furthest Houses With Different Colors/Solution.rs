impl Solution {
    pub fn max_distance(colors: Vec<i32>) -> i32 {
        let n = colors.len();
        if colors[0] != colors[n - 1] {
            return (n - 1) as i32;
        }
        let mut i = 1;
        while colors[i] == colors[0] {
            i += 1;
        }
        let mut j = n - 2;
        while colors[j] == colors[0] {
            j -= 1;
        }
        std::cmp::max((n - i - 1) as i32, j as i32)
    }
}
