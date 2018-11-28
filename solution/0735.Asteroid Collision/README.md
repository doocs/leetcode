## 行星碰撞

### 问题描述

给定一个整数数组 asteroids，表示在同一行的行星。

对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。

找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。

**示例1:**
```
输入:
asteroids = [5, 10, -5]
输出: [5, 10]
解释:
10 和 -5 碰撞后只剩下 10。 5 和 10 永远不会发生碰撞。
```
**示例2:**
```
输入:
asteroids = [8, -8]
输出: []
解释:
8 和 -8 碰撞后，两者都发生爆炸。
```
**说明:**
- 数组 asteroids 的长度不超过 10000。
- 每一颗行星的大小都是非零整数，范围是 [-1000, 1000] 。

### 解法

从左向右遍历数组。
- 对于遇到的大于零的小行星，应先假定其存活；
- 对于遇到的小于零的小行星`x`，其绝对值为'val'，若之前没有大于零的小行星，则此小行星会存活。若之前有大于零的小行星，则从右到左遍历之前存活的小行星：
 - 遇到值小于零的小行星，则小行星`x`存活，并停止；
 - 遇到值等于'val'的小行星，那个小行星会爆炸，并停止；
 - 遇到值大于'val'的小行星停止；
 - 遇到值大于零小于'val'的小行星，那个小行星会爆炸，并继续向左遍历；

```python
class Solution:
    def asteroidCollision(self, asteroids):
        if not asteroids:
            return []
        ans = []
        for i in asteroids:
            if i > 0:
                ans.append(i)
            if i < 0:
                if not ans or ans[-1] < 0:
                    ans.append(i)
                else:
                    while ans:
                        if ans[-1] < 0:
                            ans.append(i)
                            break
                        elif ans[-1] > abs(i):
                            break
                        elif ans[-1] == abs(i):
                            ans.pop(-1)
                            break
                        else:
                            ans.pop(-1)

                    else:
                        ans.append(i)
        return ans
```
