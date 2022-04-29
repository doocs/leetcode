# [1400. 构造 K 个回文字符串](https://leetcode.cn/problems/construct-k-palindrome-strings)

[English Version](/solution/1400-1499/1400.Construct%20K%20Palindrome%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code>&nbsp;和一个整数 <code>k</code>&nbsp;。请你用 <code>s</code>&nbsp;字符串中 <strong>所有字符</strong>&nbsp;构造 <code>k</code>&nbsp;个非空 <strong>回文串</strong>&nbsp;。</p>

<p>如果你可以用&nbsp;<code>s</code>&nbsp;中所有字符构造&nbsp;<code>k</code>&nbsp;个回文字符串，那么请你返回 <strong>True</strong>&nbsp;，否则返回&nbsp;<strong>False</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;annabelle&quot;, k = 2
<strong>输出：</strong>true
<strong>解释：</strong>可以用 s 中所有字符构造 2 个回文字符串。
一些可行的构造方案包括：&quot;anna&quot; + &quot;elble&quot;，&quot;anbna&quot; + &quot;elle&quot;，&quot;anellena&quot; + &quot;b&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;leetcode&quot;, k = 3
<strong>输出：</strong>false
<strong>解释：</strong>无法用 s 中所有字符构造 3 个回文串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;true&quot;, k = 4
<strong>输出：</strong>true
<strong>解释：</strong>唯一可行的方案是让 s 中每个字符单独构成一个字符串。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;yzyzyzyzyzyzyzy&quot;, k = 2
<strong>输出：</strong>true
<strong>解释：</strong>你只需要将所有的 z 放在一个字符串中，所有的 y 放在另一个字符串中。那么两个字符串都是回文串。
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;cr&quot;, k = 7
<strong>输出：</strong>false
<strong>解释：</strong>我们没有足够的字符去构造 7 个回文串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>s</code>&nbsp;中所有字符都是小写英文字母。</li>
	<li><code>1 &lt;= k &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canConstruct(self, s: str, k: int) -> bool:
        if len(s) < k:
            return False
        counter = Counter(s)
        cnt = sum(1 for n in counter.values() if n % 2 == 1)
        return cnt <= k
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canConstruct(String s, int k) {
        if (s.length() < k) {
            return false;
        }
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        int cnt = 0;
        for (int v : counter) {
            if (v % 2 == 1) {
                ++cnt;
            }
        }
        return cnt <= k;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canConstruct(string s, int k) {
        if (s.size() < k) return 0;
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        int cnt = 0;
        for (int v : counter)
            if (v % 2)
                ++cnt;
        return cnt <= k;
    }
};
```

### **Go**

```go
func canConstruct(s string, k int) bool {
	if len(s) < k {
		return false
	}
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	cnt := 0
	for _, v := range counter {
		if v%2 == 1 {
			cnt++
		}
	}
	return cnt <= k
}
```

### **...**

```

```

<!-- tabs:end -->
