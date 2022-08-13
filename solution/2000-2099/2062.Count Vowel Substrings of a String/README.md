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

**方法一：暴力枚举**

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
    const n = word.length;
    let left = 0,
        right = 0;
    let ans = 0;
    while (right < n) {
        if (!isVowel(word.charAt(right))) {
            // 移动左指针
            left = right + 1;
        } else {
            let cur = word.substring(left, right + 1).split('');
            while (cur.length > 0) {
                if (isValiedArr(cur)) {
                    ans++;
                }
                cur.shift();
            }
        }
        right++;
    }
    return ans;
}

function isVowel(char: string): boolean {
    return ['a', 'e', 'i', 'o', 'u'].includes(char);
}

function isValiedArr(arr: Array<string>): boolean {
    return new Set(arr).size == 5;
}
```

### **...**

```

```

<!-- tabs:end -->
