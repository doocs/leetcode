# [1870. 准时到达的列车最小时速](https://leetcode-cn.com/problems/minimum-speed-to-arrive-on-time)

[English Version](/solution/1800-1899/1870.Minimum%20Speed%20to%20Arrive%20on%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个浮点数 <code>hour</code> ，表示你到达办公室可用的总通勤时间。要到达办公室，你必须按给定次序乘坐 <code>n</code> 趟列车。另给你一个长度为 <code>n</code> 的整数数组 <code>dist</code> ，其中 <code>dist[i]</code> 表示第 <code>i</code> 趟列车的行驶距离（单位是千米）。</p>

<p>每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。</p>

<ul>
	<li>例如，第 <code>1</code> 趟列车需要 <code>1.5</code> 小时，那你必须再等待 <code>0.5</code> 小时，搭乘在第 2 小时发车的第 <code>2</code> 趟列车。</li>
</ul>

<p>返回能满足你准时到达办公室所要求全部列车的<strong> 最小正整数 </strong>时速（单位：千米每小时），如果无法准时到达，则返回 <code>-1</code> 。</p>

<p>生成的测试用例保证答案不超过 <code>10<sup>7</sup></code> ，且 <code>hour</code> 的 <strong>小数点后最多存在两位数字</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dist = [1,3,2], hour = 6
<strong>输出：</strong>1
<strong>解释：</strong>速度为 1 时：
- 第 1 趟列车运行需要 1/1 = 1 小时。
- 由于是在整数时间到达，可以立即换乘在第 1 小时发车的列车。第 2 趟列车运行需要 3/1 = 3 小时。
- 由于是在整数时间到达，可以立即换乘在第 4 小时发车的列车。第 3 趟列车运行需要 2/1 = 2 小时。
- 你将会恰好在第 6 小时到达。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>dist = [1,3,2], hour = 2.7
<strong>输出：</strong>3
<strong>解释：</strong>速度为 3 时：
- 第 1 趟列车运行需要 1/3 = 0.33333 小时。
- 由于不是在整数时间到达，故需要等待至第 1 小时才能搭乘列车。第 2 趟列车运行需要 3/3 = 1 小时。
- 由于是在整数时间到达，可以立即换乘在第 2 小时发车的列车。第 3 趟列车运行需要 2/3 = 0.66667 小时。
- 你将会在第 2.66667 小时到达。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>dist = [1,3,2], hour = 1.9
<strong>输出：</strong>-1
<strong>解释：</strong>不可能准时到达，因为第 3 趟列车最早是在第 2 小时发车。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == dist.length</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= dist[i] <= 10<sup>5</sup></code></li>
	<li><code>1 <= hour <= 10<sup>9</sup></code></li>
	<li><code>hours</code> 中，小数点后最多存在两位数字</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分法。

以“二分”的方式枚举速度值，找到满足条件的最小速度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSpeedOnTime(self, dist: List[int], hour: float) -> int:
        def arrive_on_time(speed):
            res = 0
            for i, d in enumerate(dist):
                res += (d / speed) if i == len(dist) - 1 else math.ceil(d / speed)
            return res <= hour

        left, right = 1, 10 ** 7
        while left < right:
            mid = (left + right) >> 1
            if arrive_on_time(mid):
                right = mid
            else:
                left = mid + 1
        return left if arrive_on_time(left) else -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int left = 1, right = (int) 1e7;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arriveOnTime(dist, mid, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arriveOnTime(dist, left, hour) ? left : -1;
    }

    private boolean arriveOnTime(int[] dist, int speed, double hour) {
        double res = 0;
        for (int i = 0; i < dist.length; ++i) {
            double cost = dist[i] * 1.0 / speed;
            res += (i == dist.length - 1 ? cost : Math.ceil(cost));
        }
        return res <= hour;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSpeedOnTime(vector<int>& dist, double hour) {
        int left = 1, right = 1e7;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arriveOnTime(dist, mid, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arriveOnTime(dist, left, hour) ? left : -1;
    }

    bool arriveOnTime(vector<int>& dist, int speed, double hour) {
        double res = 0;
        for (int i = 0; i < dist.size(); ++i) {
            double cost = dist[i] * 1.0 / speed;
            res += (i == dist.size() - 1 ? cost : ceil(cost));
        }
        return res <= hour;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number[]} dist
 * @param {number} hour
 * @return {number}
 */
var minSpeedOnTime = function (dist, hour) {
    if (dist.length > Math.ceil(hour)) return -1;
    let left = 1,
        right = 10 ** 7;
    while (left < right) {
        let mid = (left + right) >> 1;
        if (arriveOnTime(dist, mid, hour)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};

function arriveOnTime(dist, speed, hour) {
    let res = 0.0;
    let n = dist.length;
    for (let i = 0; i < n; i++) {
        let cost = parseFloat(dist[i]) / speed;
        if (i != n - 1) {
            cost = Math.ceil(cost);
        }
        res += cost;
    }
    return res <= hour;
}
```

### **Go**

```go
func minSpeedOnTime(dist []int, hour float64) int {
	n := len(dist)
	left, right := 1, int(1e7)
	for left < right {
		mid := (left + right) >> 1
		if arriveOnTime(dist, n, float64(mid), hour) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if arriveOnTime(dist, n, float64(left), hour) {
		return left
	}
	return -1
}

func arriveOnTime(dist []int, n int, speed, hour float64) bool {
	var cost float64
	for _, v := range dist[:n-1] {
		cost += math.Ceil(float64(v) / speed)
	}
	cost += float64(dist[n-1]) / speed
	return cost <= hour
}
```

### **...**

```

```

<!-- tabs:end -->
