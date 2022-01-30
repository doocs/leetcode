# [318. 最大单词长度乘积](https://leetcode-cn.com/problems/maximum-product-of-word-lengths)

[English Version](/solution/0300-0399/0318.Maximum%20Product%20of%20Word%20Lengths/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串数组&nbsp;<code>words</code>，找到&nbsp;<code>length(word[i]) * length(word[j])</code>&nbsp;的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> <code>[&quot;abcw&quot;,&quot;baz&quot;,&quot;foo&quot;,&quot;bar&quot;,&quot;xtfn&quot;,&quot;abcdef&quot;]</code>
<strong>输出: </strong><code>16 
<strong>解释:</strong> 这两个单词为<strong> </strong></code><code>&quot;abcw&quot;, &quot;xtfn&quot;</code>。</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> <code>[&quot;a&quot;,&quot;ab&quot;,&quot;abc&quot;,&quot;d&quot;,&quot;cd&quot;,&quot;bcd&quot;,&quot;abcd&quot;]</code>
<strong>输出: </strong><code>4 
<strong>解释: </strong></code>这两个单词为 <code>&quot;ab&quot;, &quot;cd&quot;</code>。</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> <code>[&quot;a&quot;,&quot;aa&quot;,&quot;aaa&quot;,&quot;aaaa&quot;]</code>
<strong>输出: </strong><code>0 
<strong>解释: </strong>不存在这样的两个单词。</code></pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

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
        int[] masks = new int[n];
        for (int i = 0; i < n; ++i) {
            for (char c : words[i].toCharArray()) {
                masks[i] |= (1 << (c - 'a'));
            }
        }
        int ans = 0;
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if ((masks[i] & masks[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
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
                    ans = max(ans, (int) (words[i].size() * words[j].size()));
        return ans;
    }
};
```

### **Go**

```go
func maxProduct(words []string) int {
	n := len(words)
	mask := make([]int, n)
	for i, word := range words {
		for _, c := range word {
			mask[i] |= (1 << (c - 'a'))
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

### **...**

```

```

<!-- tabs:end -->
