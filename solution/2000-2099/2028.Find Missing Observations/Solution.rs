impl Solution {
    pub fn missing_rolls(rolls: Vec<i32>, mean: i32, n: i32) -> Vec<i32> {
        let n = n as usize;
        let mean = mean as usize;
        let len = rolls.len() + n;
        let sum: i32 = rolls.iter().sum();
        let sum = sum as usize;
        let max = n * 6;
        let min = n;
        if (sum + max) < mean * len || (sum + min) > mean * len {
            return vec![];
        }

        let mut res = vec![0; n];
        for i in min..=max {
            if (sum + i) / len == mean {
                let num = i / n;
                res.fill(num as i32);
                let mut count = i - n * num;
                let mut j = 0;
                while count != 0 {
                    if res[j] == 6 {
                        j += 1;
                    } else {
                        res[j] += 1;
                        count -= 1;
                    }
                }
                break;
            }
        }
        res
    }
}
