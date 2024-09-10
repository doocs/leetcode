---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1209.Remove%20All%20Adjacent%20Duplicates%20in%20String%20II/README_EN.md
rating: 1541
source: Weekly Contest 156 Q3
tags:
    - Stack
    - String
---

<!-- problem:start -->

# [1209. Remove All Adjacent Duplicates in String II](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii)

[中文文档](/solution/1200-1299/1209.Remove%20All%20Adjacent%20Duplicates%20in%20String%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>k</code>, a <code>k</code> <strong>duplicate removal</strong> consists of choosing <code>k</code> adjacent and equal letters from <code>s</code> and removing them, causing the left and the right side of the deleted substring to concatenate together.</p>

<p>We repeatedly make <code>k</code> <strong>duplicate removals</strong> on <code>s</code> until we no longer can.</p>

<p>Return <em>the final string after all such duplicate removals have been made</em>. It is guaranteed that the answer is <strong>unique</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, k = 2
<strong>Output:</strong> &quot;abcd&quot;
<strong>Explanation: </strong>There&#39;s nothing to delete.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;deeedbbcccbdaa&quot;, k = 3
<strong>Output:</strong> &quot;aa&quot;
<strong>Explanation: 
</strong>First delete &quot;eee&quot; and &quot;ccc&quot;, get &quot;ddbbbdaa&quot;
Then delete &quot;bbb&quot;, get &quot;dddaa&quot;
Finally delete &quot;ddd&quot;, get &quot;aa&quot;</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;pbbcggttciiippooaais&quot;, k = 2
<strong>Output:</strong> &quot;ps&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= k &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> only contains lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Stack

We can traverse the string $s$, maintaining a stack that stores the characters and their occurrence counts. When traversing to character $c$, if the character at the top of the stack is the same as $c$, we increment the count of the top element by one; otherwise, we push the character $c$ and count $1$ into the stack. When the count of the top element equals $k$, we pop the top element from the stack.

After traversing the string $s$, the elements remaining in the stack form the final result. We can pop the elements from the stack one by one, concatenate them into a string, and that's our answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeDuplicates(self, s: str, k: int) -> str:
        stk = []
        for c in s:
            if stk and stk[-1][0] == c:
                stk[-1][1] = (stk[-1][1] + 1) % k
                if stk[-1][1] == 0:
                    stk.pop()
            else:
                stk.append([c, 1])
        ans = [c * v for c, v in stk]
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String removeDuplicates(String s, int k) {
        Deque<int[]> stk = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            if (!stk.isEmpty() && stk.peek()[0] == j) {
                stk.peek()[1] = (stk.peek()[1] + 1) % k;
                if (stk.peek()[1] == 0) {
                    stk.pop();
                }
            } else {
                stk.push(new int[] {j, 1});
            }
        }
        StringBuilder ans = new StringBuilder();
        for (var e : stk) {
            char c = (char) (e[0] + 'a');
            for (int i = 0; i < e[1]; ++i) {
                ans.append(c);
            }
        }
        ans.reverse();
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string removeDuplicates(string s, int k) {
        vector<pair<char, int>> stk;
        for (char& c : s) {
            if (stk.size() && stk.back().first == c) {
                stk.back().second = (stk.back().second + 1) % k;
                if (stk.back().second == 0) {
                    stk.pop_back();
                }
            } else {
                stk.push_back({c, 1});
            }
        }
        string ans;
        for (auto [c, v] : stk) {
            ans += string(v, c);
        }
        return ans;
    }
};
```

#### Go

```go
func removeDuplicates(s string, k int) string {
	stk := []pair{}
	for _, c := range s {
		if len(stk) > 0 && stk[len(stk)-1].c == c {
			stk[len(stk)-1].v = (stk[len(stk)-1].v + 1) % k
			if stk[len(stk)-1].v == 0 {
				stk = stk[:len(stk)-1]
			}
		} else {
			stk = append(stk, pair{c, 1})
		}
	}
	ans := []rune{}
	for _, e := range stk {
		for i := 0; i < e.v; i++ {
			ans = append(ans, e.c)
		}
	}
	return string(ans)
}

type pair struct {
	c rune
	v int
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
