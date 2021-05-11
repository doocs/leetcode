# [08.09. Bracket](https://leetcode-cn.com/problems/bracket-lcci)

[中文文档](/lcci/08.09.Bracket/README.md)

## Description

<p>Implement an algorithm to print all valid (e.g., properly opened and closed) combinations of n pairs of parentheses.</p>

<p>Note: The result set should not contain duplicated subsets.</p>

<p>For example, given&nbsp;n = 3, the result should be:</p>

<pre>

[

  &quot;((()))&quot;,

  &quot;(()())&quot;,

  &quot;(())()&quot;,

  &quot;()(())&quot;,

  &quot;()()()&quot;

]

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        res = []
        def generate(state, left, right):
            if left > right:
                return
            if right == 0:
                res.append(state)
                return
            if left > 0:
                generate(state + '(', left - 1, right)
            if right > 0:
                generate(state + ')', left, right - 1)
        generate('', n, n)
        return res
```

### **Java**

```java
class Solution {
    List<String> res;

    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        generate("", n, n);
        return res;
    }

    private void generate(String state, int left, int right) {
        if (left > right) {
            return;
        }
        if (right == 0) {
            res.add(state);
            return;
        }
        if (left > 0) {
            generate(state + "(", left - 1, right);
        }
        if (right > 0) {
            generate(state + ")", left, right - 1);
        }
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {string[]}
 */
 var generateParenthesis = function(n) {
    let res = [];
    dfs(n, 0, 0, '', res);
    return res;
};

function dfs(n, left, right, prev, res) {
    if (left == n && right == n) {
        res.push(prev);
        return;
    }
    if (left < n) {
        dfs(n, left + 1, right, prev + '(', res);
    }
    if (right < left) {
        dfs(n, left, right + 1, prev + ')', res);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
