---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20076.%20%E6%95%B0%E7%BB%84%E4%B8%AD%E7%9A%84%E7%AC%AC%20k%20%E5%A4%A7%E7%9A%84%E6%95%B0%E5%AD%97/README.md
---

<!-- problem:start -->

# [剑指 Offer II 076. 数组中的第 k 大的数字](https://leetcode.cn/problems/xx4gT2)

## 题目描述

<!-- description:start -->

<p>给定整数数组 <code>nums</code> 和整数 <code>k</code>，请返回数组中第 <code><strong>k</strong></code> 个最大的元素。</p>

<p>请注意，你需要找的是数组排序后的第 <code>k</code> 个最大的元素，而不是第 <code>k</code> 个不同的元素。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <code>[3,2,1,5,6,4] 和</code> k = 2
<strong>输出:</strong> 5
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> <code>[3,2,3,1,2,4,5,5,6] 和</code> k = 4
<strong>输出:</strong> 4</pre>

<p>&nbsp;</p>

<p><strong>提示： </strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 215&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/kth-largest-element-in-an-array/">https://leetcode.cn/problems/kth-largest-element-in-an-array/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        def quick_sort(left, right, k):
            if left == right:
                return nums[left]
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
            if j < k:
                return quick_sort(j + 1, right, k)
            return quick_sort(left, j, k)

        n = len(nums)
        return quick_sort(0, n - 1, n - k)
```

#### Java

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSort(nums, 0, n - 1, n - k);
    }

    private int quickSort(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        int i = left - 1, j = right + 1;
        int x = nums[(left + right) >>> 1];
        while (i < j) {
            while (nums[++i] < x)
                ;
            while (nums[--j] > x)
                ;
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        if (j < k) {
            return quickSort(nums, j + 1, right, k);
        }
        return quickSort(nums, left, j, k);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        int n = nums.size();
        return quickSort(nums, 0, n - 1, n - k);
    }

    int quickSort(vector<int>& nums, int left, int right, int k) {
        if (left == right) return nums[left];
        int i = left - 1, j = right + 1;
        int x = nums[left + right >> 1];
        while (i < j) {
            while (nums[++i] < x)
                ;
            while (nums[--j] > x)
                ;
            if (i < j) swap(nums[i], nums[j]);
        }
        return j < k ? quickSort(nums, j + 1, right, k) : quickSort(nums, left, j, k);
    }
};
```

#### Go

```go
func findKthLargest(nums []int, k int) int {
	n := len(nums)
	return quickSort(nums, 0, n-1, n-k)
}

func quickSort(nums []int, left, right, k int) int {
	if left == right {
		return nums[left]
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
	if j < k {
		return quickSort(nums, j+1, right, k)
	}
	return quickSort(nums, left, j, k)
}
```

#### Swift

```swift
class Solution {
    func findKthLargest(_ nums: [Int], _ k: Int) -> Int {
        var nums = nums
        let n = nums.count
        return quickSelect(&nums, 0, n - 1, n - k)
    }

    private func quickSelect(_ nums: inout [Int], _ left: Int, _ right: Int, _ k: Int) -> Int {
        if left == right {
            return nums[left]
        }

        var i = left - 1
        var j = right + 1
        let pivot = nums[(left + right) / 2]

        while i < j {
            repeat { i += 1 } while nums[i] < pivot
            repeat { j -= 1 } while nums[j] > pivot
            if i < j {
                nums.swapAt(i, j)
            }
        }

        if j < k {
            return quickSelect(&nums, j + 1, right, k)
        }
        return quickSelect(&nums, left, j, k)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
