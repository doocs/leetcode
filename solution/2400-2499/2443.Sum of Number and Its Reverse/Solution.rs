impl Solution {
    pub fn sum_of_number_and_reverse(num: i32) -> bool {
        for i in 0..=num {
            if i + {
                let mut t = i;
                let mut j = 0;
                while t > 0 {
                    j = j * 10 + t % 10;
                    t /= 10;
                }
                j
            } == num
            {
                return true;
            }
        }
        false
    }
}
