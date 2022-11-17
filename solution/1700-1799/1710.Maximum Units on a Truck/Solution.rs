impl Solution {
    pub fn maximum_units(mut box_types: Vec<Vec<i32>>, truck_size: i32) -> i32 {
        box_types.sort_by(|a, b| b[1].cmp(&a[1]));
        let mut sum = 0;
        let mut ans = 0;
        for box_type in box_types.iter() {
            if sum + box_type[0] < truck_size {
                sum += box_type[0];
                ans += box_type[0] * box_type[1];
            } else {
                ans += (truck_size - sum) * box_type[1];
                break;
            }
        }
        ans
    }
}
