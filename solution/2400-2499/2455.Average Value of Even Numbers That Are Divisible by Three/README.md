# [2455. 可被三整除的偶数的平均值](https://leetcode.cn/problems/average-value-of-even-numbers-that-are-divisible-by-three)

[English Version](/solution/2400-2499/2455.Average%20Value%20of%20Even%20Numbers%20That%20Are%20Divisible%20by%20Three/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由正整数组成的整数数组 <code>nums</code> ，返回其中可被 <code>3</code> 整除的所有偶数的平均值。</p>

<p>注意：<code>n</code> 个元素的平均值等于 <code>n</code> 个元素 <strong>求和</strong> 再除以 <code>n</code> ，结果 <strong>向下取整</strong> 到最接近的整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,6,10,12,15]
<strong>输出：</strong>9
<strong>解释：</strong>6 和 12 是可以被 3 整除的偶数。(6 + 12) / 2 = 9 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4,7,10]
<strong>输出：</strong>0
<strong>解释：</strong>不存在满足题目要求的整数，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

直接遍历 `nums`，统计可被 3 整除的偶数的和，以及可被 3 整除的偶数的个数，最后返回两者的商即可。注意，如果没有可被 3 整除的偶数，返回 0。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def averageValue(self, nums: List[int]) -> int:
        s = n = 0
        for v in nums:
            if v % 6 == 0:
                s += v
                n += 1
        return 0 if n == 0 else s // n
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int averageValue(int[] nums) {
        int s = 0, n = 0;
        for (int v : nums) {
            if (v % 6 == 0) {
                s += v;
                ++n;
            }
        }
        return n == 0 ? 0 : s / n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int averageValue(vector<int>& nums) {
        int s = 0, n = 0;
        for (int v : nums) {
            if (v % 6 == 0) {
                s += v;
                ++n;
            }
        }
        return n == 0 ? 0 : s / n;
    }
};
```

### **Go**

```go
func averageValue(nums []int) int {
	s, n := 0, 0
	for _, v := range nums {
		if v%6 == 0 {
			s += v
			n++
		}
	}
	if n == 0 {
		return 0
	}
	return s / n
}
```

### **C**

```c
int averageValue(int *nums, int numsSize) {
    int sum = 0;
    int n = 0;
    for (int i = 0; i < numsSize; i++) {
        if (nums[i] % 6 == 0) {
            sum += nums[i];
            n++;
        }
    }

    if (n == 0) {
        return 0;
    }
    return sum / n;
}
```

### **TypeScript**

```ts
function averageValue(nums: number[]): number {
    let sum = 0;
    let n = 0;
    for (const num of nums) {
        if (num % 6 === 0) {
            sum += num;
            n++;
        }
    }

    if (n === 0) {
        return 0;
    }
    return Math.floor(sum / n);
}
```

### **Rust**

```rust
impl Solution {
    pub fn average_value(nums: Vec<i32>) -> i32 {
        let mut sum = 0;
        let mut n = 0;
        for num in nums.iter() {
            if num % 6 == 0 {
                sum += num;
                n += 1;
            }
        }

        if n == 0 {
            return 0;
        }
        sum / n
    }
}
```

### **...**

```

```

<!-- tabs:end -->
