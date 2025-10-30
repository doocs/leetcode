---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3712.Sum%20of%20Elements%20With%20Frequency%20Divisible%20by%20K/README.md
rating: 1198
source: 第 471 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [3712. 出现次数能被 K 整除的元素总和](https://leetcode.cn/problems/sum-of-elements-with-frequency-divisible-by-k)

[English Version](/solution/3700-3799/3712.Sum%20of%20Elements%20With%20Frequency%20Divisible%20by%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>请返回一个整数，表示 <code>nums</code> 中所有其 <strong>出现次数</strong> 能被 <code>k</code> 整除的元素的<strong>总和</strong>；如果没有这样的元素，则返回 0 。</p>

<p><strong>注意：</strong> 若某个元素在数组中的总出现次数能被 <code>k</code> 整除，则它在求和中会被计算 <strong>恰好</strong> 与其出现次数相同的次数。</p>

<p>元素 <code>x</code> 的&nbsp;<strong>出现次数&nbsp;</strong>指它在数组中出现的次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,2,3,3,3,3,4], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">16</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>数字 1 出现 1 次（奇数次）。</li>
	<li>数字 2 出现 2 次（偶数次）。</li>
	<li>数字 3 出现 4 次（偶数次）。</li>
	<li>数字 4 出现 1 次（奇数次）。</li>
</ul>

<p>因此总和为 <code>2 + 2 + 3 + 3 + 3 + 3 = 16</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4,5], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>没有元素出现偶数次，因此总和为 0。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,4,4,1,2,3], k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>数字 1 出现 1 次。</li>
	<li>数字 2 出现 1 次。</li>
	<li>数字 3 出现 1 次。</li>
	<li>数字 4 出现 3 次。</li>
</ul>

<p>因此总和为 <code>4 + 4 + 4 = 12</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们用一个哈希表 $\textit{cnt}$ 来记录每个数字的出现次数。遍历数组 $\textit{nums}$，对于每个数字 $x$，我们将 $\textit{cnt}[x]$ 增加 $1$。

然后，我们遍历哈希表 $\textit{cnt}$，对于每个元素 $x$，如果它的出现次数 $\textit{cnt}[x]$ 能被 $k$ 整除，就将 $x$ 乘以它的出现次数加到结果中。

时间复杂度为 $O(n)$，其中 $n$ 是数组的长度。空间复杂度为 $O(m)$，其中 $m$ 是数组中不同元素的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumDivisibleByK(self, nums: List[int], k: int) -> int:
        cnt = Counter(nums)
        return sum(x * v for x, v in cnt.items() if v % k == 0)
```

#### Java

```java
class Solution {
    public int sumDivisibleByK(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            if (v % k == 0) {
                ans += x * v;
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
    int sumDivisibleByK(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (auto& [x, v] : cnt) {
            if (v % k == 0) {
                ans += x * v;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func sumDivisibleByK(nums []int, k int) (ans int) {
    cnt := map[int]int{}
    for _, x := range nums {
        cnt[x]++
    }
    for x, v := range cnt {
        if v%k == 0 {
            ans += x * v
        }
    }
    return
}
```

#### TypeScript

```ts
function sumDivisibleByK(nums: number[], k: number): number {
    const cnt = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    let ans = 0;
    for (const [x, v] of cnt.entries()) {
        if (v % k === 0) {
            ans += x * v;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
