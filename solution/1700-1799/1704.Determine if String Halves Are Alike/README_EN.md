---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1704.Determine%20if%20String%20Halves%20Are%20Alike/README_EN.md
rating: 1207
source: Weekly Contest 221 Q1
tags:
    - String
    - Counting
---

<!-- problem:start -->

# [1704. Determine if String Halves Are Alike](https://leetcode.com/problems/determine-if-string-halves-are-alike)

[中文文档](/solution/1700-1799/1704.Determine%20if%20String%20Halves%20Are%20Alike/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of even length. Split this string into two halves of equal lengths, and let <code>a</code> be the first half and <code>b</code> be the second half.</p>

<p>Two strings are <strong>alike</strong> if they have the same number of vowels (<code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;u&#39;</code>, <code>&#39;A&#39;</code>, <code>&#39;E&#39;</code>, <code>&#39;I&#39;</code>, <code>&#39;O&#39;</code>, <code>&#39;U&#39;</code>). Notice that <code>s</code> contains uppercase and lowercase letters.</p>

<p>Return <code>true</code><em> if </em><code>a</code><em> and </em><code>b</code><em> are <strong>alike</strong></em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;book&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> a = &quot;b<u>o</u>&quot; and b = &quot;<u>o</u>k&quot;. a has 1 vowel and b has 1 vowel. Therefore, they are alike.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;textbook&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> a = &quot;t<u>e</u>xt&quot; and b = &quot;b<u>oo</u>k&quot;. a has 1 vowel whereas b has 2. Therefore, they are not alike.
Notice that the vowel o is counted twice.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s.length</code> is even.</li>
	<li><code>s</code> consists of <strong>uppercase and lowercase</strong> letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

Traverse the string. If the number of vowels in the first half of the string is equal to the number of vowels in the second half, return `true`. Otherwise, return `false`.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(C)$, where $C$ is the number of vowel characters.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def halvesAreAlike(self, s: str) -> bool:
        cnt, n = 0, len(s) >> 1
        vowels = set('aeiouAEIOU')
        for i in range(n):
            cnt += s[i] in vowels
            cnt -= s[i + n] in vowels
        return cnt == 0
```

#### Java

```java
class Solution {
    public boolean halvesAreAlike(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        int n = s.length() >> 1;
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            cnt += vowels.contains(s.charAt(i)) ? 1 : 0;
            cnt -= vowels.contains(s.charAt(i + n)) ? 1 : 0;
        }
        return cnt == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool halvesAreAlike(string s) {
        unordered_set<char> vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        int cnt = 0, n = s.size() / 2;
        for (int i = 0; i < n; ++i) {
            cnt += vowels.count(s[i]);
            cnt -= vowels.count(s[i + n]);
        }
        return cnt == 0;
    }
};
```

#### Go

```go
func halvesAreAlike(s string) bool {
	vowels := map[byte]bool{}
	for _, c := range "aeiouAEIOU" {
		vowels[byte(c)] = true
	}
	cnt, n := 0, len(s)>>1
	for i := 0; i < n; i++ {
		if vowels[s[i]] {
			cnt++
		}
		if vowels[s[i+n]] {
			cnt--
		}
	}
	return cnt == 0
}
```

#### TypeScript

```ts
function halvesAreAlike(s: string): boolean {
    const vowels = new Set('aeiouAEIOU'.split(''));
    let cnt = 0;
    const n = s.length >> 1;
    for (let i = 0; i < n; ++i) {
        cnt += vowels.has(s[i]) ? 1 : 0;
        cnt -= vowels.has(s[n + i]) ? 1 : 0;
    }
    return cnt === 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn halves_are_alike(s: String) -> bool {
        let n = s.len() / 2;
        let vowels: std::collections::HashSet<char> = "aeiouAEIOU".chars().collect();
        let mut cnt = 0;

        for i in 0..n {
            if vowels.contains(&s.chars().nth(i).unwrap()) {
                cnt += 1;
            }
            if vowels.contains(&s.chars().nth(i + n).unwrap()) {
                cnt -= 1;
            }
        }

        cnt == 0
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var halvesAreAlike = function (s) {
    const vowels = new Set('aeiouAEIOU'.split(''));
    let cnt = 0;
    const n = s.length >> 1;
    for (let i = 0; i < n; ++i) {
        cnt += vowels.has(s[i]);
        cnt -= vowels.has(s[n + i]);
    }
    return cnt === 0;
};
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function halvesAreAlike($s) {
        $n = strlen($s) / 2;
        $vowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'];
        $cnt = 0;

        for ($i = 0; $i < $n; $i++) {
            if (in_array($s[$i], $vowels)) {
                $cnt++;
            }
            if (in_array($s[$i + $n], $vowels)) {
                $cnt--;
            }
        }

        return $cnt == 0;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
