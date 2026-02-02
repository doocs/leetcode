impl Solution {
    pub fn largest_square_area(bottom_left: Vec<Vec<i32>>, top_right: Vec<Vec<i32>>) -> i64 {
        let mut ans: i64 = 0;
        let n = bottom_left.len();

        for i in 0..n {
            let x1 = bottom_left[i][0];
            let y1 = bottom_left[i][1];
            let x2 = top_right[i][0];
            let y2 = top_right[i][1];

            for j in (i + 1)..n {
                let x3 = bottom_left[j][0];
                let y3 = bottom_left[j][1];
                let x4 = top_right[j][0];
                let y4 = top_right[j][1];

                let w = (x2.min(x4) - x1.max(x3)) as i64;
                let h = (y2.min(y4) - y1.max(y3)) as i64;
                let e = w.min(h);

                if e > 0 {
                    ans = ans.max(e * e);
                }
            }
        }

        ans
    }
}
