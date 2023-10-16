# [1307. 口算难题](https://leetcode.cn/problems/verbal-arithmetic-puzzle)

[English Version](/solution/1300-1399/1307.Verbal%20Arithmetic%20Puzzle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个方程，左边用&nbsp;<code>words</code>&nbsp;表示，右边用&nbsp;<code>result</code> 表示。</p>

<p>你需要根据以下规则检查方程是否可解：</p>

<ul>
	<li>每个字符都会被解码成一位数字（0 - 9）。</li>
	<li>每对不同的字符必须映射到不同的数字。</li>
	<li>每个 <code>words[i]</code> 和 <code>result</code>&nbsp;都会被解码成一个没有前导零的数字。</li>
	<li>左侧数字之和（<code>words</code>）等于右侧数字（<code>result</code>）。&nbsp;</li>
</ul>

<p>如果方程可解，返回&nbsp;<code>True</code>，否则返回&nbsp;<code>False</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = [&quot;SEND&quot;,&quot;MORE&quot;], result = &quot;MONEY&quot;
<strong>输出：</strong>true
<strong>解释：</strong>映射 &#39;S&#39;-&gt; 9, &#39;E&#39;-&gt;5, &#39;N&#39;-&gt;6, &#39;D&#39;-&gt;7, &#39;M&#39;-&gt;1, &#39;O&#39;-&gt;0, &#39;R&#39;-&gt;8, &#39;Y&#39;-&gt;&#39;2&#39;
所以 &quot;SEND&quot; + &quot;MORE&quot; = &quot;MONEY&quot; ,  9567 + 1085 = 10652</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = [&quot;SIX&quot;,&quot;SEVEN&quot;,&quot;SEVEN&quot;], result = &quot;TWENTY&quot;
<strong>输出：</strong>true
<strong>解释：</strong>映射 &#39;S&#39;-&gt; 6, &#39;I&#39;-&gt;5, &#39;X&#39;-&gt;0, &#39;E&#39;-&gt;8, &#39;V&#39;-&gt;7, &#39;N&#39;-&gt;2, &#39;T&#39;-&gt;1, &#39;W&#39;-&gt;&#39;3&#39;, &#39;Y&#39;-&gt;4
所以 &quot;SIX&quot; + &quot;SEVEN&quot; + &quot;SEVEN&quot; = &quot;TWENTY&quot; ,  650 + 68782 + 68782 = 138214</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>words = [&quot;THIS&quot;,&quot;IS&quot;,&quot;TOO&quot;], result = &quot;FUNNY&quot;
<strong>输出：</strong>true
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>words = [&quot;LEET&quot;,&quot;CODE&quot;], result = &quot;POINT&quot;
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= words.length &lt;= 5</code></li>
	<li><code>1 &lt;= words[i].length,&nbsp;results.length&nbsp;&lt;= 7</code></li>
	<li><code>words[i], result</code>&nbsp;只含有大写英文字母</li>
	<li>表达式中使用的不同字符数最大为&nbsp;10</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isSolvable(self, words: List[str], result: str) -> bool:
        if max(map(len, words)) > len(
            result
        ):  # If any of the words are bigger than the result, it will be impossible to solve
            return False
        # Add the result to the list, this way we will only subtract values when it comes to the result word.
        # Thus at every index, if the total is zero, then for that letter index the formulat is correct
        words = [word[::-1] for word in words] + [result[::-1]]
        values = {}  # Mapping from letter to values
        nums = [0] * 10

        # i: word index, j: ltr index, total: total current Sum
        def dfs(i, j, total):
            if j == len(
                result
            ):  # Reached end of the indecies for ltrs in all words (END)
                return (
                    total % 10 == 0
                )  # Checking to see if the total for the current character is correct or not
            if i == len(words):  # Checked ltr at index j for all the words
                return total % 10 == 0 and dfs(0, j + 1, total // 10)

            if j >= len(words[i]):
                return dfs(i + 1, j, total)

            if words[i][j] in values:
                if (
                    values[words[i][j]] == 0
                    and j == len(words[i]) - 1
                    and len(words[i]) > 1
                ):
                    return False
                if i == len(words) - 1:
                    return dfs(i + 1, j, total - values[words[i][j]])
                else:
                    return dfs(i + 1, j, total + values[words[i][j]])
            else:
                for val, isUsed in enumerate(nums):
                    if not isUsed and (val != 0 or j == 0 or j < len(words[i]) - 1):
                        values[words[i][j]] = val
                        nums[val] = True

                        if i == len(words) - 1 and dfs(
                            i + 1, j, total - values[words[i][j]]
                        ):
                            return True
                        elif i < len(words) - 1 and dfs(
                            i + 1, j, total + values[words[i][j]]
                        ):
                            return True

                        values.pop(words[i][j])
                        nums[val] = False

        return dfs(0, 0, 0)

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
