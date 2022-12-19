# [2506. 统计相似字符串对的数目](https://leetcode.cn/problems/count-pairs-of-similar-strings)

[English Version](/solution/2500-2599/2506.Count%20Pairs%20Of%20Similar%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串数组 <code>words</code> 。</p>

<p>如果两个字符串由相同的字符组成，则认为这两个字符串 <strong>相似</strong> 。</p>

<ul>
	<li>例如，<code>"abca"</code> 和 <code>"cba"</code> 相似，因为它们都由字符 <code>'a'</code>、<code>'b'</code>、<code>'c'</code> 组成。</li>
	<li>然而，<code>"abacba"</code> 和 <code>"bcfd"</code> 不相似，因为它们不是相同字符组成的。</li>
</ul>

<p>请你找出满足字符串&nbsp;<code>words[i]</code><em> </em>和<em> </em><code>words[j]</code> 相似的下标对<em> </em><code>(i, j)</code><em> </em>，并返回下标对的数目，其中 <code>0 &lt;= i &lt; j &lt;= word.length - 1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = ["aba","aabb","abcd","bac","aabc"]
<strong>输出：</strong>2
<strong>解释：</strong>共有 2 对满足条件：
- i = 0 且 j = 1 ：words[0] 和 words[1] 只由字符 'a' 和 'b' 组成。 
- i = 3 且 j = 4 ：words[3] 和 words[4] 只由字符 'a'、'b' 和 'c' 。 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = ["aabb","ab","ba"]
<strong>输出：</strong>3
<strong>解释：</strong>共有 3 对满足条件：
- i = 0 且 j = 1 ：words[0] 和 words[1] 只由字符 'a' 和 'b' 组成。 
- i = 0 且 j = 2 ：words[0] 和 words[2] 只由字符 'a' 和 'b' 组成。 
- i = 1 且 j = 2 ：words[1] 和 words[2] 只由字符 'a' 和 'b' 组成。 
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>words = ["nba","cba","dba"]
<strong>输出：</strong>0
<strong>解释：</strong>不存在满足条件的下标对，返回 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 位运算**

对于每个字符串，我们可以将其转换为一个长度为 $26$ 的二进制数，其中第 $i$ 位为 $1$ 表示该字符串中包含第 $i$ 个字母。

如果两个字符串包含相同的字母，则它们的二进制数是相同的，因此，对于每个字符串，我们用哈希表统计其二进制数出现的次数，每一次累加到答案中，再将其二进制数出现的次数加 $1$。

时间复杂度 $O(L)$，空间复杂度 $O(n)$。其中 $L$ 是所有字符串的长度之和，而 $n$ 是字符串的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def similarPairs(self, words: List[str]) -> int:
        ans = 0
        cnt = Counter()
        for w in words:
            v = 0
            for c in w:
                v |= 1 << (ord(c) - ord("A"))
            ans += cnt[v]
            cnt[v] += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int similarPairs(String[] words) {
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var w : words) {
            int v = 0;
            for (int i = 0; i < w.length(); ++i) {
                v |= 1 << (w.charAt(i) - 'a');
            }
            ans += cnt.getOrDefault(v, 0);
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int similarPairs(vector<string>& words) {
        int ans = 0;
        unordered_map<int, int> cnt;
        for (auto& w : words) {
            int v = 0;
            for (auto& c : w) v |= 1 << c - 'a';
            ans += cnt[v];
            cnt[v]++;
        }
        return ans;
    }
};
```

### **Go**

```go
func similarPairs(words []string) (ans int) {
	cnt := map[int]int{}
	for _, w := range words {
		v := 0
		for _, c := range w {
			v |= 1 << (c - 'a')
		}
		ans += cnt[v]
		cnt[v]++
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
