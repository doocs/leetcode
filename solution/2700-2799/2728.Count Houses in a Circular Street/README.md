# [2728. 计算一个环形街道上的房屋数量](https://leetcode.cn/problems/count-houses-in-a-circular-street)

[English Version](/solution/2700-2799/2728.Count%20Houses%20in%20a%20Circular%20Street/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个代表环形街道的类 <code>Street</code> 和一个正整数 <code>k</code>，表示街道上房屋的最大数量（也就是说房屋数量不超过 <code>k</code>&nbsp;）。每个房屋的门初始时可以是开着的也可以是关着的。</p>

<p>刚开始，你站在一座房子的门前。你的任务是计算街道上的房屋数量。</p>

<p><code>Street</code> 类包含以下函数：</p>

<ul>
	<li><code>void openDoor()</code> ：打开当前房屋的门。</li>
	<li><code>void closeDoor()</code> ：关闭当前房屋的门。</li>
	<li><code>boolean isDoorOpen()</code> ：如果当前房屋的门是开着的返回 <code>true</code> ，否则返回 <code>false</code> 。</li>
	<li><code>void moveRight()</code> ：向右移动到下一座房屋。</li>
	<li><code>void moveLeft()</code> ：向左移动到上一座房屋。</li>
</ul>

<p>返回 <code>ans</code>，它表示街道上的房屋数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>street = [0,0,0,0], k = 10
<b>输出：</b>4
<b>解释：</b>街道上有 4 座房屋，它们的门都是关着的。
房屋数量小于 k，即 10。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>street = [1,0,1,1,0], k = 5
<b>输出：</b>5
<b>解释：</b>街道上有 5 座房屋，向右移动时第 1、3 和 4 座房屋的门是开着的，其余的门都是关着的。房屋数量等于 k，即 5。
</pre>

<p>&nbsp;</p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>n&nbsp; 是房屋数量</code></li>
	<li><code>1 &lt;= n &lt;= k &lt;= 10<sup>3</sup></code></li>
</ul>

## 解法

### 方法一：遍历

我们先循环 $k$ 次，每次打开当前房子的门，然后向左移动一格，循环结束后，所有房子的门都是打开的。

然后，我们再循环左移，如果当前房子的门是打开的，就关闭它，房子数加一，继续左移，直到当前房子的门是关闭的，循环结束，返回房子数。

时间复杂度 $O(k)$，其中 $k$ 为题目给定的整数。空间复杂度 $O(1)$。

相似题目：

-   [2753. 计算一个环形街道上的房屋数量 II](https://github.com/doocs/leetcode/blob/main/solution/2700-2799/2753.Count%20Houses%20in%20a%20Circular%20Street%20II/README.md)

<!-- tabs:start -->

```python
# Definition for a street.
# class Street:
#     def openDoor(self):
#         pass
#     def closeDoor(self):
#         pass
#     def isDoorOpen(self):
#         pass
#     def moveRight(self):
#         pass
#     def moveLeft(self):
#         pass
class Solution:
    def houseCount(self, street: Optional["Street"], k: int) -> int:
        for _ in range(k):
            street.openDoor()
            street.moveLeft()
        ans = 0
        while street.isDoorOpen():
            street.closeDoor()
            street.moveLeft()
            ans += 1
        return ans
```

```java
/**
 * Definition for a street.
 * class Street {
 *     public Street(int[] doors);
 *     public void openDoor();
 *     public void closeDoor();
 *     public boolean isDoorOpen();
 *     public void moveRight();
 *     public void moveLeft();
 * }
 */
class Solution {
    public int houseCount(Street street, int k) {
        while (k-- > 0) {
            street.openDoor();
            street.moveLeft();
        }
        int ans = 0;
        while (street.isDoorOpen()) {
            ++ans;
            street.closeDoor();
            street.moveLeft();
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
 *     void openDoor();
 *     void closeDoor();
 *     bool isDoorOpen();
 *     void moveRight();
 *     void moveLeft();
 * };
 */
class Solution {
public:
    int houseCount(Street* street, int k) {
        while (k--) {
            street->openDoor();
            street->moveLeft();
        }
        int ans = 0;
        while (street->isDoorOpen()) {
            ans++;
            street->closeDoor();
            street->moveLeft();
        }
        return ans;
    }
};
```

```go
/**
 * Definition for a street.
 * type Street interface {
 *     OpenDoor()
 *     CloseDoor()
 *     IsDoorOpen() bool
 *     MoveRight()
 *     MoveLeft()
 * }
 */
func houseCount(street Street, k int) (ans int) {
	for ; k > 0; k-- {
		street.OpenDoor()
		street.MoveLeft()
	}
	for ; street.IsDoorOpen(); street.MoveLeft() {
		ans++
		street.CloseDoor()
	}
	return
}
```

```ts
/**
 * Definition for a street.
 * class Street {
 *     constructor(doors: number[]);
 *     public openDoor(): void;
 *     public closeDoor(): void;
 *     public isDoorOpen(): boolean;
 *     public moveRight(): void;
 *     public moveLeft(): void;
 * }
 */
function houseCount(street: Street | null, k: number): number {
    while (k-- > 0) {
        street.openDoor();
        street.moveLeft();
    }
    let ans = 0;
    while (street.isDoorOpen()) {
        ++ans;
        street.closeDoor();
        street.moveLeft();
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
