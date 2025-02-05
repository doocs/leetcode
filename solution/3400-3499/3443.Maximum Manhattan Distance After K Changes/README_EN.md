---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3443.Maximum%20Manhattan%20Distance%20After%20K%20Changes/README_EN.md
tags:
    - Hash Table
    - Math
    - String
    - Counting
---

<!-- problem:start -->

# [3443. Maximum Manhattan Distance After K Changes](https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes)

[中文文档](/solution/3400-3499/3443.Maximum%20Manhattan%20Distance%20After%20K%20Changes/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of the characters <code>&#39;N&#39;</code>, <code>&#39;S&#39;</code>, <code>&#39;E&#39;</code>, and <code>&#39;W&#39;</code>, where <code>s[i]</code> indicates movements in an infinite grid:</p>

<ul>
	<li><code>&#39;N&#39;</code> : Move north by 1 unit.</li>
	<li><code>&#39;S&#39;</code> : Move south by 1 unit.</li>
	<li><code>&#39;E&#39;</code> : Move east by 1 unit.</li>
	<li><code>&#39;W&#39;</code> : Move west by 1 unit.</li>
</ul>

<p>Initially, you are at the origin <code>(0, 0)</code>. You can change <strong>at most</strong> <code>k</code> characters to any of the four directions.</p>

<p>Find the <strong>maximum</strong> <strong>Manhattan distance</strong> from the origin that can be achieved <strong>at any time</strong> while performing the movements <strong>in order</strong>.</p>
The <strong>Manhattan Distance</strong> between two cells <code>(x<sub>i</sub>, y<sub>i</sub>)</code> and <code>(x<sub>j</sub>, y<sub>j</sub>)</code> is <code>|x<sub>i</sub> - x<sub>j</sub>| + |y<sub>i</sub> - y<sub>j</sub>|</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;NWSE&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Change <code>s[2]</code> from <code>&#39;S&#39;</code> to <code>&#39;N&#39;</code>. The string <code>s</code> becomes <code>&quot;NWNE&quot;</code>.</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Movement</th>
			<th style="border: 1px solid black;">Position (x, y)</th>
			<th style="border: 1px solid black;">Manhattan Distance</th>
			<th style="border: 1px solid black;">Maximum</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">s[0] == &#39;N&#39;</td>
			<td style="border: 1px solid black;">(0, 1)</td>
			<td style="border: 1px solid black;">0 + 1 = 1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">s[1] == &#39;W&#39;</td>
			<td style="border: 1px solid black;">(-1, 1)</td>
			<td style="border: 1px solid black;">1 + 1 = 2</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">s[2] == &#39;N&#39;</td>
			<td style="border: 1px solid black;">(-1, 2)</td>
			<td style="border: 1px solid black;">1 + 2 = 3</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">s[3] == &#39;E&#39;</td>
			<td style="border: 1px solid black;">(0, 2)</td>
			<td style="border: 1px solid black;">0 + 2 = 2</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>

<p>The maximum Manhattan distance from the origin that can be achieved is 3. Hence, 3 is the output.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;NSWWEW&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>Change <code>s[1]</code> from <code>&#39;S&#39;</code> to <code>&#39;N&#39;</code>, and <code>s[4]</code> from <code>&#39;E&#39;</code> to <code>&#39;W&#39;</code>. The string <code>s</code> becomes <code>&quot;NNWWWW&quot;</code>.</p>

<p>The maximum Manhattan distance from the origin that can be achieved is 6. Hence, 6 is the output.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code> consists of only <code>&#39;N&#39;</code>, <code>&#39;S&#39;</code>, <code>&#39;E&#39;</code>, and <code>&#39;W&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Greedy

We can enumerate four cases: $\textit{SE}$, $\textit{SW}$, $\textit{NE}$, and $\textit{NW}$, and then calculate the maximum Manhattan distance for each case.

We define a function $\text{calc}(a, b)$ to calculate the maximum Manhattan distance when the effective directions are $\textit{a}$ and $\textit{b}$.

We define a variable $\textit{mx}$ to record the current Manhattan distance, a variable $\textit{cnt}$ to record the number of changes made, and initialize the answer $\textit{ans}$ to $0$.

Traverse the string $\textit{s}$. If the current character is $\textit{a}$ or $\textit{b}$, increment $\textit{mx}$ by $1$. Otherwise, if $\textit{cnt} < k$, increment $\textit{mx}$ by $1$ and increment $\textit{cnt}$ by $1$. Otherwise, decrement $\textit{mx}$ by $1$. Then update $\textit{ans} = \max(\textit{ans}, \textit{mx})$.

Finally, return the maximum value among the four cases.

The time complexity is $O(n)$, where $n$ is the length of the string $\textit{s}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistance(self, s: str, k: int) -> int:
        def calc(a: str, b: str) -> int:
            ans = mx = cnt = 0
            for c in s:
                if c == a or c == b:
                    mx += 1
                elif cnt < k:
                    cnt += 1
                    mx += 1
                else:
                    mx -= 1
                ans = max(ans, mx)
            return ans

        a = calc("S", "E")
        b = calc("S", "W")
        c = calc("N", "E")
        d = calc("N", "W")
        return max(a, b, c, d)
```

#### Java

```java
class Solution {
    private char[] s;
    private int k;

    public int maxDistance(String s, int k) {
        this.s = s.toCharArray();
        this.k = k;
        int a = calc('S', 'E');
        int b = calc('S', 'W');
        int c = calc('N', 'E');
        int d = calc('N', 'W');
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    private int calc(char a, char b) {
        int ans = 0, mx = 0, cnt = 0;
        for (char c : s) {
            if (c == a || c == b) {
                ++mx;
            } else if (cnt < k) {
                ++mx;
                ++cnt;
            } else {
                --mx;
            }
            ans = Math.max(ans, mx);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDistance(string s, int k) {
        auto calc = [&](char a, char b) {
            int ans = 0, mx = 0, cnt = 0;
            for (char c : s) {
                if (c == a || c == b) {
                    ++mx;
                } else if (cnt < k) {
                    ++mx;
                    ++cnt;
                } else {
                    --mx;
                }
                ans = max(ans, mx);
            }
            return ans;
        };
        int a = calc('S', 'E');
        int b = calc('S', 'W');
        int c = calc('N', 'E');
        int d = calc('N', 'W');
        return max({a, b, c, d});
    }
};
```

#### Go

```go
func maxDistance(s string, k int) int {
	calc := func(a rune, b rune) int {
		var ans, mx, cnt int
		for _, c := range s {
			if c == a || c == b {
				mx++
			} else if cnt < k {
				mx++
				cnt++
			} else {
				mx--
			}
			ans = max(ans, mx)
		}
		return ans
	}
	a := calc('S', 'E')
	b := calc('S', 'W')
	c := calc('N', 'E')
	d := calc('N', 'W')
	return max(a, b, c, d)
}
```

#### TypeScript

```ts
function maxDistance(s: string, k: number): number {
    const calc = (a: string, b: string): number => {
        let [ans, mx, cnt] = [0, 0, 0];
        for (const c of s) {
            if (c === a || c === b) {
                ++mx;
            } else if (cnt < k) {
                ++mx;
                ++cnt;
            } else {
                --mx;
            }
            ans = Math.max(ans, mx);
        }
        return ans;
    };
    const a = calc('S', 'E');
    const b = calc('S', 'W');
    const c = calc('N', 'E');
    const d = calc('N', 'W');
    return Math.max(a, b, c, d);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
