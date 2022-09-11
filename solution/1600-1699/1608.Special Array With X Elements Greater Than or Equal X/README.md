# [1608. 特殊数组的特征值](https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x)

[English Version](/solution/1600-1699/1608.Special%20Array%20With%20X%20Elements%20Greater%20Than%20or%20Equal%20X/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个非负整数数组 <code>nums</code> 。如果存在一个数 <code>x</code> ，使得 <code>nums</code> 中恰好有 <code>x</code> 个元素 <strong>大于或者等于</strong> <code>x</code> ，那么就称 <code>nums</code> 是一个 <strong>特殊数组</strong> ，而 <code>x</code> 是该数组的 <strong>特征值</strong> 。</p>

<p>注意： <code>x</code> <strong>不必</strong> 是 <code>nums</code> 的中的元素。</p>

<p>如果数组 <code>nums</code> 是一个 <strong>特殊数组</strong> ，请返回它的特征值 <code>x</code> 。否则，返回<em> </em><code>-1</code> 。可以证明的是，如果 <code>nums</code> 是特殊数组，那么其特征值 <code>x</code> 是 <strong>唯一的</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [3,5]
<strong>输出：</strong>2
<strong>解释：</strong>有 2 个元素（3 和 5）大于或等于 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [0,0]
<strong>输出：</strong>-1
<strong>解释：</strong>没有满足题目要求的特殊数组，故而也不存在特征值 x 。
如果 x = 0，应该有 0 个元素 &gt;= x，但实际有 2 个。
如果 x = 1，应该有 1 个元素 &gt;= x，但实际有 0 个。
如果 x = 2，应该有 2 个元素 &gt;= x，但实际有 0 个。
x 不能取更大的值，因为 nums 中只有两个元素。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [0,4,3,0,4]
<strong>输出：</strong>3
<strong>解释：</strong>有 3 个元素大于或等于 3 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nums = [3,6,7,7,0]
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举**

在 $[1..n]$ 范围内枚举 $x$，然后统计数组中大于等于 $x$ 的元素个数，记为 $cnt$。若存在 $cnt$ 与 $x$ 相等，直接返回 $x$。

时间复杂度 $O(n^2)$。

**方法二：排序 + 二分查找**

我们也可以先对 `nums` 进行排序。

接下来同样枚举 $x$，利用二分查找，找到 `nums` 中第一个大于等于 $x$ 的元素，快速统计出 `nums` 中大于等于 $x$ 的元素个数。

时间复杂度 $O(n\log n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def specialArray(self, nums: List[int]) -> int:
        for x in range(1, len(nums) + 1):
            cnt = sum(v >= x for v in nums)
            if cnt == x:
                return x
        return -1
```

```python
class Solution:
    def specialArray(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)
        for x in range(1, n + 1):
            cnt = n - bisect_left(nums, x)
            if cnt == x:
                return x
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int specialArray(int[] nums) {
        for (int x = 1; x <= nums.length; ++x) {
            int cnt = 0;
            for (int v : nums) {
                if (v >= x) {
                    ++cnt;
                }
            }
            if (cnt == x) {
                return x;
            }
        }
        return -1;
    }
}
```

```java
class Solution {
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int x = 1; x <= n; ++x) {
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (nums[mid] >= x) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int cnt = n - left;
            if (cnt == x) {
                return x;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int specialArray(vector<int>& nums) {
        for (int x = 1; x <= nums.size(); ++x) {
            int cnt = 0;
            for (int v : nums) cnt += v >= x;
            if (cnt == x) return x;
        }
        return -1;
    }
};
```

```cpp
class Solution {
public:
    int specialArray(vector<int>& nums) {
        int n = nums.size();
        sort(nums.begin(), nums.end());
        for (int x = 1; x <= n; ++x) {
            int cnt = n - (lower_bound(nums.begin(), nums.end(), x) - nums.begin());
            if (cnt == x) return x;
        }
        return -1;
    }
};
```

### **Go**

```go
func specialArray(nums []int) int {
	for x := 1; x <= len(nums); x++ {
		cnt := 0
		for _, v := range nums {
			if v >= x {
				cnt++
			}
		}
		if cnt == x {
			return x
		}
	}
	return -1
}
```

```go
func specialArray(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	for x := 1; x <= n; x++ {
		left, right := 0, n
		for left < right {
			mid := (left + right) >> 1
			if nums[mid] >= x {
				right = mid
			} else {
				left = mid + 1
			}
		}
		cnt := n - left
		if cnt == x {
			return x
		}
	}
	return -1
}
```

### **TypeScript**

```ts
function specialArray(nums: number[]): number {
    const n = nums.length;
    for (let i = 0; i <= n; i++) {
        if (i === nums.reduce((r, v) => r + (v >= i ? 1 : 0), 0)) {
            return i;
        }
    }
    return -1;
}
```

```ts
function specialArray(nums: number[]): number {
    const n = nums.length;
    let left = 0;
    let right = n + 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        const count = nums.reduce((r, v) => r + (v >= mid ? 1 : 0), 0);

        if (count === mid) {
            return mid;
        }

        if (count > mid) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return -1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn special_array(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        for i in 0..=n {
            let mut count = 0;
            for &num in nums.iter() {
                if num >= i {
                    count += 1;
                }
            }
            if count == i {
                return i;
            }
        }
        -1
    }
}
```

```rust
use std::cmp::Ordering;
impl Solution {
    pub fn special_array(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let mut left = 0;
        let mut right = n + 1;
        while left < right {
            let mid = left + (right - left) / 2;
            let mut count = 0;
            for &num in nums.iter() {
                if num >= mid {
                    count += 1;
                }
            }
            match count.cmp(&mid) {
                Ordering::Equal => {
                    return mid;
                }
                Ordering::Less => {
                    right = mid;
                }
                Ordering::Greater => {
                    left = mid + 1;
                }
            }
        }
        -1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
