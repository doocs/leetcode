# [1921. 消灭怪物的最大数量](https://leetcode.cn/problems/eliminate-maximum-number-of-monsters)

[English Version](/solution/1900-1999/1921.Eliminate%20Maximum%20Number%20of%20Monsters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在玩一款电子游戏，在游戏中你需要保护城市免受怪物侵袭。给你一个 <strong>下标从 0 开始</strong> 且长度为 <code>n</code> 的整数数组 <code>dist</code> ，其中 <code>dist[i]</code> 是第 <code>i</code> 个怪物与城市的 <strong>初始距离</strong>（单位：米）。</p>

<p>怪物以 <strong>恒定</strong> 的速度走向城市。给你一个长度为 <code>n</code> 的整数数组 <code>speed</code> 表示每个怪物的速度，其中 <code>speed[i]</code> 是第 <code>i</code> 个怪物的速度（单位：米/分）。</p>

<p>怪物从 <strong>第 0 分钟</strong> 时开始移动。你有一把武器，并可以 <strong>选择</strong> 在每一分钟的开始时使用，包括第 0 分钟。但是你无法在一分钟的中间使用武器。这种武器威力惊人，一次可以消灭任一还活着的怪物。</p>

<p>一旦任一怪物到达城市，你就输掉了这场游戏。如果某个怪物 <strong>恰</strong> 在某一分钟开始时到达城市，这会被视为<strong> 输掉</strong> 游戏，在你可以使用武器之前，游戏就会结束。</p>

<p>返回在你输掉游戏前可以消灭的怪物的 <strong>最大</strong> 数量。如果你可以在所有怪物到达城市前将它们全部消灭，返回  <code>n</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dist = [1,3,4], speed = [1,1,1]
<strong>输出：</strong>3
<strong>解释：</strong>
第 0 分钟开始时，怪物的距离是 [1,3,4]，你消灭了第一个怪物。
第 1 分钟开始时，怪物的距离是 [X,2,3]，你没有消灭任何怪物。
第 2 分钟开始时，怪物的距离是 [X,1,2]，你消灭了第二个怪物。
第 3 分钟开始时，怪物的距离是 [X,X,1]，你消灭了第三个怪物。
所有 3 个怪物都可以被消灭。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>dist = [1,1,2,3], speed = [1,1,1,1]
<strong>输出：</strong>1
<strong>解释：</strong>
第 0 分钟开始时，怪物的距离是 [1,1,2,3]，你消灭了第一个怪物。
第 1 分钟开始时，怪物的距离是 [X,0,1,2]，你输掉了游戏。
你只能消灭 1 个怪物。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>dist = [3,2,4], speed = [5,3,2]
<strong>输出：</strong>1
<strong>解释：</strong>
第 0 分钟开始时，怪物的距离是 [3,2,4]，你消灭了第一个怪物。
第 1 分钟开始时，怪物的距离是 [X,0,2]，你输掉了游戏。 
你只能消灭 1 个怪物。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == dist.length == speed.length</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= dist[i], speed[i] <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

用 $times$ 数组记录每个怪物最晚可被消灭的时间。对于第 $i$ 个怪物，最晚可被消灭的时间满足：

$$times[i] = \lfloor \frac{dist[i]-1}{speed[i]} \rfloor$$

我们对 $times$ 数组升序排列，然后遍历 $times$ 数组。对于第 $i$ 个怪物，如果 $times[i] \geq i$，说明第 $i$ 个怪物可以被消灭，否则说明第 $i$ 个怪物无法被消灭，直接返回 $i$ 即可。

若所有怪物都可以被消灭，则返回 $n$。

时间复杂度 $O(nlogn)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def eliminateMaximum(self, dist: List[int], speed: List[int]) -> int:
        times = [(d - 1) // s for d, s in zip(dist, speed)]
        times.sort()
        for i, t in enumerate(times):
            if t < i:
                return i
        return len(dist)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] times = new int[n];
        for (int i = 0; i < n; ++i) {
            times[i] = (dist[i] - 1) / speed[i];
        }
        Arrays.sort(times);
        for (int i = 0; i < n; ++i) {
            if (times[i] < i) {
                return i;
            }
        }
        return n;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} dist
 * @param {number[]} speed
 * @return {number}
 */
var eliminateMaximum = function (dist, speed) {
    let arr = [];
    for (let i = 0; i < dist.length; i++) {
        arr[i] = dist[i] / speed[i];
    }
    arr.sort((a, b) => a - b);
    let ans = 0;
    while (arr[0] > ans) {
        arr.shift();
        ++ans;
    }
    return ans;
};
```

### **C++**

```cpp
class Solution {
public:
    int eliminateMaximum(vector<int>& dist, vector<int>& speed) {
        int n = dist.size();
        vector<int> times;
        for (int i = 0; i < n; ++i) {
            times.push_back((dist[i] - 1) / speed[i]);
        }
        sort(times.begin(), times.end());
        for (int i = 0; i < n; ++i) {
            if (times[i] < i) {
                return i;
            }
        }
        return n;
    }
};
```

### **Go**

```go
func eliminateMaximum(dist []int, speed []int) int {
	n := len(dist)
	times := make([]int, n)
	for i, d := range dist {
		times[i] = (d - 1) / speed[i]
	}
	sort.Ints(times)
	for i, t := range times {
		if t < i {
			return i
		}
	}
	return n
}
```

### **TypeScript**

```ts
function eliminateMaximum(dist: number[], speed: number[]): number {
    const n = dist.length;
    const times = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        times[i] = Math.floor((dist[i] - 1) / speed[i]);
    }
    times.sort((a, b) => a - b);
    for (let i = 0; i < n; ++i) {
        if (times[i] < i) {
            return i;
        }
    }
    return n;
}
```

### **...**

```

```

<!-- tabs:end -->
