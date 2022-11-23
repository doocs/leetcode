# [1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？](https://leetcode.cn/problems/can-you-eat-your-favorite-candy-on-your-favorite-day)

[English Version](/solution/1700-1799/1744.Can%20You%20Eat%20Your%20Favorite%20Candy%20on%20Your%20Favorite%20Day/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的正整数数组 <code>candiesCount</code> ，其中 <code>candiesCount[i]</code> 表示你拥有的第 <code>i</code> 类糖果的数目。同时给你一个二维数组 <code>queries</code> ，其中 <code>queries[i] = [favoriteType<sub>i</sub>, favoriteDay<sub>i</sub>, dailyCap<sub>i</sub>]</code> 。</p>

<p>你按照如下规则进行一场游戏：</p>

<ul>
	<li>你从第 <code><strong>0</strong></code><strong> </strong>天开始吃糖果。</li>
	<li>你在吃完 <strong>所有</strong> 第 <code>i - 1</code> 类糖果之前，<strong>不能</strong> 吃任何一颗第 <code>i</code> 类糖果。</li>
	<li>在吃完所有糖果之前，你必须每天 <strong>至少</strong> 吃 <strong>一颗</strong> 糖果。</li>
</ul>

<p>请你构建一个布尔型数组 <code>answer</code> ，用以给出 <code>queries</code> 中每一项的对应答案。此数组满足：</p>

<ul>
	<li><code>answer.length == queries.length</code> 。<code>answer[i]</code> 是 <code>queries[i]</code> 的答案。</li>
	<li><code>answer[i]</code> 为 <code>true</code> 的条件是：在每天吃 <strong>不超过</strong> <code>dailyCap<sub>i</sub></code><sub> </sub>颗糖果的前提下，你可以在第 <code>favoriteDay<sub>i</sub></code> 天吃到第 <code>favoriteType<sub>i</sub></code> 类糖果；否则 <code>answer[i]</code> 为 <code>false</code> 。</li>
</ul>

<p>注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。</p>

<p>请你返回得到的数组<em> </em><code>answer</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
<b>输出：</b>[true,false,true]
<strong>提示：</strong>
1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
<b>输出：</b>[false,true,true,false,false]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= candiesCount.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= candiesCount[i] <= 10<sup>5</sup></code></li>
	<li><code>1 <= queries.length <= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 3</code></li>
	<li><code>0 <= favoriteType<sub>i</sub> < candiesCount.length</code></li>
	<li><code>0 <= favoriteDay<sub>i</sub> <= 10<sup>9</sup></code></li>
	<li><code>1 <= dailyCap<sub>i</sub> <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和**

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `candiesCount` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canEat(self, candiesCount: List[int], queries: List[List[int]]) -> List[bool]:
        s = list(accumulate(candiesCount, initial=0))
        ans = []
        for t, day, mx in queries:
            least, most = day, (day + 1) * mx
            ans.append(least < s[t + 1] and most > s[t])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + candiesCount[i];
        }
        int m = queries.length;
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; ++i) {
            int t = queries[i][0], day = queries[i][1], mx = queries[i][2];
            long least = day, most = (long) (day + 1) * mx;
            ans[i] = least < s[t + 1] && most > s[t];
        }
        return ans;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    vector<bool> canEat(vector<int>& candiesCount, vector<vector<int>>& queries) {
        int n = candiesCount.size();
        vector<ll> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + candiesCount[i];
        vector<bool> ans;
        for (auto& q : queries) {
            int t = q[0], day = q[1], mx = q[2];
            ll least = day, most = 1ll * (day + 1) * mx;
            ans.emplace_back(least < s[t + 1] && most > s[t]);
        }
        return ans;
    }
};
```

### **Go**

```go
func canEat(candiesCount []int, queries [][]int) (ans []bool) {
	n := len(candiesCount)
	s := make([]int, n+1)
	for i, v := range candiesCount {
		s[i+1] = s[i] + v
	}
	for _, q := range queries {
		t, day, mx := q[0], q[1], q[2]
		least, most := day, (day+1)*mx
		ans = append(ans, least < s[t+1] && most > s[t])
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
