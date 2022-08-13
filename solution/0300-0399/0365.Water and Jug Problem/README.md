# [365. 水壶问题](https://leetcode.cn/problems/water-and-jug-problem)

[English Version](/solution/0300-0399/0365.Water%20and%20Jug%20Problem/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有两个水壶，容量分别为&nbsp;<code>jug1Capacity</code>&nbsp;和 <code>jug2Capacity</code> 升。水的供应是无限的。确定是否有可能使用这两个壶准确得到&nbsp;<code>targetCapacity</code> 升。</p>

<p>如果可以得到&nbsp;<code>targetCapacity</code>&nbsp;升水，最后请用以上水壶中的一或两个来盛放取得的&nbsp;<code>targetCapacity</code>&nbsp;升水。</p>

<p>你可以：</p>

<ul>
	<li>装满任意一个水壶</li>
	<li>清空任意一个水壶</li>
	<li>从一个水壶向另外一个水壶倒水，直到装满或者倒空</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong>&nbsp;</p>

<pre>
<strong>输入:</strong> jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
<strong>输出:</strong> true
<strong>解释</strong>：来自著名的&nbsp;<a href="https://www.youtube.com/watch?v=BVtQNK_ZUJg"><em>"Die Hard"</em></a></pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
<strong>输出:</strong> false
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
<strong>输出:</strong> true
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= jug1Capacity, jug2Capacity, targetCapacity &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

可以认为，每次操作只会让壶里的水带来 x 或者 y 的变化量。因此只要满足 `ax + by = z` 即可。

根据裴蜀定理，`ax + by = z` 有解，当且仅当 z 是 x，y 的最大公约数的倍数。所以我们只要找到 x，y 的最大公约数，然后判断 z 是否是这个最大公约数的倍数即可求得答案。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canMeasureWater(
        self, jug1Capacity: int, jug2Capacity: int, targetCapacity: int
    ) -> bool:
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
            if (
                cur1 == targetCapacity
                or cur2 == targetCapacity
                or cur1 + cur2 == targetCapacity
            ):
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

```python
class Solution:
    def canMeasureWater(self, jug1Capacity: int, jug2Capacity: int, targetCapacity: int) -> bool:
        if jug1Capacity + jug2Capacity < targetCapacity:
            return False
        if jug1Capacity == 0 or jug2Capacity == 0:
            return targetCapacity == 0 or jug1Capacity + jug2Capacity == targetCapacity
        return targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0
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

```java
class Solution {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (jug1Capacity + jug2Capacity < targetCapacity) {
            return false;
        }
        if (jug1Capacity == 0 || jug2Capacity == 0) {
            return targetCapacity == 0 || jug1Capacity + jug2Capacity == targetCapacity;
        }
        return targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (jug1Capacity + jug2Capacity < targetCapacity) return false;
        if (jug1Capacity == 0 || jug2Capacity == 0)
            return targetCapacity == 0 || jug1Capacity + jug2Capacity == targetCapacity;
        return targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};
```

### **Go**

```go
func canMeasureWater(jug1Capacity int, jug2Capacity int, targetCapacity int) bool {
	if jug1Capacity+jug2Capacity < targetCapacity {
		return false
	}
	if jug1Capacity == 0 || jug2Capacity == 0 {
		return targetCapacity == 0 || jug1Capacity+jug2Capacity == targetCapacity
	}

	var gcd func(a, b int) int
	gcd = func(a, b int) int {
		if b == 0 {
			return a
		}
		return gcd(b, a%b)
	}
	return targetCapacity%gcd(jug1Capacity, jug2Capacity) == 0
}
```

### **...**

```

```

<!-- tabs:end -->
