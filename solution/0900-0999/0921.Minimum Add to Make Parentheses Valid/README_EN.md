---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0921.Minimum%20Add%20to%20Make%20Parentheses%20Valid/README_EN.md
tags:
    - Stack
    - Greedy
    - String
---

<!-- problem:start -->

# [921. Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid)

[中文文档](/solution/0900-0999/0921.Minimum%20Add%20to%20Make%20Parentheses%20Valid/README.md)

## Description

<!-- description:start -->

<p>A parentheses string is valid if and only if:</p>

<ul>
	<li>It is the empty string,</li>
	<li>It can be written as <code>AB</code> (<code>A</code> concatenated with <code>B</code>), where <code>A</code> and <code>B</code> are valid strings, or</li>
	<li>It can be written as <code>(A)</code>, where <code>A</code> is a valid string.</li>
</ul>

<p>You are given a parentheses string <code>s</code>. In one move, you can insert a parenthesis at any position of the string.</p>

<ul>
	<li>For example, if <code>s = &quot;()))&quot;</code>, you can insert an opening parenthesis to be <code>&quot;(<strong>(</strong>)))&quot;</code> or a closing parenthesis to be <code>&quot;())<strong>)</strong>)&quot;</code>.</li>
</ul>

<p>Return <em>the minimum number of moves required to make </em><code>s</code><em> valid</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;())&quot;
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(((&quot;
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;(&#39;</code> or <code>&#39;)&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Stack

This problem is a classic parenthesis matching problem, which can be solved using "Greedy + Stack".

Iterate through each character $c$ in the string $s$:

- If $c$ is a left parenthesis, directly push $c$ into the stack;
- If $c$ is a right parenthesis, at this point if the stack is not empty, and the top element of the stack is a left parenthesis, then pop the top element of the stack, indicating a successful match; otherwise, push $c$ into the stack.

After the iteration ends, the number of remaining elements in the stack is the number of parentheses that need to be added.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        stk = []
        for c in s:
            if c == ')' and stk and stk[-1] == '(':
                stk.pop()
            else:
                stk.append(c)
        return len(stk)
```

#### Java

```java
class Solution {
    public int minAddToMakeValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ')' && !stk.isEmpty() && stk.peek() == '(') {
                stk.pop();
            } else {
                stk.push(c);
            }
        }
        return stk.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minAddToMakeValid(string s) {
        string stk;
        for (char c : s) {
            if (c == ')' && stk.size() && stk.back() == '(')
                stk.pop_back();
            else
                stk.push_back(c);
        }
        return stk.size();
    }
};
```

#### Go

```go
func minAddToMakeValid(s string) int {
	stk := []rune{}
	for _, c := range s {
		if c == ')' && len(stk) > 0 && stk[len(stk)-1] == '(' {
			stk = stk[:len(stk)-1]
		} else {
			stk = append(stk, c)
		}
	}
	return len(stk)
}
```

#### TypeScript

```ts
function minAddToMakeValid(s: string): number {
    const stk: string[] = [];
    for (const c of s) {
        if (c === ')' && stk.length > 0 && stk.at(-1)! === '(') {
            stk.pop();
        } else {
            stk.push(c);
        }
    }
    return stk.length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Greedy + Counting

Solution 1 uses a stack to implement parenthesis matching, but we can also directly implement it through counting.

Define a variable `cnt` to represent the current number of left parentheses to be matched, and a variable `ans` to record the answer. Initially, both variables are set to $0$.

Iterate through each character $c$ in the string $s$:

- If $c$ is a left parenthesis, increase the value of `cnt` by $1$;
- If $c$ is a right parenthesis, at this point if $cnt > 0$, it means that there are left parentheses that can be matched, so decrease the value of `cnt` by $1$; otherwise, it means that the current right parenthesis cannot be matched, so increase the value of `ans` by $1$.

After the iteration ends, add the value of `cnt` to `ans`, which is the answer.

The time complexity is $O(n)$, and the space complexity is $O(1)$, where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        ans = cnt = 0
        for c in s:
            if c == '(':
                cnt += 1
            elif cnt:
                cnt -= 1
            else:
                ans += 1
        ans += cnt
        return ans
```

#### Java

```java
class Solution {
    public int minAddToMakeValid(String s) {
        int ans = 0, cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++cnt;
            } else if (cnt > 0) {
                --cnt;
            } else {
                ++ans;
            }
        }
        ans += cnt;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minAddToMakeValid(string s) {
        int ans = 0, cnt = 0;
        for (char c : s) {
            if (c == '(')
                ++cnt;
            else if (cnt)
                --cnt;
            else
                ++ans;
        }
        ans += cnt;
        return ans;
    }
};
```

#### Go

```go
func minAddToMakeValid(s string) int {
	ans, cnt := 0, 0
	for _, c := range s {
		if c == '(' {
			cnt++
		} else if cnt > 0 {
			cnt--
		} else {
			ans++
		}
	}
	ans += cnt
	return ans
}
```

#### TypeScript

```ts
function minAddToMakeValid(s: string): number {
    let [ans, cnt] = [0, 0];
    for (const c of s) {
        if (c === '(') {
            ++cnt;
        } else if (cnt) {
            --cnt;
        } else {
            ++ans;
        }
    }
    ans += cnt;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Replace + recursion

<!-- tabs:start -->

#### TypeScript

```ts
function minAddToMakeValid(s: string): number {
    const l = s.length;
    s = s.replace('()', '');

    return s.length === l ? l : minAddToMakeValid(s);
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var minAddToMakeValid = function (s) {
    const l = s.length;
    s = s.replace('()', '');
    return s.length === l ? l : minAddToMakeValid(s);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
