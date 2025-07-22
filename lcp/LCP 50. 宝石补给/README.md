---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2050.%20%E5%AE%9D%E7%9F%B3%E8%A1%A5%E7%BB%99/README.md
---

<!-- problem:start -->

# [LCP 50. 宝石补给](https://leetcode.cn/problems/WHnhjV)

## 题目描述

<!-- description:start -->

欢迎各位勇者来到力扣新手村，在开始试炼之前，请各位勇者先进行「宝石补给」。

每位勇者初始都拥有一些能量宝石， `gem[i]` 表示第 `i` 位勇者的宝石数量。现在这些勇者们进行了一系列的赠送，`operations[j] = [x, y]` 表示在第 `j` 次的赠送中 第 `x` 位勇者将自己一半的宝石（需向下取整）赠送给第 `y` 位勇者。

在完成所有的赠送后，请找到拥有**最多**宝石的勇者和拥有**最少**宝石的勇者，并返回他们二者的宝石数量**之差**。

**注意：**

-   赠送将按顺序逐步进行。

**示例 1：**

> 输入：`gem = [3,1,2], operations = [[0,2],[2,1],[2,0]]`
>
> 输出：`2`
>
> 解释：
> 第 1 次操作，勇者 `0` 将一半的宝石赠送给勇者 `2`， `gem = [2,1,3]`
> 第 2 次操作，勇者 `2` 将一半的宝石赠送给勇者 `1`， `gem = [2,2,2]`
> 第 3 次操作，勇者 `2` 将一半的宝石赠送给勇者 `0`， `gem = [3,2,1]`
> 返回 3 - 1 = 2

**示例 2：**

> 输入：`gem = [100,0,50,100], operations = [[0,2],[0,1],[3,0],[3,0]]`
>
> 输出：`75`
>
> 解释：
> 第 1 次操作，勇者 `0` 将一半的宝石赠送给勇者 `2`， `gem = [50,0,100,100]`
> 第 2 次操作，勇者 `0` 将一半的宝石赠送给勇者 `1`， `gem = [25,25,100,100]`
> 第 3 次操作，勇者 `3` 将一半的宝石赠送给勇者 `0`， `gem = [75,25,100,50]`
> 第 4 次操作，勇者 `3` 将一半的宝石赠送给勇者 `0`， `gem = [100,25,100,25]`
> 返回 100 - 25 = 75

**示例 3：**

> 输入：`gem = [0,0,0,0], operations = [[1,2],[3,1],[1,2]]`
>
> 输出：`0`

**提示：**

-   `2 <= gem.length <= 10^3`
-   `0 <= gem[i] <= 10^3`
-   `0 <= operations.length <= 10^4`
-   `operations[i].length == 2`
-   `0 <= operations[i][0], operations[i][1] < gem.length`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们直接模拟宝石的赠送过程，最后返回最大值和最小值的差值即可。

时间复杂度 $O(m + n)$，其中 $m$ 和 $n$ 分别是数组 `gem` 和 `operations` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def giveGem(self, gem: List[int], operations: List[List[int]]) -> int:
        for x, y in operations:
            v = gem[x] >> 1
            gem[y] += v
            gem[x] -= v
        return max(gem) - min(gem)
```

#### Java

```java
class Solution {
    public int giveGem(int[] gem, int[][] operations) {
        for (var op : operations) {
            int x = op[0], y = op[1];
            int v = gem[x] >> 1;
            gem[y] += v;
            gem[x] -= v;
        }
        int mx = 0, mi = 1 << 30;
        for (int x : gem) {
            mx = Math.max(mx, x);
            mi = Math.min(mi, x);
        }
        return mx - mi;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int giveGem(vector<int>& gem, vector<vector<int>>& operations) {
        for (auto& op : operations) {
            int x = op[0], y = op[1];
            int v = gem[x] >> 1;
            gem[y] += v;
            gem[x] -= v;
        }
        int mx = *max_element(gem.begin(), gem.end());
        int mi = *min_element(gem.begin(), gem.end());
        return mx - mi;
    }
};
```

#### Go

```go
func giveGem(gem []int, operations [][]int) int {
	for _, op := range operations {
		x, y := op[0], op[1]
		v := gem[x] >> 1
		gem[y] += v
		gem[x] -= v
	}
	return slices.Max(gem) - slices.Min(gem)
}
```

#### TypeScript

```ts
function giveGem(gem: number[], operations: number[][]): number {
    for (const [x, y] of operations) {
        const v = gem[x] >> 1;
        gem[y] += v;
        gem[x] -= v;
    }
    return Math.max(...gem) - Math.min(...gem);
}
```

#### Swift

```swift
class Solution {
    func giveGem(_ gem: [Int], _ operations: [[Int]]) -> Int {
        var gem = gem

        for op in operations {
            let x = op[0], y = op[1]
            let v = gem[x] / 2
            gem[y] += v
            gem[x] -= v
        }

        let maxGem = gem.max() ?? 0
        let minGem = gem.min() ?? 0
        return maxGem - minGem
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
