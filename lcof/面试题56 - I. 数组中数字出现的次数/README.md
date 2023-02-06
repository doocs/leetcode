# [面试题 56 - I. 数组中数字出现的次数](https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/)

## 题目描述

<p>一个整型数组 <code>nums</code> 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [4,1,4,6]
<strong>输出：</strong>[1,6] 或 [6,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,10,4,1,4,3,3]
<strong>输出：</strong>[2,10] 或 [10,2]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10000</code></li>
</ul>

<p>&nbsp;</p>

## 解法

**方法一：位运算**

由于数组中除了两个数字之外，其他数字都出现了两次，因此对数组中的所有数字进行异或运算，得到的结果即为两个只出现一次的数字的异或结果。

由于这两个数字不相等，因此异或结果中至少存在一位为 $1$。我们通过 `lowbit` 运算找到异或结果中最低位的 $1$，并将数组中的所有数字按照该位是否为 $1$ 分为两组，这样两个只出现一次的数字就被分到了不同的组中。

对两个组分别进行异或运算，即可得到两个只出现一次的数字。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def singleNumbers(self, nums: List[int]) -> List[int]:
        xs = reduce(xor, nums)
        a = 0
        lb = xs & -xs
        for x in nums:
            if x & lb:
                a ^= x
        b = xs ^ a
        return [a, b]
```

### **Java**

```java
class Solution {
    public int[] singleNumbers(int[] nums) {
        int xs = 0;
        for (int x : nums) {
            xs ^= x;
        }
        int lb = xs & -xs;
        int a = 0;
        for (int x : nums) {
            if ((x & lb) != 0) {
                a ^= x;
            }
        }
        int b = xs ^ a;
        return new int[] {a, b};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> singleNumbers(vector<int>& nums) {
        int xs = 0;
        for (int& x : nums) {
            xs ^= x;
        }
        int lb = xs & -xs;
        int a = 0;
        for (int& x : nums) {
            if (x & lb) {
                a ^= x;
            }
        }
        int b = xs ^ a;
        return {a, b};
    }
};
```

### **Go**

```go
func singleNumbers(nums []int) []int {
	xs := 0
	for _, x := range nums {
		xs ^= x
	}
	lb := xs & -xs
	a := 0
	for _, x := range nums {
		if x&lb != 0 {
			a ^= x
		}
	}
	b := xs ^ a
	return []int{a, b}
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var singleNumbers = function (nums) {
    let xs = 0;
    for (const x of nums) {
        xs ^= x;
    }
    const lb = xs & -xs;
    let a = 0;
    for (const x of nums) {
        if (x & lb) {
            a ^= x;
        }
    }
    const b = xs ^ a;
    return [a, b];
};
```

### **C#**

```cs
public class Solution {
    public int[] SingleNumbers(int[] nums) {
        int xs = 0;
        foreach(int x in nums) {
            xs ^= x;
        }
        int lb = xs & - xs;
        int a = 0;
        foreach(int x in nums) {
            if ((x & lb) != 0) {
                a ^= x;
            }
        }
        int b = xs ^ a;
        return new int[] {a, b};
    }
}
```

### **TypeScript**

```ts
function singleNumbers(nums: number[]): number[] {
    let xs = 0;
    for (const x of nums) {
        xs ^= x;
    }
    const lb = xs & -xs;
    let a = 0;
    for (const x of nums) {
        if (x & lb) {
            a ^= x;
        }
    }
    const b = xs ^ a;
    return [a, b];
}
```

### **...**

```

```

<!-- tabs:end -->
