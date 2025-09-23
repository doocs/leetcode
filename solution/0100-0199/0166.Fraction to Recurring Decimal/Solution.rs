use std::collections::HashMap;

impl Solution {
    pub fn fraction_to_decimal(numerator: i32, denominator: i32) -> String {
        if numerator == 0 {
            return "0".to_string();
        }
        let mut ans = String::new();

        let neg = (numerator > 0) ^ (denominator > 0);
        if neg {
            ans.push('-');
        }

        let mut a = (numerator as i64).abs();
        let b = (denominator as i64).abs();

        ans.push_str(&(a / b).to_string());
        a %= b;

        if a == 0 {
            return ans;
        }

        ans.push('.');

        let mut d: HashMap<i64, usize> = HashMap::new();
        while a != 0 {
            if let Some(&pos) = d.get(&a) {
                ans.insert(pos, '(');
                ans.push(')');
                break;
            }
            d.insert(a, ans.len());
            a *= 10;
            ans.push_str(&(a / b).to_string());
            a %= b;
        }

        ans
    }
}
