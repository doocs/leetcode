impl Solution {
    pub fn categorize_box(length: i32, width: i32, height: i32, mass: i32) -> String {
        let v = length * width * height;
        let bulky =
            length >= 10000 ||
            width >= 10000 ||
            height >= 10000 ||
            (length as i64) * (width as i64) * (height as i64) >= 1000000000;

        let heavy = mass >= 100;

        if bulky && heavy {
            return "Both".to_string();
        }
        if bulky {
            return "Bulky".to_string();
        }
        if heavy {
            return "Heavy".to_string();
        }

        "Neither".to_string()
    }
}
