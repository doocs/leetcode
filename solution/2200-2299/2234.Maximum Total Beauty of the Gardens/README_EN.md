---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2234.Maximum%20Total%20Beauty%20of%20the%20Gardens/README_EN.md
rating: 2561
source: Weekly Contest 288 Q4
tags:
    - Greedy
    - Array
    - Two Pointers
    - Binary Search
    - Enumeration
    - Prefix Sum
    - Sorting
---

<!-- problem:start -->

# [2234. Maximum Total Beauty of the Gardens](https://leetcode.com/problems/maximum-total-beauty-of-the-gardens)

[中文文档](/solution/2200-2299/2234.Maximum%20Total%20Beauty%20of%20the%20Gardens/README.md)

## Description

<!-- description:start -->

<p>Alice is a caretaker of <code>n</code> gardens and she wants to plant flowers to maximize the total beauty of all her gardens.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code>flowers</code> of size <code>n</code>, where <code>flowers[i]</code> is the number of flowers already planted in the <code>i<sup>th</sup></code> garden. Flowers that are already planted <strong>cannot</strong> be removed. You are then given another integer <code>newFlowers</code>, which is the <strong>maximum</strong> number of flowers that Alice can additionally plant. You are also given the integers <code>target</code>, <code>full</code>, and <code>partial</code>.</p>

<p>A garden is considered <strong>complete</strong> if it has <strong>at least</strong> <code>target</code> flowers. The <strong>total beauty</strong> of the gardens is then determined as the <strong>sum</strong> of the following:</p>

<ul>
	<li>The number of <strong>complete</strong> gardens multiplied by <code>full</code>.</li>
	<li>The <strong>minimum</strong> number of flowers in any of the <strong>incomplete</strong> gardens multiplied by <code>partial</code>. If there are no incomplete gardens, then this value will be <code>0</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> total beauty that Alice can obtain after planting at most </em><code>newFlowers</code><em> flowers.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial = 1
<strong>Output:</strong> 14
<strong>Explanation:</strong> Alice can plant
- 2 flowers in the 0<sup>th</sup> garden
- 3 flowers in the 1<sup>st</sup> garden
- 1 flower in the 2<sup>nd</sup> garden
- 1 flower in the 3<sup>rd</sup> garden
The gardens will then be [3,6,2,2]. She planted a total of 2 + 3 + 1 + 1 = 7 flowers.
There is 1 garden that is complete.
The minimum number of flowers in the incomplete gardens is 2.
Thus, the total beauty is 1 * 12 + 2 * 1 = 12 + 2 = 14.
No other way of planting flowers can obtain a total beauty higher than 14.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial = 6
<strong>Output:</strong> 30
<strong>Explanation:</strong> Alice can plant
- 3 flowers in the 0<sup>th</sup> garden
- 0 flowers in the 1<sup>st</sup> garden
- 0 flowers in the 2<sup>nd</sup> garden
- 2 flowers in the 3<sup>rd</sup> garden
The gardens will then be [5,4,5,5]. She planted a total of 3 + 0 + 0 + 2 = 5 flowers.
There are 3 gardens that are complete.
The minimum number of flowers in the incomplete gardens is 4.
Thus, the total beauty is 3 * 2 + 4 * 6 = 6 + 24 = 30.
No other way of planting flowers can obtain a total beauty higher than 30.
Note that Alice could make all the gardens complete but in this case, she would obtain a lower total beauty.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= flowers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= flowers[i], target &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= newFlowers &lt;= 10<sup>10</sup></code></li>
	<li><code>1 &lt;= full, partial &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Binary Search

We note that if the number of flowers in a garden is already greater than or equal to $\textit{target}$, then this garden is already a perfect garden and cannot be changed. For imperfect gardens, we can plant more flowers to make them perfect gardens.

Let's enumerate how many gardens will eventually become perfect gardens. Suppose initially there are $x$ perfect gardens, then we can enumerate in the range $[x, n]$. Which imperfect gardens should we choose to become perfect gardens? In fact, we should choose the gardens with more flowers so that the remaining flowers can be used to increase the minimum value of the imperfect gardens. Therefore, we sort the array $\textit{flowers}$.

Next, we enumerate the number of perfect gardens $x$. The current garden to become a perfect garden is $\textit{target}[n-x]$, and the number of flowers needed is $\max(0, \textit{target} - \textit{flowers}[n - x])$.

We update the remaining flowers $\textit{newFlowers}$. If it is less than $0$, it means we can no longer make more gardens perfect, so we stop the enumeration.

