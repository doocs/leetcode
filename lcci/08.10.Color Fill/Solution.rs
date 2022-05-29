impl Solution {
    fn dfs(i: usize, j: usize, target: i32, new_color: i32, image: &mut Vec<Vec<i32>>) {
        if image[i][j] != target {
            return;
        }
        image[i][j] = new_color;
        if i != 0 {
            Self::dfs(i - 1, j, target, new_color, image);
        }
        if j != 0 {
            Self::dfs(i, j - 1, target, new_color, image);
        }
        if i + 1 != image.len() {
            Self::dfs(i + 1, j, target, new_color, image);
        }
        if j + 1 != image[0].len() {
            Self::dfs(i, j + 1, target, new_color, image);
        }
    }

    pub fn flood_fill(mut image: Vec<Vec<i32>>, sr: i32, sc: i32, new_color: i32) -> Vec<Vec<i32>> {
        let (sr, sc) = (sr as usize, sc as usize);
        let target = image[sr][sc];
        if target == new_color {
            return image;
        }
        Self::dfs(sr, sc, target, new_color, &mut image);
        image
    }
}
