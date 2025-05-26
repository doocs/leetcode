---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1138.Alphabet%20Board%20Path/README_EN.md
rating: 1410
source: Weekly Contest 147 Q2
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [1138. Alphabet Board Path](https://leetcode.com/problems/alphabet-board-path)

[中文文档](/solution/1100-1199/1138.Alphabet%20Board%20Path/README.md)

## Description

<!-- description:start -->

<p>On an alphabet board, we start at position <code>(0, 0)</code>, corresponding to character&nbsp;<code>board[0][0]</code>.</p>

<p>Here, <code>board = [&quot;abcde&quot;, &quot;fghij&quot;, &quot;klmno&quot;, &quot;pqrst&quot;, &quot;uvwxy&quot;, &quot;z&quot;]</code>, as shown in the diagram below.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1138.Alphabet%20Board%20Path/images/azboard.png" style="width: 250px; height: 317px;" /></p>

<p>We may make the following moves:</p>

<ul>

    <li><code>&#39;U&#39;</code> moves our position up one row, if the position exists on the board;</li>

    <li><code>&#39;D&#39;</code> moves our position down one row, if the position exists on the board;</li>

    <li><code>&#39;L&#39;</code> moves our position left one column, if the position exists on the board;</li>

    <li><code>&#39;R&#39;</code> moves our position right one column, if the position exists on the board;</li>

    <li><code>&#39;!&#39;</code>&nbsp;adds the character <code>board[r][c]</code> at our current position <code>(r, c)</code>&nbsp;to the&nbsp;answer.</li>

</ul>

<p>(Here, the only positions that exist on the board are positions with letters on them.)</p>

<p>Return a sequence of moves that makes our answer equal to <code>target</code>&nbsp;in the minimum number of moves.&nbsp; You may return any path that does so.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> target = "leet"

<strong>Output:</strong> "DDR!UURRR!!DDD!"

</pre><p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> target = "code"

<strong>Output:</strong> "RR!DDRR!UUL!R!"

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= target.length &lt;= 100</code></li>

    <li><code>target</code> consists only of English lowercase letters.</li>

</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

Starting from the origin point $(0, 0)$, simulate each step of the movement, appending the result of each step to the answer. Note that the direction of movement follows the order "left, up, right, down".

The time complexity is $O(n)$, where $n$ is the length of the string target, as each character in the string target needs to be traversed. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def alphabetBoardPath(self, target: str) -> str:
        i = j = 0
        ans = []
        for c in target:
            v = ord(c) - ord("a")
            x, y = v // 5, v % 5
            while j > y:
                j -= 1
                ans.append("L")
            while i > x:
                i -= 1
                ans.append("U")
            while j < y:
                j += 1
                ans.append("R")
            while i < x:
                i += 1
                ans.append("D")
            ans.append("!")
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String alphabetBoardPath(String target) {
        StringBuilder ans = new StringBuilder();
        int i = 0, j = 0;
        for (int k = 0; k < target.length(); ++k) {
            int v = target.charAt(k) - 'a';
            int x = v / 5, y = v % 5;
            while (j > y) {
                --j;
                ans.append('L');
            }
            while (i > x) {
                --i;
                ans.append('U');
            }
            while (j < y) {
                ++j;
                ans.append('R');
            }
            while (i < x) {
                ++i;
                ans.append('D');
            }
            ans.append("!");
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string alphabetBoardPath(string target) {
        string ans;
        int i = 0, j = 0;
        for (char& c : target) {
            int v = c - 'a';
            int x = v / 5, y = v % 5;
            while (j > y) {
                --j;
                ans += 'L';
            }
            while (i > x) {
                --i;
                ans += 'U';
            }
            while (j < y) {
                ++j;
                ans += 'R';
            }
            while (i < x) {
                ++i;
                ans += 'D';
            }
            ans += '!';
        }
        return ans;
    }
};
```

#### Go

```go
func alphabetBoardPath(target string) string {
	ans := []byte{}
	var i, j int
	for _, c := range target {
		v := int(c - 'a')
		x, y := v/5, v%5
		for j > y {
			j--
			ans = append(ans, 'L')
		}
		for i > x {
			i--
			ans = append(ans, 'U')
		}
		for j < y {
			j++
			ans = append(ans, 'R')
		}
		for i < x {
			i++
			ans = append(ans, 'D')
		}
		ans = append(ans, '!')
	}
	return string(ans)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
