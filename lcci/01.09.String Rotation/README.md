# [面试题 01.09. 字符串轮转](https://leetcode-cn.com/problems/string-rotation-lcci)

## 题目描述
<!-- 这里写题目描述 -->
<p>字符串轮转。给定两个字符串<code>s1</code>和<code>s2</code>，请编写代码检查<code>s2</code>是否为<code>s1</code>旋转而成（比如，<code>waterbottle</code>是<code>erbottlewat</code>旋转后的字符串）。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：s1 = &quot;waterbottle&quot;, s2 = &quot;erbottlewat&quot;
<strong> 输出</strong>：True
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：s1 = &quot;aa&quot;, &quot;aba&quot;
<strong> 输出</strong>：False
</pre>

<ol>
</ol>

<p><strong>提示：</strong></p>

<ol>
	<li>字符串长度在[0, 100000]范围内。</li>
</ol>

<p><strong>说明:</strong></p>

<ol>
	<li>你能只调用一次检查子串的方法吗？</li>
</ol>


## 解法
<!-- 这里可写通用的实现逻辑 -->


### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 != len2) {
            return false;
        }
        if ((len1 == 0 && len2 == 0) || (s1.equals(s2))) {
            return true;
        }

        for (int i = 0; i < len1; ++i) {
            s1 = flip(s1);
            if (s1.equals(s2)) {
                return true;
            }
        }
        return false;

    }

    private String flip(String s) {
        return s.substring(1) + s.charAt(0);
    }
}
```

### ...
```

```
