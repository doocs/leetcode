# [2450. 应用操作后不同二进制字符串的数量](https://leetcode.cn/problems/number-of-distinct-binary-strings-after-applying-operations)

[English Version](/solution/2400-2499/2450.Number%20of%20Distinct%20Binary%20Strings%20After%20Applying%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个&nbsp;<strong>二进制&nbsp;</strong>字符串 <code>s</code> 和一个正整数 <code>k</code>。</p>

<p>你可以对字符串应用以下操作&nbsp;<strong>任意&nbsp;</strong>次数:</p>

<ul>
	<li>从 <code>s</code> 中选择任何大小为 <code>k</code> 的子字符串，将其所有字符&nbsp;<strong>翻转</strong>，即将所有 <code>1</code>&nbsp;都变成 <code>0</code>，所有 <code>0</code>&nbsp;都变成 <code>1</code>。</li>
</ul>

<p>返回<em>您可以获得的&nbsp;<strong>不同&nbsp;</strong>字符串的数量。</em>因为答案可能太大，所以对 <code>10<sup>9</sup> + 7</code>&nbsp;<strong>取模&nbsp;</strong>后返回。</p>

<p><b>注意</b>:</p>

<ul>
	<li>二进制字符串是&nbsp;<strong>仅由&nbsp;</strong>字符 <code>0</code> 和 <code>1</code> 组成的字符串。</li>
	<li>
	<p data-group="1-1">子字符串是字符串的连续部分。</p>
	</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "1001", k = 3
<strong>输出:</strong> 4
<strong>解释:</strong> 我们可以获得以下字符串:
- 对字符串不应用任何操作将得到 s = "1001"。
- 对从下标 0 开始的子字符串应用一个操作，得到 s = "<u><strong>011</strong></u>1"。
- 对从下标 1 开始的子字符串应用一个操作，得到 s = "1<u><strong>110</strong></u>"。
- 对从下标 0 和 1 开始的两个子字符串都应用一个操作，得到 s = "<u><strong>0000</strong></u>"。
可以证明，我们不能获得任何其他字符串，所以答案是 4。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "10110", k = 5
<strong>输出:</strong> 2
<strong>解释:</strong> 我们可以获得以下字符串:
- 对字符串不执行任何操作，将得到 s = "10110"。
- 对整个字符串应用一个操作将得到 s = "01001"。
可以证明，我们不能获得任何其他字符串，所以答案是 2。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;是&nbsp;<code>0</code> 或&nbsp;<code>1</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

假设字符串 $s$ 长度为 $n$，那么长度为 $k$ 的子串共有 $n - k + 1$ 个，每个子串都可以翻转，因此共有 $2^{n - k + 1}$ 种翻转方式。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countDistinctStrings(self, s: str, k: int) -> int:
        return pow(2, len(s) - k + 1) % (10**9 + 7)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public static final int MOD = (int) 1e9 + 7;

    public int countDistinctStrings(String s, int k) {
        int ans = 1;
        for (int i = 0; i < s.length() - k + 1; ++i) {
            ans = (ans * 2) % MOD;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countDistinctStrings(string s, int k) {
        int ans = 1;
        for (int i = 0; i < s.size() - k + 1; ++i) {
            ans = (ans * 2) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func countDistinctStrings(s string, k int) int {
	const mod int = 1e9 + 7
	ans := 1
	for i := 0; i < len(s)-k+1; i++ {
		ans = (ans * 2) % mod
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
