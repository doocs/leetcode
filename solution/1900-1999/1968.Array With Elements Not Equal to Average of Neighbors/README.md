---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1968.Array%20With%20Elements%20Not%20Equal%20to%20Average%20of%20Neighbors/README.md
rating: 1499
source: 第 254 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [1968. 构造元素不等于两相邻元素平均值的数组](https://leetcode.cn/problems/array-with-elements-not-equal-to-average-of-neighbors)

[English Version](/solution/1900-1999/1968.Array%20With%20Elements%20Not%20Equal%20to%20Average%20of%20Neighbors/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>下标从 0 开始</strong> 的数组 <code>nums</code> ，数组由若干 <strong>互不相同的</strong> 整数组成。你打算重新排列数组中的元素以满足：重排后，数组中的每个元素都 <strong>不等于</strong> 其两侧相邻元素的 <strong>平均值</strong> 。</p>

<p>更公式化的说法是，重新排列的数组应当满足这一属性：对于范围&nbsp;<code>1 &lt;= i &lt; nums.length - 1</code> 中的每个 <code>i</code> ，<code>(nums[i-1] + nums[i+1]) / 2</code> <strong>不等于</strong> <code>nums[i]</code> 均成立 。</p>

<p>返回满足题意的任一重排结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4,5]
<strong>输出：</strong>[1,2,4,5,3]
<strong>解释：</strong>
i=1, nums[i] = 2, 两相邻元素平均值为 (1+4) / 2 = 2.5
i=2, nums[i] = 4, 两相邻元素平均值为 (2+5) / 2 = 3.5
i=3, nums[i] = 5, 两相邻元素平均值为 (4+3) / 2 = 3.5
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [6,2,0,9,7]
<strong>输出：</strong>[9,7,6,2,0]
<strong>解释：</strong>
i=1, nums[i] = 7, 两相邻元素平均值为 (9+6) / 2 = 7.5
i=2, nums[i] = 6, 两相邻元素平均值为 (7+2) / 2 = 4.5
i=3, nums[i] = 2, 两相邻元素平均值为 (6+0) / 2 = 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

由于数组中的元素是互不相同的，我们可以先对数组进行排序，然后将数组分成两部分，将前一半的元素放到答案数组中的偶数位置，将后一半的元素放到答案数组中的奇数位置。这样，对于每个元素，它的两个相邻元素都不会等于它的平均值。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeArray(self, nums: List[int]) -> List[int]:
        nums.sort()
        n = len(nums)
        m = (n + 1) // 2
        ans = []
        for i in range(m):
            ans.append(nums[i])
            if i + m < n:
                ans.append(nums[i + m])
        return ans
```

#### Java

```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = (n + 1) >> 1;
        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i += 2, j++) {
            ans[i] = nums[j];
            if (j + m < n) {
                ans[i + 1] = nums[j + m];
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
    vector<int> rearrangeArray(vector<int>& nums) {
        ranges::sort(nums);
        vector<int> ans;
        int n = nums.size();
        int m = (n + 1) >> 1;
        for (int i = 0; i < m; ++i) {
            ans.push_back(nums[i]);
            if (i + m < n) {
                ans.push_back(nums[i + m]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func rearrangeArray(nums []int) (ans []int) {
	sort.Ints(nums)
	n := len(nums)
	m := (n + 1) >> 1
	for i := 0; i < m; i++ {
		ans = append(ans, nums[i])
		if i+m < n {
			ans = append(ans, nums[i+m])
		}
	}
	return
}
```

#### TypeScript

```ts
function rearrangeArray(nums: number[]): number[] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const m = (n + 1) >> 1;
    const ans: number[] = [];
    for (let i = 0; i < m; i++) {
        ans.push(nums[i]);
        if (i + m < n) {
            ans.push(nums[i + m]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
