---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3227.Vowels%20Game%20in%20a%20String/README_EN.md
tags:
    - Brainteaser
    - Math
    - String
    - Game Theory
---

<!-- problem:start -->

# [3227. Vowels Game in a String](https://leetcode.com/problems/vowels-game-in-a-string)

[中文文档](/solution/3200-3299/3227.Vowels%20Game%20in%20a%20String/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob are playing a game on a string.</p>

<p>You are given a string <code>s</code>, Alice and Bob will take turns playing the following game where Alice starts <strong>first</strong>:</p>

<ul>
	<li>On Alice&#39;s turn, she has to remove any <strong>non-empty</strong> <span data-keyword="substring">substring</span> from <code>s</code> that contains an <strong>odd</strong> number of vowels.</li>
	<li>On Bob&#39;s turn, he has to remove any <strong>non-empty</strong> <span data-keyword="substring">substring</span> from <code>s</code> that contains an <strong>even</strong> number of vowels.</li>
</ul>

<p>The first player who cannot make a move on their turn loses the game. We assume that both Alice and Bob play <strong>optimally</strong>.</p>

<p>Return <code>true</code> if Alice wins the game, and <code>false</code> otherwise.</p>

<p>The English vowels are: <code>a</code>, <code>e</code>, <code>i</code>, <code>o</code>, and <code>u</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;leetcoder&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong><br />
Alice can win the game as follows:</p>

<ul>
	<li>Alice plays first, she can delete the underlined substring in <code>s = &quot;<u><strong>leetco</strong></u>der&quot;</code> which contains 3 vowels. The resulting string is <code>s = &quot;der&quot;</code>.</li>
	<li>Bob plays second, he can delete the underlined substring in <code>s = &quot;<u><strong>d</strong></u>er&quot;</code> which contains 0 vowels. The resulting string is <code>s = &quot;er&quot;</code>.</li>
	<li>Alice plays third, she can delete the whole string <code>s = &quot;<strong><u>er</u></strong>&quot;</code> which contains 1 vowel.</li>
	<li>Bob plays fourth, since the string is empty, there is no valid play for Bob. So Alice wins the game.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;bbcd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong><br />
There is no valid play for Alice in her first turn, so Alice loses the game.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brain Teaser

Let's denote the number of vowels in the string as $k$.

If $k = 0$, meaning there are no vowels in the string, then Little Red cannot remove any substring, and Little Ming wins directly.

If $k$ is odd, then Little Red can remove the entire string, resulting in a direct win for Little Red.

If $k$ is even, then Little Red can remove $k - 1$ vowels, leaving one vowel in the string. In this case, Little Ming cannot remove any substring, leading to a direct win for Little Red.

In conclusion, if the string contains vowels, then Little Red wins; otherwise, Little Ming wins.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def doesAliceWin(self, s: str) -> bool:
        vowels = set("aeiou")
        return any(c in vowels for c in s)
```

#### Java

```java
class Solution {
    public boolean doesAliceWin(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if ("aeiou".indexOf(s.charAt(i)) != -1) {
                return true;
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
    bool doesAliceWin(string s) {
        string vowels = "aeiou";
        for (char c : s) {
            if (vowels.find(c) != string::npos) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func doesAliceWin(s string) bool {
	vowels := "aeiou"
	for _, c := range s {
		if strings.ContainsRune(vowels, c) {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function doesAliceWin(s: string): boolean {
    const vowels = 'aeiou';
    for (const c of s) {
        if (vowels.includes(c)) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
