# [2090. K Radius Subarray Averages](https://leetcode.com/problems/k-radius-subarray-averages)

[中文文档](/solution/2000-2099/2090.K%20Radius%20Subarray%20Averages/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> of <code>n</code> integers, and an integer <code>k</code>.</p>

<p>The <strong>k-radius average</strong> for a subarray of <code>nums</code> <strong>centered</strong> at some index <code>i</code> with the <strong>radius</strong> <code>k</code> is the average of <strong>all</strong> elements in <code>nums</code> between the indices <code>i - k</code> and <code>i + k</code> (<strong>inclusive</strong>). If there are less than <code>k</code> elements before <strong>or</strong> after the index <code>i</code>, then the <strong>k-radius average</strong> is <code>-1</code>.</p>

<p>Build and return <em>an array </em><code>avgs</code><em> of length </em><code>n</code><em> where </em><code>avgs[i]</code><em> is the <strong>k-radius average</strong> for the subarray centered at index </em><code>i</code>.</p>

<p>The <strong>average</strong> of <code>x</code> elements is the sum of the <code>x</code> elements divided by <code>x</code>, using <strong>integer division</strong>. The integer division truncates toward zero, which means losing its fractional part.</p>

<ul>
	<li>For example, the average of four elements <code>2</code>, <code>3</code>, <code>1</code>, and <code>5</code> is <code>(2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75</code>, which truncates to <code>2</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2090.K%20Radius%20Subarray%20Averages/images/eg1.png" style="width: 343px; height: 119px;" />
<pre>
<strong>Input:</strong> nums = [7,4,3,9,1,8,5,2,6], k = 3
<strong>Output:</strong> [-1,-1,-1,5,4,4,-1,-1,-1]
<strong>Explanation:</strong>
- avg[0], avg[1], and avg[2] are -1 because there are less than k elements <strong>before</strong> each index.
- The sum of the subarray centered at index 3 with radius 3 is: 7 + 4 + 3 + 9 + 1 + 8 + 5 = 37.
  Using <strong>integer division</strong>, avg[3] = 37 / 7 = 5.
- For the subarray centered at index 4, avg[4] = (4 + 3 + 9 + 1 + 8 + 5 + 2) / 7 = 4.
- For the subarray centered at index 5, avg[5] = (3 + 9 + 1 + 8 + 5 + 2 + 6) / 7 = 4.
- avg[6], avg[7], and avg[8] are -1 because there are less than k elements <strong>after</strong> each index.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [100000], k = 0
<strong>Output:</strong> [100000]
<strong>Explanation:</strong>
- The sum of the subarray centered at index 0 with radius 0 is: 100000.
  avg[0] = 100000 / 1 = 100000.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [8], k = 100000
<strong>Output:</strong> [-1]
<strong>Explanation:</strong> 
- avg[0] is -1 because there are less than k elements before and after index 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], k &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getAverages(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        presum = [0] * (n + 1)
        for i in range(n):
            presum[i + 1] = presum[i] + nums[i]
        return [
            -1
            if i - k < 0 or i + k >= n
            else (presum[i + k + 1] - presum[i - k]) // (k * 2 + 1)
            for i in range(n)
        ]
```

### **Java**

```java
class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        long[] presum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + nums[i];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i - k < 0 || i + k >= n) {
                ans[i] = -1;
            } else {
                ans[i] = (int) ((presum[i + k + 1] - presum[i - k]) / (k * 2 + 1));
            }
        }
        return ans;
    }
}
```

### \*_TypeScipt_

```ts
function getAverages(nums: number[], k: number): number[] {
    const n = nums.length;
    const l = 2 * k + 1;
    let sum = 0;
    let ans = new Array(n).fill(-1);
    for (let i = 0; i < n; i++) {
        sum += nums[i];
        let shiftIndex = i - l;
        if (shiftIndex > -1) {
            sum -= nums[shiftIndex];
        }
        if (i + 1 >= l) {
            ans[i - k] = Math.floor(sum / l);
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getAverages(vector<int>& nums, int k) {
        int n = nums.size();
        vector<long long> presum(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + nums[i];
        vector<int> ans(n, -1);
        for (int i = 0; i < n; ++i)
            if (i - k >= 0 && i + k < n)
                ans[i] = (presum[i + k + 1] - presum[i - k]) * 1ll / (k * 2 + 1);
        return ans;
    }
};
```

### **Go**

```go
func getAverages(nums []int, k int) []int {
	n := len(nums)
	presum := make([]int64, n+1)
	for i, num := range nums {
		presum[i+1] = presum[i] + int64(num)
	}
	var ans []int
	for i := 0; i < n; i++ {
		if i-k < 0 || i+k >= n {
			ans = append(ans, -1)
		} else {
			ans = append(ans, int((presum[i+k+1]-presum[i-k])/int64(k*2+1)))
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
