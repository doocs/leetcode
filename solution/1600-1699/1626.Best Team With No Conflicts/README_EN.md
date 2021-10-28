# [1626. Best Team With No Conflicts](https://leetcode.com/problems/best-team-with-no-conflicts)

[中文文档](/solution/1600-1699/1626.Best%20Team%20With%20No%20Conflicts/README.md)

## Description

<p>You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the <strong>sum</strong> of scores of all the players in the team.</p>

<p>However, the basketball team is not allowed to have <strong>conflicts</strong>. A <strong>conflict</strong> exists if a younger player has a <strong>strictly higher</strong> score than an older player. A conflict does <strong>not</strong> occur between players of the same age.</p>

<p>Given two lists, <code>scores</code> and <code>ages</code>, where each <code>scores[i]</code> and <code>ages[i]</code> represents the score and age of the <code>i<sup>th</sup></code> player, respectively, return <em>the highest overall score of all possible basketball teams</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> scores = [1,3,5,10,15], ages = [1,2,3,4,5]
<strong>Output:</strong> 34
<strong>Explanation:</strong>&nbsp;You can choose all the players.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> scores = [4,5,6,5], ages = [2,1,2,1]
<strong>Output:</strong> 16
<strong>Explanation:</strong>&nbsp;It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> scores = [1,2,3,5], ages = [8,9,10,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong>&nbsp;It is best to choose the first 3 players. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= scores.length, ages.length &lt;= 1000</code></li>
	<li><code>scores.length == ages.length</code></li>
	<li><code>1 &lt;= scores[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= ages[i] &lt;= 1000</code></li>
</ul>

## Solutions

LIS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def bestTeamScore(self, scores: List[int], ages: List[int]) -> int:
        nums = list(zip(scores, ages))
        nums.sort(key=lambda x: (x[1], x[0]))
        dp = [num[0] for num in nums]
        res, n = 0, len(ages)
        for i in range(n):
            for j in range(i):
                if nums[j][0] <= nums[i][0]:
                    dp[i] = max(dp[i], dp[j] + nums[i][0])
            res = max(res, dp[i])
        return res
```

### **Java**

```java
class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = ages.length;
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; ++i) {
            nums[i] = new int[]{scores[i], ages[i]};
        }
        Arrays.sort(nums, (a, b) -> {
            return a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
        });
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            dp[i] = nums[i][0];
            for (int j = 0; j < i; ++j) {
                if (nums[j][0] <= nums[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i][0]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int bestTeamScore(vector<int> &scores, vector<int> &ages) {
        vector<pair<int, int>> nums;
        int n = ages.size();
        for (int i = 0; i < n; ++i) nums.push_back({scores[i], ages[i]});
        sort(nums.begin(), nums.end(), [](auto &a, auto &b) {
            return a.second == b.second ? a.first < b.first : a.second < b.second;
        });
        vector<int> dp(n);
        int res = 0;
        for (int i = 0; i < n; ++i)
        {
            dp[i] = nums[i].first;
            for (int j = 0; j < i; ++j)
            {
                if (nums[j].first <= nums[i].first)
                    dp[i] = max(dp[i], dp[j] + nums[i].first);
            }
            res = max(res, dp[i]);
        }
        return res;
    }
};
```

### **Go**

```go
func bestTeamScore(scores []int, ages []int) int {
	n := len(ages)
	var nums [][]int
	for i := 0; i < n; i++ {
		nums = append(nums, []int{scores[i], ages[i]})
	}
	sort.Slice(nums, func(i, j int) bool {
		if nums[i][1] == nums[j][1] {
			return nums[i][0] < nums[j][0]
		}
		return nums[i][1] < nums[j][1]
	})
	dp := make([]int, n)
	res := 0
	for i := 0; i < n; i++ {
		dp[i] = nums[i][0]
		for j := 0; j < i; j++ {
			if nums[j][0] <= nums[i][0] {
				dp[i] = max(dp[i], dp[j]+nums[i][0])
			}
		}
		res = max(res, dp[i])
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
