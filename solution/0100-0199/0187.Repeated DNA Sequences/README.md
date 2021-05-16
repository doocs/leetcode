# [187. 重复的DNA序列](https://leetcode-cn.com/problems/repeated-dna-sequences)

[English Version](/solution/0100-0199/0187.Repeated%20DNA%20Sequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>所有 DNA 都由一系列缩写为 <code>'A'</code>，<code>'C'</code>，<code>'G'</code> 和 <code>'T'</code> 的核苷酸组成，例如：<code>"ACGAATTCCG"</code>。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。</p>

<p>编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 <code>s</code> 中出现次数超过一次。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
<strong>输出：</strong>["AAAAACCCCC","CCCCCAAAAA"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "AAAAAAAAAAAAA"
<strong>输出：</strong>["AAAAAAAAAA"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= s.length <= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 为 <code>'A'</code>、<code>'C'</code>、<code>'G'</code> 或 <code>'T'</code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        n = 10
        subs = set()
        res = set()
        for i in range(len(s) - n + 1):
            sub = s[i:i + n]
            if sub in subs:
                res.add(sub)
            subs.add(sub)
        return list(res)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int len = 10;
        Set<String> subs = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (int i = 0; i < s.length() - len + 1; ++i) {
            String sub = s.substring(i, i + len);
            if (subs.contains(sub)) {
                res.add(sub);
            }
            subs.add(sub);
        }
        return new ArrayList<>(res);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