Otherwise, we perform a binary search in the range $[0,..n-x-1]$ to find the maximum index of the imperfect gardens that can be turned into perfect gardens. Let the index be $l$, then the number of flowers needed is $\textit{cost} = \textit{flowers}[l] \times (l + 1) - s[l + 1]$, where $s[i]$ is the sum of the first $i$ numbers in the $\textit{flowers}$ array. If we can still increase the minimum value, we calculate the increase $\frac{\textit{newFlowers} - \textit{cost}}{l + 1}$, and ensure that the final minimum value does not exceed $\textit{target}-1$. That is, the minimum value $y = \min(\textit{flowers}[l] + \frac{\textit{newFlowers} - \textit{cost}}{l + 1}, \textit{target} - 1)$. Then the beauty value of the garden is $x \times \textit{full} + y \times \textit{partial}$. The answer is the maximum of all beauty values.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the $\textit{flowers}$ array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumBeauty(
        self, flowers: List[int], newFlowers: int, target: int, full: int, partial: int
    ) -> int:
        flowers.sort()
        n = len(flowers)
        s = list(accumulate(flowers, initial=0))
        ans, i = 0, n - bisect_left(flowers, target)
        for x in range(i, n + 1):
            newFlowers -= 0 if x == 0 else max(target - flowers[n - x], 0)
            if newFlowers < 0:
                break
            l, r = 0, n - x - 1
            while l < r:
                mid = (l + r + 1) >> 1
                if flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers:
                    l = mid
                else:
                    r = mid - 1
            y = 0
            if r != -1:
                cost = flowers[l] * (l + 1) - s[l + 1]
                y = min(flowers[l] + (newFlowers - cost) // (l + 1), target - 1)
            ans = max(ans, x * full + y * partial)
        return ans
```

#### Java

```java
class Solution {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        int n = flowers.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + flowers[i];
        }
        long ans = 0;
        int x = 0;
        for (int v : flowers) {
            if (v >= target) {
                ++x;
            }
        }
        for (; x <= n; ++x) {
            newFlowers -= (x == 0 ? 0 : Math.max(target - flowers[n - x], 0));
            if (newFlowers < 0) {
                break;
            }
            int l = 0, r = n - x - 1;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                if ((long) flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            long y = 0;
            if (r != -1) {
                long cost = (long) flowers[l] * (l + 1) - s[l + 1];
                y = Math.min(flowers[l] + (newFlowers - cost) / (l + 1), target - 1);
            }
            ans = Math.max(ans, (long) x * full + y * partial);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumBeauty(vector<int>& flowers, long long newFlowers, int target, int full, int partial) {
        sort(flowers.begin(), flowers.end());
        int n = flowers.size();
        long long s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + flowers[i - 1];
        }
        long long ans = 0;
        int i = flowers.end() - lower_bound(flowers.begin(), flowers.end(), target);
        for (int x = i; x <= n; ++x) {
            newFlowers -= (x == 0 ? 0 : max(target - flowers[n - x], 0));
            if (newFlowers < 0) {
                break;
            }
            int l = 0, r = n - x - 1;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                if (1LL * flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            int y = 0;
            if (r != -1) {
                long long cost = 1LL * flowers[l] * (l + 1) - s[l + 1];
                long long mx = flowers[l] + (newFlowers - cost) / (l + 1);
                long long threshold = target - 1;
                y = min(mx, threshold);
            }
            ans = max(ans, 1LL * x * full + 1LL * y * partial);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumBeauty(flowers []int, newFlowers int64, target int, full int, partial int) int64 {
	sort.Ints(flowers)
	n := len(flowers)
	s := make([]int, n+1)
	for i, x := range flowers {
		s[i+1] = s[i] + x
	}
	ans := 0
	i := n - sort.SearchInts(flowers, target)
	for x := i; x <= n; x++ {
		if x > 0 {
			newFlowers -= int64(max(target-flowers[n-x], 0))
		}
		if newFlowers < 0 {
			break
		}
		l, r := 0, n-x-1
		for l < r {
			mid := (l + r + 1) >> 1
			if int64(flowers[mid]*(mid+1)-s[mid+1]) <= newFlowers {
				l = mid
			} else {
				r = mid - 1
			}
		}
		y := 0
		if r != -1 {
			cost := flowers[l]*(l+1) - s[l+1]
			y = min(flowers[l]+int((newFlowers-int64(cost))/int64(l+1)), target-1)
		}
		ans = max(ans, x*full+y*partial)
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function maximumBeauty(
    flowers: number[],
    newFlowers: number,
    target: number,
    full: number,
    partial: number,
): number {
    flowers.sort((a, b) => a - b);
    const n = flowers.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        s[i] = s[i - 1] + flowers[i - 1];
    }
    let x = flowers.filter(f => f >= target).length;
    let ans = 0;
    for (; x <= n; ++x) {
        newFlowers -= x === 0 ? 0 : Math.max(target - flowers[n - x], 0);
        if (newFlowers < 0) {
            break;
        }
        let l = 0;
        let r = n - x - 1;
        while (l < r) {
            const mid = (l + r + 1) >> 1;
            if (flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        let y = 0;
        if (r !== -1) {
            const cost = flowers[l] * (l + 1) - s[l + 1];
            y = Math.min(flowers[l] + Math.floor((newFlowers - cost) / (l + 1)), target - 1);
        }
        ans = Math.max(ans, x * full + y * partial);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
