---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0293.Flip%20Game/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [293. Flip Game 🔒](https://leetcode.com/problems/flip-game)

[中文文档](/solution/0200-0299/0293.Flip%20Game/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal + Simulation

We traverse the string. If the current character and the next character are both `+`, we change these two characters to `-`, add the result to the result array, and then change these two characters back to `+`.

After the traversal ends, we return the result array.

The time complexity is $O(n^2)$, where $n$ is the length of the string. Ignoring the space complexity of the result array, the space complexity is $O(n)$ or $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def generatePossibleNextMoves(self, currentState: str) -> List[str]:
        s = list(currentState)
        ans = []
        for i, (a, b) in enumerate(pairwise(s)):
            if a == b == "+":
                s[i] = s[i + 1] = "-"
                ans.append("".join(s))
                s[i] = s[i + 1] = "+"
        return ans
```

#### Java

```java
class Solution {
    public List<String> generatePossibleNextMoves(String currentState) {
        List<String> ans = new ArrayList<>();
        char[] s = currentState.toCharArray();
        for (int i = 0; i < s.length - 1; ++i) {
            if (s[i] == '+' && s[i + 1] == '+') {
                s[i] = '-';
                s[i + 1] = '-';
                ans.add(new String(s));
                s[i] = '+';
                s[i + 1] = '+';
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> generatePossibleNextMoves(string s) {
        vector<string> ans;
        for (int i = 0; i < s.size() - 1; ++i) {
            if (s[i] == '+' && s[i + 1] == '+') {
                s[i] = s[i + 1] = '-';
                ans.emplace_back(s);
                s[i] = s[i + 1] = '+';
            }
        }
        return ans;
    }
};
```

#### Go

```go
func generatePossibleNextMoves(currentState string) (ans []string) {
	s := []byte(currentState)
	for i := 0; i < len(s)-1; i++ {
		if s[i] == '+' && s[i+1] == '+' {
			s[i], s[i+1] = '-', '-'
			ans = append(ans, string(s))
			s[i], s[i+1] = '+', '+'
		}
	}
	return
}
```

#### TypeScript

```ts
function generatePossibleNextMoves(currentState: string): string[] {
    const s = currentState.split('');
    const ans: string[] = [];
    for (let i = 0; i < s.length - 1; ++i) {
        if (s[i] === '+' && s[i + 1] === '+') {
            s[i] = s[i + 1] = '-';
            ans.push(s.join(''));
            s[i] = s[i + 1] = '+';
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
