impl Solution {
    pub fn categorize_box(length: i32, width: i32, height: i32, mass: i32) -> String {
        let v = (length as i64) * (width as i64) * (height as i64);
        let mut i = 0;

        if length >= 10000 || width >= 10000 || height >= 10000 || v >= 1000000000 {
            i |= 1;
        }

        if mass >= 100 {
            i |= 2;
        }

        let d = vec!["Neither", "Bulky", "Heavy", "Both"];
        d[i].to_string()
    }
}
