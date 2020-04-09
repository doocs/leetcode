# [面试题 08.09. 括号](https://leetcode-cn.com/problems/bracket-lcci)

## 题目描述
<!-- 这里写题目描述 -->
<p>括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。</p>

<p>说明：解集不能包含重复的子集。</p>

<p>例如，给出 n = 3，生成结果为：</p>

<pre>
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
</pre>


## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->
递归求解。其中，`left` 表示剩余的 `(`，`right` 表示剩余的 `)`。

- 当 `left` > `right` 时，说明 state 中 `(` 少于 `)`，不是合法组合，直接剪枝；
- 当 `right` == 0 时，说明 state 组合完毕；
- 当 `left` > 0 时，此时可往 state 添加一个 `(`；
- 当 `right` > 0 时，此时可往 state 添加一个 `)`。


```python
class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        res = []
        def generate(state, left, right):
            # 剩余的`(`多于`)`
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

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### ...
```

```
