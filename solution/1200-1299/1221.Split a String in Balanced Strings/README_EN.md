# [1221. Split a String in Balanced Strings](https://leetcode.com/problems/split-a-string-in-balanced-strings)

[中文文档](/solution/1200-1299/1221.Split%20a%20String%20in%20Balanced%20Strings/README.md)

<!-- tags:Greedy,String,Counting -->

<!-- difficulty:Easy -->

## Description

<p><strong>Balanced</strong> strings are those that have an equal quantity of <code>&#39;L&#39;</code> and <code>&#39;R&#39;</code> characters.</p>

<p>Given a <strong>balanced</strong> string <code>s</code>, split it into some number of substrings such that:</p>

<ul>
	<li>Each substring is balanced.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of balanced strings you can obtain.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;RLRRLLRLRL&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> s can be split into &quot;RL&quot;, &quot;RRLL&quot;, &quot;RL&quot;, &quot;RL&quot;, each substring contains same number of &#39;L&#39; and &#39;R&#39;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;RLRRRLLRLL&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> s can be split into &quot;RL&quot;, &quot;RRRLLRLL&quot;, each substring contains same number of &#39;L&#39; and &#39;R&#39;.
Note that s cannot be split into &quot;RL&quot;, &quot;RR&quot;, &quot;RL&quot;, &quot;LR&quot;, &quot;LL&quot;, because the 2<sup>nd</sup> and 5<sup>th</sup> substrings are not balanced.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;LLLLRRRR&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> s can be split into &quot;LLLLRRRR&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;L&#39;</code> or <code>&#39;R&#39;</code>.</li>
	<li><code>s</code> is a <strong>balanced</strong> string.</li>
</ul>

## Solutions

### Solution 1: Greedy

We use a variable $l$ to maintain the current balance of the string, i.e., the value of $l$ is the number of 'L's minus the number of 'R's in the current string. When the value of $l$ is 0, we have found a balanced string.

We traverse the string $s$. When we traverse to the $i$-th character, if $s[i] = L$, then the value of $l$ is increased by 1, otherwise, the value of $l$ is decreased by 1. When the value of $l$ is 0, we increase the answer by 1.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def balancedStringSplit(self, s: str) -> int:
        ans = l = 0
        for c in s:
            if c == 'L':
                l += 1
            else:
                l -= 1
            if l == 0:
                ans += 1
        return ans
```

```java
class Solution {
    public int balancedStringSplit(String s) {
        int ans = 0, l = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                ++l;
            } else {
                --l;
            }
            if (l == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int balancedStringSplit(string s) {
        int ans = 0, l = 0;
        for (char c : s) {
            if (c == 'L')
                ++l;
            else
                --l;
            if (l == 0) ++ans;
        }
        return ans;
    }
};
```

```go
func balancedStringSplit(s string) int {
	ans, l := 0, 0
	for _, c := range s {
		if c == 'L' {
			l++
		} else {
			l--
		}
		if l == 0 {
			ans++
		}
	}
	return ans
}
```

```js
/**
 * @param {string} s
 * @return {number}
 */
var balancedStringSplit = function (s) {
    let ans = 0;
    let l = 0;
    for (let c of s) {
        if (c == 'L') {
            ++l;
        } else {
            --l;
        }
        if (l == 0) {
            ++ans;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- end -->
