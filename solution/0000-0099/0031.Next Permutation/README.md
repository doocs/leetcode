# [31. 下一个排列](https://leetcode.cn/problems/next-permutation)

[English Version](/solution/0000-0099/0031.Next%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>整数数组的一个 <strong>排列</strong>&nbsp; 就是将其所有成员以序列或线性顺序排列。</p>

<ul>
	<li>例如，<code>arr = [1,2,3]</code> ，以下这些都可以视作 <code>arr</code> 的排列：<code>[1,2,3]</code>、<code>[1,3,2]</code>、<code>[3,1,2]</code>、<code>[2,3,1]</code> 。</li>
</ul>

<p>整数数组的 <strong>下一个排列</strong> 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 <strong>下一个排列</strong> 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。</p>

<ul>
	<li>例如，<code>arr = [1,2,3]</code> 的下一个排列是 <code>[1,3,2]</code> 。</li>
	<li>类似地，<code>arr = [2,3,1]</code> 的下一个排列是 <code>[3,1,2]</code> 。</li>
	<li>而 <code>arr = [3,2,1]</code> 的下一个排列是 <code>[1,2,3]</code> ，因为 <code>[3,2,1]</code> 不存在一个字典序更大的排列。</li>
</ul>

<p>给你一个整数数组 <code>nums</code> ，找出 <code>nums</code> 的下一个排列。</p>

<p>必须<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地 </a></strong>修改，只允许使用额外常数空间。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[1,3,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,1]
<strong>输出：</strong>[1,2,3]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,5]
<strong>输出：</strong>[1,5,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：两次遍历**

我们先从后往前遍历数组 $nums$，找到第一个满足 $nums[i] \lt nums[i + 1]$ 的位置 $i$，那么 $nums[i]$ 就是我们需要交换的元素，而 $nums[i + 1]$ 到 $nums[n - 1]$ 的元素是一个降序序列。

接下来，我们再从后往前遍历数组 $nums$，找到第一个满足 $nums[j] \gt nums[i]$ 的位置 $j$，然后我们交换 $nums[i]$ 和 $nums[j]$。最后，我们将 $nums[i + 1]$ 到 $nums[n - 1]$ 的元素反转，即可得到下一个排列。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        n = len(nums)
        i = next((i for i in range(n - 2, -1, -1) if nums[i] < nums[i + 1]), -1)
        if ~i:
            j = next((j for j in range(n - 1, i, -1) if nums[j] > nums[i]))
            nums[i], nums[j] = nums[j], nums[i]
        nums[i + 1 :] = nums[i + 1 :][::-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        for (; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        if (i >= 0) {
            for (int j = n - 1; j > i; --j) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }

        for (int j = i + 1, k = n - 1; j < k; ++j, --k) {
            swap(nums, j, k);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void nextPermutation(vector<int>& nums) {
        int n = nums.size();
        int i = n - 2;
        while (~i && nums[i] >= nums[i + 1]) {
            --i;
        }
        if (~i) {
            for (int j = n - 1; j > i; --j) {
                if (nums[j] > nums[i]) {
                    swap(nums[i], nums[j]);
                    break;
                }
            }
        }
        reverse(nums.begin() + i + 1, nums.end());
    }
};
```

### **Go**

```go
func nextPermutation(nums []int) {
	n := len(nums)
	i := n - 2
	for ; i >= 0 && nums[i] >= nums[i+1]; i-- {
	}
	if i >= 0 {
		for j := n - 1; j > i; j-- {
			if nums[j] > nums[i] {
				nums[i], nums[j] = nums[j], nums[i]
				break
			}
		}
	}
	for j, k := i+1, n-1; j < k; j, k = j+1, k-1 {
		nums[j], nums[k] = nums[k], nums[j]
	}
}
```

### **TypeScript**

```ts
function nextPermutation(nums: number[]): void {
    const n = nums.length;
    let i = n - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        --i;
    }
    if (i >= 0) {
        for (let j = n - 1; j > i; --j) {
            if (nums[j] > nums[i]) {
                [nums[i], nums[j]] = [nums[j], nums[i]];
                break;
            }
        }
    }
    for (let j = n - 1; j > i; --j, ++i) {
        [nums[i + 1], nums[j]] = [nums[j], nums[i + 1]];
    }
}
```

### **C#**

```cs
public class Solution {
    public void NextPermutation(int[] nums) {
        int n = nums.Length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }
        if (i >= 0) {
            for (int j = n - 1; j > i; --j) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }
        for (int j = i + 1, k = n - 1; j < k; ++j, --k) {
            swap(nums, j, k);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
