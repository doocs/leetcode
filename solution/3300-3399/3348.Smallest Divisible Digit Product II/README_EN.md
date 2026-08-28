---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3348.Smallest%20Divisible%20Digit%20Product%20II/README_EN.md
rating: 3101
source: Biweekly Contest 143 Q4
tags:
    - Greedy
    - Math
    - String
    - Backtracking
    - Number Theory
---

<!-- problem:start -->

# [3348. Smallest Divisible Digit Product II](https://leetcode.com/problems/smallest-divisible-digit-product-ii)

[中文文档](/solution/3300-3399/3348.Smallest%20Divisible%20Digit%20Product%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>num</code> which represents a <strong>positive</strong> integer, and an integer <code>t</code>.</p>

<p>A number is called <strong>zero-free</strong> if <em>none</em> of its digits are 0.</p>

<p>Return a string representing the <strong>smallest</strong> <strong>zero-free</strong> number greater than or equal to <code>num</code> such that the <strong>product of its digits</strong> is divisible by <code>t</code>. If no such number exists, return <code>&quot;-1&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num = &quot;1234&quot;, t = 256</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;1488&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>The smallest zero-free number that is greater than 1234 and has the product of its digits divisible by 256 is 1488, with the product of its digits equal to 256.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num = &quot;12355&quot;, t = 50</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;12355&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>12355 is already zero-free and has the product of its digits divisible by 50, with the product of its digits equal to 150.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num = &quot;11111&quot;, t = 26</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;-1&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>No number greater than 11111 has the product of its digits divisible by 26.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= num.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>num</code> consists only of digits in the range <code>[&#39;0&#39;, &#39;9&#39;]</code>.</li>
	<li><code>num</code> does not contain leading zeros.</li>
	<li><code>1 &lt;= t &lt;= 10<sup>14</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go
func smallestNumber(num string, t int64) string {
	primeCount, isDivisible := getPrimeCount(t)
	if !isDivisible {
		return "-1"
	}

	factorCount := getFactorCount(primeCount)
	if sumValues(factorCount) > len(num) {
		return construct(factorCount)
	}

	primeCountPrefix := getPrimeCountFromString(num)
	firstZeroIndex := strings.Index(num, "0")
	if firstZeroIndex == -1 {
		firstZeroIndex = len(num)
		if isSubset(primeCount, primeCountPrefix) {
			return num
		}
	}

	for i := len(num) - 1; i >= 0; i-- {
		d := int(num[i] - '0')
		primeCountPrefix = subtract(primeCountPrefix, kFactorCounts[d])
		spaceAfterThisDigit := len(num) - 1 - i
		if i > firstZeroIndex {
			continue
		}
		for biggerDigit := d + 1; biggerDigit < 10; biggerDigit++ {
			factorsAfterReplacement := getFactorCount(
				subtract(subtract(primeCount, primeCountPrefix), kFactorCounts[biggerDigit]),
			)
			if sumValues(factorsAfterReplacement) <= spaceAfterThisDigit {
				fillOnes := spaceAfterThisDigit - sumValues(factorsAfterReplacement)
				return num[:i] + strconv.Itoa(biggerDigit) + strings.Repeat("1", fillOnes) + construct(factorsAfterReplacement)
			}
		}
	}

	factorsAfterExtension := getFactorCount(primeCount)
	return strings.Repeat("1", len(num)+1-sumValues(factorsAfterExtension)) + construct(factorsAfterExtension)
}

var kFactorCounts = map[int]map[int]int{
	0: {}, 1: {}, 2: {2: 1}, 3: {3: 1}, 4: {2: 2},
	5: {5: 1}, 6: {2: 1, 3: 1}, 7: {7: 1}, 8: {2: 3}, 9: {3: 2},
}

func getPrimeCount(t int64) (map[int]int, bool) {
	count := map[int]int{2: 0, 3: 0, 5: 0, 7: 0}
	for _, prime := range []int{2, 3, 5, 7} {
		for t%int64(prime) == 0 {
			t /= int64(prime)
			count[prime]++
		}
	}
	return count, t == 1
}

func getPrimeCountFromString(num string) map[int]int {
	count := map[int]int{2: 0, 3: 0, 5: 0, 7: 0}
	for _, d := range num {
		for prime, freq := range kFactorCounts[int(d-'0')] {
			count[prime] += freq
		}
	}
	return count
}

func getFactorCount(count map[int]int) map[int]int {
	res := map[int]int{}
	count8 := count[2] / 3
	remaining2 := count[2] % 3
	count9 := count[3] / 2
	count3 := count[3] % 2
	count4 := remaining2 / 2
	count2 := remaining2 % 2
	count6 := 0
	if count2 == 1 && count3 == 1 {
		count2, count3 = 0, 0
		count6 = 1
	}
	if count3 == 1 && count4 == 1 {
		count2 = 1
		count6 = 1
		count3, count4 = 0, 0
	}
	res[2] = count2
	res[3] = count3
	res[4] = count4
	res[5] = count[5]
	res[6] = count6
	res[7] = count[7]
	res[8] = count8
	res[9] = count9
	return res
}

func construct(factors map[int]int) string {
	var res strings.Builder
	for digit := 2; digit < 10; digit++ {
		res.WriteString(strings.Repeat(strconv.Itoa(digit), factors[digit]))
	}
	return res.String()
}

func isSubset(a, b map[int]int) bool {
	for key, value := range a {
		if b[key] < value {
			return false
		}
	}
	return true
}

func subtract(a, b map[int]int) map[int]int {
	res := make(map[int]int, len(a))
	for k, v := range a {
		res[k] = v
	}
	for k, v := range b {
		res[k] = max(0, res[k]-v)
	}
	return res
}

func sumValues(count map[int]int) int {
	sum := 0
	for _, v := range count {
		sum += v
	}
	return sum
}
```

#### Rust

```rust
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
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
