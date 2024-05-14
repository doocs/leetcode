---
comment: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2055.%20%E9%87%87%E9%9B%86%E6%9E%9C%E5%AE%9E/README.md
---

# [LCP 55. 采集果实](https://leetcode.cn/problems/PTXy4P)

## 题目描述

<!-- 这里写题目描述 -->

欢迎各位勇者来到力扣新手村，本次训练内容为「采集果实」。

在新手村中，各位勇者需要采集一些果实来制作药剂。`time[i]` 表示勇者每次采集 `1～limit` 颗第 `i` 种类型的果实需要的时间（即每次最多可以采集 `limit` 颗果实）。

当前勇者需要完成「采集若干批果实」的任务， `fruits[j] = [type, num]` 表示第 `j` 批需要采集 `num` 颗 `type` 类型的果实。采集规则如下：

-   按 `fruits` 给定的顺序**依次**采集每一批次
-   采集完当前批次的果实才能开始采集下一批次
-   勇者完成当前批次的采集后将**清空背包**（即多余的果实将清空）

请计算并返回勇者完成采集任务最少需要的时间。

**示例 1：**

> 输入：`time = [2,3,2], fruits = [[0,2],[1,4],[2,1]], limit = 3`
>
> 输出：`10`
>
> 解释：
> 由于单次最多采集 3 颗
> 第 0 批需要采集 2 颗第 0 类型果实，需要采集 1 次，耗时为 2\*1=2
> 第 1 批需要采集 4 颗第 1 类型果实，需要采集 2 次，耗时为 3\*2=6
> 第 2 批需要采集 1 颗第 2 类型果实，需要采集 1 次，耗时为 2\*1=2
> 返回总耗时 2+6+2=10

**示例 2：**

> 输入：`time = [1], fruits = [[0,3],[0,5]], limit = 2`
>
> 输出：`5`
>
> 解释：
> 由于单次最多采集 2 颗
> 第 0 批需要采集 3 颗第 0 类型果实，需要采集 2 次，耗时为 1\*2=2
> 第 1 批需要采集 5 颗第 0 类型果实，需要采集 3 次，耗时为 1\*3=3
> 需按照顺序依次采集，返回 2+3=5

**提示：**

-   `1 <= time.length <= 100`
-   `1 <= time[i] <= 100`
-   `1 <= fruits.length <= 10^3`
-   `0 <= fruits[i][0] < time.length`
-   `1 <= fruits[i][1] < 10^3`
-   `1 <= limit <= 100`

## 解法

### 方法一：贪心

对于每个任务，我们贪心地按照 $limit$ 的大小来采集，那么每个任务需要的时间为 $\lceil \frac{num}{limit} \rceil \times time[type]$，其中 $\lceil x \rceil$ 表示对 $x$ 向上取整。我们将所有任务需要的时间求和即为答案。

时间复杂度 $O(n)$，其中 $n$ 是数组 $fruits$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def getMinimumTime(
        self, time: List[int], fruits: List[List[int]], limit: int
    ) -> int:
        return sum((num + limit - 1) // limit * time[i] for i, num in fruits)
```

```java
class Solution {
    public int getMinimumTime(int[] time, int[][] fruits, int limit) {
        int ans = 0;
        for (int[] f : fruits) {
            int i = f[0], num = f[1];
            ans += (num + limit - 1) / limit * time[i];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int getMinimumTime(vector<int>& time, vector<vector<int>>& fruits, int limit) {
        int ans = 0;
        for (auto& f : fruits) {
            int i = f[0], num = f[1];
            ans += (num + limit - 1) / limit * time[i];
        }
        return ans;
    }
};
```

```go
func getMinimumTime(time []int, fruits [][]int, limit int) (ans int) {
	for _, f := range fruits {
		i, num := f[0], f[1]
		ans += (num + limit - 1) / limit * time[i]
	}
	return
}
```

```ts
function getMinimumTime(time: number[], fruits: number[][], limit: number): number {
    let ans = 0;
    for (const [i, num] of fruits) {
        ans += Math.ceil(num / limit) * time[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
