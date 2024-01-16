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

### 方法一：预处理 + 二分查找

我们可以预处理出所有以元音开头和结尾的字符串的下标，按顺序记录在数组 $nums$ 中。

接下来，我们遍历每个查询 $(l, r)$，通过二分查找在 $nums$ 中找到第一个大于等于 $l$ 的下标 $i$，以及第一个大于 $r$ 的下标 $j$，那么当前查询的答案就是 $j - i$。

时间复杂度 $O(n + m \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是数组 $words$ 和 $queries$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def vowelStrings(self, words: List[str], queries: List[List[int]]) -> List[int]:
        vowels = set("aeiou")
        nums = [i for i, w in enumerate(words) if w[0] in vowels and w[-1] in vowels]
        return [bisect_right(nums, r) - bisect_left(nums, l) for l, r in queries]
```

```java
class Solution {
    private List<Integer> nums = new ArrayList<>();

    public int[] vowelStrings(String[] words, int[][] queries) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        for (int i = 0; i < words.length; ++i) {
            char a = words[i].charAt(0), b = words[i].charAt(words[i].length() - 1);
            if (vowels.contains(a) && vowels.contains(b)) {
                nums.add(i);
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int l = queries[i][0], r = queries[i][1];
            ans[i] = search(r + 1) - search(l);
        }
        return ans;
    }

    private int search(int x) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums.get(mid) >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    vector<int> vowelStrings(vector<string>& words, vector<vector<int>>& queries) {
        unordered_set<char> vowels = {'a', 'e', 'i', 'o', 'u'};
        vector<int> nums;
        for (int i = 0; i < words.size(); ++i) {
            char a = words[i][0], b = words[i].back();
            if (vowels.count(a) && vowels.count(b)) {
                nums.push_back(i);
            }
        }
        vector<int> ans;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            int cnt = upper_bound(nums.begin(), nums.end(), r) - lower_bound(nums.begin(), nums.end(), l);
            ans.push_back(cnt);
        }
        return ans;
    }
};
```

```go
func vowelStrings(words []string, queries [][]int) []int {
	vowels := map[byte]bool{'a': true, 'e': true, 'i': true, 'o': true, 'u': true}
	nums := []int{}
	for i, w := range words {
		if vowels[w[0]] && vowels[w[len(w)-1]] {
			nums = append(nums, i)
		}
	}
	ans := make([]int, len(queries))
	for i, q := range queries {
		l, r := q[0], q[1]
		ans[i] = sort.SearchInts(nums, r+1) - sort.SearchInts(nums, l)
	}
	return ans
}
```

```ts
function vowelStrings(words: string[], queries: number[][]): number[] {
    const vowels = new Set(['a', 'e', 'i', 'o', 'u']);
    const nums: number[] = [];
    for (let i = 0; i < words.length; ++i) {
        if (vowels.has(words[i][0]) && vowels.has(words[i][words[i].length - 1])) {
            nums.push(i);
        }
    }
    const search = (x: number): number => {
        let l = 0,
            r = nums.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    return queries.map(([l, r]) => search(r + 1) - search(l));
}
```

<!-- tabs:end -->

### 方法二：前缀和

我们可以创建一个长度为 $n+1$ 的前缀和数组 $s$，其中 $s[i]$ 表示数组 $words$ 的前 $i$ 个字符串中以元音开头和结尾的字符串的数目。初始时 $s[0] = 0$。

接下来，我们遍历数组 $words$，如果当前字符串以元音开头和结尾，那么 $s[i+1] = s[i] + 1$，否则 $s[i+1] = s[i]$。

最后，我们遍历每个查询 $(l, r)$，那么当前查询的答案就是 $s[r+1] - s[l]$。

时间复杂度 $O(n + m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是数组 $words$ 和 $queries$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def vowelStrings(self, words: List[str], queries: List[List[int]]) -> List[int]:
        vowels = set("aeiou")
        s = list(
            accumulate(
                (int(w[0] in vowels and w[-1] in vowels) for w in words), initial=0
            )
        )
        return [s[r + 1] - s[l] for l, r in queries]
```

```java
class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int n = words.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            char a = words[i].charAt(0), b = words[i].charAt(words[i].length() - 1);
            s[i + 1] = s[i] + (vowels.contains(a) && vowels.contains(b) ? 1 : 0);
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int l = queries[i][0], r = queries[i][1];
            ans[i] = s[r + 1] - s[l];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> vowelStrings(vector<string>& words, vector<vector<int>>& queries) {
        unordered_set<char> vowels = {'a', 'e', 'i', 'o', 'u'};
        int n = words.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            char a = words[i][0], b = words[i].back();
            s[i + 1] = s[i] + (vowels.count(a) && vowels.count(b));
        }
        vector<int> ans;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            ans.push_back(s[r + 1] - s[l]);
        }
        return ans;
    }
};
```

```go
func vowelStrings(words []string, queries [][]int) []int {
	vowels := map[byte]bool{'a': true, 'e': true, 'i': true, 'o': true, 'u': true}
	n := len(words)
	s := make([]int, n+1)
	for i, w := range words {
		x := 0
		if vowels[w[0]] && vowels[w[len(w)-1]] {
			x = 1
		}
		s[i+1] = s[i] + x
	}
	ans := make([]int, len(queries))
	for i, q := range queries {
		l, r := q[0], q[1]
		ans[i] = s[r+1] - s[l]
	}
	return ans
}
```

```ts
function vowelStrings(words: string[], queries: number[][]): number[] {
    const vowels = new Set(['a', 'e', 'i', 'o', 'u']);
    const n = words.length;
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        if (vowels.has(words[i][0]) && vowels.has(words[i][words[i].length - 1])) {
            s[i + 1] = s[i] + 1;
        } else {
            s[i + 1] = s[i];
        }
    }
    return queries.map(([l, r]) => s[r + 1] - s[l]);
}
```

<!-- tabs:end -->

<!-- end -->
