# [1456. 定长子串中元音的最大数目](https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length)

[English Version](/solution/1400-1499/1456.Maximum%20Number%20of%20Vowels%20in%20a%20Substring%20of%20Given%20Length/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你字符串 <code>s</code> 和整数 <code>k</code> 。</p>

<p>请返回字符串 <code>s</code> 中长度为 <code>k</code> 的单个子字符串中可能包含的最大元音字母数。</p>

<p>英文中的 <strong>元音字母 </strong>为（<code>a</code>, <code>e</code>, <code>i</code>, <code>o</code>, <code>u</code>）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;abciiidef&quot;, k = 3
<strong>输出：</strong>3
<strong>解释：</strong>子字符串 &quot;iii&quot; 包含 3 个元音字母。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;aeiou&quot;, k = 2
<strong>输出：</strong>2
<strong>解释：</strong>任意长度为 2 的子字符串都包含 2 个元音字母。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;leetcode&quot;, k = 3
<strong>输出：</strong>2
<strong>解释：</strong>&quot;lee&quot;、&quot;eet&quot; 和 &quot;ode&quot; 都包含 2 个元音字母。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;rhythms&quot;, k = 4
<strong>输出：</strong>0
<strong>解释：</strong>字符串 s 中不含任何元音字母。
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>s = &quot;tryhard&quot;, k = 4
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：滑动窗口**

找出所有窗口为 $k$ 中的元音字母数目，并记录最大值。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxVowels(self, s: str, k: int) -> int:
        vowels = set('aeiou')
        t = sum(c in vowels for c in s[:k])
        ans = t
        for i in range(k, len(s)):
            t += s[i] in vowels
            t -= s[i - k] in vowels
            ans = max(ans, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxVowels(String s, int k) {
        int t = 0, n = s.length();
        for (int i = 0; i < k; ++i) {
            if (isVowel(s.charAt(i))) {
                ++t;
            }
        }
        int ans = t;
        for (int i = k; i < n; ++i) {
            if (isVowel(s.charAt(i))) {
                ++t;
            }
            if (isVowel(s.charAt(i - k))) {
                --t;
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxVowels(string s, int k) {
        int t = 0, n = s.size();
        for (int i = 0; i < k; ++i) t += isVowel(s[i]);
        int ans = t;
        for (int i = k; i < n; ++i) {
            t += isVowel(s[i]);
            t -= isVowel(s[i - k]);
            ans = max(ans, t);
        }
        return ans;
    }

    bool isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
};
```

### **Go**

```go
func maxVowels(s string, k int) int {
	isVowel := func(c byte) bool {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
	}
	t := 0
	for i := 0; i < k; i++ {
		if isVowel(s[i]) {
			t++
		}
	}
	ans := t
	for i := k; i < len(s); i++ {
		if isVowel(s[i]) {
			t++
		}
		if isVowel(s[i-k]) {
			t--
		}
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxVowels(s: string, k: number): number {
    function isVowel(c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    let t = 0;
    for (let i = 0; i < k; ++i) {
        if (isVowel(s.charAt(i))) {
            t++;
        }
    }
    let ans = t;
    for (let i = k; i < s.length; ++i) {
        if (isVowel(s.charAt(i))) {
            t++;
        }
        if (isVowel(s.charAt(i - k))) {
            t--;
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
