# [293. Flip Game](https://leetcode.com/problems/flip-game)

[中文文档](/solution/0200-0299/0293.Flip%20Game/README.md)

## Description

<p>You are playing the following Flip Game with your friend: Given a string that contains only these two characters: <code>+</code> and <code>-</code>, you and your friend take turns to flip two <b>consecutive</b> <code>"++"</code> into <code>"--"</code>. The game ends when a person can no longer make a move and therefore the other person will be the winner.</p>

<p>Write a function to compute all possible states of the string after one valid move.</p>

<p><strong>Example:</strong></p>

<pre>
<strong>Input:</strong> <code>s = "++++"</code>
<strong>Output:</strong> 
[
  "--++",
  "+--+",
  "++--"
]
</pre>

<p><strong>Note: </strong>If there is no valid move, return an empty list <code>[]</code>.</p>

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
