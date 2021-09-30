# [1941. 检查是否所有字符出现次数相同](https://leetcode-cn.com/problems/check-if-all-characters-have-equal-number-of-occurrences)

[English Version](/solution/1900-1999/1941.Check%20if%20All%20Characters%20Have%20Equal%20Number%20of%20Occurrences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，如果 <code>s</code> 是一个 <strong>好</strong> 字符串，请你返回 <code>true</code> ，否则请返回 <code>false</code> 。</p>

<p>如果 <code>s</code> 中出现过的 <strong>所有</strong> 字符的出现次数 <strong>相同</strong> ，那么我们称字符串 <code>s</code> 是 <strong>好</strong> 字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "abacbc"
<b>输出：</b>true
<b>解释：</b>s 中出现过的字符为 'a'，'b' 和 'c' 。s 中所有字符均出现 2 次。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "aaabb"
<b>输出：</b>false
<b>解释：</b>s 中出现过的字符为 'a' 和 'b' 。
'a' 出现了 3 次，'b' 出现了 2 次，两者出现次数不同。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 只包含小写英文字母。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def areOccurrencesEqual(self, s: str) -> bool:
        counter = collections.Counter(s)
        cnt = -1
        for c, times in counter.items():
            if cnt == -1:
                cnt = times
            elif cnt != times:
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean areOccurrencesEqual(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        int cnt = -1;
        for (int i = 0; i < 26; ++i) {
            if (counter[i] == 0) {
                continue;
            }

            if (cnt == -1) {
                cnt = counter[i];
            } else if (cnt != counter[i]) {
                return false;
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
