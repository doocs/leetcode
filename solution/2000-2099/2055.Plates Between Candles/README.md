# [2055. 蜡烛之间的盘子](https://leetcode.cn/problems/plates-between-candles)

[English Version](/solution/2000-2099/2055.Plates%20Between%20Candles/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;，它只包含字符&nbsp;<code>'*'</code> 和&nbsp;<code>'|'</code>&nbsp;，其中&nbsp;<code>'*'</code>&nbsp;表示一个 <strong>盘子</strong>&nbsp;，<code>'|'</code>&nbsp;表示一支&nbsp;<strong>蜡烛</strong>&nbsp;。</p>

<p>同时给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>queries</code>&nbsp;，其中&nbsp;<code>queries[i] = [left<sub>i</sub>, right<sub>i</sub>]</code>&nbsp;表示 <strong>子字符串</strong>&nbsp;<code>s[left<sub>i</sub>...right<sub>i</sub>]</code>&nbsp;（<strong>包含左右端点的字符</strong>）。对于每个查询，你需要找到 <strong>子字符串中</strong>&nbsp;在 <strong>两支蜡烛之间</strong>&nbsp;的盘子的 <b>数目</b>&nbsp;。如果一个盘子在 <strong>子字符串中</strong>&nbsp;左边和右边 <strong>都</strong>&nbsp;至少有一支蜡烛，那么这个盘子满足在 <strong>两支蜡烛之间</strong>&nbsp;。</p>

<ul>
	<li>比方说，<code>s = "||**||**|*"</code>&nbsp;，查询&nbsp;<code>[3, 8]</code>&nbsp;，表示的是子字符串&nbsp;<code>"*||<strong><em>**</em></strong>|"</code>&nbsp;。子字符串中在两支蜡烛之间的盘子数目为&nbsp;<code>2</code>&nbsp;，子字符串中右边两个盘子在它们左边和右边 <strong>都 </strong>至少有一支蜡烛。</li>
</ul>

<p>请你返回一个整数数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;个查询的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="ex-1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2055.Plates%20Between%20Candles/images/ex-1.png" style="width: 400px; height: 134px;"></p>

<pre><b>输入：</b>s = "**|**|***|", queries = [[2,5],[5,9]]
<b>输出：</b>[2,3]
<b>解释：</b>
- queries[0] 有两个盘子在蜡烛之间。
- queries[1] 有三个盘子在蜡烛之间。
</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="ex-2" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2055.Plates%20Between%20Candles/images/ex-2.png" style="width: 600px; height: 193px;"></p>

<pre><b>输入：</b>s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
<b>输出：</b>[9,0,0,0,0]
<strong>解释：</strong>
- queries[0] 有 9 个盘子在蜡烛之间。
- 另一个查询没有盘子在蜡烛之间。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含字符&nbsp;<code>'*'</code> 和&nbsp;<code>'|'</code>&nbsp;。</li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= left<sub>i</sub> &lt;= right<sub>i</sub> &lt; s.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

预处理得到每个位置最左边、最右边的蜡烛位置 `left`, `right`。

对于每个查询 `(i, j)`，可以获取到区间左端、右端蜡烛位置 `right[i]`, `left[j]`，然后前缀和求解两个蜡烛之间的盘子数量即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def platesBetweenCandles(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        presum = [0] * (n + 1)
        for i, c in enumerate(s):
            presum[i + 1] = presum[i] + (c == '*')

        left, right = [0] * n, [0] * n
        l = r = -1
        for i, c in enumerate(s):
            if c == '|':
                l = i
            left[i] = l
        for i in range(n - 1, -1, -1):
            if s[i] == '|':
                r = i
            right[i] = r

        ans = [0] * len(queries)
        for k, (l, r) in enumerate(queries):
            i, j = right[l], left[r]
            if i >= 0 and j >= 0 and i < j:
                ans[k] = presum[j] - presum[i + 1]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + (s.charAt(i) == '*' ? 1 : 0);
        }
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0, l = -1; i < n; ++i) {
            if (s.charAt(i) == '|') {
                l = i;
            }
            left[i] = l;
        }
        for (int i = n - 1, r = -1; i >= 0; --i) {
            if (s.charAt(i) == '|') {
                r = i;
            }
            right[i] = r;
        }
        int[] ans = new int[queries.length];
        for (int k = 0; k < queries.length; ++k) {
            int i = right[queries[k][0]];
            int j = left[queries[k][1]];
            if (i >= 0 && j >= 0 && i < j) {
                ans[k] = presum[j] - presum[i + 1];
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
    vector<int> platesBetweenCandles(string s, vector<vector<int>>& queries) {
        int n = s.size();
        vector<int> presum(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + (s[i] == '*');
        vector<int> left(n);
        vector<int> right(n);
        for (int i = 0, l = -1; i < n; ++i) {
            if (s[i] == '|') l = i;
            left[i] = l;
        }
        for (int i = n - 1, r = -1; i >= 0; --i) {
            if (s[i] == '|') r = i;
            right[i] = r;
        }
        vector<int> ans(queries.size());
        for (int k = 0; k < queries.size(); ++k) {
            int i = right[queries[k][0]];
            int j = left[queries[k][1]];
            if (i >= 0 && j >= 0 && i < j) ans[k] = presum[j] - presum[i + 1];
        }
        return ans;
    }
};
```

### **Go**

```go
func platesBetweenCandles(s string, queries [][]int) []int {
	n := len(s)
	presum := make([]int, n+1)
	for i := range s {
		if s[i] == '*' {
			presum[i+1] = 1
		}
		presum[i+1] += presum[i]
	}
	left, right := make([]int, n), make([]int, n)
	for i, l := 0, -1; i < n; i++ {
		if s[i] == '|' {
			l = i
		}
		left[i] = l
	}
	for i, r := n-1, -1; i >= 0; i-- {
		if s[i] == '|' {
			r = i
		}
		right[i] = r
	}
	ans := make([]int, len(queries))
	for k, q := range queries {
		i, j := right[q[0]], left[q[1]]
		if i >= 0 && j >= 0 && i < j {
			ans[k] = presum[j] - presum[i+1]
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
