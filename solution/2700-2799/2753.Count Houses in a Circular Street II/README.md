---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2753.Count%20Houses%20in%20a%20Circular%20Street%20II/README.md
---

<!-- problem:start -->

# [2753. 计算一个环形街道上的房屋数量 II 🔒](https://leetcode.cn/problems/count-houses-in-a-circular-street-ii)

[English Version](/solution/2700-2799/2753.Count%20Houses%20in%20a%20Circular%20Street%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个代表&nbsp;<strong>环形&nbsp;</strong>街道的类&nbsp;<code>Street</code>&nbsp;和一个正整数&nbsp;<code>k</code>，表示街道上房屋的最大数量（也就是说房屋数量不超过&nbsp;<code>k</code>）。每个房屋的门初始时可以是开着的也可以是关着的（至少有一个房屋的门是开着的）。</p>

<p>刚开始，你站在一座房子的门前。你的任务是计算街道上的房屋数量。</p>

<p><code>Street</code>&nbsp;类包含以下函数：</p>

<ul>
	<li><code>void closeDoor()</code>：关闭当前房屋的门。</li>
	<li><code>boolean isDoorOpen()</code>：如果当前房屋的门是开着的返回&nbsp;<code>true</code>，否则返回&nbsp;<code>false</code>。</li>
	<li><code>void moveRight()</code>：向右移动到下一座房屋。</li>
</ul>

<p><strong>注意：</strong>在<strong>&nbsp;环形&nbsp;</strong>街道内，如果将房屋从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;编号，则当&nbsp;<code>i &lt; n</code>&nbsp;时&nbsp;<code>house<sub>i</sub></code>&nbsp;右边的房子是&nbsp;<code>house<sub>i+1</sub></code>，<code>house<sub>n</sub></code>&nbsp;右边的房子是&nbsp;<code>house<sub>1</sub></code>。</p>

<p>返回&nbsp;<code>ans</code>，它表示街道上的房屋数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>street = [1,1,1,1], k = 10
<b>输出：</b>4
<b>解释：</b>街道上有 4 座房屋，它们的门都是开着的。
房屋数量小于 k，即 10。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>street = [1,0,1,1,0], k = 5
<b>输出：</b>5
<strong>解释：</strong>街道上有 5 座房屋，向右移动时第 1、3 和 4 座房屋的门是开着的，其余的门都是关着的。
房屋数量等于 k，即 5。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n</code>&nbsp;是房屋数量</li>
	<li><code>1 &lt;= n &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>street</code>&nbsp;是环形的</li>
	<li>输入数据中至少有一扇门是开着的</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

我们注意到，题目中至少有一扇门是开着的，我们可以先找到其中一扇开着的门。

然后，我们跳过这扇开着的门，往右移动，每次移动时，计数器加一，如果遇到开着的门，就把门关上。那么答案就是最后一次遇到的开着的门时的计数器的值。

时间复杂度 $O(k)$，空间复杂度 $O(1)$。

相似题目：

-   [2728. 计算一个环形街道上的房屋数量](https://github.com/doocs/leetcode/blob/main/solution/2700-2799/2728.Count%20Houses%20in%20a%20Circular%20Street/README.md)

<!-- tabs:start -->

```python
# Definition for a street.
# class Street:
#     def closeDoor(self):
#         pass
#     def isDoorOpen(self):
#         pass
#     def moveRight(self):
#         pass
class Solution:
    def houseCount(self, street: Optional["Street"], k: int) -> int:
        while not street.isDoorOpen():
            street.moveRight()
        for i in range(1, k + 1):
            street.moveRight()
            if street.isDoorOpen():
                ans = i
                street.closeDoor()
        return ans
```

```java
/**
 * Definition for a street.
 * class Street {
 *     public Street(int[] doors);
 *     public void closeDoor();
 *     public boolean isDoorOpen();
 *     public void moveRight();
 * }
 */
class Solution {
    public int houseCount(Street street, int k) {
        while (!street.isDoorOpen()) {
            street.moveRight();
        }
        int ans = 0;
        for (int i = 1; i <= k; ++i) {
            street.moveRight();
            if (street.isDoorOpen()) {
                ans = i;
                street.closeDoor();
            }
        }
        return ans;
    }
}
```

```cpp
/**
 * Definition for a street.
 * class Street {
 * public:
 *     Street(vector<int> doors);
 *     void closeDoor();
 *     bool isDoorOpen();
 *     void moveRight();
 * };
 */
class Solution {
public:
    int houseCount(Street* street, int k) {
        while (!street->isDoorOpen()) {
            street->moveRight();
        }
        int ans = 0;
        for (int i = 1; i <= k; ++i) {
            street->moveRight();
            if (street->isDoorOpen()) {
                ans = i;
                street->closeDoor();
            }
        }
        return ans;
    }
};
```

```go
/**
 * Definition for a street.
 * type Street interface {
 *     CloseDoor()
 *     IsDoorOpen() bool
 *     MoveRight()
 * }
 */
func houseCount(street Street, k int) (ans int) {
	for !street.IsDoorOpen() {
		street.MoveRight()
	}
	for i := 1; i <= k; i++ {
		street.MoveRight()
		if street.IsDoorOpen() {
			ans = i
			street.CloseDoor()
		}
	}
	return
}
```

```ts
/**
 * Definition for a street.
 * class Street {
 *     constructor(doors: number[]);
 *     public closeDoor(): void;
 *     public isDoorOpen(): boolean;
 *     public moveRight(): void;
 * }
 */
function houseCount(street: Street | null, k: number): number {
    while (!street.isDoorOpen()) {
        street.moveRight();
    }
    let ans = 0;
    for (let i = 1; i <= k; ++i) {
        street.moveRight();
        if (street.isDoorOpen()) {
            ans = i;
            street.closeDoor();
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
