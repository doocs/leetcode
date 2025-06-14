---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2566.Maximum%20Difference%20by%20Remapping%20a%20Digit/README_EN.md
rating: 1396
source: Biweekly Contest 98 Q1
tags:
    - Greedy
    - Math
---

<!-- problem:start -->

# [2566. Maximum Difference by Remapping a Digit](https://leetcode.com/problems/maximum-difference-by-remapping-a-digit)

[中文文档](/solution/2500-2599/2566.Maximum%20Difference%20by%20Remapping%20a%20Digit/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>num</code>. You know that Bob will sneakily <strong>remap</strong> one of the <code>10</code> possible digits (<code>0</code> to <code>9</code>) to another digit.</p>

<p>Return <em>the difference between the maximum and minimum&nbsp;values Bob can make by remapping&nbsp;<strong>exactly</strong> <strong>one</strong> digit in </em><code>num</code>.</p>

<p><strong>Notes:</strong></p>

<ul>
	<li>When Bob remaps a digit <font face="monospace">d1</font>&nbsp;to another digit <font face="monospace">d2</font>, Bob replaces all occurrences of <code>d1</code>&nbsp;in <code>num</code>&nbsp;with <code>d2</code>.</li>
	<li>Bob can remap a digit to itself, in which case <code>num</code>&nbsp;does not change.</li>
	<li>Bob can remap different digits for obtaining minimum and maximum values respectively.</li>
	<li>The resulting number after remapping can contain leading zeroes.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 11891
<strong>Output:</strong> 99009
<strong>Explanation:</strong> 
To achieve the maximum value, Bob can remap the digit 1 to the digit 9 to yield 99899.
To achieve the minimum value, Bob can remap the digit 1 to the digit 0, yielding 890.
The difference between these two numbers is 99009.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 90
<strong>Output:</strong> 99
<strong>Explanation:</strong>
The maximum value that can be returned by the function is 99 (if 0 is replaced by 9) and the minimum value that can be returned by the function is 0 (if 9 is replaced by 0).
Thus, we return 99.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

First, we convert the number to a string $s$.

To get the minimum value, we just need to find the first digit $s[0]$ in the string $s$, and then replace all $s[0]$ in the string with $0$.

To get the maximum value, we need to find the first digit $s[i]$ in the string $s$ that is not $9$, and then replace all $s[i]$ in the string with $9$.

Finally, return the difference between the maximum and minimum values.

The time complexity is $O(\log n)$, and the space complexity is $O(\log n)$. Where $n$ is the size of the number $\textit{num}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMaxDifference(self, num: int) -> int:
        s = str(num)
        mi = int(s.replace(s[0], '0'))
        for c in s:
            if c != '9':
                return int(s.replace(c, '9')) - mi
        return num - mi
```

#### Java

```java
class Solution {
    public int minMaxDifference(int num) {
        String s = String.valueOf(num);
        int mi = Integer.parseInt(s.replace(s.charAt(0), '0'));
        for (char c : s.toCharArray()) {
            if (c != '9') {
                return Integer.parseInt(s.replace(c, '9')) - mi;
            }
        }
        return num - mi;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMaxDifference(int num) {
        string s = to_string(num);
        string t = s;
        char first = s[0];
        for (char& c : s) {
            if (c == first) {
                c = '0';
            }
        }
        int mi = stoi(s);
        for (int i = 0; i < t.size(); ++i) {
            if (t[i] != '9') {
                char second = t[i];
                for (int j = i; j < t.size(); ++j) {
                    if (t[j] == second) {
                        t[j] = '9';
                    }
                }
                return stoi(t) - mi;
            }
        }
        return num - mi;
    }
};
```

#### Go

```go
func minMaxDifference(num int) int {
	s := []byte(strconv.Itoa(num))
	first := s[0]
	for i := range s {
		if s[i] == first {
			s[i] = '0'
		}
	}
	mi, _ := strconv.Atoi(string(s))
	t := []byte(strconv.Itoa(num))
	for i := range t {
		if t[i] != '9' {
			second := t[i]
			for j := i; j < len(t); j++ {
				if t[j] == second {
					t[j] = '9'
				}
			}
			mx, _ := strconv.Atoi(string(t))
			return mx - mi
		}
	}
	return num - mi
}
```

#### TypeScript

```ts
function minMaxDifference(num: number): number {
    const s = num.toString();
    const mi = +s.replaceAll(s[0], '0');
    for (const c of s) {
        if (c !== '9') {
            const mx = +s.replaceAll(c, '9');
            return mx - mi;
        }
    }
    return num - mi;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_max_difference(num: i32) -> i32 {
        let s = num.to_string();
        let mi = s.replace(s.chars().next().unwrap(), "0").parse::<i32>().unwrap();
        for c in s.chars() {
            if c != '9' {
                let mx = s.replace(c, "9").parse::<i32>().unwrap();
                return mx - mi;
            }
        }
        num - mi
    }
}
```

#### JavaScript

```js
/**
 * @param {number} num
 * @return {number}
 */
var minMaxDifference = function (num) {
    const s = num.toString();
    const mi = +s.replaceAll(s[0], '0');
    for (const c of s) {
        if (c !== '9') {
            const mx = +s.replaceAll(c, '9');
            return mx - mi;
        }
    }
    return num - mi;
};
```

#### C

```c
int minMaxDifference(int num) {
    char s[12];
    sprintf(s, "%d", num);

    int mi;
    {
        char tmp[12];
        char t = s[0];
        for (int i = 0; s[i]; i++) {
            tmp[i] = (s[i] == t) ? '0' : s[i];
        }
        tmp[strlen(s)] = '\0';
        mi = atoi(tmp);
    }

    for (int i = 0; s[i]; i++) {
        char c = s[i];
        if (c != '9') {
            char tmp[12];
            for (int j = 0; s[j]; j++) {
                tmp[j] = (s[j] == c) ? '9' : s[j];
            }
            tmp[strlen(s)] = '\0';
            return atoi(tmp) - mi;
        }
    }

    return num - mi;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
