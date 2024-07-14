---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0032.Longest%20Valid%20Parentheses/README_EN.md
tags:
    - Stack
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses)

[中文文档](/solution/0000-0099/0032.Longest%20Valid%20Parentheses/README.md)

## Description

<!-- description:start -->

<p>Given a string containing just the characters <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>, return <em>the length of the longest valid (well-formed) parentheses </em><span data-keyword="substring-nonempty"><em>substring</em></span>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(()&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest valid parentheses substring is &quot;()&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;)()())&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest valid parentheses substring is &quot;()()&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;&quot;
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s[i]</code> is <code>&#39;(&#39;</code>, or <code>&#39;)&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i]$ to be the length of the longest valid parentheses that ends with $s[i-1]$, and the answer is $max(f[i])$.

When $i \lt 2$, the length of the string is less than $2$, and there is no valid parentheses, so $f[i] = 0$.

When $i \ge 2$, we consider the length of the longest valid parentheses that ends with $s[i-1]$, that is, $f[i]$:

-   If $s[i-1]$ is a left parenthesis, then the length of the longest valid parentheses that ends with $s[i-1]$ must be $0$, so $f[i] = 0$.
-   If $s[i-1]$ is a right parenthesis, there are the following two cases:
    -   If $s[i-2]$ is a left parenthesis, then the length of the longest valid parentheses that ends with $s[i-1]$ is $f[i-2] + 2$.
    -   If $s[i-2]$ is a right parenthesis, then the length of the longest valid parentheses that ends with $s[i-1]$ is $f[i-1] + 2$, but we also need to consider whether $s[i-f[i-1]-2]$ is a left parenthesis. If it is a left parenthesis, then the length of the longest valid parentheses that ends with $s[i-1]$ is $f[i-1] + 2 + f[i-f[i-1]-2]$.

Therefore, we can get the state transition equation:

$$
\begin{cases}
f[i] = 0, & \text{if } s[i-1] = '(',\\
f[i] = f[i-2] + 2, & \text{if } s[i-1] = ')' \text{ and } s[i-2] = '(',\\
f[i] = f[i-1] + 2 + f[i-f[i-1]-2], & \text{if } s[i-1] = ')' \text{ and } s[i-2] = ')' \text{ and } s[i-f[i-1]-2] = '(',\\
\end{cases}
$$

Finally, we only need to return $max(f)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        n = len(s)
        f = [0] * (n + 1)
        for i, c in enumerate(s, 1):
            if c == ")":
                if i > 1 and s[i - 2] == "(":
                    f[i] = f[i - 2] + 2
                else:
                    j = i - f[i - 1] - 1
                    if j and s[j - 1] == "(":
                        f[i] = f[i - 1] + 2 + f[j - 1]
        return max(f)
