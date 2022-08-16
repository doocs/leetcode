# [134. 加油站](https://leetcode.cn/problems/gas-station)

[English Version](/solution/0100-0199/0134.Gas%20Station/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一条环路上有 <code>n</code>&nbsp;个加油站，其中第 <code>i</code>&nbsp;个加油站有汽油&nbsp;<code>gas[i]</code><em>&nbsp;</em>升。</p>

<p>你有一辆油箱容量无限的的汽车，从第<em> </em><code>i</code><em> </em>个加油站开往第<em> </em><code>i+1</code><em>&nbsp;</em>个加油站需要消耗汽油&nbsp;<code>cost[i]</code><em>&nbsp;</em>升。你从其中的一个加油站出发，开始时油箱为空。</p>

<p>给定两个整数数组 <code>gas</code> 和 <code>cost</code> ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 <code>-1</code> 。如果存在解，则 <strong>保证</strong> 它是 <strong>唯一</strong> 的。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> gas = [1,2,3,4,5], cost = [3,4,5,1,2]
<strong>输出:</strong> 3
<strong>解释:
</strong>从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> gas = [2,3,4], cost = [3,4,3]
<strong>输出:</strong> -1
<strong>解释:
</strong>你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
因此，无论怎样，你都不可能绕环路行驶一周。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>gas.length == n</code></li>
	<li><code>cost.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= gas[i], cost[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：从任意起点开始遍历**

我们用 $i$, $j$ 分别标记起点和终点，用 $s$ 表示当前剩余汽油，而 $cnt$ 表示当前行驶过的加油站数量。初始时，我们将起点设在最后一个位置，即 $i=n-1$。

开始行驶，移动 $j$。若发现当前剩余汽油小于 $0$，说明当前 $i$ 作为起点不符合要求，我们将起点 $i$ 循环左移，并且更新剩余汽油，直至剩余汽油是非负数。

当行驶过的加油站数量达到 $n$ 时，结束。判断此时的剩余汽油是否非负，是则返回当前的 $i$ 作为答案；否则返回 $-1$，表示无解。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 表示加油站的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        n = len(gas)
        i = j = n - 1
        cnt = s = 0
        while cnt < n:
            s += gas[j] - cost[j]
            cnt += 1
            j = (j + 1) % n
            while s < 0 and cnt < n:
                i -= 1
                s += gas[i] - cost[i]
                cnt += 1
        return -1 if s < 0 else i
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = n - 1, j = n - 1;
        int cnt = 0, s = 0;
        while (cnt < n) {
            s += gas[j] - cost[j];
            ++cnt;
            j = (j + 1) % n;
            while (s < 0 && cnt < n) {
                --i;
                s += gas[i] - cost[i];
                ++cnt;
            }
        }
        return s < 0 ? -1 : i;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int n = gas.size();
        int i = n - 1, j = n - 1;
        int cnt = 0, s = 0;
        while (cnt < n) {
            s += gas[j] - cost[j];
            ++cnt;
            j = (j + 1) % n;
            while (s < 0 && cnt < n) {
                --i;
                s += gas[i] - cost[i];
                ++cnt;
            }
        }
        return s < 0 ? -1 : i;
    }
};
```

### **Go**

```go
func canCompleteCircuit(gas []int, cost []int) int {
    n := len(gas)
    i, j := n - 1, n - 1
    cnt, s := 0, 0
    for cnt < n {
        s += gas[j] - cost[j]
        cnt++
        j = (j + 1) % n
        for s < 0 && cnt < n {
            i--
            s += gas[i] - cost[i]
            cnt++
        }
    }
    if s < 0 {
        return -1
    }
    return i
}
```

### **...**

```

```

<!-- tabs:end -->
