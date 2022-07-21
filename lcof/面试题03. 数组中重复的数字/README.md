# [面试题 03. 数组中重复的数字](https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

## 题目描述

<p>找出数组中重复的数字。</p>

<p><br>
在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
[2, 3, 1, 0, 2, 5, 3]
<strong>输出：</strong>2 或 3 
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>2 &lt;= n &lt;= 100000</code></p>

## 解法

三种方式

-   排序
    -   先排序，将相同的数字聚集到一起。
    -   再遍历，当位于 `i` 与 `i + 1` 的数字相等时，返回该数字。
-   哈希表
    -   记录数字在数组中的数量，当数量为 2 时，返回即可。
-   原地交换
    -   0 ～ n-1 范围内的数，分别还原到对应的位置上，如：数字 2 交换到下标为 2 的位置。
    -   若交换过程中发现重复，则直接返回。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findRepeatNumber(self, nums: List[int]) -> int:
        for i, num in enumerate(nums):
            while i != num:
                if num == nums[num]:
                    return num
                nums[i], nums[num] = nums[num], nums[i]
                num = nums[i]
        return -1
```

### **Java**

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0, n = nums.length; i < n; ++i) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) return nums[i];
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

### **Kotlin**

```kotlin
class Solution {
    fun findRepeatNumber(nums: IntArray): Int {
        for (i in nums.indices) {
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    fun swap(nums: IntArray, i: Int, j: Int) {
        var t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findRepeatNumber = function (nums) {
    let m = {};
    for (let num of nums) {
        if (m[num]) return num;
        m[num] = 1;
    }
};
```

### **Go**

```go
func findRepeatNumber(nums []int) int {
    for i := 0; i < len(nums); i++ {
        for nums[i] != i {
            if nums[i] == nums[nums[i]] {
                return nums[i]
            }
            nums[i], nums[nums[i]] = nums[nums[i]], nums[i]
        }
    }
    return -1
}
```

### **C++**

```cpp
class Solution {
public:
    int findRepeatNumber(vector<int>& nums) {
        int len = nums.size();
        for (int i = 0; i < len; i++) {
            while (i != nums[i]) {
                // 这一位的值，不等于这一位的数字
                if (nums[i] == nums[nums[i]]) {
                    // 如果在交换的过程中，发现了相等的数字，直接返回
                    return nums[i];
                }

                swap(nums[i], nums[nums[i]]);
            }
        }

        return 0;
    }
};
```

### **TypeScript**

```ts
function findRepeatNumber(nums: number[]): number {
    let n: number = nums.length;
    for (let i: number = 0; i < n; i++) {
        while (nums[i] != i) {
            if (nums[i] == nums[nums[i]]) return nums[i];
            swap(nums, i, nums[i]);
        }
    }
    return -1;
}

function swap(nums: number[], i: number, j: number): void {
    [nums[i], nums[j]] = [nums[j], nums[i]];
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_repeat_number(mut nums: Vec<i32>) -> i32 {
        for i in 0..nums.len() {
            while i as i32 != nums[i] {
                let j = nums[i] as usize;
                if nums[i] == nums[j] {
                    return nums[i];
                }
                nums.swap(i, j);
            }
        }
        -1
    }
}
```

### **C#**

```cs
public class Solution {
    public int FindRepeatNumber(int[] nums) {
        int temp;
        for (int i = 0; i < nums.Length; i++) {
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
