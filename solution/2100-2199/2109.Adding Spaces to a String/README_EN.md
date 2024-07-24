---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2109.Adding%20Spaces%20to%20a%20String/README_EN.md
rating: 1315
source: Weekly Contest 272 Q2
tags:
    - Array
    - Two Pointers
    - String
    - Simulation
---

<!-- problem:start -->

# [2109. Adding Spaces to a String](https://leetcode.com/problems/adding-spaces-to-a-string)

[中文文档](/solution/2100-2199/2109.Adding%20Spaces%20to%20a%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> string <code>s</code> and a <strong>0-indexed</strong> integer array <code>spaces</code> that describes the indices in the original string where spaces will be added. Each space should be inserted <strong>before</strong> the character at the given index.</p>

<ul>
	<li>For example, given <code>s = &quot;EnjoyYourCoffee&quot;</code> and <code>spaces = [5, 9]</code>, we place spaces before <code>&#39;Y&#39;</code> and <code>&#39;C&#39;</code>, which are at indices <code>5</code> and <code>9</code> respectively. Thus, we obtain <code>&quot;Enjoy <strong><u>Y</u></strong>our <u><strong>C</strong></u>offee&quot;</code>.</li>
</ul>

<p>Return<strong> </strong><em>the modified string <strong>after</strong> the spaces have been added.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;LeetcodeHelpsMeLearn&quot;, spaces = [8,13,15]
<strong>Output:</strong> &quot;Leetcode Helps Me Learn&quot;
<strong>Explanation:</strong> 
The indices 8, 13, and 15 correspond to the underlined characters in &quot;Leetcode<u><strong>H</strong></u>elps<u><strong>M</strong></u>e<u><strong>L</strong></u>earn&quot;.
We then place spaces before those characters.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;icodeinpython&quot;, spaces = [1,5,7,9]
<strong>Output:</strong> &quot;i code in py thon&quot;
<strong>Explanation:</strong>
The indices 1, 5, 7, and 9 correspond to the underlined characters in &quot;i<u><strong>c</strong></u>ode<u><strong>i</strong></u>n<u><strong>p</strong></u>y<u><strong>t</strong></u>hon&quot;.
We then place spaces before those characters.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;spacing&quot;, spaces = [0,1,2,3,4,5,6]
<strong>Output:</strong> &quot; s p a c i n g&quot;
<strong>Explanation:</strong>
We are also able to place spaces before the first character of the string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase and uppercase English letters.</li>
	<li><code>1 &lt;= spaces.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= spaces[i] &lt;= s.length - 1</code></li>
	<li>All the values of <code>spaces</code> are <strong>strictly increasing</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We can use two pointers $i$ and $j$ to point to the beginning of the string $s$ and the array $\textit{spaces}$, respectively. Then, we iterate through the string $s$ from the beginning to the end. When $i$ equals $\textit{spaces}[j]$, we add a space to the result string, and then increment $j$ by $1$. Next, we add $s[i]$ to the result string, and then increment $i$ by $1$. We continue this process until we have iterated through the entire string $s$.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$, where $n$ and $m$ are the lengths of the string $s$ and the array $spaces$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def addSpaces(self, s: str, spaces: List[int]) -> str:
        ans = []
        j = 0
        for i, c in enumerate(s):
            if j < len(spaces) and i == spaces[j]:
                ans.append(' ')
                j += 1
            ans.append(c)
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String addSpaces(String s, int[] spaces) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (j < spaces.length && i == spaces[j]) {
                ans.append(' ');
                ++j;
            }
            ans.append(s.charAt(i));
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string addSpaces(string s, vector<int>& spaces) {
        string ans = "";
        for (int i = 0, j = 0; i < s.size(); ++i) {
            if (j < spaces.size() && i == spaces[j]) {
                ans += ' ';
                ++j;
            }
            ans += s[i];
        }
        return ans;
    }
};
```

#### Go

```go
func addSpaces(s string, spaces []int) string {
	var ans []byte
	for i, j := 0, 0; i < len(s); i++ {
		if j < len(spaces) && i == spaces[j] {
			ans = append(ans, ' ')
			j++
		}
		ans = append(ans, s[i])
	}
	return string(ans)
}
```

#### TypeScript

```ts
function addSpaces(s: string, spaces: number[]): string {
    const ans: string[] = [];
    for (let i = 0, j = 0; i < s.length; i++) {
        if (i === spaces[j]) {
            ans.push(' ');
            j++;
        }
        ans.push(s[i]);
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
