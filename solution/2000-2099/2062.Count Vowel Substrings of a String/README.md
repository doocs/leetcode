# [2062. 统计字符串中的元音子字符串](https://leetcode.cn/problems/count-vowel-substrings-of-a-string)

[English Version](/solution/2000-2099/2062.Count%20Vowel%20Substrings%20of%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>子字符串</strong> 是字符串中的一个连续（非空）的字符序列。</p>

<p><strong>元音子字符串</strong> 是 <strong>仅</strong> 由元音（<code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code> 和 <code>'u'</code>）组成的一个子字符串，且必须包含 <strong>全部五种</strong> 元音。</p>

<p>给你一个字符串 <code>word</code> ，统计并返回 <code>word</code> 中 <strong>元音子字符串的数目</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "aeiouu"
<strong>输出：</strong>2
<strong>解释：</strong>下面列出 word 中的元音子字符串（斜体加粗部分）：
- "<em><strong>aeiou</strong></em>u"
- "<strong><em>aeiouu</em></strong>"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "unicornarihan"
<strong>输出：</strong>0
<strong>解释：</strong>word 中不含 5 种元音，所以也不会存在元音子字符串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>word = "cuaieuouac"
<strong>输出：</strong>7
<strong>解释：</strong>下面列出 word 中的元音子字符串（斜体加粗部分）：
- "c<em><strong>uaieuo</strong></em>uac"
- "c<em><strong>uaieuou</strong></em>ac"
- "c<em><strong>uaieuoua</strong></em>c"
- "cu<em><strong>aieuo</strong></em>uac"
- "cu<em><strong>aieuou</strong></em>ac"
- "cu<em><strong>aieuoua</strong></em>c"
- "cua<em><strong>ieuoua</strong></em>c"</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>word = "bbaeixoubb"
<strong>输出：</strong>0
<strong>解释：</strong>所有包含全部五种元音的子字符串都含有辅音，所以不存在元音子字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举 + 哈希表**

我们可以枚举子字符串的左端点 $i$，对于当前左端点，维护一个哈希表，记录当前子字符串中出现的元音字母，然后枚举右端点 $j$，如果当前右端点对应的字母不是元音字母，则跳出循环，否则将当前右端点对应的字母加入哈希表，如果哈希表中的元素个数为 $5$，则说明当前子字符串是一个元音子字符串，将结果加 $1$。

时间复杂度 $O(n^2)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 $word$ 的长度；而 $C$ 为字符集大小，本题中 $C=5$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countVowelSubstrings(self, word: str) -> int:
        n = len(word)
        s = set('aeiou')
        return sum(set(word[i:j]) == s for i in range(n) for j in range(i + 1, n + 1))
```

```python
class Solution:
    def countVowelSubstrings(self, word: str) -> int:
        s = set('aeiou')
        ans, n = 0, len(word)
        for i in range(n):
            t = set()
            for c in word[i:]:
                if c not in s:
                    break
                t.add(c)
                ans += len(t) == 5
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countVowelSubstrings(String word) {
        int n = word.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            Set<Character> t = new HashSet<>();
            for (int j = i; j < n; ++j) {
                char c = word.charAt(j);
                if (!isVowel(c)) {
                    break;
                }
                t.add(c);
                if (t.size() == 5) {
                    ++ans;
                }
            }
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
    int countVowelSubstrings(string word) {
        int ans = 0;
        int n = word.size();
        for (int i = 0; i < n; ++i) {
            unordered_set<char> t;
            for (int j = i; j < n; ++j) {
                char c = word[j];
                if (!isVowel(c)) break;
                t.insert(c);
                ans += t.size() == 5;
            }
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
func countVowelSubstrings(word string) int {
	ans, n := 0, len(word)
	for i := range word {
		t := map[byte]bool{}
		for j := i; j < n; j++ {
			c := word[j]
			if !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				break
			}
			t[c] = true
			if len(t) == 5 {
				ans++
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function countVowelSubstrings(word: string): number {
    let ans = 0;
    const n = word.length;
    for (let i = 0; i < n; ++i) {
        const t = new Set<string>();
        for (let j = i; j < n; ++j) {
            const c = word[j];
            if (
                !(c === 'a' || c === 'e' || c === 'i' || c === 'o' || c === 'u')
            ) {
                break;
            }
            t.add(c);
            if (t.size === 5) {
                ans++;
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
