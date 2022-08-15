# [2374. 边积分最高的节点](https://leetcode.cn/problems/node-with-highest-edge-score)

[English Version](/solution/2300-2399/2374.Node%20With%20Highest%20Edge%20Score/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个有向图，图中有 <code>n</code> 个节点，节点编号从 <code>0</code> 到 <code>n - 1</code> ，其中每个节点都 <strong>恰有一条</strong> 出边。</p>

<p>图由一个下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的整数数组 <code>edges</code> 表示，其中 <code>edges[i]</code> 表示存在一条从节点 <code>i</code> 到节点 <code>edges[i]</code> 的 <strong>有向</strong> 边。</p>

<p>节点 <code>i</code> 的 <strong>边积分</strong> 定义为：所有存在一条指向节点 <code>i</code> 的边的节点的 <strong>编号</strong> 总和。</p>

<p>返回 <strong>边积分</strong> 最高的节点。如果多个节点的 <strong>边积分</strong> 相同，返回编号 <strong>最小</strong> 的那个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2374.Node%20With%20Highest%20Edge%20Score/images/image-20220620195403-1.png" style="width: 450px; height: 260px;">
<pre><strong>输入：</strong>edges = [1,0,0,0,0,7,7,5]
<strong>输出：</strong>7
<strong>解释：</strong>
- 节点 1、2、3 和 4 都有指向节点 0 的边，节点 0 的边积分等于 1 + 2 + 3 + 4 = 10 。
- 节点 0 有一条指向节点 1 的边，节点 1 的边积分等于 0 。
- 节点 7 有一条指向节点 5 的边，节点 5 的边积分等于 7 。
- 节点 5 和 6 都有指向节点 7 的边，节点 7 的边积分等于 5 + 6 = 11 。
节点 7 的边积分最高，所以返回 7 。
</pre>

<p><strong>示例 2：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2374.Node%20With%20Highest%20Edge%20Score/images/image-20220620200212-3.png" style="width: 150px; height: 155px;">
<pre><strong>输入：</strong>edges = [2,0,0,2]
<strong>输出：</strong>0
<strong>解释：
</strong>- 节点 1 和 2 都有指向节点 0 的边，节点 0 的边积分等于 1 + 2 = 3 。
- 节点 0 和 3 都有指向节点 2 的边，节点 2 的边积分等于 0 + 3 = 3 。
节点 0 和 2 的边积分都是 3 。由于节点 0 的编号更小，返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges[i] &lt; n</code></li>
	<li><code>edges[i] != i</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历计数**

定义 $cnt$，其中每个元素 $cnt[i]$ 表示到节点 $i$ 的所有节点编号之和。

最后找出 $cnt$ 中最大的元素 $cnt[i]$，返回 $i$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是节点的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def edgeScore(self, edges: List[int]) -> int:
        cnt = Counter()
        for i, v in enumerate(edges):
            cnt[v] += i
        ans = 0
        for i in range(len(edges)):
            if cnt[ans] < cnt[i]:
                ans = i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] cnt = new long[n];
        for (int i = 0; i < n; ++i) {
            cnt[edges[i]] += i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (cnt[ans] < cnt[i]) {
                ans = i;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int edgeScore(vector<int>& edges) {
        int n = edges.size();
        vector<long long> cnt(n);
        for (int i = 0; i < n; ++i) {
            cnt[edges[i]] += i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (cnt[ans] < cnt[i]) {
                ans = i;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func edgeScore(edges []int) int {
	n := len(edges)
	cnt := make([]int, n)
	for i, v := range edges {
		cnt[v] += i
	}
	ans := 0
	for i, v := range cnt {
		if cnt[ans] < v {
			ans = i
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function edgeScore(edges: number[]): number {
    const n = edges.length;
    const sum = new Array(n).fill(0);
    for (let i = 0; i < n; i++) {
        sum[edges[i]] += i;
    }
    let res = 0;
    for (let i = 0; i < n; i++) {
        if (sum[res] < sum[i]) {
            res = i;
        }
    }
    return res;
}
```

### **...**

```


```

<!-- tabs:end -->
