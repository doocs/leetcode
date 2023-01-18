# [1826. 有缺陷的传感器](https://leetcode.cn/problems/faulty-sensor)

[English Version](/solution/1800-1899/1826.Faulty%20Sensor/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实验室里正在进行一项实验。为了确保数据的准确性，同时使用 <strong>两个</strong> 传感器来采集数据。您将获得2个数组 <code>sensor1</code> and <code>sensor2</code>，其中 <code>sensor1[i]</code> 和 <code>sensor2[i]</code> 分别是两个传感器对<span style="">第 <code>i</code> 个</span>数据点采集到的数据。</p>

<p>但是，这种类型的传感器有可能存在缺陷，它会导致 <strong>某一个</strong> 数据点采集的数据（掉落值）被丢弃。</p>

<p>数据被丢弃后，所有在其右侧的数据点采集的数据，都会被向左移动一个位置，最后一个数据点采集的数据会被一些随机值替换。可以保证此随机值不等于掉落值。</p>

<ul>
	<li>举个例子, 如果正确的数据是 <code>[1,2,<em><strong>3</strong></em>,4,5]</code> ， 此时 <code>3</code> 被丢弃了, 传感器会返回 <code>[1,2,4,5,<em><strong>7</strong></em>]</code> (最后的位置可以是任何值, 不仅仅是 <code>7</code>).</li>
</ul>

<p>可以确定的是，<strong>最多有一个</strong> 传感器有缺陷。请返回这个有缺陷的传感器的编号 （<code>1</code> 或 <code>2</code>）。如果任一传感器 <strong>没有缺陷</strong> ，或者 <strong>无法</strong> 确定有缺陷的传感器，则返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sensor1 = [2,3,4,5], sensor2 = [2,1,3,4]
<strong>输出：</strong>1
<strong>解释：</strong>传感器 2 返回了所有正确的数据.
传感器2对第二个数据点采集的数据，被传感器1丢弃了，传感器1返回的最后一个数据被替换为 5 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sensor1 = [2,2,2,2,2], sensor2 = [2,2,2,2,5]
<strong>输出：</strong>-1
<strong>解释：</strong>无法判定拿个传感器是有缺陷的。
假设任一传感器丢弃的数据是最后一位，那么，另一个传感器就能给出与之对应的输出。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>sensor1 = [2,3,2,2,3,2], sensor2 = [2,3,2,3,2,7]
<strong>输出：</strong>2
<strong>解释：</strong>传感器 1 返回了所有正确的数据.
传感器 1 对第四个数据点的采集数据，被传感器2丢失了, 传感器 2 返回的最后一个数据被替换为 7 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>sensor1.length == sensor2.length</code></li>
	<li><code>1 <= sensor1.length <= 100</code></li>
	<li><code>1 <= sensor1[i], sensor2[i] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历**

遍历两个数组，找到第一个不相等的位置 $i$。如果 $i \lt n - 1$，循环比较 $sensor1[i + 1]$ 和 $sensor2[i]$，如果不相等，说明传感器 $1$ 有缺陷，返回 $1$；否则比较 $sensor1[i]$ 和 $sensor2[i + 1]$，如果不相等，说明传感器 $2$ 有缺陷，返回 $2$。

遍历结束，说明无法确定有缺陷的传感器，返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def badSensor(self, sensor1: List[int], sensor2: List[int]) -> int:
        i, n = 0, len(sensor1)
        while i < n - 1:
            if sensor1[i] != sensor2[i]:
                break
            i += 1
        while i < n - 1:
            if sensor1[i + 1] != sensor2[i]:
                return 1
            if sensor1[i] != sensor2[i + 1]:
                return 2
            i += 1
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int badSensor(int[] sensor1, int[] sensor2) {
        int i = 0;
        int n = sensor1.length;
        for (; i < n - 1 && sensor1[i] == sensor2[i]; ++i) {
        }
        for (; i < n - 1; ++i) {
            if (sensor1[i + 1] != sensor2[i]) {
                return 1;
            }
            if (sensor1[i] != sensor2[i + 1]) {
                return 2;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int badSensor(vector<int>& sensor1, vector<int>& sensor2) {
        int i = 0;
        int n = sensor1.size();
        for (; i < n - 1 && sensor1[i] == sensor2[i]; ++i) { }
        for (; i < n - 1; ++i) {
            if (sensor1[i + 1] != sensor2[i]) return 1;
            if (sensor1[i] != sensor2[i + 1]) return 2;
        }
        return -1;
    }
};
```

### **Go**

```go
func badSensor(sensor1 []int, sensor2 []int) int {
	i, n := 0, len(sensor1)
	for ; i < n-1 && sensor1[i] == sensor2[i]; i++ {
	}
	for ; i < n-1; i++ {
		if sensor1[i+1] != sensor2[i] {
			return 1
		}
		if sensor1[i] != sensor2[i+1] {
			return 2
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
