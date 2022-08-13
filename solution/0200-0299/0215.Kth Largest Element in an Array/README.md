# [215. 数组中的第 K 个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array)

[English Version](/solution/0200-0299/0215.Kth%20Largest%20Element%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定整数数组 <code>nums</code> 和整数 <code>k</code>，请返回数组中第 <code><strong>k</strong></code> 个最大的元素。</p>

<p>请注意，你需要找的是数组排序后的第 <code>k</code> 个最大的元素，而不是第 <code>k</code> 个不同的元素。</p>

<p>你必须设计并实现时间复杂度为 <code>O(n)</code> 的算法解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <code>[3,2,1,5,6,4],</code> k = 2
<strong>输出:</strong> 5
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> <code>[3,2,3,1,2,4,5,5,6], </code>k = 4
<strong>输出:</strong> 4</pre>

<p>&nbsp;</p>

<p><strong>提示： </strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

将数组 $nums$ 升序排列，然后获取 $nums[n-k]$。

时间复杂度 $O(nlogn)$，其中 $n$ 表示数组 $nums$ 的长度。

**方法二：partition**

并不是所有时候，都需要整个数组进入有序状态，只需要**局部有序**，或者说，从大到小排序，只要 $[0..k)$ 位置的元素有序，那么就能确定结果，此处使用**快速排序**。

快速排序有一特点，每一次循环结束时，能够确定的是：$partition$ 一定处于它该处于的索引位置。从而根据它得知，结果值是在左数组还是在右数组当中，然后对那一数组进行排序即可。

时间复杂度 $O(n)$，其中 $n$ 表示数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
            while (nums[++i] < x);
            while (nums[--j] > x);
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

### **C++**

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

### **Go**

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

### **TypeScript**

```ts
function findKthLargest(nums: number[], k: number): number {
    const n = nums.length;
    const swap = (i: number, j: number) => {
        [nums[i], nums[j]] = [nums[j], nums[i]];
    };
    const sort = (l: number, r: number) => {
        if (l + 1 > k || l >= r) {
            return;
        }
        swap(l, l + Math.floor(Math.random() * (r - l)));
        const num = nums[l];
        let mark = l;
        for (let i = l + 1; i < r; i++) {
            if (nums[i] > num) {
                mark++;
                swap(i, mark);
            }
        }
        swap(l, mark);

        sort(l, mark);
        sort(mark + 1, r);
    };
    sort(0, n);
    return nums[k - 1];
}
```

### **Rust**

```rust
use rand::Rng;

impl Solution {
    fn sort(nums: &mut Vec<i32>, l: usize, r: usize, k: usize) {
        if l + 1 > k || l >= r {
            return;
        }
        nums.swap(l, rand::thread_rng().gen_range(l, r));
        let num = nums[l];
        let mut mark = l;
        for i in l..r {
            if nums[i] > num {
                mark += 1;
                nums.swap(i, mark);
            }
        }
        nums.swap(l, mark);

        Self::sort(nums, l, mark, k);
        Self::sort(nums, mark + 1, r, k);
    }

    pub fn find_kth_largest(mut nums: Vec<i32>, k: i32) -> i32 {
        let n = nums.len();
        let k = k as usize;
        Self::sort(&mut nums, 0, n, k);
        nums[k - 1]
    }
}
```

```rust
use rand::Rng;

impl Solution {
    pub fn find_kth_largest(mut nums: Vec<i32>, k: i32) -> i32 {
        let k = k as usize;
        let n = nums.len();
        let mut l = 0;
        let mut r = n;
        while l <= k - 1 && l < r {
            nums.swap(l, rand::thread_rng().gen_range(l, r));
            let num = nums[l];
            let mut mark = l;
            for i in l..r {
                if nums[i] > num {
                    mark += 1;
                    nums.swap(i, mark);
                }
            }
            nums.swap(l, mark);
            if mark + 1 <= k {
                l = mark + 1;
            } else {
                r = mark;
            }
        }
        nums[k - 1]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
