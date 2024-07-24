---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2135.Count%20Words%20Obtained%20After%20Adding%20a%20Letter/README_EN.md
rating: 1828
source: Weekly Contest 275 Q3
tags:
    - Bit Manipulation
    - Array
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [2135. Count Words Obtained After Adding a Letter](https://leetcode.com/problems/count-words-obtained-after-adding-a-letter)

[中文文档](/solution/2100-2199/2135.Count%20Words%20Obtained%20After%20Adding%20a%20Letter/README.md)

## Description

<!-- description:start -->

<p>You are given two <strong>0-indexed</strong> arrays of strings <code>startWords</code> and <code>targetWords</code>. Each string consists of <strong>lowercase English letters</strong> only.</p>

<p>For each string in <code>targetWords</code>, check if it is possible to choose a string from <code>startWords</code> and perform a <strong>conversion operation</strong> on it to be equal to that from <code>targetWords</code>.</p>

<p>The <strong>conversion operation</strong> is described in the following two steps:</p>

<ol>
	<li><strong>Append</strong> any lowercase letter that is <strong>not present</strong> in the string to its end.

    <ul>
    	<li>For example, if the string is <code>&quot;abc&quot;</code>, the letters <code>&#39;d&#39;</code>, <code>&#39;e&#39;</code>, or <code>&#39;y&#39;</code> can be added to it, but not <code>&#39;a&#39;</code>. If <code>&#39;d&#39;</code> is added, the resulting string will be <code>&quot;abcd&quot;</code>.</li>
    </ul>
    </li>
    <li><strong>Rearrange</strong> the letters of the new string in <strong>any</strong> arbitrary order.
    <ul>
    	<li>For example, <code>&quot;abcd&quot;</code> can be rearranged to <code>&quot;acbd&quot;</code>, <code>&quot;bacd&quot;</code>, <code>&quot;cbda&quot;</code>, and so on. Note that it can also be rearranged to <code>&quot;abcd&quot;</code> itself.</li>
    </ul>
    </li>

</ol>

<p>Return <em>the <strong>number of strings</strong> in </em><code>targetWords</code><em> that can be obtained by performing the operations on <strong>any</strong> string of </em><code>startWords</code>.</p>

<p><strong>Note</strong> that you will only be verifying if the string in <code>targetWords</code> can be obtained from a string in <code>startWords</code> by performing the operations. The strings in <code>startWords</code> <strong>do not</strong> actually change during this process.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> startWords = [&quot;ant&quot;,&quot;act&quot;,&quot;tack&quot;], targetWords = [&quot;tack&quot;,&quot;act&quot;,&quot;acti&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
- In order to form targetWords[0] = &quot;tack&quot;, we use startWords[1] = &quot;act&quot;, append &#39;k&#39; to it, and rearrange &quot;actk&quot; to &quot;tack&quot;.
- There is no string in startWords that can be used to obtain targetWords[1] = &quot;act&quot;.
  Note that &quot;act&quot; does exist in startWords, but we <strong>must</strong> append one letter to the string before rearranging it.
- In order to form targetWords[2] = &quot;acti&quot;, we use startWords[1] = &quot;act&quot;, append &#39;i&#39; to it, and rearrange &quot;acti&quot; to &quot;acti&quot; itself.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> startWords = [&quot;ab&quot;,&quot;a&quot;], targetWords = [&quot;abc&quot;,&quot;abcd&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
- In order to form targetWords[0] = &quot;abc&quot;, we use startWords[0] = &quot;ab&quot;, add &#39;c&#39; to it, and rearrange it to &quot;abc&quot;.
- There is no string in startWords that can be used to obtain targetWords[1] = &quot;abcd&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= startWords.length, targetWords.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= startWords[i].length, targetWords[j].length &lt;= 26</code></li>
	<li>Each string of <code>startWords</code> and <code>targetWords</code> consists of lowercase English letters only.</li>
	<li>No letter occurs more than once in any string of <code>startWords</code> or <code>targetWords</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Bit Manipulation

We notice that the given strings only contain lowercase letters, and each letter in a string appears at most once. Therefore, we can represent a string with a binary number of length $26$, where the $i$-th bit being $1$ indicates that the string contains the $i$-th lowercase letter, and $0$ indicates the absence of the $i$-th lowercase letter.

We can convert each string in the array $\textit{startWords}$ into a binary number and store these binary numbers in a set $\textit{s}$. For each string in the array $\textit{targetWords}$, we first convert it into a binary number, then enumerate each letter in this string, remove this letter from the binary number, and check if there exists a binary number in the set $\textit{s}$ such that the XOR result of this binary number with the removed letter's binary number is in the set $\textit{s}$. If such a binary number exists, then this string can be obtained by performing a transformation operation on some string in $\textit{startWords}$, and we increment the answer by one. Then, we skip this string and continue processing the next string.

The time complexity is $O(n \times |\Sigma|)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string array $\textit{targetWords}$, and $|\Sigma|$ is the size of the character set in the string, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def wordCount(self, startWords: List[str], targetWords: List[str]) -> int:
        s = {sum(1 << (ord(c) - 97) for c in w) for w in startWords}
        ans = 0
        for w in targetWords:
            x = sum(1 << (ord(c) - 97) for c in w)
            for c in w:
                if x ^ (1 << (ord(c) - 97)) in s:
                    ans += 1
                    break
        return ans
```

#### Java

```java
class Solution {
    public int wordCount(String[] startWords, String[] targetWords) {
        Set<Integer> s = new HashSet<>();
        for (var w : startWords) {
            int x = 0;
            for (var c : w.toCharArray()) {
                x |= 1 << (c - 'a');
            }
            s.add(x);
        }
        int ans = 0;
        for (var w : targetWords) {
            int x = 0;
            for (var c : w.toCharArray()) {
                x |= 1 << (c - 'a');
            }
            for (var c : w.toCharArray()) {
                if (s.contains(x ^ (1 << (c - 'a')))) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int wordCount(vector<string>& startWords, vector<string>& targetWords) {
        unordered_set<int> s;
        for (auto& w : startWords) {
            int x = 0;
            for (char c : w) {
                x |= 1 << (c - 'a');
            }
            s.insert(x);
        }
        int ans = 0;
        for (auto& w : targetWords) {
            int x = 0;
            for (char c : w) {
                x |= 1 << (c - 'a');
            }
            for (char c : w) {
                if (s.contains(x ^ (1 << (c - 'a')))) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func wordCount(startWords []string, targetWords []string) (ans int) {
	s := map[int]bool{}
	for _, w := range startWords {
		x := 0
		for _, c := range w {
			x |= 1 << (c - 'a')
		}
		s[x] = true
	}
	for _, w := range targetWords {
		x := 0
		for _, c := range w {
			x |= 1 << (c - 'a')
		}
		for _, c := range w {
			if s[x^(1<<(c-'a'))] {
				ans++
				break
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function wordCount(startWords: string[], targetWords: string[]): number {
    const s = new Set<number>();
    for (const w of startWords) {
        let x = 0;
        for (const c of w) {
            x ^= 1 << (c.charCodeAt(0) - 97);
        }
        s.add(x);
    }
    let ans = 0;
    for (const w of targetWords) {
        let x = 0;
        for (const c of w) {
            x ^= 1 << (c.charCodeAt(0) - 97);
        }
        for (const c of w) {
            if (s.has(x ^ (1 << (c.charCodeAt(0) - 97)))) {
                ++ans;
                break;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
