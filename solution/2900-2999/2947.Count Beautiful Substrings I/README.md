# [2947. 统计美丽子字符串 I](https://leetcode.cn/problems/count-beautiful-substrings-i)

[English Version](/solution/2900-2999/2947.Count%20Beautiful%20Substrings%20I/README_EN.md)

<!-- tags:哈希表,数学,字符串,枚举,数论,前缀和 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个正整数 <code>k</code> 。</p>

<p>用 <code>vowels</code> 和 <code>consonants</code> 分别表示字符串中元音字母和辅音字母的数量。</p>

<p>如果某个字符串满足以下条件，则称其为 <strong>美丽字符串</strong> ：</p>

<ul>
	<li><code>vowels == consonants</code>，即元音字母和辅音字母的数量相等。</li>
	<li><code>(vowels * consonants) % k == 0</code>，即元音字母和辅音字母的数量的乘积能被 <code>k</code> 整除。</li>
</ul>

<p>返回字符串 <code>s</code> 中 <strong>非空美丽子字符串</strong> 的数量。</p>

<p>子字符串是字符串中的一个连续字符序列。</p>

<p>英语中的<strong> 元音字母 </strong>为 <code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code> 和 <code>'u'</code> 。</p>

<p>英语中的<strong> 辅音字母 </strong>为除了元音字母之外的所有字母。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "baeyh", k = 2
<strong>输出：</strong>2
<strong>解释：</strong>字符串 s 中有 2 个美丽子字符串。
- 子字符串 "b<em><strong>aeyh</strong></em>"，vowels = 2（["a","e"]），consonants = 2（["y","h"]）。
可以看出字符串 "aeyh" 是美丽字符串，因为 vowels == consonants 且 vowels * consonants % k == 0 。
- 子字符串 "<em><strong>baey</strong></em>h"，vowels = 2（["a","e"]），consonants = 2（["b","y"]）。
可以看出字符串 "baey" 是美丽字符串，因为 vowels == consonants 且 vowels * consonants % k == 0 。
可以证明字符串 s 中只有 2 个美丽子字符串。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abba", k = 1
<strong>输出：</strong>3
<strong>解释：</strong>字符串 s 中有 3 个美丽子字符串。
- 子字符串 "<strong><em>ab</em></strong>ba"，vowels = 1（["a"]），consonants = 1（["b"]）。
- 子字符串 "ab<strong><em>ba</em></strong>"，vowels = 1（["a"]），consonants = 1（["b"]）。
- 子字符串 "<em><strong>abba</strong></em>"，vowels = 2（["a","a"]），consonants = 2（["b","b"]）。
可以证明字符串 s 中只有 3 个美丽子字符串。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "bcdf", k = 1
<strong>输出：</strong>0
<strong>解释：</strong>字符串 s 中没有美丽子字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def beautifulSubstrings(self, s: str, k: int) -> int:
        n = len(s)
        vs = set("aeiou")
        ans = 0
        for i in range(n):
            vowels = 0
            for j in range(i, n):
                vowels += s[j] in vs
                consonants = j - i + 1 - vowels
                if vowels == consonants and vowels * consonants % k == 0:
                    ans += 1
        return ans
```

```java
class Solution {
    public int beautifulSubstrings(String s, int k) {
        int n = s.length();
        int[] vs = new int[26];
        for (char c : "aeiou".toCharArray()) {
            vs[c - 'a'] = 1;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int vowels = 0;
            for (int j = i; j < n; ++j) {
                vowels += vs[s.charAt(j) - 'a'];
                int consonants = j - i + 1 - vowels;
                if (vowels == consonants && vowels * consonants % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int beautifulSubstrings(string s, int k) {
        int n = s.size();
        int vs[26]{};
        string t = "aeiou";
        for (char c : t) {
            vs[c - 'a'] = 1;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int vowels = 0;
            for (int j = i; j < n; ++j) {
                vowels += vs[s[j] - 'a'];
                int consonants = j - i + 1 - vowels;
                if (vowels == consonants && vowels * consonants % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

```go
func beautifulSubstrings(s string, k int) (ans int) {
	n := len(s)
	vs := [26]int{}
	for _, c := range "aeiou" {
		vs[c-'a'] = 1
	}
	for i := 0; i < n; i++ {
		vowels := 0
		for j := i; j < n; j++ {
			vowels += vs[s[j]-'a']
			consonants := j - i + 1 - vowels
			if vowels == consonants && vowels*consonants%k == 0 {
				ans++
			}
		}
	}
	return
}
```

```ts
function beautifulSubstrings(s: string, k: number): number {
    const n = s.length;
    const vs: number[] = Array(26).fill(0);
    for (const c of 'aeiou') {
        vs[c.charCodeAt(0) - 'a'.charCodeAt(0)] = 1;
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let vowels = 0;
        for (let j = i; j < n; ++j) {
            vowels += vs[s.charCodeAt(j) - 'a'.charCodeAt(0)];
            const consonants = j - i + 1 - vowels;
            if (vowels === consonants && (vowels * consonants) % k === 0) {
                ++ans;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
