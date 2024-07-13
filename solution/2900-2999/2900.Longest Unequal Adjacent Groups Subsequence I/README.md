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

<p>给你一个下标从&nbsp;<strong>0</strong>&nbsp;开始的字符串数组&nbsp;<code>words</code>&nbsp;，和一个下标从 <strong>0</strong>&nbsp;开始的 <strong>二进制</strong>&nbsp;数组&nbsp;<code>groups</code>&nbsp;，两个数组长度都是&nbsp;<code>n</code>&nbsp;。</p>

<p>你需要从&nbsp;<code>words</code>&nbsp;中选出&nbsp;<strong>最长<span data-keyword="subsequence-array">子序列</span></strong>。如果对于序列中的任何两个连续串，二进制数组&nbsp;<code>groups</code>&nbsp;中它们的对应元素不同，则&nbsp;<code>words</code> 的子序列是不同的。</p>

<p>正式来说，你需要从下标&nbsp;<code>[0, 1, ..., n - 1]</code>&nbsp;中选出一个&nbsp;<strong>最长子序列</strong>&nbsp;，将这个子序列记作长度为 <code>k</code> 的&nbsp;<code>[i<sub>0</sub>, i<sub>1</sub>, ..., i<sub>k - 1</sub>]</code>&nbsp;，对于所有满足&nbsp;<code>0 &lt;= j&nbsp;&lt; k - 1</code>&nbsp;的&nbsp;<code>j</code>&nbsp;都有&nbsp;<code>groups[i<sub>j</sub>] != groups[i<sub>j + 1</sub>]</code>&nbsp;。</p>

<p>请你返回一个字符串数组，它是下标子序列&nbsp;<strong>依次</strong>&nbsp;对应&nbsp;<code>words</code>&nbsp;数组中的字符串连接形成的字符串数组。如果有多个答案，返回 <strong>任意</strong> 一个。</p>

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
    def getWordsInLongestSubsequence(
        self, n: int, words: List[str], groups: List[int]
    ) -> List[str]:
        return [words[i] for i, x in enumerate(groups) if i == 0 or x != groups[i - 1]]
```

#### Java

```java
class Solution {
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
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
    vector<string> getWordsInLongestSubsequence(int n, vector<string>& words, vector<int>& groups) {
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
func getWordsInLongestSubsequence(n int, words []string, groups []int) (ans []string) {
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
function getWordsInLongestSubsequence(n: number, words: string[], groups: number[]): string[] {
    const ans: string[] = [];
    for (let i = 0; i < n; ++i) {
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
    pub fn get_words_in_longest_subsequence(
        n: i32,
        words: Vec<String>,
        groups: Vec<i32>,
    ) -> Vec<String> {
        let mut ans = vec![];

        for i in 0..n {
            if i == 0 || groups[i as usize] != groups[(i - 1) as usize] {
                ans.push(words[i as usize].clone());
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
