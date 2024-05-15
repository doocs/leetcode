---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2744.Find%20Maximum%20Number%20of%20String%20Pairs/README.md
rating: 1405
tags:
    - 数组
    - 哈希表
    - 字符串
    - 模拟
---

# [2744. 最大字符串配对数目](https://leetcode.cn/problems/find-maximum-number-of-string-pairs)

[English Version](/solution/2700-2799/2744.Find%20Maximum%20Number%20of%20String%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>words</code>&nbsp;，数组中包含 <strong>互不相同</strong>&nbsp;的字符串。</p>

<p>如果字符串&nbsp;<code>words[i]</code>&nbsp;与字符串 <code>words[j]</code>&nbsp;满足以下条件，我们称它们可以匹配：</p>

<ul>
	<li>字符串&nbsp;<code>words[i]</code>&nbsp;等于&nbsp;<code>words[j]</code>&nbsp;的反转字符串。</li>
	<li><code>0 &lt;= i &lt; j &lt; words.length</code></li>
</ul>

<p>请你返回数组 <code>words</code>&nbsp;中的&nbsp;<strong>最大</strong>&nbsp;匹配数目。</p>

<p>注意，每个字符串最多匹配一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>words = ["cd","ac","dc","ca","zz"]
<b>输出：</b>2
<strong>解释：</strong>在此示例中，我们可以通过以下方式匹配 2 对字符串：
- 我们将第 0 个字符串与第 2 个字符串匹配，因为 word[0] 的反转字符串是 "dc" 并且等于 words[2]。
- 我们将第 1 个字符串与第 3 个字符串匹配，因为 word[1] 的反转字符串是 "ca" 并且等于 words[3]。
可以证明最多匹配数目是 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>words = ["ab","ba","cc"]
<b>输出：</b>1
<b>解释：</b>在此示例中，我们可以通过以下方式匹配 1 对字符串：
- 我们将第 0 个字符串与第 1 个字符串匹配，因为 words[1] 的反转字符串 "ab" 与 words[0] 相等。
可以证明最多匹配数目是 1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>words = ["aa","ab"]
<b>输出：</b>0
<strong>解释：</strong>这个例子中，无法匹配任何字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 50</code></li>
	<li><code>words[i].length == 2</code></li>
	<li><code>words</code>&nbsp;包含的字符串互不相同。</li>
	<li><code>words[i]</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：哈希表

我们可以用哈希表 $cnt$ 来存储数组 $words$ 中每个字符串的反转字符串出现的次数。

遍历数组 $words$，对于每个字符串 $w$，我们将其反转字符串 $w$ 的出现次数加到答案中，然后将 $w$ 的出现次数加 $1$。

最后返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $words$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumNumberOfStringPairs(self, words: List[str]) -> int:
        cnt = Counter()
        ans = 0
        for w in words:
            ans += cnt[w[::-1]]
            cnt[w] += 1
        return ans
```

```java
class Solution {
    public int maximumNumberOfStringPairs(String[] words) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (var w : words) {
            int a = w.charAt(0) - 'a', b = w.charAt(1) - 'a';
            ans += cnt.getOrDefault(b << 5 | a, 0);
            cnt.merge(a << 5 | b, 1, Integer::sum);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumNumberOfStringPairs(vector<string>& words) {
        unordered_map<int, int> cnt;
        int ans = 0;
        for (auto& w : words) {
            int a = w[0] - 'a', b = w[1] - 'a';
            ans += cnt[b << 5 | a];
            cnt[a << 5 | b]++;
        }
        return ans;
    }
};
```

```go
func maximumNumberOfStringPairs(words []string) (ans int) {
	cnt := map[int]int{}
	for _, w := range words {
		a, b := int(w[0]-'a'), int(w[1]-'a')
		ans += cnt[b<<5|a]
		cnt[a<<5|b]++
	}
	return
}
```

```ts
function maximumNumberOfStringPairs(words: string[]): number {
    const cnt: { [key: number]: number } = {};
    let ans = 0;
    for (const w of words) {
        const [a, b] = [w.charCodeAt(0) - 97, w.charCodeAt(w.length - 1) - 97];
        ans += cnt[(b << 5) | a] || 0;
        cnt[(a << 5) | b] = (cnt[(a << 5) | b] || 0) + 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
