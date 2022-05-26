impl Solution {
    pub fn max_number_of_balloons(text: String) -> i32 {
        let mut arr = [0; 5];
        for c in text.chars() {
            match c {
                'b' => arr[0] += 1,
                'a' => arr[1] += 1,
                'l' => arr[2] += 1,
                'o' => arr[3] += 1,
                'n' => arr[4] += 1,
                _ => {}
            }
        }
        arr[2] /= 2;
        arr[3] /= 2;
        let mut res = i32::MAX;
        for num in arr {
            res = res.min(num);
        }
        res
    }
}
