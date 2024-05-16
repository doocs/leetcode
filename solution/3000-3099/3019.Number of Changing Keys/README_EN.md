---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3019.Number%20of%20Changing%20Keys/README_EN.md
rating: 1175
source: Weekly Contest 382 Q1
tags:
    - String
---

<!-- problem:start -->

# [3019. Number of Changing Keys](https://leetcode.com/problems/number-of-changing-keys)

[中文文档](/solution/3000-3099/3019.Number%20of%20Changing%20Keys/README.md)

## Description

<p>You are given a <strong>0-indexed </strong>string <code>s</code> typed by a user. Changing a key is defined as using a key different from the last used key. For example, <code>s = &quot;ab&quot;</code> has a change of a key while <code>s = &quot;bBBb&quot;</code> does not have any.</p>

<p>Return <em>the number of times the user had to change the key. </em></p>

<p><strong>Note: </strong>Modifiers like <code>shift</code> or <code>caps lock</code> won&#39;t be counted in changing the key that is if a user typed the letter <code>&#39;a&#39;</code> and then the letter <code>&#39;A&#39;</code> then it will not be considered as a changing of key.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aAbBcC&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
From s[0] = &#39;a&#39; to s[1] = &#39;A&#39;, there is no change of key as caps lock or shift is not counted.
From s[1] = &#39;A&#39; to s[2] = &#39;b&#39;, there is a change of key.
From s[2] = &#39;b&#39; to s[3] = &#39;B&#39;, there is no change of key as caps lock or shift is not counted.
From s[3] = &#39;B&#39; to s[4] = &#39;c&#39;, there is a change of key.
From s[4] = &#39;c&#39; to s[5] = &#39;C&#39;, there is no change of key as caps lock or shift is not counted.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;AaAaAaaA&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no change of key since only the letters &#39;a&#39; and &#39;A&#39; are<!-- notionvc: 8849fe75-f31e-41dc-a2e0-b7d33d8427d2 --> pressed which does not require change of key.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of only upper case and lower case English letters.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We can traverse the string, each time checking whether the lowercase form of the current character is the same as the lowercase form of the previous character. If they are different, it means that the key has been changed, and we can increment the answer accordingly.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def countKeyChanges(self, s: str) -> int:
        return sum(a.lower() != b.lower() for a, b in pairwise(s))
```

```java
class Solution {
    public int countKeyChanges(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(i - 1))) {
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
    int countKeyChanges(string s) {
        transform(s.begin(), s.end(), s.begin(), ::tolower);
        int ans = 0;
        for (int i = 1; i < s.size(); ++i) {
            ans += s[i] != s[i - 1];
        }
        return ans;
    }
};
```

```go
func countKeyChanges(s string) (ans int) {
	s = strings.ToLower(s)
	for i, c := range s[1:] {
		if byte(c) != s[i] {
			ans++
		}
	}
	return
}
```

```ts
function countKeyChanges(s: string): number {
    s = s.toLowerCase();
    let ans = 0;
    for (let i = 1; i < s.length; ++i) {
        if (s[i] !== s[i - 1]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
