impl Solution {
    pub fn num_of_burgers(tomato_slices: i32, cheese_slices: i32) -> Vec<i32> {
        let k = 4 * cheese_slices - tomato_slices;
        let y = k / 2;
        let x = cheese_slices - y;
        if k % 2 != 0 || y < 0 || x < 0 {
            Vec::new()
        } else {
            vec![x, y]
        }
    }
}
