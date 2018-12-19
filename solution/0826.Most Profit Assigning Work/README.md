## 安排工作以达到最大收益

### 问题描述

有一些工作：difficulty[i] 表示第i个工作的难度，profit[i]表示第i个工作的收益。

现在我们有一些工人。worker[i]是第i个工人的能力，即该工人只能完成难度小于等于worker[i]的工作。

每一个工人都最多只能安排一个工作，但是一个工作可以完成多次。

举个例子，如果3个工人都尝试完成一份报酬为1的同样工作，那么总收益为 $3。如果一个工人不能完成任何工作，他的收益为 $0 。

我们能得到的最大收益是多少？

**示例:**
```
输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
输出: 100
解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
```

**提示:**
- 1 <= difficulty.length = profit.length <= 10000
- 1 <= worker.length <= 10000
- difficulty[i], profit[i], worker[i]  的范围是 [1, 10^5]

### 解法
先按工作难度将`difficulty`和`profit`排序，再将`worker`排序。赚的最多的收益是在其能力之内的
收益最大值。能力高的工人不会比能力低的工人赚的少。**注意**，工作难度高不代表收益一定高。

```python
class Solution:
    def maxProfitAssignment(self, difficulty, profit, worker):
        ans = 0
        worker.sort()
        ls = [[difficulty[i], profit[i]] for i in range(len(profit))]
        ls.sort(key=lambda x: x[0])

        loc = 0
        flag = ls[0][1]
        leng = len(ls)
        for i in worker:
            while loc < leng:
                if i < ls[loc][0] and loc == 0:
                    break
                elif i < ls[loc][0]:
                    ans += flag
                    break
                else:
                    if flag < ls[loc][1]:
                        flag = ls[loc][1]
                    loc += 1
            else:
                ans += flag
        return ans

```
