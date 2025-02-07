---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1864.Minimum%20Number%20of%20Swaps%20to%20Make%20the%20Binary%20String%20Alternating/README_EN.md
rating: 1600
source: Weekly Contest 241 Q2
tags:
    - Greedy
    - String
---

<!-- problem:start -->

# [1864. Minimum Number of Swaps to Make the Binary String Alternating](https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating)

[中文文档](/solution/1800-1899/1864.Minimum%20Number%20of%20Swaps%20to%20Make%20the%20Binary%20String%20Alternating/README.md)

## Description

<!-- description:start -->

<p>Given a binary string <code>s</code>, return <em>the <strong>minimum</strong> number of character swaps to make it <strong>alternating</strong>, or </em><code>-1</code><em> if it is impossible.</em></p>

<p>The string is called <strong>alternating</strong> if no two adjacent characters are equal. For example, the strings <code>&quot;010&quot;</code> and <code>&quot;1010&quot;</code> are alternating, while the string <code>&quot;0100&quot;</code> is not.</p>

<p>Any two characters may be swapped, even if they are&nbsp;<strong>not adjacent</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;111000&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> Swap positions 1 and 4: &quot;1<u>1</u>10<u>0</u>0&quot; -&gt; &quot;1<u>0</u>10<u>1</u>0&quot;
The string is now alternating.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;010&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> The string is already alternating, no swaps are needed.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1110&quot;
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

First, we count the number of characters $0$ and $1$ in the string $\textit{s}$, denoted as $n_0$ and $n_1$ respectively.

If the absolute difference between $n_0$ and $n_1$ is greater than $1$, it is impossible to form an alternating string, so we return $-1$.

If $n_0$ and $n_1$ are equal, we can calculate the number of swaps needed to convert the string into an alternating string starting with $0$ and starting with $1$, and take the minimum value.

If $n_0$ and $n_1$ are not equal, we only need to calculate the number of swaps needed to convert the string into an alternating string starting with the character that appears more frequently.

The problem is reduced to calculating the number of swaps needed to convert the string $\textit{s}$ into an alternating string starting with character $c$.

We define a function $\text{calc}(c)$, which represents the number of swaps needed to convert the string $\textit{s}$ into an alternating string starting with character $c$. We traverse the string $\textit{s}$, and for each position $i$, if the parity of $i$ is different from $c$, we need to swap the character at this position, incrementing the counter by $1$. Since each swap makes two positions have the same character, the final number of swaps is half of the counter.

The time complexity is $O(n)$, where $n$ is the length of the string $\textit{s}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSwaps(self, s: str) -> int:
        def calc(c: int) -> int:
            return sum((c ^ i & 1) != x for i, x in enumerate(map(int, s))) // 2

        n0 = s.count("0")
        n1 = len(s) - n0
        if abs(n0 - n1) > 1:
            return -1
        if n0 == n1:
            return min(calc(0), calc(1))
        return calc(0 if n0 > n1 else 1)
```

#### Java

```java
class Solution {
    private char[] s;

    public int minSwaps(String s) {
        this.s = s.toCharArray();
        int n1 = 0;
        for (char c : this.s) {
            n1 += (c - '0');
        }
        int n0 = this.s.length - n1;
        if (Math.abs(n0 - n1) > 1) {
            return -1;
        }
        if (n0 == n1) {
            return Math.min(calc(0), calc(1));
        }
        return calc(n0 > n1 ? 0 : 1);
    }

    private int calc(int c) {
        int cnt = 0;
        for (int i = 0; i < s.length; ++i) {
            int x = s[i] - '0';
            if ((i & 1 ^ c) != x) {
                ++cnt;
            }
        }
        return cnt / 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSwaps(string s) {
        int n0 = ranges::count(s, '0');
        int n1 = s.size() - n0;
        if (abs(n0 - n1) > 1) {
            return -1;
        }
        auto calc = [&](int c) -> int {
            int cnt = 0;
            for (int i = 0; i < s.size(); ++i) {
                int x = s[i] - '0';
                if ((i & 1 ^ c) != x) {
                    ++cnt;
                }
            }
            return cnt / 2;
        };
        if (n0 == n1) {
            return min(calc(0), calc(1));
        }
        return calc(n0 > n1 ? 0 : 1);
    }
};
```

#### Go

```go
func minSwaps(s string) int {
	n0 := strings.Count(s, "0")
	n1 := len(s) - n0
	if abs(n0-n1) > 1 {
		return -1
	}
	calc := func(c int) int {
		cnt := 0
		for i, ch := range s {
			x := int(ch - '0')
			if i&1^c != x {
				cnt++
			}
		}
		return cnt / 2
	}
	if n0 == n1 {
		return min(calc(0), calc(1))
	}
	if n0 > n1 {
		return calc(0)
	}
	return calc(1)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minSwaps(s: string): number {
    const n0 = (s.match(/0/g) || []).length;
    const n1 = s.length - n0;
    if (Math.abs(n0 - n1) > 1) {
        return -1;
    }
    const calc = (c: number): number => {
        let cnt = 0;
        for (let i = 0; i < s.length; i++) {
            const x = +s[i];
            if (((i & 1) ^ c) !== x) {
                cnt++;
            }
        }
        return Math.floor(cnt / 2);
    };
    if (n0 === n1) {
        return Math.min(calc(0), calc(1));
    }
    return calc(n0 > n1 ? 0 : 1);
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var minSwaps = function (s) {
    const n0 = (s.match(/0/g) || []).length;
    const n1 = s.length - n0;
    if (Math.abs(n0 - n1) > 1) {
        return -1;
    }
    const calc = c => {
        let cnt = 0;
        for (let i = 0; i < s.length; i++) {
            const x = +s[i];
            if (((i & 1) ^ c) !== x) {
                cnt++;
            }
        }
        return Math.floor(cnt / 2);
    };
    if (n0 === n1) {
        return Math.min(calc(0), calc(1));
    }
    return calc(n0 > n1 ? 0 : 1);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
