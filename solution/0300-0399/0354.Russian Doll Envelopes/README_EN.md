# [354. Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes)

[中文文档](/solution/0300-0399/0354.Russian%20Doll%20Envelopes/README.md)

## Description

<p>You have a number of envelopes with widths and heights given as a pair of integers <code>(w, h)</code>. One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.</p>

<p>What is the maximum number of envelopes can you Russian doll? (put one inside other)</p>

<p><b>Note:</b><br />

Rotation is not allowed.</p>

<p><strong>Example:</strong></p>

<div>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[[5,4],[6,4],[6,7],[2,3]]</span>

<strong>Output: </strong><span id="example-output-1">3 

<strong>Explanation: T</strong></span>he maximum number of envelopes you can Russian doll is <code>3</code> ([2,3] =&gt; [5,4] =&gt; [6,7]).

</pre>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

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
