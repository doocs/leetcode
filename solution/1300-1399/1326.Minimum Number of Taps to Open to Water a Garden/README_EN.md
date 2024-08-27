---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/README_EN.md
rating: 1885
source: Weekly Contest 172 Q4
tags:
    - Greedy
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1326. Minimum Number of Taps to Open to Water a Garden](https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden)

[中文文档](/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/README.md)

## Description

<!-- description:start -->

<p>There is a one-dimensional garden on the x-axis. The garden starts at the point <code>0</code> and ends at the point <code>n</code>. (i.e., the&nbsp;length of the garden is <code>n</code>).</p>

<p>There are <code>n + 1</code> taps located at points <code>[0, 1, ..., n]</code> in the garden.</p>

<p>Given an integer <code>n</code> and an integer array <code>ranges</code> of length <code>n + 1</code> where <code>ranges[i]</code> (0-indexed) means the <code>i-th</code> tap can water the area <code>[i - ranges[i], i + ranges[i]]</code> if it was open.</p>

<p>Return <em>the minimum number of taps</em> that should be open to water the whole garden, If the garden cannot be watered return <strong>-1</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/images/1685_example_1.png" style="width: 525px; height: 255px;" />
<pre>
<strong>Input:</strong> n = 5, ranges = [3,4,1,1,0,0]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The tap at point 0 can cover the interval [-3,3]
The tap at point 1 can cover the interval [-3,5]
The tap at point 2 can cover the interval [1,3]
The tap at point 3 can cover the interval [2,4]
The tap at point 4 can cover the interval [4,4]
The tap at point 5 can cover the interval [5,5]
Opening Only the second tap will water the whole garden [0,5]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, ranges = [0,0,0,0]
<strong>Output:</strong> -1
<strong>Explanation:</strong> Even if you activate all the four taps you cannot water the whole garden.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>ranges.length == n + 1</code></li>
	<li><code>0 &lt;= ranges[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We note that for all taps that can cover a certain left endpoint, choosing the tap that can cover the farthest right endpoint is optimal.

Therefore, we can preprocess the array $ranges$. For the $i$-th tap, it can cover the left endpoint $l = \max(0, i - ranges[i])$ and the right endpoint $r = i + ranges[i]$. We calculate the position of the tap that can cover the left endpoint $l$ with the farthest right endpoint and record it in the array $last[i]$.

Then we define the following three variables:

-   Variable $ans$ represents the final answer, i.e., the minimum number of taps;
-   Variable $mx$ represents the farthest right endpoint that can currently be covered;
-   Variable $pre$ represents the farthest right endpoint covered by the previous tap.

We traverse all positions in the range $[0, \ldots, n-1]$. For the current position $i$, we use $last[i]$ to update $mx$, i.e., $mx = \max(mx, last[i])$.

-   If $mx \leq i$, it means the next position cannot be covered, so we return $-1$.
-   If $pre = i$, it means a new subinterval needs to be used, so we increment $ans$ by $1$ and update $pre = mx$.

After the traversal, we return $ans$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the garden.

Similar problems:

-   [45. Jump Game II](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0045.Jump%20Game%20II/README.md)
-   [55. Jump Game](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0055.Jump%20Game/README.md)
-   [1024. Video Stitching](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1024.Video%20Stitching/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minTaps(self, n: int, ranges: List[int]) -> int:
        last = [0] * (n + 1)
        for i, x in enumerate(ranges):
            l, r = max(0, i - x), i + x
            last[l] = max(last[l], r)

        ans = mx = pre = 0
        for i in range(n):
            mx = max(mx, last[i])
            if mx <= i:
                return -1
            if pre == i:
                ans += 1
                pre = mx
        return ans
```

#### Java

```java
class Solution {
    public int minTaps(int n, int[] ranges) {
        int[] last = new int[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            int l = Math.max(0, i - ranges[i]), r = i + ranges[i];
            last[l] = Math.max(last[l], r);
        }
        int ans = 0, mx = 0, pre = 0;
        for (int i = 0; i < n; ++i) {
            mx = Math.max(mx, last[i]);
            if (mx <= i) {
                return -1;
            }
            if (pre == i) {
                ++ans;
                pre = mx;
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
    int minTaps(int n, vector<int>& ranges) {
        vector<int> last(n + 1);
        for (int i = 0; i < n + 1; ++i) {
            int l = max(0, i - ranges[i]), r = i + ranges[i];
            last[l] = max(last[l], r);
        }
        int ans = 0, mx = 0, pre = 0;
        for (int i = 0; i < n; ++i) {
            mx = max(mx, last[i]);
            if (mx <= i) {
                return -1;
            }
            if (pre == i) {
                ++ans;
                pre = mx;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minTaps(n int, ranges []int) (ans int) {
	last := make([]int, n+1)
	for i, x := range ranges {
		l, r := max(0, i-x), i+x
		last[l] = max(last[l], r)
	}
	var pre, mx int
	for i, j := range last[:n] {
		mx = max(mx, j)
		if mx <= i {
			return -1
		}
		if pre == i {
			ans++
			pre = mx
		}
	}
	return
}
```

#### TypeScript

```ts
function minTaps(n: number, ranges: number[]): number {
    const last = new Array(n + 1).fill(0);
    for (let i = 0; i < n + 1; ++i) {
        const l = Math.max(0, i - ranges[i]);
        const r = i + ranges[i];
        last[l] = Math.max(last[l], r);
    }
    let ans = 0;
    let mx = 0;
    let pre = 0;
    for (let i = 0; i < n; ++i) {
        mx = Math.max(mx, last[i]);
        if (mx <= i) {
            return -1;
        }
        if (pre == i) {
            ++ans;
            pre = mx;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn min_taps(n: i32, ranges: Vec<i32>) -> i32 {
        let mut last = vec![0; (n + 1) as usize];
        let mut ans = 0;
        let mut mx = 0;
        let mut pre = 0;

        // Initialize the last vector
        for (i, &r) in ranges.iter().enumerate() {
            if (i as i32) - r >= 0 {
                last[((i as i32) - r) as usize] =
                    std::cmp::max(last[((i as i32) - r) as usize], (i as i32) + r);
            } else {
                last[0] = std::cmp::max(last[0], (i as i32) + r);
            }
        }

        for i in 0..n as usize {
            mx = std::cmp::max(mx, last[i]);
            if mx <= (i as i32) {
                return -1;
            }
            if pre == (i as i32) {
                ans += 1;
                pre = mx;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
