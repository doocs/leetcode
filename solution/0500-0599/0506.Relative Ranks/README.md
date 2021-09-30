# [506. 相对名次](https://leetcode-cn.com/problems/relative-ranks)

[English Version](/solution/0500-0599/0506.Relative%20Ranks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出&nbsp;<strong>N</strong> 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 &ldquo;金牌&rdquo;，&ldquo;银牌&rdquo; 和&ldquo; 铜牌&rdquo;（&quot;Gold Medal&quot;, &quot;Silver Medal&quot;, &quot;Bronze Medal&quot;）。</p>

<p>(注：分数越高的选手，排名越靠前。)</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [5, 4, 3, 2, 1]
<strong>输出:</strong> [&quot;Gold Medal&quot;, &quot;Silver Medal&quot;, &quot;Bronze Medal&quot;, &quot;4&quot;, &quot;5&quot;]
<strong>解释:</strong> 前三名运动员的成绩为前三高的，因此将会分别被授予 &ldquo;金牌&rdquo;，&ldquo;银牌&rdquo;和&ldquo;铜牌&rdquo; (&quot;Gold Medal&quot;, &quot;Silver Medal&quot; and &quot;Bronze Medal&quot;).
余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。</pre>

<p><strong>提示:</strong></p>

<ol>
	<li>N 是一个正整数并且不会超过&nbsp;10000。</li>
	<li>所有运动员的成绩都不相同。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findRelativeRanks(self, score: List[int]) -> List[str]:
        n = len(score)
        idx = list(range(n))
        idx.sort(key=lambda x: -score[x])
        res = [None] * n
        for i in range(n):
            if i == 0:
                res[idx[i]] = 'Gold Medal'
            elif i == 1:
                res[idx[i]] = 'Silver Medal'
            elif i == 2:
                res[idx[i]] = 'Bronze Medal'
            else:
                res[idx[i]] = str(i + 1)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String[] findRelativeRanks(int[] nums) {
        int n = nums.length;
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; ++i) {
            index[i] = i;
        }
        Arrays.sort(index, (o1, o2) -> Integer.compare(nums[o2], nums[o1]));
        String[] res = new String[n];
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                res[index[i]] = "Gold Medal";
            } else if (i == 1) {
                res[index[i]] = "Silver Medal";
            } else if (i == 2) {
                res[index[i]] = "Bronze Medal";
            } else {
                res[index[i]] = String.valueOf(i + 1);
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
