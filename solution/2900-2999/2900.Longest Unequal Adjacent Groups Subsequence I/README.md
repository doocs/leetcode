---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2900.Longest%20Unequal%20Adjacent%20Groups%20Subsequence%20I/README.md
rating: 1468
source: 第 115 场双周赛 Q2
tags:
    - 贪心
    - 数组
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [2900. 最长相邻不相等子序列 I](https://leetcode.cn/problems/longest-unequal-adjacent-groups-subsequence-i)

[English Version](/solution/2900-2999/2900.Longest%20Unequal%20Adjacent%20Groups%20Subsequence%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串数组&nbsp;<code>words</code>&nbsp;，和一个&nbsp;<strong>二进制</strong>&nbsp;数组&nbsp;<code>groups</code>&nbsp;，两个数组长度都是&nbsp;<code>n</code>&nbsp;。</p>

<p>如果&nbsp;<code>words</code>&nbsp;的一个 <span data-keyword="subsequence-array">子序列</span> 是交替的，那么对于序列中的任意两个连续字符串，它们在&nbsp;<code>groups</code>&nbsp;中相同索引的对应元素是 <strong>不同</strong> 的（也就是说，不能有连续的 0 或 1），</p>

<p>你需要从&nbsp;<code>words</code>&nbsp;中选出&nbsp;<strong>最长交替<span data-keyword="subsequence-array">子序列</span></strong>。</p>

<p>返回选出的子序列。如果有多个答案，返回 <strong>任意</strong> 一个。</p>

<p><b>注意：</b><code>words</code>&nbsp;中的元素是不同的&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>words = ["e","a","b"], groups = [0,0,1]
<b>输出：</b>["e","b"]
<strong>解释：</strong>一个可行的子序列是 [0,2] ，因为 groups[0] != groups[2] 。
所以一个可行的答案是 [words[0],words[2]] = ["e","b"] 。
另一个可行的子序列是 [1,2] ，因为 groups[1] != groups[2] 。
得到答案为 [words[1],words[2]] = ["a","b"] 。
这也是一个可行的答案。
符合题意的最长子序列的长度为 2 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>words = ["a","b","c","d"], groups = [1,0,1,1]
<b>输出：</b>["a","b","c"]
<b>解释：</b>一个可行的子序列为 [0,1,2] 因为 groups[0] != groups[1] 且 groups[1] != groups[2] 。
所以一个可行的答案是 [words[0],words[1],words[2]] = ["a","b","c"] 。
另一个可行的子序列为 [0,1,3] 因为 groups[0] != groups[1] 且 groups[1] != groups[3] 。
得到答案为 [words[0],words[1],words[3]] = ["a","b","d"] 。
这也是一个可行的答案。
符合题意的最长子序列的长度为 3 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == words.length == groups.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>groups[i]</code>&nbsp;是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>。</li>
	<li><code>words</code>&nbsp;中的字符串 <strong>互不相同</strong>&nbsp;。</li>
	<li><code>words[i]</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 一次遍历

我们可以遍历数组 $groups$，对于当前遍历到的下标 $i$，如果 $i=0$ 或者 $groups[i] \neq groups[i - 1]$，我们就将 $words[i]$ 加入答案数组中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $groups$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getLongestSubsequence(self, words: List[str], groups: List[int]) -> List[str]:
        return [words[i] for i, x in enumerate(groups) if i == 0 or x != groups[i - 1]]
```

#### Java

```java
class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n = groups.length;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (i == 0 || groups[i] != groups[i - 1]) {
                ans.add(words[i]);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> getLongestSubsequence(vector<string>& words, vector<int>& groups) {
        int n = groups.size();
        vector<string> ans;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || groups[i] != groups[i - 1]) {
                ans.emplace_back(words[i]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getLongestSubsequence(words []string, groups []int) (ans []string) {
	for i, x := range groups {
		if i == 0 || x != groups[i-1] {
			ans = append(ans, words[i])
		}
	}
	return
}
```

#### TypeScript

```ts
function getLongestSubsequence(words: string[], groups: number[]): string[] {
    const ans: string[] = [];
    for (let i = 0; i < groups.length; ++i) {
        if (i === 0 || groups[i] !== groups[i - 1]) {
            ans.push(words[i]);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn get_longest_subsequence(words: Vec<String>, groups: Vec<i32>) -> Vec<String> {
        let mut ans = Vec::new();
        for (i, &g) in groups.iter().enumerate() {
            if i == 0 || g != groups[i - 1] {
                ans.push(words[i].clone());
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
