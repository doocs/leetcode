# [282. 给表达式添加运算符](https://leetcode-cn.com/problems/expression-add-operators)

[English Version](/solution/0200-0299/0282.Expression%20Add%20Operators/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个仅包含数字 <code>0-9</code> 的字符串和一个目标值，在数字之间添加 <strong>二元 </strong>运算符（不是一元）<code>+</code>、<code>-</code> 或 <code>*</code> ，返回所有能够得到目标值的表达式。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <code><em>num</em> = </code>"123", <em>target</em> = 6
<strong>输出: </strong>["1+2+3", "1*2*3"] 
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> <code><em>num</em> = </code>"232", <em>target</em> = 8
<strong>输出: </strong>["2*3+2", "2+3*2"]</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> <code><em>num</em> = </code>"105", <em>target</em> = 5
<strong>输出: </strong>["1*0+5","10-5"]</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入:</strong> <code><em>num</em> = </code>"00", <em>target</em> = 0
<strong>输出: </strong>["0+0", "0-0", "0*0"]
</pre>

<p><strong>示例 5:</strong></p>

<pre>
<strong>输入:</strong> <code><em>num</em> = </code>"3456237490", <em>target</em> = 9191
<strong>输出: </strong>[]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= num.length <= 10</code></li>
	<li><code>num</code> 仅含数字</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
