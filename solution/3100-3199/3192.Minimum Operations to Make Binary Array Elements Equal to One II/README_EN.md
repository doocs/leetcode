---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3192.Minimum%20Operations%20to%20Make%20Binary%20Array%20Elements%20Equal%20to%20One%20II/README_EN.md
---

<!-- problem:start -->

# [3192. Minimum Operations to Make Binary Array Elements Equal to One II](https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-ii)

[中文文档](/solution/3100-3199/3192.Minimum%20Operations%20to%20Make%20Binary%20Array%20Elements%20Equal%20to%20One%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a <span data-keyword="binary-array">binary array</span> <code>nums</code>.</p>

<p>You can do the following operation on the array <strong>any</strong> number of times (possibly zero):</p>

<ul>
	<li>Choose <strong>any</strong> index <code>i</code> from the array and <strong>flip</strong> <strong>all</strong> the elements from index <code>i</code> to the end of the array.</li>
</ul>

<p><strong>Flipping</strong> an element means changing its value from 0 to 1, and from 1 to 0.</p>

<p>Return the <strong>minimum</strong> number of operations required to make all elements in <code>nums</code> equal to 1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,1,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong><br />
We can do the following operations:</p>

<ul>
	<li>Choose the index <code>i = 1</code><span class="example-io">. The resulting array will be <code>nums = [0,<u><strong>0</strong></u>,<u><strong>0</strong></u>,<u><strong>1</strong></u>,<u><strong>0</strong></u>]</code>.</span></li>
	<li>Choose the index <code>i = 0</code><span class="example-io">. The resulting array will be <code>nums = [<u><strong>1</strong></u>,<u><strong>1</strong></u>,<u><strong>1</strong></u>,<u><strong>0</strong></u>,<u><strong>1</strong></u>]</code>.</span></li>
	<li>Choose the index <code>i = 4</code><span class="example-io">. The resulting array will be <code>nums = [1,1,1,0,<u><strong>0</strong></u>]</code>.</span></li>
	<li>Choose the index <code>i = 3</code><span class="example-io">. The resulting array will be <code>nums = [1,1,1,<u><strong>1</strong></u>,<u><strong>1</strong></u>]</code>.</span></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,0,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong><br />
We can do the following operation:</p>

<ul>
	<li>Choose the index <code>i = 1</code><span class="example-io">. The resulting array will be <code>nums = [1,<u><strong>1</strong></u>,<u><strong>1</strong></u>,<u><strong>1</strong></u>]</code>.</span></li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

We notice that whenever we change an element at a certain position to 1, all the elements to its right are flipped. Therefore, we can use a variable $v$ to record whether the current position and all elements to its right have been flipped. If flipped, the value of $v$ is 1, otherwise, it is 0.

We iterate through the array $\text{nums}$. For each element $x$, we perform an XOR operation between $x$ and $v$. If $x$ is 0, then we need to change $x$ to 1, which requires a flip operation. We increment the answer by one and flip the value of $v$.

After the iteration, we can obtain the minimum number of operations.

The time complexity is $O(n)$, where $n$ is the length of the array $\text{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        ans = v = 0
        for x in nums:
            x ^= v
            if x == 0:
                ans += 1
                v ^= 1
        return ans
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        int ans = 0, v = 0;
        for (int x : nums) {
            x ^= v;
            if (x == 0) {
                v ^= 1;
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0, v = 0;
        for (int x : nums) {
            x ^= v;
            if (x == 0) {
                v ^= 1;
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int) (ans int) {
	v := 0
	for _, x := range nums {
		x ^= v
		if x == 0 {
			v ^= 1
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    let [ans, v] = [0, 0];
    for (let x of nums) {
        x ^= v;
        if (x === 0) {
            v ^= 1;
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
