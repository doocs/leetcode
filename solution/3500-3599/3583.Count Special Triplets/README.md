---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3583.Count%20Special%20Triplets/README.md
rating: 1509
source: 第 454 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [3583. 统计特殊三元组](https://leetcode.cn/problems/count-special-triplets)

[English Version](/solution/3500-3599/3583.Count%20Special%20Triplets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p><strong>特殊三元组 </strong>定义为满足以下条件的下标三元组 <code>(i, j, k)</code>：</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; k &lt; n</code>，其中 <code>n = nums.length</code></li>
	<li><code>nums[i] == nums[j] * 2</code></li>
	<li><code>nums[k] == nums[j] * 2</code></li>
</ul>

<p>返回数组中&nbsp;<strong>特殊三元组&nbsp;</strong>的总数。</p>

<p>由于答案可能非常大，请返回结果对 <code>10<sup>9</sup> + 7</code> 取余数后的值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [6,3,6]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>唯一的特殊三元组是 <code>(i, j, k) = (0, 1, 2)</code>，其中：</p>

<ul>
	<li><code>nums[0] = 6</code>, <code>nums[1] = 3</code>, <code>nums[2] = 6</code></li>
	<li><code>nums[0] = nums[1] * 2 = 3 * 2 = 6</code></li>
	<li><code>nums[2] = nums[1] * 2 = 3 * 2 = 6</code></li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,1,0,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>唯一的特殊三元组是 <code>(i, j, k) = (0, 2, 3)</code>，其中：</p>

<ul>
	<li><code>nums[0] = 0</code>, <code>nums[2] = 0</code>, <code>nums[3] = 0</code></li>
	<li><code>nums[0] = nums[2] * 2 = 0 * 2 = 0</code></li>
	<li><code>nums[3] = nums[2] * 2 = 0 * 2 = 0</code></li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [8,4,2,8,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>共有两个特殊三元组：</p>

<ul>
	<li><code>(i, j, k) = (0, 1, 3)</code>

    <ul>
    	<li><code>nums[0] = 8</code>, <code>nums[1] = 4</code>, <code>nums[3] = 8</code></li>
    	<li><code>nums[0] = nums[1] * 2 = 4 * 2 = 8</code></li>
    	<li><code>nums[3] = nums[1] * 2 = 4 * 2 = 8</code></li>
    </ul>
    </li>
    <li><code>(i, j, k) = (1, 2, 4)</code>
    <ul>
    	<li><code>nums[1] = 4</code>, <code>nums[2] = 2</code>, <code>nums[4] = 4</code></li>
    	<li><code>nums[1] = nums[2] * 2 = 2 * 2 = 4</code></li>
    	<li><code>nums[4] = nums[2] * 2 = 2 * 2 = 4</code></li>
    </ul>
    </li>

</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举中间数字 + 哈希表

我们可以枚举中间数字 $\textit{nums}[j]$，用两个哈希表 $\textit{left}$ 和 $\textit{right}$ 分别记录 $\textit{nums}[j]$ 左侧和右侧的数字出现次数。

我们首先将所有数字加入 $\textit{right}$ 中，然后从左到右遍历每个数字 $\textit{nums}[j]$，在遍历过程中：

1. 将 $\textit{nums}[j]$ 从 $\textit{right}$ 中移除。
2. 计算 $\textit{nums}[j]$ 左侧的数字 $\textit{nums}[i] = \textit{nums}[j] * 2$ 的出现次数，记为 $\textit{left}[\textit{nums}[j] * 2]$。
3. 计算 $\textit{nums}[j]$ 右侧的数字 $\textit{nums}[k] = \textit{nums}[j] * 2$ 的出现次数，记为 $\textit{right}[\textit{nums}[j] * 2]$。
4. 将 $\textit{left}[\textit{nums}[j] * 2]$ 和 $\textit{right}[\textit{nums}[j] * 2]$ 相乘，得到以 $\textit{nums}[j]$ 为中间数字的特殊三元组数量，并将结果累加到答案中。
5. 将 $\textit{nums}[j]$ 加入 $\textit{left}$ 中。

最后返回答案。

时间复杂度为 $O(n)$，空间复杂度为 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def specialTriplets(self, nums: List[int]) -> int:
        left = Counter()
        right = Counter(nums)
        ans = 0
        mod = 10**9 + 7
        for x in nums:
            right[x] -= 1
            ans = (ans + left[x * 2] * right[x * 2] % mod) % mod
            left[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public int specialTriplets(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        for (int x : nums) {
            right.merge(x, 1, Integer::sum);
        }
        long ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int x : nums) {
            right.merge(x, -1, Integer::sum);
            ans = (ans + 1L * left.getOrDefault(x * 2, 0) * right.getOrDefault(x * 2, 0) % mod)
                % mod;
            left.merge(x, 1, Integer::sum);
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int specialTriplets(vector<int>& nums) {
        unordered_map<int, int> left, right;
        for (int x : nums) {
            right[x]++;
        }
        long long ans = 0;
        const int mod = 1e9 + 7;
        for (int x : nums) {
            right[x]--;
            ans = (ans + 1LL * left[x * 2] * right[x * 2] % mod) % mod;
            left[x]++;
        }
        return (int) ans;
    }
};
```

#### Go

```go
func specialTriplets(nums []int) int {
	left := make(map[int]int)
	right := make(map[int]int)
	for _, x := range nums {
		right[x]++
	}
	ans := int64(0)
	mod := int64(1e9 + 7)
	for _, x := range nums {
		right[x]--
		ans = (ans + int64(left[x*2])*int64(right[x*2])%mod) % mod
		left[x]++
	}
	return int(ans)
}
```

#### TypeScript

```ts
function specialTriplets(nums: number[]): number {
    const left = new Map<number, number>();
    const right = new Map<number, number>();
    for (const x of nums) {
        right.set(x, (right.get(x) || 0) + 1);
    }
    let ans = 0;
    const mod = 1e9 + 7;
    for (const x of nums) {
        right.set(x, (right.get(x) || 0) - 1);
        const lx = left.get(x * 2) || 0;
        const rx = right.get(x * 2) || 0;
        ans = (ans + ((lx * rx) % mod)) % mod;
        left.set(x, (left.get(x) || 0) + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
