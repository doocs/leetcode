# [493. Reverse Pairs](https://leetcode.com/problems/reverse-pairs)

[中文文档](/solution/0400-0499/0493.Reverse%20Pairs/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the number of <strong>reverse pairs</strong> in the array</em>.</p>

<p>A reverse pair is a pair <code>(i, j)</code> where <code>0 &lt;= i &lt; j &lt; nums.length</code> and <code>nums[i] &gt; 2 * nums[j]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,3,2,3,1]
<strong>Output:</strong> 2
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,4,3,5,1]
<strong>Output:</strong> 3
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        def merge_sort(nums, left, right):
            if left >= right:
                return 0
            mid = (left + right) >> 1
            res = merge_sort(nums, left, mid) + \
                merge_sort(nums, mid + 1, right)
            i, j = left, mid + 1
            while i <= mid and j <= right:
                if nums[i] <= 2 * nums[j]:
                    i += 1
                else:
                    res += (mid - i + 1)
                    j += 1
            tmp = []
            i, j = left, mid + 1
            while i <= mid and j <= right:
                if nums[i] <= nums[j]:
                    tmp.append(nums[i])
                    i += 1
                else:
                    tmp.append(nums[j])
                    j += 1
            while i <= mid:
                tmp.append(nums[i])
                i += 1
            while j <= right:
                tmp.append(nums[j])
                j += 1
            for i in range(left, right + 1):
                nums[i] = tmp[i - left]
            return res

        return merge_sort(nums, 0, len(nums) - 1)
```

### **Java**

```java
class Solution {
    private static int[] tmp = new int[50010];

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int res = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if ((long) nums[i] <= (long) 2 * nums[j]) {
                ++i;
            } else {
                res += (mid - i + 1);
                ++j;
            }
        }
        i = left;
        j = mid + 1;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        for (i = left; i <= right; ++i) {
            nums[i] = tmp[i - left];
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int reversePairs(vector<int>& nums) {
        int n = nums.size();
        vector<int> temp(n);
        return mergeSort(nums, temp, 0, n - 1);
    }

private:
    int mergeSort(vector<int>& nums, vector<int>& temp, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int m = l + r >> 1;
        int count = mergeSort(nums, temp, l, m) + mergeSort(nums, temp, m + 1, r);
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            if ((long long) nums[i] <= (long long) 2 * nums[j]) {
                ++i;
            } else {
                count += (m - i + 1);
                ++j;
            }
        }
        i = l;
        j = m + 1;
        while (i <= m || j <= r) {
            if (i > m) {
                temp[k++] = nums[j++];
            } else if (j > r || nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        copy(temp.begin() + l, temp.begin() + r + 1, nums.begin() + l);
        return count;
    }
};
```

### **Go**

```go
func reversePairs(nums []int) int {
	return mergeSort(nums, 0, len(nums)-1)
}

func mergeSort(nums []int, left, right int) int {
	if left >= right {
		return 0
	}
	mid := (left + right) >> 1
	res := mergeSort(nums, left, mid) + mergeSort(nums, mid+1, right)
	i, j := left, mid+1
	for i <= mid && j <= right {
		if nums[i] <= 2*nums[j] {
			i++
		} else {
			res += (mid - i + 1)
			j++
		}
	}
	i, j = left, mid+1
	var tmp []int
	for i <= mid && j <= right {
		if nums[i] <= nums[j] {
			tmp = append(tmp, nums[i])
			i++
		} else {
			tmp = append(tmp, nums[j])
			j++
		}
	}
	for i <= mid {
		tmp = append(tmp, nums[i])
		i++
	}
	for j <= right {
		tmp = append(tmp, nums[j])
		j++
	}
	for i = left; i <= right; i++ {
		nums[i] = tmp[i-left]
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
