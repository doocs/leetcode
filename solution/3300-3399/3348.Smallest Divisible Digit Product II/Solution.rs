impl Solution {
    const DIGIT_PRIME_COUNTS: [[i32; 4]; 10] = [
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [1, 0, 0, 0],
        [0, 1, 0, 0],
        [2, 0, 0, 0],
        [0, 0, 1, 0],
        [1, 1, 0, 0],
        [0, 0, 0, 1],
        [3, 0, 0, 0],
        [0, 2, 0, 0],
    ];

    pub fn smallest_number(num: String, t: i64) -> String {
        let (required_prime_counts, has_valid_prime_factors) = Self::factorize_target(t);
        if !has_valid_prime_factors {
            return "-1".to_string();
        }
        let required_digit_counts = Self::prime_counts_to_digits(&required_prime_counts);
        if Self::digit_count(&required_digit_counts) > num.len() as i32 {
            let mut result = String::with_capacity(num.len());
            Self::append_digits(&required_digit_counts, &mut result);
            return result;
        }
        let mut prefix_prime_counts = Self::count_primes_in_number(&num);
        let mut first_zero_index = num.find('0');
        if first_zero_index.is_none() {
            first_zero_index = Some(num.len());
            if required_prime_counts
                .iter()
                .zip(prefix_prime_counts.iter())
                .all(|(required, available)| required <= available)
            {
                return num;
            }
        }
        let length = num.len();
        for index in (0..length).rev() {
            let digit = num.as_bytes()[index] - b'0';
            prefix_prime_counts = Self::subtract_counts(
                prefix_prime_counts,
                Self::DIGIT_PRIME_COUNTS[digit as usize],
            );
            let suffix_length = length - 1 - index;
            if index > first_zero_index.unwrap() {
                continue;
            }
            for bigger_digit in digit as i32 + 1..10 {
                let suffix_digit_counts = Self::prime_counts_to_digits(&Self::subtract_counts(
                    Self::subtract_counts(required_prime_counts, prefix_prime_counts),
                    Self::DIGIT_PRIME_COUNTS[bigger_digit as usize],
                ));
                if Self::digit_count(&suffix_digit_counts) <= suffix_length as i32 {
                    let ones_count = suffix_length as i32 - Self::digit_count(&suffix_digit_counts);
                    let mut result = String::with_capacity(length + 1);
                    result.push_str(&num[..index]);
                    result.push((b'0' + bigger_digit as u8) as char);
                    result.extend(std::iter::repeat('1').take(ones_count as usize));
                    Self::append_digits(&suffix_digit_counts, &mut result);
                    return result;
                }
            }
        }
        let extended_digit_counts = Self::prime_counts_to_digits(&required_prime_counts);
        let mut result = String::with_capacity(length + 1);
        result.extend(
            std::iter::repeat('1')
                .take(length + 1 - Self::digit_count(&extended_digit_counts) as usize),
        );
        Self::append_digits(&extended_digit_counts, &mut result);
        result
    }

    fn factorize_target(mut target: i64) -> ([i32; 4], bool) {
        let mut prime_counts = [0; 4];
        for (index, prime) in [2i64, 3, 5, 7].iter().enumerate() {
            while target % prime == 0 {
                target /= prime;
                prime_counts[index] += 1;
            }
        }
        (prime_counts, target == 1)
    }

    fn count_primes_in_number(num: &str) -> [i32; 4] {
        let mut prime_counts = [0; 4];
        for byte in num.bytes() {
            for index in 0..4 {
                prime_counts[index] += Self::DIGIT_PRIME_COUNTS[(byte - b'0') as usize][index];
            }
        }
        prime_counts
    }

    fn prime_counts_to_digits(prime_counts: &[i32; 4]) -> [i32; 10] {
        let count_8 = prime_counts[0] / 3;
        let remaining_2 = prime_counts[0] % 3;
        let count_9 = prime_counts[1] / 2;
        let mut count_3 = prime_counts[1] % 2;
        let mut count_4 = remaining_2 / 2;
        let mut count_2 = remaining_2 % 2;
        let mut count_6 = 0;
        if count_2 == 1 && count_3 == 1 {
            count_2 = 0;
            count_3 = 0;
            count_6 = 1;
        }
        if count_3 == 1 && count_4 == 1 {
            count_2 = 1;
            count_6 = 1;
            count_3 = 0;
            count_4 = 0;
        }
        [
            0,
            0,
            count_2,
            count_3,
            count_4,
            prime_counts[2],
            count_6,
            prime_counts[3],
            count_8,
            count_9,
        ]
    }

    fn append_digits(digit_counts: &[i32; 10], result: &mut String) {
        for digit in 2..10 {
            for _ in 0..digit_counts[digit] {
                result.push((b'0' + digit as u8) as char);
            }
        }
    }

    fn digit_count(digit_counts: &[i32; 10]) -> i32 {
        digit_counts.iter().sum()
    }

    fn subtract_counts(mut counts: [i32; 4], subtrahend: [i32; 4]) -> [i32; 4] {
        for index in 0..4 {
            counts[index] = (counts[index] - subtrahend[index]).max(0);
        }
        counts
    }
}
