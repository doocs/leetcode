---
comments: true
---

<!-- problem:start -->

# [LCP 29. 乐团站位](https://leetcode.cn/problems/SNJvJP)

## 题目描述

<!-- description:start -->

某乐团的演出场地可视作 `num * num` 的二维矩阵 `grid`（左上角坐标为 `[0,0]`)，每个位置站有一位成员。乐团共有 `9` 种乐器，乐器编号为 `1~9`，每位成员持有 `1` 个乐器。

为保证声乐混合效果，成员站位规则为：自 `grid` 左上角开始顺时针螺旋形向内循环以 `1，2，...，9` 循环重复排列。例如当 num = `5` 时，站位如图所示

![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2029.%20乐团站位/images/1616125411-WOblWH-image.png)

请返回位于场地坐标 [`Xpos`,`Ypos`] 的成员所持乐器编号。

**示例 1：**

> 输入：`num = 3, Xpos = 0, Ypos = 2`
>
> 输出：`3`
>
> 解释：
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2029.%20乐团站位/images/1616125437-WUOwsu-image.png)

**示例 2：**

> 输入：`num = 4, Xpos = 1, Ypos = 2`
>
> 输出：`5`
>
> 解释：
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2029.%20乐团站位/images/1616125453-IIDpxg-image.png)

**提示：**

- `1 <= num <= 10^9`
- `0 <= Xpos, Ypos < num`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

<!-- thinking:start -->

> **思考**
>
> $n$ 最大为 $10^9$，按顺时针把 $1$ 到 $9$ 填满矩阵再读取 $(x,y)$，时间和空间都无法承受。每个位置只落在某一圈上，圈号由它到四条边界的最短距离决定，圈外已经站过的人数可以写成关于圈号的闭合式。
>
> 乐器以 $9$ 为周期，圈首编号只跟圈外人数对 $9$ 的余数有关。圈内不必再逐格模拟，只要判断坐标落在上、右、下、左哪一条边上，用边长算出从圈左上角走到该点的步数，加到圈首编号上再取模。

<!-- thinking:end -->

记场地边长为 $n$，查询坐标为 $(x,y)$。该位置所在的圈号为

$$
k=\min(x,y,n-1-x,n-1-y)
$$

第 $i$ 圈（ $i$ 从 $0$ 计）的边长是 $n-2i$，一圈的人数是 $4(n-1-2i)$。因此第 $k$ 圈之外已经站过的人数为

$$
\sum_{i=0}^{k-1}4(n-1-2i)=4k(n-k)
$$

这一圈左上角的乐器编号是 $start=4k(n-k)\bmod 9+1$。Python 先计算 $4(n-1)k-4k(k-1)$，它与 $4k(n-k)$ 相等；其余语言把每个因子对 $9$ 取模后再相乘，避免 $n\le 10^9$ 时中间结果溢出。

当前圈的边长 $s=n-2k$。从左上角 $(k,k)$ 顺时针行走，四条边上的步数 $offset$ 为：

- 上边 $x=k$： $offset=y-k$
- 右边 $y=n-k-1$： $offset=(s-1)+(x-k)$
- 下边 $x=n-k-1$： $offset=2(s-1)+(n-k-1-y)$
- 左边： $offset=3(s-1)+(n-k-1-x)$

先匹配上边、再匹配右边和下边，拐角只计入先经过的那条边。最内层若只剩一格，它落在上边且步数为 $0$。最终乐器编号为

$$
(start-1+offset)\bmod 9+1
$$

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def orchestraLayout(self, num: int, xPos: int, yPos: int) -> int:
        layer = min(xPos, yPos, num - 1 - xPos, num - 1 - yPos)
        side = num - 2 * layer
        start = (4 * (num - 1) * layer - 4 * layer * (layer - 1)) % 9 + 1

        if xPos == layer:
            offset = yPos - layer
        elif yPos == num - layer - 1:
            offset = side - 1 + xPos - layer
        elif xPos == num - layer - 1:
            offset = 2 * side - 2 + num - layer - 1 - yPos
        else:
            offset = 3 * side - 3 + num - layer - 1 - xPos

        return (start - 1 + offset) % 9 + 1
```

#### Java

```java
class Solution {
    public int orchestraLayout(int num, int xPos, int yPos) {
        long n = num;
        long x = xPos;
        long y = yPos;

        long layer = Math.min(Math.min(x, y), Math.min(n - 1 - x, n - 1 - y));
        long side = n - 2 * layer;
        long start = ((4 * (layer % 9)) % 9 * ((n - layer) % 9)) % 9 + 1;

        long offset;
        if (x == layer) {
            offset = y - layer;
        } else if (y == n - layer - 1) {
            offset = side - 1 + x - layer;
        } else if (x == n - layer - 1) {
            offset = 2 * side - 2 + n - layer - 1 - y;
        } else {
            offset = 3 * side - 3 + n - layer - 1 - x;
        }

        return (int) ((start - 1 + offset) % 9 + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int orchestraLayout(int num, int xPos, int yPos) {
        long long n = num;
        long long x = xPos;
        long long y = yPos;

        long long layer = min(min(x, y), min(n - 1 - x, n - 1 - y));
        long long side = n - 2 * layer;
        long long start = ((4 * (layer % 9)) % 9 * ((n - layer) % 9)) % 9 + 1;

        long long offset;
        if (x == layer) {
            offset = y - layer;
        } else if (y == n - layer - 1) {
            offset = side - 1 + x - layer;
        } else if (x == n - layer - 1) {
            offset = 2 * side - 2 + n - layer - 1 - y;
        } else {
            offset = 3 * side - 3 + n - layer - 1 - x;
        }

        return (start - 1 + offset) % 9 + 1;
    }
};
```

#### Go

```go
func orchestraLayout(num int, xPos int, yPos int) int {
	n := int64(num)
	x := int64(xPos)
	y := int64(yPos)

	layer := min(min(x, y), min(n-1-x, n-1-y))
	side := n - 2*layer
	start := ((4*(layer%9))%9*((n-layer)%9))%9 + 1

	var offset int64
	if x == layer {
		offset = y - layer
	} else if y == n-layer-1 {
		offset = side - 1 + x - layer
	} else if x == n-layer-1 {
		offset = 2*side - 2 + n - layer - 1 - y
	} else {
		offset = 3*side - 3 + n - layer - 1 - x
	}

	return int((start-1+offset)%9 + 1)
}
```

#### TypeScript

```ts
function orchestraLayout(num: number, xPos: number, yPos: number): number {
    const layer = Math.min(xPos, yPos, num - 1 - xPos, num - 1 - yPos);
    const side = num - 2 * layer;
    const start = ((((4 * (layer % 9)) % 9) * ((num - layer) % 9)) % 9) + 1;

    let offset: number;
    if (xPos === layer) {
        offset = yPos - layer;
    } else if (yPos === num - layer - 1) {
        offset = side - 1 + xPos - layer;
    } else if (xPos === num - layer - 1) {
        offset = 2 * side - 2 + num - layer - 1 - yPos;
    } else {
        offset = 3 * side - 3 + num - layer - 1 - xPos;
    }

    return ((start - 1 + offset) % 9) + 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
