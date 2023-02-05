# [2559. 统计范围内的元音字符串数](https://leetcode.cn/problems/count-vowel-strings-in-ranges)

[English Version](/solution/2500-2599/2559.Count%20Vowel%20Strings%20in%20Ranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串数组 <code>words</code> 以及一个二维整数数组 <code>queries</code> 。</p>

<p>每个查询 <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> 会要求我们统计在 <code>words</code> 中下标在 <code>l<sub>i</sub></code> 到 <code>r<sub>i</sub></code> 范围内（<strong>包含</strong> 这两个值）并且以元音开头和结尾的字符串的数目。</p>

<p>返回一个整数数组，其中数组的第 <code>i</code> 个元素对应第 <code>i</code> 个查询的答案。</p>

<p><strong>注意：</strong>元音字母是 <code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code> 和 <code>'u'</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
<strong>输出：</strong>[2,3,0]
<strong>解释：</strong>以元音开头和结尾的字符串是 "aba"、"ece"、"aa" 和 "e" 。
查询 [0,2] 结果为 2（字符串 "aba" 和 "ece"）。
查询 [1,4] 结果为 3（字符串 "ece"、"aa"、"e"）。
查询 [1,1] 结果为 0 。
返回结果 [2,3,0] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
<strong>输出：</strong>[3,2,1]
<strong>解释：</strong>每个字符串都满足这一条件，所以返回 [3,2,1] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 40</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成</li>
	<li><code>sum(words[i].length) &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= queries[j][0] &lt;= queries[j][1] &lt;&nbsp;words.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理 + 二分查找**

预处理出所有以元音开头和结尾的字符串的下标，然后对于每个查询，统计在预处理数组中的下标范围内的字符串的数目。

时间复杂度 $O(n + m \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为 `words` 和 `queries` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def vowelStrings(self, words: List[str], queries: List[List[int]]) -> List[int]:
        t = [i for i, w in enumerate(words) if w[0] in "aeiou" and w[-1] in "aeiou"]
        return [bisect_left(t, r + 1) - bisect_left(t, l) for l, r in queries]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        List<Integer> t = new ArrayList<>();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        for (int i = 0; i < words.length; ++i) {
            char a = words[i].charAt(0), b = words[i].charAt(words[i].length() - 1);
            if (vowels.contains(a) && vowels.contains(b)) {
                t.add(i);
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = search(t, queries[i][1] + 1) - search(t, queries[i][0]);
        }
        return ans;
    }

    private int search(List<Integer> nums, int x) {
        int left = 0, right = nums.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums.get(mid) >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> vowelStrings(vector<string>& words, vector<vector<int>>& queries) {
        vector<int> t;
        unordered_set<char> vowels = {'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < words.size(); ++i) {
            if (vowels.count(words[i][0]) && vowels.count(words[i].back())) {
                t.push_back(i);
            }
        }
        vector<int> ans;
        for (auto& q : queries) {
            int x = lower_bound(t.begin(), t.end(), q[1] + 1) - lower_bound(t.begin(), t.end(), q[0]);
            ans.push_back(x);
        }
        return ans;
    }
};
```

### **Go**

```go
func vowelStrings(words []string, queries [][]int) (ans []int) {
	vowels := "aeiou"
	t := []int{}
	for i, w := range words {
		if strings.Contains(vowels, w[:1]) && strings.Contains(vowels, w[len(w)-1:]) {
			t = append(t, i)
		}
	}
	for _, q := range queries {
		i := sort.Search(len(t), func(i int) bool { return t[i] >= q[0] })
		j := sort.Search(len(t), func(i int) bool { return t[i] >= q[1]+1 })
		ans = append(ans, j-i)
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
