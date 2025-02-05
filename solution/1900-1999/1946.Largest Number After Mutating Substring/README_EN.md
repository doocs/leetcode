---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1946.Largest%20Number%20After%20Mutating%20Substring/README_EN.md
rating: 1445
source: Weekly Contest 251 Q2
tags:
    - Greedy
    - Array
    - String
---

<!-- problem:start -->

# [1946. Largest Number After Mutating Substring](https://leetcode.com/problems/largest-number-after-mutating-substring)

[中文文档](/solution/1900-1999/1946.Largest%20Number%20After%20Mutating%20Substring/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>num</code>, which represents a large integer. You are also given a <strong>0-indexed</strong> integer array <code>change</code> of length <code>10</code> that maps each digit <code>0-9</code> to another digit. More formally, digit <code>d</code> maps to digit <code>change[d]</code>.</p>

<p>You may <strong>choose</strong> to <b>mutate a single substring</b> of <code>num</code>. To mutate a substring, replace each digit <code>num[i]</code> with the digit it maps to in <code>change</code> (i.e. replace <code>num[i]</code> with <code>change[num[i]]</code>).</p>

<p>Return <em>a string representing the <strong>largest</strong> possible integer after <strong>mutating</strong> (or choosing not to) a <strong>single substring</strong> of </em><code>num</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within the string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;<u>1</u>32&quot;, change = [9,8,5,0,3,6,4,2,6,8]
<strong>Output:</strong> &quot;<u>8</u>32&quot;
<strong>Explanation:</strong> Replace the substring &quot;1&quot;:
- 1 maps to change[1] = 8.
Thus, &quot;<u>1</u>32&quot; becomes &quot;<u>8</u>32&quot;.
&quot;832&quot; is the largest number that can be created, so return it.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;<u>021</u>&quot;, change = [9,4,3,5,7,2,1,9,0,6]
<strong>Output:</strong> &quot;<u>934</u>&quot;
<strong>Explanation:</strong> Replace the substring &quot;021&quot;:
- 0 maps to change[0] = 9.
- 2 maps to change[2] = 3.
- 1 maps to change[1] = 4.
Thus, &quot;<u>021</u>&quot; becomes &quot;<u>934</u>&quot;.
&quot;934&quot; is the largest number that can be created, so return it.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;5&quot;, change = [1,4,7,5,3,2,5,6,9,4]
<strong>Output:</strong> &quot;5&quot;
<strong>Explanation:</strong> &quot;5&quot; is already the largest number that can be created, so return it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num</code> consists of only digits <code>0-9</code>.</li>
	<li><code>change.length == 10</code></li>
	<li><code>0 &lt;= change[d] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

According to the problem description, we can start from the highest digit of the string and greedily perform continuous replacement operations until we encounter a digit smaller than the current digit.

First, we convert the string $\textit{num}$ into a character array $\textit{s}$ and use a variable $\textit{changed}$ to record whether a change has already occurred, initially $\textit{changed} = \text{false}$.

Then we traverse the character array $\textit{s}$. For each character $\textit{c}$, we convert it to a number $\textit{d} = \text{change}[\text{int}(\textit{c})]$. If a change has already occurred and $\textit{d} < \textit{c}$, it means we cannot continue changing, so we exit the loop immediately. Otherwise, if $\textit{d} > \textit{c}$, it means we can replace $\textit{c}$ with $\textit{d}$. At this point, we set $\textit{changed} = \text{true}$ and replace $\textit{s}[i]$ with $\textit{d}$.

Finally, we convert the character array $\textit{s}$ back to a string and return it.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $\textit{num}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumNumber(self, num: str, change: List[int]) -> str:
        s = list(num)
        changed = False
        for i, c in enumerate(s):
            d = str(change[int(c)])
            if changed and d < c:
                break
            if d > c:
                changed = True
                s[i] = d
        return "".join(s)
```

#### Java

```java
class Solution {
    public String maximumNumber(String num, int[] change) {
        char[] s = num.toCharArray();
        boolean changed = false;
        for (int i = 0; i < s.length; ++i) {
            char d = (char) (change[s[i] - '0'] + '0');
            if (changed && d < s[i]) {
                break;
            }
            if (d > s[i]) {
                changed = true;
                s[i] = d;
            }
        }
        return new String(s);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string maximumNumber(string num, vector<int>& change) {
        int n = num.size();
        bool changed = false;
        for (int i = 0; i < n; ++i) {
            char d = '0' + change[num[i] - '0'];
            if (changed && d < num[i]) {
                break;
            }
            if (d > num[i]) {
                changed = true;
                num[i] = d;
            }
        }
        return num;
    }
};
```

#### Go

```go
func maximumNumber(num string, change []int) string {
	s := []byte(num)
	changed := false
	for i, c := range num {
		d := byte('0' + change[c-'0'])
		if changed && d < s[i] {
			break
		}
		if d > s[i] {
			s[i] = d
			changed = true
		}
	}
	return string(s)
}
```

#### TypeScript

```ts
function maximumNumber(num: string, change: number[]): string {
    const s = num.split('');
    let changed = false;
    for (let i = 0; i < s.length; ++i) {
        const d = change[+s[i]].toString();
        if (changed && d < s[i]) {
            break;
        }
        if (d > s[i]) {
            s[i] = d;
            changed = true;
        }
    }
    return s.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_number(num: String, change: Vec<i32>) -> String {
        let mut s: Vec<char> = num.chars().collect();
        let mut changed = false;
        for i in 0..s.len() {
            let d = (change[s[i] as usize - '0' as usize] + '0' as i32) as u8 as char;
            if changed && d < s[i] {
                break;
            }
            if d > s[i] {
                changed = true;
                s[i] = d;
            }
        }
        s.into_iter().collect()
    }
}
```

#### JavaScript

```js
/**
 * @param {string} num
 * @param {number[]} change
 * @return {string}
 */
var maximumNumber = function (num, change) {
    const s = num.split('');
    let changed = false;
    for (let i = 0; i < s.length; ++i) {
        const d = change[+s[i]].toString();
        if (changed && d < s[i]) {
            break;
        }
        if (d > s[i]) {
            s[i] = d;
            changed = true;
        }
    }
    return s.join('');
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
