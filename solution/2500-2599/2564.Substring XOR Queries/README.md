# [2564. 子字符串异或查询](https://leetcode.cn/problems/substring-xor-queries)

[English Version](/solution/2500-2599/2564.Substring%20XOR%20Queries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>二进制字符串</strong>&nbsp;<code>s</code>&nbsp;和一个整数数组&nbsp;<code>queries</code>&nbsp;，其中&nbsp;<code>queries[i] = [first<sub>i</sub>, second<sub>i</sub>]</code>&nbsp;。</p>

<p>对于第&nbsp;<code>i</code>&nbsp;个查询，找到 <code>s</code>&nbsp;的 <strong>最短子字符串</strong>&nbsp;，它对应的 <strong>十进制</strong>值&nbsp;<code>val</code>&nbsp;与&nbsp;<code>first<sub>i</sub></code>&nbsp;<b>按位异或</b>&nbsp;得到&nbsp;<code>second<sub>i</sub></code>&nbsp;，换言之，<code>val ^ first<sub>i</sub> == second<sub>i</sub></code>&nbsp;。</p>

<p>第&nbsp;<code>i</code>&nbsp;个查询的答案是子字符串&nbsp;<code>[left<sub>i</sub>, right<sub>i</sub>]</code> 的两个端点（下标从&nbsp;<strong>0</strong>&nbsp;开始），如果不存在这样的子字符串，则答案为&nbsp;<code>[-1, -1]</code>&nbsp;。如果有多个答案，请你选择&nbsp;<code>left<sub>i</sub></code>&nbsp;最小的一个。</p>

<p>请你返回一个数组&nbsp;<code>ans</code>&nbsp;，其中&nbsp;<code>ans[i] = [left<sub>i</sub>, right<sub>i</sub>]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;个查询的答案。</p>

<p><strong>子字符串</strong>&nbsp;是一个字符串中一段连续非空的字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "101101", queries = [[0,5],[1,2]]
<b>输出：</b>[[0,2],[2,3]]
<b>解释：</b>第一个查询，端点为 <code>[0,2]</code> 的子字符串为 <strong>"101"</strong> ，对应十进制数字 <strong><code>5 ，且</code></strong> <strong><code>5 ^ 0 = 5</code></strong>&nbsp;，所以第一个查询的答案为 <code>[0,2]。第二个查询中，</code>端点为 <code>[2,3] 的子字符串为 </code><strong>"11" ，对应十进制数字</strong> <strong>3</strong>&nbsp;，且 <strong>3<code> ^ 1 = 2</code></strong><code>&nbsp;。所以第二个查询的答案为</code> <code>[2,3]</code> 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "0101", queries = [[12,8]]
<b>输出：</b>[[-1,-1]]
<b>解释：</b>这个例子中，没有符合查询的答案，所以返回 <code>[-1,-1] 。</code>
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>s = "1", queries = [[4,5]]
<b>输出：</b>[[0,0]]
<b>解释：</b>这个例子中，端点为 <code>[0,0]</code> 的子字符串对应的十进制值为 <strong><code>1</code></strong><code>&nbsp;，且</code> <strong><code>1 ^ 4 = 5</code></strong><code>&nbsp;。所以答案为</code> <code>[0,0] 。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s[i]</code>&nbsp;要么是&nbsp;<code>'0'</code>&nbsp;，要么是&nbsp;<code>'1'</code>&nbsp;。</li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= first<sub>i</sub>, second<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理 + 枚举**

我们可以先预处理出所有长度为 $1$ 到 $32$ 的子串对应的十进制值，找到每个值对应的最小下标以及对应的右端点下标，存放在哈希表 $d$ 中。

然后枚举每个查询，对于每个查询 $[first, second]$，我们只需要在哈希表 $d$ 中查找是否存在键为 $first \oplus second$ 的键值对，如果存在，把对应的最小下标和右端点下标加入答案数组即可，否则加入 $[-1, -1]$。

时间复杂度 $O(n \times \log M + m)$，空间复杂度 $O(n \times \log M)$。其中 $n$ 和 $m$ 分别为字符串 $s$ 和查询数组 $queries$ 的长度，而 $M$ 可以取整数的最大值 $2^{31} - 1$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def substringXorQueries(self, s: str, queries: List[List[int]]) -> List[List[int]]:
        d = {}
        n = len(s)
        for i in range(n):
            x = 0
            for j in range(32):
                if i + j >= n:
                    break
                x = x << 1 | int(s[i + j])
                if x not in d:
                    d[x] = [i, i + j]
                if x == 0:
                    break
        return [d.get(first ^ second, [-1, -1]) for first, second in queries]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] substringXorQueries(String s, int[][] queries) {
        Map<Integer, int[]> d = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int x = 0;
            for (int j = 0; j < 32 && i + j < n; ++j) {
                x = x << 1 | (s.charAt(i + j) - '0');
                d.putIfAbsent(x, new int[] {i, i + j});
                if (x == 0) {
                    break;
                }
            }
        }
        int m = queries.length;
        int[][] ans = new int[m][2];
        for (int i = 0; i < m; ++i) {
            int first = queries[i][0], second = queries[i][1];
            int val = first ^ second;
            ans[i] = d.getOrDefault(val, new int[] {-1, -1});
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> substringXorQueries(string s, vector<vector<int>>& queries) {
        unordered_map<int, vector<int>> d;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            int x = 0;
            for (int j = 0; j < 32 && i + j < n; ++j) {
                x = x << 1 | (s[i + j] - '0');
                if (!d.count(x)) {
                    d[x] = {i, i + j};
                }
                if (x == 0) {
                    break;
                }
            }
        }
        vector<vector<int>> ans;
        for (auto& q : queries) {
            int first = q[0], second = q[1];
            int val = first ^ second;
            if (d.count(val)) {
                ans.emplace_back(d[val]);
            } else {
                ans.push_back({-1, -1});
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func substringXorQueries(s string, queries [][]int) (ans [][]int) {
	d := map[int][]int{}
	for i := range s {
		x := 0
		for j := 0; j < 32 && i+j < len(s); j++ {
			x = x<<1 | int(s[i+j]-'0')
			if _, ok := d[x]; !ok {
				d[x] = []int{i, i + j}
			}
			if x == 0 {
				break
			}
		}
	}
	for _, q := range queries {
		first, second := q[0], q[1]
		val := first ^ second
		if v, ok := d[val]; ok {
			ans = append(ans, v)
		} else {
			ans = append(ans, []int{-1, -1})
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
