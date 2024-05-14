---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3069.Distribute%20Elements%20Into%20Two%20Arrays%20I/README.md
rating: 1203
tags:
    - 数组
    - 模拟
---

# [3069. 将元素分配到两个数组中 I](https://leetcode.cn/problems/distribute-elements-into-two-arrays-i)

[English Version](/solution/3000-3099/3069.Distribute%20Elements%20Into%20Two%20Arrays%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>1</strong> 开始、包含<strong> 不同 </strong>整数的数组 <code>nums</code> ，数组长度为 <code>n</code> 。</p>

<p>你需要通过 <code>n</code> 次操作，将 <code>nums</code> 中的所有元素分配到两个数组 <code>arr1</code> 和 <code>arr2</code> 中。在第一次操作中，将 <code>nums[1]</code> 追加到 <code>arr1</code> 。在第二次操作中，将 <code>nums[2]</code> 追加到 <code>arr2</code> 。之后，在第 <code>i</code> 次操作中：</p>

<ul>
	<li>如果 <code>arr1</code> 的最后一个元素 <strong>大于 </strong><code>arr2</code> 的最后一个元素，就将 <code>nums[i]</code> 追加到 <code>arr1</code> 。否则，将 <code>nums[i]</code> 追加到 <code>arr2</code> 。</li>
</ul>

<p>通过连接数组 <code>arr1</code> 和 <code>arr2</code> 形成数组 <code>result</code> 。例如，如果 <code>arr1 == [1,2,3]</code> 且 <code>arr2 == [4,5,6]</code> ，那么 <code>result = [1,2,3,4,5,6]</code> 。</p>

<p>返回数组 <code>result</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,3]
<strong>输出：</strong>[2,3,1]
<strong>解释：</strong>在前两次操作后，arr1 = [2] ，arr2 = [1] 。
在第 3 次操作中，由于 arr1 的最后一个元素大于 arr2 的最后一个元素（2 &gt; 1），将 nums[3] 追加到 arr1 。
3 次操作后，arr1 = [2,3] ，arr2 = [1] 。
因此，连接形成的数组 result 是 [2,3,1] 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,4,3,8]
<strong>输出：</strong>[5,3,4,8]
<strong>解释：</strong>在前两次操作后，arr1 = [5] ，arr2 = [4] 。
在第 3 次操作中，由于 arr1 的最后一个元素大于 arr2 的最后一个元素（5 &gt; 4），将 nums[3] 追加到 arr1 ，因此 arr1 变为 [5,3] 。
在第 4 次操作中，由于 arr2 的最后一个元素大于 arr1 的最后一个元素（4 &gt; 3），将 nums[4] 追加到 arr2 ，因此 arr2 变为 [4,8] 。
4 次操作后，arr1 = [5,3] ，arr2 = [4,8] 。
因此，连接形成的数组 result 是 [5,3,4,8] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code>中的所有元素都互不相同。</li>
</ul>

## 解法

### 方法一：模拟

我们创建两个数组 `arr1` 和 `arr2`，分别存放 `nums` 中的元素，初始时 `arr1` 中只有 `nums[0]`，`arr2` 中只有 `nums[1]`。

然后遍历 `nums` 下标从 $2$ 开始的元素，如果 `arr1` 的最后一个元素大于 `arr2` 的最后一个元素，就将当前元素追加到 `arr1`，否则追加到 `arr2`。

最后将 `arr2` 中的元素追加到 `arr1` 中，返回 `arr1`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def resultArray(self, nums: List[int]) -> List[int]:
        arr1 = [nums[0]]
        arr2 = [nums[1]]
        for x in nums[2:]:
            if arr1[-1] > arr2[-1]:
                arr1.append(x)
            else:
                arr2.append(x)
        return arr1 + arr2
```

```java
class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        arr1[0] = nums[0];
        arr2[0] = nums[1];
        int i = 0, j = 0;
        for (int k = 2; k < n; ++k) {
            if (arr1[i] > arr2[j]) {
                arr1[++i] = nums[k];
            } else {
                arr2[++j] = nums[k];
            }
        }
        for (int k = 0; k <= j; ++k) {
            arr1[++i] = arr2[k];
        }
        return arr1;
    }
}
```

```cpp
class Solution {
public:
    vector<int> resultArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> arr1 = {nums[0]};
        vector<int> arr2 = {nums[1]};
        for (int k = 2; k < n; ++k) {
            if (arr1.back() > arr2.back()) {
                arr1.push_back(nums[k]);
            } else {
                arr2.push_back(nums[k]);
            }
        }
        arr1.insert(arr1.end(), arr2.begin(), arr2.end());
        return arr1;
    }
};
```

```go
func resultArray(nums []int) []int {
	arr1 := []int{nums[0]}
	arr2 := []int{nums[1]}
	for _, x := range nums[2:] {
		if arr1[len(arr1)-1] > arr2[len(arr2)-1] {
			arr1 = append(arr1, x)
		} else {
			arr2 = append(arr2, x)
		}
	}
	return append(arr1, arr2...)
}
```

```ts
function resultArray(nums: number[]): number[] {
    const arr1: number[] = [nums[0]];
    const arr2: number[] = [nums[1]];
    for (const x of nums.slice(2)) {
        if (arr1.at(-1)! > arr2.at(-1)!) {
            arr1.push(x);
        } else {
            arr2.push(x);
        }
    }
    return arr1.concat(arr2);
}
```

<!-- tabs:end -->

<!-- end -->
