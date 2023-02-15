# [2340. 生成有效数组的最少交换次数](https://leetcode.cn/problems/minimum-adjacent-swaps-to-make-a-valid-array)

[English Version](/solution/2300-2399/2340.Minimum%20Adjacent%20Swaps%20to%20Make%20a%20Valid%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个<strong>&nbsp;下标从 0 开始</strong>&nbsp;的整数数组 <code>nums</code>。</p>

<p><code>nums</code>&nbsp;上的&nbsp;<strong>相邻&nbsp;</strong>元素可以进行&nbsp;<strong>交换</strong>。</p>

<p data-group="1-1">一个&nbsp;<strong>有效&nbsp;</strong>的数组必须满足以下条件:</p>

<ul>
	<li>最大的元素 (如果有多个，则为最大元素中的任何一个) 位于数组中最右边的位置。</li>
	<li>最小的元素 (如果有多个，则为最小的任何一个元素) 位于数组的最左侧。</li>
</ul>

<p>返回<em>使 </em><code>nums</code><em> </em><em>成为有效数组所需的最少交换次数。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [3,4,5,5,3,1]
<strong>输出:</strong> 6
<strong>解释:</strong> 进行以下交换:
- 交换 1:交换第 3 和第 4 个元素，然后 nums 是 [3,4,5,<u><strong>3</strong></u>,<u><strong>5</strong></u>,1].
- 交换 2:交换第 4 和第 5 个元素，然后 nums 是 [3,4,5,3,<u><strong>1</strong></u>,<u><strong>5</strong></u>].
- 交换 3:交换第 3 和第 4 个元素，然后 nums 是  [3,4,5,<u><strong>1</strong></u>,<u><strong>3</strong></u>,5].
- 交换 4:交换第 2 和第 3 个元素，然后 nums 是  [3,4,<u><strong>1</strong></u>,<u><strong>5</strong></u>,3,5].
- 交换 5:交换第 1 和第 2 个元素，然后 nums 是  [3,<u><strong>1</strong></u>,<u><strong>4</strong></u>,5,3,5].
- 交换 6:交换第 0 和第 1 个元素，然后 nums 是  [<u><strong>1</strong></u>,<u><strong>3</strong></u>,4,5,3,5].
可以证明，6 次交换是组成一个有效数组所需的最少交换次数。
</pre>

<strong class="example">示例 2:</strong>

<pre>
<strong>输入:</strong> nums = [9]
<strong>输出:</strong> 0
<strong>解释:</strong> 该数组已经有效，因此返回 0。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：维护最值下标 + 分类讨论**

我们可以用下标 $i$ 和 $j$ 分别记录数组 `nums` 第一个最小值和最后一个最大值的下标，遍历数组 `nums`，更新 $i$ 和 $j$ 的值。

接下来，我们需要考虑交换的次数。

-   如果 $i = j$，说明数组 `nums` 已经是有效数组，不需要交换，返回 $0$；
-   如果 $i < j$，说明数组 `nums` 中最小值在最大值的左边，需要交换 $i + n - 1 - j$ 次，其中 $n$ 为数组 `nums` 的长度；
-   如果 $i > j$，说明数组 `nums` 中最小值在最大值的右边，需要交换 $i + n - 1 - j - 1$ 次。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumSwaps(self, nums: List[int]) -> int:
        i = j = 0
        for k, v in enumerate(nums):
            if v < nums[i] or (v == nums[i] and k < i):
                i = k
            if v >= nums[j] or (v == nums[j] and k > j):
                j = k
        return 0 if i == j else i + len(nums) - 1 - j - (i > j)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumSwaps(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        for (int k = 0; k < n; ++k) {
            if (nums[k] < nums[i] || (nums[k] == nums[i] && k < i)) {
                i = k;
            }
            if (nums[k] > nums[j] || (nums[k] == nums[j] && k > j)) {
                j = k;
            }
        }
        if (i == j) {
            return 0;
        }
        return i + n - 1 - j - (i > j ? 1 : 0);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumSwaps(vector<int>& nums) {
        int n = nums.size();
        int i = 0, j = 0;
        for (int k = 0; k < n; ++k) {
            if (nums[k] < nums[i] || (nums[k] == nums[i] && k < i)) {
                i = k;
            }
            if (nums[k] > nums[j] || (nums[k] == nums[j] && k > j)) {
                j = k;
            }
        }
        if (i == j) {
            return 0;
        }
        return i + n - 1 - j - (i > j);
    }
};
```

### **Go**

```go
func minimumSwaps(nums []int) int {
	var i, j int
	for k, v := range nums {
		if v < nums[i] || (v == nums[i] && k < i) {
			i = k
		}
		if v > nums[j] || (v == nums[j] && k > j) {
			j = k
		}
	}
	if i == j {
		return 0
	}
	if i < j {
		return i + len(nums) - 1 - j
	}
	return i + len(nums) - 2 - j
}
```

### **TypeScript**

```ts
function minimumSwaps(nums: number[]): number {
    let i = 0;
    let j = 0;
    const n = nums.length;
    for (let k = 0; k < n; ++k) {
        if (nums[k] < nums[i] || (nums[k] == nums[i] && k < i)) {
            i = k;
        }
        if (nums[k] > nums[j] || (nums[k] == nums[j] && k > j)) {
            j = k;
        }
    }
    return i == j ? 0 : i + n - 1 - j - (i > j ? 1 : 0);
}
```

### **...**

```

```

<!-- tabs:end -->
