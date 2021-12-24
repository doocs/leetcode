# [282. Expression Add Operators](https://leetcode.com/problems/expression-add-operators)

[中文文档](/solution/0200-0299/0282.Expression%20Add%20Operators/README.md)

## Description

<p>Given a string <code>num</code> that contains only digits and an integer <code>target</code>, return <em>all possibilities to add the binary operators</em> <code>&#39;+&#39;</code>, <code>&#39;-&#39;</code>, <em>or</em> <code>&#39;*&#39;</code> <em>between the digits of</em> <code>num</code> <em>so that the resultant expression evaluates to the</em> <code>target</code> <em>value</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> num = "123", target = 6
<strong>Output:</strong> ["1*2*3","1+2+3"]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> num = "232", target = 8
<strong>Output:</strong> ["2*3+2","2+3*2"]
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> num = "105", target = 5
<strong>Output:</strong> ["1*0+5","10-5"]
</pre><p><strong>Example 4:</strong></p>
<pre><strong>Input:</strong> num = "00", target = 0
<strong>Output:</strong> ["0*0","0+0","0-0"]
</pre><p><strong>Example 5:</strong></p>
<pre><strong>Input:</strong> num = "3456237490", target = 9191
<strong>Output:</strong> []
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
                next = int(num[u: i+1])
                if u == 0:
                    dfs(i + 1, next, next, path + str(next))
                else:
                    dfs(i + 1,  next, curr + next, path + "+" + str(next))
                    dfs(i + 1, -next, curr - next, path + "-" + str(next))
                    dfs(i + 1, prev * next, curr - prev +
                        prev * next, path + "*" + str(next))

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
            return ;
        }
        for (int i = u; i < num.length(); i++) {
            if (i != u && num.charAt(u) == '0') {
                break;
            }
            long next = Long.parseLong(num.substring(u, i + 1));
            if (u == 0) {
                dfs(i + 1, next, next, path + next);
            } else {
                dfs(i + 1,  next, curr + next, path + "+" + next);
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
