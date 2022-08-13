# [893. 特殊等价字符串组](https://leetcode.cn/problems/groups-of-special-equivalent-strings)

[English Version](/solution/0800-0899/0893.Groups%20of%20Special-Equivalent%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>words</code>。</p>

<p>一步操作中，你可以交换字符串 <code>words[i]</code> 的任意两个偶数下标对应的字符或任意两个奇数下标对应的字符。</p>

<p>对两个字符串&nbsp;<code>words[i]</code> 和 <code>words[j]</code> 而言，如果经过任意次数的操作，<code>words[i] == words[j]</code> ，那么这两个字符串是 <strong>特殊等价 </strong>的。</p>

<ul>
	<li>例如，<code>words[i] = "zzxy"</code> 和 <code>words[j] = "xyzz"</code> 是一对 <strong>特殊等价</strong> 字符串，因为可以按 <code>"zzxy" -&gt; "xzzy" -&gt; "xyzz"</code> 的操作路径使&nbsp;<code>words[i] == words[j]</code> 。</li>
</ul>

<p>现在规定，<strong><code>words</code> </strong>的 <strong>一组特殊等价字符串 </strong>就是 <code>words</code> 的一个同时满足下述条件的非空子集：</p>

<ul>
	<li>该组中的每一对字符串都是<strong> 特殊等价 </strong>的</li>
	<li>该组字符串已经涵盖了该类别中的所有特殊等价字符串，容量达到理论上的最大值（也就是说，如果一个字符串不在该组中，那么这个字符串就 <strong>不会</strong> 与该组内任何字符串特殊等价）</li>
</ul>

<p>返回 <code>words</code> 中 <strong>特殊等价字符串组</strong> 的数量。</p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["abcd","cdab","cbad","xyzz","zzxy","zzyx"]
<strong>输出：</strong>3
<strong>解释：</strong>
其中一组为 ["abcd", "cdab", "cbad"]，因为它们是成对的特殊等价字符串，且没有其他字符串与这些字符串特殊等价。
另外两组分别是 ["xyzz", "zzxy"] 和 ["zzyx"]。特别需要注意的是，"zzxy" 不与 "zzyx" 特殊等价。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["abc","acb","bac","bca","cab","cba"]
<strong>输出：</strong>3
<strong>解释：</strong>3 组 ["abc","cba"]，["acb","bca"]，["bac","cab"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li>所有 <code>words[i]</code>&nbsp;都只由小写字母组成。</li>
	<li>所有 <code>words[i]</code>&nbsp;都具有相同的长度。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numSpecialEquivGroups(self, words: List[str]) -> int:
        s = {''.join(sorted(word[::2]) + sorted(word[1::2])) for word in words}
        return len(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numSpecialEquivGroups(String[] words) {
        Set<String> s = new HashSet<>();
        for (String word : words) {
            s.add(convert(word));
        }
        return s.size();
    }

    private String convert(String word) {
        List<Character> a = new ArrayList<>();
        List<Character> b = new ArrayList<>();
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (i % 2 == 0) {
                a.add(ch);
            } else {
                b.add(ch);
            }
        }
        Collections.sort(a);
        Collections.sort(b);
        StringBuilder sb = new StringBuilder();
        for (char c : a) {
            sb.append(c);
        }
        for (char c : b) {
            sb.append(c);
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numSpecialEquivGroups(vector<string>& words) {
        unordered_set<string> s;
        for (auto& word : words) {
            string a = "", b = "";
            for (int i = 0; i < word.size(); ++i) {
                if (i & 1)
                    a += word[i];
                else
                    b += word[i];
            }
            sort(a.begin(), a.end());
            sort(b.begin(), b.end());
            s.insert(a + b);
        }
        return s.size();
    }
};
```

### **Go**

```go
func numSpecialEquivGroups(words []string) int {
	s := map[string]bool{}
	for _, word := range words {
		a, b := []rune{}, []rune{}
		for i, c := range word {
			if i&1 == 1 {
				a = append(a, c)
			} else {
				b = append(b, c)
			}
		}
		sort.Slice(a, func(i, j int) bool {
			return a[i] < a[j]
		})
		sort.Slice(b, func(i, j int) bool {
			return b[i] < b[j]
		})
		s[string(a)+string(b)] = true
	}
	return len(s)
}
```

### **...**

```

```

<!-- tabs:end -->
