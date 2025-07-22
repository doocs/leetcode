---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3561.Resulting%20String%20After%20Adjacent%20Removals/README_EN.md
rating: 1397
source: Weekly Contest 451 Q2
tags:
    - Stack
    - String
    - Simulation
---

<!-- problem:start -->

# [3561. Resulting String After Adjacent Removals](https://leetcode.com/problems/resulting-string-after-adjacent-removals)

[中文文档](/solution/3500-3599/3561.Resulting%20String%20After%20Adjacent%20Removals/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>You <strong>must</strong> repeatedly perform the following operation while the string <code>s</code> has <strong>at least</strong> two <strong>consecutive </strong>characters:</p>

<ul>
	<li>Remove the <strong>leftmost</strong> pair of <strong>adjacent</strong> characters in the string that are <strong>consecutive</strong> in the alphabet, in either order (e.g., <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code>, or <code>&#39;b&#39;</code> and <code>&#39;a&#39;</code>).</li>
	<li>Shift the remaining characters to the left to fill the gap.</li>
</ul>

<p>Return the resulting string after no more operations can be performed.</p>

<p><strong>Note:</strong> Consider the alphabet as circular, thus <code>&#39;a&#39;</code> and <code>&#39;z&#39;</code> are consecutive.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;c&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>&quot;ab&quot;</code> from the string, leaving <code>&quot;c&quot;</code> as the remaining string.</li>
	<li>No further operations are possible. Thus, the resulting string after all possible removals is <code>&quot;c&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;adcb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>&quot;dc&quot;</code> from the string, leaving <code>&quot;ab&quot;</code> as the remaining string.</li>
	<li>Remove <code>&quot;ab&quot;</code> from the string, leaving <code>&quot;&quot;</code> as the remaining string.</li>
	<li>No further operations are possible. Thus, the resulting string after all possible removals is <code>&quot;&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;zadb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;db&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>&quot;za&quot;</code> from the string, leaving <code>&quot;db&quot;</code> as the remaining string.</li>
	<li>No further operations are possible. Thus, the resulting string after all possible removals is <code>&quot;db&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Stack

We can use a stack to simulate the process of removing adjacent characters. Iterate through each character in the string. If the character at the top of the stack and the current character are consecutive (i.e., their ASCII values differ by 1 or 25), pop the top character from the stack; otherwise, push the current character onto the stack. Finally, the characters remaining in the stack are those that can no longer be removed. Join the characters in the stack into a string and return it.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def resultingString(self, s: str) -> str:
        stk = []
        for c in s:
            if stk and abs(ord(c) - ord(stk[-1])) in (1, 25):
                stk.pop()
            else:
                stk.append(c)
        return "".join(stk)
```

#### Java

```java
class Solution {
    public String resultingString(String s) {
        StringBuilder stk = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (stk.length() > 0 && isContiguous(stk.charAt(stk.length() - 1), c)) {
                stk.deleteCharAt(stk.length() - 1);
            } else {
                stk.append(c);
            }
        }
        return stk.toString();
    }

    private boolean isContiguous(char a, char b) {
        int t = Math.abs(a - b);
        return t == 1 || t == 25;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string resultingString(string s) {
        string stk;
        for (char c : s) {
            if (stk.size() && (abs(stk.back() - c) == 1 || abs(stk.back() - c) == 25)) {
                stk.pop_back();
            } else {
                stk.push_back(c);
            }
        }
        return stk;
    }
};
```

#### Go

```go
func resultingString(s string) string {
	isContiguous := func(a, b rune) bool {
		x := abs(int(a - b))
		return x == 1 || x == 25
	}
	stk := []rune{}
	for _, c := range s {
		if len(stk) > 0 && isContiguous(stk[len(stk)-1], c) {
			stk = stk[:len(stk)-1]
		} else {
			stk = append(stk, c)
		}
	}
	return string(stk)
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
function resultingString(s: string): string {
    const stk: string[] = [];
    const isContiguous = (a: string, b: string): boolean => {
        const x = Math.abs(a.charCodeAt(0) - b.charCodeAt(0));
        return x === 1 || x === 25;
    };
    for (const c of s) {
        if (stk.length && isContiguous(stk.at(-1)!, c)) {
            stk.pop();
        } else {
            stk.push(c);
        }
    }
    return stk.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
