---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2868.The%20Wording%20Game/README_EN.md
tags:
    - Array
    - Math
    - Two Pointers
    - String
    - Game Theory
---

<!-- problem:start -->

# [2868. The Wording Game 🔒](https://leetcode.com/problems/the-wording-game)

[中文文档](/solution/2800-2899/2868.The%20Wording%20Game/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob each have a <strong>lexicographically sorted</strong> array of strings named <code>a</code> and <code>b</code> respectively.</p>

<p>They are playing a wording game with the following rules:</p>

<ul>
	<li>On each turn, the current player should play a word from their list such that the new word is <strong>closely greater</strong> than the last played word; then it&#39;s the other player&#39;s turn.</li>
	<li>If a player can&#39;t play a word on their turn, they lose.</li>
</ul>

<p>Alice starts the game by playing her <strong>lexicographically </strong><strong>smallest </strong>word.</p>

<p>Given <code>a</code> and <code>b</code>, return <code>true</code> <em>if Alice can win knowing that both players play their best, and</em> <code>false</code> <em>otherwise.</em></p>

<p>A word <code>w</code> is <strong>closely greater</strong> than a word <code>z</code> if the following conditions are met:</p>

<ul>
	<li><code>w</code> is <strong>lexicographically greater</strong> than <code>z</code>.</li>
	<li>If <code>w<sub>1</sub></code> is the first letter of <code>w</code> and <code>z<sub>1</sub></code> is the first letter of <code>z</code>, <code>w<sub>1</sub></code> should either be <strong>equal</strong> to <code>z<sub>1</sub></code> or be the <strong>letter after</strong> <code>z<sub>1</sub></code> in the alphabet.</li>
	<li>For example, the word <code>&quot;care&quot;</code> is closely greater than <code>&quot;book&quot;</code> and <code>&quot;car&quot;</code>, but is not closely greater than <code>&quot;ant&quot;</code> or <code>&quot;cook&quot;</code>.</li>
</ul>

<p>A string <code>s</code> is <b>lexicographically </b><strong>greater</strong> than a string <code>t</code> if in the first position where <code>s</code> and <code>t</code> differ, string <code>s</code> has a letter that appears later in the alphabet than the corresponding letter in <code>t</code>. If the first <code>min(s.length, t.length)</code> characters do not differ, then the longer string is the lexicographically greater one.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = [&quot;avokado&quot;,&quot;dabar&quot;], b = [&quot;brazil&quot;]
<strong>Output:</strong> false
<strong>Explanation:</strong> Alice must start the game by playing the word &quot;avokado&quot; since it&#39;s her smallest word, then Bob plays his only word, &quot;brazil&quot;, which he can play because its first letter, &#39;b&#39;, is the letter after Alice&#39;s word&#39;s first letter, &#39;a&#39;.
Alice can&#39;t play a word since the first letter of the only word left is not equal to &#39;b&#39; or the letter after &#39;b&#39;, &#39;c&#39;.
So, Alice loses, and the game ends.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = [&quot;ananas&quot;,&quot;atlas&quot;,&quot;banana&quot;], b = [&quot;albatros&quot;,&quot;cikla&quot;,&quot;nogomet&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong> Alice must start the game by playing the word &quot;ananas&quot;.
Bob can&#39;t play a word since the only word he has that starts with the letter &#39;a&#39; or &#39;b&#39; is &quot;albatros&quot;, which is smaller than Alice&#39;s word.
So Alice wins, and the game ends.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = [&quot;hrvatska&quot;,&quot;zastava&quot;], b = [&quot;bijeli&quot;,&quot;galeb&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong> Alice must start the game by playing the word &quot;hrvatska&quot;.
Bob can&#39;t play a word since the first letter of both of his words are smaller than the first letter of Alice&#39;s word, &#39;h&#39;.
So Alice wins, and the game ends.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>a[i]</code> and <code>b[i]</code> consist only of lowercase English letters.</li>
	<li><code>a</code> and <code>b</code> are <strong>lexicographically sorted</strong>.</li>
	<li>All the words in <code>a</code> and <code>b</code> combined are <strong>distinct</strong>.</li>
	<li>The sum of the lengths of all the words in <code>a</code> and <code>b</code> combined does not exceed <code>10<sup>6</sup></code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use $k$ to record whose turn it is, where $k=0$ means it is Alice's turn, and $k=1$ means it is Bob's turn. We use $i$ to record Alice's index, $j$ to record Bob's index, and $w$ to record the current word. Initially, we set $i=1$, $j=0$, and $w=a[0]$.

We perform the following steps repeatedly:

If $k=1$, we check if $j$ is equal to the length of $b$. If it is, then Alice wins and we return $true$. Otherwise, we check if the first letter of $b[j]$ is equal to the first letter of $w$. If it is, we check if $b[j]$ is greater than $w$, or if the first letter of $b[j]$ is one greater than the first letter of $w$. If either of these conditions is true, then Bob can play the $j$-th word. We set $w=b[j]$ and toggle $k$. Otherwise, Bob cannot play the $j$-th word, so we increment $j$.

If $k=0$, we check if $i$ is equal to the length of $a$. If it is, then Bob wins and we return $false$. Otherwise, we check if the first letter of $a[i]$ is equal to the first letter of $w$. If it is, we check if $a[i]$ is greater than $w$, or if the first letter of $a[i]$ is one greater than the first letter of $w$. If either of these conditions is true, then Alice can play the $i$-th word. We set $w=a[i]$ and toggle $k$. Otherwise, Alice cannot play the $i$-th word, so we increment $i$.

The time complexity is $O(m+n)$, where $m$ and $n$ are the lengths of arrays $a$ and $b$, respectively. We only need to traverse the arrays once. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def canAliceWin(self, a: List[str], b: List[str]) -> bool:
        i, j, k = 1, 0, 1
        w = a[0]
        while 1:
            if k:
                if j == len(b):
                    return True
                if (b[j][0] == w[0] and b[j] > w) or ord(b[j][0]) - ord(w[0]) == 1:
                    w = b[j]
                    k ^= 1
                j += 1
            else:
                if i == len(a):
                    return False
                if (a[i][0] == w[0] and a[i] > w) or ord(a[i][0]) - ord(w[0]) == 1:
                    w = a[i]
                    k ^= 1
                i += 1
```

```java
class Solution {
    public boolean canAliceWin(String[] a, String[] b) {
        int i = 1, j = 0;
        boolean k = true;
        String w = a[0];
        while (true) {
            if (k) {
                if (j == b.length) {
                    return true;
                }
                if ((b[j].charAt(0) == w.charAt(0) && w.compareTo(b[j]) < 0)
                    || b[j].charAt(0) - w.charAt(0) == 1) {
                    w = b[j];
                    k = !k;
                }
                ++j;
            } else {
                if (i == a.length) {
                    return false;
                }
                if ((a[i].charAt(0) == w.charAt(0) && w.compareTo(a[i]) < 0)
                    || a[i].charAt(0) - w.charAt(0) == 1) {
                    w = a[i];
                    k = !k;
                }
                ++i;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    bool canAliceWin(vector<string>& a, vector<string>& b) {
        int i = 1, j = 0, k = 1;
        string w = a[0];
        while (1) {
            if (k) {
                if (j == b.size()) {
                    return true;
                }
                if ((b[j][0] == w[0] && w < b[j]) || b[j][0] - w[0] == 1) {
                    w = b[j];
                    k ^= 1;
                }
                ++j;
            } else {
                if (i == a.size()) {
                    return false;
                }
                if ((a[i][0] == w[0] && w < a[i]) || a[i][0] - w[0] == 1) {
                    w = a[i];
                    k ^= 1;
                }
                ++i;
            }
        }
    }
};
```

```go
func canAliceWin(a []string, b []string) bool {
	i, j, k := 1, 0, 1
	w := a[0]
	for {
		if k&1 == 1 {
			if j == len(b) {
				return true
			}
			if (b[j][0] == w[0] && w < b[j]) || b[j][0]-w[0] == 1 {
				w = b[j]
				k ^= 1
			}
			j++
		} else {
			if i == len(a) {
				return false
			}
			if (a[i][0] == w[0] && w < a[i]) || a[i][0]-w[0] == 1 {
				w = a[i]
				k ^= 1
			}
			i++
		}
	}
}
```

```ts
function canAliceWin(a: string[], b: string[]): boolean {
    let [i, j, k] = [1, 0, 1];
    let w = a[0];
    while (1) {
        if (k) {
            if (j === b.length) {
                return true;
            }
            if ((b[j][0] === w[0] && w < b[j]) || b[j].charCodeAt(0) - w.charCodeAt(0) === 1) {
                w = b[j];
                k ^= 1;
            }
            ++j;
        } else {
            if (i === a.length) {
                return false;
            }
            if ((a[i][0] === w[0] && w < a[i]) || a[i].charCodeAt(0) - w.charCodeAt(0) === 1) {
                w = a[i];
                k ^= 1;
            }
            ++i;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
