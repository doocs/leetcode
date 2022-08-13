# [1011. 在 D 天内送达包裹的能力](https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days)

[English Version](/solution/1000-1099/1011.Capacity%20To%20Ship%20Packages%20Within%20D%20Days/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>传送带上的包裹必须在 <code>days</code> 天内从一个港口运送到另一个港口。</p>

<p>传送带上的第 <code>i</code>&nbsp;个包裹的重量为&nbsp;<code>weights[i]</code>。每一天，我们都会按给出重量（<code>weights</code>）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。</p>

<p>返回能在 <code>days</code> 天内将传送带上的所有包裹送达的船的最低运载能力。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>weights = [1,2,3,4,5,6,7,8,9,10], days = 5
<strong>输出：</strong>15
<strong>解释：</strong>
船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
第 1 天：1, 2, 3, 4, 5
第 2 天：6, 7
第 3 天：8
第 4 天：9
第 5 天：10

请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>weights = [3,2,2,4,1,4], days = 3
<strong>输出：</strong>6
<strong>解释：</strong>
船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
第 1 天：3, 2
第 2 天：2, 4
第 3 天：1, 4
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>weights = [1,2,3,1,1], days = 4
<strong>输出：</strong>3
<strong>解释：</strong>
第 1 天：1
第 2 天：2
第 3 天：3
第 4 天：1, 1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= days &lt;= weights.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= weights[i] &lt;= 500</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

二分枚举运载能力 capacity，找到能在 D 天内送达的最小运载。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shipWithinDays(self, weights: List[int], D: int) -> int:
        def check(capacity):
            cnt, t = 1, 0
            for w in weights:
                if w > capacity:
                    return False
                if t + w <= capacity:
                    t += w
                else:
                    cnt += 1
                    t = w
            return cnt <= D

        left, right = 1, 25000000
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (canCarry(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    public boolean canCarry(int[] weights, int days, int carry) {
        int useDay = 1;
        int curCarry = 0;
        for (int weight : weights) {
            if (weight > carry) {
                return false;
            }
            if ((carry - curCarry) >= weight) {
                curCarry += weight;
            } else {
                curCarry = weight;
                useDay++;
            }
        }
        return useDay <= days;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shipWithinDays(vector<int>& weights, int days) {
        int left = 1, right = 25000000;
        while (left < right) {
            int mid = left + right >> 1;
            if (check(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    bool check(vector<int>& weights, int days, int capacity) {
        int cnt = 1, t = 0;
        for (auto w : weights) {
            if (w > capacity) {
                return false;
            }
            if (t + w <= capacity) {
                t += w;
            } else {
                ++cnt;
                t = w;
            }
        }
        return cnt <= days;
    }
};
```

### **Go**

```go
func shipWithinDays(weights []int, days int) int {
	left, right := 1, 25000000
	for left < right {
		mid := (left + right) >> 1
		if check(weights, days, mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func check(weights []int, days, capacity int) bool {
	cnt, t := 1, 0
	for _, w := range weights {
		if w > capacity {
			return false
		}
		if t+w <= capacity {
			t += w
		} else {
			cnt++
			t = w
		}
	}
	return cnt <= days
}
```

### **...**

```

```

<!-- tabs:end -->
