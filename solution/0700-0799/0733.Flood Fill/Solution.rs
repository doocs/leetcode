impl Solution {
    pub fn flood_fill(mut image: Vec<Vec<i32>>, sr: i32, sc: i32, color: i32) -> Vec<Vec<i32>> {
        let m = image.len();
        let n = image[0].len();
        let sr = sr as usize;
        let sc = sc as usize;

        let oc = image[sr][sc];
        if oc == color {
            return image;
        }
        let dirs = [-1, 0, 1, 0, -1];
        fn dfs(
            image: &mut Vec<Vec<i32>>,
            i: usize,
            j: usize,
            oc: i32,
            color: i32,
            m: usize,
            n: usize,
            dirs: &[i32; 5],
        ) {
            image[i][j] = color;
            for k in 0..4 {
                let x = i as isize + dirs[k] as isize;
                let y = j as isize + dirs[k + 1] as isize;
                if x >= 0 && x < m as isize && y >= 0 && y < n as isize {
                    let x = x as usize;
                    let y = y as usize;
                    if image[x][y] == oc {
                        dfs(image, x, y, oc, color, m, n, dirs);
                    }
                }
            }
        }

        dfs(&mut image, sr, sc, oc, color, m, n, &dirs);
        image
    }
}
