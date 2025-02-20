use std::collections::VecDeque;

impl Solution {
    pub fn flood_fill(mut image: Vec<Vec<i32>>, sr: i32, sc: i32, color: i32) -> Vec<Vec<i32>> {
        let m = image.len();
        let n = image[0].len();
        let (sr, sc) = (sr as usize, sc as usize);

        if image[sr][sc] == color {
            return image;
        }

        let oc = image[sr][sc];
        image[sr][sc] = color;

        let mut q = VecDeque::new();
        q.push_back((sr, sc));

        let dirs = [-1, 0, 1, 0, -1];

        while let Some((i, j)) = q.pop_front() {
            for k in 0..4 {
                let x = i as isize + dirs[k] as isize;
                let y = j as isize + dirs[k + 1] as isize;

                if x >= 0 && x < m as isize && y >= 0 && y < n as isize {
                    let (x, y) = (x as usize, y as usize);
                    if image[x][y] == oc {
                        q.push_back((x, y));
                        image[x][y] = color;
                    }
                }
            }
        }

        image
    }
}
