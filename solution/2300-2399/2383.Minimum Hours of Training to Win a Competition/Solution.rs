impl Solution {
    pub fn min_number_of_hours(
        mut x: i32,
        mut y: i32,
        energy: Vec<i32>,
        experience: Vec<i32>,
    ) -> i32 {
        let mut ans = 0;

        for (&dx, &dy) in energy.iter().zip(experience.iter()) {
            if x <= dx {
                ans += dx + 1 - x;
                x = dx + 1;
            }
            if y <= dy {
                ans += dy + 1 - y;
                y = dy + 1;
            }
            x -= dx;
            y += dy;
        }

        ans
    }
}
