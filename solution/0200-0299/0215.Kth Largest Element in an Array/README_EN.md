# [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array)

[中文文档](/solution/0200-0299/0215.Kth%20Largest%20Element%20in%20an%20Array/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the</em> <code>k<sup>th</sup></code> <em>largest element in the array</em>.</p>

<p>Note that it is the <code>k<sup>th</sup></code> largest element in the sorted order, not the <code>k<sup>th</sup></code> distinct element.</p>

<p>You must solve it in <code>O(n)</code> time complexity.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,1,5,6,4], k = 2
<strong>Output:</strong> 5
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,3,1,2,4,5,5,6], k = 4
<strong>Output:</strong> 4
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
