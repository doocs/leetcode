# [面试题 56 - II. 数组中数字出现的次数 II](https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/)

## 题目描述

<p>在一个数组 <code>nums</code> 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [3,4,3,3]
<strong>输出：</strong>4
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [9,1,7,9,7,9,7]
<strong>输出：</strong>1</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10000</code></li>
	<li><code>1 &lt;= nums[i] &lt; 2^31</code></li>
</ul>

<p>&nbsp;</p>

## 解法

**方法一：位运算**

我们用一个长度为 32 的数组 $cnt$ 来统计所有数字的每一位中 $1$ 的出现次数。如果某一位的 $1$ 的出现次数能被 $3$ 整除，那么那个只出现一次的数字二进制表示中对应的那一位也是 $0$；否则，那个只出现一次的数字二进制表示中对应的那一位是 $1$。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是数组的长度；而 $C$ 是整数的位数，本题中 $C=32$。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        cnt = [0] * 32
        for x in nums:
            for i in range(32):
                cnt[i] += x & 1
                x >>= 1
        return sum(1 << i for i in range(32) if cnt[i] % 3)
```

### **Java**

```java
class Solution {
    public int singleNumber(int[] nums) {
        int[] cnt = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 32; ++i) {
                cnt[i] += x & 1;
                x >>= 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            if (cnt[i] % 3 == 1) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int cnt[32]{};
        for (int& x : nums) {
            for (int i = 0; i < 32; ++i) {
                cnt[i] += x & 1;
                x >>= 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            if (cnt[i] % 3) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func singleNumber(nums []int) (ans int) {
	cnt := [32]int{}
	for _, x := range nums {
		for i := range cnt {
			cnt[i] += x & 1
			x >>= 1
		}
	}
	for i, v := range cnt {
		if v%3 == 1 {
			ans |= 1 << i
		}
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumber = function (nums) {
    const cnt = new Array(32).fill(0);
    for (let x of nums) {
        for (let i = 0; i < 32; ++i) {
            cnt[i] += x & 1;
            x >>= 1;
        }
    }
    let ans = 0;
    for (let i = 0; i < 32; ++i) {
        if (cnt[i] % 3) {
            ans |= 1 << i;
        }
    }
    return ans;
};
```

### **Rust**

```rust
impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut counts = [0; 32];
        for num in nums.iter() {
            for i in 0..32 {
                counts[i] += (num >> i) & 1;
            }
        }
        let mut res = 0;
        for count in counts.iter().rev() {
            res <<= 1;
            res |= count % 3;
        }
        res
    }
}
```

### **C#**

```cs
public class Solution {
    public int SingleNumber(int[] nums) {
        int[] cnt = new int[32];
        foreach(int x in nums) {
            int v = x;
            for (int i = 0; i < 32; ++i) {
                cnt[i] += v & 1;
                v >>= 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            if (cnt[i] % 3 == 1) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
