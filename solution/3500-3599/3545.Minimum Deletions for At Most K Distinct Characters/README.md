---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3545.Minimum%20Deletions%20for%20At%20Most%20K%20Distinct%20Characters/README.md
rating: 1210
source: 第 449 场周赛 Q1
tags:
    - 贪心
    - 哈希表
    - 字符串
    - 计数
    - 排序
---

<!-- problem:start -->

# [3545. 不同字符数量最多为 K 时的最少删除数](https://leetcode.cn/problems/minimum-deletions-for-at-most-k-distinct-characters)

[English Version](/solution/3500-3599/3545.Minimum%20Deletions%20for%20At%20Most%20K%20Distinct%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>（由小写英文字母组成）和一个整数 <code>k</code>。</p>

<p>你的任务是删除字符串中的一些字符（可以不删除任何字符），使得结果字符串中的&nbsp;<strong>不同字符数量&nbsp;</strong>最多为 <code>k</code>。</p>

<p>返回为达到上述目标所需删除的&nbsp;<strong>最小&nbsp;</strong>字符数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abc", k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>s</code> 有三个不同的字符：<code>'a'</code>、<code>'b'</code> 和 <code>'c'</code>，每个字符的出现频率为 1。</li>
	<li>由于最多只能有 <code>k = 2</code> 个不同字符，需要删除某一个字符的所有出现。</li>
	<li>例如，删除所有 <code>'c'</code> 后，结果字符串中的不同字符数最多为 <code>k</code>。因此，答案是 1。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aabb", k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>s</code> 有两个不同的字符（<code>'a'</code> 和 <code>'b'</code>），它们的出现频率分别为 2 和 2。</li>
	<li>由于最多可以有 <code>k = 2</code> 个不同字符，不需要删除任何字符。因此，答案是 0。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "yyyzz", k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>s</code> 有两个不同的字符（<code>'y'</code> 和 <code>'z'</code>），它们的出现频率分别为 3 和 2。</li>
	<li>由于最多只能有 <code>k = 1</code> 个不同字符，需要删除某一个字符的所有出现。</li>
	<li>删除所有 <code>'z'</code> 后，结果字符串中的不同字符数最多为 <code>k</code>。因此，答案是 2。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 16</code></li>
	<li><code>1 &lt;= k &lt;= 16</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 贪心

我们可以使用一个数组 $\textit{cnt}$ 来统计每个字符的出现频率。然后我们对这个数组进行排序，最后返回前 $26 - k$ 个元素的和。

时间复杂度 $O(|\Sigma| \times \log |\Sigma|)$，空间复杂度 $O(|\Sigma|)$，其中 $|\Sigma|$ 是字符集的大小，本题中 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDeletion(self, s: str, k: int) -> int:
        return sum(sorted(Counter(s).values())[:-k])
```

#### Java

```java
class Solution {
    public int minDeletion(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = 0; i + k < 26; ++i) {
            ans += cnt[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDeletion(string s, int k) {
        vector<int> cnt(26);
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        ranges::sort(cnt);
        int ans = 0;
        for (int i = 0; i + k < 26; ++i) {
            ans += cnt[i];
        }
        return ans;
    }
};
```

#### Go

```go
func minDeletion(s string, k int) (ans int) {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	sort.Ints(cnt)
	for i := 0; i+k < len(cnt); i++ {
		ans += cnt[i]
	}
	return
}
```

#### TypeScript

```ts
function minDeletion(s: string, k: number): number {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    cnt.sort((a, b) => a - b);
    return cnt.slice(0, 26 - k).reduce((a, b) => a + b, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
