# [912. 排序数组](https://leetcode-cn.com/problems/sort-an-array)

[English Version](/solution/0900-0999/0912.Sort%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>，请你将该数组升序排列。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [5,2,3,1]
<strong>输出：</strong>[1,2,3,5]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [5,1,1,2,0,0]
<strong>输出：</strong>[0,0,1,1,2,5]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= nums.length &lt;= 50000</code></li>
	<li><code>-50000 &lt;= nums[i] &lt;= 50000</code></li>
</ol>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sortArray(self, nums: List[int]) -> List[int]:
        def quick_sort(nums, left, right):
            if left >= right:
                return
            i, j = left - 1, right + 1
            x = nums[(left + right) >> 1]
            while i < j:
                while 1:
                    i += 1
                    if nums[i] >= x:
                        break
                while 1:
                    j -= 1
                    if nums[j] <= x:
                        break
                if i < j:
                    nums[i], nums[j] = nums[j], nums[i]
            quick_sort(nums, left, j)
            quick_sort(nums, j + 1, right)

        quick_sort(nums, 0, len(nums) - 1)
        return nums
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left - 1, j = right + 1;
        int x = nums[(left + right) >> 1];
        while (i < j) {
            while (nums[++i] < x);
            while (nums[--j] > x);
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        quickSort(nums, left, j);
        quickSort(nums, j + 1, right);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> sortArray(vector<int>& nums) {
        quick_sort(nums, 0, nums.size() - 1);
        return nums;
    }

    void quick_sort(vector<int>& nums, int left, int right) {
        if (left >= right) return;
        int i = left - 1, j = right + 1;
        int x = nums[left + right >> 1];
        while (i < j)
        {
            while (nums[++i] < x);
            while (nums[--j] > x);
            if (i < j) swap(nums[i], nums[j]);
        }
        quick_sort(nums, left, j);
        quick_sort(nums, j + 1, right);
    }
};
```

### **Go**

```go
func sortArray(nums []int) []int {
	quickSort(nums, 0, len(nums)-1)
	return nums
}

func quickSort(nums []int, left, right int) {
	if left >= right {
		return
	}
	i, j := left-1, right+1
	x := nums[(left+right)>>1]
	for i < j {
		for {
			i++
			if nums[i] >= x {
				break
			}
		}
		for {
			j--
			if nums[j] <= x {
				break
			}
		}
		if i < j {
			nums[i], nums[j] = nums[j], nums[i]
		}
	}
	quickSort(nums, left, j)
	quickSort(nums, j+1, right)
}
```

### **...**

```

```

<!-- tabs:end -->
