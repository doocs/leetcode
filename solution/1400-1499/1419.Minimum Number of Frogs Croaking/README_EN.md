---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1419.Minimum%20Number%20of%20Frogs%20Croaking/README_EN.md
rating: 1689
source: Weekly Contest 185 Q3
tags:
    - String
    - Counting
---

<!-- problem:start -->

# [1419. Minimum Number of Frogs Croaking](https://leetcode.com/problems/minimum-number-of-frogs-croaking)

[中文文档](/solution/1400-1499/1419.Minimum%20Number%20of%20Frogs%20Croaking/README.md)

## Description

<!-- description:start -->

<p>You are given the string <code>croakOfFrogs</code>, which represents a combination of the string <code>&quot;croak&quot;</code> from different frogs, that is, multiple frogs can croak at the same time, so multiple <code>&quot;croak&quot;</code> are mixed.</p>

<p><em>Return the minimum number of </em>different<em> frogs to finish all the croaks in the given string.</em></p>

<p>A valid <code>&quot;croak&quot;</code> means a frog is printing five letters <code>&#39;c&#39;</code>, <code>&#39;r&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;a&#39;</code>, and <code>&#39;k&#39;</code> <strong>sequentially</strong>. The frogs have to print all five letters to finish a croak. If the given string is not a combination of a valid <code>&quot;croak&quot;</code> return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> croakOfFrogs = &quot;croakcroak&quot;
<strong>Output:</strong> 1 
<strong>Explanation:</strong> One frog yelling &quot;croak<strong>&quot;</strong> twice.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> croakOfFrogs = &quot;crcoakroak&quot;
<strong>Output:</strong> 2 
<strong>Explanation:</strong> The minimum number of frogs is two. 
The first frog could yell &quot;<strong>cr</strong>c<strong>oak</strong>roak&quot;.
The second frog could yell later &quot;cr<strong>c</strong>oak<strong>roak</strong>&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> croakOfFrogs = &quot;croakcrook&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> The given string is an invalid combination of &quot;croak<strong>&quot;</strong> from different frogs.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= croakOfFrogs.length &lt;= 10<sup>5</sup></code></li>
	<li><code>croakOfFrogs</code> is either <code>&#39;c&#39;</code>, <code>&#39;r&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;a&#39;</code>, or <code>&#39;k&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Simulation

We note that if the string `croakOfFrogs` is composed of several valid `"croak"` characters mixed together, its length must be a multiple of $5$. Therefore, if the length of the string is not a multiple of $5$, we can directly return $-1$.

Next, we map the letters `'c'`, `'r'`, `'o'`, `'a'`, `'k'` to indices $0$ to $4$, respectively, and use an array $cnt$ of length $5$ to record the number of occurrences of each letter in the string `croakOfFrogs`, where $cnt[i]$ represents the number of occurrences of the letter at index $i$. Additionally, we define an integer variable $x$ to represent the number of frogs that have not completed their croak, and the minimum number of frogs needed $ans$ is the maximum value of $x$.

We traverse each letter $c$ in the string `croakOfFrogs`, find the index $i$ corresponding to $c$, and then increment $cnt[i]$ by $1$. Next, depending on the value of $i$, we perform the following operations:

-   If $i=0$, then a new frog starts croaking, so we increment $x$ by $1$, and then update $ans = \max(ans, x)$;
-   Otherwise, if $cnt[i-1]=0$, it means that there is no frog that can make the sound $c$, and the croak cannot be completed, so we return $-1$. Otherwise, we decrement $cnt[i-1]$ by $1$. If $i=4$, it means that a frog has completed a croak, so we decrement $x$ by $1$.

After traversing, if $x=0$, it means that all frogs have completed their croaks, and we return $ans$. Otherwise, we return $-1$.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Where $n$ is the length of the string `croakOfFrogs`, and $C$ is the size of the character set, in this problem $C=26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minNumberOfFrogs(self, croakOfFrogs: str) -> int:
        if len(croakOfFrogs) % 5 != 0:
            return -1
        idx = {c: i for i, c in enumerate('croak')}
        cnt = [0] * 5
        ans = x = 0
        for i in map(idx.get, croakOfFrogs):
            cnt[i] += 1
            if i == 0:
                x += 1
                ans = max(ans, x)
            else:
                if cnt[i - 1] == 0:
                    return -1
                cnt[i - 1] -= 1
                if i == 4:
                    x -= 1
        return -1 if x else ans
```

#### Java

```java
class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int n = croakOfFrogs.length();
        if (n % 5 != 0) {
            return -1;
        }
        int[] idx = new int[26];
        String s = "croak";
        for (int i = 0; i < 5; ++i) {
            idx[s.charAt(i) - 'a'] = i;
        }
        int[] cnt = new int[5];
        int ans = 0, x = 0;
        for (int k = 0; k < n; ++k) {
            int i = idx[croakOfFrogs.charAt(k) - 'a'];
            ++cnt[i];
            if (i == 0) {
                ans = Math.max(ans, ++x);
            } else {
                if (--cnt[i - 1] < 0) {
                    return -1;
                }
                if (i == 4) {
                    --x;
                }
            }
        }
        return x > 0 ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minNumberOfFrogs(string croakOfFrogs) {
        int n = croakOfFrogs.size();
        if (n % 5 != 0) {
            return -1;
        }
        int idx[26]{};
        string s = "croak";
        for (int i = 0; i < 5; ++i) {
            idx[s[i] - 'a'] = i;
        }
        int cnt[5]{};
        int ans = 0, x = 0;
        for (char& c : croakOfFrogs) {
            int i = idx[c - 'a'];
            ++cnt[i];
            if (i == 0) {
                ans = max(ans, ++x);
            } else {
                if (--cnt[i - 1] < 0) {
                    return -1;
                }
                if (i == 4) {
                    --x;
                }
            }
        }
        return x > 0 ? -1 : ans;
    }
};
```

#### Go

```go
func minNumberOfFrogs(croakOfFrogs string) int {
	n := len(croakOfFrogs)
	if n%5 != 0 {
		return -1
	}
	idx := [26]int{}
	for i, c := range "croak" {
		idx[c-'a'] = i
	}
	cnt := [5]int{}
	ans, x := 0, 0
	for _, c := range croakOfFrogs {
		i := idx[c-'a']
		cnt[i]++
		if i == 0 {
			x++
			ans = max(ans, x)
		} else {
			cnt[i-1]--
			if cnt[i-1] < 0 {
				return -1
			}
			if i == 4 {
				x--
			}
		}
	}
	if x > 0 {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minNumberOfFrogs(croakOfFrogs: string): number {
    const n = croakOfFrogs.length;
    if (n % 5 !== 0) {
        return -1;
    }
    const idx = (c: string): number => 'croak'.indexOf(c);
    const cnt: number[] = [0, 0, 0, 0, 0];
    let ans = 0;
    let x = 0;
    for (const c of croakOfFrogs) {
        const i = idx(c);
        ++cnt[i];
        if (i === 0) {
            ans = Math.max(ans, ++x);
        } else {
            if (--cnt[i - 1] < 0) {
                return -1;
            }
            if (i === 4) {
                --x;
            }
        }
    }
    return x > 0 ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
