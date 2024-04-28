impl Solution {
    pub fn base_neg2(n: i32) -> String {
        if n == 0 {
            return "0".to_string();
        }
        let mut k = 1;
        let mut ans = String::new();
        let mut num = n;
        while num != 0 {
            if num % 2 != 0 {
                ans.push('1');
                num -= k;
            } else {
                ans.push('0');
            }
            k *= -1;
            num /= 2;
        }
        ans.chars().rev().collect::<String>()
    }
}
