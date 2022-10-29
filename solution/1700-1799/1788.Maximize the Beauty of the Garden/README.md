# [1788. 最大化花园的美观度](https://leetcode.cn/problems/maximize-the-beauty-of-the-garden)

[English Version](/solution/1700-1799/1788.Maximize%20the%20Beauty%20of%20the%20Garden/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个花园，有 <code>n</code> 朵花，这些花都有一个用整数表示的美观度。这些花被种在一条线上。给定一个长度为 <code>n</code> 的整数类型数组 <code>flowers</code> ，每一个 <code>flowers[i]</code> 表示第 <code>i</code> 朵花的美观度。</p>

<p>一个花园满足下列条件时，该花园是<strong>有效</strong>的。</p>

<ul>
	<li>花园中至少包含两朵花。</li>
	<li>第一朵花和最后一朵花的美观度相同。</li>
</ul>

<p>作为一个被钦定的园丁，你可以从花园中<strong>去除</strong>任意朵花（也可以不去除任意一朵）。你想要通过一种方法移除某些花朵，使得剩下的花园变得<strong>有效</strong>。花园的美观度是其中所有剩余的花朵美观度之和。</p>

<p>返回你去除了任意朵花（也可以不去除任意一朵）之后形成的<strong>有效</strong>花园中最大可能的美观度。</p>

<p> </p>

<p><b>示例 1：</b></p>

<pre><strong>输入:</strong> flowers = [1,2,3,1,2]
<strong>输出:</strong> 8
<strong>解释:</strong> 你可以修整为有效花园 [2,3,1,2] 来达到总美观度 2 + 3 + 1 + 2 = 8。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> flowers = [100,1,1,-3,1]
<strong>输出:</strong> 3
<strong>解释:</strong> 你可以修整为有效花园 [1,1,1] 来达到总美观度 1 + 1 + 1 = 3。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入:</strong> flowers = [-1,-2,0,-1]
<strong>输出:</strong> -2
<strong>解释:</strong> 你可以修整为有效花园 [-1,-1] 来达到总美观度 -1 + -1 = -2。
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>2 &lt;= flowers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= flowers[i] &lt;= 10<sup>4</sup></code></li>
	<li>去除一些花朵（可能没有）后，是有可能形成一个有效花园的。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumBeauty(self, flowers: List[int]) -> int:
        s = [0] * (len(flowers) + 1)
        d = {}
        ans = -inf
        for i, v in enumerate(flowers):
            if v in d:
                ans = max(ans, s[i] - s[d[v] + 1] + v * 2)
            else:
                d[v] = i
            s[i + 1] = s[i] + max(v, 0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumBeauty(int[] flowers) {
        int[] s = new int[flowers.length + 1];
        Map<Integer, Integer> d = new HashMap<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < flowers.length; ++i) {
            int v = flowers[i];
            if (d.containsKey(v)) {
                ans = Math.max(ans, s[i] - s[d.get(v) + 1] + v * 2);
            } else {
                d.put(v, i);
            }
            s[i + 1] = s[i] + Math.max(v, 0);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumBeauty(vector<int>& flowers) {
        int n = flowers.size();
        vector<int> s(n + 1);
        unordered_map<int, int> d;
        int ans = INT_MIN;
        for (int i = 0; i < n; ++i) {
            int v = flowers[i];
            if (d.count(v)) {
                ans = max(ans, s[i] - s[d[v] + 1] + v * 2);
            } else {
                d[v] = i;
            }
            s[i + 1] = s[i] + max(v, 0);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumBeauty(flowers []int) int {
	n := len(flowers)
	s := make([]int, n+1)
	d := map[int]int{}
	ans := math.MinInt32
	for i, v := range flowers {
		if j, ok := d[v]; ok {
			ans = max(ans, s[i]-s[j+1]+v*2)
		} else {
			d[v] = i
		}
		s[i+1] = s[i] + max(v, 0)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
