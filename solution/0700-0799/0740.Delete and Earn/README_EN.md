# [740. Delete and Earn](https://leetcode.com/problems/delete-and-earn)

[中文文档](/solution/0700-0799/0740.Delete%20and%20Earn/README.md)

## Description

<p>You are given an integer array <code>nums</code>. You want to maximize the number of points you get by performing the following operation any number of times:</p>

<ul>
	<li>Pick any <code>nums[i]</code> and delete it to earn <code>nums[i]</code> points. Afterwards, you must delete <b>every</b> element equal to <code>nums[i] - 1</code> and <strong>every</strong> element equal to <code>nums[i] + 1</code>.</li>
</ul>

<p>Return <em>the <strong>maximum number of points</strong> you can earn by applying the above operation some number of times</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,2]
<strong>Output:</strong> 6
<strong>Explanation:</strong> You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,3,3,3,4]
<strong>Output:</strong> 9
<strong>Explanation:</strong> You can perform the following operations:
- Delete a 3 to earn 3 points. All 2&#39;s and 4&#39;s are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

Intuition: **If we take a number, we will take all of the copies of it**.

First calculate the sum of each number as **sums**, and keep updating two dp arrays: **select** and **nonSelect**

-   `sums[i]` represents the sum of elements whose value is i;
-   `select[i]` represents the maximum sum of processing from 0 to i if the number i is selected;
-   `nonSelect[i]` represents the maximum sum of processing from 0 to i if the number i is not selected;

Then we have the following conclusions:

-   If i is selected, then i-1 must not be selected;
-   If you do not choose i, then i-1 can choose or not, so we choose the larger one;

```java
select[i] = nonSelect[i-1] + sums[i];
nonSelect[i] = Math.max(select[i-1], nonSelect[i-1]);
```

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:
        mx = -inf
        for num in nums:
            mx = max(mx, num)
        total = [0] * (mx + 1)
        for num in nums:
            total[num] += num
        first = total[0]
        second = max(total[0], total[1])
        for i in range(2, mx + 1):
            cur = max(first + total[i], second)
            first = second
            second = cur
        return second
```

### **Java**

```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] sums = new int[10010];
        int[] select = new int[10010];
        int[] nonSelect = new int[10010];

        int maxV = 0;
        for (int x : nums) {
            sums[x] += x;
            maxV = Math.max(maxV, x);
        }

        for (int i = 1; i <= maxV; i++) {
            select[i] = nonSelect[i - 1] + sums[i];
            nonSelect[i] = Math.max(select[i - 1], nonSelect[i - 1]);
        }
        return Math.max(select[maxV], nonSelect[maxV]);
    }
}
```

### **Go**

```go
func deleteAndEarn(nums []int) int {

	max := func(x, y int) int {
		if x > y {
			return x
		}
		return y
	}

	mx := math.MinInt32
	for _, num := range nums {
		mx = max(mx, num)
	}
	total := make([]int, mx+1)
	for _, num := range nums {
		total[num] += num
	}
	first := total[0]
	second := max(total[0], total[1])
	for i := 2; i <= mx; i++ {
		cur := max(first+total[i], second)
		first = second
		second = cur
	}
	return second
}
```

### **C++**

```cpp
class Solution {
public:
    int deleteAndEarn(vector<int>& nums) {
        vector<int> vals(10010);
        for (int& num : nums) {
            vals[num] += num;
        }
        return rob(vals);
    }

    int rob(vector<int>& nums) {
        int a = 0, b = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int c = max(nums[i] + a, b);
            a = b;
            b = c;
        }
        return b;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
