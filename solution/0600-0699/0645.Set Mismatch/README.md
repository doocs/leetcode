# [645. 错误的集合](https://leetcode.cn/problems/set-mismatch)

[English Version](/solution/0600-0699/0645.Set%20Mismatch/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>集合 <code>s</code> 包含从 <code>1</code> 到 <code>n</code> 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 <strong>丢失了一个数字</strong> 并且 <strong>有一个数字重复</strong> 。</p>

<p>给定一个数组 <code>nums</code> 代表了集合 <code>S</code> 发生错误后的结果。</p>

<p>请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,4]
<strong>输出：</strong>[2,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1]
<strong>输出：</strong>[1,2]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= nums.length <= 10<sup>4</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

异或运算求解。

首先明确，两个相同的数异或之后的结果为 0。对该数组所有元素以及 `i∈[1, n]` 所有数字进行异或运算，结果就是**两个只出现一次的数字异或的结果**，即 `eor = a ^ b`。

找出这个结果 eor 中最后一个二进制位为 1 而其余位为 0 的数，即 `eor & (~eor + 1)`，之后遍历数组所有元素以及 `i∈[1, n]` 所有数字，二进制位为 0 的元素异或到 a。

遍历结束后 `b = eor ^ a`，返回结果即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        eor, n = 0, len(nums)
        for i in range(1, n + 1):
            eor ^= i ^ nums[i - 1]
        diff = eor & (~eor + 1)
        a = 0
        for i in range(1, n + 1):
            if (nums[i - 1] & diff) == 0:
                a ^= nums[i - 1]
            if (i & diff) == 0:
                a ^= i
        b = eor ^ a
        for num in nums:
            if a == num:
                return [a, b]
        return [b, a]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int eor = 0;
        for (int i = 1; i <= nums.length; ++i) {
            eor ^= (i ^ nums[i - 1]);
        }
        int diff = eor & (~eor + 1);
        int a = 0;
        for (int i = 1; i <= nums.length; ++i) {
            if ((nums[i - 1] & diff) == 0) {
                a ^= nums[i - 1];
            }
            if ((i & diff) == 0) {
                a ^= i;
            }
        }
        int b = eor ^ a;
        for (int num : nums) {
            if (a == num) {
                return new int[] {a, b};
            }
        }
        return new int[] {b, a};
    }
}
```

### **TypeScript**

```ts
function findErrorNums(nums: number[]): number[] {
    let xor = 0;
    for (let i = 0; i < nums.length; ++i) {
        xor ^= (i + 1) ^ nums[i];
    }

    let divide = 1;
    while ((xor & divide) == 0) {
        divide <<= 1;
    }

    let ans1 = 0,
        ans2 = 0;
    for (let i = 0; i < nums.length; ++i) {
        let cur = nums[i];
        if (divide & cur) {
            ans1 ^= cur;
        } else {
            ans2 ^= cur;
        }

        let idx = i + 1;
        if (divide & idx) {
            ans1 ^= idx;
        } else {
            ans2 ^= idx;
        }
    }
    return nums.includes(ans1) ? [ans1, ans2] : [ans2, ans1];
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        int eor = 0, n = nums.size();
        for (int i = 1; i <= n; ++i) {
            eor ^= (i ^ nums[i - 1]);
        }
        int diff = eor & (~eor + 1);
        int a = 0;
        for (int i = 1; i <= n; ++i) {
            if ((nums[i - 1] & diff) == 0) {
                a ^= nums[i - 1];
            }
            if ((i & diff) == 0) {
                a ^= i;
            }
        }
        int b = eor ^ a;
        for (int num : nums) {
            if (a == num) {
                return {a, b};
            }
        }
        return {b, a};
    }
};
```

### **Go**

把每个数都放到它应该在的位置，最后出现“异常”的就是重复的数和丢失的数。

```go
func findErrorNums(nums []int) []int {
	n := len(nums)
	for i := 0; i < n; i++ {
		for nums[i] != i+1 && nums[nums[i]-1] != nums[i] {
			nums[i], nums[nums[i]-1] = nums[nums[i]-1], nums[i]
		}
	}
	for i := 0; i < n; i++ {
		if nums[i] != i+1 {
			return []int{nums[i], i + 1}
		}
	}
	return []int{-1, -1}
}
```

也可以使用位运算。

```go
func findErrorNums(nums []int) []int {
	eor, n := 0, len(nums)
	for i := 1; i <= n; i++ {
		eor ^= (i ^ nums[i-1])
	}
	diff := eor & (-eor)
	a := 0
	for i := 1; i <= n; i++ {
		if (nums[i-1] & diff) == 0 {
			a ^= nums[i-1]
		}
		if (i & diff) == 0 {
			a ^= i
		}
	}
	b := eor ^ a
	for _, num := range nums {
		if a == num {
			return []int{a, b}
		}
	}
	return []int{b, a}
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        int eor = 0, n = nums.size();
        for (int i = 1; i <= n; ++i) {
            eor ^= (i ^ nums[i - 1]);
        }
        int diff = eor & (~eor + 1);
        int a = 0;
        for (int i = 1; i <= n; ++i) {
            if ((nums[i - 1] & diff) == 0) {
                a ^= nums[i - 1];
            }
            if ((i & diff) == 0) {
                a ^= i;
            }
        }
        int b = eor ^ a;
        for (int num : nums) {
            if (a == num) {
                return {a, b};
            }
        }
        return {b, a};
    }
};
```

### **...**

```

```

<!-- tabs:end -->
