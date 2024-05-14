# [3012. Minimize Length of Array Using Operations](https://leetcode.com/problems/minimize-length-of-array-using-operations)

[中文文档](/solution/3000-3099/3012.Minimize%20Length%20of%20Array%20Using%20Operations/README.md)

<!-- tags:Greedy,Array,Math,Number Theory -->

<!-- difficulty:Medium -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> containing <strong>positive</strong> integers.</p>

<p>Your task is to <strong>minimize</strong> the length of <code>nums</code> by performing the following operations <strong>any</strong> number of times (including zero):</p>

<ul>
	<li>Select <strong>two</strong> <strong>distinct</strong> indices <code>i</code> and <code>j</code> from <code>nums</code>, such that <code>nums[i] &gt; 0</code> and <code>nums[j] &gt; 0</code>.</li>
	<li>Insert the result of <code>nums[i] % nums[j]</code> at the end of <code>nums</code>.</li>
	<li>Delete the elements at indices <code>i</code> and <code>j</code> from <code>nums</code>.</li>
</ul>

<p>Return <em>an integer denoting the <strong>minimum</strong> <strong>length</strong> of </em><code>nums</code><em> after performing the operation any number of times.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,3,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> One way to minimize the length of the array is as follows:
Operation 1: Select indices 2 and 1, insert nums[2] % nums[1] at the end and it becomes [1,4,3,1,3], then delete elements at indices 2 and 1.
nums becomes [1,1,3].
Operation 2: Select indices 1 and 2, insert nums[1] % nums[2] at the end and it becomes [1,1,3,1], then delete elements at indices 1 and 2.
nums becomes [1,1].
Operation 3: Select indices 1 and 0, insert nums[1] % nums[0] at the end and it becomes [1,1,0], then delete elements at indices 1 and 0.
nums becomes [0].
The length of nums cannot be reduced further. Hence, the answer is 1.
It can be shown that 1 is the minimum achievable length. </pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,5,5,10,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong> One way to minimize the length of the array is as follows:
Operation 1: Select indices 0 and 3, insert nums[0] % nums[3] at the end and it becomes [5,5,5,10,5,5], then delete elements at indices 0 and 3.
nums becomes [5,5,5,5]. 
Operation 2: Select indices 2 and 3, insert nums[2] % nums[3] at the end and it becomes [5,5,5,5,0], then delete elements at indices 2 and 3. 
nums becomes [5,5,0]. 
Operation 3: Select indices 0 and 1, insert nums[0] % nums[1] at the end and it becomes [5,5,0,0], then delete elements at indices 0 and 1.
nums becomes [0,0].
The length of nums cannot be reduced further. Hence, the answer is 2.
It can be shown that 2 is the minimum achievable length. </pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,4]
<strong>Output:</strong> 1
<strong>Explanation:</strong> One way to minimize the length of the array is as follows: 
Operation 1: Select indices 1 and 2, insert nums[1] % nums[2] at the end and it becomes [2,3,4,3], then delete elements at indices 1 and 2.
nums becomes [2,3].
Operation 2: Select indices 1 and 0, insert nums[1] % nums[0] at the end and it becomes [2,3,1], then delete elements at indices 1 and 0.
nums becomes [1].
The length of nums cannot be reduced further. Hence, the answer is 1.
It can be shown that 1 is the minimum achievable length.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Case Discussion

Let's denote the smallest element in the array $nums$ as $mi$.

If $mi$ appears only once, we can perform operations with $mi$ and the other elements in the array $nums$ to eliminate all other elements, leaving only $mi$. The answer is $1$.

If $mi$ appears multiple times, we need to check whether all elements in the array $nums$ are multiples of $mi$. If not, there exists at least one element $x$ such that $0 < x \bmod mi < mi$. This means we can construct an element smaller than $mi$ through operations. This smaller element can eliminate all other elements through operations, leaving only this smaller element. The answer is $1$. If all elements are multiples of $mi$, we can first use $mi$ to eliminate all elements larger than $mi$. The remaining elements are all $mi$, with a count of $cnt$. Pair them up, and perform an operation for each pair. Finally, there will be $\lceil cnt / 2 \rceil$ elements left, so the answer is $\lceil cnt / 2 \rceil$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minimumArrayLength(self, nums: List[int]) -> int:
        mi = min(nums)
        if any(x % mi for x in nums):
            return 1
        return (nums.count(mi) + 1) // 2
```

```java
class Solution {
    public int minimumArrayLength(int[] nums) {
        int mi = Arrays.stream(nums).min().getAsInt();
        int cnt = 0;
        for (int x : nums) {
            if (x % mi != 0) {
                return 1;
            }
            if (x == mi) {
                ++cnt;
            }
        }
        return (cnt + 1) / 2;
    }
}
```

```cpp
class Solution {
public:
    int minimumArrayLength(vector<int>& nums) {
        int mi = *min_element(nums.begin(), nums.end());
        int cnt = 0;
        for (int x : nums) {
            if (x % mi) {
                return 1;
            }
            cnt += x == mi;
        }
        return (cnt + 1) / 2;
    }
};
```

```go
func minimumArrayLength(nums []int) int {
	mi := slices.Min(nums)
	cnt := 0
	for _, x := range nums {
		if x%mi != 0 {
			return 1
		}
		if x == mi {
			cnt++
		}
	}
	return (cnt + 1) / 2
}
```

```ts
function minimumArrayLength(nums: number[]): number {
    const mi = Math.min(...nums);
    let cnt = 0;
    for (const x of nums) {
        if (x % mi) {
            return 1;
        }
        if (x === mi) {
            ++cnt;
        }
    }
    return (cnt + 1) >> 1;
}
```

<!-- tabs:end -->

<!-- end -->
