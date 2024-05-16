---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1165.Single-Row%20Keyboard/README_EN.md
rating: 1199
source: Biweekly Contest 7 Q1
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [1165. Single-Row Keyboard 🔒](https://leetcode.com/problems/single-row-keyboard)

[中文文档](/solution/1100-1199/1165.Single-Row%20Keyboard/README.md)

## Description

<p>There is a special keyboard with <strong>all keys in a single row</strong>.</p>

<p>Given a string <code>keyboard</code> of length <code>26</code> indicating the layout of the keyboard (indexed from <code>0</code> to <code>25</code>). Initially, your finger is at index <code>0</code>. To type a character, you have to move your finger to the index of the desired character. The time taken to move your finger from index <code>i</code> to index <code>j</code> is <code>|i - j|</code>.</p>

<p>You want to type a string <code>word</code>. Write a function to calculate how much time it takes to type it with one finger.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> keyboard = &quot;abcdefghijklmnopqrstuvwxyz&quot;, word = &quot;cba&quot;
<strong>Output:</strong> 4
<strong>Explanation: </strong>The index moves from 0 to 2 to write &#39;c&#39; then to 1 to write &#39;b&#39; then to 0 again to write &#39;a&#39;.
Total time = 2 + 1 + 1 = 4. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> keyboard = &quot;pqrstuvwxyzabcdefghijklmno&quot;, word = &quot;leetcode&quot;
<strong>Output:</strong> 73
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>keyboard.length == 26</code></li>
	<li><code>keyboard</code> contains each English lowercase letter exactly once in some order.</li>
	<li><code>1 &lt;= word.length &lt;= 10<sup>4</sup></code></li>
	<li><code>word[i]</code> is an English lowercase letter.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table or Array

We can use a hash table or an array $pos$ of length $26$ to store the position of each character on the keyboard, where $pos[c]$ represents the position of character $c$ on the keyboard.

Then we traverse the string $word$, using a variable $i$ to record the current position of the finger, initially $i = 0$. Each time, we calculate the position $j$ of the current character $c$ on the keyboard, and increase the answer by $|i - j|$, then update $i$ to $j$. Continue to traverse the next character until the entire string $word$ is traversed.

After traversing the string $word$, we can get the answer.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Here, $n$ is the length of the string $word$, and $C$ is the size of the character set. In this problem, $C = 26$.

<!-- tabs:start -->

```python
class Solution:
    def calculateTime(self, keyboard: str, word: str) -> int:
        pos = {c: i for i, c in enumerate(keyboard)}
        ans = i = 0
        for c in word:
            ans += abs(pos[c] - i)
            i = pos[c]
        return ans
```

```java
class Solution {
    public int calculateTime(String keyboard, String word) {
        int[] pos = new int[26];
        for (int i = 0; i < 26; ++i) {
            pos[keyboard.charAt(i) - 'a'] = i;
        }
        int ans = 0, i = 0;
        for (int k = 0; k < word.length(); ++k) {
            int j = pos[word.charAt(k) - 'a'];
            ans += Math.abs(i - j);
            i = j;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int calculateTime(string keyboard, string word) {
        int pos[26];
        for (int i = 0; i < 26; ++i) {
            pos[keyboard[i] - 'a'] = i;
        }
        int ans = 0, i = 0;
        for (char& c : word) {
            int j = pos[c - 'a'];
            ans += abs(i - j);
            i = j;
        }
        return ans;
    }
};
```

```go
func calculateTime(keyboard string, word string) (ans int) {
	pos := [26]int{}
	for i, c := range keyboard {
		pos[c-'a'] = i
	}
	i := 0
	for _, c := range word {
		j := pos[c-'a']
		ans += abs(i - j)
		i = j
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function calculateTime(keyboard: string, word: string): number {
    const pos: number[] = Array(26).fill(0);
    for (let i = 0; i < 26; ++i) {
        pos[keyboard.charCodeAt(i) - 97] = i;
    }
    let ans = 0;
    let i = 0;
    for (const c of word) {
        const j = pos[c.charCodeAt(0) - 97];
        ans += Math.abs(i - j);
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
