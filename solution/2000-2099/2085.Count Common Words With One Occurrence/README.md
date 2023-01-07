# [2085. 统计出现过一次的公共字符串](https://leetcode.cn/problems/count-common-words-with-one-occurrence)

[English Version](/solution/2000-2099/2085.Count%20Common%20Words%20With%20One%20Occurrence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串数组&nbsp;<code>words1</code>&nbsp;和&nbsp;<code>words2</code>&nbsp;，请你返回在两个字符串数组中 <strong>都恰好出现一次</strong>&nbsp;的字符串的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
<b>输出：</b>2
<strong>解释：</strong>
- "leetcode" 在两个数组中都恰好出现一次，计入答案。
- "amazing" 在两个数组中都恰好出现一次，计入答案。
- "is" 在两个数组中都出现过，但在 words1 中出现了 2 次，不计入答案。
- "as" 在 words1 中出现了一次，但是在 words2 中没有出现过，不计入答案。
所以，有 2 个字符串在两个数组中都恰好出现了一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
<b>输出：</b>0
<b>解释：</b>没有字符串在两个数组中都恰好出现一次。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>words1 = ["a","ab"], words2 = ["a","a","a","ab"]
<b>输出：</b>1
<b>解释：</b>唯一在两个数组中都出现一次的字符串是 "ab" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words1.length, words2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words1[i].length, words2[j].length &lt;= 30</code></li>
	<li><code>words1[i]</code> 和&nbsp;<code>words2[j]</code>&nbsp;都只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们可以用两个哈希表分别统计两个字符串数组中每个字符串出现的次数，然后遍历其中一个哈希表，如果某个字符串在另一个哈希表中出现了一次，且在当前哈希表中也出现了一次，则答案加一。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是两个字符串数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countWords(self, words1: List[str], words2: List[str]) -> int:
        cnt1 = Counter(words1)
        cnt2 = Counter(words2)
        return sum(cnt2[k] == 1 for k, v in cnt1.items() if v == 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> cnt1 = count(words1);
        Map<String, Integer> cnt2 = count(words2);
        int ans = 0;
        for (String w : words1) {
            if (cnt1.getOrDefault(w, 0) == 1 && cnt2.getOrDefault(w, 0) == 1) {
                ++ans;
            }
        }
        return ans;
    }

    private Map<String, Integer> count(String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            cnt.put(w, cnt.getOrDefault(w, 0) + 1);
        }
        return cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countWords(vector<string>& words1, vector<string>& words2) {
        unordered_map<string, int> cnt1;
        unordered_map<string, int> cnt2;
        for (auto& w : words1) cnt1[w]++;
        for (auto& w : words2) cnt2[w]++;
        int ans = 0;
        for (auto& w : words1) ans += (cnt1[w] == 1 && cnt2[w] == 1);
        return ans;
    }
};
```

### **Go**

```go
func countWords(words1 []string, words2 []string) int {
	cnt1 := map[string]int{}
	cnt2 := map[string]int{}
	for _, w := range words1 {
		cnt1[w]++
	}
	for _, w := range words2 {
		cnt2[w]++
	}
	ans := 0
	for _, w := range words1 {
		if cnt1[w] == 1 && cnt2[w] == 1 {
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
