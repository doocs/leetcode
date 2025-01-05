---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3403.Find%20the%20Lexicographically%20Largest%20String%20From%20the%20Box%20I/README_EN.md
---

<!-- problem:start -->

# [3403. Find the Lexicographically Largest String From the Box I](https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i)

[中文文档](/solution/3400-3499/3403.Find%20the%20Lexicographically%20Largest%20String%20From%20the%20Box%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>word</code>, and an integer <code>numFriends</code>.</p>

<p>Alice is organizing a game for her <code>numFriends</code> friends. There are multiple rounds in the game, where in each round:</p>

<ul>
	<li><code>word</code> is split into <code>numFriends</code> <strong>non-empty</strong> strings, such that no previous round has had the <strong>exact</strong> same split.</li>
	<li>All the split words are put into a box.</li>
</ul>

<p>Find the <span data-keyword="lexicographically-smaller-string">lexicographically largest</span> string from the box after all the rounds are finished.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;dbca&quot;, numFriends = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;dbc&quot;</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>All possible splits are:</p>

<ul>
	<li><code>&quot;d&quot;</code> and <code>&quot;bca&quot;</code>.</li>
	<li><code>&quot;db&quot;</code> and <code>&quot;ca&quot;</code>.</li>
	<li><code>&quot;dbc&quot;</code> and <code>&quot;a&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;gggg&quot;, numFriends = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;g&quot;</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>The only possible split is: <code>&quot;g&quot;</code>, <code>&quot;g&quot;</code>, <code>&quot;g&quot;</code>, and <code>&quot;g&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 5&nbsp;* 10<sup>3</sup></code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= numFriends &lt;= word.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def answerString(self, word: str, numFriends: int) -> str:
        if numFriends == 1:
            return word
        n = len(word)
        ans = ""
        for i in range(n):
            k = min(n - i, n - numFriends + 1)
            ans = max(ans, word[i : i + k])
        return ans
```

#### Java

```java
class Solution {
    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        int n = word.length();
        String ans = "";
        for (int i = 0; i < n; ++i) {
            int k = Math.min(n - i, n - numFriends + 1);
            String t = word.substring(i, i + k);
            if (ans.compareTo(t) < 0) {
                ans = t;
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
    string answerString(string word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        int n = word.size();
        string ans;
        for (int i = 0; i < n; ++i) {
            int k = min(n - i, n - numFriends + 1);
            string t = word.substr(i, k);
            ans = max(ans, t);
        }
        return ans;
    }
};
```

#### Go

```go
func answerString(word string, numFriends int) (ans string) {
	if numFriends == 1 {
		return word
	}
	n := len(word)
	for i := range word {
		k := min(n-i, n-numFriends+1)
		t := word[i : i+k]
		ans = max(ans, t)
	}
	return
}
```

#### TypeScript

```ts
function answerString(word: string, numFriends: number): string {
    if (numFriends === 1) {
        return word;
    }
    let ans: string = '';
    const n = word.length;
    for (let i = 0; i < n; ++i) {
        const k = Math.min(n - i, n - numFriends + 1);
        const t = word.slice(i, i + k);
        if (ans < t) {
            ans = t;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
