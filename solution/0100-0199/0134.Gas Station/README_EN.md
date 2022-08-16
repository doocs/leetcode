# [134. Gas Station](https://leetcode.com/problems/gas-station)

[中文文档](/solution/0100-0199/0134.Gas%20Station/README.md)

## Description

<p>There are <code>n</code> gas stations along a circular route, where the amount of gas at the <code>i<sup>th</sup></code> station is <code>gas[i]</code>.</p>

<p>You have a car with an unlimited gas tank and it costs <code>cost[i]</code> of gas to travel from the <code>i<sup>th</sup></code> station to its next <code>(i + 1)<sup>th</sup></code> station. You begin the journey with an empty tank at one of the gas stations.</p>

<p>Given two integer arrays <code>gas</code> and <code>cost</code>, return <em>the starting gas station&#39;s index if you can travel around the circuit once in the clockwise direction, otherwise return</em> <code>-1</code>. If there exists a solution, it is <strong>guaranteed</strong> to be <strong>unique</strong></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> gas = [1,2,3,4,5], cost = [3,4,5,1,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> gas = [2,3,4], cost = [3,4,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong>
You can&#39;t start at station 0 or 1, as there is not enough gas to travel to the next station.
Let&#39;s start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can&#39;t travel around the circuit once no matter where you start.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == gas.length == cost.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= gas[i], cost[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
