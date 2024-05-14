---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0905.Sort%20Array%20By%20Parity/README.md
tags:
    - 数组
    - 双指针
    - 排序
---

# [905. 按奇偶排序数组](https://leetcode.cn/problems/sort-array-by-parity)

[English Version](/solution/0900-0999/0905.Sort%20Array%20By%20Parity/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code>，将 <code>nums</code> 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。</p>

<p>返回满足此条件的 <strong>任一数组</strong> 作为答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,2,4]
<strong>输出：</strong>[2,4,3,1]
<strong>解释：</strong>[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 5000</code></li>
</ul>

## 解法

### 方法一：双指针

我们用两个指针 $i$ 和 $j$ 分别指向数组的首尾，当 $i < j$ 时，执行以下操作。

-   如果 $nums[i]$ 为偶数，则 $i$ 自增 $1$。
-   如果 $nums[j]$ 为奇数，则 $j$ 自减 $1$。
-   如果 $nums[i]$ 为奇数，且 $nums[j]$ 为偶数，则交换 $nums[i]$ 和 $nums[j]$。然后 $i$ 自增 $1$，而 $j$ 自减 $1$。

最后返回数组 $nums$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def sortArrayByParity(self, nums: List[int]) -> List[int]:
        i, j = 0, len(nums) - 1
        while i < j:
            if nums[i] % 2 == 0:
                i += 1
            elif nums[j] % 2 == 1:
                j -= 1
            else:
                nums[i], nums[j] = nums[j], nums[i]
                i, j = i + 1, j - 1
        return nums
```

```java
class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] % 2 == 0) {
                ++i;
            } else if (nums[j] % 2 == 1) {
                --j;
            } else {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
                ++i;
                --j;
            }
        }
        return nums;
    }
}
```

```cpp
class Solution {
public:
    vector<int> sortArrayByParity(vector<int>& nums) {
        int i = 0, j = nums.size() - 1;
        while (i < j) {
            if (nums[i] % 2 == 0) {
                ++i;
            } else if (nums[j] % 2 == 1) {
                --j;
            } else {
                swap(nums[i++], nums[j--]);
            }
        }
        return nums;
    }
};
```

```go
func sortArrayByParity(nums []int) []int {
	for i, j := 0, len(nums)-1; i < j; {
		if nums[i]%2 == 0 {
			i++
		} else if nums[j]%2 == 1 {
			j--
		} else {
			nums[i], nums[j] = nums[j], nums[i]
		}
	}
	return nums
}
```

```ts
function sortArrayByParity(nums: number[]): number[] {
    for (let i = 0, j = nums.length - 1; i < j; ) {
        if (nums[i] % 2 === 0) {
            ++i;
        } else if (nums[j] % 2 === 1) {
            --j;
        } else {
            [nums[i], nums[j]] = [nums[j], nums[i]];
            ++i;
            --j;
        }
    }
    return nums;
}
```

```rust
impl Solution {
    pub fn sort_array_by_parity(mut nums: Vec<i32>) -> Vec<i32> {
        let (mut i, mut j) = (0, nums.len() - 1);
        while i < j {
            if nums[i] % 2 == 0 {
                i += 1;
            } else if nums[j] % 2 == 1 {
                j -= 1;
            } else {
                nums.swap(i, j);
                i += 1;
                j -= 1;
            }
        }
        nums
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var sortArrayByParity = function (nums) {
    for (let i = 0, j = nums.length - 1; i < j; ) {
        if (nums[i] % 2 === 0) {
            ++i;
        } else if (nums[j] % 2 === 1) {
            --j;
        } else {
            [nums[i], nums[j]] = [nums[j], nums[i]];
            ++i;
            --j;
        }
    }
    return nums;
};
```

<!-- tabs:end -->

<!-- end -->
