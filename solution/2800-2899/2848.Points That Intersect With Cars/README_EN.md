# [2848. Points That Intersect With Cars](https://leetcode.com/problems/points-that-intersect-with-cars)

[中文文档](/solution/2800-2899/2848.Points%20That%20Intersect%20With%20Cars/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D integer array <code>nums</code> representing the coordinates of the cars parking on a number line. For any index <code>i</code>, <code>nums[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> where <code>start<sub>i</sub></code> is the starting point of the <code>i<sup>th</sup></code> car and <code>end<sub>i</sub></code> is the ending point of the <code>i<sup>th</sup></code> car.</p>

<p>Return <em>the number of integer points on the line that are covered with <strong>any part</strong> of a car.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [[3,6],[1,5],[4,7]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> All the points from 1 to 7 intersect at least one car, therefore the answer would be 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [[1,3],[5,8]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Points intersecting at least one car are 1, 2, 3, 5, 6, 7, 8. There are a total of 7 points, therefore the answer would be 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums[i].length == 2</code></li>
	<li><code><font face="monospace">1 &lt;= start<sub>i</sub>&nbsp;&lt;= end<sub>i</sub>&nbsp;&lt;= 100</font></code></li>
</ul>

## Solutions

**Solution 1: Difference Array**

We create a difference array $d$ of length $110$, then traverse the given array. For each interval $[a, b]$, we increase $d[a]$ by $1$ and decrease $d[b + 1]$ by $1$. Finally, we traverse the difference array $d$, calculate the prefix sum $s$ at each position. If $s > 0$, it means that the position is covered, and we increase the answer by $1$.

The time complexity is $O(n)$, and the space complexity is $O(M)$. Here, $n$ is the length of the given array, and $M$ is the maximum value in the array.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfPoints(self, nums: List[List[int]]) -> int:
        d = [0] * 110
        for a, b in nums:
            d[a] += 1
            d[b + 1] -= 1
        return sum(s > 0 for s in accumulate(d))
```

### **Java**

```java
class Solution {
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] d = new int[110];
        for (var e : nums) {
            d[e.get(0)]++;
            d[e.get(1) + 1]--;
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            if (s > 0) {
                ans++;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfPoints(vector<vector<int>>& nums) {
        int d[110]{};
        for (auto& e : nums) {
            d[e[0]]++;
            d[e[1] + 1]--;
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            ans += s > 0;
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfPoints(nums [][]int) (ans int) {
	d := [110]int{}
	for _, e := range nums {
		d[e[0]]++
		d[e[1]+1]--
	}
	s := 0
	for _, x := range d {
		s += x
		if s > 0 {
			ans++
		}
	}
	return
}
```

### **TypeScript**

```ts
function numberOfPoints(nums: number[][]): number {
    const d: number[] = Array(110).fill(0);
    for (const [a, b] of nums) {
        d[a]++;
        d[b + 1]--;
    }
    let ans = 0;
    let s = 0;
    for (const x of d) {
        s += x;
        if (s > 0) {
            ans++;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
