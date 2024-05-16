---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2262.Total%20Appeal%20of%20A%20String/README_EN.md
rating: 2033
source: Weekly Contest 291 Q4
tags:
    - Hash Table
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [2262. Total Appeal of A String](https://leetcode.com/problems/total-appeal-of-a-string)

[中文文档](/solution/2200-2299/2262.Total%20Appeal%20of%20A%20String/README.md)

## Description

<!-- description:start -->

<p>The <b>appeal</b> of a string is the number of <strong>distinct</strong> characters found in the string.</p>

<ul>
	<li>For example, the appeal of <code>&quot;abbca&quot;</code> is <code>3</code> because it has <code>3</code> distinct characters: <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</li>
</ul>

<p>Given a string <code>s</code>, return <em>the <strong>total appeal of all of its <strong>substrings</strong>.</strong></em></p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbca&quot;
<strong>Output:</strong> 28
<strong>Explanation:</strong> The following are the substrings of &quot;abbca&quot;:
- Substrings of length 1: &quot;a&quot;, &quot;b&quot;, &quot;b&quot;, &quot;c&quot;, &quot;a&quot; have an appeal of 1, 1, 1, 1, and 1 respectively. The sum is 5.
- Substrings of length 2: &quot;ab&quot;, &quot;bb&quot;, &quot;bc&quot;, &quot;ca&quot; have an appeal of 2, 1, 2, and 2 respectively. The sum is 7.
- Substrings of length 3: &quot;abb&quot;, &quot;bbc&quot;, &quot;bca&quot; have an appeal of 2, 2, and 3 respectively. The sum is 7.
- Substrings of length 4: &quot;abbc&quot;, &quot;bbca&quot; have an appeal of 3 and 3 respectively. The sum is 6.
- Substrings of length 5: &quot;abbca&quot; has an appeal of 3. The sum is 3.
The total sum is 5 + 7 + 7 + 6 + 3 = 28.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;code&quot;
<strong>Output:</strong> 20
<strong>Explanation:</strong> The following are the substrings of &quot;code&quot;:
- Substrings of length 1: &quot;c&quot;, &quot;o&quot;, &quot;d&quot;, &quot;e&quot; have an appeal of 1, 1, 1, and 1 respectively. The sum is 4.
- Substrings of length 2: &quot;co&quot;, &quot;od&quot;, &quot;de&quot; have an appeal of 2, 2, and 2 respectively. The sum is 6.
- Substrings of length 3: &quot;cod&quot;, &quot;ode&quot; have an appeal of 3 and 3 respectively. The sum is 6.
- Substrings of length 4: &quot;code&quot; has an appeal of 4. The sum is 4.
The total sum is 4 + 6 + 6 + 4 = 20.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate all the substrings that end with each character $s[i]$ and calculate their gravitational value sum $t$. Finally, we add up all the $t$ to get the total gravitational value sum.

When we reach $s[i]$, which is added to the end of the substring that ends with $s[i-1]$, we consider the change of the gravitational value sum $t$:

If $s[i]$ has not appeared before, then the gravitational value of all substrings that end with $s[i-1]$ will increase by $1$, and there are a total of $i$ such substrings. Therefore, $t$ increases by $i$, plus the gravitational value of $s[i]$ itself, which is $1$. Therefore, $t$ increases by a total of $i+1$.

If $s[i]$ has appeared before, let the last appearance position be $j$. Then we add $s[i]$ to the end of the substrings $s[0..i-1]$, $[1..i-1]$, $s[2..i-1]$, $\cdots$, $s[j..i-1]$. The gravitational value of these substrings will not change because $s[i]$ has already appeared in these substrings. The gravitational value of the substrings $s[j+1..i-1]$, $s[j+2..i-1]$, $\cdots$, $s[i-1]$ will increase by $1$, and there are a total of $i-j-1$ such substrings. Therefore, $t$ increases by $i-j-1$, plus the gravitational value of $s[i]$ itself, which is $1$. Therefore, $t$ increases by a total of $i-j$.
Therefore, we can use an array $pos$ to record the last appearance position of each character. Initially, all positions are set to $-1$.

Next, we traverse the string, and each time we update the gravitational value sum $t$ of the substring that ends with the current character to $t = t + i - pos[c]$, where $c$ is the current character. We add $t$ to the answer. Then we update $pos[c]$ to the current position $i$. We continue to traverse until the end of the string.

The time complexity is $O(n)$, and the space complexity is $O(|\Sigma|)$, where $n$ is the length of the string $s$, and $|\Sigma|$ is the size of the character set. In this problem, $|\Sigma| = 26$.

<!-- tabs:start -->

```python
class Solution:
    def appealSum(self, s: str) -> int:
        ans = t = 0
        pos = [-1] * 26
        for i, c in enumerate(s):
            c = ord(c) - ord('a')
            t += i - pos[c]
            ans += t
            pos[c] = i
        return ans
```

```java
class Solution {
    public long appealSum(String s) {
        long ans = 0;
        long t = 0;
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i) - 'a';
            t += i - pos[c];
            ans += t;
            pos[c] = i;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long appealSum(string s) {
        long long ans = 0, t = 0;
        vector<int> pos(26, -1);
        for (int i = 0; i < s.size(); ++i) {
            int c = s[i] - 'a';
            t += i - pos[c];
            ans += t;
            pos[c] = i;
        }
        return ans;
    }
};
```

```go
func appealSum(s string) int64 {
	var ans, t int64
	pos := make([]int, 26)
	for i := range pos {
		pos[i] = -1
	}
	for i, c := range s {
		c -= 'a'
		t += int64(i - pos[c])
		ans += t
		pos[c] = i
	}
	return ans
}
```

```ts
function appealSum(s: string): number {
    const pos: number[] = Array(26).fill(-1);
    const n = s.length;
    let ans = 0;
    let t = 0;
    for (let i = 0; i < n; ++i) {
        const c = s.charCodeAt(i) - 97;
        t += i - pos[c];
        ans += t;
        pos[c] = i;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
