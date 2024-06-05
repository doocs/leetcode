---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20032.%20%E6%9C%89%E6%95%88%E7%9A%84%E5%8F%98%E4%BD%8D%E8%AF%8D/README.md
---

<!-- problem:start -->

# [剑指 Offer II 032. 有效的变位词](https://leetcode.cn/problems/dKk3P7)

## 题目描述

<!-- description:start -->

<p>给定两个字符串 <code>s</code> 和 <code>t</code> ，编写一个函数来判断它们是不是一组变位词（字母异位词）。</p>

<p><strong>注意：</strong>若&nbsp;<code><em>s</em></code> 和 <code><em>t</em></code><em>&nbsp;</em>中每个字符出现的次数都相同且<strong>字符顺序不完全相同</strong>，则称&nbsp;<code><em>s</em></code> 和 <code><em>t</em></code><em>&nbsp;</em>互为变位词（字母异位词）。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;anagram&quot;, t = &quot;nagaram&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;rat&quot;, t = &quot;car&quot;
<strong>输出: </strong>false</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;a&quot;, t = &quot;a&quot;
<strong>输出: </strong>false</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;and&nbsp;<code>t</code>&nbsp;仅包含小写字母</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶:&nbsp;</strong>如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？</p>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 242&nbsp;题相似（字母异位词定义不同）：<a href="https://leetcode.cn/problems/valid-anagram/">https://leetcode.cn/problems/valid-anagram/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

如果字符串 $s$ 与字符串 $t$ 长度不相等，或者字符串 $s$ 与字符串 $t$ 完全相等，那么 $s$ 和 $t$ 一定不是变位词，返回 `false`。

否则，我们用一个长度为 $26$ 的数组 $cnt$ 维护字符串 $s$ 中每个字母出现的次数与字符串 $t$ 中每个字母出现的次数的差值。如果 $cnt$ 数组的所有元素都为 $0$，则 $s$ 和 $t$ 互为变位词，返回 `true`，否则返回 `false`。

时间复杂度 $O(m + n + |\Sigma|)$，空间复杂度 $O(|\Sigma|)$，其中 $m$ 和 $n$ 分别是字符串 $s$ 和 $t$ 的长度，而 $|\Sigma|$ 是字符集，在本题中字符集为所有小写字母，因此 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t) or s == t:
            return False
        return Counter(s) == Counter(t)
```

#### Java

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m != n || s.equals(t)) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < m; ++i) {
            ++cnt[s.charAt(i) - 'a'];
            --cnt[t.charAt(i) - 'a'];
        }
        for (int x : cnt) {
            if (x != 0) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isAnagram(string s, string t) {
        int m = s.size();
        int n = t.size();
        if (m != n || s == t) {
            return false;
        }
        vector<int> cnt(26);
        for (int i = 0; i < m; ++i) {
            ++cnt[s[i] - 'a'];
            --cnt[t[i] - 'a'];
        }
        for (int x : cnt) {
            if (x) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isAnagram(s string, t string) bool {
	m, n := len(s), len(t)
	if m != n || s == t {
		return false
	}
	cnt := [26]int{}
	for i, c := range s {
		cnt[c-'a']++
		cnt[t[i]-'a']--
	}
	for _, x := range cnt {
		if x != 0 {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isAnagram(s: string, t: string): boolean {
    const m = s.length;
    const n = t.length;
    if (m !== n || s === t) {
        return false;
    }
    const cnt: number[] = new Array(26).fill(0);
    for (let i = 0; i < m; ++i) {
        ++cnt[s[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        --cnt[t[i].charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    return cnt.every(x => x === 0);
}
```

#### Swift

```swift
class Solution {
    func isAnagram(_ s: String, _ t: String) -> Bool {
        let m = s.count
        let n = t.count
        if m != n || s == t {
            return false
        }
        
        var cnt = [Int](repeating: 0, count: 26)
        
        for (sc, tc) in zip(s, t) {
            cnt[Int(sc.asciiValue! - Character("a").asciiValue!)] += 1
            cnt[Int(tc.asciiValue! - Character("a").asciiValue!)] -= 1
        }
        
        for x in cnt {
            if x != 0 {
                return false
            }
        }
        
        return true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
