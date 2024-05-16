---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0875.Koko%20Eating%20Bananas/README_EN.md
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas)

[中文文档](/solution/0800-0899/0875.Koko%20Eating%20Bananas/README.md)

## Description

<p>Koko loves to eat bananas. There are <code>n</code> piles of bananas, the <code>i<sup>th</sup></code> pile has <code>piles[i]</code> bananas. The guards have gone and will come back in <code>h</code> hours.</p>

<p>Koko can decide her bananas-per-hour eating speed of <code>k</code>. Each hour, she chooses some pile of bananas and eats <code>k</code> bananas from that pile. If the pile has less than <code>k</code> bananas, she eats all of them instead and will not eat any more bananas during this hour.</p>

<p>Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.</p>

<p>Return <em>the minimum integer</em> <code>k</code> <em>such that she can eat all the bananas within</em> <code>h</code> <em>hours</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> piles = [3,6,7,11], h = 8
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [30,11,23,4,20], h = 5
<strong>Output:</strong> 30
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> piles = [30,11,23,4,20], h = 6
<strong>Output:</strong> 23
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= piles.length &lt;= 10<sup>4</sup></code></li>
	<li><code>piles.length &lt;= h &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We notice that if Koko can eat all the bananas at a speed of $k$ within $h$ hours, then she can also eat all the bananas at a speed of $k' > k$ within $h$ hours. This shows monotonicity, so we can use binary search to find the smallest $k$ that satisfies the condition.

We define the left boundary of the binary search as $l = 1$, and the right boundary as $r = \max(\text{piles})$. For each binary search, we take the middle value $mid = \frac{l + r}{2}$, and then calculate the time $s$ required to eat bananas at a speed of $mid$. If $s \leq h$, it means that the speed of $mid$ can meet the condition, and we update the right boundary $r$ to $mid$; otherwise, we update the left boundary $l$ to $mid + 1$. Finally, when $l = r$, we find the smallest $k$ that satisfies the condition.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the length and maximum value of the array `piles` respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        def check(k: int) -> bool:
            return sum((x + k - 1) // k for x in piles) <= h

        return 1 + bisect_left(range(1, max(piles) + 1), True, key=check)
```

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = (int) 1e9;
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            for (int x : piles) {
                s += (x + mid - 1) / mid;
            }
            if (s <= h) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    int minEatingSpeed(vector<int>& piles, int h) {
        int l = 1, r = ranges::max(piles);
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            for (int x : piles) {
                s += (x + mid - 1) / mid;
            }
            if (s <= h) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

```go
func minEatingSpeed(piles []int, h int) int {
	return 1 + sort.Search(slices.Max(piles), func(k int) bool {
		k++
		s := 0
		for _, x := range piles {
			s += (x + k - 1) / k
		}
		return s <= h
	})
}
```

```ts
function minEatingSpeed(piles: number[], h: number): number {
    let [l, r] = [1, Math.max(...piles)];
    while (l < r) {
        const mid = (l + r) >> 1;
        const s = piles.map(x => Math.ceil(x / mid)).reduce((a, b) => a + b);
        if (s <= h) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

```rust
impl Solution {
    pub fn min_eating_speed(piles: Vec<i32>, h: i32) -> i32 {
        let mut l = 1;
        let mut r = *piles.iter().max().unwrap_or(&0);
        while l < r {
            let mid = (l + r) >> 1;
            let mut s = 0;
            for x in piles.iter() {
                s += (x + mid - 1) / mid;
            }
            if s <= h {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}
```

```cs
public class Solution {
    public int MinEatingSpeed(int[] piles, int h) {
        int l = 1, r = (int) 1e9;
        while (l < r) {
            int mid = (l + r) >> 1;
            int s = 0;
            foreach (int x in piles) {
                s += (x + mid - 1) / mid;
            }
            if (s <= h) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
