---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2061.%20%E6%B0%94%E6%B8%A9%E5%8F%98%E5%8C%96%E8%B6%8B%E5%8A%BF/README.md
---

<!-- problem:start -->

# [LCP 61. 气温变化趋势](https://leetcode.cn/problems/6CE719)

## 题目描述

<!-- description:start -->

力扣城计划在两地设立「力扣嘉年华」的分会场，气象小组正在分析两地区的气温变化趋势，对于第 `i ~ (i+1)` 天的气温变化趋势，将根据以下规则判断：

-   若第 `i+1` 天的气温 **高于** 第 `i` 天，为 **上升** 趋势
-   若第 `i+1` 天的气温 **等于** 第 `i` 天，为 **平稳** 趋势
-   若第 `i+1` 天的气温 **低于** 第 `i` 天，为 **下降** 趋势

已知 `temperatureA[i]` 和 `temperatureB[i]` 分别表示第 `i` 天两地区的气温。
组委会希望找到一段天数尽可能多，且两地气温变化趋势相同的时间举办嘉年华活动。请分析并返回两地气温变化趋势**相同的最大连续天数**。

> 即最大的 `n`，使得第 `i~i+n` 天之间，两地气温变化趋势相同

**示例 1：**

> 输入：
> `temperatureA = [21,18,18,18,31]` >`temperatureB = [34,32,16,16,17]`
>
> 输出：`2`
>
> 解释：如下表所示， 第 `2～4` 天两地气温变化趋势相同，且持续时间最长，因此返回 `4-2=2` > ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2061.%20%E6%B0%94%E6%B8%A9%E5%8F%98%E5%8C%96%E8%B6%8B%E5%8A%BF/images/1663902654-hlrSvs-image.png){:width=1000px}

**示例 2：**

> 输入：
> `temperatureA = [5,10,16,-6,15,11,3]` >`temperatureB = [16,22,23,23,25,3,-16]`
>
> 输出：`3`

**提示：**

-   `2 <= temperatureA.length == temperatureB.length <= 1000`
-   `-20 <= temperatureA[i], temperatureB[i] <= 40`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们用变量 $f$ 维护当前趋势相同的连续天数，用变量 $ans$ 维护最大的连续天数。

遍历数组，对于第 $i$ 天，记两地的气温变化趋势分别为 $x$ 和 $y$，如果 $x$ 和 $y$ 均为 $0$ 或者 $x$ 和 $y$ 均为正数或负数，则说明第 $i$ 天和第 $i+1$ 天的气温变化趋势相同，此时 $f$ 自增 $1$，并更新 $ans$；否则说明第 $i$ 天和第 $i+1$ 天的气温变化趋势不同，此时 $f$ 重置为 $0$。

最终返回 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def temperatureTrend(self, temperatureA: List[int], temperatureB: List[int]) -> int:
        ans = f = 0
        for (a1, b1), (a2, b2) in pairwise(zip(temperatureA, temperatureB)):
            x, y = a2 - a1, b2 - b1
            if x == y == 0 or x * y > 0:
                f += 1
                ans = max(ans, f)
            else:
                f = 0
        return ans
```

#### Java

```java
class Solution {
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int ans = 0, f = 0;
        for (int i = 0; i < temperatureA.length - 1; ++i) {
            int x = temperatureA[i + 1] - temperatureA[i];
            int y = temperatureB[i + 1] - temperatureB[i];
            if ((x == 0 && y == 0) || x * y > 0) {
                ans = Math.max(ans, ++f);
            } else {
                f = 0;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int temperatureTrend(vector<int>& temperatureA, vector<int>& temperatureB) {
        int ans = 0, f = 0;
        for (int i = 0; i < temperatureA.size() - 1; ++i) {
            int x = temperatureA[i + 1] - temperatureA[i];
            int y = temperatureB[i + 1] - temperatureB[i];
            if ((x == 0 && y == 0) || x * y > 0) {
                ans = max(ans, ++f);
            } else {
                f = 0;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func temperatureTrend(temperatureA []int, temperatureB []int) int {
	ans, f := 0, 0
	for i := range temperatureA[1:] {
		x := temperatureA[i+1] - temperatureA[i]
		y := temperatureB[i+1] - temperatureB[i]
		if (x == 0 && y == 0) || x*y > 0 {
			f++
			ans = max(ans, f)
		} else {
			f = 0
		}
	}
	return ans
}
```

#### TypeScript

```ts
function temperatureTrend(temperatureA: number[], temperatureB: number[]): number {
    let [ans, f] = [0, 0];
    for (let i = 0; i < temperatureA.length - 1; ++i) {
        let x = temperatureA[i + 1] - temperatureA[i];
        let y = temperatureB[i + 1] - temperatureB[i];
        if ((x === 0 && y === 0) || x * y > 0) {
            ans = Math.max(ans, ++f);
        } else {
            f = 0;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn temperature_trend(temperature_a: Vec<i32>, temperature_b: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut f = 0;

        for i in 0..temperature_a.len() - 1 {
            let x = temperature_a[i + 1] - temperature_a[i];
            let y = temperature_b[i + 1] - temperature_b[i];

            if (x == 0 && y == 0) || (x > 0 && y > 0) || (x < 0 && y < 0) {
                f += 1;
                if f > ans {
                    ans = f;
                }
            } else {
                f = 0;
            }
        }

        ans
    }
}
```

#### Swift

```swift
class Solution {
    func temperatureTrend(_ temperatureA: [Int], _ temperatureB: [Int]) -> Int {
        var maxTrend = 0
        var currentTrend = 0

        for i in 0..<temperatureA.count - 1 {
            let changeA = temperatureA[i + 1] - temperatureA[i]
            let changeB = temperatureB[i + 1] - temperatureB[i]

            if (changeA == 0 && changeB == 0) || (changeA * changeB > 0) {
                currentTrend += 1
                maxTrend = max(maxTrend, currentTrend)
            } else {
                currentTrend = 0
            }
        }

        return maxTrend
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
