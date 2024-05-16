---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3110.Score%20of%20a%20String/README_EN.md
rating: 1152
source: Biweekly Contest 128 Q1
tags:
    - String
---

# [3110. Score of a String](https://leetcode.com/problems/score-of-a-string)

[中文文档](/solution/3100-3199/3110.Score%20of%20a%20String/README.md)

## Description

<p>You are given a string <code>s</code>. The <strong>score</strong> of a string is defined as the sum of the absolute difference between the <strong>ASCII</strong> values of adjacent characters.</p>

<p>Return the <strong>score</strong> of<em> </em><code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;hello&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>The <strong>ASCII</strong> values of the characters in <code>s</code> are: <code>&#39;h&#39; = 104</code>, <code>&#39;e&#39; = 101</code>, <code>&#39;l&#39; = 108</code>, <code>&#39;o&#39; = 111</code>. So, the score of <code>s</code> would be <code>|104 - 101| + |101 - 108| + |108 - 108| + |108 - 111| = 3 + 7 + 0 + 3 = 13</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;zaz&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">50</span></p>

<p><strong>Explanation:</strong></p>

<p>The <strong>ASCII</strong> values of the characters in <code>s</code> are: <code>&#39;z&#39; = 122</code>, <code>&#39;a&#39; = 97</code>. So, the score of <code>s</code> would be <code>|122 - 97| + |97 - 122| = 25 + 25 = 50</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Simulation

We directly traverse the string $s$, calculating the sum of the absolute differences of the ASCII codes of adjacent characters.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def scoreOfString(self, s: str) -> int:
        return sum(abs(a - b) for a, b in pairwise(map(ord, s)))
```

```java
class Solution {
    public int scoreOfString(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); ++i) {
            ans += Math.abs(s.charAt(i - 1) - s.charAt(i));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int scoreOfString(string s) {
        int ans = 0;
        for (int i = 1; i < s.size(); ++i) {
            ans += abs(s[i] - s[i - 1]);
        }
        return ans;
    }
};
```

```go
func scoreOfString(s string) (ans int) {
	for i := 1; i < len(s); i++ {
		ans += abs(int(s[i-1]) - int(s[i]))
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function scoreOfString(s: string): number {
    let ans = 0;
    for (let i = 1; i < s.length; ++i) {
        ans += Math.abs(s.charCodeAt(i) - s.charCodeAt(i - 1));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