```

#### Java

```java
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            if (s.charAt(i - 1) == ')') {
                if (s.charAt(i - 2) == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j > 0 && s.charAt(j - 1) == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
                ans = Math.max(ans, f[i]);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestValidParentheses(string s) {
        int n = s.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 2; i <= n; ++i) {
            if (s[i - 1] == ')') {
                if (s[i - 2] == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j && s[j - 1] == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
            }
        }
        return *max_element(f, f + n + 1);
    }
};
```

#### Go

```go
func longestValidParentheses(s string) int {
	n := len(s)
	f := make([]int, n+1)
	for i := 2; i <= n; i++ {
		if s[i-1] == ')' {
			if s[i-2] == '(' {
				f[i] = f[i-2] + 2
			} else if j := i - f[i-1] - 1; j > 0 && s[j-1] == '(' {
				f[i] = f[i-1] + 2 + f[j-1]
			}
		}
	}
	return slices.Max(f)
}
```

#### TypeScript

```ts
function longestValidParentheses(s: string): number {
    const n = s.length;
    const f: number[] = new Array(n + 1).fill(0);
    for (let i = 2; i <= n; ++i) {
        if (s[i - 1] === ')') {
            if (s[i - 2] === '(') {
                f[i] = f[i - 2] + 2;
            } else {
                const j = i - f[i - 1] - 1;
                if (j && s[j - 1] === '(') {
                    f[i] = f[i - 1] + 2 + f[j - 1];
                }
            }
        }
    }
    return Math.max(...f);
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_valid_parentheses(s: String) -> i32 {
        let mut ans = 0;
        let mut f = vec![0; s.len() + 1];
        for i in 2..=s.len() {
            if s.chars().nth(i - 1).unwrap() == ')' {
                if s.chars().nth(i - 2).unwrap() == '(' {
                    f[i] = f[i - 2] + 2;
                } else if (i as i32) - f[i - 1] - 1 > 0
                    && s.chars().nth(i - (f[i - 1] as usize) - 2).unwrap() == '('
                {
                    f[i] = f[i - 1] + 2 + f[i - (f[i - 1] as usize) - 2];
                }
                ans = ans.max(f[i]);
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var longestValidParentheses = function (s) {
    const n = s.length;
    const f = new Array(n + 1).fill(0);
    for (let i = 2; i <= n; ++i) {
        if (s[i - 1] === ')') {
            if (s[i - 2] === '(') {
                f[i] = f[i - 2] + 2;
            } else {
                const j = i - f[i - 1] - 1;
                if (j && s[j - 1] === '(') {
                    f[i] = f[i - 1] + 2 + f[j - 1];
                }
            }
        }
    }
    return Math.max(...f);
};
```

#### C#

```cs
public class Solution {
    public int LongestValidParentheses(string s) {
        int n = s.Length;
        int[] f = new int[n + 1];
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            if (s[i - 1] == ')') {
                if (s[i - 2] == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j > 0 && s[j - 1] == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
                ans = Math.Max(ans, f[i]);
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Using Stack

-   Maintain a stack to store the indices of left parentheses. Initialize the bottom element of the stack with the value -1 to facilitate the calculation of the length of valid parentheses.
-   Iterate through each element of the string:
    -   If the character is a left parenthesis, push the index of the character onto the stack.
    -   If the character is a right parenthesis, pop an element from the stack to represent that we have found a valid pair of parentheses.
        -   If the stack is empty, it means we couldn't find a left parenthesis to match the right parenthesis. In this case, push the index of the character as a new starting point.
        -   If the stack is not empty, calculate the length of the valid parentheses and update it.

Summary:

The key to this algorithm is to maintain a stack to store the indices of left parentheses and then update the length of the valid substring of parentheses by pushing and popping elements.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        stack = [-1]
        ans = 0
        for i in range(len(s)):
            if s[i] == '(':
                stack.append(i)
            else:
                stack.pop()
                if not stack:
                    stack.append(i)
                else:
                    ans = max(ans, i - stack[-1])
        return ans
```

#### Go

```go
func longestValidParentheses(s string) int {
	ans := 0
	stack := []int{-1}
	for i, v := range s {
		if v == '(' {
			stack = append(stack, i)
		} else {
			stack = stack[:len(stack)-1]
			if len(stack) == 0 {
				stack = append(stack, i)
			} else {
				if ans < i-stack[len(stack)-1] {
					ans = i - stack[len(stack)-1]
				}
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestValidParentheses(s: string): number {
    let max_length: number = 0;
    const stack: number[] = [-1];
    for (let i = 0; i < s.length; i++) {
        if (s.charAt(i) == '(') {
            stack.push(i);
        } else {
            stack.pop();

            if (stack.length === 0) {
                stack.push(i);
            } else {
                max_length = Math.max(max_length, i - stack[stack.length - 1]);
            }
        }
    }

    return max_length;
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_valid_parentheses(s: String) -> i32 {
        let mut stack = vec![-1];
        let mut res = 0;
        for i in 0..s.len() {
            if let Some('(') = s.chars().nth(i) {
                stack.push(i as i32);
            } else {
                stack.pop().unwrap();
                if stack.is_empty() {
                    stack.push(i as i32);
                } else {
                    res = std::cmp::max(res, (i as i32) - stack.last().unwrap());
                }
            }
        }
        res
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var longestValidParentheses = function (s) {
    let ans = 0;
    const stack = [-1];
    for (i = 0; i < s.length; i++) {
        if (s.charAt(i) === '(') {
            stack.push(i);
        } else {
            stack.pop();
            if (stack.length === 0) {
                stack.push(i);
            } else {
                ans = Math.max(ans, i - stack[stack.length - 1]);
            }
        }
    }
    return ans;
};
```

#### PHP

```php
class Solution {
    /**
     * @param string $s
     * @return integer
     */

    function longestValidParentheses($s) {
        $stack = [];
        $maxLength = 0;

        array_push($stack, -1);
        for ($i = 0; $i < strlen($s); $i++) {
            if ($s[$i] === '(') {
                array_push($stack, $i);
            } else {
                array_pop($stack);

                if (empty($stack)) {
                    array_push($stack, $i);
                } else {
                    $length = $i - end($stack);
                    $maxLength = max($maxLength, $length);
                }
            }
        }
        return $maxLength;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
