---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2840.Check%20if%20Strings%20Can%20be%20Made%20Equal%20With%20Operations%20II/README_EN.md
rating: 1486
source: Biweekly Contest 112 Q2
tags:
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [2840. Check if Strings Can be Made Equal With Operations II](https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-ii)

[中文文档](/solution/2800-2899/2840.Check%20if%20Strings%20Can%20be%20Made%20Equal%20With%20Operations%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s1</code> and <code>s2</code>, both of length <code>n</code>, consisting of <strong>lowercase</strong> English letters.</p>

<p>You can apply the following operation on <strong>any</strong> of the two strings <strong>any</strong> number of times:</p>

<ul>
	<li>Choose any two indices <code>i</code> and <code>j</code> such that <code>i &lt; j</code> and the difference <code>j - i</code> is <strong>even</strong>, then <strong>swap</strong> the two characters at those indices in the string.</li>
</ul>

<p>Return <code>true</code><em> if you can make the strings </em><code>s1</code><em> and </em><code>s2</code><em> equal, and&nbsp;</em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;abcdba&quot;, s2 = &quot;cabdab&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> We can apply the following operations on s1:
- Choose the indices i = 0, j = 2. The resulting string is s1 = &quot;cbadba&quot;.
- Choose the indices i = 2, j = 4. The resulting string is s1 = &quot;cbbdaa&quot;.
- Choose the indices i = 1, j = 5. The resulting string is s1 = &quot;cabdab&quot; = s2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;abe&quot;, s2 = &quot;bea&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It is not possible to make the two strings equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s1.length == s2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>s1</code> and <code>s2</code> consist only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We observe the operation in the problem, and find that if the parity of the two indices $i$ and $j$ of the string is the same, then their order can be changed by swapping.

Therefore, we can count the occurrence times of the characters at odd indices and even indices in the two strings. If the counting results of the two strings are the same, then we can make the two strings equal through the operation.

The time complexity is $O(n + |\Sigma|)$, and the space complexity is $O(|\Sigma|)$. Here, $n$ is the length of the string, and $\Sigma$ is the character set.

Similar problems:

-   [2839. Check if Strings Can be Made Equal With Operations I](https://github.com/doocs/leetcode/blob/main/solution/2800-2899/2839.Check%20if%20Strings%20Can%20be%20Made%20Equal%20With%20Operations%20I/README_EN.md)

<!-- tabs:start -->

```python
class Solution:
    def checkStrings(self, s1: str, s2: str) -> bool:
        return sorted(s1[::2]) == sorted(s2[::2]) and sorted(s1[1::2]) == sorted(
            s2[1::2]
        )
```

```java
class Solution {
    public boolean checkStrings(String s1, String s2) {
        int[][] cnt = new int[2][26];
        for (int i = 0; i < s1.length(); ++i) {
            ++cnt[i & 1][s1.charAt(i) - 'a'];
            --cnt[i & 1][s2.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[0][i] != 0 || cnt[1][i] != 0) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool checkStrings(string s1, string s2) {
        vector<vector<int>> cnt(2, vector<int>(26, 0));
        for (int i = 0; i < s1.size(); ++i) {
            ++cnt[i & 1][s1[i] - 'a'];
            --cnt[i & 1][s2[i] - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[0][i] || cnt[1][i]) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func checkStrings(s1 string, s2 string) bool {
	cnt := [2][26]int{}
	for i := 0; i < len(s1); i++ {
		cnt[i&1][s1[i]-'a']++
		cnt[i&1][s2[i]-'a']--
	}
	for i := 0; i < 26; i++ {
		if cnt[0][i] != 0 || cnt[1][i] != 0 {
			return false
		}
	}
	return true
}
```

```ts
function checkStrings(s1: string, s2: string): boolean {
    const cnt: number[][] = Array.from({ length: 2 }, () => Array.from({ length: 26 }, () => 0));
    for (let i = 0; i < s1.length; ++i) {
        ++cnt[i & 1][s1.charCodeAt(i) - 97];
        --cnt[i & 1][s2.charCodeAt(i) - 97];
    }
    for (let i = 0; i < 26; ++i) {
        if (cnt[0][i] || cnt[1][i]) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
