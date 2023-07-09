# [2772. Apply Operations to Make All Array Elements Equal to Zero](https://leetcode.com/problems/apply-operations-to-make-all-array-elements-equal-to-zero)

[中文文档](/solution/2700-2799/2772.Apply%20Operations%20to%20Make%20All%20Array%20Elements%20Equal%20to%20Zero/README.md)

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

**Solution 1: Difference Array + Prefix Sum**

Let's first consider the first element $nums[0]$ of $nums$:

-   If $nums[0] = 0$, then we don't need to do anything;
-   If $nums[0] \gt 0$, then we need to operate $nums[0]$ times on $nums[0..k-1]$ to make the elements in $nums[0..k-1]$ all subtract $nums[0]$, so that $nums[0]$ becomes $0$.

We can use difference array to maintain the operations on a segment of continuous elements. We use $d[i]$ to represent the difference array, and take the prefix sum of the difference array to get the variation amount of each position.

Therefore, we traverse $nums$, for each element $nums[i]$, the current variation amount $s = \sum_{j=0}^{i} d[j]$ and we add $s$ to $nums[i]$ to get the actual value of $nums[i]$.

-   If $nums[i] = 0$, then we don't need to do anything, just skip.
-   If $nums[i]=0$ or $i + k \gt n$, it means that after the previous operation, $nums[i]$ has become negative, or $nums[i..i+k-1]$ out of bounds, so it is impossible to make all elements in $nums$ equal to $0$, then return `false`. Otherwise, we need to subtract $nums[i]$ from all elements in $[i..i+k-1]$, so we subtract $nums[i]$ from $s$ and add $nums[i]$ to $d[i+k]$.
-   Continue to traverse the next element.

If the traversal is over, it means that we can make all elements in $nums$ equal to $0$, return `true`.

Time complexity $O(n)$, space complexity $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
