---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1941.Check%20if%20All%20Characters%20Have%20Equal%20Number%20of%20Occurrences/README.md
rating: 1242
source: 第 57 场双周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [1941. 检查是否所有字符出现次数相同](https://leetcode.cn/problems/check-if-all-characters-have-equal-number-of-occurrences)

[English Version](/solution/1900-1999/1941.Check%20if%20All%20Characters%20Have%20Equal%20Number%20of%20Occurrences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，如果 <code>s</code> 是一个 <strong>好</strong> 字符串，请你返回 <code>true</code> ，否则请返回 <code>false</code> 。</p>

<p>如果 <code>s</code> 中出现过的 <strong>所有</strong> 字符的出现次数 <strong>相同</strong> ，那么我们称字符串 <code>s</code> 是 <strong>好</strong> 字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "abacbc"
<b>输出：</b>true
<b>解释：</b>s 中出现过的字符为 'a'，'b' 和 'c' 。s 中所有字符均出现 2 次。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "aaabb"
<b>输出：</b>false
<b>解释：</b>s 中出现过的字符为 'a' 和 'b' 。
'a' 出现了 3 次，'b' 出现了 2 次，两者出现次数不同。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们用一个哈希表或者一个长度为 $26$ 的数组 $\textit{cnt}$ 记录字符串 $s$ 中每个字符出现的次数。

接下来遍历 $\textit{cnt}$ 中的每个值，判断所有非零值是否相等即可。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 是字符串 $s$ 的长度；而 $\Sigma$ 是字符集大小，本题中字符集为小写英文字母，因此 $|\Sigma|=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def areOccurrencesEqual(self, s: str) -> bool:
        return len(set(Counter(s).values())) == 1
```

#### Java

```java
class Solution {
    public boolean areOccurrencesEqual(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int v = 0;
        for (int x : cnt) {
            if (x == 0) {
                continue;
            }
            if (v > 0 && v != x) {
                return false;
            }
            v = x;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool areOccurrencesEqual(string s) {
        vector<int> cnt(26);
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        int v = 0;
        for (int x : cnt) {
            if (x == 0) {
                continue;
            }
            if (v && v != x) {
                return false;
            }
            v = x;
        }
        return true;
    }
};
```

#### Go

```go
func areOccurrencesEqual(s string) bool {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	v := 0
	for _, x := range cnt {
		if x == 0 {
			continue
		}
		if v > 0 && v != x {
			return false
		}
		v = x
	}
	return true
}
```

#### TypeScript

```ts
function areOccurrencesEqual(s: string): boolean {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    const v = cnt.find(v => v);
    return cnt.every(x => !x || v === x);
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function areOccurrencesEqual($s) {
        $cnt = array_fill(0, 26, 0);
        for ($i = 0; $i < strlen($s); $i++) {
            $cnt[ord($s[$i]) - ord('a')]++;
        }
        $v = 0;
        foreach ($cnt as $x) {
            if ($x == 0) {
                continue;
            }
            if ($v && $v != $x) {
                return false;
            }
            $v = $x;
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
