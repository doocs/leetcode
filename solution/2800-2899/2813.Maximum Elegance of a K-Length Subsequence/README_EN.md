---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2813.Maximum%20Elegance%20of%20a%20K-Length%20Subsequence/README_EN.md
rating: 2582
source: Weekly Contest 357 Q4
tags:
    - Greedy
    - Array
    - Hash Table
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2813. Maximum Elegance of a K-Length Subsequence](https://leetcode.com/problems/maximum-elegance-of-a-k-length-subsequence)

[中文文档](/solution/2800-2899/2813.Maximum%20Elegance%20of%20a%20K-Length%20Subsequence/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D integer array <code>items</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p><code>items[i] = [profit<sub>i</sub>, category<sub>i</sub>]</code>, where <code>profit<sub>i</sub></code> and <code>category<sub>i</sub></code> denote the profit and category of the <code>i<sup>th</sup></code> item respectively.</p>

<p>Let&#39;s define the <strong>elegance</strong> of a <strong>subsequence</strong> of <code>items</code> as <code>total_profit + distinct_categories<sup>2</sup></code>, where <code>total_profit</code> is the sum of all profits in the subsequence, and <code>distinct_categories</code> is the number of <strong>distinct</strong> categories from all the categories in the selected subsequence.</p>

<p>Your task is to find the <strong>maximum elegance</strong> from all subsequences of size <code>k</code> in <code>items</code>.</p>

<p>Return <em>an integer denoting the maximum elegance of a subsequence of </em><code>items</code><em> with size exactly </em><code>k</code>.</p>

<p><strong>Note:</strong> A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements&#39; relative order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> items = [[3,2],[5,1],[10,1]], k = 2
<strong>Output:</strong> 17
<strong>Explanation: </strong>In this example, we have to select a subsequence of size 2.
We can select items[0] = [3,2] and items[2] = [10,1].
The total profit in this subsequence is 3 + 10 = 13, and the subsequence contains 2 distinct categories [2,1].
Hence, the elegance is 13 + 2<sup>2</sup> = 17, and we can show that it is the maximum achievable elegance. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> items = [[3,1],[3,1],[2,2],[5,3]], k = 3
<strong>Output:</strong> 19
<strong>Explanation:</strong> In this example, we have to select a subsequence of size 3. 
We can select items[0] = [3,1], items[2] = [2,2], and items[3] = [5,3]. 
The total profit in this subsequence is 3 + 2 + 5 = 10, and the subsequence contains 3 distinct categories [1,2,3]. 
Hence, the elegance is 10 + 3<sup>2</sup> = 19, and we can show that it is the maximum achievable elegance.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> items = [[1,1],[2,1],[3,1]], k = 3
<strong>Output:</strong> 7
<strong>Explanation:</strong> In this example, we have to select a subsequence of size 3. 
We should select all the items. 
The total profit will be 1 + 2 + 3 = 6, and the subsequence contains 1 distinct category [1]. 
Hence, the maximum elegance is 6 + 1<sup>2</sup> = 7.  </pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>items[i][0] == profit<sub>i</sub></code></li>
	<li><code>items[i][1] == category<sub>i</sub></code></li>
	<li><code>1 &lt;= profit<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= category<sub>i</sub> &lt;= n </code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We can sort all items by profit from large to small. First choose the first $k$ items and calculate the total profit $tot$. Use a hash table $vis$ to record the categories of these $k$ items, use a stack $dup$ to record the profits of the repeated categories in order, and use a variable $ans$ to record the current maximum elegance.

Next, we consider starting from the $k+1$ item. If its category is already in $vis$, it means that if we choose this category, the number of different categories will not increase, so we can skip this item directly. If there is no duplicate category before, we can also skip this item directly. Otherwise, we can consider replacing the top item of $dup$ stack (the item with the minimum profit in the duplicate category) with the current item, which can increase the total profit by $p - dup.pop()$ and increase the number of different categories by $1$, so we can update $tot$ and $ans$.

Finally, we return $ans$.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the number of items.

<!-- tabs:start -->

```python
class Solution:
    def findMaximumElegance(self, items: List[List[int]], k: int) -> int:
        items.sort(key=lambda x: -x[0])
        tot = 0
        vis = set()
        dup = []
        for p, c in items[:k]:
            tot += p
            if c not in vis:
                vis.add(c)
            else:
                dup.append(p)
        ans = tot + len(vis) ** 2
        for p, c in items[k:]:
            if c in vis or not dup:
                continue
            vis.add(c)
            tot += p - dup.pop()
            ans = max(ans, tot + len(vis) ** 2)
        return ans
```

```java
class Solution {
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        int n = items.length;
        long tot = 0;
        Set<Integer> vis = new HashSet<>();
        Deque<Integer> dup = new ArrayDeque<>();
        for (int i = 0; i < k; ++i) {
            int p = items[i][0], c = items[i][1];
            tot += p;
            if (!vis.add(c)) {
                dup.push(p);
            }
        }
        long ans = tot + (long) vis.size() * vis.size();
        for (int i = k; i < n; ++i) {
            int p = items[i][0], c = items[i][1];
            if (vis.contains(c) || dup.isEmpty()) {
                continue;
            }
            vis.add(c);
            tot += p - dup.pop();
            ans = Math.max(ans, tot + (long) vis.size() * vis.size());
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long findMaximumElegance(vector<vector<int>>& items, int k) {
        sort(items.begin(), items.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[0] > b[0];
        });
        long long tot = 0;
        unordered_set<int> vis;
        stack<int> dup;
        for (int i = 0; i < k; ++i) {
            int p = items[i][0], c = items[i][1];
            tot += p;
            if (vis.count(c)) {
                dup.push(p);
            } else {
                vis.insert(c);
            }
        }
        int n = items.size();
        long long ans = tot + 1LL * vis.size() * vis.size();
        for (int i = k; i < n; ++i) {
            int p = items[i][0], c = items[i][1];
            if (vis.count(c) || dup.empty()) {
                continue;
            }
            vis.insert(c);
            tot += p - dup.top();
            dup.pop();
            ans = max(ans, tot + (long long) (1LL * vis.size() * vis.size()));
        }
        return ans;
    }
};
```

```go
func findMaximumElegance(items [][]int, k int) int64 {
	sort.Slice(items, func(i, j int) bool { return items[i][0] > items[j][0] })
	tot := 0
	vis := map[int]bool{}
	dup := []int{}
	for _, item := range items[:k] {
		p, c := item[0], item[1]
		tot += p
		if vis[c] {
			dup = append(dup, p)
		} else {
			vis[c] = true
		}
	}
	ans := tot + len(vis)*len(vis)
	for _, item := range items[k:] {
		p, c := item[0], item[1]
		if vis[c] || len(dup) == 0 {
			continue
		}
		vis[c] = true
		tot += p - dup[len(dup)-1]
		dup = dup[:len(dup)-1]
		ans = max(ans, tot+len(vis)*len(vis))
	}
	return int64(ans)
}
```

```ts
function findMaximumElegance(items: number[][], k: number): number {
    items.sort((a, b) => b[0] - a[0]);
    let tot = 0;
    const vis: Set<number> = new Set();
    const dup: number[] = [];
    for (const [p, c] of items.slice(0, k)) {
        tot += p;
        if (vis.has(c)) {
            dup.push(p);
        } else {
            vis.add(c);
        }
    }
    let ans = tot + vis.size ** 2;
    for (const [p, c] of items.slice(k)) {
        if (vis.has(c) || dup.length === 0) {
            continue;
        }
        tot += p - dup.pop()!;
        vis.add(c);
        ans = Math.max(ans, tot + vis.size ** 2);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
