# [189. 轮转数组](https://leetcode.cn/problems/rotate-array)

[English Version](/solution/0100-0199/0189.Rotate%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组，将数组中的元素向右轮转 <code>k</code><em>&nbsp;</em>个位置，其中&nbsp;<code>k</code><em>&nbsp;</em>是非负数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4,5,6,7], k = 3
<strong>输出:</strong> <code>[5,6,7,1,2,3,4]</code>
<strong>解释:</strong>
向右轮转 1 步: <code>[7,1,2,3,4,5,6]</code>
向右轮转 2 步: <code>[6,7,1,2,3,4,5]
</code>向右轮转 3 步: <code>[5,6,7,1,2,3,4]</code>
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,-100,3,99], k = 2
<strong>输出：</strong>[3,99,-1,-100]
<strong>解释:</strong> 
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>尽可能想出更多的解决方案，至少有 <strong>三种</strong> 不同的方法可以解决这个问题。</li>
	<li>你可以使用空间复杂度为&nbsp;<code>O(1)</code> 的&nbsp;<strong>原地&nbsp;</strong>算法解决这个问题吗？</li>
</ul>

<ul>
</ul>

<ul>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

若 `k=3`，`nums=[1,2,3,4,5,6,7]`。

先将 `nums` 整体翻转：`[1,2,3,4,5,6,7]` -> `[7,6,5,4,3,2,1]`

再翻转 `0~k-1` 范围内的元素：`[7,6,5,4,3,2,1]` -> `[5,6,7,4,3,2,1]`

最后翻转 `k~n-1` 范围内的元素，即可得到最终结果：`[5,6,7,4,3,2,1]` -> `[5,6,7,1,2,3,4]`

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        n = len(nums)
        k %= n
        if n < 2 or k == 0:
            return
        nums[:] = nums[::-1]
        nums[:k] = nums[:k][::-1]
        nums[k:] = nums[k:][::-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null) {
            return;
        }
        int n = nums.length;
        k %= n;
        if (n < 2 || k == 0) {
            return;
        }

        rotate(nums, 0, n - 1);
        rotate(nums, 0, k - 1);
        rotate(nums, k, n - 1);
    }

    private void rotate(int[] nums, int i, int j) {
        while (i < j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            ++i;
            --j;
        }
    }
}
```

### **JavaScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

使用原生 API 将数组的 `k~n-1` 范围内的元素插入到前面

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var rotate = function (nums, k) {
    k %= nums.length;
    nums.splice(0, 0, ...nums.splice(-k, k));
};
```

使用三次数组翻转 + 双指针实现翻转

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var rotate = function (nums, k) {
    k %= nums.length;
    // 使用三次数组翻转
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
};
function reverse(nums, start, end) {
    // 双指针实现翻转
    while (start < end) {
        const temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start += 1;
        end -= 1;
    }
}
```

### **Go**

```go
func rotate(nums []int, k int) {
	n := len(nums)
	k %= n

	reverse(nums, 0, n-1)
	reverse(nums, 0, k-1)
	reverse(nums, k, n-1)
}

func reverse(nums []int, i, j int) {
	for i < j {
		nums[i], nums[j] = nums[j], nums[i]
		i++
		j--
	}
}
```

### **Rust**

```rust
impl Solution {
    pub fn rotate(nums: &mut Vec<i32>, k: i32) {
        let n = nums.len();
        let k = k as usize % n;
        if n == 1 || k == 0 {
            return;
        }

        nums.reverse();
        nums[..k].reverse();
        nums[k..].reverse();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
