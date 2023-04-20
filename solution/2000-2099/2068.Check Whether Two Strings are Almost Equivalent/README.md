# [2068. 检查两个字符串是否几乎相等](https://leetcode.cn/problems/check-whether-two-strings-are-almost-equivalent)

[English Version](/solution/2000-2099/2068.Check%20Whether%20Two%20Strings%20are%20Almost%20Equivalent/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果两个字符串 <code>word1</code>&nbsp;和 <code>word2</code>&nbsp;中从 <code>'a'</code>&nbsp;到 <code>'z'</code>&nbsp;每一个字母出现频率之差都 <strong>不超过</strong>&nbsp;<code>3</code>&nbsp;，那么我们称这两个字符串&nbsp;<code>word1</code> 和&nbsp;<code>word2</code> <strong>几乎相等</strong>&nbsp;。</p>

<p>给你两个长度都为&nbsp;<code>n</code>&nbsp;的字符串&nbsp;<code>word1</code> 和&nbsp;<code>word2</code>&nbsp;，如果&nbsp;<code>word1</code>&nbsp;和&nbsp;<code>word2</code>&nbsp;<strong>几乎相等</strong>&nbsp;，请你返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>一个字母 <code>x</code>&nbsp;的出现 <strong>频率</strong>&nbsp;指的是它在字符串中出现的次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>word1 = "aaaa", word2 = "bccb"
<b>输出：</b>false
<b>解释：</b>字符串 "aaaa" 中有 4 个 'a' ，但是 "bccb" 中有 0 个 'a' 。
两者之差为 4 ，大于上限 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>word1 = "abcdeef", word2 = "abaaacc"
<b>输出：</b>true
<b>解释：</b>word1 和 word2 中每个字母出现频率之差至多为 3 ：
- 'a' 在 word1 中出现了 1 次，在 word2 中出现了 4 次，差为 3 。
- 'b' 在 word1 中出现了 1 次，在 word2 中出现了 1 次，差为 0 。
- 'c' 在 word1 中出现了 1 次，在 word2 中出现了 2 次，差为 1 。
- 'd' 在 word1 中出现了 1 次，在 word2 中出现了 0 次，差为 1 。
- 'e' 在 word1 中出现了 2 次，在 word2 中出现了 0 次，差为 2 。
- 'f' 在 word1 中出现了 1 次，在 word2 中出现了 0 次，差为 1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>word1 = "cccddabba", word2 = "babababab"
<b>输出：</b>true
<b>解释：</b>word1 和 word2 中每个字母出现频率之差至多为 3 ：
- 'a' 在 word1 中出现了 2 次，在 word2 中出现了 4 次，差为 2 。
- 'b' 在 word1 中出现了 2 次，在 word2 中出现了 5 次，差为 3 。
- 'c' 在 word1 中出现了 3 次，在 word2 中出现了 0 次，差为 3 。
- 'd' 在 word1 中出现了 2 次，在 word2 中出现了 0 次，差为 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == word1.length == word2.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>word1</code> 和&nbsp;<code>word2</code>&nbsp;都只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

我们可以创建一个长度为 $26$ 的数组 $cnt$，记录两个字符串中每个字母出现的次数之差。最后遍历 $cnt$，如果有任意一个字母出现的次数之差大于 $3$，则返回 `false`，否则返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是字符串的长度；而 $C$ 是字符集的大小，本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkAlmostEquivalent(self, word1: str, word2: str) -> bool:
        cnt = Counter(word1)
        for c in word2:
            cnt[c] -= 1
        return all(abs(x) <= 3 for x in cnt.values())
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] cnt = new int[26];
        for (int i = 0; i < word1.length(); ++i) {
            ++cnt[word1.charAt(i) - 'a'];
        }
        for (int i = 0; i < word2.length(); ++i) {
            --cnt[word2.charAt(i) - 'a'];
        }
        for (int x : cnt) {
            if (Math.abs(x) > 3) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkAlmostEquivalent(string word1, string word2) {
        int cnt[26]{};
        for (char& c : word1) {
            ++cnt[c - 'a'];
        }
        for (char& c : word2) {
            --cnt[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (abs(cnt[i]) > 3) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkAlmostEquivalent(word1 string, word2 string) bool {
	cnt := [26]int{}
	for _, c := range word1 {
		cnt[c-'a']++
	}
	for _, c := range word2 {
		cnt[c-'a']--
	}
	for _, x := range cnt {
		if x > 3 || x < -3 {
			return false
		}
	}
	return true
}
```

### **TypeScript**

```ts
function checkAlmostEquivalent(word1: string, word2: string): boolean {
    const cnt: number[] = new Array(26).fill(0);
    for (const c of word1) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    for (const c of word2) {
        --cnt[c.charCodeAt(0) - 97];
    }
    return cnt.every(x => Math.abs(x) <= 3);
}
```

### **C#**

```cs
public class Solution {
    public bool CheckAlmostEquivalent(string word1, string word2) {
        int[] cnt = new int[26];
        foreach (var c in word1) {
            cnt[c - 'a']++;
        }
        foreach (var c in word2) {
            cnt[c - 'a']--;
        }
        return cnt.All(x => Math.Abs(x) <= 3);
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String $word1
     * @param String $word2
     * @return Boolean
     */
    function checkAlmostEquivalent($word1, $word2) {
        for ($i = 0; $i < strlen($word1); $i++) {
            $hashtable[$word1[$i]] += 1;
            $hashtable[$word2[$i]] -= 1;
        }
        $keys = array_keys($hashtable);
        for ($j = 0; $j < count($keys); $j++) {
            if (abs($hashtable[$keys[$j]]) > 3) return false;
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
