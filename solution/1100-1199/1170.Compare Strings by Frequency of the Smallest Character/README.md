---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1170.Compare%20Strings%20by%20Frequency%20of%20the%20Smallest%20Character/README.md
rating: 1431
source: 第 151 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 字符串
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [1170. 比较字符串最小字母出现频次](https://leetcode.cn/problems/compare-strings-by-frequency-of-the-smallest-character)

[English Version](/solution/1100-1199/1170.Compare%20Strings%20by%20Frequency%20of%20the%20Smallest%20Character/README_EN.md)

## 题目描述

<!-- description:start -->

<p>定义一个函数 <code>f(s)</code>，统计 <code>s</code>  中<strong>（按字典序比较）最小字母的出现频次</strong> ，其中 <code>s</code> 是一个非空字符串。</p>

<p>例如，若 <code>s = "dcce"</code>，那么 <code>f(s) = 2</code>，因为字典序最小字母是 <code>"c"</code>，它出现了 2 次。</p>

<p>现在，给你两个字符串数组待查表 <code>queries</code> 和词汇表 <code>words</code> 。对于每次查询 <code>queries[i]</code> ，需统计 <code>words</code> 中满足 <code>f(queries[i])</code> < <code>f(W)</code> 的<strong> 词的数目</strong> ，<code>W</code> 表示词汇表 <code>words</code> 中的每个词。</p>

<p>请你返回一个整数数组 <code>answer</code> 作为答案，其中每个 <code>answer[i]</code> 是第 <code>i</code> 次查询的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>queries = ["cbd"], words = ["zaaaz"]
<strong>输出：</strong>[1]
<strong>解释：</strong>查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
<strong>输出：</strong>[1,2]
<strong>解释：</strong>第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= queries.length <= 2000</code></li>
	<li><code>1 <= words.length <= 2000</code></li>
	<li><code>1 <= queries[i].length, words[i].length <= 10</code></li>
	<li><code>queries[i][j]</code>、<code>words[i][j]</code> 都由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

我们先按照题目描述，实现函数 $f(s)$，函数返回字符串 $s$ 中按字典序比较最小字母的出现频次。

接下来，我们将 $words$ 中的每个字符串 $w$ 都计算出 $f(w)$，并将其排序，存放在数组 $nums$ 中。

然后，我们遍历 $queries$ 中的每个字符串 $q$，在 $nums$ 中二分查找第一个大于 $f(q)$ 的位置 $i$，则 $nums$ 中下标 $i$ 及其后面的元素都满足 $f(q) < f(W)$，那么当前查询的答案就是 $n - i$。

时间复杂度 $O((n + q) \times M)$，空间复杂度 $O(n)$。其中 $n$ 和 $q$ 分别是数组 $words$ 和 $queries$ 的长度，而 $M$ 是字符串的最大长度。

<!-- tabs:start -->

```python
class Solution:
    def numSmallerByFrequency(self, queries: List[str], words: List[str]) -> List[int]:
        def f(s: str) -> int:
            cnt = Counter(s)
            return next(cnt[c] for c in ascii_lowercase if cnt[c])

        n = len(words)
        nums = sorted(f(w) for w in words)
        return [n - bisect_right(nums, f(q)) for q in queries]
```

```java
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = f(words[i]);
        }
        Arrays.sort(nums);
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int x = f(queries[i]);
            int l = 0, r = n;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (nums[mid] > x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans[i] = n - l;
        }
        return ans;
    }

    private int f(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int x : cnt) {
            if (x > 0) {
                return x;
            }
        }
        return 0;
    }
}
```

```cpp
class Solution {
public:
    vector<int> numSmallerByFrequency(vector<string>& queries, vector<string>& words) {
        auto f = [](string s) {
            int cnt[26] = {0};
            for (char c : s) {
                cnt[c - 'a']++;
            }
            for (int x : cnt) {
                if (x) {
                    return x;
                }
            }
            return 0;
        };
        int n = words.size();
        int nums[n];
        for (int i = 0; i < n; i++) {
            nums[i] = f(words[i]);
        }
        sort(nums, nums + n);
        vector<int> ans;
        for (auto& q : queries) {
            int x = f(q);
            ans.push_back(n - (upper_bound(nums, nums + n, x) - nums));
        }
        return ans;
    }
};
```

```go
func numSmallerByFrequency(queries []string, words []string) (ans []int) {
	f := func(s string) int {
		cnt := [26]int{}
		for _, c := range s {
			cnt[c-'a']++
		}
		for _, x := range cnt {
			if x > 0 {
				return x
			}
		}
		return 0
	}
	n := len(words)
	nums := make([]int, n)
	for i, w := range words {
		nums[i] = f(w)
	}
	sort.Ints(nums)
	for _, q := range queries {
		x := f(q)
		ans = append(ans, n-sort.SearchInts(nums, x+1))
	}
	return
}
```

```ts
function numSmallerByFrequency(queries: string[], words: string[]): number[] {
    const f = (s: string): number => {
        const cnt = new Array(26).fill(0);
        for (const c of s) {
            cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
        }
        return cnt.find(x => x > 0);
    };
    const nums = words.map(f).sort((a, b) => a - b);
    const ans: number[] = [];
    for (const q of queries) {
        const x = f(q);
        let l = 0,
            r = nums.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        ans.push(nums.length - l);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
