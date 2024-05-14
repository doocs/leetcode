---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1927.Sum%20Game/README_EN.md
rating: 2004
tags:
    - Greedy
    - Math
    - String
    - Game Theory
---

# [1927. Sum Game](https://leetcode.com/problems/sum-game)

[中文文档](/solution/1900-1999/1927.Sum%20Game/README.md)

## Description

<p>Alice and Bob take turns playing a game, with <strong>Alice</strong><strong>&nbsp;starting first</strong>.</p>

<p>You are given a string <code>num</code> of <strong>even length</strong> consisting of digits and <code>&#39;?&#39;</code> characters. On each turn, a player will do the following if there is still at least one <code>&#39;?&#39;</code> in <code>num</code>:</p>

<ol>
	<li>Choose an index <code>i</code> where <code>num[i] == &#39;?&#39;</code>.</li>
	<li>Replace <code>num[i]</code> with any digit between <code>&#39;0&#39;</code> and <code>&#39;9&#39;</code>.</li>
</ol>

<p>The game ends when there are no more <code>&#39;?&#39;</code> characters in <code>num</code>.</p>

<p>For Bob&nbsp;to win, the sum of the digits in the first half of <code>num</code> must be <strong>equal</strong> to the sum of the digits in the second half. For Alice&nbsp;to win, the sums must <strong>not be equal</strong>.</p>

<ul>
	<li>For example, if the game ended with <code>num = &quot;243801&quot;</code>, then Bob&nbsp;wins because <code>2+4+3 = 8+0+1</code>. If the game ended with <code>num = &quot;243803&quot;</code>, then Alice&nbsp;wins because <code>2+4+3 != 8+0+3</code>.</li>
</ul>

<p>Assuming Alice and Bob play <strong>optimally</strong>, return <code>true</code> <em>if Alice will win and </em><code>false</code> <em>if Bob will win</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;5023&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> There are no moves to be made.
The sum of the first half is equal to the sum of the second half: 5 + 0 = 2 + 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;25??&quot;
<strong>Output:</strong> true
<strong>Explanation: </strong>Alice can replace one of the &#39;?&#39;s with &#39;9&#39; and it will be impossible for Bob to make the sums equal.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;?3295???&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It can be proven that Bob will always win. One possible outcome is:
- Alice replaces the first &#39;?&#39; with &#39;9&#39;. num = &quot;93295???&quot;.
- Bob replaces one of the &#39;?&#39; in the right half with &#39;9&#39;. num = &quot;932959??&quot;.
- Alice replaces one of the &#39;?&#39; in the right half with &#39;2&#39;. num = &quot;9329592?&quot;.
- Bob replaces the last &#39;?&#39; in the right half with &#39;7&#39;. num = &quot;93295927&quot;.
Bob wins because 9 + 3 + 2 + 9 = 5 + 9 + 2 + 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num.length</code> is <strong>even</strong>.</li>
	<li><code>num</code> consists of only digits and <code>&#39;?&#39;</code>.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def sumGame(self, num: str) -> bool:
        n = len(num)
        cnt1 = num[: n // 2].count("?")
        cnt2 = num[n // 2 :].count("?")
        s1 = sum(int(x) for x in num[: n // 2] if x != "?")
        s2 = sum(int(x) for x in num[n // 2 :] if x != "?")
        return (cnt1 + cnt2) % 2 == 1 or s1 - s2 != 9 * (cnt2 - cnt1) // 2
```

```java
class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int cnt1 = 0, cnt2 = 0;
        int s1 = 0, s2 = 0;
        for (int i = 0; i < n / 2; ++i) {
            if (num.charAt(i) == '?') {
                cnt1++;
            } else {
                s1 += num.charAt(i) - '0';
            }
        }
        for (int i = n / 2; i < n; ++i) {
            if (num.charAt(i) == '?') {
                cnt2++;
            } else {
                s2 += num.charAt(i) - '0';
            }
        }
        return (cnt1 + cnt2) % 2 == 1 || s1 - s2 != 9 * (cnt2 - cnt1) / 2;
    }
}
```

```cpp
class Solution {
public:
    bool sumGame(string num) {
        int n = num.size();
        int cnt1 = 0, cnt2 = 0;
        int s1 = 0, s2 = 0;
        for (int i = 0; i < n / 2; ++i) {
            if (num[i] == '?') {
                cnt1++;
            } else {
                s1 += num[i] - '0';
            }
        }
        for (int i = n / 2; i < n; ++i) {
            if (num[i] == '?') {
                cnt2++;
            } else {
                s2 += num[i] - '0';
            }
        }
        return (cnt1 + cnt2) % 2 == 1 || (s1 - s2) != 9 * (cnt2 - cnt1) / 2;
    }
};
```

```go
func sumGame(num string) bool {
	n := len(num)
	var cnt1, cnt2, s1, s2 int
	for i := 0; i < n/2; i++ {
		if num[i] == '?' {
			cnt1++
		} else {
			s1 += int(num[i] - '0')
		}
	}
	for i := n / 2; i < n; i++ {
		if num[i] == '?' {
			cnt2++
		} else {
			s2 += int(num[i] - '0')
		}
	}
	return (cnt1+cnt2)%2 == 1 || s1-s2 != (cnt2-cnt1)*9/2
}
```

```ts
function sumGame(num: string): boolean {
    const n = num.length;
    let [cnt1, cnt2, s1, s2] = [0, 0, 0, 0];
    for (let i = 0; i < n >> 1; ++i) {
        if (num[i] === '?') {
            ++cnt1;
        } else {
            s1 += num[i].charCodeAt(0) - '0'.charCodeAt(0);
        }
    }
    for (let i = n >> 1; i < n; ++i) {
        if (num[i] === '?') {
            ++cnt2;
        } else {
            s2 += num[i].charCodeAt(0) - '0'.charCodeAt(0);
        }
    }
    return (cnt1 + cnt2) % 2 === 1 || 2 * (s1 - s2) !== 9 * (cnt2 - cnt1);
}
```

<!-- tabs:end -->

<!-- end -->
