---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3121.Count%20the%20Number%20of%20Special%20Characters%20II/README_EN.md
rating: 1411
source: Weekly Contest 394 Q2
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [3121. Count the Number of Special Characters II](https://leetcode.com/problems/count-the-number-of-special-characters-ii)

[中文文档](/solution/3100-3199/3121.Count%20the%20Number%20of%20Special%20Characters%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>word</code>. A letter&nbsp;<code>c</code> is called <strong>special</strong> if it appears <strong>both</strong> in lowercase and uppercase in <code>word</code>, and <strong>every</strong> lowercase occurrence of <code>c</code> appears before the <strong>first</strong> uppercase occurrence of <code>c</code>.</p>

<p>Return the number of<em> </em><strong>special</strong> letters<em> </em>in<em> </em><code>word</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;aaAbcBC&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The special characters are <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no special characters in <code>word</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;AbBCab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no special characters in <code>word</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>word</code> consists of only lowercase and uppercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table or Array

We define two hash tables or arrays `first` and `last` to store the positions where each letter first appears and last appears respectively.

Then we traverse the string `word`, updating `first` and `last`.

Finally, we traverse all lowercase and uppercase letters. If `last[a]` exists and `first[b]` exists and `last[a] < first[b]`, it means that the letter `a` is a special letter, and we increment the answer by one.

The time complexity is $O(n + |\Sigma|)$, and the space complexity is $O(|\Sigma|)$. Where $n$ is the length of the string `word`, and $|\Sigma|$ is the size of the character set. In this problem, $|\Sigma| \leq 128$.

<!-- tabs:start -->

```python
class Solution:
    def numberOfSpecialChars(self, word: str) -> int:
        first, last = {}, {}
        for i, c in enumerate(word):
            if c not in first:
                first[c] = i
            last[c] = i
        return sum(
            a in last and b in first and last[a] < first[b]
            for a, b in zip(ascii_lowercase, ascii_uppercase)
        )
```

```java
class Solution {
    public int numberOfSpecialChars(String word) {
        int[] first = new int['z' + 1];
        int[] last = new int['z' + 1];
        for (int i = 1; i <= word.length(); ++i) {
            int j = word.charAt(i - 1);
            if (first[j] == 0) {
                first[j] = i;
            }
            last[j] = i;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (last['a' + i] > 0 && first['A' + i] > 0 && last['a' + i] < first['A' + i]) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfSpecialChars(string word) {
        vector<int> first('z' + 1);
        vector<int> last('z' + 1);
        for (int i = 1; i <= word.size(); ++i) {
            int j = word[i - 1];
            if (first[j] == 0) {
                first[j] = i;
            }
            last[j] = i;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (last['a' + i] && first['A' + i] && last['a' + i] < first['A' + i]) {
                ++ans;
            }
        }
        return ans;
    }
};
```

```go
func numberOfSpecialChars(word string) (ans int) {
	first := make([]int, 'z'+1)
	last := make([]int, 'z'+1)
	for i, c := range word {
		if first[c] == 0 {
			first[c] = i + 1
		}
		last[c] = i + 1
	}
	for i := 0; i < 26; i++ {
		if last['a'+i] > 0 && first['A'+i] > 0 && last['a'+i] < first['A'+i] {
			ans++
		}
	}
	return
}
```

```ts
function numberOfSpecialChars(word: string): number {
    const first: number[] = Array.from({ length: 'z'.charCodeAt(0) + 1 }, () => 0);
    const last: number[] = Array.from({ length: 'z'.charCodeAt(0) + 1 }, () => 0);
    for (let i = 0; i < word.length; ++i) {
        const j = word.charCodeAt(i);
        if (first[j] === 0) {
            first[j] = i + 1;
        }
        last[j] = i + 1;
    }
    let ans: number = 0;
    for (let i = 0; i < 26; ++i) {
        if (
            last['a'.charCodeAt(0) + i] &&
            first['A'.charCodeAt(0) + i] &&
            last['a'.charCodeAt(0) + i] < first['A'.charCodeAt(0) + i]
        ) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
