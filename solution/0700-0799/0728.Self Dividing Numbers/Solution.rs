impl Solution {
    pub fn self_dividing_numbers(left: i32, right: i32) -> Vec<i32> {
        let mut res = vec![];
        for i in left..=right {
            let mut num = i;
            if loop {
                if num == 0 {
                    break true;
                }
                let j = num % 10;
                if j == 0 || i % j != 0 {
                    break false;
                }
                num /= 10;
            } {
                res.push(i);
            }
        }
        res
    }
}
