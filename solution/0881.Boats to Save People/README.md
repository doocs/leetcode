## 救生艇

### 问题描述

第 `i` 个人的体重为 `people[i]`，每艘船可以承载的最大重量为 `limit`。

每艘船最多可同时载两人，但条件是这些人的重量之和最多为 `limit`。

返回载到每一个人所需的最小船数。(保证每个人都能被船载)。

**示例1:**
```
输入：people = [1,2], limit = 3
输出：1
解释：1 艘船载 (1, 2)
```
**示例2:**
```
输入：people = [3,2,2,1], limit = 3
输出：3
解释：3 艘船分别载 (1, 2), (2) 和 (3)
```
**示例3:**
```
输入：people = [3,5,3,4], limit = 5
输出：4
解释：4 艘船分别载 (3), (3), (4), (5)
```
**提示:**
- 1 <= people.length <= 50000
- 1 <= people[i] <= limit <= 30000

### 解法
最重的人必定和最轻的人一组。如果最重的人加最轻的人的体重都超标了，则最重的人只能单独一组。使用头尾双指针即可解决问题。

```python
class Solution:
    def numRescueBoats(self, people, limit):
        people.sort()
        left = 0
        right = len(people) - 1
        ans = 0
        while left < right:
            if people[left] + people[right] <= limit:
                ans += 1
                left += 1
                right -= 1
            else:
                ans += 1
                right -= 1
        else:
            if left == right:
                ans += 1
        return ans
```
