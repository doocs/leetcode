# [2167. 移除所有载有违禁货物车厢所需的最少时间](https://leetcode.cn/problems/minimum-time-to-remove-all-cars-containing-illegal-goods)

[English Version](/solution/2100-2199/2167.Minimum%20Time%20to%20Remove%20All%20Cars%20Containing%20Illegal%20Goods/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的二进制字符串 <code>s</code> ，表示一个列车车厢序列。<code>s[i] = '0'</code> 表示第 <code>i</code> 节车厢 <strong>不</strong> 含违禁货物，而 <code>s[i] = '1'</code> 表示第 <code>i</code> 节车厢含违禁货物。</p>

<p>作为列车长，你需要清理掉所有载有违禁货物的车厢。你可以不限次数执行下述三种操作中的任意一个：</p>

<ol>
	<li>从列车 <strong>左</strong> 端移除一节车厢（即移除 <code>s[0]</code>），用去 1 单位时间。</li>
	<li>从列车 <strong>右</strong> 端移除一节车厢（即移除 <code>s[s.length - 1]</code>），用去 1 单位时间。</li>
	<li>从列车车厢序列的 <strong>任意位置</strong> 移除一节车厢，用去 2 单位时间。</li>
</ol>

<p>返回移除所有载有违禁货物车厢所需要的 <strong>最少</strong> 单位时间数。</p>

<p>注意，空的列车车厢序列视为没有车厢含违禁货物。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "<em><strong>11</strong></em>00<em><strong>1</strong></em>0<em><strong>1</strong></em>"
<strong>输出：</strong>5
<strong>解释：</strong>
一种从序列中移除所有载有违禁货物的车厢的方法是：
- 从左端移除一节车厢 2 次。所用时间是 2 * 1 = 2 。
- 从右端移除一节车厢 1 次。所用时间是 1 。
- 移除序列中间位置载有违禁货物的车厢。所用时间是 2 。
总时间是 2 + 1 + 2 = 5 。

一种替代方法是：
- 从左端移除一节车厢 2 次。所用时间是 2 * 1 = 2 。
- 从右端移除一节车厢 3 次。所用时间是 3 * 1 = 3 。
总时间也是 2 + 3 = 5 。

5 是移除所有载有违禁货物的车厢所需要的最少单位时间数。
没有其他方法能够用更少的时间移除这些车厢。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "00<em><strong>1</strong></em>0"
<strong>输出：</strong>2
<strong>解释：</strong>
一种从序列中移除所有载有违禁货物的车厢的方法是：
- 从左端移除一节车厢 3 次。所用时间是 3 * 1 = 3 。
总时间是 3.

另一种从序列中移除所有载有违禁货物的车厢的方法是：
- 移除序列中间位置载有违禁货物的车厢。所用时间是 2 。
总时间是 2.

另一种从序列中移除所有载有违禁货物的车厢的方法是：
- 从右端移除一节车厢 2 次。所用时间是 2 * 1 = 2 。
总时间是 2.

2 是移除所有载有违禁货物的车厢所需要的最少单位时间数。
没有其他方法能够用更少的时间移除这些车厢。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumTime(self, s: str) -> int:
        n = len(s)
        pre = [0] * (n + 1)
        suf = [0] * (n + 1)
        for i, c in enumerate(s):
            pre[i + 1] = pre[i] if c == '0' else min(pre[i] + 2, i + 1)
        for i in range(n - 1, -1, -1):
            suf[i] = suf[i + 1] if s[i] == '0' else min(suf[i + 1] + 2, n - i)
        return min(a + b for a, b in zip(pre[1:], suf[1:]))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumTime(String s) {
        int n = s.length();
        int[] pre = new int[n + 1];
        int[] suf = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            pre[i + 1] = s.charAt(i) == '0' ? pre[i] : Math.min(pre[i] + 2, i + 1);
        }
        for (int i = n - 1; i >= 0; --i) {
            suf[i] = s.charAt(i) == '0' ? suf[i + 1] : Math.min(suf[i + 1] + 2, n - i);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; ++i) {
            ans = Math.min(ans, pre[i] + suf[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumTime(string s) {
        int n = s.size();
        vector<int> pre(n + 1);
        vector<int> suf(n + 1);
        for (int i = 0; i < n; ++i) pre[i + 1] = s[i] == '0' ? pre[i] : min(pre[i] + 2, i + 1);
        for (int i = n - 1; ~i; --i) suf[i] = s[i] == '0' ? suf[i + 1] : min(suf[i + 1] + 2, n - i);
        int ans = INT_MAX;
        for (int i = 1; i <= n; ++i) ans = min(ans, pre[i] + suf[i]);
        return ans;
    }
};
```

### **Go**

```go
func minimumTime(s string) int {
	n := len(s)
	pre := make([]int, n+1)
	suf := make([]int, n+1)
	for i, c := range s {
		pre[i+1] = pre[i]
		if c == '1' {
			pre[i+1] = min(pre[i]+2, i+1)
		}
	}
	for i := n - 1; i >= 0; i-- {
		suf[i] = suf[i+1]
		if s[i] == '1' {
			suf[i] = min(suf[i+1]+2, n-i)
		}
	}
	ans := 0x3f3f3f3f
	for i := 1; i <= n; i++ {
		ans = min(ans, pre[i]+suf[i])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
