impl Solution {
    pub fn max_alive_year(birth: Vec<i32>, death: Vec<i32>) -> i32 {
        let n = birth.len();
        let mut counter = vec![0; 101];
        for i in 0..n {
            let (start, end) = (birth[i] - 1900, death[i] - 1900);
            for j in start..=end {
                counter[j as usize] += 1;
            }
        }
        let mut res = 0;
        let mut max = 0;
        for (i, count) in counter.iter().enumerate() {
            if *count > max {
                res = i;
                max = *count;
            }
        }
        (res + 1900) as i32
    }
}
