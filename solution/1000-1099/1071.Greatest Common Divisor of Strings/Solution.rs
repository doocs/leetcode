impl Solution {
    pub fn gcd_of_strings(str1: String, str2: String) -> String {
        if str1.clone() + &str2 != str2.clone() + &str1 {
            return String::from("");
        }
        fn gcd(a: usize, b: usize) -> usize {
            if b == 0 {
                return a;
            }
            gcd(b, a % b)
        }

        let (m, n) = (str1.len().max(str2.len()), str1.len().min(str2.len()));
        str1[..gcd(m, n)].to_string()
    }
}
