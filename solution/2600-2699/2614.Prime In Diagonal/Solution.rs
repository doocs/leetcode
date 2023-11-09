impl Solution {
    pub fn diagonal_prime(nums: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        let n = nums.len();

        for (i, row) in nums.iter().enumerate() {
            if Self::is_prime(row[i]) && row[i] > ans {
                ans = row[i];
            }
            if Self::is_prime(row[n - i - 1]) && row[n - i - 1] > ans {
                ans = row[n - i - 1];
            }
        }

        ans
    }

    fn is_prime(n: i32) -> bool {
        if n < 2 {
            return false;
        }

        let upper = (n as f64).sqrt() as i32;
        for i in 2..=upper {
            if n % i == 0 {
                return false;
            }
        }

        true
    }
}
