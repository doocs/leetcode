impl Solution {
    pub fn image_smoother(img: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let m = img.len();
        let n = img[0].len();
        let locations = [
            [-1, -1],
            [-1, 0],
            [-1, 1],
            [0, -1],
            [0, 0],
            [0, 1],
            [1, -1],
            [1, 0],
            [1, 1],
        ];

        let mut res = vec![];
        for i in 0..m {
            res.push(vec![]);
            for j in 0..n {
                let mut sum = 0;
                let mut count = 0;
                for [y, x] in locations.iter() {
                    let i = i as i32 + y;
                    let j = j as i32 + x;
                    if i < 0 || i == m as i32 || j < 0 || j == n as i32 {
                        continue;
                    }
                    count += 1;
                    sum += img[i as usize][j as usize];
                }
                res[i].push(sum / count);
            }
        }
        res
    }
}
