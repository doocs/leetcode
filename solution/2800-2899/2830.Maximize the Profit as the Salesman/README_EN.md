---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2830.Maximize%20the%20Profit%20as%20the%20Salesman/README_EN.md
rating: 1851
tags:
    - Array
    - Hash Table
    - Binary Search
    - Dynamic Programming
    - Sorting
---

# [2830. Maximize the Profit as the Salesman](https://leetcode.com/problems/maximize-the-profit-as-the-salesman)

[中文文档](/solution/2800-2899/2830.Maximize%20the%20Profit%20as%20the%20Salesman/README.md)

## Description

<p>You are given an integer <code>n</code> representing the number of houses on a number line, numbered from <code>0</code> to <code>n - 1</code>.</p>

<p>Additionally, you are given a 2D integer array <code>offers</code> where <code>offers[i] = [start<sub>i</sub>, end<sub>i</sub>, gold<sub>i</sub>]</code>, indicating that <code>i<sup>th</sup></code> buyer wants to buy all the houses from <code>start<sub>i</sub></code> to <code>end<sub>i</sub></code> for <code>gold<sub>i</sub></code> amount of gold.</p>

<p>As a salesman, your goal is to <strong>maximize</strong> your earnings by strategically selecting and selling houses to buyers.</p>

<p>Return <em>the maximum amount of gold you can earn</em>.</p>

<p><strong>Note</strong> that different buyers can&#39;t buy the same house, and some houses may remain unsold.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, offers = [[0,0,1],[0,2,2],[1,3,2]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 5 houses numbered from 0 to 4 and there are 3 purchase offers.
We sell houses in the range [0,0] to 1<sup>st</sup> buyer for 1 gold and houses in the range [1,3] to 3<sup>rd</sup> buyer for 2 golds.
It can be proven that 3 is the maximum amount of gold we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, offers = [[0,0,1],[0,2,10],[1,3,2]]
<strong>Output:</strong> 10
<strong>Explanation:</strong> There are 5 houses numbered from 0 to 4 and there are 3 purchase offers.
We sell houses in the range [0,2] to 2<sup>nd</sup> buyer for 10 golds.
It can be proven that 10 is the maximum amount of gold we can achieve.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= offers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>offers[i].length == 3</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= gold<sub>i</sub> &lt;= 10<sup>3</sup></code></li>
</ul>

## Solutions

### Solution 1: Sorting + Binary Search + Dynamic Programming

We sort all the purchase offers by $end$ in ascending order, and then use dynamic programming to solve the problem.

Define $f[i]$ to represent the maximum amount of gold we can get from the first $i$ purchase offers. The answer is $f[n]$.

For $f[i]$, we can choose not to sell the $i$th purchase offer, in which case $f[i] = f[i - 1]$; or we can choose to sell the $i$th purchase offer, in which case $f[i] = f[j] + gold_i$, where $j$ is the largest index that satisfies $end_j \leq start_i$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of purchase offers.

<!-- tabs:start -->

```python
class Solution:
    def maximizeTheProfit(self, n: int, offers: List[List[int]]) -> int:
        offers.sort(key=lambda x: x[1])
        f = [0] * (len(offers) + 1)
        g = [x[1] for x in offers]
        for i, (s, _, v) in enumerate(offers, 1):
            j = bisect_left(g, s)
            f[i] = max(f[i - 1], f[j] + v)
        return f[-1]
```

```java
class Solution {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        offers.sort((a, b) -> a.get(1) - b.get(1));
        n = offers.size();
        int[] f = new int[n + 1];
        int[] g = new int[n];
        for (int i = 0; i < n; ++i) {
            g[i] = offers.get(i).get(1);
        }
        for (int i = 1; i <= n; ++i) {
            var o = offers.get(i - 1);
            int j = search(g, o.get(0));
            f[i] = Math.max(f[i - 1], f[j] + o.get(2));
        }
        return f[n];
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
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
    int maximizeTheProfit(int n, vector<vector<int>>& offers) {
        sort(offers.begin(), offers.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        n = offers.size();
        vector<int> f(n + 1);
        vector<int> g;
        for (auto& o : offers) {
            g.push_back(o[1]);
        }
        for (int i = 1; i <= n; ++i) {
            auto o = offers[i - 1];
            int j = lower_bound(g.begin(), g.end(), o[0]) - g.begin();
            f[i] = max(f[i - 1], f[j] + o[2]);
        }
        return f[n];
    }
};
```

```go
func maximizeTheProfit(n int, offers [][]int) int {
	sort.Slice(offers, func(i, j int) bool { return offers[i][1] < offers[j][1] })
	n = len(offers)
	f := make([]int, n+1)
	g := []int{}
	for _, o := range offers {
		g = append(g, o[1])
	}
	for i := 1; i <= n; i++ {
		j := sort.SearchInts(g, offers[i-1][0])
		f[i] = max(f[i-1], f[j]+offers[i-1][2])
	}
	return f[n]
}
```

```ts
function maximizeTheProfit(n: number, offers: number[][]): number {
    offers.sort((a, b) => a[1] - b[1]);
    n = offers.length;
    const f: number[] = Array(n + 1).fill(0);
    const g = offers.map(x => x[1]);
    const search = (x: number) => {
        let l = 0;
        let r = n;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (g[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 1; i <= n; ++i) {
        const j = search(offers[i - 1][0]);
        f[i] = Math.max(f[i - 1], f[j] + offers[i - 1][2]);
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- end -->
