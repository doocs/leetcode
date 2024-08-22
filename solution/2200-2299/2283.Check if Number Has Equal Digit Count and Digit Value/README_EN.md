---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2283.Check%20if%20Number%20Has%20Equal%20Digit%20Count%20and%20Digit%20Value/README_EN.md
rating: 1253
source: Biweekly Contest 79 Q1
tags:
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [2283. Check if Number Has Equal Digit Count and Digit Value](https://leetcode.com/problems/check-if-number-has-equal-digit-count-and-digit-value)

[中文文档](/solution/2200-2299/2283.Check%20if%20Number%20Has%20Equal%20Digit%20Count%20and%20Digit%20Value/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> string <code>num</code> of length <code>n</code> consisting of digits.</p>

<p>Return <code>true</code> <em>if for <strong>every</strong> index </em><code>i</code><em> in the range </em><code>0 &lt;= i &lt; n</code><em>, the digit </em><code>i</code><em> occurs </em><code>num[i]</code><em> times in </em><code>num</code><em>, otherwise return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;1210&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>
num[0] = &#39;1&#39;. The digit 0 occurs once in num.
num[1] = &#39;2&#39;. The digit 1 occurs twice in num.
num[2] = &#39;1&#39;. The digit 2 occurs once in num.
num[3] = &#39;0&#39;. The digit 3 occurs zero times in num.
The condition holds true for every index in &quot;1210&quot;, so return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;030&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong>
num[0] = &#39;0&#39;. The digit 0 should occur zero times, but actually occurs twice in num.
num[1] = &#39;3&#39;. The digit 1 should occur three times, but actually occurs zero times in num.
num[2] = &#39;0&#39;. The digit 2 occurs zero times in num.
The indices 0 and 1 both violate the condition, so return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == num.length</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>num</code> consists of digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Enumeration

We can use an array $\textit{cnt}$ of length $10$ to count the occurrences of each digit in the string $\textit{num}$. Then, we enumerate each digit in the string $\textit{num}$ and check if its occurrence count equals the digit itself. If this condition is satisfied for all digits, we return $\text{true}$; otherwise, we return $\text{false}$.

The time complexity is $O(n)$, and the space complexity is $O(|\Sigma|)$. Here, $n$ is the length of the string $\textit{num}$, and $|\Sigma|$ is the range of possible digit values, which is $10$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def digitCount(self, num: str) -> bool:
        cnt = Counter(int(x) for x in num)
        return all(cnt[i] == int(x) for i, x in enumerate(num))
```

#### Java

```java
class Solution {
    public boolean digitCount(String num) {
        int[] cnt = new int[10];
        int n = num.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[num.charAt(i) - '0'];
        }
        for (int i = 0; i < n; ++i) {
            if (num.charAt(i) - '0' != cnt[i]) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool digitCount(string num) {
        int cnt[10]{};
        for (char& c : num) {
            ++cnt[c - '0'];
        }
        for (int i = 0; i < num.size(); ++i) {
            if (cnt[i] != num[i] - '0') {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func digitCount(num string) bool {
	cnt := [10]int{}
	for _, c := range num {
		cnt[c-'0']++
	}
	for i, c := range num {
		if int(c-'0') != cnt[i] {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function digitCount(num: string): boolean {
    const cnt: number[] = Array(10).fill(0);
    for (const c of num) {
        ++cnt[+c];
    }
    for (let i = 0; i < num.length; ++i) {
        if (cnt[i] !== +num[i]) {
            return false;
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn digit_count(num: String) -> bool {
        let mut cnt = vec![0; 10];
        for c in num.chars() {
            let x = c.to_digit(10).unwrap() as usize;
            cnt[x] += 1;
        }
        for (i, c) in num.chars().enumerate() {
            let x = c.to_digit(10).unwrap() as usize;
            if cnt[i] != x {
                return false;
            }
        }
        true
    }
}
```

#### C

```c
bool digitCount(char* num) {
    int cnt[10] = {0};
    for (int i = 0; num[i] != '\0'; ++i) {
        ++cnt[num[i] - '0'];
    }
    for (int i = 0; num[i] != '\0'; ++i) {
        if (cnt[i] != num[i] - '0') {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
