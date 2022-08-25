# [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive)

[中文文档](/solution/0000-0099/0041.First%20Missing%20Positive/README.md)

## Description

<p>Given an unsorted integer array <code>nums</code>, return the smallest missing positive integer.</p>

<p>You must implement an algorithm that runs in <code>O(n)</code> time and uses constant extra space.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,0]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The numbers in the range [1,2] are all in the array.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,-1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 1 is in the array but 2 is missing.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,8,9,11,12]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The smallest positive integer 1 is missing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
