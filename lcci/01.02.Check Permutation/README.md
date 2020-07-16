# [面试题 01.02. 判定是否互为字符重排](https://leetcode-cn.com/problems/check-permutation-lcci)

[English Version](/lcci/01.02.Check%20Permutation/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定两个字符串 <code>s1</code> 和 <code>s2</code>，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> <code>s1</code> = &quot;abc&quot;, <code>s2</code> = &quot;bca&quot;
<strong>输出:</strong> true 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> <code>s1</code> = &quot;abc&quot;, <code>s2</code> = &quot;bad&quot;
<strong>输出:</strong> false
</pre>

<p><strong>说明：</strong></p>

<ul>
	<li><code>0 &lt;= len(s1) &lt;= 100 </code></li>
	<li><code>0 &lt;= len(s2) &lt;= 100 </code></li>
</ul>


## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        return sorted(s1) == sorted(s2)
```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkPermutation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }
}
```

### ...
```

```
