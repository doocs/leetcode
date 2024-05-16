---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2085.Count%20Common%20Words%20With%20One%20Occurrence/README_EN.md
rating: 1307
source: Biweekly Contest 66 Q1
tags:
    - Array
    - Hash Table
    - String
    - Counting
---

# [2085. Count Common Words With One Occurrence](https://leetcode.com/problems/count-common-words-with-one-occurrence)

[中文文档](/solution/2000-2099/2085.Count%20Common%20Words%20With%20One%20Occurrence/README.md)

## Description

<p>Given two string arrays <code>words1</code> and <code>words2</code>, return <em>the number of strings that appear <strong>exactly once</strong> in <b>each</b>&nbsp;of the two arrays.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words1 = [&quot;leetcode&quot;,&quot;is&quot;,&quot;amazing&quot;,&quot;as&quot;,&quot;is&quot;], words2 = [&quot;amazing&quot;,&quot;leetcode&quot;,&quot;is&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
- &quot;leetcode&quot; appears exactly once in each of the two arrays. We count this string.
- &quot;amazing&quot; appears exactly once in each of the two arrays. We count this string.
- &quot;is&quot; appears in each of the two arrays, but there are 2 occurrences of it in words1. We do not count this string.
- &quot;as&quot; appears once in words1, but does not appear in words2. We do not count this string.
Thus, there are 2 strings that appear exactly once in each of the two arrays.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words1 = [&quot;b&quot;,&quot;bb&quot;,&quot;bbb&quot;], words2 = [&quot;a&quot;,&quot;aa&quot;,&quot;aaa&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no strings that appear in each of the two arrays.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words1 = [&quot;a&quot;,&quot;ab&quot;], words2 = [&quot;a&quot;,&quot;a&quot;,&quot;a&quot;,&quot;ab&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only string that appears exactly once in each of the two arrays is &quot;ab&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words1.length, words2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words1[i].length, words2[j].length &lt;= 30</code></li>
	<li><code>words1[i]</code> and <code>words2[j]</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Hash Table + Counting

We can use two hash tables, $cnt1$ and $cnt2$, to count the occurrences of each string in the two string arrays respectively. Then, we traverse one of the hash tables. If a string appears once in the other hash table and also appears once in the current hash table, we increment the answer by one.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$. Where $n$ and $m$ are the lengths of the two string arrays respectively.

<!-- tabs:start -->

```python
class Solution:
    def countWords(self, words1: List[str], words2: List[str]) -> int:
        cnt1 = Counter(words1)
        cnt2 = Counter(words2)
        return sum(v == 1 and cnt2[w] == 1 for w, v in cnt1.items())
```

```java
class Solution {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> cnt1 = new HashMap<>();
        Map<String, Integer> cnt2 = new HashMap<>();
        for (var w : words1) {
            cnt1.merge(w, 1, Integer::sum);
        }
        for (var w : words2) {
            cnt2.merge(w, 1, Integer::sum);
        }
        int ans = 0;
        for (var e : cnt1.entrySet()) {
            if (e.getValue() == 1 && cnt2.getOrDefault(e.getKey(), 0) == 1) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countWords(vector<string>& words1, vector<string>& words2) {
        unordered_map<string, int> cnt1;
        unordered_map<string, int> cnt2;
        for (auto& w : words1) {
            ++cnt1[w];
        }
        for (auto& w : words2) {
            ++cnt2[w];
        }
        int ans = 0;
        for (auto& [w, v] : cnt1) {
            ans += v == 1 && cnt2[w] == 1;
        }
        return ans;
    }
};
```

```go
func countWords(words1 []string, words2 []string) (ans int) {
	cnt1 := map[string]int{}
	cnt2 := map[string]int{}
	for _, w := range words1 {
		cnt1[w]++
	}
	for _, w := range words2 {
		cnt2[w]++
	}
	for w, v := range cnt1 {
		if v == 1 && cnt2[w] == 1 {
			ans++
		}
	}
	return
}
```

```ts
function countWords(words1: string[], words2: string[]): number {
    const cnt1 = new Map<string, number>();
    const cnt2 = new Map<string, number>();
    for (const w of words1) {
        cnt1.set(w, (cnt1.get(w) ?? 0) + 1);
    }
    for (const w of words2) {
        cnt2.set(w, (cnt2.get(w) ?? 0) + 1);
    }
    let ans = 0;
    for (const [w, v] of cnt1) {
        if (v === 1 && cnt2.get(w) === 1) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
