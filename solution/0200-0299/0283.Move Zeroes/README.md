# [283. 移动零](https://leetcode.cn/problems/move-zeroes)

[English Version](/solution/0200-0299/0283.Move%20Zeroes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>nums</code>，编写一个函数将所有 <code>0</code> 移动到数组的末尾，同时保持非零元素的相对顺序。</p>

<p><strong>请注意</strong>&nbsp;，必须在不复制数组的情况下原地对数组进行操作。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = <code>[0,1,0,3,12]</code>
<strong>输出:</strong> <code>[1,3,12,0,0]</code>
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = <code>[0]</code>
<strong>输出:</strong> <code>[0]</code></pre>

<p>&nbsp;</p>

<p><strong>提示</strong>:</p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= nums[i] &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

<p>&nbsp;</p>

<p><b>进阶：</b>你能尽量减少完成的操作次数吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        left, n = 0, len(nums)
        for right in range(n):
            if nums[right] != 0:
                nums[left], nums[right] = nums[right], nums[left]
                left += 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0, n = nums.length;
        for (int right = 0; right < n; ++right) {
            if (nums[right] != 0) {
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
                ++left;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int left = 0, n = nums.size();
        for (int right = 0; right < n; ++right) {
            if (nums[right] != 0) {
                swap(nums[left], nums[right]);
                ++left;
            }
        }
    }
};
```

### **Go**

```go
func moveZeroes(nums []int) {
	n := len(nums)
	left := 0
	for right := 0; right < n; right++ {
		if nums[right] != 0 {
			nums[left], nums[right] = nums[right], nums[left]
			left++
		}
	}
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function (nums) {
    let left = 0,
        n = nums.length;
    for (let right = 0; right < n; ++right) {
        if (nums[right]) {
            [nums[left], nums[right]] = [nums[right], nums[left]];
            ++left;
        }
    }
};
```

```js
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function (nums) {
    let left = 0;
    let right = left;
    while (left < nums.length) {
        if (nums[left] != 0) {
            left++;
        } else {
            right = left + 1;
            while (right < nums.length) {
                if (nums[right] == 0) {
                    right++;
                } else {
                    let tem = nums[left];
                    nums[left] = nums[right];
                    nums[right] = tem;
                    break;
                }
            }
            left++;
        }
    }
};
```

### **TypeScript**

```ts
/**
 Do not return anything, modify nums in-place instead.
 */
function moveZeroes(nums: number[]): void {
    const n = nums.length;
    let i = 0;
    for (let j = 0; j < n; j++) {
        if (nums[j]) {
            if (j > i) {
                [nums[i], nums[j]] = [nums[j], 0];
            }
            i++;
        }
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn move_zeroes(nums: &mut Vec<i32>) {
        let mut i = 0;
        for j in 0..nums.len() {
            if nums[j] != 0 {
                if j > i {
                    nums[i] = nums[j];
                    nums[j] = 0;
                }
                i += 1;
            }
        }
    }
}
```

### **C**

```c
void moveZeroes(int *nums, int numsSize) {
    int i = 0;
    for (int j = 0; j < numsSize; j++) {
        if (nums[j] != 0) {
            if (j > i) {
                nums[i] = nums[j];
                nums[j] = 0;
            }
            i++;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
