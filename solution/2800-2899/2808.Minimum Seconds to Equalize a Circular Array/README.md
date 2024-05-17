---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2808.Minimum%20Seconds%20to%20Equalize%20a%20Circular%20Array/README.md
rating: 1875
source: 第 110 场双周赛 Q3
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [2808. 使循环数组所有元素相等的最少秒数](https://leetcode.cn/problems/minimum-seconds-to-equalize-a-circular-array)

[English Version](/solution/2800-2899/2808.Minimum%20Seconds%20to%20Equalize%20a%20Circular%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>每一秒，你可以对数组执行以下操作：</p>

<ul>
	<li>对于范围在&nbsp;<code>[0, n - 1]</code>&nbsp;内的每一个下标&nbsp;<code>i</code>&nbsp;，将&nbsp;<code>nums[i]</code> 替换成&nbsp;<code>nums[i]</code>&nbsp;，<code>nums[(i - 1 + n) % n]</code>&nbsp;或者&nbsp;<code>nums[(i + 1) % n]</code>&nbsp;三者之一。</li>
</ul>

<p><strong>注意</strong>，所有元素会被同时替换。</p>

<p>请你返回将数组 <code>nums</code>&nbsp;中所有元素变成相等元素所需要的 <strong>最少</strong>&nbsp;秒数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,1,2]
<b>输出：</b>1
<b>解释：</b>我们可以在 1 秒内将数组变成相等元素：
- 第 1 秒，将每个位置的元素分别变为 [nums[3],nums[1],nums[3],nums[3]] 。变化后，nums = [2,2,2,2] 。
1 秒是将数组变成相等元素所需要的最少秒数。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,1,3,3,2]
<b>输出：</b>2
<b>解释：</b>我们可以在 2 秒内将数组变成相等元素：
- 第 1 秒，将每个位置的元素分别变为 [nums[0],nums[2],nums[2],nums[2],nums[3]] 。变化后，nums = [2,3,3,3,3] 。
- 第 2 秒，将每个位置的元素分别变为 [nums[1],nums[1],nums[2],nums[3],nums[4]] 。变化后，nums = [3,3,3,3,3] 。
2 秒是将数组变成相等元素所需要的最少秒数。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [5,5,5,5]
<b>输出：</b>0
<b>解释：</b>不需要执行任何操作，因为一开始数组中的元素已经全部相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们假设最终所有元素都变成了 $x$，那么 $x$ 一定是数组中的某个元素。

数字 $x$ 每一秒都可以向左右两边扩展一位，如果有多个相同的 $x$，那么扩展完整个数组所需要的时间，就取决于相邻两个 $x$ 之间的最大间距。

因此，我们枚举每个元素作为最终的 $x$，计算出每个 $x$ 中相邻两个元素之间的最大间距，记为 $t$，那么最终答案就是 $\min\limits_{x \in nums} \left\lfloor \frac{t}{2} \right\rfloor$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSeconds(self, nums: List[int]) -> int:
        d = defaultdict(list)
        for i, x in enumerate(nums):
            d[x].append(i)
        ans = inf
        n = len(nums)
        for idx in d.values():
            t = idx[0] + n - idx[-1]
            for i, j in pairwise(idx):
                t = max(t, j - i)
            ans = min(ans, t // 2)
        return ans
```

#### Java

```java
class Solution {
    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, List<Integer>> d = new HashMap<>();
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            d.computeIfAbsent(nums.get(i), k -> new ArrayList<>()).add(i);
        }
        int ans = 1 << 30;
        for (List<Integer> idx : d.values()) {
            int m = idx.size();
            int t = idx.get(0) + n - idx.get(m - 1);
            for (int i = 1; i < m; ++i) {
                t = Math.max(t, idx.get(i) - idx.get(i - 1));
            }
            ans = Math.min(ans, t / 2);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSeconds(vector<int>& nums) {
        unordered_map<int, vector<int>> d;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            d[nums[i]].push_back(i);
        }
        int ans = 1 << 30;
        for (auto& [_, idx] : d) {
            int m = idx.size();
            int t = idx[0] + n - idx[m - 1];
            for (int i = 1; i < m; ++i) {
                t = max(t, idx[i] - idx[i - 1]);
            }
            ans = min(ans, t / 2);
        }
        return ans;
    }
};
```

#### Go

```go
func minimumSeconds(nums []int) int {
	d := map[int][]int{}
	for i, x := range nums {
		d[x] = append(d[x], i)
	}
	ans := 1 << 30
	n := len(nums)
	for _, idx := range d {
		m := len(idx)
		t := idx[0] + n - idx[m-1]
		for i := 1; i < m; i++ {
			t = max(t, idx[i]-idx[i-1])
		}
		ans = min(ans, t/2)
	}
	return ans
}
```

#### TypeScript

```ts
function minimumSeconds(nums: number[]): number {
    const d: Map<number, number[]> = new Map();
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (!d.has(nums[i])) {
            d.set(nums[i], []);
        }
        d.get(nums[i])!.push(i);
    }
    let ans = 1 << 30;
    for (const [_, idx] of d) {
        const m = idx.length;
        let t = idx[0] + n - idx[m - 1];
        for (let i = 1; i < m; ++i) {
            t = Math.max(t, idx[i] - idx[i - 1]);
        }
        ans = Math.min(ans, t >> 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
