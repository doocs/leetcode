---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2739.Total%20Distance%20Traveled/README.md
rating: 1262
source: 第 350 场周赛 Q1
tags:
    - 数学
    - 模拟
---

# [2739. 总行驶距离](https://leetcode.cn/problems/total-distance-traveled)

[English Version](/solution/2700-2799/2739.Total%20Distance%20Traveled/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>卡车有两个油箱。给你两个整数，<code>mainTank</code> 表示主油箱中的燃料（以升为单位），<code>additionalTank</code> 表示副油箱中的燃料（以升为单位）。</p>

<p>该卡车每耗费 <code>1</code> 升燃料都可以行驶 <code>10</code> km。每当主油箱使用了 <code>5</code> 升燃料时，如果副油箱至少有 <code>1</code> 升燃料，则会将 <code>1</code> 升燃料从副油箱转移到主油箱。</p>

<p>返回卡车可以行驶的最大距离。</p>

<p>注意：从副油箱向主油箱注入燃料不是连续行为。这一事件会在每消耗 <code>5</code> 升燃料时突然且立即发生。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>mainTank = 5, additionalTank = 10
<strong>输出：</strong>60
<strong>解释：</strong>
在用掉 5 升燃料后，主油箱中燃料还剩下 (5 - 5 + 1) = 1 升，行驶距离为 50km 。
在用掉剩下的 1 升燃料后，没有新的燃料注入到主油箱中，主油箱变为空。
总行驶距离为 60km 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>mainTank = 1, additionalTank = 2
<strong>输出：</strong>10
<strong>解释：</strong>
在用掉 1 升燃料后，主油箱变为空。
总行驶距离为 10km 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= mainTank, additionalTank &lt;= 100</code></li>
</ul>

## 解法

### 方法一：模拟

我们可以模拟卡车的行驶过程，每次消耗 $1$ 升主油箱中的燃料，行驶 $10$ 公里。每当主油箱中的燃料消耗 $5$ 升时，如果副油箱中有燃料，则将副油箱中的 $1$ 升燃料转移到主油箱中。一直模拟到主油箱中的燃料消耗完为止。

时间复杂度 $O(n + m)$，其中 $n$ 和 $m$ 分别是主油箱和副油箱中的燃料数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def distanceTraveled(self, mainTank: int, additionalTank: int) -> int:
        ans = cur = 0
        while mainTank:
            cur += 1
            ans += 10
            mainTank -= 1
            if cur % 5 == 0 and additionalTank:
                additionalTank -= 1
                mainTank += 1
        return ans
```

```java
class Solution {
    public int distanceTraveled(int mainTank, int additionalTank) {
        int ans = 0, cur = 0;
        while (mainTank > 0) {
            cur++;
            ans += 10;
            mainTank--;
            if (cur % 5 == 0 && additionalTank > 0) {
                additionalTank--;
                mainTank++;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int distanceTraveled(int mainTank, int additionalTank) {
        int ans = 0, cur = 0;
        while (mainTank > 0) {
            cur++;
            ans += 10;
            mainTank--;
            if (cur % 5 == 0 && additionalTank > 0) {
                additionalTank--;
                mainTank++;
            }
        }
        return ans;
    }
};
```

```go
func distanceTraveled(mainTank int, additionalTank int) (ans int) {
	cur := 0
	for mainTank > 0 {
		cur++
		ans += 10
		mainTank--
		if cur%5 == 0 && additionalTank > 0 {
			additionalTank--
			mainTank++
		}
	}
	return
}
```

```rust
impl Solution {
    pub fn distance_traveled(mut main_tank: i32, mut additional_tank: i32) -> i32 {
        let mut cur = 0;
        let mut ans = 0;

        while main_tank > 0 {
            cur += 1;
            main_tank -= 1;
            ans += 10;

            if cur % 5 == 0 && additional_tank > 0 {
                additional_tank -= 1;
                main_tank += 1;
            }
        }

        ans
    }
}
```

```js
var distanceTraveled = function (mainTank, additionalTank) {
    let ans = 0,
        cur = 0;
    while (mainTank) {
        cur++;
        ans += 10;
        mainTank--;
        if (cur % 5 === 0 && additionalTank) {
            additionalTank--;
            mainTank++;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- end -->
