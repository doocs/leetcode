# [1128. 等价多米诺骨牌对的数量](https://leetcode.cn/problems/number-of-equivalent-domino-pairs)

[English Version](/solution/1100-1199/1128.Number%20of%20Equivalent%20Domino%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由一些多米诺骨牌组成的列表&nbsp;<code>dominoes</code>。</p>

<p>如果其中某一张多米诺骨牌可以通过旋转 <code>0</code>&nbsp;度或 <code>180</code> 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。</p>

<p>形式上，<code>dominoes[i] = [a, b]</code>&nbsp;和&nbsp;<code>dominoes[j] = [c, d]</code>&nbsp;等价的前提是&nbsp;<code>a==c</code>&nbsp;且&nbsp;<code>b==d</code>，或是&nbsp;<code>a==d</code> 且&nbsp;<code>b==c</code>。</p>

<p>在&nbsp;<code>0 &lt;= i &lt; j &lt; dominoes.length</code>&nbsp;的前提下，找出满足&nbsp;<code>dominoes[i]</code> 和&nbsp;<code>dominoes[j]</code>&nbsp;等价的骨牌对 <code>(i, j)</code> 的数量。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>dominoes = [[1,2],[2,1],[3,4],[5,6]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= dominoes.length &lt;= 40000</code></li>
	<li><code>1 &lt;= dominoes[i][j] &lt;= 9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numEquivDominoPairs(self, dominoes: List[List[int]]) -> int:
        counter = Counter()
        ans = 0
        for a, b in dominoes:
            v = a * 10 + b if a > b else b * 10 + a
            ans += counter[v]
            counter[v] += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int ans = 0;
        int[] counter = new int[100];
        for (int[] d : dominoes) {
            int v = d[0] > d[1] ? d[0] * 10 + d[1] : d[1] * 10 + d[0];
            ans += counter[v];
            ++counter[v];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] counter = new int[100];
        for (int[] d : dominoes) {
            int v = d[0] > d[1] ? d[0] * 10 + d[1] : d[1] * 10 + d[0];
            ++counter[v];
        }
        int ans = 0;
        for (int c : counter) {
            ans += c * (c - 1) / 2;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numEquivDominoPairs(vector<vector<int>>& dominoes) {
        vector<int> counter(100);
        int ans = 0;
        for (auto& d : dominoes) {
            int v = d[0] > d[1] ? d[0] * 10 + d[1] : d[1] * 10 + d[0];
            ans += counter[v];
            ++counter[v];
        }
        return ans;
    }
};
```

### **Go**

```go
func numEquivDominoPairs(dominoes [][]int) int {
	counter := make([]int, 100)
	for _, d := range dominoes {
		if d[1] < d[0] {
			d[0], d[1] = d[1], d[0]
		}
		v := d[0]*10 + d[1]
		counter[v]++
	}
	ans := 0
	for _, c := range counter {
		ans += c * (c - 1) / 2
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
