---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2966.Divide%20Array%20Into%20Arrays%20With%20Max%20Difference/README.md
rating: 1395
source: 第 376 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [2966. 划分数组并满足最大差限制](https://leetcode.cn/problems/divide-array-into-arrays-with-max-difference)

[English Version](/solution/2900-2999/2966.Divide%20Array%20Into%20Arrays%20With%20Max%20Difference/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>，以及一个正整数 <code>k</code> 。</p>

<p>将这个数组划分为一个或多个长度为 <code>3</code> 的子数组，并满足以下条件：</p>

<ul>
	<li><code>nums</code> 中的 <strong>每个 </strong>元素都必须 <strong>恰好 </strong>存在于某个子数组中。</li>
	<li>子数组中<strong> 任意 </strong>两个元素的差必须小于或等于 <code>k</code> 。</li>
</ul>

<p>返回一个<em> </em><strong>二维数组 </strong>，包含所有的子数组。如果不可能满足条件，就返回一个空数组。如果有多个答案，返回 <strong>任意一个</strong> 即可。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,4,8,7,9,3,5,1], k = 2
<strong>输出：</strong>[[1,1,3],[3,4,5],[7,8,9]]
<strong>解释：</strong>可以将数组划分为以下子数组：[1,1,3]，[3,4,5] 和 [7,8,9] 。
每个子数组中任意两个元素的差都小于或等于 2 。
注意，元素的顺序并不重要。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,3,2,7,3], k = 3
<strong>输出：</strong>[]
<strong>解释：</strong>无法划分数组满足所有条件。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> 是 <code>3</code> 的倍数</li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们先对数组进行排序，然后每次取出三个元素，如果这三个元素的最大值和最小值的差大于 $k$，则无法满足条件，返回空数组。否则，我们将这三个元素组成的数组加入答案数组中。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def divideArray(self, nums: List[int], k: int) -> List[List[int]]:
        nums.sort()
        ans = []
        n = len(nums)
        for i in range(0, n, 3):
            t = nums[i : i + 3]
            if t[2] - t[0] > k:
                return []
            ans.append(t)
        return ans
```

```java
class Solution {
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[][] ans = new int[n / 3][];
        for (int i = 0; i < n; i += 3) {
            int[] t = Arrays.copyOfRange(nums, i, i + 3);
            if (t[2] - t[0] > k) {
                return new int[][] {};
            }
            ans[i / 3] = t;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> divideArray(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> ans;
        int n = nums.size();
        for (int i = 0; i < n; i += 3) {
            vector<int> t = {nums[i], nums[i + 1], nums[i + 2]};
            if (t[2] - t[0] > k) {
                return {};
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

```go
func divideArray(nums []int, k int) [][]int {
	sort.Ints(nums)
	ans := [][]int{}
	for i := 0; i < len(nums); i += 3 {
		t := slices.Clone(nums[i : i+3])
		if t[2]-t[0] > k {
			return [][]int{}
		}
		ans = append(ans, t)
	}
	return ans
}
```

```ts
function divideArray(nums: number[], k: number): number[][] {
    nums.sort((a, b) => a - b);
    const ans: number[][] = [];
    for (let i = 0; i < nums.length; i += 3) {
        const t = nums.slice(i, i + 3);
        if (t[2] - t[0] > k) {
            return [];
        }
        ans.push(t);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
