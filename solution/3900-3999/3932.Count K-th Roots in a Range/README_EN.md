---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3932.Count%20K-th%20Roots%20in%20a%20Range/README_EN.md
---

<!-- problem:start -->

# [3932. Count K-th Roots in a Range](https://leetcode.com/problems/count-k-th-roots-in-a-range)

[中文文档](/solution/3900-3999/3932.Count%20K-th%20Roots%20in%20a%20Range/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>l</code>, <code>r</code>, and <code>k</code>.</p>

<p>An integer <code>y</code> is said to be a <strong>perfect k<sup>th</sup> power</strong> if there exists an integer <code>x</code> such that <code>y = x<sup>k</sup></code>.</p>

<p>Return the number of integers <code>y</code> in the range <code>[l, r]</code> (inclusive) that are <strong>perfect k<sup>th</sup> powers</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 1, r = 9, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>
The perfect cubes in the range <code>[1, 9]</code> are:

<ul>
	<li><code>1 = 1<sup>3</sup></code></li>
	<li><code>8 = 2<sup>3</sup></code></li>
</ul>
Hence, the answer is 2.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 8, r = 30, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>
The perfect squares in the range <code>[8, 30]</code> are:

<ul>
	<li><code>9 = 3<sup>2</sup></code></li>
	<li><code>16 = 4<sup>2</sup></code></li>
	<li><code>25 = 5<sup>2</sup></code></li>
</ul>
Hence, the answer is 3.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= l &lt;= r &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 30</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

First, we check if $k$ equals 1. If it does, the count of perfect 1st powers in the range is the count of integers in the range, which is $r - l + 1$.

Otherwise, we enumerate integers $x$, compute $y = x^k$. If $y$ exceeds $r$, we stop enumeration. If $y$ is within the range $[l, r]$, we increment the answer by 1.

The time complexity is $O(r^{1/k} \cdot k)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countKthRoots(self, l: int, r: int, k: int) -> int:
        if k == 1:
            return r - l + 1
        ans = 0
        for x in count():
            y = x**k
            if y > r:
                break
            if l <= y <= r:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countKthRoots(int l, int r, int k) {
        if (k == 1) {
            return r - l + 1;
        }
        int ans = 0;
        for (int x = 0;; x++) {
            long y = 1;
            for (int i = 0; i < k; i++) {
                y *= x;
                if (y > r) {
                    break;
                }
            }
            if (y > r) {
                break;
            }
            if (l <= y && y <= r) {
                ans++;
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
    int countKthRoots(int l, int r, int k) {
        if (k == 1) {
            return r - l + 1;
        }
        int ans = 0;
        for (int x = 0;; x++) {
            long long y = 1;
            for (int i = 0; i < k; i++) {
                y *= x;
                if (y > r) {
                    break;
                }
            }
            if (y > r) {
                break;
            }
            if (l <= y && y <= r) {
                ans++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countKthRoots(l int, r int, k int) int {
	if k == 1 {
		return r - l + 1
	}
	ans := 0
	for x := 0; ; x++ {
		y := 1
		for i := 0; i < k; i++ {
			y *= x
			if y > r {
				break
			}
		}
		if y > r {
			break
		}
		if l <= y && y <= r {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countKthRoots(l: number, r: number, k: number): number {
    if (k === 1) {
        return r - l + 1;
    }
    let ans = 0;
    for (let x = 0; ; x++) {
        let y = 1;
        for (let i = 0; i < k; i++) {
            y *= x;
            if (y > r) {
                break;
            }
        }
        if (y > r) {
            break;
        }
        if (l <= y && y <= r) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
