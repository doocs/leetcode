# [1525. 字符串的好分割数目](https://leetcode.cn/problems/number-of-good-ways-to-split-a-string)

[English Version](/solution/1500-1599/1525.Number%20of%20Good%20Ways%20to%20Split%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，一个分割被称为 「好分割」&nbsp;当它满足：将&nbsp;<code>s</code>&nbsp;分割成 2 个字符串&nbsp;<code>p</code> 和&nbsp;<code>q</code>&nbsp;，它们连接起来等于&nbsp;<code>s</code>&nbsp;且 <code>p</code>&nbsp;和 <code>q</code>&nbsp;中不同字符的数目相同。</p>

<p>请你返回 <code>s</code>&nbsp;中好分割的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;aacaba&quot;
<strong>输出：</strong>2
<strong>解释：</strong>总共有 5 种分割字符串 <code>&quot;aacaba&quot;</code> 的方法，其中 2 种是好分割。
(&quot;a&quot;, &quot;acaba&quot;) 左边字符串和右边字符串分别包含 1 个和 3 个不同的字符。
(&quot;aa&quot;, &quot;caba&quot;) 左边字符串和右边字符串分别包含 1 个和 3 个不同的字符。
(&quot;aac&quot;, &quot;aba&quot;) 左边字符串和右边字符串分别包含 2 个和 2 个不同的字符。这是一个好分割。
(&quot;aaca&quot;, &quot;ba&quot;) 左边字符串和右边字符串分别包含 2 个和 2 个不同的字符。这是一个好分割。
(&quot;aacab&quot;, &quot;a&quot;) 左边字符串和右边字符串分别包含 3 个和 1 个不同的字符。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;abcd&quot;
<strong>输出：</strong>1
<strong>解释：</strong>好分割为将字符串分割成 (&quot;ab&quot;, &quot;cd&quot;) 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;aaaaa&quot;
<strong>输出：</strong>4
<strong>解释：</strong>所有分割都是好分割。</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;acbadbaada&quot;
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们维护两个哈希表，分别记录左侧出现的字符、右侧的字符以及出现的次数。初始时，左侧的哈希表为空，右侧的哈希表为字符串 $s$ 中所有字符出现的次数。

接下来，我们从左到右遍历字符串 $s$，对于遍历到的字符 $c$，我们将其加入左侧的哈希表，同时将其在右侧的哈希表中的出现次数减一。如果减一后，右侧哈希表中的出现次数为 0，则将其从右侧哈希表中移除。然后，我们判断左侧哈希表中的键值对数量是否与右侧哈希表中的键值对数量相等，如果相等，则将答案加一。

最终，我们返回答案即可。

时间复杂度为 $O(n)$，空间复杂度为 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numSplits(self, s: str) -> int:
        cnt = Counter(s)
        vis = set()
        ans = 0
        for c in s:
            vis.add(c)
            cnt[c] -= 1
            if cnt[c] == 0:
                cnt.pop(c)
            ans += len(vis) == len(cnt)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numSplits(String s) {
        Map<Character, Integer> cnt = new HashMap<>();
        for (char c : s.toCharArray()) {
            cnt.merge(c, 1, Integer::sum);
        }
        Set<Character> vis = new HashSet<>();
        int ans = 0;
        for (char c : s.toCharArray()) {
            vis.add(c);
            if (cnt.merge(c, -1, Integer::sum) == 0) {
                cnt.remove(c);
            }
            if (vis.size() == cnt.size()) {
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
    int numSplits(string s) {
        unordered_map<char, int> cnt;
        for (char& c : s) {
            ++cnt[c];
        }
        unordered_set<char> vis;
        int ans = 0;
        for (char& c : s) {
            vis.insert(c);
            if (--cnt[c] == 0) {
                cnt.erase(c);
            }
            ans += vis.size() == cnt.size();
        }
        return ans;
    }
};
```

### **Go**

```go
func numSplits(s string) (ans int) {
	cnt := map[rune]int{}
	for _, c := range s {
		cnt[c]++
	}
	vis := map[rune]bool{}
	for _, c := range s {
		vis[c] = true
		cnt[c]--
		if cnt[c] == 0 {
			delete(cnt, c)
		}
		if len(vis) == len(cnt) {
			ans++
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
