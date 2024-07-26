---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2531.Make%20Number%20of%20Distinct%20Characters%20Equal/README.md
rating: 1775
source: 第 327 场周赛 Q3
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [2531. 使字符串中不同字符的数目相等](https://leetcode.cn/problems/make-number-of-distinct-characters-equal)

[English Version](/solution/2500-2599/2531.Make%20Number%20of%20Distinct%20Characters%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <strong>0</strong> 开始的字符串 <code>word1</code> 和 <code>word2</code> 。</p>

<p>一次 <strong>移动</strong> 由以下两个步骤组成：</p>

<ul>
	<li>选中两个下标&nbsp;<code>i</code> 和 <code>j</code> ，分别满足 <code>0 &lt;= i &lt; word1.length</code> 和 <code>0 &lt;= j &lt; word2.length</code> ，</li>
	<li>交换 <code>word1[i]</code> 和 <code>word2[j]</code> 。</li>
</ul>

<p>如果可以通过 <strong>恰好一次</strong> 移动，使 <code>word1</code> 和 <code>word2</code> 中不同字符的数目相等，则返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>word1 = "ac", word2 = "b"
<strong>输出：</strong>false
<strong>解释：</strong>交换任何一组下标都会导致第一个字符串中有 2 个不同的字符，而在第二个字符串中只有 1 个不同字符。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>word1 = "abcc", word2 = "aab"
<strong>输出：</strong>true
<strong>解释：</strong>交换第一个字符串的下标 2 和第二个字符串的下标 0 。之后得到 word1 = "abac" 和 word2 = "cab" ，各有 3 个不同字符。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>word1 = "abcde", word2 = "fghij"
<strong>输出：</strong>true
<strong>解释：</strong>无论交换哪一组下标，两个字符串中都会有 5 个不同字符。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word1</code> 和 <code>word2</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 枚举

我们先用两个长度为 $26$ 的数组 $\textit{cnt1}$ 和 $\textit{cnt2}$ 分别记录字符串 $\textit{word1}$ 和 $\textit{word2}$ 中每个字符的出现次数。

然后我们分别统计 $\textit{word1}$ 和 $\textit{word2}$ 中不同字符的个数，分别记为 $x$ 和 $y$。

接下来我们枚举 $\textit{word1}$ 中的每个字符 $c1$ 和 $\textit{word2}$ 中的每个字符 $c2$，如果 $c1 = c2$，那么我们只需要判断 $x$ 和 $y$ 是否相等；否则，我们需要判断 $x - (\textit{cnt1}[c1] = 1) + (\textit{cnt1}[c2] = 0)$ 和 $y - (\textit{cnt2}[c2] = 1) + (\textit{cnt2}[c1] = 0)$ 是否相等。如果相等，那么我们就找到了一种方案，返回 $\text{true}$。

如果我们枚举完所有的字符都没有找到合适的方案，那么我们就返回 $\text{false}$。

时间复杂度 $O(m + n + |\Sigma|^2)$，其中 $m$ 和 $n$ 分别是字符串 $\textit{word1}$ 和 $\textit{word2}$ 的长度，而 $\Sigma$ 是字符集，本题中字符集为小写字母，所以 $|\Sigma| = 26$。

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
