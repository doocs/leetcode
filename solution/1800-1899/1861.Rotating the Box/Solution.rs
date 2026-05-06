use std::collections::VecDeque;

impl Solution {
    pub fn rotate_the_box(box_grid: Vec<Vec<char>>) -> Vec<Vec<char>> {
        let m: usize = box_grid.len();
        let n: usize = box_grid[0].len();
        let mut ans: Vec<Vec<char>> = vec![vec![' '; m]; n];

        for i in 0..m {
            for j in 0..n {
                ans[j][m - i - 1] = box_grid[i][j];
            }
        }

        for j in 0..m {
            let mut q: VecDeque<usize> = VecDeque::new();
            for i in (0..n).rev() {
                if ans[i][j] == '*' {
                    q.clear();
                } else if ans[i][j] == '.' {
                    q.push_back(i);
                } else if !q.is_empty() {
                    let t = q.pop_front().unwrap();
                    ans[t][j] = '#';
                    ans[i][j] = '.';
                    q.push_back(i);
                }
            }
        }

        ans
    }
}
