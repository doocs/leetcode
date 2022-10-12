# [282. Expression Add Operators](https://leetcode.com/problems/expression-add-operators)

[中文文档](/solution/0200-0299/0282.Expression%20Add%20Operators/README.md)

## Description

<p>Given a string <code>num</code> that contains only digits and an integer <code>target</code>, return <em><strong>all possibilities</strong> to insert the binary operators </em><code>&#39;+&#39;</code><em>, </em><code>&#39;-&#39;</code><em>, and/or </em><code>&#39;*&#39;</code><em> between the digits of </em><code>num</code><em> so that the resultant expression evaluates to the </em><code>target</code><em> value</em>.</p>

<p>Note that operands in the returned expressions <strong>should not</strong> contain leading zeros.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;123&quot;, target = 6
<strong>Output:</strong> [&quot;1*2*3&quot;,&quot;1+2+3&quot;]
<strong>Explanation:</strong> Both &quot;1*2*3&quot; and &quot;1+2+3&quot; evaluate to 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;232&quot;, target = 8
<strong>Output:</strong> [&quot;2*3+2&quot;,&quot;2+3*2&quot;]
<strong>Explanation:</strong> Both &quot;2*3+2&quot; and &quot;2+3*2&quot; evaluate to 8.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;3456237490&quot;, target = 9191
<strong>Output:</strong> []
<strong>Explanation:</strong> There are no expressions that can be created from &quot;3456237490&quot; to evaluate to 9191.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10</code></li>
	<li><code>num</code> consists of only digits.</li>
	<li><code>-2<sup>31</sup> &lt;= target &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def addOperators(self, num: str, target: int) -> List[str]:
        ans = []

        def dfs(u, prev, curr, path):
            if u == len(num):
                if curr == target:
                    ans.append(path)
                return
            for i in range(u, len(num)):
                if i != u and num[u] == '0':
                    break
                next = int(num[u : i + 1])
                if u == 0:
                    dfs(i + 1, next, next, path + str(next))
                else:
                    dfs(i + 1, next, curr + next, path + "+" + str(next))
                    dfs(i + 1, -next, curr - next, path + "-" + str(next))
                    dfs(
                        i + 1,
                        prev * next,
                        curr - prev + prev * next,
                        path + "*" + str(next),
                    )

        dfs(0, 0, 0, "")
        return ans
```

### **Java**

```java

class Solution {
    private List<String> ans;
    private String num;
    private int target;

    public List<String> addOperators(String num, int target) {
        ans = new ArrayList<>();
        this.num = num;
        this.target = target;
        dfs(0, 0, 0, "");
        return ans;
    }

    private void dfs(int u, long prev, long curr, String path) {
        if (u == num.length()) {
            if (curr == target) ans.add(path);
            return;
        }
        for (int i = u; i < num.length(); i++) {
            if (i != u && num.charAt(u) == '0') {
                break;
            }
            long next = Long.parseLong(num.substring(u, i + 1));
            if (u == 0) {
                dfs(i + 1, next, next, path + next);
            } else {
                dfs(i + 1, next, curr + next, path + "+" + next);
                dfs(i + 1, -next, curr - next, path + "-" + next);
                dfs(i + 1, prev * next, curr - prev + prev * next, path + "*" + next);
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
