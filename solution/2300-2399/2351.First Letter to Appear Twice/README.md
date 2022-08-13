# [2351. 第一个出现两次的字母](https://leetcode.cn/problems/first-letter-to-appear-twice)

[English Version](/solution/2300-2399/2351.First%20Letter%20to%20Appear%20Twice/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由小写英文字母组成的字符串 <code>s</code> ，请你找出并返回第一个出现 <strong>两次</strong> 的字母。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>如果 <code>a</code> 的 <strong>第二次</strong> 出现比 <code>b</code> 的 <strong>第二次</strong> 出现在字符串中的位置更靠前，则认为字母 <code>a</code> 在字母 <code>b</code> 之前出现两次。</li>
	<li><code>s</code> 包含至少一个出现两次的字母。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "abccbaacz"
<strong>输出：</strong>"c"
<strong>解释：</strong>
字母 'a' 在下标 0 、5 和 6 处出现。
字母 'b' 在下标 1 和 4 处出现。
字母 'c' 在下标 2 、3 和 7 处出现。
字母 'z' 在下标 8 处出现。
字母 'c' 是第一个出现两次的字母，因为在所有字母中，'c' 第二次出现的下标是最小的。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "abcdd"
<strong>输出：</strong>"d"
<strong>解释：</strong>
只有字母 'd' 出现两次，所以返回 'd' 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
	<li><code>s</code> 包含至少一个重复字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def repeatedCharacter(self, s: str) -> str:
        cnt = Counter()
        for v in s:
            cnt[v] += 1
            if cnt[v] == 2:
                return v
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public char repeatedCharacter(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            if (++cnt[c - 'a'] == 2) {
                return c;
            }
        }
        return '.';
    }
}
```

### **C++**

```cpp
class Solution {
public:
    char repeatedCharacter(string s) {
        vector<int> cnt(26);
        for (char c : s)
            if (++cnt[c - 'a'] == 2) return c;
        return '.';
    }
};
```

### **Go**

```go
func repeatedCharacter(s string) byte {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
		if cnt[c-'a'] == 2 {
			return byte(c)
		}
	}
	return '.'
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
