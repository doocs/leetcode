---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1704.Determine%20if%20String%20Halves%20Are%20Alike/README.md
rating: 1207
source: 第 221 场周赛 Q1
tags:
    - 字符串
    - 计数
---

<!-- problem:start -->

# [1704. 判断字符串的两半是否相似](https://leetcode.cn/problems/determine-if-string-halves-are-alike)

[English Version](/solution/1700-1799/1704.Determine%20if%20String%20Halves%20Are%20Alike/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个偶数长度的字符串 <code>s</code> 。将其拆分成长度相同的两半，前一半为 <code>a</code> ，后一半为 <code>b</code> 。</p>

<p>两个字符串 <strong>相似</strong> 的前提是它们都含有相同数目的元音（<code>'a'</code>，<code>'e'</code>，<code>'i'</code>，<code>'o'</code>，<code>'u'</code>，<code>'A'</code>，<code>'E'</code>，<code>'I'</code>，<code>'O'</code>，<code>'U'</code>）。注意，<code>s</code> 可能同时含有大写和小写字母。</p>

<p>如果<em> </em><code>a</code><em> </em>和<em> </em><code>b</code> 相似，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "book"
<strong>输出：</strong>true
<strong>解释：</strong>a = "b<strong>o</strong>" 且 b = "<strong>o</strong>k" 。a 中有 1 个元音，b 也有 1 个元音。所以，a 和 b 相似。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "textbook"
<strong>输出：</strong>false
<strong>解释：</strong>a = "t<strong>e</strong>xt" 且 b = "b<strong>oo</strong>k" 。a 中有 1 个元音，b 中有 2 个元音。因此，a 和 b 不相似。
注意，元音 o 在 b 中出现两次，记为 2 个。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s.length</code> 是偶数</li>
	<li><code>s</code> 由 <strong>大写和小写</strong> 字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

遍历字符串，若字符串前半段的元音个数等于后半段的元音个数，则返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。空间复杂度 $O(C)$，其中 $C$ 为元音字母的个数。

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
