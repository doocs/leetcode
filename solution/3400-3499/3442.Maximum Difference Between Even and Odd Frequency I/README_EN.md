---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3442.Maximum%20Difference%20Between%20Even%20and%20Odd%20Frequency%20I/README_EN.md
rating: 1220
source: Weekly Contest 435 Q1
tags:
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [3442. Maximum Difference Between Even and Odd Frequency I](https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i)

[中文文档](/solution/3400-3499/3442.Maximum%20Difference%20Between%20Even%20and%20Odd%20Frequency%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>Your task is to find the <strong>maximum</strong> difference <code>diff = freq(a<sub>1</sub>) - freq(a<sub>2</sub>)</code> between the frequency of characters <code>a<sub>1</sub></code> and <code>a<sub>2</sub></code> in the string such that:</p>

<ul>
	<li><code>a<sub>1</sub></code> has an <strong>odd frequency</strong> in the string.</li>
	<li><code>a<sub>2</sub></code> has an <strong>even frequency</strong> in the string.</li>
</ul>

<p>Return this <strong>maximum</strong> difference.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaaaabbc&quot;</span></p>

<p><strong>Output:</strong> 3</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The character <code>&#39;a&#39;</code> has an <strong>odd frequency</strong> of <code><font face="monospace">5</font></code><font face="monospace">,</font> and <code>&#39;b&#39;</code> has an <strong>even frequency</strong> of <code><font face="monospace">2</font></code>.</li>
	<li>The maximum difference is <code>5 - 2 = 3</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcabcab&quot;</span></p>

<p><strong>Output:</strong> 1</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The character <code>&#39;a&#39;</code> has an <strong>odd frequency</strong> of <code><font face="monospace">3</font></code><font face="monospace">,</font> and <code>&#39;c&#39;</code> has an <strong>even frequency</strong> of <font face="monospace">2</font>.</li>
	<li>The maximum difference is <code>3 - 2 = 1</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
	<li><code>s</code> contains at least one character with an odd frequency and one with an even frequency.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can use a hash table or an array $\textit{cnt}$ to record the occurrences of each character in the string $s$. Then, we traverse $\textit{cnt}$ to find the maximum frequency $a$ of characters that appear an odd number of times and the minimum frequency $b$ of characters that appear an even number of times. Finally, we return $a - b$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(|\Sigma|)$, where $\Sigma$ is the character set. In this problem, $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDifference(self, s: str) -> int:
        cnt = Counter(s)
        a, b = 0, inf
        for v in cnt.values():
            if v % 2:
                a = max(a, v)
            else:
                b = min(b, v)
        return a - b
```

#### Java

```java
class Solution {
    public int maxDifference(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int a = 0, b = 1 << 30;
        for (int v : cnt) {
            if (v % 2 == 1) {
                a = Math.max(a, v);
            } else if (v > 0) {
                b = Math.min(b, v);
            }
        }
        return a - b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDifference(string s) {
        int cnt[26]{};
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        int a = 0, b = 1 << 30;
        for (int v : cnt) {
            if (v % 2 == 1) {
                a = max(a, v);
            } else if (v > 0) {
                b = min(b, v);
            }
        }
        return a - b;
    }
};
```

#### Go

```go
func maxDifference(s string) int {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	a, b := 0, 1<<30
	for _, v := range cnt {
		if v%2 == 1 {
			a = max(a, v)
		} else if v > 0 {
			b = min(b, v)
		}
	}
	return a - b
}
```

#### TypeScript

```ts
function maxDifference(s: string): number {
    const cnt: Record<string, number> = {};
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    let [a, b] = [0, Infinity];
    for (const [_, v] of Object.entries(cnt)) {
        if (v % 2 === 1) {
            a = Math.max(a, v);
        } else {
            b = Math.min(b, v);
        }
    }
    return a - b;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_difference(s: String) -> i32 {
        let mut cnt = [0; 26];
        for c in s.bytes() {
            cnt[(c - b'a') as usize] += 1;
        }
        let mut a = 0;
        let mut b = 1 << 30;
        for &v in cnt.iter() {
            if v % 2 == 1 {
                a = a.max(v);
            } else if v > 0 {
                b = b.min(v);
            }
        }
        a - b
    }
}
```

#### C#

```cs
public class Solution {
    public int MaxDifference(string s) {
        int[] cnt = new int[26];
        foreach (char c in s) {
            ++cnt[c - 'a'];
        }
        int a = 0, b = 1 << 30;
        foreach (int v in cnt) {
            if (v % 2 == 1) {
                a = Math.Max(a, v);
            } else if (v > 0) {
                b = Math.Min(b, v);
            }
        }
        return a - b;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
