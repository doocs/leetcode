# [408. Valid Word Abbreviation 🔒](https://leetcode.com/problems/valid-word-abbreviation)

[中文文档](/solution/0400-0499/0408.Valid%20Word%20Abbreviation/README.md)

<!-- tags:Two Pointers,String -->

<!-- difficulty:Easy -->

## Description

<p>A string can be <strong>abbreviated</strong> by replacing any number of <strong>non-adjacent</strong>, <strong>non-empty</strong> substrings with their lengths. The lengths <strong>should not</strong> have leading zeros.</p>

<p>For example, a string such as <code>&quot;substitution&quot;</code> could be abbreviated as (but not limited to):</p>

<ul>
	<li><code>&quot;s10n&quot;</code> (<code>&quot;s <u>ubstitutio</u> n&quot;</code>)</li>
	<li><code>&quot;sub4u4&quot;</code> (<code>&quot;sub <u>stit</u> u <u>tion</u>&quot;</code>)</li>
	<li><code>&quot;12&quot;</code> (<code>&quot;<u>substitution</u>&quot;</code>)</li>
	<li><code>&quot;su3i1u2on&quot;</code> (<code>&quot;su <u>bst</u> i <u>t</u> u <u>ti</u> on&quot;</code>)</li>
	<li><code>&quot;substitution&quot;</code> (no substrings replaced)</li>
</ul>

<p>The following are <strong>not valid</strong> abbreviations:</p>

<ul>
	<li><code>&quot;s55n&quot;</code> (<code>&quot;s <u>ubsti</u> <u>tutio</u> n&quot;</code>, the replaced substrings are adjacent)</li>
	<li><code>&quot;s010n&quot;</code> (has leading zeros)</li>
	<li><code>&quot;s0ubstitution&quot;</code> (replaces an empty substring)</li>
</ul>

<p>Given a string <code>word</code> and an abbreviation <code>abbr</code>, return <em>whether the string <strong>matches</strong> the given abbreviation</em>.</p>

<p>A <strong>substring</strong> is a contiguous <strong>non-empty</strong> sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;internationalization&quot;, abbr = &quot;i12iz4n&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The word &quot;internationalization&quot; can be abbreviated as &quot;i12iz4n&quot; (&quot;i <u>nternational</u> iz <u>atio</u> n&quot;).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;apple&quot;, abbr = &quot;a2e&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The word &quot;apple&quot; cannot be abbreviated as &quot;a2e&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 20</code></li>
	<li><code>word</code> consists of only lowercase English letters.</li>
	<li><code>1 &lt;= abbr.length &lt;= 10</code></li>
	<li><code>abbr</code> consists of lowercase English letters and digits.</li>
	<li>All the integers in <code>abbr</code> will fit in a 32-bit integer.</li>
</ul>

## Solutions

### Solution 1: Simulation

We can directly simulate character matching and replacement.

Assume the lengths of the string $word$ and the string $abbr$ are $m$ and $n$ respectively. We use two pointers $i$ and $j$ to point to the initial positions of the string $word$ and the string $abbr$ respectively, and use an integer variable $x$ to record the current matched number in $abbr$.

Loop to match each character of the string $word$ and the string $abbr$:

If the character $abbr[j]$ pointed by the pointer $j$ is a number, if $abbr[j]$ is `'0'` and $x$ is $0$, it means that the number in $abbr$ has leading zeros, so it is not a valid abbreviation, return `false`; otherwise, update $x$ to $x \times 10 + abbr[j] - '0'$.

If the character $abbr[j]$ pointed by the pointer $j$ is not a number, then we move the pointer $i$ forward by $x$ positions at this time, and then reset $x$ to $0$. If $i \geq m$ or $word[i] \neq abbr[j]$ at this time, it means that the two strings cannot match, return `false`; otherwise, move the pointer $i$ forward by $1$ position.

Then we move the pointer $j$ forward by $1$ position, repeat the above process, until $i$ exceeds the length of the string $word$ or $j$ exceeds the length of the string $abbr$.

Finally, if $i + x$ equals $m$ and $j$ equals $n$, it means that the string $word$ can be abbreviated as the string $abbr$, return `true`; otherwise return `false`.

The time complexity is $O(m + n)$, where $m$ and $n$ are the lengths of the string $word$ and the string $abbr$ respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def validWordAbbreviation(self, word: str, abbr: str) -> bool:
        m, n = len(word), len(abbr)
        i = j = x = 0
        while i < m and j < n:
            if abbr[j].isdigit():
                if abbr[j] == "0" and x == 0:
                    return False
                x = x * 10 + int(abbr[j])
            else:
                i += x
                x = 0
                if i >= m or word[i] != abbr[j]:
                    return False
                i += 1
            j += 1
        return i + x == m and j == n
```

```java
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int m = word.length(), n = abbr.length();
        int i = 0, j = 0, x = 0;
        for (; i < m && j < n; ++j) {
            char c = abbr.charAt(j);
            if (Character.isDigit(c)) {
                if (c == '0' && x == 0) {
                    return false;
                }
                x = x * 10 + (c - '0');
            } else {
                i += x;
                x = 0;
                if (i >= m || word.charAt(i) != c) {
                    return false;
                }
                ++i;
            }
        }
        return i + x == m && j == n;
    }
}
```

```cpp
class Solution {
public:
    bool validWordAbbreviation(string word, string abbr) {
        int m = word.size(), n = abbr.size();
        int i = 0, j = 0, x = 0;
        for (; i < m && j < n; ++j) {
            if (isdigit(abbr[j])) {
                if (abbr[j] == '0' && x == 0) {
                    return false;
                }
                x = x * 10 + (abbr[j] - '0');
            } else {
                i += x;
                x = 0;
                if (i >= m || word[i] != abbr[j]) {
                    return false;
                }
                ++i;
            }
        }
        return i + x == m && j == n;
    }
};
```

```go
func validWordAbbreviation(word string, abbr string) bool {
	m, n := len(word), len(abbr)
	i, j, x := 0, 0, 0
	for ; i < m && j < n; j++ {
		if abbr[j] >= '0' && abbr[j] <= '9' {
			if x == 0 && abbr[j] == '0' {
				return false
			}
			x = x*10 + int(abbr[j]-'0')
		} else {
			i += x
			x = 0
			if i >= m || word[i] != abbr[j] {
				return false
			}
			i++
		}
	}
	return i+x == m && j == n
}
```

```ts
function validWordAbbreviation(word: string, abbr: string): boolean {
    const [m, n] = [word.length, abbr.length];
    let [i, j, x] = [0, 0, 0];
    for (; i < m && j < n; ++j) {
        if (abbr[j] >= '0' && abbr[j] <= '9') {
            if (abbr[j] === '0' && x === 0) {
                return false;
            }
            x = x * 10 + Number(abbr[j]);
        } else {
            i += x;
            x = 0;
            if (i >= m || word[i++] !== abbr[j]) {
                return false;
            }
        }
    }
    return i + x === m && j === n;
}
```

<!-- tabs:end -->

<!-- end -->
