# [1307. Verbal Arithmetic Puzzle](https://leetcode.com/problems/verbal-arithmetic-puzzle)

[中文文档](/solution/1300-1399/1307.Verbal%20Arithmetic%20Puzzle/README.md)

## Description

<p>Given an equation, represented by <code>words</code> on the left side and the <code>result</code> on the right side.</p>

<p>You need to check if the equation is solvable under the following rules:</p>

<ul>
	<li>Each character is decoded as one digit (0 - 9).</li>
	<li>No two characters can map to the same digit.</li>
	<li>Each <code>words[i]</code> and <code>result</code> are decoded as one number <strong>without</strong> leading zeros.</li>
	<li>Sum of numbers on the left side (<code>words</code>) will equal to the number on the right side (<code>result</code>).</li>
</ul>

<p>Return <code>true</code> <em>if the equation is solvable, otherwise return</em> <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;SEND&quot;,&quot;MORE&quot;], result = &quot;MONEY&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Map &#39;S&#39;-&gt; 9, &#39;E&#39;-&gt;5, &#39;N&#39;-&gt;6, &#39;D&#39;-&gt;7, &#39;M&#39;-&gt;1, &#39;O&#39;-&gt;0, &#39;R&#39;-&gt;8, &#39;Y&#39;-&gt;&#39;2&#39;
Such that: &quot;SEND&quot; + &quot;MORE&quot; = &quot;MONEY&quot; ,  9567 + 1085 = 10652</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;SIX&quot;,&quot;SEVEN&quot;,&quot;SEVEN&quot;], result = &quot;TWENTY&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Map &#39;S&#39;-&gt; 6, &#39;I&#39;-&gt;5, &#39;X&#39;-&gt;0, &#39;E&#39;-&gt;8, &#39;V&#39;-&gt;7, &#39;N&#39;-&gt;2, &#39;T&#39;-&gt;1, &#39;W&#39;-&gt;&#39;3&#39;, &#39;Y&#39;-&gt;4
Such that: &quot;SIX&quot; + &quot;SEVEN&quot; + &quot;SEVEN&quot; = &quot;TWENTY&quot; ,  650 + 68782 + 68782 = 138214</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;LEET&quot;,&quot;CODE&quot;], result = &quot;POINT&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no possible mapping to satisfy the equation, so we return false.
Note that two different characters cannot map to the same digit.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= words.length &lt;= 5</code></li>
	<li><code>1 &lt;= words[i].length, result.length &lt;= 7</code></li>
	<li><code>words[i], result</code> contain only uppercase English letters.</li>
	<li>The number of different characters used in the expression is at most <code>10</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

```java

```

### **...**

```

```

<!-- tabs:end -->
