# [2798. 满足目标工作时长的员工数目](https://leetcode.cn/problems/number-of-employees-who-met-the-target)

[English Version](/solution/2700-2799/2798.Number%20of%20Employees%20Who%20Met%20the%20Target/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>公司里共有 <code>n</code> 名员工，按从 <code>0</code> 到 <code>n - 1</code> 编号。每个员工 <code>i</code> 已经在公司工作了 <code>hours[i]</code> 小时。</p>

<p>公司要求每位员工工作&nbsp;<strong>至少</strong> <code>target</code> 小时。</p>

<p>给你一个下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的非负整数数组 <code>hours</code> 和一个非负整数 <code>target</code> 。</p>

<p>请你用整数表示并返回工作至少 <code>target</code> 小时的员工数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>hours = [0,1,2,3,4], target = 2
<strong>输出：</strong>3
<strong>解释：</strong>公司要求每位员工工作至少 2 小时。
- 员工 0 工作 0 小时，不满足要求。
- 员工 1 工作 1 小时，不满足要求。
- 员工 2 工作 2 小时，满足要求。
- 员工 3 工作 3 小时，满足要求。
- 员工 4 工作 4 小时，满足要求。
共有 3 位满足要求的员工。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>hours = [5,1,4,2,2], target = 6
<strong>输出：</strong>0
<strong>解释：</strong>公司要求每位员工工作至少 6 小时。
共有 0 位满足要求的员工。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == hours.length &lt;= 50</code></li>
	<li><code>0 &lt;=&nbsp;hours[i], target &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历计数**

我们可以遍历数组 $hours$，对于每个员工，如果其工作时长 $x$ 大于等于 $target$，则将计数器 $ans$ 加一。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $hours$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfEmployeesWhoMetTarget(self, hours: List[int], target: int) -> int:
        return sum(x >= target for x in hours)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int ans = 0;
        for (int x : hours) {
            if (x >= target) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfEmployeesWhoMetTarget(vector<int>& hours, int target) {
        int ans = 0;
        for (int x : hours) {
            ans += x >= target;
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfEmployeesWhoMetTarget(hours []int, target int) (ans int) {
	for _, x := range hours {
		if x >= target {
			ans++
		}
	}
	return
}
```

### **TypeScript**

```ts
function numberOfEmployeesWhoMetTarget(
    hours: number[],
    target: number,
): number {
    let ans = 0;
    for (const x of hours) {
        if (x >= target) {
            ++ans;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn number_of_employees_who_met_target(hours: Vec<i32>, target: i32) -> i32 {
        let mut ans = 0;
        for &v in hours.iter() {
            if v >= target {
                ans += 1;
            }
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
