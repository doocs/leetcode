# [面试题 08.03. 魔术索引](https://leetcode.cn/problems/magic-index-lcci)

[English Version](/lcci/08.03.Magic%20Index/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>魔术索引。 在数组<code>A[0...n-1]</code>中，有所谓的魔术索引，满足条件<code>A[i] = i</code>。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：nums = [0, 2, 3, 4, 5]
<strong> 输出</strong>：0
<strong> 说明</strong>: 0下标的元素为0
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：nums = [1, 1, 1]
<strong> 输出</strong>：1
</pre>

<p><strong>提示:</strong></p>

<ol>
	<li>nums长度在[1, 1000000]之间</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**线性查找：**

遍历数组，当 `A[i] = i` 时直接返回即可。

**优化：**

在遍历的基础，进行可能的 "跳跃"，结束时执行 `i = max(A[i], i + 1)`，而不再单纯 `i++`。

可行性证明：

因为数组是**有序**的，若 `A[i] != i`，那么就可以将 `A[i]` 以下的可能全部排除，直接将 `i` 设定为 `A[i]`。

若是考虑最糟的状况（所有元素都为负数），则该优化与遍历无差别。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMagicIndex(self, nums: List[int]) -> int:
        def find(nums, left, right):
            if left > right:
                return -1
            mid = (left + right) >> 1
            left_index = find(nums, left, mid - 1)
            if left_index != -1:
                return left_index
            if nums[mid] == mid:
                return mid
            return find(nums, mid + 1, right)

        return find(nums, 0, len(nums) - 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findMagicIndex(int[] nums) {
        int left = 0, right = nums.length - 1;
        return find(nums, left, right);
    }

    private int find(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >> 1;
        int leftIndex = find(nums, left, mid - 1);
        if (leftIndex != -1) {
            return leftIndex;
        }
        if (nums[mid] == mid) {
            return mid;
        }
        return find(nums, mid + 1, right);
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMagicIndex = function (nums) {
    return helper(nums, 0, nums.length - 1);
};

function helper(nums, left, right) {
    if (left > right) return -1;
    let mid = Math.floor((left + right) / 2);
    let leftIndex = helper(nums, left, mid - 1);
    if (leftIndex != -1) return leftIndex;
    if (nums[mid] == mid) return mid;
    return helper(nums, mid + 1, right);
}
```

### **C++**

```cpp
class Solution {
public:
    int findMagicIndex(vector<int>& nums) {
        return find(nums, 0, nums.size() - 1);
    }

    int find(vector<int>& nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + right >> 1;
        int leftIndex = find(nums, left, mid - 1);
        if (leftIndex != -1) {
            return leftIndex;
        }
        if (nums[mid] == mid) {
            return mid;
        }
        return find(nums, mid + 1, right);
    }
};
```

### **Go**

```go
func findMagicIndex(nums []int) int {
	return find(nums, 0, len(nums)-1)
}

func find(nums []int, left, right int) int {
	if left > right {
		return -1
	}
	mid := (left + right) >> 1
	leftIndex := find(nums, left, mid-1)
	if leftIndex != -1 {
		return leftIndex
	}
	if nums[mid] == mid {
		return mid
	}
	return find(nums, mid+1, right)
}
```

### **TypeScript**

```ts
function findMagicIndex(nums: number[]): number {
    const n = nums.length;
    const find = (l: number, r: number): number => {
        if (l > r || nums[r] < 0) {
            return -1;
        }
        const mid = l + Math.floor((r - l) / 2);
        if (nums[mid] >= l) {
            const res = find(l, mid - 1);
            if (res !== -1) {
                return res;
            }
        }
        if (nums[mid] === mid) {
            return mid;
        }
        return find(mid + 1, r);
    };
    return find(0, n - 1);
}
```

```ts
function findMagicIndex(nums: number[]): number {
    const n = nums.length;
    let i = 0;
    while (i < n) {
        if (nums[i] === i) {
            return i;
        }
        i = Math.max(nums[i], i + 1);
    }
    return -1;
}
```

## **Rust**

```rust
impl Solution {
    fn find(nums: &Vec<i32>, l: usize, r: usize) -> i32 {
        if l >= r || nums[r - 1] < 0 {
            return -1;
        }
        let mid = l + (r - l) / 2;
        if nums[mid] >= l as i32 {
            let res = Self::find(nums, l, mid);
            if res != -1 {
                return res;
            }
        }
        if nums[mid] == mid as i32 {
            return mid as i32;
        }
        Self::find(nums, mid + 1, r)
    }

    pub fn find_magic_index(nums: Vec<i32>) -> i32 {
        Self::find(&nums, 0, nums.len())
    }
}
```

```rust
impl Solution {
    pub fn find_magic_index(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut i = 0 as i32;
        while (i as usize) < n {
            let num = nums[i as usize];
            if num == i {
                return i;
            }
            i = num.max(i + 1);
        }
        -1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
