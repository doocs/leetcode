# [1906. Minimum Absolute Difference Queries](https://leetcode.com/problems/minimum-absolute-difference-queries)

[中文文档](/solution/1900-1999/1906.Minimum%20Absolute%20Difference%20Queries/README.md)

## Description

<p>The <strong>minimum absolute difference</strong> of an array <code>a</code> is defined as the <strong>minimum value</strong> of <code>|a[i] - a[j]|</code>, where <code>0 &lt;= i &lt; j &lt; a.length</code> and <code>a[i] != a[j]</code>. If all elements of <code>a</code> are the <strong>same</strong>, the minimum absolute difference is <code>-1</code>.</p>

<ul>
	<li>For example, the minimum absolute difference of the array <code>[5,<u>2</u>,<u>3</u>,7,2]</code> is <code>|2 - 3| = 1</code>. Note that it is not <code>0</code> because <code>a[i]</code> and <code>a[j]</code> must be different.</li>
</ul>

<p>You are given an integer array <code>nums</code> and the array <code>queries</code> where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>. For each query <code>i</code>, compute the <strong>minimum absolute difference</strong> of the <strong>subarray</strong> <code>nums[l<sub>i</sub>...r<sub>i</sub>]</code> containing the elements of <code>nums</code> between the <strong>0-based</strong> indices <code>l<sub>i</sub></code> and <code>r<sub>i</sub></code> (<strong>inclusive</strong>).</p>

<p>Return <em>an <strong>array</strong> </em><code>ans</code> <em>where</em> <code>ans[i]</code> <em>is the answer to the</em> <code>i<sup>th</sup></code> <em>query</em>.</p>

<p>A <strong>subarray</strong> is a contiguous sequence of elements in an array.</p>

<p>The value of <code>|x|</code> is defined as:</p>

<ul>
	<li><code>x</code> if <code>x &gt;= 0</code>.</li>
	<li><code>-x</code> if <code>x &lt; 0</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,4,8], queries = [[0,1],[1,2],[2,3],[0,3]]
<strong>Output:</strong> [2,1,4,1]
<strong>Explanation:</strong> The queries are processed as follows:
- queries[0] = [0,1]: The subarray is [<u>1</u>,<u>3</u>] and the minimum absolute difference is |1-3| = 2.
- queries[1] = [1,2]: The subarray is [<u>3</u>,<u>4</u>] and the minimum absolute difference is |3-4| = 1.
- queries[2] = [2,3]: The subarray is [<u>4</u>,<u>8</u>] and the minimum absolute difference is |4-8| = 4.
- queries[3] = [0,3]: The subarray is [1,<u>3</u>,<u>4</u>,8] and the minimum absolute difference is |3-4| = 1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,5,2,2,7,10], queries = [[2,3],[0,2],[0,5],[3,5]]
<strong>Output:</strong> [-1,1,1,3]
<strong>Explanation: </strong>The queries are processed as follows:
- queries[0] = [2,3]: The subarray is [2,2] and the minimum absolute difference is -1 because all the
  elements are the same.
- queries[1] = [0,2]: The subarray is [<u>4</u>,<u>5</u>,2] and the minimum absolute difference is |4-5| = 1.
- queries[2] = [0,5]: The subarray is [<u>4</u>,<u>5</u>,2,2,7,10] and the minimum absolute difference is |4-5| = 1.
- queries[3] = [3,5]: The subarray is [2,<u>7</u>,<u>10</u>] and the minimum absolute difference is |7-10| = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= queries.length &lt;= 2&nbsp;* 10<sup>4</sup></code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt; r<sub>i</sub> &lt; nums.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minDifference(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        m, n = len(nums), len(queries)
        pre_sum = [[0] * 101 for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, 101):
                t = 1 if nums[i - 1] == j else 0
                pre_sum[i][j] = pre_sum[i - 1][j] + t

        ans = []
        for i in range(n):
            left, right = queries[i][0], queries[i][1] + 1
            t = inf
            last = -1
            for j in range(1, 101):
                if pre_sum[right][j] - pre_sum[left][j] > 0:
                    if last != -1:
                        t = min(t, j - last)
                    last = j
            if t == inf:
                t = -1
            ans.append(t)
        return ans
```

### **Java**

```java
class Solution {
    public int[] minDifference(int[] nums, int[][] queries) {
        int m = nums.length, n = queries.length;
        int[][] preSum = new int[m + 1][101];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= 100; ++j) {
                int t = nums[i - 1] == j ? 1 : 0;
                preSum[i][j] = preSum[i - 1][j] + t;
            }
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int left = queries[i][0], right = queries[i][1] + 1;
            int t = Integer.MAX_VALUE;
            int last = -1;
            for (int j = 1; j <= 100; ++j) {
                if (preSum[right][j] > preSum[left][j]) {
                    if (last != -1) {
                        t = Math.min(t, j - last);
                    }
                    last = j;
                }
            }
            if (t == Integer.MAX_VALUE) {
                t = -1;
            }
            ans[i] = t;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function minDifference(nums: number[], queries: number[][]): number[] {
    let m = nums.length,
        n = queries.length;
    let max = 100;
    // let max = Math.max(...nums);
    let pre: number[][] = [];
    pre.push(new Array(max + 1).fill(0));
    for (let i = 0; i < m; ++i) {
        let num = nums[i];
        pre.push(pre[i].slice());
        pre[i + 1][num] += 1;
    }

    let ans = [];
    for (let [left, right] of queries) {
        let last = -1;
        let min = Infinity;
        for (let j = 1; j < max + 1; ++j) {
            if (pre[left][j] < pre[right + 1][j]) {
                if (last != -1) {
                    min = Math.min(min, j - last);
                }
                last = j;
            }
        }
        ans.push(min == Infinity ? -1 : min);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> minDifference(vector<int>& nums, vector<vector<int>>& queries) {
        int m = nums.size(), n = queries.size();
        int preSum[m + 1][101];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= 100; ++j) {
                int t = nums[i - 1] == j ? 1 : 0;
                preSum[i][j] = preSum[i - 1][j] + t;
            }
        }

        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int left = queries[i][0], right = queries[i][1] + 1;
            int t = 101;
            int last = -1;
            for (int j = 1; j <= 100; ++j) {
                if (preSum[right][j] > preSum[left][j]) {
                    if (last != -1) {
                        t = min(t, j - last);
                    }
                    last = j;
                }
            }
            if (t == 101) {
                t = -1;
            }
            ans[i] = t;
        }
        return ans;
    }
};
```

### **Go**

```go
func minDifference(nums []int, queries [][]int) []int {
	m, n := len(nums), len(queries)
	preSum := make([][101]int, m+1)
	for i := 1; i <= m; i++ {
		for j := 1; j <= 100; j++ {
			t := 0
			if nums[i-1] == j {
				t = 1
			}
			preSum[i][j] = preSum[i-1][j] + t
		}
	}

	ans := make([]int, n)
	for i := 0; i < n; i++ {
		left, right := queries[i][0], queries[i][1]+1
		t, last := 101, -1
		for j := 1; j <= 100; j++ {
			if preSum[right][j]-preSum[left][j] > 0 {
				if last != -1 {
					if t > j-last {
						t = j - last
					}
				}
				last = j
			}
		}
		if t == 101 {
			t = -1
		}
		ans[i] = t
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
