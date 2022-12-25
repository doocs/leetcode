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

**方法一：贪心 + 分类讨论**

我们先找出数组中第一个最小值和最后一个最大值的位置，分别记为 $i$ 和 $j$。

如果 $i$ 和 $j$ 相等，说明数组已经是有效的，直接返回 $0$ 即可。

否则，我们判断 $i$ 是否在 $j$ 的左边，如果是，那么交换次数为 $i + n - 1 - j$；否则，交换次数为 $i + n - 2 - j$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumSwaps(self, nums: List[int]) -> int:
        mi, mx = min(nums), max(nums)
        i, j = -1, -1
        for k, v in enumerate(nums):
            if v == mi and i == -1:
                i = k
            if v == mx:
                j = k
        if i == j:
            return 0
        n = len(nums)
        if i < j:
            return i + n - 1 - j
        return i + n - 2 - j
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumSwaps(int[] nums) {
        int n = nums.length;
        int mi = min(nums), mx = max(nums);
        int i = -1, j = -1;
        for (int k = 0; k < n; ++k) {
            if (nums[k] == mi && i == -1) {
                i = k;
            }
            if (nums[k] == mx) {
                j = k;
            }
        }
        if (i == j) {
            return 0;
        }
        return i < j ? i + n - 1 - j : i + n - 2 - j;
    }

    private int max(int[] nums) {
        int v = 0;
        for (int x : nums) {
            v = Math.max(v, x);
        }
        return v;
    }

    private int min(int[] nums) {
        int v = nums[0];
        for (int x : nums) {
            v = Math.min(v, x);
        }
        return v;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumSwaps(vector<int>& nums) {
        int n = nums.size();
        int mi = *min_element(nums.begin(), nums.end());
        int mx = *max_element(nums.begin(), nums.end());
        int i = -1, j = -1;
        for (int k = 0; k < n; ++k) {
            if (nums[k] == mi && i == -1) i = k;
            if (nums[k] == mx) j = k;
        }
        if (i == j) return 0;
        return i < j ? i + n - 1 - j : i + n - 2 - j;
    }
};
```

### **Go**

```go
func minimumSwaps(nums []int) int {
	mi, mx := nums[0], 0
	for _, v := range nums {
		mi = min(mi, v)
		mx = max(mx, v)
	}
	i, j := -1, -1
	for k, v := range nums {
		if v == mi && i == -1 {
			i = k
		}
		if v == mx {
			j = k
		}
	}
	if i == j {
		return 0
	}
	n := len(nums)
	if i < j {
		return i + n - 1 - j
	}
	return i + n - 2 - j
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
