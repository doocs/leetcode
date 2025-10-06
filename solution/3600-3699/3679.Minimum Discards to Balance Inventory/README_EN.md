---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3679.Minimum%20Discards%20to%20Balance%20Inventory/README_EN.md
rating: 1638
source: Biweekly Contest 165 Q2
tags:
    - Array
    - Hash Table
    - Counting
    - Sliding Window
    - Simulation
---

<!-- problem:start -->

# [3679. Minimum Discards to Balance Inventory](https://leetcode.com/problems/minimum-discards-to-balance-inventory)

[中文文档](/solution/3600-3699/3679.Minimum%20Discards%20to%20Balance%20Inventory/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>w</code> and <code>m</code>, and an integer array <code>arrivals</code>, where <code>arrivals[i]</code> is the type of item arriving on day <code>i</code> (days are <strong>1-indexed</strong>).</p>

<p>Items are managed according to the following rules:</p>

<ul>
	<li>Each arrival may be <strong>kept</strong> or <strong>discarded</strong>; an item may only be discarded on its arrival day.</li>
	<li>For each day <code>i</code>, consider the window of days <code>[max(1, i - w + 1), i]</code> (the <code>w</code> most recent days up to day <code>i</code>):
	<ul>
		<li>For <strong>any</strong> such window, each item type may appear <strong>at most</strong> <code>m</code> times among kept arrivals whose arrival day lies in that window.</li>
		<li>If keeping the arrival on day <code>i</code> would cause its type to appear <strong>more than</strong> <code>m</code> times in the window, that arrival <strong>must</strong> be discarded.</li>
	</ul>
	</li>
</ul>

<p>Return the <strong>minimum</strong> number of arrivals to be discarded so that every <code>w</code>-day window contains at most <code>m</code> occurrences of each type.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">arrivals = [1,2,1,3,1], w = 4, m = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>On day 1, Item 1 arrives; the window contains no more than <code>m</code> occurrences of this type, so we keep it.</li>
	<li>On day 2, Item 2 arrives; the window of days 1 - 2 is fine.</li>
	<li>On day 3, Item 1 arrives, window <code>[1, 2, 1]</code> has item 1 twice, within limit.</li>
	<li>On day 4, Item 3 arrives, window <code>[1, 2, 1, 3]</code> has item 1 twice, allowed.</li>
	<li>On day 5, Item 1 arrives, window <code>[2, 1, 3, 1]</code> has item 1 twice, still valid.</li>
</ul>

<p>There are no discarded items, so return 0.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">arrivals = [1,2,3,3,3,4], w = 3, m = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>On day 1, Item 1 arrives. We keep it.</li>
	<li>On day 2, Item 2 arrives, window <code>[1, 2]</code> is fine.</li>
	<li>On day 3, Item 3 arrives, window <code>[1, 2, 3]</code> has item 3 once.</li>
	<li>On day 4, Item 3 arrives, window <code>[2, 3, 3]</code> has item 3 twice, allowed.</li>
	<li>On day 5, Item 3 arrives, window <code>[3, 3, 3]</code> has item 3 three times, exceeds limit, so the arrival must be discarded.</li>
	<li>On day 6, Item 4 arrives, window <code>[3, 4]</code> is fine.</li>
</ul>

<p>Item 3 on day 5 is discarded, and this is the minimum number of arrivals to discard, so return 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arrivals.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arrivals[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= w &lt;= arrivals.length</code></li>
	<li><code>1 &lt;= m &lt;= w</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation + Sliding Window

We use a hash map $\textit{cnt}$ to record the quantity of each item type in the current window, and an array $\textit{marked}$ to record whether each item is kept.

We iterate through the array from left to right. For each item $x$:

1. If the current day $i$ is greater than or equal to the window size $w$, we subtract $\textit{marked}[i - w]$ from the count of the leftmost item in the window (if that item was kept).
2. If the quantity of the current item in the window exceeds $m$, we discard the item.
3. Otherwise, we keep the item and increment its count.

Finally, the answer is the number of items discarded.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minArrivalsToDiscard(self, arrivals: List[int], w: int, m: int) -> int:
        cnt = Counter()
        n = len(arrivals)
        marked = [0] * n
        ans = 0
        for i, x in enumerate(arrivals):
            if i >= w:
                cnt[arrivals[i - w]] -= marked[i - w]
            if cnt[x] >= m:
                ans += 1
            else:
                marked[i] = 1
                cnt[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int n = arrivals.length;
        int[] marked = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = arrivals[i];
            if (i >= w) {
                int prev = arrivals[i - w];
                cnt.merge(prev, -marked[i - w], Integer::sum);
            }
            if (cnt.getOrDefault(x, 0) >= m) {
                ans++;
            } else {
                marked[i] = 1;
                cnt.merge(x, 1, Integer::sum);
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
    int minArrivalsToDiscard(vector<int>& arrivals, int w, int m) {
        unordered_map<int, int> cnt;
        int n = arrivals.size();
        vector<int> marked(n, 0);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = arrivals[i];
            if (i >= w) {
                cnt[arrivals[i - w]] -= marked[i - w];
            }
            if (cnt[x] >= m) {
                ans++;
            } else {
                marked[i] = 1;
                cnt[x] += 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minArrivalsToDiscard(arrivals []int, w int, m int) (ans int) {
	cnt := make(map[int]int)
	n := len(arrivals)
	marked := make([]int, n)
	for i, x := range arrivals {
		if i >= w {
			cnt[arrivals[i-w]] -= marked[i-w]
		}
		if cnt[x] >= m {
			ans++
		} else {
			marked[i] = 1
			cnt[x]++
		}
	}
	return
}
```

#### TypeScript

```ts
function minArrivalsToDiscard(arrivals: number[], w: number, m: number): number {
    const cnt = new Map<number, number>();
    const n = arrivals.length;
    const marked = Array<number>(n).fill(0);
    let ans = 0;

    for (let i = 0; i < n; i++) {
        const x = arrivals[i];
        if (i >= w) {
            cnt.set(arrivals[i - w], (cnt.get(arrivals[i - w]) || 0) - marked[i - w]);
        }
        if ((cnt.get(x) || 0) >= m) {
            ans++;
        } else {
            marked[i] = 1;
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
