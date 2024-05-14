---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2459.Sort%20Array%20by%20Moving%20Items%20to%20Empty%20Space/README.md
tags:
    - 贪心
    - 数组
    - 排序
---

# [2459. 通过移动项目到空白区域来排序数组 🔒](https://leetcode.cn/problems/sort-array-by-moving-items-to-empty-space)

[English Version](/solution/2400-2499/2459.Sort%20Array%20by%20Moving%20Items%20to%20Empty%20Space/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个大小为 <code>n</code> 的整数数组 <code>nums</code>，其中包含从 <code>0</code> 到 <code>n - 1</code>&nbsp;(<strong>包含边界</strong>) 的&nbsp;<strong>每个&nbsp;</strong>元素。从 <code>1</code> 到 <code>n - 1</code> 的每一个元素都代表一项目，元素 <code>0</code> 代表一个空白区域。</p>

<p>在一个操作中，您可以将&nbsp;<strong>任何&nbsp;</strong>项目移动到空白区域。如果所有项目的编号都是&nbsp;<strong>升序&nbsp;</strong>的，并且空格在数组的开头或结尾，则认为 <code>nums</code> 已排序。</p>

<p data-group="1-1">例如，如果 <code>n = 4</code>，则 <code>nums</code> 按以下条件排序:</p>

<ul>
	<li><code>nums = [0,1,2,3]</code>&nbsp;或</li>
	<li><code>nums = [1,2,3,0]</code></li>
</ul>

<p>...否则被认为是无序的。</p>

<p>返回<em>排序&nbsp;<code>nums</code> 所需的最小操作数。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,2,0,3,1]
<strong>输出:</strong> 3
<strong>解释:</strong>
- 将项目 2 移动到空白区域。现在，nums =[4,0,2,3,1]。
- 将项目 1 移动到空白区域。现在，nums =[4,1,2,3,0]。
- 将项目 4 移动到空白区域。现在，nums =[0,1,2,3,4]。
可以证明，3 是所需的最小操作数。
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4,0]
<strong>输出:</strong> 0
<strong>解释:</strong> nums 已经排序了，所以返回 0。</pre>

<p><strong class="example">示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,0,2,4,3]
<strong>输出:</strong> 2
<strong>解释:</strong>
- 将项目 2 移动到空白区域。现在，nums =[1,2,0,4,3]。
- 将项目 3 移动到空白区域。现在，nums =[1,2,3,4,0]。
可以证明，2 是所需的最小操作数。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; n</code></li>
	<li><code>nums</code> 的所有值都是&nbsp;<strong>唯一&nbsp;</strong>的。</li>
</ul>

## 解法

### 方法一：置换环

一个长度为 $m$ 的置换环，如果 $0$ 在环中，那么交换次数为 $m-1$，否则交换次数为 $m+1$。

我们找到所有置换环，先按照交换次数为 $m+1$ 计算总的次数，然后判断 $0$ 是否错位，若是，说明 $0$ 在置换环中，那么总的次数减 $2$。

这里 $0$ 可以在 $0$ 位置，也可以在 $n-1$ 位置，我们取这两种情况的最小值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

```python
class Solution:
    def sortArray(self, nums: List[int]) -> int:
        def f(nums, k):
            vis = [False] * n
            cnt = 0
            for i, v in enumerate(nums):
                if i == v or vis[i]:
                    continue
                cnt += 1
                j = i
                while not vis[j]:
                    vis[j] = True
                    cnt += 1
                    j = nums[j]
            return cnt - 2 * (nums[k] != k)

        n = len(nums)
        a = f(nums, 0)
        b = f([(v - 1 + n) % n for v in nums], n - 1)
        return min(a, b)
```

```java
class Solution {
    public int sortArray(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = (nums[i] - 1 + n) % n;
        }
        int a = f(nums, 0);
        int b = f(arr, n - 1);
        return Math.min(a, b);
    }

    private int f(int[] nums, int k) {
        boolean[] vis = new boolean[nums.length];
        int cnt = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i == nums[i] || vis[i]) {
                continue;
            }
            ++cnt;
            int j = nums[i];
            while (!vis[j]) {
                vis[j] = true;
                ++cnt;
                j = nums[j];
            }
        }
        if (nums[k] != k) {
            cnt -= 2;
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int sortArray(vector<int>& nums) {
        int n = nums.size();
        auto f = [&](vector<int>& nums, int k) {
            vector<bool> vis(n);
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (i == nums[i] || vis[i]) continue;
                int j = i;
                ++cnt;
                while (!vis[j]) {
                    vis[j] = true;
                    ++cnt;
                    j = nums[j];
                }
            }
            if (nums[k] != k) cnt -= 2;
            return cnt;
        };

        int a = f(nums, 0);
        vector<int> arr = nums;
        for (int& v : arr) v = (v - 1 + n) % n;
        int b = f(arr, n - 1);
        return min(a, b);
    }
};
```

```go
func sortArray(nums []int) int {
	n := len(nums)
	f := func(nums []int, k int) int {
		vis := make([]bool, n)
		cnt := 0
		for i, v := range nums {
			if i == v || vis[i] {
				continue
			}
			cnt++
			j := i
			for !vis[j] {
				vis[j] = true
				cnt++
				j = nums[j]
			}
		}
		if nums[k] != k {
			cnt -= 2
		}
		return cnt
	}
	a := f(nums, 0)
	arr := make([]int, n)
	for i, v := range nums {
		arr[i] = (v - 1 + n) % n
	}
	b := f(arr, n-1)
	return min(a, b)
}
```

<!-- tabs:end -->

<!-- end -->
