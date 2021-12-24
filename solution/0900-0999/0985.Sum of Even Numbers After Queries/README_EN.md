# [985. Sum of Even Numbers After Queries](https://leetcode.com/problems/sum-of-even-numbers-after-queries)

[中文文档](/solution/0900-0999/0985.Sum%20of%20Even%20Numbers%20After%20Queries/README.md)

## Description

<p>We have an array <code>A</code> of integers, and an array <code>queries</code>&nbsp;of queries.</p>

<p>For the <code>i</code>-th&nbsp;query <code>val =&nbsp;queries[i][0], index&nbsp;= queries[i][1]</code>, we add <font face="monospace">val</font>&nbsp;to <code>A[index]</code>.&nbsp; Then, the answer to the <code>i</code>-th query is the sum of the even values of <code>A</code>.</p>

<p><em>(Here, the given <code>index = queries[i][1]</code> is a 0-based index, and each query permanently modifies the array <code>A</code>.)</em></p>

<p>Return the answer to all queries.&nbsp; Your <code>answer</code> array should have&nbsp;<code>answer[i]</code>&nbsp;as&nbsp;the answer to the <code>i</code>-th query.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>A = <span id="example-input-1-1">[1,2,3,4]</span>, queries = <span id="example-input-1-2">[[1,0],[-3,1],[-4,0],[2,3]]</span>

<strong>Output: </strong><span id="example-output-1">[8,6,2,4]</span>

<strong>Explanation: </strong>

At the beginning, the array is [1,2,3,4].

After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.

After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.

After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.

After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 10000</code></li>
	<li><code>-10000 &lt;= A[i] &lt;= 10000</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10000</code></li>
	<li><code>-10000 &lt;= queries[i][0] &lt;= 10000</code></li>
	<li><code>0 &lt;= queries[i][1] &lt; A.length</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumEvenAfterQueries(self, nums: List[int], queries: List[List[int]]) -> List[int]:
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
        for (auto& q : queries)
        {
            int v = q[0], i = q[1];
            int old = nums[i];
            nums[i] += v;
            if (nums[i] % 2 == 0 && old % 2 == 0) s += v;
            else if (nums[i] % 2 == 0 && old % 2 != 0) s += nums[i];
            else if (old % 2 == 0) s -= old;
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
