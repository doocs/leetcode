# [1685. Sum of Absolute Differences in a Sorted Array](https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array)

[中文文档](/solution/1600-1699/1685.Sum%20of%20Absolute%20Differences%20in%20a%20Sorted%20Array/README.md)

## Description

<p>You are given an integer array <code>nums</code> sorted in <strong>non-decreasing</strong> order.</p>

<p>Build and return <em>an integer array </em><code>result</code><em> with the same length as </em><code>nums</code><em> such that </em><code>result[i]</code><em> is equal to the <strong>summation of absolute differences</strong> between </em><code>nums[i]</code><em> and all the other elements in the array.</em></p>

<p>In other words, <code>result[i]</code> is equal to <code>sum(|nums[i]-nums[j]|)</code> where <code>0 &lt;= j &lt; nums.length</code> and <code>j != i</code> (<strong>0-indexed</strong>).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,5]
<strong>Output:</strong> [4,3,5]
<strong>Explanation:</strong> Assuming the arrays are 0-indexed, then
result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,6,8,10]
<strong>Output:</strong> [24,15,13,15,21]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= nums[i + 1] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getSumAbsoluteDifferences(self, nums: List[int]) -> List[int]:
        ans = []
        s, t = sum(nums), 0
        for i, x in enumerate(nums):
            v = x * i - t + s - t - x * (len(nums) - i)
            ans.append(v)
            t += x
        return ans
```

### **Java**

```java
class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        // int s = Arrays.stream(nums).sum();
        int s = 0, t = 0;
        for (int x : nums) {
            s += x;
        }
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int v = nums[i] * i - t + s - t - nums[i] * (n - i);
            ans[i] = v;
            t += nums[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getSumAbsoluteDifferences(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0), t = 0;
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int v = nums[i] * i - t + s - t - nums[i] * (n - i);
            ans[i] = v;
            t += nums[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func getSumAbsoluteDifferences(nums []int) (ans []int) {
	var s, t int
	for _, x := range nums {
		s += x
	}
	for i, x := range nums {
		v := x*i - t + s - t - x*(len(nums)-i)
		ans = append(ans, v)
		t += x
	}
	return
}
```

### **TypeScript**

```ts
function getSumAbsoluteDifferences(nums: number[]): number[] {
    const s = nums.reduce((a, b) => a + b);
    let t = 0;
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        const v = nums[i] * i - t + s - t - nums[i] * (n - i);
        ans[i] = v;
        t += nums[i];
    }
    return ans;
}
```

### **C#**

```cs
public class Solution {
    public int[] GetSumAbsoluteDifferences(int[] nums) {
        int s = 0, t = 0;
        foreach (int x in nums) {
            s += x;
        }
        int n = nums.Length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int v = nums[i] * i - t + s - t - nums[i] * (n - i);
            ans[i] = v;
            t += nums[i];
        }
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var getSumAbsoluteDifferences = function (nums) {
    const s = nums.reduce((a, b) => a + b);
    let t = 0;
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        const v = nums[i] * i - t + s - t - nums[i] * (n - i);
        ans[i] = v;
        t += nums[i];
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
