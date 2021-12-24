# [293. 翻转游戏](https://leetcode-cn.com/problems/flip-game)

[English Version](/solution/0200-0299/0293.Flip%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你和朋友玩一个叫做「翻转游戏」的游戏。游戏规则如下：</p>

<p>给你一个字符串 <code>currentState</code> ，其中只含 <code>'+'</code> 和 <code>'-'</code> 。你和朋友轮流将 <strong>连续 </strong>的两个 <code>"++"</code> 反转成 <code>"--"</code> 。当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。</p>

<p>计算并返回 <strong>一次有效操作</strong> 后，字符串 <code>currentState</code> 所有的可能状态，返回结果可以按 <strong>任意顺序</strong> 排列。如果不存在可能的有效操作，请返回一个空列表 <code>[]</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>currentState = "++++"
<strong>输出：</strong>["--++","+--+","++--"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>currentState = "+"
<strong>输出：</strong>[]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= currentState.length <= 500</code></li>
	<li><code>currentState[i]</code> 不是 <code>'+'</code> 就是 <code>'-'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
