# [665. 非递减数列](https://leetcode.cn/problems/non-decreasing-array)

[English Version](/solution/0600-0699/0665.Non-decreasing%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为&nbsp;<code>n</code>&nbsp;的整数数组<meta charset="UTF-8" />&nbsp;<code>nums</code>&nbsp;，请你判断在 <strong>最多 </strong>改变&nbsp;<code>1</code> 个元素的情况下，该数组能否变成一个非递减数列。</p>

<p>我们是这样定义一个非递减数列的：&nbsp;对于数组中任意的&nbsp;<code>i</code> <code>(0 &lt;= i &lt;= n-2)</code>，总满足 <code>nums[i] &lt;= nums[i + 1]</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,2,3]
<strong>输出:</strong> true
<strong>解释:</strong> 你可以通过把第一个 4 变成 1 来使得它成为一个非递减数列。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,2,1]
<strong>输出:</strong> false
<strong>解释:</strong> 你不能在只改变一个元素的情况下将其变为非递减数列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：两次遍历**

在最多改变一个元素的情况下，若要将数组变成非递减数列，那么数组最多只能有一个位置，其左右两侧的元素不满足非递减数列的要求。也即数组中只会存在一个位置 $i$，使得 $nums[i] \gt nums[i+1]$。

因此，我们可以从左到右遍历数组，找到第一个不满足非递减数列要求的位置 $i$，然后将 $nums[i]$ 修改为 $nums[i+1]$ 或者将 $nums[i+1]$ 修改为 $nums[i]$，再判断修改后的数组是否满足非递减数列的要求。如果满足，则返回 `true`，否则返回 `false`。

遍历结束后，如果没有找到不满足非递减数列要求的位置，说明数组本身就是非递减数列，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkPossibility(self, nums: List[int]) -> bool:
        def is_sorted(nums: List[int]) -> bool:
            return all(a <= b for a, b in pairwise(nums))

        n = len(nums)
        for i in range(n - 1):
            a, b = nums[i], nums[i + 1]
            if a > b:
                nums[i] = b
                if is_sorted(nums):
                    return True
                nums[i] = nums[i + 1] = a
                return is_sorted(nums)
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkPossibility(int[] nums) {
        for (int i = 0; i < nums.length - 1; ++i) {
            int a = nums[i], b = nums[i + 1];
            if (a > b) {
                nums[i] = b;
                if (isSorted(nums)) {
                    return true;
                }
                nums[i] = a;
                nums[i + 1] = a;
                return isSorted(nums);
            }
        }
        return true;
    }

    private boolean isSorted(int[] nums) {
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkPossibility(vector<int>& nums) {
        int n = nums.size();
        for (int i = 0; i < n - 1; ++i) {
            int a = nums[i], b = nums[i + 1];
            if (a > b) {
                nums[i] = b;
                if (is_sorted(nums.begin(), nums.end())) {
                    return true;
                }
                nums[i] = a;
                nums[i + 1] = a;
                return is_sorted(nums.begin(), nums.end());
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkPossibility(nums []int) bool {
	isSorted := func(nums []int) bool {
		for i, b := range nums[1:] {
			if nums[i] > b {
				return false
			}
		}
		return true
	}
	for i := 0; i < len(nums)-1; i++ {
		a, b := nums[i], nums[i+1]
		if a > b {
			nums[i] = b
			if isSorted(nums) {
				return true
			}
			nums[i] = a
			nums[i+1] = a
			return isSorted(nums)
		}
	}
	return true
}
```

### **TypeScript**

```ts
function checkPossibility(nums: number[]): boolean {
    const isSorted = (nums: number[]) => {
        for (let i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0; i < nums.length - 1; ++i) {
        const a = nums[i],
            b = nums[i + 1];
        if (a > b) {
            nums[i] = b;
            if (isSorted(nums)) {
                return true;
            }
            nums[i] = a;
            nums[i + 1] = a;
            return isSorted(nums);
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
