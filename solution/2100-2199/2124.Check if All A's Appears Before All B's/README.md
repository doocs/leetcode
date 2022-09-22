# [2124. 检查是否所有 A 都在 B 之前](https://leetcode.cn/problems/check-if-all-as-appears-before-all-bs)

[English Version](/solution/2100-2199/2124.Check%20if%20All%20A%27s%20Appears%20Before%20All%20B%27s/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>仅</strong> 由字符 <code>'a'</code> 和 <code>'b'</code> 组成的字符串&nbsp; <code>s</code> 。如果字符串中 <strong>每个</strong> <em> </em><code>'a'</code> 都出现在 <strong>每个</strong><em> </em><code>'b'</code><em> </em>之前，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "aaabbb"
<strong>输出：</strong>true
<strong>解释：</strong>
'a' 位于下标 0、1 和 2 ；而 'b' 位于下标 3、4 和 5 。
因此，每个 'a' 都出现在每个 'b' 之前，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "abab"
<strong>输出：</strong>false
<strong>解释：</strong>
存在一个 'a' 位于下标 2 ，而一个 'b' 位于下标 1 。
因此，不能满足每个 'a' 都出现在每个 'b' 之前，所以返回 false 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = "bbb"
<strong>输出：</strong>true
<strong>解释：</strong>
不存在 'a' ，因此可以视作每个 'a' 都出现在每个 'b' 之前，所以返回 true 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s[i]</code> 为 <code>'a'</code> 或 <code>'b'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题意，字符串 $s$ 仅由字符 `a`, `b` 组成。

要使得所有 `a` 都在 `b` 之前出现，需要满足 `b` 之后不会出现 `a`，也就是说，字符串 "ba" 不是字符串 $s$ 的子串，条件才能成立。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkString(self, s: str) -> bool:
        return "ba" not in s
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkString(String s) {
        return !s.contains("ba");
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkString(string s) {
        return s.find("ba") == string::npos;
    }
};
```

### **Go**

```go
func checkString(s string) bool {
	return !strings.Contains(s, "ba")
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
