# [354. 俄罗斯套娃信封问题](https://leetcode-cn.com/problems/russian-doll-envelopes)

[English Version](/solution/0300-0399/0354.Russian%20Doll%20Envelopes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>envelopes</code> ，其中 <code>envelopes[i] = [w<sub>i</sub>, h<sub>i</sub>]</code> ，表示第 <code>i</code> 个信封的宽度和高度。</p>

<p>当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。</p>

<p>请计算 <strong>最多能有多少个</strong> 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。</p>

<p><strong>注意</strong>：不允许旋转信封。</p>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>envelopes = [[5,4],[6,4],[6,7],[2,3]]
<strong>输出：</strong>3
<strong>解释：</strong>最多信封的个数为 <code>3, 组合为: </code>[2,3] => [5,4] => [6,7]。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>envelopes = [[1,1],[1,1],[1,1]]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= envelopes.length <= 5000</code></li>
	<li><code>envelopes[i].length == 2</code></li>
	<li><code>1 <= w<sub>i</sub>, h<sub>i</sub> <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

排序 + [最长递增子序列](/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README.md)。

按 w 进行升序排序，若 w 相同则按 h 降序排序。然后问题转换为求 h 数组的最长递增子序列长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxEnvelopes(self, envelopes: List[List[int]]) -> int:
        if not envelopes:
            return 0
        envelopes.sort(key=lambda x: (x[0], -x[1]))
        nums = [x[1] for x in envelopes]
        n = len(nums)
        dp = [1] * n
        res = 1
        for i in range(1, n):
            for j in range(i):
                if nums[j] < nums[i]:
                    dp[i] = max(dp[i], dp[j] + 1)
            res = max(res, dp[i])
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n;
        if (envelopes == null || (n = envelopes.length) == 0) return 0;
        Arrays.sort(envelopes, (a, b) -> {
            return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
        });
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
