---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3931.Check%20Adjacent%20Digit%20Differences/README_EN.md
rating: 1165
source: Weekly Contest 502 Q1
---

<!-- problem:start -->

# [3931. Check Adjacent Digit Differences](https://leetcode.com/problems/check-adjacent-digit-differences)

[中文文档](/solution/3900-3999/3931.Check%20Adjacent%20Digit%20Differences/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of digits.</p>

<p>Return <code>true</code> if the <strong>absolute difference</strong> between every pair of <strong>adjacent</strong> digits is at most 2, otherwise return <code>false</code>.</p>

<p>The absolute difference between <code>a</code> and <code>b</code> is defined as <code>abs(a - b)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;132&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The absolute difference between digits at <code>s[0]</code> and <code>s[1]</code> is <code>abs(1 - 3) = 2</code>.</li>
	<li>The absolute difference between digits at <code>s[1]</code> and <code>s[2]</code> is <code>abs(3 - 2) = 1</code>.</li>
	<li>Since both differences are at most 2, the answer is true.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;129&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The absolute difference between digits at <code>s[0]</code> and <code>s[1]</code> is <code>abs(1 - 2) = 1</code>.</li>
	<li>The absolute difference between digits at <code>s[1]</code> and <code>s[2]</code> is <code>abs(2 - 9) = 7</code>, which is greater than 2.</li>
	<li>Therefore, the answer is false.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can simulate the process described in the problem: iterate through each pair of adjacent digits in the string and compute their absolute difference. If any pair has an absolute difference greater than 2, return $\text{false}$. If no such pair is found after the traversal, return $\text{true}$.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isAdjacentDiffAtMostTwo(self, s: str) -> bool:
        return all(abs(x - y) <= 2 for x, y in pairwise(map(int, list(s))))
```

#### Java

```java
class Solution {
    public boolean isAdjacentDiffAtMostTwo(String s) {
        for (int i = 1; i < s.length(); ++i) {
            if (Math.abs(s.charAt(i - 1) - s.charAt(i)) > 2) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isAdjacentDiffAtMostTwo(string s) {
        for (int i = 1; i < s.size(); ++i) {
            if (abs(s[i - 1] - s[i]) > 2) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isAdjacentDiffAtMostTwo(s string) bool {
	for i := 1; i < len(s); i++ {
		if abs(int(s[i-1])-int(s[i])) > 2 {
			return false
		}
	}
	return true
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function isAdjacentDiffAtMostTwo(s: string): boolean {
    for (let i = 1; i < s.length; i++) {
        if (Math.abs(Number(s[i]) - Number(s[i - 1])) > 2) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
