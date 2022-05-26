# [2185. 统计包含给定前缀的字符串](https://leetcode.cn/problems/counting-words-with-a-given-prefix)

[English Version](/solution/2100-2199/2185.Counting%20Words%20With%20a%20Given%20Prefix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>words</code> 和一个字符串 <code>pref</code> 。</p>

<p>返回 <code>words</code><em> </em>中以 <code>pref</code> 作为 <strong>前缀</strong> 的字符串的数目。</p>

<p>字符串 <code>s</code> 的 <strong>前缀</strong> 就是&nbsp; <code>s</code> 的任一前导连续字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = ["pay","<em><strong>at</strong></em>tention","practice","<em><strong>at</strong></em>tend"], <code>pref </code>= "at"
<strong>输出：</strong>2
<strong>解释：</strong>以 "at" 作为前缀的字符串有两个，分别是："<em><strong>at</strong></em>tention" 和 "<em><strong>at</strong></em>tend" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = ["leetcode","win","loops","success"], <code>pref </code>= "code"
<strong>输出：</strong>0
<strong>解释：</strong>不存在以 "code" 作为前缀的字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length, pref.length &lt;= 100</code></li>
	<li><code>words[i]</code> 和 <code>pref</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def prefixCount(self, words: List[str], pref: str) -> int:
        return sum(w.startswith(pref) for w in words)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int prefixCount(String[] words, String pref) {
        int ans = 0;
        for (String w : words) {
            if (w.startsWith(pref)) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function prefixCount(words: string[], pref: string): number {
    const n = pref.length;
    let ans = 0;
    for (let str of words) {
        if (str.substring(0, n) == pref) {
            ans++;
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int prefixCount(vector<string>& words, string pref) {
        int ans = 0;
        for (auto& w : words)
            if (w.find(pref) == 0)
                ++ans;
        return ans;
    }
};
```

### **Go**

```go
func prefixCount(words []string, pref string) int {
	ans := 0
	for _, w := range words {
		if strings.HasPrefix(w, pref) {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
