# [41. 缺失的第一个正数](https://leetcode.cn/problems/first-missing-positive)

[English Version](/solution/0000-0099/0041.First%20Missing%20Positive/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个未排序的整数数组 <code>nums</code> ，请你找出其中没有出现的最小的正整数。</p>
请你实现时间复杂度为 <code>O(n)</code> 并且只使用常数级别额外空间的解决方案。

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,0]
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,-1,1]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [7,8,9,11,12]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 5 * 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

从简单到复杂。

**哈希表：**

无视空间需求，使用哈希表或数组来记录 `nums` 中所有出现过的**正整数**。然后在其中查找 `(1 ~ nums.length)`，返回第一个不存在记录的正整数即可。

**排序：**

方法之一

```txt
FRRST-MISSING-POSITIVE(A)
    A.sort()
    r = 1
    for n in A
        if n == r
            r++
        else if n > r
            break
    return r
```

**常数空间：**

有一个现成的记录容器，那就是 `nums` 本身。

使用下标来记录数据，只关注正整数，对于正整数 `x`，将其归为至 `nums[x - 1]` 位置（与对应位置的元素进行交换）。

当所有的正整数归位之后，再次遍历 `nums`，找到第一个不满足 `nums[i] != i + 1` 表达式的位置，返回 `i + 1`。若是全部满足，则返回 `nums.length + 1`。

> 因为存在重复元素，只要 `nums[x - 1] == x` 条件已满足，那么再遇到 `x` 时，直接跳过。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def firstMissingPositive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """

        i = 1
        while i in nums:
            i += 1
        return i
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
public class Solution {
    public int firstMissingPositive(int[] num) {
        for (int i = 0; i < num.length; i++) {
            if (num[i] > 0 && num[i] < num.length && num[num[i] - 1] != num[i]) {
                swap(num, i, num[i] - 1);
                i--;
            }
        }

        for (int i = 0; i < num.length; i++) {
            if (i + 1 != num[i]) {
                return i + 1;
            }
        }

        return num.length + 1;
    }

    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int firstMissingPositive(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int len = nums.size();
        if (len == 0) return 1;
        int i = 0;
        while (nums[i] <= 0 && i < len) i++;
        if (i == len) return 1;

        int tmp = 1;
        while (i < len) {
            if (nums[i] != tmp) return tmp;
            while (len > i + 1 && nums[i] == nums[i + 1]) i++; //去重
            i++;
            tmp++;
        }
        return tmp;
    }
};
```

### **C**

```c
int firstMissingPositive(int* nums, int numsSize) {

    int Max = nums[0], i, *Count;

    for (i = 1; i < numsSize; i++) {
        Max = (Max < nums[i]) ? nums[i] : Max;
    }

    Count = (int*)calloc(Max + 1, sizeof(int));
    for (i = 0; i < numsSize; i++) {
        if (nums[i] > 0) {
            Count[nums[i]]++;
        }
    }

    i = 1;
    while (Count[i] != 0) {
        i++;
    }

    return i;
}
```

### **C#**

```cs
public class Solution {
    public int FirstMissingPositive(int[] nums) {
        var i = 0;
        while (i < nums.Length)
        {
            if (nums[i] > 0 && nums[i] <= nums.Length)
            {
                var index = nums[i] -1;
                if (index != i && nums[index] != nums[i])
                {
                    var temp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = temp;
                }
                else
                {
                    ++i;
                }
            }
            else
            {
                ++i;
            }
        }

        for (i = 0; i < nums.Length; ++i)
        {
            if (nums[i] != i + 1)
            {
                return i + 1;
            }
        }
        return nums.Length + 1;
    }
}
```

### **TypeScript**

```ts
function firstMissingPositive(nums: number[]): number {
    const n = nums.length;
    let i = 0;
    while (i < n) {
        const j = nums[i] - 1;
        if (j === i || j < 0 || j >= n || nums[i] === nums[j]) {
            i++;
        } else {
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    }

    const res = nums.findIndex((v, i) => v !== i + 1);
    return (res === -1 ? n : res) + 1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn first_missing_positive(mut nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut i = 0;
        while i < n {
            let j = nums[i] - 1;
            if i as i32 == j || j < 0 || j >= n as i32 || nums[i] == nums[j as usize] {
                i += 1;
            } else {
                nums.swap(i, j as usize);
            }
        }
        nums.iter()
            .enumerate()
            .position(|(i, &v)| v as usize != i + 1)
            .unwrap_or(n) as i32
            + 1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
