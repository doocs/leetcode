# [293. Flip Game](https://leetcode.com/problems/flip-game)

[中文文档](/solution/0200-0299/0293.Flip%20Game/README.md)

## Description

<p>You are playing a Flip Game with your friend.</p>

<p>You are given a string <code>currentState</code> that contains only <code>&#39;+&#39;</code> and <code>&#39;-&#39;</code>. You and your friend take turns to flip <strong>two consecutive</strong> <code>&quot;++&quot;</code> into <code>&quot;--&quot;</code>. The game ends when a person can no longer make a move, and therefore the other person will be the winner.</p>

<p>Return all possible states of the string <code>currentState</code> after <strong>one valid move</strong>. You may return the answer in <strong>any order</strong>. If there is no valid move, return an empty list <code>[]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> currentState = &quot;++++&quot;
<strong>Output:</strong> [&quot;--++&quot;,&quot;+--+&quot;,&quot;++--&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> currentState = &quot;+&quot;
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= currentState.length &lt;= 500</code></li>
	<li><code>currentState[i]</code> is either <code>&#39;+&#39;</code> or <code>&#39;-&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def generatePossibleNextMoves(self, s: str) -> List[str]:
        if not s or len(s) < 2:
            return []
        n = len(s)
        res = []
        for i in range(n - 1):
            if s[i] == '+' and s[i + 1] == '+':
                res.append(s[:i] + "--" + s[i + 2:])
        return res
```

### **Java**

```java
class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        int n;
        if (s == null || (n = s.length()) < 2) return Collections.emptyList();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n - 1; ++i) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                StringBuilder sb = new StringBuilder(s);
                sb.replace(i, i + 2, "--");
                res.add(sb.toString());
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
