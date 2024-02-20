# [2938. Separate Black and White Balls](https://leetcode.com/problems/separate-black-and-white-balls)

[中文文档](/solution/2900-2999/2938.Separate%20Black%20and%20White%20Balls/README.md)

<!-- tags:Greedy,Two Pointers,String -->

## Description

<p>There are <code>n</code> balls on a table, each ball has a color black or white.</p>

<p>You are given a <strong>0-indexed</strong> binary string <code>s</code> of length <code>n</code>, where <code>1</code> and <code>0</code> represent black and white balls, respectively.</p>

<p>In each step, you can choose two adjacent balls and swap them.</p>

<p>Return <em>the <strong>minimum</strong> number of steps to group all the black balls to the right and all the white balls to the left</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;101&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can group all the black balls to the right in the following way:
- Swap s[0] and s[1], s = &quot;011&quot;.
Initially, 1s are not grouped together, requiring at least 1 step to group them to the right.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;100&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can group all the black balls to the right in the following way:
- Swap s[0] and s[1], s = &quot;010&quot;.
- Swap s[1] and s[2], s = &quot;001&quot;.
It can be proven that the minimum number of steps needed is 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0111&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> All the black balls are already grouped to the right.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

### Solution 1: Counting Simulation

We consider moving all the '1's to the rightmost side. We use a variable $cnt$ to record the current number of '1's that have been moved to the rightmost side, and a variable $ans$ to record the number of moves.

We traverse the string from right to left. If the current position is '1', then we increment $cnt$ by one, and add $n - i - cnt$ to $ans$, where $n$ is the length of the string. Finally, we return $ans$.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minimumSteps(self, s: str) -> int:
        n = len(s)
        ans = cnt = 0
        for i in range(n - 1, -1, -1):
            if s[i] == '1':
                cnt += 1
                ans += n - i - cnt
        return ans
```

```java
class Solution {
    public long minimumSteps(String s) {
        long ans = 0;
        int cnt = 0;
        int n = s.length();
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == '1') {
                ++cnt;
                ans += n - i - cnt;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long minimumSteps(string s) {
        long long ans = 0;
        int cnt = 0;
        int n = s.size();
        for (int i = n - 1; i >= 0; --i) {
            if (s[i] == '1') {
                ++cnt;
                ans += n - i - cnt;
            }
        }
        return ans;
    }
};
```

```go
func minimumSteps(s string) (ans int64) {
	n := len(s)
	cnt := 0
	for i := n - 1; i >= 0; i-- {
		if s[i] == '1' {
			cnt++
			ans += int64(n - i - cnt)
		}
	}
	return
}
```

```ts
function minimumSteps(s: string): number {
    const n = s.length;
    let [ans, cnt] = [0, 0];
    for (let i = n - 1; ~i; --i) {
        if (s[i] === '1') {
            ++cnt;
            ans += n - i - cnt;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
