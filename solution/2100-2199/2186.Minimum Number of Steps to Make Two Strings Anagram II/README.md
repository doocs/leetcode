# [2186. 使两字符串互为字母异位词的最少步骤数](https://leetcode.cn/problems/minimum-number-of-steps-to-make-two-strings-anagram-ii)

[English Version](/solution/2100-2199/2186.Minimum%20Number%20of%20Steps%20to%20Make%20Two%20Strings%20Anagram%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串 <code>s</code> 和 <code>t</code> 。在一步操作中，你可以给 <code>s</code> 或者 <code>t</code> 追加 <strong>任一字符</strong> 。</p>

<p>返回使 <code>s</code> 和 <code>t</code> 互为 <strong>字母异位词</strong> 所需的最少步骤数<em>。</em></p>

<p><strong>字母异位词 </strong>指字母相同但是顺序不同（或者相同）的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "<em><strong>lee</strong>t</em>co<em><strong>de</strong></em>", t = "co<em><strong>a</strong></em>t<em><strong>s</strong></em>"
<strong>输出：</strong>7
<strong>解释：</strong>
- 执行 2 步操作，将 "as" 追加到 s = "leetcode" 中，得到 s = "leetcode<em><strong>as</strong></em>" 。
- 执行 5 步操作，将 "leede" 追加到 t = "coats" 中，得到 t = "coats<em><strong>leede</strong></em>" 。
"leetcodeas" 和 "coatsleede" 互为字母异位词。
总共用去 2 + 5 = 7 步。
可以证明，无法用少于 7 步操作使这两个字符串互为字母异位词。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "night", t = "thing"
<strong>输出：</strong>0
<strong>解释：</strong>给出的字符串已经互为字母异位词。因此，不需要任何进一步操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>t</code> 由小写英文字符组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSteps(self, s: str, t: str) -> int:
        cnt = Counter(s)
        for c in t:
            cnt[c] -= 1
        return sum(abs(v) for v in cnt.values())
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSteps(String s, String t) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        for (char c : t.toCharArray()) {
            --cnt[c - 'a'];
        }
        int ans = 0;
        for (int v : cnt) {
            ans += Math.abs(v);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function minSteps(s: string, t: string): number {
    let cnt = new Array(128).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0)];
    }
    for (const c of t) {
        --cnt[c.charCodeAt(0)];
    }
    let ans = 0;
    for (const v of cnt) {
        ans += Math.abs(v);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int minSteps(string s, string t) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        for (char& c : t) --cnt[c - 'a'];
        int ans = 0;
        for (int& v : cnt) ans += abs(v);
        return ans;
    }
};
```

### **Go**

```go
func minSteps(s string, t string) int {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	for _, c := range t {
		cnt[c-'a']--
	}
	ans := 0
	for _, v := range cnt {
		ans += abs(v)
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @param {string} t
 * @return {number}
 */
var minSteps = function (s, t) {
    let cnt = new Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt() - 'a'.charCodeAt()];
    }
    for (const c of t) {
        --cnt[c.charCodeAt() - 'a'.charCodeAt()];
    }
    let ans = 0;
    for (const v of cnt) {
        ans += Math.abs(v);
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
