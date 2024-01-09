# [2997. Minimum Number of Operations to Make Array XOR Equal to K](https://leetcode.com/problems/minimum-number-of-operations-to-make-array-xor-equal-to-k)

[中文文档](/solution/2900-2999/2997.Minimum%20Number%20of%20Operations%20to%20Make%20Array%20XOR%20Equal%20to%20K/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and a positive integer <code>k</code>.</p>

<p>You can apply the following operation on the array <strong>any</strong> number of times:</p>

<ul>
	<li>Choose <strong>any</strong> element of the array and <strong>flip</strong> a bit in its <strong>binary</strong> representation. Flipping a bit means changing a <code>0</code> to <code>1</code> or vice versa.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of operations required to make the bitwise </em><code>XOR</code><em> of <strong>all</strong> elements of the final array equal to </em><code>k</code>.</p>

<p><strong>Note</strong> that you can flip leading zero bits in the binary representation of elements. For example, for the number <code>(101)<sub>2</sub></code> you can flip the fourth bit and obtain <code>(1101)<sub>2</sub></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,3,4], k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can do the following operations:
- Choose element 2 which is 3 == (011)<sub>2</sub>, we flip the first bit and we obtain (010)<sub>2</sub> == 2. nums becomes [2,1,2,4].
- Choose element 0 which is 2 == (010)<sub>2</sub>, we flip the third bit and we obtain (110)<sub>2</sub> = 6. nums becomes [6,1,2,4].
The XOR of elements of the final array is (6 XOR 1 XOR 2 XOR 4) == 1 == k.
It can be shown that we cannot make the XOR equal to k in less than 2 operations.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,0,2,0], k = 0
<strong>Output:</strong> 0
<strong>Explanation:</strong> The XOR of elements of the array is (2 XOR 0 XOR 2 XOR 0) == 0 == k. So no operation is needed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

**Solution 1: Bit Manipulation**

We can perform a bitwise XOR operation on all elements in the array $nums$. The number of bits that differ from the binary representation of $k$ in the result is the minimum number of operations.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        return reduce(xor, nums, k).bit_count()
```

### **Java**

```java
class Solution {
    public int minOperations(int[] nums, int k) {
        for (int x : nums) {
            k ^= x;
        }
        return Integer.bitCount(k);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        for (int x : nums) {
            k ^= x;
        }
        return __builtin_popcount(k);
    }
};
```

### **Go**

```go
func minOperations(nums []int, k int) (ans int) {
	for _, x := range nums {
		k ^= x
	}
	return bits.OnesCount(uint(k))
}
```

### **TypeScript**

```ts
function minOperations(nums: number[], k: number): number {
    for (const x of nums) {
        k ^= x;
    }
    return bitCount(k);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

### **...**

```

```

<!-- tabs:end -->
