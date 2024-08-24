---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1796.Second%20Largest%20Digit%20in%20a%20String/README_EN.md
rating: 1341
source: Biweekly Contest 48 Q1
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [1796. Second Largest Digit in a String](https://leetcode.com/problems/second-largest-digit-in-a-string)

[中文文档](/solution/1700-1799/1796.Second%20Largest%20Digit%20in%20a%20String/README.md)

## Description

<!-- description:start -->

<p>Given an alphanumeric string <code>s</code>, return <em>the <strong>second largest</strong> numerical digit that appears in </em><code>s</code><em>, or </em><code>-1</code><em> if it does not exist</em>.</p>

<p>An <strong>alphanumeric</strong><strong> </strong>string is a string consisting of lowercase English letters and digits.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;dfa12321afd&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The digits that appear in s are [1, 2, 3]. The second largest digit is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc1111&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> The digits that appear in s are [1]. There is no second largest digit. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> consists of only lowercase English letters and digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: One Pass

We define $a$ and $b$ to represent the largest and second largest numbers in the string, initially $a = b = -1$.

We traverse the string $s$. If the current character is a digit, we convert it to a number $v$. If $v > a$, it means that $v$ is the largest number currently appearing, we update $b$ to $a$, and update $a$ to $v$; if $v < a$, it means that $v$ is the second largest number currently appearing, we update $b$ to $v$.

After the traversal, we return $b$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def secondHighest(self, s: str) -> int:
        a = b = -1
        for c in s:
            if c.isdigit():
                v = int(c)
                if v > a:
                    a, b = v, a
                elif b < v < a:
                    b = v
        return b
```

#### Java

```java
class Solution {
    public int secondHighest(String s) {
        int a = -1, b = -1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int v = c - '0';
                if (v > a) {
                    b = a;
                    a = v;
                } else if (v > b && v < a) {
                    b = v;
                }
            }
        }
        return b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int secondHighest(string s) {
        int a = -1, b = -1;
        for (char& c : s) {
            if (isdigit(c)) {
                int v = c - '0';
                if (v > a) {
                    b = a, a = v;
                } else if (v > b && v < a) {
                    b = v;
                }
            }
        }
        return b;
    }
};
```

#### Go

```go
func secondHighest(s string) int {
	a, b := -1, -1
	for _, c := range s {
		if c >= '0' && c <= '9' {
			v := int(c - '0')
			if v > a {
				b, a = a, v
			} else if v > b && v < a {
				b = v
			}
		}
	}
	return b
}
```

#### TypeScript

```ts
function secondHighest(s: string): number {
    let first = -1;
    let second = -1;
    for (const c of s) {
        if (c >= '0' && c <= '9') {
            const num = c.charCodeAt(0) - '0'.charCodeAt(0);
            if (first < num) {
                [first, second] = [num, first];
            } else if (first !== num && second < num) {
                second = num;
            }
        }
    }
    return second;
}
```

#### Rust

```rust
impl Solution {
    pub fn second_highest(s: String) -> i32 {
        let mut first = -1;
        let mut second = -1;
        for c in s.as_bytes() {
            if char::is_digit(*c as char, 10) {
                let num = (c - b'0') as i32;
                if first < num {
                    second = first;
                    first = num;
                } else if num < first && second < num {
                    second = num;
                }
            }
        }
        second
    }
}
```

#### C

```c
int secondHighest(char* s) {
    int first = -1;
    int second = -1;
    for (int i = 0; s[i]; i++) {
        if (isdigit(s[i])) {
            int num = s[i] - '0';
            if (num > first) {
                second = first;
                first = num;
            } else if (num < first && second < num) {
                second = num;
            }
        }
    }
    return second;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Bit Manipulation

We can use an integer $mask$ to mark the numbers that appear in the string, where the $i$-th bit of $mask$ indicates whether the number $i$ has appeared.

We traverse the string $s$. If the current character is a digit, we convert it to a number $v$, and set the $v$-th bit of $mask$ to $1$.

Finally, we traverse $mask$ from high to low, find the second bit that is $1$, and the corresponding number is the second largest number. If there is no second largest number, return $-1$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def secondHighest(self, s: str) -> int:
        mask = reduce(or_, (1 << int(c) for c in s if c.isdigit()), 0)
        cnt = 0
        for i in range(9, -1, -1):
            if (mask >> i) & 1:
                cnt += 1
            if cnt == 2:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int secondHighest(String s) {
        int mask = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                mask |= 1 << (c - '0');
            }
        }
        for (int i = 9, cnt = 0; i >= 0; --i) {
            if (((mask >> i) & 1) == 1 && ++cnt == 2) {
                return i;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int secondHighest(string s) {
        int mask = 0;
        for (char& c : s)
            if (isdigit(c)) mask |= 1 << c - '0';
        for (int i = 9, cnt = 0; ~i; --i)
            if (mask >> i & 1 && ++cnt == 2) return i;
        return -1;
    }
};
```

#### Go

```go
func secondHighest(s string) int {
	mask := 0
	for _, c := range s {
		if c >= '0' && c <= '9' {
			mask |= 1 << int(c-'0')
		}
	}
	for i, cnt := 9, 0; i >= 0; i-- {
		if mask>>i&1 == 1 {
			cnt++
			if cnt == 2 {
				return i
			}
		}
	}
	return -1
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
