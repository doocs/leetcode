# [985. Sum of Even Numbers After Queries](https://leetcode.com/problems/sum-of-even-numbers-after-queries)

[中文文档](/solution/0900-0999/0985.Sum%20of%20Even%20Numbers%20After%20Queries/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an array <code>queries</code> where <code>queries[i] = [val<sub>i</sub>, index<sub>i</sub>]</code>.</p>

<p>For each query <code>i</code>, first, apply <code>nums[index<sub>i</sub>] = nums[index<sub>i</sub>] + val<sub>i</sub></code>, then print the sum of the even values of <code>nums</code>.</p>

<p>Return <em>an integer array </em><code>answer</code><em> where </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
<strong>Output:</strong> [8,6,2,4]
<strong>Explanation:</strong> At the beginning, the array is [1,2,3,4].
After adding 1 to nums[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
After adding -3 to nums[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
After adding -4 to nums[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
After adding 2 to nums[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], queries = [[4,0]]
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= val<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= index<sub>i</sub> &lt; nums.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumEvenAfterQueries(
        self, nums: List[int], queries: List[List[int]]
    ) -> List[int]:
        ans = []
        s = sum(num for num in nums if num % 2 == 0)
        for v, i in queries:
            old = nums[i]
            nums[i] += v
            if nums[i] % 2 == 0 and old % 2 == 0:
                s += v
            elif nums[i] % 2 == 0 and old % 2 == 1:
                s += nums[i]
            elif old % 2 == 0:
                s -= old
            ans.append(s)
        return ans
```

### **Java**

```java
class Solution {
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int s = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                s += num;
            }
        }
        int[] ans = new int[queries.length];
        int idx = 0;
        for (int[] q : queries) {
            int v = q[0], i = q[1];
            int old = nums[i];
            nums[i] += v;
            if (nums[i] % 2 == 0 && old % 2 == 0) {
                s += v;
            } else if (nums[i] % 2 == 0 && old % 2 != 0) {
                s += nums[i];
            } else if (old % 2 == 0) {
                s -= old;
            }
            ans[idx++] = s;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> sumEvenAfterQueries(vector<int>& nums, vector<vector<int>>& queries) {
        int s = 0;
        for (int& num : nums)
            if (num % 2 == 0)
                s += num;
        vector<int> ans;
        for (auto& q : queries) {
            int v = q[0], i = q[1];
            int old = nums[i];
            nums[i] += v;
            if (nums[i] % 2 == 0 && old % 2 == 0)
                s += v;
            else if (nums[i] % 2 == 0 && old % 2 != 0)
                s += nums[i];
            else if (old % 2 == 0)
                s -= old;
            ans.push_back(s);
        }
        return ans;
    }
};
```

### **Go**

```go
func sumEvenAfterQueries(nums []int, queries [][]int) []int {
	s := 0
	for _, num := range nums {
		if num%2 == 0 {
			s += num
		}
	}
	var ans []int
	for _, q := range queries {
		v, i := q[0], q[1]
		old := nums[i]
		nums[i] += v
		if nums[i]%2 == 0 && old%2 == 0 {
			s += v
		} else if nums[i]%2 == 0 && old%2 != 0 {
			s += nums[i]
		} else if old%2 == 0 {
			s -= old
		}
		ans = append(ans, s)
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number[][]} queries
 * @return {number[]}
 */
var sumEvenAfterQueries = function (nums, queries) {
    let s = 0;
    for (let num of nums) {
        if (num % 2 == 0) {
            s += num;
        }
    }
    let ans = [];
    for (let [v, i] of queries) {
        const old = nums[i];
        nums[i] += v;
        if (nums[i] % 2 == 0 && old % 2 == 0) {
            s += v;
        } else if (nums[i] % 2 == 0 && old % 2 != 0) {
            s += nums[i];
        } else if (old % 2 == 0) {
            s -= old;
        }
        ans.push(s);
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
