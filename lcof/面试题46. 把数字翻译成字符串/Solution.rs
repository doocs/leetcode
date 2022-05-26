impl Solution {
    pub fn translate_num(num: i32) -> i32 {
        let mut a = 1;
        let mut b = 1;
        let str = num.to_string();
        for i in 0..str.len() - 1 {
            let c = a + b;
            a = b;
            let num = str[i..i + 2].parse::<i32>().unwrap();
            if num >= 10 && num < 26 {
                b = c;
            }
        }
        b
    }
}
