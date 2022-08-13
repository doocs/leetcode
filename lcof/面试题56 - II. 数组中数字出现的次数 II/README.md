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

统计所有数字每个位中 1 出现的次数，对于某个位，1 出现的次数一定是 3 的倍数 +1 或 0。对这个数 %3 得到的结果就是那个出现一次的数字在该位上的值。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        bits = [0 for _ in range(32)]
        for num in nums:
            for i in range(32):
                bits[i] += num & 1
                num >>= 1
        res = 0
        for i in range(32):
            if bits[i] % 3 == 1:
                res += 1 << i
        return res
```

### **Java**

```java
class Solution {
    public int singleNumber(int[] nums) {
        int[] bits = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; ++i) {
                bits[i] += (num & 1);
                num >>= 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            if (bits[i] % 3 == 1) {
                res += (1 << i);
            }
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumber = function (nums) {
    let a = 0;
    let b = 0;
    for (let num of nums) {
        a = (a ^ num) & ~b;
        b = (b ^ num) & ~a;
    }
    return a;
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
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int bit = 0;
            foreach (var num in nums) {
                bit += ((num >> i) & 1);
            }
            res += ((bit % 3) << i);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
