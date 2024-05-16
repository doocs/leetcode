---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2048.Next%20Greater%20Numerically%20Balanced%20Number/README_EN.md
rating: 1734
source: Weekly Contest 264 Q2
tags:
    - Math
    - Backtracking
    - Enumeration
---

<!-- problem:start -->

# [2048. Next Greater Numerically Balanced Number](https://leetcode.com/problems/next-greater-numerically-balanced-number)

[中文文档](/solution/2000-2099/2048.Next%20Greater%20Numerically%20Balanced%20Number/README.md)

## Description

<p>An integer <code>x</code> is <strong>numerically balanced</strong> if for every digit <code>d</code> in the number <code>x</code>, there are <strong>exactly</strong> <code>d</code> occurrences of that digit in <code>x</code>.</p>

<p>Given an integer <code>n</code>, return <em>the <strong>smallest numerically balanced</strong> number <strong>strictly greater</strong> than </em><code>n</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 22
<strong>Explanation:</strong> 
22 is numerically balanced since:
- The digit 2 occurs 2 times. 
It is also the smallest numerically balanced number strictly greater than 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1000
<strong>Output:</strong> 1333
<strong>Explanation:</strong> 
1333 is numerically balanced since:
- The digit 1 occurs 1 time.
- The digit 3 occurs 3 times. 
It is also the smallest numerically balanced number strictly greater than 1000.
Note that 1022 cannot be the answer because 0 appeared more than 0 times.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3000
<strong>Output:</strong> 3133
<strong>Explanation:</strong> 
3133 is numerically balanced since:
- The digit 1 occurs 1 time.
- The digit 3 occurs 3 times.
It is also the smallest numerically balanced number strictly greater than 3000.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We note that the range of $n$ in the problem is $[0, 10^6]$, and one of the balanced numbers greater than $10^6$ is $1224444$. Therefore, we directly enumerate $x \in [n + 1, ..]$ and then judge whether $x$ is a balanced number. The enumerated $x$ will definitely not exceed $1224444$.

The time complexity is $O(M - n)$, where $M = 1224444$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def nextBeautifulNumber(self, n: int) -> int:
        for x in count(n + 1):
            y = x
            cnt = [0] * 10
            while y:
                y, v = divmod(y, 10)
                cnt[v] += 1
            if all(v == 0 or i == v for i, v in enumerate(cnt)):
                return x
```

```java
class Solution {
    public int nextBeautifulNumber(int n) {
        for (int x = n + 1;; ++x) {
            int[] cnt = new int[10];
            for (int y = x; y > 0; y /= 10) {
                ++cnt[y % 10];
            }
            boolean ok = true;
            for (int y = x; y > 0; y /= 10) {
                if (y % 10 != cnt[y % 10]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return x;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    int nextBeautifulNumber(int n) {
        for (int x = n + 1;; ++x) {
            int cnt[10]{};
            for (int y = x; y > 0; y /= 10) {
                ++cnt[y % 10];
            }
            bool ok = true;
            for (int y = x; y > 0; y /= 10) {
                if (y % 10 != cnt[y % 10]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return x;
            }
        }
    }
};
```

```go
func nextBeautifulNumber(n int) int {
	for x := n + 1; ; x++ {
		cnt := [10]int{}
		for y := x; y > 0; y /= 10 {
			cnt[y%10]++
		}
		ok := true
		for y := x; y > 0; y /= 10 {
			if y%10 != cnt[y%10] {
				ok = false
				break
			}
		}
		if ok {
			return x
		}
	}
}
```

```ts
function nextBeautifulNumber(n: number): number {
    for (let x = n + 1; ; ++x) {
        const cnt: number[] = Array(10).fill(0);
        for (let y = x; y > 0; y = (y / 10) | 0) {
            cnt[y % 10]++;
        }
        let ok = true;
        for (let i = 0; i < 10; ++i) {
            if (cnt[i] && cnt[i] !== i) {
                ok = false;
                break;
            }
        }
        if (ok) {
            return x;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
