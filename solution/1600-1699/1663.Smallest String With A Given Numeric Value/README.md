# [1663. 具有给定数值的最小字符串](https://leetcode.cn/problems/smallest-string-with-a-given-numeric-value)

[English Version](/solution/1600-1699/1663.Smallest%20String%20With%20A%20Given%20Numeric%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>小写字符 </strong>的 <strong>数值</strong> 是它在字母表中的位置（从 <code>1</code> 开始），因此 <code>a</code> 的数值为 <code>1</code> ，<code>b</code> 的数值为 <code>2</code> ，<code>c</code> 的数值为 <code>3</code> ，以此类推。</p>

<p>字符串由若干小写字符组成，<strong>字符串的数值</strong> 为各字符的数值之和。例如，字符串 <code>"abe"</code> 的数值等于 <code>1 + 2 + 5 = 8</code> 。</p>

<p>给你两个整数 <code>n</code> 和 <code>k</code> 。返回 <strong>长度</strong> 等于 <code>n</code> 且 <strong>数值</strong> 等于 <code>k</code> 的 <strong>字典序最小</strong> 的字符串。</p>

<p>注意，如果字符串 <code>x</code> 在字典排序中位于 <code>y</code> 之前，就认为 <code>x</code> 字典序比 <code>y</code> 小，有以下两种情况：</p>

<ul>
	<li><code>x</code> 是 <code>y</code> 的一个前缀；</li>
	<li>如果 <code>i</code> 是 <code>x[i] != y[i]</code> 的第一个位置，且 <code>x[i]</code> 在字母表中的位置比 <code>y[i]</code> 靠前。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, k = 27
<strong>输出：</strong>"aay"
<strong>解释：</strong>字符串的数值为 1 + 1 + 25 = 27，它是数值满足要求且长度等于 3 字典序最小的字符串。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5, k = 73
<strong>输出：</strong>"aaszz"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>n <= k <= 26 * n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们先将字符串的每个字符都初始化为 `'a'`，此时剩余的数值为 $d=k-n$。

接着从后往前遍历字符串，每次贪心地将当前位置的字符替换为能够使得剩余的数字最小的字符 `'z'`，直到剩余的数字不超过 $25$。最后将剩余的数字加到我们遍历到的位置上即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getSmallestString(self, n: int, k: int) -> str:
        ans = ['a'] * n
        i, d = n - 1, k - n
        while d > 25:
            ans[i] = 'z'
            d -= 25
            i -= 1
        ans[i] = chr(ord(ans[i]) + d)
        return ''.join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String getSmallestString(int n, int k) {
        char[] ans = new char[n];
        Arrays.fill(ans, 'a');
        int i = n - 1, d = k - n;
        for (; d > 25; d -= 25) {
            ans[i--] = 'z';
        }
        ans[i] = (char) ('a' + d);
        return String.valueOf(ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string getSmallestString(int n, int k) {
        string ans(n, 'a');
        int i = n - 1, d = k - n;
        for (; d > 25; d -= 25) {
            ans[i--] = 'z';
        }
        ans[i] += d;
        return ans;
    }
};
```

### **Go**

```go
func getSmallestString(n int, k int) string {
	ans := make([]byte, n)
	for i := range ans {
		ans[i] = 'a'
	}
	i, d := n-1, k-n
	for ; d > 25; i, d = i-1, d-25 {
		ans[i] = 'z'
	}
	ans[i] += byte(d)
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
