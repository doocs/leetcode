impl Solution {
    pub fn multiply(num1: String, num2: String) -> String {
        if num1 == "0" || num2 == "0" {
            return String::from("0");
        }
        let mut res = vec![0];
        let num1 = num1.as_bytes();
        let num2 = num2.as_bytes();
        let n = num1.len();
        let m = num2.len();
        for i in 0..n {
            let num = num1[n - i - 1] - b'0';
            let mut sum = 0;
            let mut j = 0;
            while j < m || sum != 0 {
                if i + j == res.len() {
                    res.push(0);
                }
                sum += num * (num2.get(m - j - 1).unwrap_or(&b'0') - b'0') + res[i + j];
                res[i + j] = sum % 10;
                sum /= 10;
                j += 1;
            }
        }
        res.iter()
            .rev()
            .map(|num| num.to_string())
            .collect::<String>()
    }
}
