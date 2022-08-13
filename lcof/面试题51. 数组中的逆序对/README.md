# [面试题 51. 数组中的逆序对](https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入</strong>: [7,5,6,4]
<strong>输出</strong>: 5</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 数组长度 &lt;= 50000</code></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

在归并中统计逆序对。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        def merge_sort(nums, left, right):
            if left >= right:
                return 0
            mid = (left + right) >> 1
            res = merge_sort(nums, left, mid) + merge_sort(nums, mid + 1, right)
            i, j = left, mid + 1
            tmp = []
            while i <= mid and j <= right:
                if nums[i] <= nums[j]:
                    tmp.append(nums[i])
                    i += 1
                else:
                    res += mid - i + 1
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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                res += (mid - i + 1);
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

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var reversePairs = function (nums) {
    const mergeSort = (nums, left, right) => {
        if (left >= right) {
            return 0;
        }
        const mid = (left + right) >> 1;
        let res = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        let i = left;
        let j = mid + 1;
        let tmp = [];
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp.push(nums[i++]);
            } else {
                tmp.push(nums[j++]);
                res += mid - i + 1;
            }
        }
        while (i <= mid) {
            tmp.push(nums[i++]);
        }
        while (j <= right) {
            tmp.push(nums[j++]);
        }
        for (i = left, j = 0; i <= right; ++i, ++j) {
            nums[i] = tmp[j];
        }
        return res;
    };

    return mergeSort(nums, 0, nums.length - 1);
};
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
        int m = l + (r - l) / 2;
        int count = mergeSort(nums, temp, l, m) + mergeSort(nums, temp, m + 1, r);
        int i = l, j = m + 1, k = l;
        while (i <= m || j <= r) {
            if (i > m) {
                temp[k++] = nums[j++];
            } else if (j > r || nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                count += m - i + 1;
                temp[k++] = nums[j++];
            }
        }
        copy(temp.begin() + l, temp.begin() + r + 1, nums.begin() + l);
        return count;
    }
};
```

### **TypeScript**

```ts
function reversePairs(nums: number[]): number {
    let count: number = 0;
    const n: number = nums.length;
    if (n < 2) return 0;

    function merge(
        nums: number[],
        left: number,
        mid: number,
        right: number,
    ): void {
        let n: number = right - left + 1;
        let t: number[] = new Array(n);
        let i: number = left,
            j: number = mid + 1,
            idx: number = 0;
        while (i <= mid && j <= right) {
            if (nums[i] > nums[j]) {
                count += mid - i + 1;
                t[idx++] = nums[j++];
            } else {
                t[idx++] = nums[i++];
            }
        }
        while (i <= mid) {
            t[idx++] = nums[i++];
        }
        while (j <= right) {
            t[idx++] = nums[j++];
        }
        for (let k: number = 0; k < n; ++k) {
            nums[left + k] = t[k];
        }
    }

    function mergeSort(nums: number[], left: number, right: number): void {
        if (left == right) return;
        let mid: number = (left + right) >> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    mergeSort(nums, 0, n - 1);
    return count;
}
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
	var tmp []int
	for i <= mid && j <= right {
		if nums[i] <= nums[j] {
			tmp = append(tmp, nums[i])
			i++
		} else {
			res += (mid - i + 1)
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

### **C#**

```cs
public class Solution {
    int[] nums, aux;
    public int ReversePairs(int[] nums) {
        int n = nums.Length;
        if (n == 0) {
            return 0;
        }
        this.nums = nums;
        aux = new int[n];
        return Merge(0, n - 1);
    }

    int Merge(int l, int r)
    {
        if (l == r) {
            return 0;
        }
        var mid = (l + r) >> 1;
        int ans = Merge(l, mid) + Merge(mid + 1, r);
        for (int k = l; k <= r; k++) {
            aux[k] = nums[k];
        }
        for (int i = l, j = mid + 1, k = l; k <= r; k++)
        {
            if (i == mid + 1) {
                nums[k] = aux[j++];
            } else if (j == r + 1) {
                nums[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                nums[k] = aux[i++];
            } else {
                nums[k] = aux[j++];
                ans += mid + 1 - i;
            }
        }
        return ans;
    }

}
```

### **...**

```

```

<!-- tabs:end -->
