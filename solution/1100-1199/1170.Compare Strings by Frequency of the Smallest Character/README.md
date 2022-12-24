# [1170. 比较字符串最小字母出现频次](https://leetcode.cn/problems/compare-strings-by-frequency-of-the-smallest-character)

[English Version](/solution/1100-1199/1170.Compare%20Strings%20by%20Frequency%20of%20the%20Smallest%20Character/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分查找**

我们先按照题目描述，实现函数 $f(s)$，函数返回字符串 $s$ 中按字典序比较最小字母的出现频次。

接下来，我们将 $words$ 中的每个字符串 $s$ 都计算出 $f(s)$，并将其排序，存放在数组 $arr$ 中。

最后，我们遍历 $queries$ 中的每个字符串 $s$，计算 $f(s)$，然后在 $arr$ 中二分查找第一个大于 $f(s)$ 的位置 $i$，则 $arr$ 中下标 $i$ 及其后面的元素都满足 $f(s) < f(W)$，其中 $W$ 表示 $words$ 中的每个字符串，因此当前查询的答案就是 $n - i$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为 $words$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numSmallerByFrequency(self, queries: List[str], words: List[str]) -> List[int]:
        def f(s):
            cnt = Counter(s)
            for c in ascii_lowercase:
                if cnt[c]:
                    return cnt[c]

        arr = [f(s) for s in words]
        arr.sort()
        n = len(arr)
        return [n - bisect_right(arr, f(q)) for q in queries]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = f(words[i]);
        }
        Arrays.sort(arr);
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int x = f(queries[i]);
            ans[i] = n - search(arr, x);
        }
        return ans;
    }

    private int search(int[] arr, int x) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int f(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int v : cnt) {
            if (v > 0) {
                return v;
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> numSmallerByFrequency(vector<string>& queries, vector<string>& words) {
        auto f = [](string& s) {
            int cnt[26] = {0};
            for (char& c : s) {
                cnt[c - 'a']++;
            }
            for (int i = 0; i < 26; ++i) {
                if (cnt[i]) {
                    return cnt[i];
                }
            }
            return 0;
        };
        vector<int> arr;
        for (auto& s : words) {
            arr.emplace_back(f(s));
        }
        sort(arr.begin(), arr.end());
        vector<int> ans;
        for (auto& q : queries) {
            int x = f(q);
            ans.emplace_back(arr.end() - upper_bound(arr.begin(), arr.end(), x));
        }
        return ans;
    }
};
```

### **Go**

```go
func numSmallerByFrequency(queries []string, words []string) (ans []int) {
	f := func(s string) int {
		cnt := [26]int{}
		for _, c := range s {
			cnt[c-'a']++
		}
		for _, v := range cnt {
			if v > 0 {
				return v
			}
		}
		return 0
	}
	arr := []int{}
	for _, s := range words {
		arr = append(arr, f(s))
	}
	sort.Ints(arr)
	n := len(arr)
	for _, q := range queries {
		x := f(q)
		ans = append(ans, n-sort.Search(n, func(i int) bool { return arr[i] > x }))
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
