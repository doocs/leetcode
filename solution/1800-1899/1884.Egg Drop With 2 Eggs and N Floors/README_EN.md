---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1884.Egg%20Drop%20With%202%20Eggs%20and%20N%20Floors/README_EN.md
tags:
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [1884. Egg Drop With 2 Eggs and N Floors](https://leetcode.com/problems/egg-drop-with-2-eggs-and-n-floors)

[中文文档](/solution/1800-1899/1884.Egg%20Drop%20With%202%20Eggs%20and%20N%20Floors/README.md)

## Description

<!-- description:start -->

<p>You are given <strong>two identical</strong> eggs and you have access to a building with <code>n</code> floors labeled from <code>1</code> to <code>n</code>.</p>

<p>You know that there exists a floor <code>f</code> where <code>0 &lt;= f &lt;= n</code> such that any egg dropped at a floor <strong>higher</strong> than <code>f</code> will <strong>break</strong>, and any egg dropped <strong>at or below</strong> floor <code>f</code> will <strong>not break</strong>.</p>

<p>In each move, you may take an <strong>unbroken</strong> egg and drop it from any floor <code>x</code> (where <code>1 &lt;= x &lt;= n</code>). If the egg breaks, you can no longer use it. However, if the egg does not break, you may <strong>reuse</strong> it in future moves.</p>

<p>Return <em>the <strong>minimum number of moves</strong> that you need to determine <strong>with certainty</strong> what the value of </em><code>f</code> is.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can drop the first egg from floor 1 and the second egg from floor 2.
If the first egg breaks, we know that f = 0.
If the second egg breaks but the first egg didn&#39;t, we know that f = 1.
Otherwise, if both eggs survive, we know that f = 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 100
<strong>Output:</strong> 14
<strong>Explanation:</strong> One optimal strategy is:
- Drop the 1st egg at floor 9. If it breaks, we know f is between 0 and 8. Drop the 2nd egg starting from floor 1 and going up one at a time to find f within 8 more drops. Total drops is 1 + 8 = 9.
- If the 1st egg does not break, drop the 1st egg again at floor 22. If it breaks, we know f is between 9 and 21. Drop the 2nd egg starting from floor 10 and going up one at a time to find f within 12 more drops. Total drops is 2 + 12 = 14.
- If the 1st egg does not break again, follow a similar process dropping the 1st egg from floors 34, 45, 55, 64, 72, 79, 85, 90, 94, 97, 99, and 100.
Regardless of the outcome, it takes at most 14 drops to determine f.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i]$ to represent the minimum number of operations to determine $f$ in $i$ floors with two eggs. Initially, $f[0] = 0$, and the rest $f[i] = +\infty$. The answer is $f[n]$.

Considering $f[i]$, we can enumerate the first egg thrown from the $j$-th floor, where $1 \leq j \leq i$. At this point, there are two cases:

-   The egg breaks. At this time, we have one egg left and need to determine $f$ in $j - 1$ floors, which requires $j - 1$ operations. Therefore, the total number of operations is $1 + (j - 1)$;
-   The egg does not break. At this time, we have two eggs left and need to determine $f$ in $i - j$ floors, which requires $f[i - j]$ operations. Therefore, the total number of operations is $1 + f[i - j]$.

In summary, we can obtain the state transition equation:

$$
f[i] = \min_{1 \leq j \leq i} \{1 + \max(j - 1, f[i - j])\}
$$

Finally, we return $f[n]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Where $n$ is the number of floors.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def twoEggDrop(self, n: int) -> int:
        f = [0] + [inf] * n
        for i in range(1, n + 1):
            for j in range(1, i + 1):
                f[i] = min(f[i], 1 + max(j - 1, f[i - j]))
        return f[n]
```

#### Java

```java
class Solution {
    public int twoEggDrop(int n) {
        int[] f = new int[n + 1];
        Arrays.fill(f, 1 << 29);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i] = Math.min(f[i], 1 + Math.max(j - 1, f[i - j]));
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int twoEggDrop(int n) {
        int f[n + 1];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i] = min(f[i], 1 + max(j - 1, f[i - j]));
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func twoEggDrop(n int) int {
	f := make([]int, n+1)
	for i := range f {
		f[i] = 1 << 29
	}
	f[0] = 0
	for i := 1; i <= n; i++ {
		for j := 1; j <= i; j++ {
			f[i] = min(f[i], 1+max(j-1, f[i-j]))
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function twoEggDrop(n: number): number {
    const f: number[] = Array(n + 1).fill(Infinity);
    f[0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= i; ++j) {
            f[i] = Math.min(f[i], 1 + Math.max(j - 1, f[i - j]));
        }
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
