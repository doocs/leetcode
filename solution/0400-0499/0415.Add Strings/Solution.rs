impl Solution {
    pub fn add_strings(num1: String, num2: String) -> String {
        let mut res = vec![];
        let s1 = num1.as_bytes();
        let s2 = num2.as_bytes();
        let (mut i, mut j) = (s1.len(), s2.len());
        let mut is_over = false;
        while i != 0 || j != 0 || is_over {
            let mut sum = if is_over { 1 } else { 0 };
            if i != 0 {
                sum += (s1[i - 1] - b'0') as i32;
                i -= 1;
            }
            if j != 0 {
                sum += (s2[j - 1] - b'0') as i32;
                j -= 1;
            }
            is_over = sum >= 10;
            res.push((sum % 10).to_string());
        }
        res.into_iter().rev().collect()
    }
}
