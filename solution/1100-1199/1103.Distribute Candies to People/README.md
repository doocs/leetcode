---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1103.Distribute%20Candies%20to%20People/README.md
rating: 1287
source: 第 143 场周赛 Q1
tags:
    - 数学
    - 模拟
---

# [1103. 分糖果 II](https://leetcode.cn/problems/distribute-candies-to-people)

[English Version](/solution/1100-1199/1103.Distribute%20Candies%20to%20People/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>排排坐，分糖果。</p>

<p>我们买了一些糖果 <code>candies</code>，打算把它们分给排好队的 <strong><code>n = num_people</code></strong> 个小朋友。</p>

<p>给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 <code>n</code>&nbsp;颗糖果。</p>

<p>然后，我们再回到队伍的起点，给第一个小朋友 <code>n&nbsp;+ 1</code> 颗糖果，第二个小朋友 <code>n&nbsp;+ 2</code> 颗，依此类推，直到给最后一个小朋友 <code>2 * n</code>&nbsp;颗糖果。</p>

<p>重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。</p>

<p>返回一个长度为 <code>num_people</code>、元素之和为 <code>candies</code> 的数组，以表示糖果的最终分发情况（即 <code>ans[i]</code> 表示第 <code>i</code> 个小朋友分到的糖果数）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>candies = 7, num_people = 4
<strong>输出：</strong>[1,2,3,1]
<strong>解释：</strong>
第一次，ans[0] += 1，数组变为 [1,0,0,0]。
第二次，ans[1] += 2，数组变为 [1,2,0,0]。
第三次，ans[2] += 3，数组变为 [1,2,3,0]。
第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>candies = 10, num_people = 3
<strong>输出：</strong>[5,2,3]
<strong>解释：</strong>
第一次，ans[0] += 1，数组变为 [1,0,0]。
第二次，ans[1] += 2，数组变为 [1,2,0]。
第三次，ans[2] += 3，数组变为 [1,2,3]。
第四次，ans[0] += 4，最终数组变为 [5,2,3]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= candies &lt;= 10^9</code></li>
	<li><code>1 &lt;= num_people &lt;= 1000</code></li>
</ul>

## 解法

### 方法一：模拟

我们可以直接模拟每一个人分到糖果的过程，按照题目描述的规则模拟即可。

时间复杂度 $O(\max(\sqrt{candies}, num\_people))$，空间复杂度 $O(num\_people)$。其中 $candies$ 为糖果数量。

<!-- tabs:start -->

```python
class Solution:
    def distributeCandies(self, candies: int, num_people: int) -> List[int]:
        ans = [0] * num_people
        i = 0
        while candies:
            ans[i % num_people] += min(candies, i + 1)
            candies -= min(candies, i + 1)
            i += 1
        return ans
```

```java
class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        for (int i = 0; candies > 0; ++i) {
            ans[i % num_people] += Math.min(candies, i + 1);
            candies -= Math.min(candies, i + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> distributeCandies(int candies, int num_people) {
        vector<int> ans(num_people);
        for (int i = 0; candies > 0; ++i) {
            ans[i % num_people] += min(candies, i + 1);
            candies -= min(candies, i + 1);
        }
        return ans;
    }
};
```

```go
func distributeCandies(candies int, num_people int) []int {
	ans := make([]int, num_people)
	for i := 0; candies > 0; i++ {
		ans[i%num_people] += min(candies, i+1)
		candies -= min(candies, i+1)
	}
	return ans
}
```

```ts
function distributeCandies(candies: number, num_people: number): number[] {
    const ans: number[] = Array(num_people).fill(0);
    for (let i = 0; candies > 0; ++i) {
        ans[i % num_people] += Math.min(candies, i + 1);
        candies -= Math.min(candies, i + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
