---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3678.Smallest%20Absent%20Positive%20Greater%20Than%20Average/README.md
---

<!-- problem:start -->

# [3678. 大于平均值的最小未出现正整数](https://leetcode.cn/problems/smallest-absent-positive-greater-than-average)

[English Version](/solution/3600-3699/3678.Smallest%20Absent%20Positive%20Greater%20Than%20Average/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>返回 <code>nums</code> 中 <strong>严格大于</strong> <code>nums</code> 中所有元素 <strong>平均值</strong> 的 <strong>最小未出现正整数</strong>。</p>
数组的 <strong>平均值</strong> 定义为数组中所有元素的总和除以元素的数量。

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [3,5]</span></p>

<p><strong>输出:</strong> <span class="example-io">6</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li><code>nums</code> 的平均值是 <code>(3 + 5) / 2 = 8 / 2 = 4</code> 。</li>
	<li>大于 4 的最小未出现正整数是 6。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [-1,1,2]</span></p>

<p><strong>输出:</strong> <span class="example-io">3</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li><code>nums</code> 的平均值是 <code>(-1 + 1 + 2) / 3 = 2 / 3 = 0.667</code> 。</li>
	<li>大于 0.667 的最小未出现正整数是 3 。</li>
</ul>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [4,-1]</span></p>

<p><strong>输出:</strong> <span class="example-io">2</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li><code>nums</code> 的平均值是 <code>(4 + (-1)) / 2 = 3 / 2 = 1.50</code>。</li>
	<li>大于 1.50 的最小未出现正整数是 2。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $\textit{s}$ 来记录数组 $\textit{nums}$ 中出现过的元素。

然后，我们计算数组 $\textit{nums}$ 的平均值 $\textit{avg}$，并将答案 $\textit{ans}$ 初始化为 $\max(1, \lfloor \textit{avg} \rfloor + 1)$。

如果 $\textit{ans}$ 在 $\textit{s}$ 中出现过，那么我们将 $\textit{ans}$ 加一，直到 $\textit{ans}$ 不在 $\textit{s}$ 中出现过为止。

最后，返回 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestAbsent(self, nums: List[int]) -> int:
        s = set(nums)
        ans = max(1, sum(nums) // len(nums) + 1)
        while ans in s:
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int smallestAbsent(int[] nums) {
        Set<Integer> s = new HashSet<>();
        int sum = 0;
        for (int x : nums) {
            s.add(x);
            sum += x;
        }
        int ans = Math.max(1, sum / nums.length + 1);
        while (s.contains(ans)) {
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestAbsent(vector<int>& nums) {
        unordered_set<int> s;
        int sum = 0;
        for (int x : nums) {
            s.insert(x);
            sum += x;
        }
        int ans = max(1, sum / (int) nums.size() + 1);
        while (s.contains(ans)) {
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func smallestAbsent(nums []int) int {
	s := map[int]bool{}
	sum := 0
	for _, x := range nums {
		s[x] = true
		sum += x
	}
	ans := max(1, sum/len(nums)+1)
	for s[ans] {
		ans++
	}
	return ans
}
```

#### TypeScript

```ts
function smallestAbsent(nums: number[]): number {
    const s = new Set<number>(nums);
    const sum = nums.reduce((a, b) => a + b, 0);
    let ans = Math.max(1, Math.floor(sum / nums.length) + 1);
    while (s.has(ans)) {
        ans++;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
