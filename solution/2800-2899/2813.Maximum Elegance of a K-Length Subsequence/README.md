---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2813.Maximum%20Elegance%20of%20a%20K-Length%20Subsequence/README.md
rating: 2582
source: 第 357 场周赛 Q4
tags:
    - 贪心
    - 数组
    - 哈希表
    - 排序
    - 堆（优先队列）
---

# [2813. 子序列最大优雅度](https://leetcode.cn/problems/maximum-elegance-of-a-k-length-subsequence)

[English Version](/solution/2800-2899/2813.Maximum%20Elegance%20of%20a%20K-Length%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的二维整数数组 <code>items</code> 和一个整数 <code>k</code> 。</p>

<p><code>items[i] = [profit<sub>i</sub>, category<sub>i</sub>]</code>，其中 <code>profit<sub>i</sub></code> 和 <code>category<sub>i</sub></code> 分别表示第 <code>i</code> 个项目的利润和类别。</p>

<p>现定义&nbsp;<code>items</code> 的 <strong>子序列</strong> 的 <strong>优雅度</strong> 可以用 <code>total_profit + distinct_categories<sup>2</sup></code> 计算，其中 <code>total_profit</code> 是子序列中所有项目的利润总和，<code>distinct_categories</code> 是所选子序列所含的所有类别中不同类别的数量。</p>

<p>你的任务是从 <code>items</code> 所有长度为 <code>k</code> 的子序列中，找出 <strong>最大优雅度</strong> 。</p>

<p>用整数形式表示并返回 <code>items</code> 中所有长度恰好为 <code>k</code> 的子序列的最大优雅度。</p>

<p><strong>注意：</strong>数组的子序列是经由原数组删除一些元素（可能不删除）而产生的新数组，且删除不改变其余元素相对顺序。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>items = [[3,2],[5,1],[10,1]], k = 2
<strong>输出：</strong>17
<strong>解释：
</strong>在这个例子中，我们需要选出长度为 2 的子序列。
其中一种方案是 items[0] = [3,2] 和 items[2] = [10,1] 。
子序列的总利润为 3 + 10 = 13 ，子序列包含 2 种不同类别 [2,1] 。
因此，优雅度为 13 + 2<sup>2</sup> = 17 ，可以证明 17 是可以获得的最大优雅度。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>items = [[3,1],[3,1],[2,2],[5,3]], k = 3
<strong>输出：</strong>19
<strong>解释：</strong>
在这个例子中，我们需要选出长度为 3 的子序列。 
其中一种方案是 items[0] = [3,1] ，items[2] = [2,2] 和 items[3] = [5,3] 。
子序列的总利润为 3 + 2 + 5 = 10 ，子序列包含 3 种不同类别 [1, 2, 3] 。 
因此，优雅度为 10 + 3<sup>2</sup> = 19 ，可以证明 19 是可以获得的最大优雅度。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>items = [[1,1],[2,1],[3,1]], k = 3
<strong>输出：</strong>7
<strong>解释：
</strong>在这个例子中，我们需要选出长度为 3 的子序列。
我们需要选中所有项目。
子序列的总利润为 1 + 2 + 3 = 6，子序列包含 1 种不同类别 [1] 。
因此，最大优雅度为 6 + 1<sup>2</sup> = 7 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= items.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>items[i][0] == profit<sub>i</sub></code></li>
	<li><code>items[i][1] == category<sub>i</sub></code></li>
	<li><code>1 &lt;= profit<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= category<sub>i</sub> &lt;= n </code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## 解法

### 方法一：贪心

我们可以将所有项目按照利润从大到小排序，先选取前 $k$ 个项目，计算其总利润 $tot$，用一个哈希表 $vis$ 记录这 $k$ 个项目的类别，用一个栈 $dup$ 按顺序记录这 $k$ 个项目中重复类别的利润，用一个变量 $ans$ 记录当前的最大优雅度。

接下来，我们考虑从第 $k+1$ 个项目开始，如果其类别已经在 $vis$ 中，这意味着如果选择该类别，不会使得不同的类别数量增加，因此我们可以直接跳过该项目。如果此前不存在重复类别，我们也可以直接跳过该项目。否则，我们可以考虑将 $dup$ 栈顶的项目（即重复类别中利润最小的项目）替换为当前项目，这样可以使得总利润增加 $p - dup.pop()$，同时不同类别数量增加 $1$，因此我们可以更新 $tot$ 和 $ans$。

最后，我们返回 $ans$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为项目数量。

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

<!-- end -->
