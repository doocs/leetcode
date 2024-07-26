---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2531.Make%20Number%20of%20Distinct%20Characters%20Equal/README_EN.md
rating: 1775
source: Weekly Contest 327 Q3
tags:
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [2531. Make Number of Distinct Characters Equal](https://leetcode.com/problems/make-number-of-distinct-characters-equal)

[中文文档](/solution/2500-2599/2531.Make%20Number%20of%20Distinct%20Characters%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given two <strong>0-indexed</strong> strings <code>word1</code> and <code>word2</code>.</p>

<p>A <strong>move</strong> consists of choosing two indices <code>i</code> and <code>j</code> such that <code>0 &lt;= i &lt; word1.length</code> and <code>0 &lt;= j &lt; word2.length</code> and swapping <code>word1[i]</code> with <code>word2[j]</code>.</p>

<p>Return <code>true</code> <em>if it is possible to get the number of distinct characters in</em> <code>word1</code> <em>and</em> <code>word2</code> <em>to be equal with <strong>exactly one</strong> move. </em>Return <code>false</code> <em>otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;ac&quot;, word2 = &quot;b&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> Any pair of swaps would yield two distinct characters in the first string, and one in the second string.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;abcc&quot;, word2 = &quot;aab&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = &quot;abac&quot; and word2 = &quot;cab&quot;, which both have 3 distinct characters.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;abcde&quot;, word2 = &quot;fghij&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Both resulting strings will have 5 distinct characters, regardless of which indices we swap.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word1</code> and <code>word2</code> consist of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Enumeration

We first use two arrays $\textit{cnt1}$ and $\textit{cnt2}$ of length $26$ to record the frequency of each character in the strings $\textit{word1}$ and $\textit{word2}$, respectively.

Then, we count the number of distinct characters in $\textit{word1}$ and $\textit{word2}$, denoted as $x$ and $y$ respectively.

Next, we enumerate each character $c1$ in $\textit{word1}$ and each character $c2$ in $\textit{word2}$. If $c1 = c2$, we only need to check if $x$ and $y$ are equal; otherwise, we need to check if $x - (\textit{cnt1}[c1] = 1) + (\textit{cnt1}[c2] = 0)$ and $y - (\textit{cnt2}[c2] = 1) + (\textit{cnt2}[c1] = 0)$ are equal. If they are equal, then we have found a solution and return $\text{true}$.

If we have enumerated all characters and have not found a suitable solution, we return $\text{false}$.

The time complexity is $O(m + n + |\Sigma|^2)$, where $m$ and $n$ are the lengths of the strings $\textit{word1}$ and $\textit{word2}$, and $\Sigma$ is the character set. In this problem, the character set consists of lowercase letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isItPossible(self, word1: str, word2: str) -> bool:
        cnt1 = Counter(word1)
        cnt2 = Counter(word2)
        x, y = len(cnt1), len(cnt2)
        for c1, v1 in cnt1.items():
            for c2, v2 in cnt2.items():
                if c1 == c2:
                    if x == y:
                        return True
                else:
                    a = x - (v1 == 1) + (cnt1[c2] == 0)
                    b = y - (v2 == 1) + (cnt2[c1] == 0)
                    if a == b:
                        return True
        return False
```

#### Java

```java
class Solution {
    public boolean isItPossible(String word1, String word2) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        int x = 0, y = 0;
        for (int i = 0; i < word1.length(); ++i) {
            if (++cnt1[word1.charAt(i) - 'a'] == 1) {
                ++x;
            }
        }
        for (int i = 0; i < word2.length(); ++i) {
            if (++cnt2[word2.charAt(i) - 'a'] == 1) {
                ++y;
            }
        }
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (cnt1[i] > 0 && cnt2[j] > 0) {
                    if (i == j) {
                        if (x == y) {
                            return true;
                        }
                    } else {
                        int a = x - (cnt1[i] == 1 ? 1 : 0) + (cnt1[j] == 0 ? 1 : 0);
                        int b = y - (cnt2[j] == 1 ? 1 : 0) + (cnt2[i] == 0 ? 1 : 0);
                        if (a == b) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isItPossible(string word1, string word2) {
        int cnt1[26]{};
        int cnt2[26]{};
        int x = 0, y = 0;
        for (char& c : word1) {
            if (++cnt1[c - 'a'] == 1) {
                ++x;
            }
        }
        for (char& c : word2) {
            if (++cnt2[c - 'a'] == 1) {
                ++y;
            }
        }
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (cnt1[i] > 0 && cnt2[j] > 0) {
                    if (i == j) {
                        if (x == y) {
                            return true;
                        }
                    } else {
                        int a = x - (cnt1[i] == 1 ? 1 : 0) + (cnt1[j] == 0 ? 1 : 0);
                        int b = y - (cnt2[j] == 1 ? 1 : 0) + (cnt2[i] == 0 ? 1 : 0);
                        if (a == b) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
};
```

#### Go

```go
func isItPossible(word1 string, word2 string) bool {
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	x, y := 0, 0
	for _, c := range word1 {
		cnt1[c-'a']++
		if cnt1[c-'a'] == 1 {
			x++
		}
	}
	for _, c := range word2 {
		cnt2[c-'a']++
		if cnt2[c-'a'] == 1 {
			y++
		}
	}
	for i := range cnt1 {
		for j := range cnt2 {
			if cnt1[i] > 0 && cnt2[j] > 0 {
				if i == j {
					if x == y {
						return true
					}
				} else {
					a := x
					if cnt1[i] == 1 {
						a--
					}
					if cnt1[j] == 0 {
						a++
					}

					b := y
					if cnt2[j] == 1 {
						b--
					}
					if cnt2[i] == 0 {
						b++
					}

					if a == b {
						return true
					}
				}
			}
		}
	}
	return false
}
```

#### TypeScript

```ts
function isItPossible(word1: string, word2: string): boolean {
    const cnt1: number[] = Array(26).fill(0);
    const cnt2: number[] = Array(26).fill(0);
    let [x, y] = [0, 0];

    for (const c of word1) {
        if (++cnt1[c.charCodeAt(0) - 'a'.charCodeAt(0)] === 1) {
            ++x;
        }
    }

    for (const c of word2) {
        if (++cnt2[c.charCodeAt(0) - 'a'.charCodeAt(0)] === 1) {
            ++y;
        }
    }

    for (let i = 0; i < 26; ++i) {
        for (let j = 0; j < 26; ++j) {
            if (cnt1[i] > 0 && cnt2[j] > 0) {
                if (i === j) {
                    if (x === y) {
                        return true;
                    }
                } else {
                    const a = x - (cnt1[i] === 1 ? 1 : 0) + (cnt1[j] === 0 ? 1 : 0);
                    const b = y - (cnt2[j] === 1 ? 1 : 0) + (cnt2[i] === 0 ? 1 : 0);
                    if (a === b) {
                        return true;
                    }
                }
            }
        }
    }

    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
