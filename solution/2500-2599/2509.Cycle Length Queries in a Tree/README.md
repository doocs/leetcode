# [2509. 查询树中环的长度](https://leetcode.cn/problems/cycle-length-queries-in-a-tree)

[English Version](/solution/2500-2599/2509.Cycle%20Length%20Queries%20in%20a%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，表示你有一棵含有&nbsp;<code>2<sup>n</sup> - 1</code>&nbsp;个节点的 <strong>完全二叉树</strong>&nbsp;。根节点的编号是&nbsp;<code>1</code>&nbsp;，树中编号在<code>[1, 2<sup>n - 1</sup> - 1]</code>&nbsp;之间，编号为&nbsp;<code>val</code>&nbsp;的节点都有两个子节点，满足：</p>

<ul>
	<li>左子节点的编号为&nbsp;<code>2 * val</code></li>
	<li>右子节点的编号为&nbsp;<code>2 * val + 1</code></li>
</ul>

<p>给你一个长度为 <code>m</code>&nbsp;的查询数组 <code>queries</code>&nbsp;，它是一个二维整数数组，其中&nbsp;<code>queries[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;。对于每个查询，求出以下问题的解：</p>

<ol>
	<li>在节点编号为&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间添加一条边。</li>
	<li>求出图中环的长度。</li>
	<li>删除节点编号为&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间新添加的边。</li>
</ol>

<p><strong>注意：</strong></p>

<ul>
	<li><strong>环</strong> 是开始和结束于同一节点的一条路径，路径中每条边都只会被访问一次。</li>
	<li>环的长度是环中边的数目。</li>
	<li>在树中添加额外的边后，两个点之间可能会有多条边。</li>
</ul>

<p>请你返回一个长度为 <code>m</code>&nbsp;的数组<em>&nbsp;</em><code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;个查询的结果<i>。</i></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2509.Cycle%20Length%20Queries%20in%20a%20Tree/images/bexample1.png" style="width: 647px; height: 128px;" /></p>

<pre>
<b>输入：</b>n = 3, queries = [[5,3],[4,7],[2,3]]
<b>输出：</b>[4,5,3]
<b>解释：</b>上图是一棵有 2<sup>3</sup> - 1 个节点的树。红色节点表示添加额外边后形成环的节点。
- 在节点 3 和节点 5 之间添加边后，环为 [5,2,1,3] ，所以第一个查询的结果是 4 。删掉添加的边后处理下一个查询。
- 在节点 4 和节点 7 之间添加边后，环为 [4,2,1,3,7] ，所以第二个查询的结果是 5 。删掉添加的边后处理下一个查询。
- 在节点 2 和节点 3 之间添加边后，环为 [2,1,3] ，所以第三个查询的结果是 3 。删掉添加的边。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2509.Cycle%20Length%20Queries%20in%20a%20Tree/images/aexample2.png" style="width: 146px; height: 71px;" /></p>

<pre>
<b>输入：</b>n = 2, queries = [[1,2]]
<b>输出：</b>[2]
<b>解释：</b>上图是一棵有 2<sup>2</sup> - 1 个节点的树。红色节点表示添加额外边后形成环的节点。
- 在节点 1 和节点 2 之间添加边后，环为 [2,1] ，所以第一个查询的结果是 2 。删掉添加的边。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 30</code></li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= 2<sup>n</sup> - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：求最近公共祖先**

对于每次查询，我们找出 $a$, $b$ 两个节点的最近公共祖先，并且记录向上走的步数，那么此次查询的答案就是步数加一。

求最近公共祖先时，如果 $a \gt b$，那么我们将 $a$ 往父节点移动；如果 $a \lt b$，我们将 $b$ 往其父节点移动。过程中累计步数，直至 $a = b$。

时间复杂度 $O(n \times m)$。其中 $m$ 为数组 `queries` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def cycleLengthQueries(self, n: int, queries: List[List[int]]) -> List[int]:
        ans = []
        for a, b in queries:
            t = 1
            while a != b:
                if a > b:
                    a >>= 1
                else:
                    b >>= 1
                t += 1
            ans.append(t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int a = queries[i][0], b = queries[i][1];
            int t = 1;
            while (a != b) {
                if (a > b) {
                    a >>= 1;
                } else {
                    b >>= 1;
                }
                ++t;
            }
            ans[i] = t;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> cycleLengthQueries(int n, vector<vector<int>>& queries) {
        vector<int> ans;
        for (auto& q : queries) {
            int a = q[0], b = q[1];
            int t = 1;
            while (a != b) {
                if (a > b) {
                    a >>= 1;
                } else {
                    b >>= 1;
                }
                ++t;
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func cycleLengthQueries(n int, queries [][]int) []int {
	ans := []int{}
	for _, q := range queries {
		a, b := q[0], q[1]
		t := 1
		for a != b {
			if a > b {
				a >>= 1
			} else {
				b >>= 1
			}
			t++
		}
		ans = append(ans, t)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
