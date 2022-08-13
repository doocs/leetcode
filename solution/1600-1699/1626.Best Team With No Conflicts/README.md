# [1626. 无矛盾的最佳球队](https://leetcode.cn/problems/best-team-with-no-conflicts)

[English Version](/solution/1600-1699/1626.Best%20Team%20With%20No%20Conflicts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 <strong>总和</strong> 。</p>

<p>然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 <strong>没有矛盾</strong> 的球队。如果一名年龄较小球员的分数 <strong>严格大于</strong> 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。</p>

<p>给你两个列表 <code>scores</code> 和 <code>ages</code>，其中每组 <code>scores[i]</code> 和 <code>ages[i]</code> 表示第 <code>i</code> 名球员的分数和年龄。请你返回 <strong>所有可能的无矛盾球队中得分最高那支的分数</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>scores = [1,3,5,10,15], ages = [1,2,3,4,5]
<strong>输出：</strong>34
<strong>解释：</strong>你可以选中所有球员。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>scores = [4,5,6,5], ages = [2,1,2,1]
<strong>输出：</strong>16
<strong>解释：</strong>最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>scores = [1,2,3,5], ages = [8,9,10,1]
<strong>输出：</strong>6
<strong>解释：</strong>最佳的选择是前 3 名球员。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= scores.length, ages.length &lt;= 1000</code></li>
	<li><code>scores.length == ages.length</code></li>
	<li><code>1 &lt;= scores[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= ages[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

最长上升子序列模型。

将所有球员先按照年龄从小到大排序（年龄相同，则按照分数从小到大排），然后在分数数组中求解最长上升子序列和的最大值即可。最长上升子序列朴素做法，时间复杂度 O(n²)。

类似题型：洛谷 “[P2782 友好城市](https://www.luogu.com.cn/problem/P2782)”。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def bestTeamScore(self, scores: List[int], ages: List[int]) -> int:
        nums = list(zip(ages, scores))
        nums.sort()
        n = len(nums)
        dp = [num[1] for num in nums]
        for i in range(n):
            for j in range(i):
                if nums[i][1] >= nums[j][1]:
                    dp[i] = max(dp[i], dp[j] + nums[i][1])
        return max(dp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = ages.length;
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; ++i) {
            nums[i] = new int[]{ages[i], scores[i]};
        }
        Arrays.sort(nums, (a, b) -> {
            return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
        });
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            dp[i] = nums[i][1];
            for (int j = 0; j < i; ++j) {
                if (nums[i][1] >= nums[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i][1]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int bestTeamScore(vector<int>& scores, vector<int>& ages) {
        int n = ages.size();
        vector<vector<int>> nums(n);
        for (int i = 0; i < n; ++i) nums[i] = {ages[i], scores[i]};
        sort(nums.begin(), nums.end());
        vector<int> dp(n);
        for (int i = 0; i < n; ++i) {
            dp[i] = nums[i][1];
            for (int j = 0; j < i; ++j) {
                if (nums[i][1] >= nums[j][1])
                    dp[i] = max(dp[i], dp[j] + nums[i][1]);
            }
        }
        return *max_element(dp.begin(), dp.end());
    }
};
```

### **Go**

```go
func bestTeamScore(scores []int, ages []int) int {
	n := len(ages)
	nums := make([][]int, n)
	for i, age := range ages {
		nums[i] = []int{age, scores[i]}
	}
	sort.Slice(nums, func(i, j int) bool {
		if nums[i][0] != nums[j][0] {
			return nums[i][0] < nums[j][0]
		}
		return nums[i][1] < nums[j][1]
	})
	dp := make([]int, n)
	ans := 0
	for i, num := range nums {
		dp[i] = num[1]
		for j := 0; j < i; j++ {
			if num[1] >= nums[j][1] {
				dp[i] = max(dp[i], dp[j]+num[1])
			}
		}
		ans = max(ans, dp[i])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} scores
 * @param {number[]} ages
 * @return {number}
 */
var bestTeamScore = function (scores, ages) {
    const nums = ages.map((age, i) => [age, scores[i]]);
    nums.sort((a, b) => (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
    const n = nums.length;
    let dp = new Array(n);
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        dp[i] = nums[i][1];
        for (let j = 0; j < i; ++j) {
            if (nums[i][1] >= nums[j][1]) {
                dp[i] = Math.max(dp[i], dp[j] + nums[i][1]);
            }
        }
        ans = Math.max(ans, dp[i]);
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
