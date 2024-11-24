---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3361.Shift%20Distance%20Between%20Two%20Strings/README_EN.md
---

<!-- problem:start -->

# [3361. Shift Distance Between Two Strings](https://leetcode.com/problems/shift-distance-between-two-strings)

[中文文档](/solution/3300-3399/3361.Shift%20Distance%20Between%20Two%20Strings/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s</code> and <code>t</code> of the same length, and two integer arrays <code>nextCost</code> and <code>previousCost</code>.</p>

<p>In one operation, you can pick any index <code>i</code> of <code>s</code>, and perform <strong>either one</strong> of the following actions:</p>

<ul>
	<li>Shift <code>s[i]</code> to the next letter in the alphabet. If <code>s[i] == &#39;z&#39;</code>, you should replace it with <code>&#39;a&#39;</code>. This operation costs <code>nextCost[j]</code> where <code>j</code> is the index of <code>s[i]</code> in the alphabet.</li>
	<li>Shift <code>s[i]</code> to the previous letter in the alphabet. If <code>s[i] == &#39;a&#39;</code>, you should replace it with <code>&#39;z&#39;</code>. This operation costs <code>previousCost[j]</code> where <code>j</code> is the index of <code>s[i]</code> in the alphabet.</li>
</ul>

<p>The <strong>shift distance</strong> is the <strong>minimum</strong> total cost of operations required to transform <code>s</code> into <code>t</code>.</p>

<p>Return the <strong>shift distance</strong> from <code>s</code> to <code>t</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abab&quot;, t = &quot;baba&quot;, nextCost = [100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0], previousCost = [1,100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>We choose index <code>i = 0</code> and shift <code>s[0]</code> 25 times to the previous character for a total cost of 1.</li>
	<li>We choose index <code>i = 1</code> and shift <code>s[1]</code> 25 times to the next character for a total cost of 0.</li>
	<li>We choose index <code>i = 2</code> and shift <code>s[2]</code> 25 times to the previous character for a total cost of 1.</li>
	<li>We choose index <code>i = 3</code> and shift <code>s[3]</code> 25 times to the next character for a total cost of 0.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;leet&quot;, t = &quot;code&quot;, nextCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1], previousCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">31</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>We choose index <code>i = 0</code> and shift <code>s[0]</code> 9 times to the previous character for a total cost of 9.</li>
	<li>We choose index <code>i = 1</code> and shift <code>s[1]</code> 10 times to the next character for a total cost of 10.</li>
	<li>We choose index <code>i = 2</code> and shift <code>s[2]</code> 1 time to the previous character for a total cost of 1.</li>
	<li>We choose index <code>i = 3</code> and shift <code>s[3]</code> 11 times to the next character for a total cost of 11.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length == t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>t</code> consist only of lowercase English letters.</li>
	<li><code>nextCost.length == previousCost.length == 26</code></li>
	<li><code>0 &lt;= nextCost[i], previousCost[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shiftDistance(
        self, s: str, t: str, nextCost: List[int], previousCost: List[int]
    ) -> int:
        m = 26
        s1 = [0] * (m << 1 | 1)
        s2 = [0] * (m << 1 | 1)
        for i in range(m << 1):
            s1[i + 1] = s1[i] + nextCost[i % m]
            s2[i + 1] = s2[i] + previousCost[(i + 1) % m]
        ans = 0
        for a, b in zip(s, t):
            x, y = ord(a) - ord("a"), ord(b) - ord("a")
            c1 = s1[y + m if y < x else y] - s1[x]
            c2 = s2[x + m if x < y else x] - s2[y]
            ans += min(c1, c2)
        return ans
```

#### Java

```java
class Solution {
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        int m = 26;
        long[] s1 = new long[(m << 1) + 1];
        long[] s2 = new long[(m << 1) + 1];
        for (int i = 0; i < (m << 1); i++) {
            s1[i + 1] = s1[i] + nextCost[i % m];
            s2[i + 1] = s2[i] + previousCost[(i + 1) % m];
        }
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i) - 'a';
            int y = t.charAt(i) - 'a';
            long c1 = s1[y + (y < x ? m : 0)] - s1[x];
            long c2 = s2[x + (x < y ? m : 0)] - s2[y];
            ans += Math.min(c1, c2);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long shiftDistance(string s, string t, vector<int>& nextCost, vector<int>& previousCost) {
        int m = 26;
        vector<long long> s1((m << 1) + 1);
        vector<long long> s2((m << 1) + 1);
        for (int i = 0; i < (m << 1); ++i) {
            s1[i + 1] = s1[i] + nextCost[i % m];
            s2[i + 1] = s2[i] + previousCost[(i + 1) % m];
        }

        long long ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            int x = s[i] - 'a';
            int y = t[i] - 'a';
            long long c1 = s1[y + (y < x ? m : 0)] - s1[x];
            long long c2 = s2[x + (x < y ? m : 0)] - s2[y];
            ans += min(c1, c2);
        }

        return ans;
    }
};
```

#### Go

```go
func shiftDistance(s string, t string, nextCost []int, previousCost []int) (ans int64) {
	m := 26
	s1 := make([]int64, (m<<1)+1)
	s2 := make([]int64, (m<<1)+1)
	for i := 0; i < (m << 1); i++ {
		s1[i+1] = s1[i] + int64(nextCost[i%m])
		s2[i+1] = s2[i] + int64(previousCost[(i+1)%m])
	}
	for i := 0; i < len(s); i++ {
		x := int(s[i] - 'a')
		y := int(t[i] - 'a')
		z := y
		if y < x {
			z += m
		}
		c1 := s1[z] - s1[x]
		z = x
		if x < y {
			z += m
		}
		c2 := s2[z] - s2[y]
		ans += min(c1, c2)
	}
	return
}
```

#### TypeScript

```ts
function shiftDistance(s: string, t: string, nextCost: number[], previousCost: number[]): number {
    const m = 26;
    const s1: number[] = Array((m << 1) + 1).fill(0);
    const s2: number[] = Array((m << 1) + 1).fill(0);
    for (let i = 0; i < m << 1; i++) {
        s1[i + 1] = s1[i] + nextCost[i % m];
        s2[i + 1] = s2[i] + previousCost[(i + 1) % m];
    }
    let ans = 0;
    const a = 'a'.charCodeAt(0);
    for (let i = 0; i < s.length; i++) {
        const x = s.charCodeAt(i) - a;
        const y = t.charCodeAt(i) - a;
        const c1 = s1[y + (y < x ? m : 0)] - s1[x];
        const c2 = s2[x + (x < y ? m : 0)] - s2[y];
        ans += Math.min(c1, c2);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
