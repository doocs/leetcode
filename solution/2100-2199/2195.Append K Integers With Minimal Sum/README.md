---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2195.Append%20K%20Integers%20With%20Minimal%20Sum/README.md
rating: 1658
source: 第 283 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 数学
    - 排序
---

<!-- problem:start -->

# [2195. 向数组中追加 K 个整数](https://leetcode.cn/problems/append-k-integers-with-minimal-sum)

[English Version](/solution/2100-2199/2195.Append%20K%20Integers%20With%20Minimal%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。请你向 <code>nums</code> 中追加 <code>k</code> 个 <strong>未</strong> 出现在 <code>nums</code> 中的、<strong>互不相同</strong> 的 <strong>正</strong> 整数，并使结果数组的元素和 <strong>最小</strong> 。</p>

<p>返回追加到 <code>nums</code> 中的 <code>k</code> 个整数之和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,4,25,10,25], k = 2
<strong>输出：</strong>5
<strong>解释：</strong>在该解法中，向数组中追加的两个互不相同且未出现的正整数是 2 和 3 。
nums 最终元素和为 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70 ，这是所有情况中的最小值。
所以追加到数组中的两个整数之和是 2 + 3 = 5 ，所以返回 5 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [5,6], k = 6
<strong>输出：</strong>25
<strong>解释：</strong>在该解法中，向数组中追加的两个互不相同且未出现的正整数是 1 、2 、3 、4 、7 和 8 。
nums 最终元素和为 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36 ，这是所有情况中的最小值。
所以追加到数组中的两个整数之和是 1 + 2 + 3 + 4 + 7 + 8 = 25 ，所以返回 25 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 贪心 + 数学

我们不妨向数组中加入两个哨兵节点，分别为 $0$ 和 $2 \times 10^9$。

然后我们对数组进行排序，那么对于数组中的任意两个相邻的元素 $a$ 和 $b$，区间 $[a+1, b-1]$ 中的整数都是未出现在数组中的，我们可以将这些整数加入到数组中。

因此，我们从小到大遍历数组中的相邻元素对 $(a, b)$，对于每个相邻元素对，我们计算区间 $[a+1, b-1]$ 中的整数个数 $m$，那么这 $m$ 个整数的和为 $\frac{m \times (a+1 + a+m)}{2}$，我们将这个和累加到答案中，并将 $k$ 减去 $m$。如果 $k$ 减到 $0$，我们就可以停止遍历了，返回答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimalKSum(self, nums: List[int], k: int) -> int:
        nums.extend([0, 2 * 10**9])
        nums.sort()
        ans = 0
        for a, b in pairwise(nums):
            m = max(0, min(k, b - a - 1))
            ans += (a + 1 + a + m) * m // 2
            k -= m
        return ans
```

```java
class Solution {
    public long minimalKSum(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[1] = 2 * 1000000000;
        System.arraycopy(nums, 0, arr, 2, n);
        Arrays.sort(arr);
        long ans = 0;
        for (int i = 0; i < n + 1 && k > 0; ++i) {
            int m = Math.max(0, Math.min(k, arr[i + 1] - arr[i] - 1));
            ans += (arr[i] + 1L + arr[i] + m) * m / 2;
            k -= m;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long minimalKSum(vector<int>& nums, int k) {
        nums.push_back(0);
        nums.push_back(2e9);
        sort(nums.begin(), nums.end());
        long long ans = 0;
        for (int i = 0; i < nums.size() - 1 && k > 0; ++i) {
            int m = max(0, min(k, nums[i + 1] - nums[i] - 1));
            ans += 1LL * (nums[i] + 1 + nums[i] + m) * m / 2;
            k -= m;
        }
        return ans;
    }
};
```

```go
func minimalKSum(nums []int, k int) (ans int64) {
	nums = append(nums, []int{0, 2e9}...)
	sort.Ints(nums)
	for i, b := range nums[1:] {
		a := nums[i]
		m := max(0, min(k, b-a-1))
		ans += int64(a+1+a+m) * int64(m) / 2
		k -= m
	}
	return ans
}
```

```ts
function minimalKSum(nums: number[], k: number): number {
    nums.push(...[0, 2 * 10 ** 9]);
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < nums.length - 1; ++i) {
        const m = Math.max(0, Math.min(k, nums[i + 1] - nums[i] - 1));
        ans += Number((BigInt(nums[i] + 1 + nums[i] + m) * BigInt(m)) / BigInt(2));
        k -= m;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
