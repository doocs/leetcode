# [455. 分发饼干](https://leetcode.cn/problems/assign-cookies)

[English Version](/solution/0400-0499/0455.Assign%20Cookies/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。</p>

<p>对每个孩子 <code>i</code>，都有一个胃口值 <code>g[i]</code><sub>，</sub>这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 <code>j</code>，都有一个尺寸 <code>s[j]</code><sub> </sub>。如果 <code>s[j] >= g[i]</code>，我们可以将这个饼干 <code>j</code> 分配给孩子 <code>i</code> ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。</p>
 

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> g = [1,2,3], s = [1,1]
<strong>输出:</strong> 1
<strong>解释:</strong> 
你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
所以你应该输出1。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> g = [1,2], s = [1,2,3]
<strong>输出:</strong> 2
<strong>解释:</strong> 
你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
你拥有的饼干数量和尺寸都足以让所有孩子满足。
所以你应该输出2.
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= g.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>0 <= s.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>1 <= g[i], s[j] <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

时间复杂度 $O(mlogm+nlogn)$，其中 $m$ 表示 $g$ 的长度，$n$ 表示 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findContentChildren(self, g: List[int], s: List[int]) -> int:
        g.sort()
        s.sort()
        j = 0
        for i, v in enumerate(g):
            while j < len(s) and s[j] < v:
                j += 1
            if j >= len(s):
                return i
            j += 1
        return len(g)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        for (; i < g.length; ++i) {
            while (j < s.length && s[j] < g[i]) {
                ++j;
            }
            if (j >= s.length) {
                break;
            }
            ++j;
        }
        return i;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findContentChildren(vector<int>& g, vector<int>& s) {
        sort(g.begin(), g.end());
        sort(s.begin(), s.end());
        int i = 0, j = 0;
        for (; i < g.size(); ++i) {
            while (j < s.size() && s[j] < g[i]) {
                ++j;
            }
            if (j >= s.size()) {
                break;
            }
            ++j;
        }
        return i;
    }
};
```

### **Go**

```go
func findContentChildren(g []int, s []int) int {
	sort.Ints(g)
	sort.Ints(s)
	i, j := 0, 0
	for ; i < len(g); i++ {
		for ; j < len(s) && s[j] < g[i]; j++ {
		}
		if j >= len(s) {
			break
		}
		j++
	}
	return i
}
```

### **JavaScript**

```js
/**
 * @param {number[]} g
 * @param {number[]} s
 * @return {number}
 */
var findContentChildren = function (g, s) {
    g.sort((a, b) => a - b);
    s.sort((a, b) => a - b);
    let i = 0;
    let j = 0;
    for (; i < g.length; ++i) {
        while (j < s.length && s[j] < g[i]) {
            ++j;
        }
        if (j >= s.length) {
            break;
        }
        ++j;
    }
    return i;
};
```

### **...**

```

```

<!-- tabs:end -->
