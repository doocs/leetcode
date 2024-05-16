---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2957.Remove%20Adjacent%20Almost-Equal%20Characters/README_EN.md
rating: 1429
source: Biweekly Contest 119 Q2
tags:
    - Greedy
    - String
    - Dynamic Programming
---

# [2957. Remove Adjacent Almost-Equal Characters](https://leetcode.com/problems/remove-adjacent-almost-equal-characters)

[中文文档](/solution/2900-2999/2957.Remove%20Adjacent%20Almost-Equal%20Characters/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>word</code>.</p>

<p>In one operation, you can pick any index <code>i</code> of <code>word</code> and change <code>word[i]</code> to any lowercase English letter.</p>

<p>Return <em>the <strong>minimum</strong> number of operations needed to remove all adjacent <strong>almost-equal</strong> characters from</em> <code>word</code>.</p>

<p>Two characters <code>a</code> and <code>b</code> are <strong>almost-equal</strong> if <code>a == b</code> or <code>a</code> and <code>b</code> are adjacent in the alphabet.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;aaaaa&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can change word into &quot;a<strong><u>c</u></strong>a<u><strong>c</strong></u>a&quot; which does not have any adjacent almost-equal characters.
It can be shown that the minimum number of operations needed to remove all adjacent almost-equal characters from word is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abddez&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can change word into &quot;<strong><u>y</u></strong>bd<u><strong>o</strong></u>ez&quot; which does not have any adjacent almost-equal characters.
It can be shown that the minimum number of operations needed to remove all adjacent almost-equal characters from word is 2.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;zyxyxyz&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can change word into &quot;z<u><strong>a</strong></u>x<u><strong>a</strong></u>x<strong><u>a</u></strong>z&quot; which does not have any adjacent almost-equal characters. 
It can be shown that the minimum number of operations needed to remove all adjacent almost-equal characters from word is 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Greedy

We start traversing the string `word` from index $1$. If `word[i]` and `word[i - 1]` are approximately equal, we greedily replace `word[i]` with a character that is not equal to both `word[i - 1]` and `word[i + 1]` (we can choose not to perform the replacement operation, just record the number of operations). Then, we skip `word[i + 1]` and continue to traverse the string `word`.

Finally, we return the recorded number of operations.

The time complexity is $O(n)$, where $n$ is the length of the string `word`. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def removeAlmostEqualCharacters(self, word: str) -> int:
        ans = 0
        i, n = 1, len(word)
        while i < n:
            if abs(ord(word[i]) - ord(word[i - 1])) < 2:
                ans += 1
                i += 2
            else:
                i += 1
        return ans
```

```java
class Solution {
    public int removeAlmostEqualCharacters(String word) {
        int ans = 0, n = word.length();
        for (int i = 1; i < n; ++i) {
            if (Math.abs(word.charAt(i) - word.charAt(i - 1)) < 2) {
                ++ans;
                ++i;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int removeAlmostEqualCharacters(string word) {
        int ans = 0, n = word.size();
        for (int i = 1; i < n; ++i) {
            if (abs(word[i] - word[i - 1]) < 2) {
                ++ans;
                ++i;
            }
        }
        return ans;
    }
};
```

```go
func removeAlmostEqualCharacters(word string) (ans int) {
	for i := 1; i < len(word); i++ {
		if abs(int(word[i])-int(word[i-1])) < 2 {
			ans++
			i++
		}
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
function removeAlmostEqualCharacters(word: string): number {
    let ans = 0;
    for (let i = 1; i < word.length; ++i) {
        if (Math.abs(word.charCodeAt(i) - word.charCodeAt(i - 1)) < 2) {
            ++ans;
            ++i;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
