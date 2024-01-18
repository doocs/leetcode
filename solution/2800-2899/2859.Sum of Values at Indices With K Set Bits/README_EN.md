# [2859. Sum of Values at Indices With K Set Bits](https://leetcode.com/problems/sum-of-values-at-indices-with-k-set-bits)

[中文文档](/solution/2800-2899/2859.Sum%20of%20Values%20at%20Indices%20With%20K%20Set%20Bits/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Return <em>an integer that denotes the <strong>sum</strong> of elements in </em><code>nums</code><em> whose corresponding <strong>indices</strong> have <strong>exactly</strong> </em><code>k</code><em> set bits in their binary representation.</em></p>

<p>The <strong>set bits</strong> in an integer are the <code>1</code>&#39;s present when it is written in binary.</p>

<ul>
	<li>For example, the binary representation of <code>21</code> is <code>10101</code>, which has <code>3</code> set bits.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,10,1,5,2], k = 1
<strong>Output:</strong> 13
<strong>Explanation:</strong> The binary representation of the indices are: 
0 = 000<sub>2</sub>
1 = 001<sub>2</sub>
2 = 010<sub>2</sub>
3 = 011<sub>2</sub>
4 = 100<sub>2 
</sub>Indices 1, 2, and 4 have k = 1 set bits in their binary representation.
Hence, the answer is nums[1] + nums[2] + nums[4] = 13.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,2,1], k = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> The binary representation of the indices are:
0 = 00<sub>2</sub>
1 = 01<sub>2</sub>
2 = 10<sub>2</sub>
3 = 11<sub>2
</sub>Only index 3 has k = 2 set bits in its binary representation.
Hence, the answer is nums[3] = 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10</code></li>
</ul>

## Solutions

### Solution 1: Simulation

We directly traverse each index $i$, and check whether the number of $1$s in its binary representation is equal to $k$. If it is, we add the corresponding element to the answer $ans$.

After the traversal ends, we return the answer.

The time complexity is $O(n \times \log n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def sumIndicesWithKSetBits(self, nums: List[int], k: int) -> int:
        return sum(x for i, x in enumerate(nums) if i.bit_count() == k)
```

```java
class Solution {
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (Integer.bitCount(i) == k) {
                ans += nums.get(i);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int sumIndicesWithKSetBits(vector<int>& nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (__builtin_popcount(i) == k) {
                ans += nums[i];
            }
        }
        return ans;
    }
};
```

```go
func sumIndicesWithKSetBits(nums []int, k int) (ans int) {
	for i, x := range nums {
		if bits.OnesCount(uint(i)) == k {
			ans += x
		}
	}
	return
}
```

```ts
function sumIndicesWithKSetBits(nums: number[], k: number): number {
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (bitCount(i) === k) {
            ans += nums[i];
        }
    }
    return ans;
}

function bitCount(n: number): number {
    let count = 0;
    while (n) {
        n &= n - 1;
        count++;
    }
    return count;
}
```

<!-- tabs:end -->

<!-- end -->
