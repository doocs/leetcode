# [2772. Apply Operations to Make All Array Elements Equal to Zero](https://leetcode.com/problems/apply-operations-to-make-all-array-elements-equal-to-zero)

[中文文档](/solution/2700-2799/2772.Apply%20Operations%20to%20Make%20All%20Array%20Elements%20Equal%20to%20Zero/README.md)

<!-- tags:Array,Prefix Sum -->

<!-- difficulty:Medium -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and a positive integer <code>k</code>.</p>

<p>You can apply the following operation on the array <strong>any</strong> number of times:</p>

<ul>
	<li>Choose <strong>any</strong> subarray of size <code>k</code> from the array and <strong>decrease</strong> all its elements by <code>1</code>.</li>
</ul>

<p>Return <code>true</code><em> if you can make all the array elements equal to </em><code>0</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>A <strong>subarray</strong> is a contiguous non-empty part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,3,1,1,0], k = 3
<strong>Output:</strong> true
<strong>Explanation:</strong> We can do the following operations:
- Choose the subarray [2,2,3]. The resulting array will be nums = [<strong><u>1</u></strong>,<strong><u>1</u></strong>,<strong><u>2</u></strong>,1,1,0].
- Choose the subarray [2,1,1]. The resulting array will be nums = [1,1,<strong><u>1</u></strong>,<strong><u>0</u></strong>,<strong><u>0</u></strong>,0].
- Choose the subarray [1,1,1]. The resulting array will be nums = [<u><strong>0</strong></u>,<u><strong>0</strong></u>,<u><strong>0</strong></u>,0,0,0].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,1,1], k = 2
<strong>Output:</strong> false
<strong>Explanation:</strong> It is not possible to make all the array elements equal to 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Difference Array + Prefix Sum

First, let's consider the first element of $nums$, $nums[0]$:

-   If $nums[0] = 0$, we don't need to do anything.
-   If $nums[0] > 0$, we need to operate on $nums[0..k-1]$ for $nums[0]$ times, subtracting $nums[0]$ from all elements in $nums[0..k-1]$, so $nums[0]$ becomes $0$.

To perform the add and subtract operations on a contiguous segment of elements simultaneously, we can use a difference array to manage these operations. We represent the difference array with $d[i]$, and calculating the prefix sum of the difference array gives us the change of the value at each position.

Therefore, we iterate over $nums$. For each element $nums[i]$, the current position's change quantity is $s = \sum_{j=0}^{i} d[j]$. We add $s$ to $nums[i]$ to get the actual value of $nums[i]$.

-   If $nums[i] = 0$, there's no need for any operation, and we can skip directly.
-   If $nums[i]=0$ or $i + k > n$, it indicates that after the previous operations, $nums[i]$ has become negative, or $nums[i..i+k-1]$ is out of bounds. Therefore, it's impossible to make all elements in $nums$ equal to $0$. We return `false`. Otherwise, we need to subtract $nums[i]$ from all elements in the interval $[i..i+k-1]$. Therefore, we subtract $nums[i]$ from $s$ and add $nums[i]$ to $d[i+k]$.
-   We continue to iterate over the next element.

If the iteration ends, it means that all elements in $nums$ can be made equal to $0$, so we return `true`.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def checkArray(self, nums: List[int], k: int) -> bool:
        n = len(nums)
        d = [0] * (n + 1)
        s = 0
        for i, x in enumerate(nums):
            s += d[i]
            x += s
            if x == 0:
                continue
            if x < 0 or i + k > n:
                return False
            s -= x
            d[i + k] += x
        return True
```

```java
class Solution {
    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;
        int[] d = new int[n + 1];
        int s = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            nums[i] += s;
            if (nums[i] == 0) {
                continue;
            }
            if (nums[i] < 0 || i + k > n) {
                return false;
            }
            s -= nums[i];
            d[i + k] += nums[i];
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool checkArray(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> d(n + 1);
        int s = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            nums[i] += s;
            if (nums[i] == 0) {
                continue;
            }
            if (nums[i] < 0 || i + k > n) {
                return false;
            }
            s -= nums[i];
            d[i + k] += nums[i];
        }
        return true;
    }
};
```

```go
func checkArray(nums []int, k int) bool {
	n := len(nums)
	d := make([]int, n+1)
	s := 0
	for i, x := range nums {
		s += d[i]
		x += s
		if x == 0 {
			continue
		}
		if x < 0 || i+k > n {
			return false
		}
		s -= x
		d[i+k] += x
	}
	return true
}
```

```ts
function checkArray(nums: number[], k: number): boolean {
    const n = nums.length;
    const d: number[] = Array(n + 1).fill(0);
    let s = 0;
    for (let i = 0; i < n; ++i) {
        s += d[i];
        nums[i] += s;
        if (nums[i] === 0) {
            continue;
        }
        if (nums[i] < 0 || i + k > n) {
            return false;
        }
        s -= nums[i];
        d[i + k] += nums[i];
    }
    return true;
}
```

<!-- tabs:end -->

<!-- end -->
