impl Solution {
    pub fn can_reach_corner(x_corner: i32, y_corner: i32, circles: Vec<Vec<i32>>) -> bool {
        let n = circles.len();
        let mut vis = vec![false; n];

        let in_circle = |x: i64, y: i64, cx: i64, cy: i64, r: i64| -> bool {
            (x - cx) * (x - cx) + (y - cy) * (y - cy) <= r * r
        };

        let cross_left_top = |cx: i64, cy: i64, r: i64| -> bool {
            let a = cx.abs() <= r && (cy >= 0 && cy <= y_corner as i64);
            let b = (cy - y_corner as i64).abs() <= r && (cx >= 0 && cx <= x_corner as i64);
            a || b
        };

        let cross_right_bottom = |cx: i64, cy: i64, r: i64| -> bool {
            let a = (cx - x_corner as i64).abs() <= r && (cy >= 0 && cy <= y_corner as i64);
            let b = cy.abs() <= r && (cx >= 0 && cx <= x_corner as i64);
            a || b
        };
        fn dfs(
            circles: &Vec<Vec<i32>>,
            vis: &mut Vec<bool>,
            i: usize,
            x_corner: i32,
            y_corner: i32,
            cross_right_bottom: &dyn Fn(i64, i64, i64) -> bool,
        ) -> bool {
            let c = &circles[i];
            let (x1, y1, r1) = (c[0] as i64, c[1] as i64, c[2] as i64);

            if cross_right_bottom(x1, y1, r1) {
                return true;
            }

            vis[i] = true;

            for j in 0..circles.len() {
                if vis[j] {
                    continue;
                }

                let c2 = &circles[j];
                let (x2, y2, r2) = (c2[0] as i64, c2[1] as i64, c2[2] as i64);

                if (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > (r1 + r2) * (r1 + r2) {
                    continue;
                }

                if x1 * r2 + x2 * r1 < (r1 + r2) * x_corner as i64
                    && y1 * r2 + y2 * r1 < (r1 + r2) * y_corner as i64
                    && dfs(circles, vis, j, x_corner, y_corner, cross_right_bottom)
                {
                    return true;
                }
            }
            false
        }

        for i in 0..n {
            let c = &circles[i];
            let (x, y, r) = (c[0] as i64, c[1] as i64, c[2] as i64);

            if in_circle(0, 0, x, y, r) || in_circle(x_corner as i64, y_corner as i64, x, y, r) {
                return false;
            }

            if !vis[i]
                && cross_left_top(x, y, r)
                && dfs(
                    &circles,
                    &mut vis,
                    i,
                    x_corner,
                    y_corner,
                    &cross_right_bottom,
                )
            {
                return false;
            }
        }

        true
    }
}
