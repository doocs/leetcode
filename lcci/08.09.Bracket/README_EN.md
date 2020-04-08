# [08.09. Bracket](https://leetcode-cn.com/problems/bracket-lcci)

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


### Python3

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

### Java

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
