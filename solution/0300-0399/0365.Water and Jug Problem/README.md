# [365. 水壶问题](https://leetcode-cn.com/problems/water-and-jug-problem)

[English Version](/solution/0300-0399/0365.Water%20and%20Jug%20Problem/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有两个容量分别为&nbsp;<em>x</em>升 和<em> y</em>升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好&nbsp;<em>z</em>升 的水？</p>

<p>如果可以，最后请用以上水壶中的一或两个来盛放取得的&nbsp;<em>z升&nbsp;</em>水。</p>

<p>你允许：</p>

<ul>
	<li>装满任意一个水壶</li>
	<li>清空任意一个水壶</li>
	<li>从一个水壶向另外一个水壶倒水，直到装满或者倒空</li>
</ul>

<p><strong>示例 1:</strong> (From the famous <a href="https://www.youtube.com/watch?v=BVtQNK_ZUJg"><em>&quot;Die Hard&quot;</em> example</a>)</p>

<pre>输入: x = 3, y = 5, z = 4
输出: True
</pre>

<p><strong>示例 2:</strong></p>

<pre>输入: x = 2, y = 6, z = 5
输出: False
</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canMeasureWater(self, jug1Capacity: int, jug2Capacity: int, targetCapacity: int) -> bool:
        stk, seen = [], set()
        stk.append([0, 0])

        def get_hash(nums):
            return nums[0] * 10000006 + nums[1]

        while stk:
            if get_hash(stk[-1]) in seen:
                stk.pop()
                continue
            seen.add(get_hash(stk[-1]))
            cur = stk.pop()
            cur1, cur2 = cur[0], cur[1]
            if cur1 == targetCapacity or cur2 == targetCapacity or cur1 + cur2 == targetCapacity:
                return True
            stk.append([jug1Capacity, cur2])
            stk.append([0, cur2])
            stk.append([cur1, jug2Capacity])
            stk.append([cur1, 0])
            if cur1 + cur2 > jug1Capacity:
                stk.append([jug1Capacity, cur2 - jug1Capacity + cur1])
            else:
                stk.append([cur1 + cur2, 0])
            if cur1 + cur2 > jug2Capacity:
                stk.append([cur1 - jug2Capacity + cur2, jug2Capacity])
            else:
                stk.append([0, cur1 + cur2])
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Deque<int[]> stk = new ArrayDeque<>();
        stk.add(new int[]{0, 0});
        Set<Long> seen = new HashSet<>();
        while (!stk.isEmpty()) {
            if (seen.contains(hash(stk.peek()))) {
                stk.pop();
                continue;
            }
            int[] cur = stk.pop();
            seen.add(hash(cur));
            int cur1 = cur[0], cur2 = cur[1];
            if (cur1 == targetCapacity || cur2 == targetCapacity || cur1 + cur2 == targetCapacity) {
                return true;
            }
            stk.offer(new int[]{jug1Capacity, cur2});
            stk.offer(new int[]{0, cur2});
            stk.offer(new int[]{cur1, jug1Capacity});
            stk.offer(new int[]{cur2, 0});
            if (cur1 + cur2 > jug1Capacity) {
                stk.offer(new int[]{jug1Capacity, cur2 - jug1Capacity + cur1});
            } else {
                stk.offer(new int[]{cur1 + cur2, 0});
            }
            if (cur1 + cur2 > jug2Capacity) {
                stk.offer(new int[]{cur1 - jug2Capacity + cur2, jug2Capacity});
            } else {
                stk.offer(new int[]{0, cur1 + cur2});
            }
       
        }
        return false;
    }

    public long hash(int[] nums) {
        return nums[0] * 10000006L + nums[1];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
