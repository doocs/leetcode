# [1870. 准时到达的列车最小时速](https://leetcode.cn/problems/minimum-speed-to-arrive-on-time)

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

**方法一：二分查找**

二分枚举速度值，找到满足条件的最小速度。

以下是二分查找的两个通用模板：

模板 1：

```java
boolean check(int x) {}

int search(int left, int right) {
    while (left < right) {
        int mid = (left + right) >> 1;
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

模板 2：

```java
boolean check(int x) {}

int search(int left, int right) {
    while (left < right) {
        int mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
```

做二分题目时，可以按照以下步骤：

1. 写出循环条件：`while (left < right)`，注意是 `left < right`，而非 `left <= right`；
1. 循环体内，先无脑写出 `mid = (left + right) >> 1`；
1. 根据具体题目，实现 `check()` 函数（有时很简单的逻辑，可以不定义 `check`），想一下究竟要用 `right = mid`（模板 1） 还是 `left = mid`（模板 2）；
    - 如果 `right = mid`，那么无脑写出 else 语句 `left = mid + 1`，并且不需要更改 mid 的计算，即保持 `mid = (left + right) >> 1`；
    - 如果 `left = mid`，那么无脑写出 else 语句 `right = mid - 1`，并且在 mid 计算时补充 +1，即 `mid = (left + right + 1) >> 1`。
1. 循环结束时，left 与 right 相等。

注意，这两个模板的优点是始终保持答案位于二分区间内，二分结束条件对应的值恰好在答案所处的位置。 对于可能无解的情况，只要判断二分结束后的 left 或者 right 是否满足题意即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSpeedOnTime(self, dist: List[int], hour: float) -> int:
        def check(speed):
            res = 0
            for i, d in enumerate(dist):
                res += (d / speed) if i == len(dist) - 1 else math.ceil(d / speed)
            return res <= hour

        left, right = 1, 10**7
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left if check(left) else -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int left = 1, right = (int) 1e7;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(dist, mid, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return check(dist, left, hour) ? left : -1;
    }

    private boolean check(int[] dist, int speed, double hour) {
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
            if (check(dist, mid, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return check(dist, left, hour) ? left : -1;
    }

    bool check(vector<int>& dist, int speed, double hour) {
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
	check := func(speed float64) bool {
		var cost float64
		for _, v := range dist[:n-1] {
			cost += math.Ceil(float64(v) / speed)
		}
		cost += float64(dist[n-1]) / speed
		return cost <= hour

	}
	for left < right {
		mid := (left + right) >> 1
		if check(float64(mid)) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if check(float64(left)) {
		return left
	}
	return -1
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_speed_on_time(dist: Vec<i32>, hour: f64) -> i32 {
        let n = dist.len();

        let check = |speed| {
            let mut cur = 0.;
            for (i, &d) in dist.iter().enumerate() {
                if i == n - 1 {
                    cur += d as f64 / speed as f64;
                } else {
                    cur += (d as f64 / speed as f64).ceil();
                }
            }
            cur <= hour
        };

        let mut left = 1;
        let mut right = 1e7 as i32;
        while left < right {
            let mid = left + (right - left) / 2;
            if !check(mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if check(left) {
            return left;
        }
        -1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
