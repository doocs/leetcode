# [剑指 Offer II 034. 外星语言是否排序](https://leetcode.cn/problems/lwyVBB)

## 题目描述

<!-- 这里写题目描述 -->

<p>某种外星语也使用英文小写字母，但可能顺序 <code>order</code> 不同。字母表的顺序（<code>order</code>）是一些小写字母的排列。</p>

<p>给定一组用外星语书写的单词 <code>words</code>，以及其字母表的顺序 <code>order</code>，只有当给定的单词在这种外星语中按字典序排列时，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;hello&quot;,&quot;leetcode&quot;], order = &quot;hlabcdefgijkmnopqrstuvwxyz&quot;
<strong>输出：</strong>true
<strong>解释：</strong>在该语言的字母表中，&#39;h&#39; 位于 &#39;l&#39; 之前，所以单词序列是按字典序排列的。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;word&quot;,&quot;world&quot;,&quot;row&quot;], order = &quot;worldabcefghijkmnpqstuvxyz&quot;
<strong>输出：</strong>false
<strong>解释：</strong>在该语言的字母表中，&#39;d&#39; 位于 &#39;l&#39; 之后，那么 words[0] &gt; words[1]，因此单词序列不是按字典序排列的。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;apple&quot;,&quot;app&quot;], order = &quot;abcdefghijklmnopqrstuvwxyz&quot;
<strong>输出：</strong>false
<strong>解释：</strong>当前三个字符 &quot;app&quot; 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 &quot;apple&quot; &gt; &quot;app&quot;，因为 &#39;l&#39; &gt; &#39;&empty;&#39;，其中 &#39;&empty;&#39; 是空白字符，定义为比任何其他字符都小（<a href="https://baike.baidu.com/item/%E5%AD%97%E5%85%B8%E5%BA%8F" target="_blank">更多信息</a>）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>order.length == 26</code></li>
	<li>在&nbsp;<code>words[i]</code>&nbsp;和&nbsp;<code>order</code>&nbsp;中的所有字符都是英文小写字母。</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 953&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/verifying-an-alien-dictionary/">https://leetcode.cn/problems/verifying-an-alien-dictionary/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

用数组或哈希表存放字母顺序。依次遍历单词列表，检测相邻两单词是否满足字典序。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        index = {c: i for i, c in enumerate(order)}
        for i in range(len(words) - 1):
            w1, w2 = words[i], words[i + 1]
            l1, l2 = len(w1), len(w2)
            flag = False
            for j in range(max(l1, l2)):
                i1, i2 = (
                    -1 if j >= l1 else index[w1[j]],
                    -1 if j >= l2 else index[w2[j]],
                )
                if i1 > i2:
                    # 说明不是按字典序排序，直接返回False
                    return False
                if i1 < i2:
                    # 说明当前两单词是按字典序排序，无需再往下进行循环比较
                    break
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < index.length; ++i) {
            index[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; ++i) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int l1 = w1.length(), l2 = w2.length();
            for (int j = 0; j < Math.max(l1, l2); ++j) {
                int i1 = j >= l1 ? -1 : index[w1.charAt(j) - 'a'];
                int i2 = j >= l2 ? -1 : index[w2.charAt(j) - 'a'];
                if (i1 > i2) {
                    // 说明不是按字典序排序，直接返回False
                    return false;
                }
                if (i1 < i2) {
                    // 说明当前两单词是按字典序排序，无需再往下进行循环比较
                    break;
                }
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function isAlienSorted(words: string[], order: string): boolean {
    let charMap = new Map();
    for (let i = 0; i < order.length; i++) {
        charMap.set(order[i], i);
    }
    function compare(str1: string, str2: string): boolean {
        const n = Math.min(str1.length, str2.length);
        for (let i = 0; i < n; i++) {
            let k1 = str1[i],
                k2 = str2[i];
            if (k1 != k2) return charMap.get(k1) < charMap.get(k2);
        }
        return n == str1.length;
    }
    for (let i = 1; i < words.length; i++) {
        if (!compare(words[i - 1], words[i])) return false;
    }
    return true;
}
```

### **C++**

```cpp
class Solution {
public:
    bool isAlienSorted(vector<string>& words, string order) {
        vector<int> index(26);
        for (int i = 0; i < index.size(); ++i)
            index[order[i] - 'a'] = i;
        for (int i = 0; i < words.size() - 1; ++i) {
            string w1 = words[i];
            string w2 = words[i + 1];
            int l1 = w1.size(), l2 = w2.size();
            for (int j = 0; j < max(l1, l2); ++j) {
                int i1 = j >= l1 ? -1 : index[w1[j] - 'a'];
                int i2 = j >= l2 ? -1 : index[w2[j] - 'a'];
                if (i1 > i2)
                    return false;
                if (i1 < i2)
                    break;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func isAlienSorted(words []string, order string) bool {
	index := make(map[byte]int)
	for i := range order {
		index[order[i]] = i
	}
	for i := 0; i < len(words)-1; i++ {
		w1, w2 := words[i], words[i+1]
		l1, l2 := len(w1), len(w2)
		flag := true
		for j := 0; j < min(l1, l2) && flag; j++ {
			i1, i2 := index[w1[j]], index[w2[j]]
			if i1 > i2 {
				return false
			}
			if i1 < i2 {
				flag = false
			}
		}
		if flag && l1 > l2 {
			return false
		}
	}
	return true
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
