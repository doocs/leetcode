# [2347. 最好的扑克手牌](https://leetcode.cn/problems/best-poker-hand)

[English Version](/solution/2300-2399/2347.Best%20Poker%20Hand/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>ranks</code>&nbsp;和一个字符数组&nbsp;<code>suit</code>&nbsp;。你有&nbsp;<code>5</code>&nbsp;张扑克牌，第&nbsp;<code>i</code>&nbsp;张牌大小为&nbsp;<code>ranks[i]</code>&nbsp;，花色为&nbsp;<code>suits[i]</code>&nbsp;。</p>

<p>下述是从好到坏你可能持有的 <strong>手牌类型&nbsp;</strong>：</p>

<ol>
	<li><code>"Flush"</code>：同花，五张相同花色的扑克牌。</li>
	<li><code>"Three of a Kind"</code>：三条，有 3 张大小相同的扑克牌。</li>
	<li><code>"Pair"</code>：对子，两张大小一样的扑克牌。</li>
	<li><code>"High Card"</code>：高牌，五张大小互不相同的扑克牌。</li>
</ol>

<p>请你返回一个字符串，表示给定的 5 张牌中，你能组成的 <strong>最好手牌类型</strong>&nbsp;。</p>

<p><strong>注意：</strong>返回的字符串&nbsp;<strong>大小写</strong>&nbsp;需与题目描述相同。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>ranks = [13,2,3,1,9], suits = ["a","a","a","a","a"]
<b>输出：</b>"Flush"
<b>解释：</b>5 张扑克牌的花色相同，所以返回 "Flush" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>ranks = [4,4,2,4,4], suits = ["d","a","a","b","c"]
<b>输出：</b>"Three of a Kind"
<b>解释：</b>第一、二和四张牌组成三张相同大小的扑克牌，所以得到 "Three of a Kind" 。
注意我们也可以得到 "Pair" ，但是 "Three of a Kind" 是更好的手牌类型。
有其他的 3 张牌也可以组成 "Three of a Kind" 手牌类型。</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>ranks = [10,10,2,12,9], suits = ["a","b","c","a","d"]
<b>输出：</b>"Pair"
<b>解释：</b>第一和第二张牌大小相同，所以得到 "Pair" 。
我们无法得到 "Flush" 或者 "Three of a Kind" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>ranks.length == suits.length == 5</code></li>
	<li><code>1 &lt;= ranks[i] &lt;= 13</code></li>
	<li><code>'a' &lt;= suits[i] &lt;= 'd'</code></li>
	<li>任意两张扑克牌不会同时有相同的大小和花色。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def bestHand(self, ranks: List[int], suits: List[str]) -> str:
        if len(set(suits)) == 1:
            return 'Flush'
        cnt = Counter(ranks)
        if any(v >= 3 for v in cnt.values()):
            return 'Three of a Kind'
        if any(v == 2 for v in cnt.values()):
            return 'Pair'
        return 'High Card'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        Set<Character> s = new HashSet<>();
        for (char c : suits) {
            s.add(c);
        }
        if (s.size() == 1) {
            return "Flush";
        }
        int[] cnt = new int[20];
        for (int v : ranks) {
            ++cnt[v];
            if (cnt[v] >= 3) {
                return "Three of a Kind";
            }
        }
        for (int v : cnt) {
            if (v == 2) {
                return "Pair";
            }
        }
        return "High Card";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string bestHand(vector<int>& ranks, vector<char>& suits) {
        unordered_set<char> s(suits.begin(), suits.end());
        if (s.size() == 1) return "Flush";
        vector<int> cnt(20);
        for (int v : ranks)
            if (++cnt[v] >= 3) return "Three of a Kind";
        for (int v : cnt)
            if (v == 2) return "Pair";
        return "High Card";
    }
};
```

### **Go**

```go
func bestHand(ranks []int, suits []byte) string {
	s := map[byte]bool{}
	for _, v := range suits {
		s[v] = true
	}
	if len(s) == 1 {
		return "Flush"
	}
	cnt := make([]int, 20)
	for _, v := range ranks {
		cnt[v]++
		if cnt[v] >= 3 {
			return "Three of a Kind"
		}
	}
	for _, v := range cnt {
		if v == 2 {
			return "Pair"
		}
	}
	return "High Card"
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
