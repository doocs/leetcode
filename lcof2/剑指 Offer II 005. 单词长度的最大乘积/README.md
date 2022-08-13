# [剑指 Offer II 005. 单词长度的最大乘积](https://leetcode.cn/problems/aseY1I)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串数组&nbsp;<code>words</code>，请计算当两个字符串 <code>words[i]</code> 和 <code>words[j]</code> 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> words = <code>[&quot;abcw&quot;,&quot;baz&quot;,&quot;foo&quot;,&quot;bar&quot;,&quot;fxyz&quot;,&quot;abcdef&quot;]</code>
<strong>输出: </strong><code>16 
<strong>解释:</strong> 这两个单词为<strong> </strong></code><code>&quot;abcw&quot;, &quot;fxyz&quot;</code>。它们不包含相同字符，且长度的乘积最大。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> words = <code>[&quot;a&quot;,&quot;ab&quot;,&quot;abc&quot;,&quot;d&quot;,&quot;cd&quot;,&quot;bcd&quot;,&quot;abcd&quot;]</code>
<strong>输出: </strong><code>4 
<strong>解释: </strong></code>这两个单词为 <code>&quot;ab&quot;, &quot;cd&quot;</code>。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> words = <code>[&quot;a&quot;,&quot;aa&quot;,&quot;aaa&quot;,&quot;aaaa&quot;]</code>
<strong>输出: </strong><code>0 
<strong>解释: </strong>不存在这样的两个单词。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 1000</code></li>
	<li><code>words[i]</code>&nbsp;仅包含小写字母</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 318&nbsp;题相同：<a href="https://leetcode.cn/problems/maximum-product-of-word-lengths/">https://leetcode.cn/problems/maximum-product-of-word-lengths/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

因为只有 26 个小写字符，所以可以用一个 `int32` 存储字符的出现情况，然后枚举最大乘积。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxProduct(self, words: List[str]) -> int:
        n = len(words)
        mask = [0] * n
        for i, word in enumerate(words):
            for ch in word:
                mask[i] |= 1 << (ord(ch) - ord('a'))
        ans = 0
        for i in range(n - 1):
            for j in range(i + 1, n):
                if mask[i] & mask[j] == 0:
                    ans = max(ans, len(words[i]) * len(words[j]))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] mask = new int[n];
        for (int i = 0; i < n; i++) {
            for (char ch : words[i].toCharArray()) {
                mask[i] |= 1 << (ch - 'a');
            }
        }
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((mask[i] & mask[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }
}
```

### **Go**

```go
func maxProduct(words []string) int {
	n := len(words)
	mask := make([]int32, n)
	for i, word := range words {
		for _, r := range word {
			mask[i] |= 1 << (r - 'a')
		}
	}
	ans := 0
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			if mask[i]&mask[j] == 0 {
				ans = max(ans, len(words[i])*len(words[j]))
			}
		}
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

### **C++**

```cpp
class Solution {
public:
    int maxProduct(vector<string>& words) {
        int n = words.size();
        vector<int> mask(n);
        for (int i = 0; i < n; ++i)
            for (char ch : words[i])
                mask[i] |= 1 << (ch - 'a');
        int ans = 0;
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                if (!(mask[i] & mask[j]))
                    ans = max(ans, (int)words[i].size() * (int)words[j].size());
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
