---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1482.Minimum%20Number%20of%20Days%20to%20Make%20m%20Bouquets/README_EN.md
rating: 1945
source: Weekly Contest 193 Q3
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [1482. Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets)

[中文文档](/solution/1400-1499/1482.Minimum%20Number%20of%20Days%20to%20Make%20m%20Bouquets/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>bloomDay</code>, an integer <code>m</code> and an integer <code>k</code>.</p>

<p>You want to make <code>m</code> bouquets. To make a bouquet, you need to use <code>k</code> <strong>adjacent flowers</strong> from the garden.</p>

<p>The garden consists of <code>n</code> flowers, the <code>i<sup>th</sup></code> flower will bloom in the <code>bloomDay[i]</code> and then can be used in <strong>exactly one</strong> bouquet.</p>

<p>Return <em>the minimum number of days you need to wait to be able to make </em><code>m</code><em> bouquets from the garden</em>. If it is impossible to make m bouquets return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> bloomDay = [1,10,3,10,2], m = 3, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
We need 3 bouquets each should contain 1 flower.
After day 1: [x, _, _, _, _]   // we can only make one bouquet.
After day 2: [x, _, _, _, x]   // we can only make two bouquets.
After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> bloomDay = [1,10,3,10,2], m = 3, k = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
<strong>Output:</strong> 12
<strong>Explanation:</strong> We need 2 bouquets each should have 3 flowers.
Here is the garden after the 7 and 12 days:
After day 7: [x, x, x, x, _, x, x]
We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
After day 12: [x, x, x, x, x, x, x]
It is obvious that we can make two bouquets in different ways.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>bloomDay.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= bloomDay[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

According to the problem description, if a day $t$ can satisfy making $m$ bouquets, then for any $t' > t$, it can also satisfy making $m$ bouquets. Therefore, we can use binary search to find the minimum day that satisfies making $m$ bouquets.

Let $mx$ be the maximum blooming day in the garden. Next, we define the left boundary of the binary search as $l = 1$ and the right boundary as $r = mx + 1$.

Then, we perform binary search. For each middle value $\textit{mid} = \frac{l + r}{2}$, we check if it is possible to make $m$ bouquets. If it is possible, we update the right boundary $r$ to $\textit{mid}$; otherwise, we update the left boundary $l$ to $\textit{mid} + 1$.

Finally, when $l = r$, the binary search ends. At this point, if $l > mx$, it means it is not possible to make $m$ bouquets, and we return $-1$; otherwise, we return $l$.

Therefore, the problem is reduced to checking if a day $\textit{days}$ can make $m$ bouquets.

We can use a function $\text{check}(\textit{days})$ to determine if it is possible to make $m$ bouquets. Specifically, we traverse each flower in the garden from left to right. If the blooming day of the current flower is less than or equal to $\textit{days}$, we add the current flower to the current bouquet; otherwise, we clear the current bouquet. When the number of flowers in the current bouquet equals $k$, we increment the bouquet count and clear the current bouquet. Finally, we check if the bouquet count is greater than or equal to $m$. If it is, it means it is possible to make $m$ bouquets; otherwise, it is not possible.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the number of flowers in the garden and the maximum blooming day, respectively. In this problem, $M \leq 10^9$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDays(self, bloomDay: List[int], m: int, k: int) -> int:
        def check(days: int) -> int:
            cnt = cur = 0
            for x in bloomDay:
                cur = cur + 1 if x <= days else 0
                if cur == k:
                    cnt += 1
                    cur = 0
            return cnt >= m

        mx = max(bloomDay)
        l = bisect_left(range(mx + 2), True, key=check)
        return -1 if l > mx else l
```

#### Java

```java
class Solution {
    private int[] bloomDay;
    private int m, k;

    public int minDays(int[] bloomDay, int m, int k) {
        this.bloomDay = bloomDay;
        this.m = m;
        this.k = k;
        final int mx = (int) 1e9;
        int l = 1, r = mx + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > mx ? -1 : l;
    }

    private boolean check(int days) {
        int cnt = 0, cur = 0;
        for (int x : bloomDay) {
            cur = x <= days ? cur + 1 : 0;
            if (cur == k) {
                ++cnt;
                cur = 0;
            }
        }
        return cnt >= m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDays(vector<int>& bloomDay, int m, int k) {
        int mx = ranges::max(bloomDay);
        int l = 1, r = mx + 1;
        auto check = [&](int days) {
            int cnt = 0, cur = 0;
            for (int x : bloomDay) {
                cur = x <= days ? cur + 1 : 0;
                if (cur == k) {
                    cnt++;
                    cur = 0;
                }
            }
            return cnt >= m;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > mx ? -1 : l;
    }
};
```

#### Go

```go
func minDays(bloomDay []int, m int, k int) int {
	mx := slices.Max(bloomDay)
	if l := sort.Search(mx+2, func(days int) bool {
		cnt, cur := 0, 0
		for _, x := range bloomDay {
			if x <= days {
				cur++
				if cur == k {
					cnt++
					cur = 0
				}
			} else {
				cur = 0
			}
		}
		return cnt >= m
	}); l <= mx {
		return l
	}
	return -1

}
```

#### TypeScript

```ts
function minDays(bloomDay: number[], m: number, k: number): number {
    const mx = Math.max(...bloomDay);
    let [l, r] = [1, mx + 1];
    const check = (days: number): boolean => {
        let [cnt, cur] = [0, 0];
        for (const x of bloomDay) {
            cur = x <= days ? cur + 1 : 0;
            if (cur === k) {
                cnt++;
                cur = 0;
            }
        }
        return cnt >= m;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l > mx ? -1 : l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
