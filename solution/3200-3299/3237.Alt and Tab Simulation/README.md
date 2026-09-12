---
comments: true
difficulty: 中等
tags:
    - 数组
    - 哈希表
    - 模拟
---

<!-- problem:start -->

# [3237. Alt 和 Tab 模拟 🔒](https://leetcode.cn/problems/alt-and-tab-simulation)

[English Version](/solution/3200-3299/3237.Alt%20and%20Tab%20Simulation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有&nbsp;<code>n</code>&nbsp;个编号从&nbsp; <code>1</code> 到&nbsp;<code>n</code>&nbsp;的打开的窗口，我们想要模拟使用&nbsp;alt + tab 键在窗口之间导航。</p>

<p>给定数组&nbsp;<code>windows</code>&nbsp;包含窗口的初始顺序（第一个元素在最前面，最后一个元素在最后面）。</p>

<p>同时给定数组&nbsp;<code>queries</code>&nbsp;表示每一次查询中，编号为&nbsp;<code>queries[i]</code>&nbsp;的窗口被切换到最前面。</p>

<p>返回&nbsp;<code>windows</code>&nbsp;数组的最后状态。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>windows = [1,2,3], queries = [3,3,2]</span></p>

<p><strong>输出：</strong><span class="example-io">[2,3,1]</span></p>

<p><strong>解释：</strong></p>

<p>以下是每次查询后的 windows 数组：</p>

<ul>
	<li>初始顺序：<code>[1,2,3]</code></li>
	<li>第一次查询后：<code>[<u><strong>3</strong></u>,1,2]</code></li>
	<li>第二次查询后：<code>[<u><strong>3</strong></u>,1,2]</code></li>
	<li>最后一次查询后：<code>[<u><strong>2</strong></u>,3,1]</code></li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>windows = [1,4,2,3], queries = [4,1,3]</span></p>

<p><span class="example-io"><b>输出：</b>[3,1,4,2]</span></p>

<p><strong>解释：</strong></p>

<p>以下是每次查询后的 windows 数组：</p>

<ul>
	<li>初始顺序：<code>[1,4,2,3]</code></li>
	<li>第一次查询后：<code>[<u><strong>4</strong></u>,1,2,3]</code></li>
	<li>第二次查询后：<code>[<u><strong>1</strong></u>,4,2,3]</code></li>
	<li>最后一次查询后：<code>[<u><strong>3</strong></u>,1,4,2]</code></li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == windows.length &lt;= 10<sup>5</sup></code></li>
	<li><code>windows</code> 是&nbsp;<code>[1, n]</code>&nbsp;的一个排列。</li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries[i] &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 逆序遍历

<!-- thinking:start -->

> **思考**
>
> 每次查询把窗口提到最前。$n,q\le 10^5$，若每次把元素移到数组头部则总时间平方。最终顺序由「最后一次被提到的先后」决定，越后的查询越靠前。
>
> 逆序扫查询，用集合去重后依次写入答案，再把从未出现的原窗口按原序接在后面。每个窗口至多入队一次。

<!-- thinking:end -->

根据题目描述，越是后面的查询，越是出现在最前面的位置。因此，我们可以逆序遍历 $\textit{queries}$ 数组，用一个哈希表 $\textit{s}$ 记录已经出现过的窗口。对于每一个查询，如果当前窗口不在哈希表中，我们将其加入答案数组，并将其加入哈希表中。最后，我们再次遍历 $\textit{windows}$ 数组，将不在哈希表中的窗口加入答案数组。

时间复杂度 $O(n + m)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别为 $\textit{windows}$ 和 $\textit{queries}$ 数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def simulationResult(self, windows: List[int], queries: List[int]) -> List[int]:
        s = set()
        ans = []
        for q in queries[::-1]:
            if q not in s:
                ans.append(q)
                s.add(q)
        for w in windows:
            if w not in s:
                ans.append(w)
        return ans
```

#### Java

```java
class Solution {
    public int[] simulationResult(int[] windows, int[] queries) {
        int n = windows.length;
        boolean[] s = new boolean[n + 1];
        int[] ans = new int[n];
        int k = 0;
        for (int i = queries.length - 1; i >= 0; --i) {
            int q = queries[i];
            if (!s[q]) {
                ans[k++] = q;
                s[q] = true;
            }
        }
        for (int w : windows) {
            if (!s[w]) {
                ans[k++] = w;
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
    vector<int> simulationResult(vector<int>& windows, vector<int>& queries) {
        int n = windows.size();
        vector<bool> s(n + 1);
        vector<int> ans;
        for (int i = queries.size() - 1; ~i; --i) {
            int q = queries[i];
            if (!s[q]) {
                s[q] = true;
                ans.push_back(q);
            }
        }
        for (int w : windows) {
            if (!s[w]) {
                ans.push_back(w);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func simulationResult(windows []int, queries []int) (ans []int) {
	n := len(windows)
	s := make([]bool, n+1)
	for i := len(queries) - 1; i >= 0; i-- {
		q := queries[i]
		if !s[q] {
			s[q] = true
			ans = append(ans, q)
		}
	}
	for _, w := range windows {
		if !s[w] {
			ans = append(ans, w)
		}
	}
	return
}
```

#### TypeScript

```ts
function simulationResult(windows: number[], queries: number[]): number[] {
    const n = windows.length;
    const s: boolean[] = Array(n + 1).fill(false);
    const ans: number[] = [];
    for (let i = queries.length - 1; i >= 0; i--) {
        const q = queries[i];
        if (!s[q]) {
            s[q] = true;
            ans.push(q);
        }
    }
    for (const w of windows) {
        if (!s[w]) {
            ans.push(w);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
