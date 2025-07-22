---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2222.Number%20of%20Ways%20to%20Select%20Buildings/README_EN.md
rating: 1656
source: Biweekly Contest 75 Q3
tags:
    - String
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [2222. Number of Ways to Select Buildings](https://leetcode.com/problems/number-of-ways-to-select-buildings)

[中文文档](/solution/2200-2299/2222.Number%20of%20Ways%20to%20Select%20Buildings/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> binary string <code>s</code> which represents the types of buildings along a street where:</p>

<ul>
	<li><code>s[i] = &#39;0&#39;</code> denotes that the <code>i<sup>th</sup></code> building is an office and</li>
	<li><code>s[i] = &#39;1&#39;</code> denotes that the <code>i<sup>th</sup></code> building is a restaurant.</li>
</ul>

<p>As a city official, you would like to <strong>select</strong> 3 buildings for random inspection. However, to ensure variety, <strong>no two consecutive</strong> buildings out of the <strong>selected</strong> buildings can be of the same type.</p>

<ul>
	<li>For example, given <code>s = &quot;0<u><strong>0</strong></u>1<u><strong>1</strong></u>0<u><strong>1</strong></u>&quot;</code>, we cannot select the <code>1<sup>st</sup></code>, <code>3<sup>rd</sup></code>, and <code>5<sup>th</sup></code> buildings as that would form <code>&quot;0<strong><u>11</u></strong>&quot;</code> which is <strong>not</strong> allowed due to having two consecutive buildings of the same type.</li>
</ul>

<p>Return <em>the <b>number of valid ways</b> to select 3 buildings.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;001101&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
The following sets of indices selected are valid:
- [0,2,4] from &quot;<u><strong>0</strong></u>0<strong><u>1</u></strong>1<strong><u>0</u></strong>1&quot; forms &quot;010&quot;
- [0,3,4] from &quot;<u><strong>0</strong></u>01<u><strong>10</strong></u>1&quot; forms &quot;010&quot;
- [1,2,4] from &quot;0<u><strong>01</strong></u>1<u><strong>0</strong></u>1&quot; forms &quot;010&quot;
- [1,3,4] from &quot;0<u><strong>0</strong></u>1<u><strong>10</strong></u>1&quot; forms &quot;010&quot;
- [2,4,5] from &quot;00<u><strong>1</strong></u>1<u><strong>01</strong></u>&quot; forms &quot;101&quot;
- [3,4,5] from &quot;001<u><strong>101</strong></u>&quot; forms &quot;101&quot;
No other selection is valid. Thus, there are 6 total ways.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;11100&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> It can be shown that there are no valid selections.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Enumeration

According to the problem description, we need to choose $3$ buildings, and two adjacent buildings cannot be of the same type.

We can enumerate the middle building, assuming it is $x$, then the types of buildings on the left and right sides can only be $x \oplus 1$, where $\oplus$ denotes the XOR operation. Therefore, we can use two arrays $l$ and $r$ to record the number of building types on the left and right sides, respectively. Then, we enumerate the middle building and calculate the answer.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfWays(self, s: str) -> int:
        l = [0, 0]
        r = [s.count("0"), s.count("1")]
        ans = 0
        for x in map(int, s):
            r[x] -= 1
            ans += l[x ^ 1] * r[x ^ 1]
            l[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public long numberOfWays(String s) {
        int n = s.length();
        int[] l = new int[2];
        int[] r = new int[2];
        for (int i = 0; i < n; ++i) {
            r[s.charAt(i) - '0']++;
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = s.charAt(i) - '0';
            r[x]--;
            ans += 1L * l[x ^ 1] * r[x ^ 1];
            l[x]++;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numberOfWays(string s) {
        int n = s.size();
        int l[2]{};
        int r[2]{};
        r[0] = ranges::count(s, '0');
        r[1] = n - r[0];
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = s[i] - '0';
            r[x]--;
            ans += 1LL * l[x ^ 1] * r[x ^ 1];
            l[x]++;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfWays(s string) (ans int64) {
	n := len(s)
	l := [2]int{}
	r := [2]int{}
	r[0] = strings.Count(s, "0")
	r[1] = n - r[0]
	for _, c := range s {
		x := int(c - '0')
		r[x]--
		ans += int64(l[x^1] * r[x^1])
		l[x]++
	}
	return
}
```

#### TypeScript

```ts
function numberOfWays(s: string): number {
    const n = s.length;
    const l: number[] = [0, 0];
    const r: number[] = [s.split('').filter(c => c === '0').length, 0];
    r[1] = n - r[0];
    let ans: number = 0;
    for (const c of s) {
        const x = c === '0' ? 0 : 1;
        r[x]--;
        ans += l[x ^ 1] * r[x ^ 1];
        l[x]++;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
