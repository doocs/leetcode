# [1100. 长度为 K 的无重复字符子串](https://leetcode.cn/problems/find-k-length-substrings-with-no-repeated-characters)

[English Version](/solution/1100-1199/1100.Find%20K-Length%20Substrings%20With%20No%20Repeated%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>S</code>，找出所有长度为&nbsp;<code>K</code>&nbsp;且不含重复字符的子串，请你返回全部满足要求的子串的&nbsp;<strong>数目</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>S = &quot;havefunonleetcode&quot;, K = 5
<strong>输出：</strong>6
<strong>解释：</strong>
这里有 6 个满足题意的子串，分别是：&#39;havef&#39;,&#39;avefu&#39;,&#39;vefun&#39;,&#39;efuno&#39;,&#39;etcod&#39;,&#39;tcode&#39;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>S = &quot;home&quot;, K = 5
<strong>输出：</strong>0
<strong>解释：</strong>
注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 10^4</code></li>
	<li><code>S</code> 中的所有字符均为小写英文字母</li>
	<li><code>1 &lt;= K &lt;= 10^4</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

固定大小的滑动窗口。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numKLenSubstrNoRepeats(self, s: str, k: int) -> int:
        ans = j = 0
        mp = {}
        for i, c in enumerate(s):
            if c in mp:
                j = max(j, mp[c] + 1)
            mp[c] = i
            if i - j + 1 >= k:
                ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int ans = 0;
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0, j = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (mp.containsKey(c)) {
                j = Math.max(j, mp.get(c) + 1);
            }
            mp.put(c, i);
            if (i - j + 1 >= k) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numKLenSubstrNoRepeats(string s, int k) {
        int ans = 0;
        unordered_map<int, int> mp;
        for (int i = 0, j = 0; i < s.size(); ++i) {
            char c = s[i];
            if (mp.count(c)) j = max(j, mp[c] + 1);
            mp[c] = i;
            if (i - j + 1 >= k) ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func numKLenSubstrNoRepeats(s string, k int) int {
	mp := make(map[rune]int)
	ans, j := 0, 0
	for i, c := range s {
		if v, ok := mp[c]; ok {
			j = max(j, v+1)
		}
		mp[c] = i
		if i-j+1 >= k {
			ans++
		}
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

### **...**

```

```

<!-- tabs:end -->
