# [1347. 制造字母异位词的最小步骤数](https://leetcode.cn/problems/minimum-number-of-steps-to-make-two-strings-anagram)

[English Version](/solution/1300-1399/1347.Minimum%20Number%20of%20Steps%20to%20Make%20Two%20Strings%20Anagram/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个长度相等的字符串&nbsp;<code>s</code> 和 <code>t</code>。每一个步骤中，你可以选择将&nbsp;<code>t</code>&nbsp;中的 <strong>任一字符</strong> 替换为 <strong>另一个字符</strong>。</p>

<p>返回使&nbsp;<code>t</code>&nbsp;成为&nbsp;<code>s</code>&nbsp;的字母异位词的最小步骤数。</p>

<p><strong>字母异位词</strong> 指字母相同，但排列不同（也可能相同）的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输出：</strong>s = &quot;bab&quot;, t = &quot;aba&quot;
<strong>输出：</strong>1
<strong>提示：</strong>用 &#39;b&#39; 替换 t 中的第一个 &#39;a&#39;，t = &quot;bba&quot; 是 s 的一个字母异位词。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输出：</strong>s = &quot;leetcode&quot;, t = &quot;practice&quot;
<strong>输出：</strong>5
<strong>提示：</strong>用合适的字符替换 t 中的 &#39;p&#39;, &#39;r&#39;, &#39;a&#39;, &#39;i&#39; 和 &#39;c&#39;，使 t 变成 s 的字母异位词。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输出：</strong>s = &quot;anagram&quot;, t = &quot;mangaar&quot;
<strong>输出：</strong>0
<strong>提示：</strong>&quot;anagram&quot; 和 &quot;mangaar&quot; 本身就是一组字母异位词。 
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输出：</strong>s = &quot;xxyyzz&quot;, t = &quot;xxyyzz&quot;
<strong>输出：</strong>0
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输出：</strong>s = &quot;friend&quot;, t = &quot;family&quot;
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50000</code></li>
	<li><code>s.length == t.length</code></li>
	<li><code>s</code> 和 <code>t</code>&nbsp;只包含小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSteps(self, s: str, t: str) -> int:
        counter = Counter(s)
        res = 0
        for c in t:
            if counter[c] > 0:
                counter[c] -= 1
            else:
                res += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSteps(String s, String t) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        int res = 0;
        for (char c : t.toCharArray()) {
            if (counter[c - 'a'] > 0) {
                --counter[c - 'a'];
            } else {
                ++res;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSteps(string s, string t) {
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        int res = 0;
        for (char c : t) {
            if (counter[c - 'a'] > 0)
                --counter[c - 'a'];
            else
                ++res;
        }
        return res;
    }
};
```

### **Go**

```go
func minSteps(s string, t string) int {
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	res := 0
	for _, c := range t {
		if counter[c-'a'] > 0 {
			counter[c-'a']--
		} else {
			res++
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
