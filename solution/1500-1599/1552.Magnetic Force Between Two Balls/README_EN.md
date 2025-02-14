---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1552.Magnetic%20Force%20Between%20Two%20Balls/README_EN.md
rating: 1919
source: Weekly Contest 202 Q3
tags:
    - Array
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [1552. Magnetic Force Between Two Balls](https://leetcode.com/problems/magnetic-force-between-two-balls)

[中文文档](/solution/1500-1599/1552.Magnetic%20Force%20Between%20Two%20Balls/README.md)

## Description

<!-- description:start -->

<p>In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has <code>n</code> empty baskets, the <code>i<sup>th</sup></code> basket is at <code>position[i]</code>, Morty has <code>m</code> balls and needs to distribute the balls into the baskets such that the <strong>minimum magnetic force</strong> between any two balls is <strong>maximum</strong>.</p>

<p>Rick stated that magnetic force between two different balls at positions <code>x</code> and <code>y</code> is <code>|x - y|</code>.</p>

<p>Given the integer array <code>position</code> and the integer <code>m</code>. Return <em>the required force</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1552.Magnetic%20Force%20Between%20Two%20Balls/images/q3v1.jpg" style="width: 562px; height: 195px;" />
<pre>
<strong>Input:</strong> position = [1,2,3,4,7], m = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> position = [5,4,3,2,1,1000000000], m = 2
<strong>Output:</strong> 999999999
<strong>Explanation:</strong> We can use baskets 1 and 1000000000.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == position.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= position[i] &lt;= 10<sup>9</sup></code></li>
	<li>All integers in <code>position</code> are <strong>distinct</strong>.</li>
	<li><code>2 &lt;= m &lt;= position.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We notice that the greater the minimum magnetic force between any two balls, the fewer balls can be placed, which exhibits monotonicity. We can use binary search to find the maximum minimum magnetic force that allows the number of balls not less than $m$ to be placed.

First, we sort the positions of the baskets, and then use binary search with the left boundary $l = 1$ and the right boundary $r = \textit{position}[n - 1]$, where $n$ is the number of baskets. In each binary search iteration, we calculate the midpoint $m = (l + r + 1) / 2$, and then determine if there is a way to place the balls such that the number of balls placed is not less than $m$.

The problem is transformed into determining whether a given minimum magnetic force $f$ can place $m$ balls. We can traverse the positions of the baskets from left to right, and if the distance between the position of the last ball and the current basket's position is greater than or equal to $f$, it indicates that a ball can be placed in the current basket. Finally, we check if the number of balls placed is not less than $m$.

The time complexity is $O(n \times \log n + n \times \log M)$, and the space complexity is $O(\log n)$. Here, $n$ and $M$ respectively represent the number of baskets and the maximum value of the basket positions.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistance(self, position: List[int], m: int) -> int:
        def check(f: int) -> bool:
            prev = -inf
            cnt = 0
            for curr in position:
                if curr - prev >= f:
                    prev = curr
                    cnt += 1
            return cnt < m

        position.sort()
        l, r = 1, position[-1]
        return bisect_left(range(l, r + 1), True, key=check)
```

#### Java

```java
class Solution {
    private int[] position;

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        this.position = position;
        int l = 1, r = position[position.length - 1];
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (count(mid) >= m) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private int count(int f) {
        int prev = position[0];
        int cnt = 1;
        for (int curr : position) {
            if (curr - prev >= f) {
                ++cnt;
                prev = curr;
            }
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDistance(vector<int>& position, int m) {
        ranges::sort(position);
        int l = 1, r = position.back();
        auto count = [&](int f) {
            int prev = position[0];
            int cnt = 1;
            for (int& curr : position) {
                if (curr - prev >= f) {
                    prev = curr;
                    cnt++;
                }
            }
            return cnt;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (count(mid) >= m) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func maxDistance(position []int, m int) int {
	sort.Ints(position)
	return sort.Search(position[len(position)-1], func(f int) bool {
		prev := position[0]
		cnt := 1
		for _, curr := range position {
			if curr-prev >= f {
				cnt++
				prev = curr
			}
		}
		return cnt < m
	}) - 1
}
```

#### TypeScript

```ts
function maxDistance(position: number[], m: number): number {
    position.sort((a, b) => a - b);
    let [l, r] = [1, position.at(-1)!];
    const count = (f: number): number => {
        let cnt = 1;
        let prev = position[0];
        for (const curr of position) {
            if (curr - prev >= f) {
                cnt++;
                prev = curr;
            }
        }
        return cnt;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (count(mid) >= m) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

#### JavaScript

```js
/**
 * @param {number[]} position
 * @param {number} m
 * @return {number}
 */
var maxDistance = function (position, m) {
    position.sort((a, b) => a - b);
    let [l, r] = [1, position.at(-1)];
    const count = f => {
        let cnt = 1;
        let prev = position[0];
        for (const curr of position) {
            if (curr - prev >= f) {
                cnt++;
                prev = curr;
            }
        }
        return cnt;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (count(mid) >= m) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
