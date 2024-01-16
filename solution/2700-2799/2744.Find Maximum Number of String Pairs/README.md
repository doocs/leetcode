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

遍历数组 $words$，对于每个字符串 $w$，我们直接将 $cnt[w]$ 的值加到答案中，然后将 $w$ 的反转字符串出现的次数加 $1$。

遍历结束后，即可得到最大匹配数目。

时间复杂度 $O(L)$，空间复杂度 $O(L)$，其中 $L$ 是数组 $words$ 中字符串的长度之和。

<!-- tabs:start -->

```python
class Solution:
    def maximumNumberOfStringPairs(self, words: List[str]) -> int:
        cnt = Counter()
        ans = 0
        for w in words:
            ans += cnt[w]
            cnt[w[::-1]] += 1
        return ans
```

```java
class Solution {
    public int maximumNumberOfStringPairs(String[] words) {
        Map<String, Integer> cnt = new HashMap<>(words.length);
        int ans = 0;
        for (String w : words) {
            ans += cnt.getOrDefault(w, 0);
            cnt.merge(new StringBuilder(w).reverse().toString(), 1, Integer::sum);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumNumberOfStringPairs(vector<string>& words) {
        unordered_map<string, int> cnt;
        int ans = 0;
        for (auto& w : words) {
            ans += cnt[w];
            reverse(w.begin(), w.end());
            cnt[w]++;
        }
        return ans;
    }
};
```

```go
func maximumNumberOfStringPairs(words []string) (ans int) {
	cnt := map[string]int{}
	for _, w := range words {
		ans += cnt[w]
		s := []byte(w)
		for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
			s[i], s[j] = s[j], s[i]
		}
		cnt[string(s)]++
	}
	return
}
```

```ts
function maximumNumberOfStringPairs(words: string[]): number {
    const cnt: Map<string, number> = new Map();
    let ans = 0;
    for (const w of words) {
        ans += cnt.get(w) || 0;
        const s = w.split('').reverse().join('');
        cnt.set(s, (cnt.get(s) || 0) + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
