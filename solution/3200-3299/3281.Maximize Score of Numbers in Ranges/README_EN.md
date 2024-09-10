---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3281.Maximize%20Score%20of%20Numbers%20in%20Ranges/README_EN.md
tags:
    - Greedy
    - Array
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [3281. Maximize Score of Numbers in Ranges](https://leetcode.com/problems/maximize-score-of-numbers-in-ranges)

[中文文档](/solution/3200-3299/3281.Maximize%20Score%20of%20Numbers%20in%20Ranges/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>start</code> and an integer <code>d</code>, representing <code>n</code> intervals <code>[start[i], start[i] + d]</code>.</p>

<p>You are asked to choose <code>n</code> integers where the <code>i<sup>th</sup></code> integer must belong to the <code>i<sup>th</sup></code> interval. The <strong>score</strong> of the chosen integers is defined as the <strong>minimum</strong> absolute difference between any two integers that have been chosen.</p>

<p>Return the <strong>maximum</strong> <em>possible score</em> of the chosen integers.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">start = [6,0,3], d = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum possible score can be obtained by choosing integers: 8, 0, and 4. The score of these chosen integers is <code>min(|8 - 0|, |8 - 4|, |0 - 4|)</code> which equals 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">start = [2,6,13,13], d = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum possible score can be obtained by choosing integers: 2, 7, 13, and 18. The score of these chosen integers is <code>min(|2 - 7|, |2 - 13|, |2 - 18|, |7 - 13|, |7 - 18|, |13 - 18|)</code> which equals 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= start.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= start[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= d &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search

We can first sort the $\textit{start}$ array. Then, we consider selecting integers from left to right, where the score is equal to the minimum difference between any two adjacent selected integers.

If a difference $x$ satisfies the condition, then any $x' < x$ will also satisfy the condition. Therefore, we can use binary search to find the largest difference that satisfies the condition.

We define the left boundary of the binary search as $l = 0$ and the right boundary as $r = \textit{start}[-1] + d - \textit{start}[0]$. Each time, we take the middle value $mid = \left\lfloor \frac{l + r + 1}{2} \right\rfloor$ and check whether it satisfies the condition.

We define a function $\text{check}(mi)$ to determine whether the condition is satisfied, implemented as follows:

-   We define a variable $\textit{last} = -\infty$, representing the last selected integer.
-   We traverse the $\textit{start}$ array. If $\textit{last} + \textit{mi} > \textit{st} + d$, it means we cannot select the integer $\textit{st}$, and we return $\text{false}$. Otherwise, we update $\textit{last} = \max(\textit{st}, \textit{last} + \textit{mi})$.
-   If we traverse the entire $\textit{start}$ array and all conditions are satisfied, we return $\text{true}$.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the length and the maximum value of the $\textit{start}$ array, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPossibleScore(self, start: List[int], d: int) -> int:
        def check(mi: int) -> bool:
            last = -inf
            for st in start:
                if last + mi > st + d:
                    return False
                last = max(st, last + mi)
            return True

        start.sort()
        l, r = 0, start[-1] + d - start[0]
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
```

#### Java

```java
class Solution {
    private int[] start;
    private int d;

    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        this.start = start;
        this.d = d;
        int n = start.length;
        int l = 0, r = start[n - 1] + d - start[0];
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int mi) {
        long last = Long.MIN_VALUE;
        for (int st : start) {
            if (last + mi > st + d) {
                return false;
            }
            last = Math.max(st, last + mi);
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxPossibleScore(vector<int>& start, int d) {
        ranges::sort(start);
        auto check = [&](int mi) -> bool {
            long long last = LLONG_MIN;
            for (int st : start) {
                if (last + mi > st + d) {
                    return false;
                }
                last = max((long long) st, last + mi);
            }
            return true;
        };
        int l = 0, r = start.back() + d - start[0];
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (check(mid)) {
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
func maxPossibleScore(start []int, d int) int {
	check := func(mi int) bool {
		last := math.MinInt64
		for _, st := range start {
			if last+mi > st+d {
				return false
			}
			last = max(st, last+mi)
		}
		return true
	}
	sort.Ints(start)
	l, r := 0, start[len(start)-1]+d-start[0]
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
```

#### TypeScript

```ts
function maxPossibleScore(start: number[], d: number): number {
    start.sort((a, b) => a - b);
    let [l, r] = [0, start.at(-1)! + d - start[0]];
    const check = (mi: number): boolean => {
        let last = -Infinity;
        for (const st of start) {
            if (last + mi > st + d) {
                return false;
            }
            last = Math.max(st, last + mi);
        }
        return true;
    };
    while (l < r) {
        const mid = l + ((r - l + 1) >> 1);
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
