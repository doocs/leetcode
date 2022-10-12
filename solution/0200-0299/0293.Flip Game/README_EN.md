# [293. Flip Game](https://leetcode.com/problems/flip-game)

[中文文档](/solution/0200-0299/0293.Flip%20Game/README.md)

## Description

<p>You are playing a Flip Game with your friend.</p>

<p>You are given a string <code>currentState</code> that contains only <code>&#39;+&#39;</code> and <code>&#39;-&#39;</code>. You and your friend take turns to flip <strong>two consecutive</strong> <code>&quot;++&quot;</code> into <code>&quot;--&quot;</code>. The game ends when a person can no longer make a move, and therefore the other person will be the winner.</p>

<p>Return all possible states of the string <code>currentState</code> after <strong>one valid move</strong>. You may return the answer in <strong>any order</strong>. If there is no valid move, return an empty list <code>[]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> currentState = &quot;++++&quot;
<strong>Output:</strong> [&quot;--++&quot;,&quot;+--+&quot;,&quot;++--&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

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
    def generatePossibleNextMoves(self, currentState: str) -> List[str]:
        s = list(currentState)
        ans = []
        for i, c in enumerate(s[:-1]):
            if c == "+" and s[i + 1] == "+":
                s[i] = s[i + 1] = "-"
                ans.append("".join(s))
                s[i] = s[i + 1] = "+"
        return ans
```

### **Java**

```java
class Solution {
    public List<String> generatePossibleNextMoves(String currentState) {
        char[] cs = currentState.toCharArray();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < cs.length - 1; ++i) {
            if (cs[i] == '+' && cs[i + 1] == '+') {
                cs[i] = '-';
                cs[i + 1] = '-';
                ans.add(String.valueOf(cs));
                cs[i] = '+';
                cs[i + 1] = '+';
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> generatePossibleNextMoves(string currentState) {
        vector<string> ans;
        for (int i = 0; i < currentState.size() - 1; ++i) {
            if (currentState[i] == '+' && currentState[i + 1] == '+') {
                currentState[i] = '-';
                currentState[i + 1] = '-';
                ans.push_back(currentState);
                currentState[i] = '+';
                currentState[i + 1] = '+';
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func generatePossibleNextMoves(currentState string) []string {
	ans := []string{}
	cs := []byte(currentState)
	for i, c := range cs[1:] {
		if c == '+' && cs[i] == '+' {
			cs[i], cs[i+1] = '-', '-'
			ans = append(ans, string(cs))
			cs[i], cs[i+1] = '+', '+'
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
