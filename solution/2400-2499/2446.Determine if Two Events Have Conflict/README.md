# [2446. 判断两个事件是否存在冲突](https://leetcode.cn/problems/determine-if-two-events-have-conflict)

[English Version](/solution/2400-2499/2446.Determine%20if%20Two%20Events%20Have%20Conflict/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串数组 <code>event1</code> 和&nbsp;<code>event2</code>&nbsp;，表示发生在同一天的两个闭区间时间段事件，其中：</p>

<ul>
	<li><code>event1 = [startTime<sub>1</sub>, endTime<sub>1</sub>]</code> 且</li>
	<li><code>event2 = [startTime<sub>2</sub>, endTime<sub>2</sub>]</code></li>
</ul>

<p>事件的时间为有效的 24 小时制且按&nbsp;<code>HH:MM</code>&nbsp;格式给出。</p>

<p>当两个事件存在某个非空的交集时（即，某些时刻是两个事件都包含的），则认为出现 <strong>冲突</strong>&nbsp;。</p>

<p>如果两个事件之间存在冲突，返回&nbsp;<code>true</code><em>&nbsp;</em>；否则，返回<em>&nbsp;</em><code>false</code> 。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
<b>输出：</b>true
<b>解释：</b>两个事件在 2:00 出现交集。
</pre>

<p><b>示例 2：</b></p>

<pre>
<b>输入：</b>event1 = ["01:00","02:00"], event2 = ["01:20","03:00"]
<b>输出：</b>true
<b>解释：</b>两个事件的交集从 01:20 开始，到 02:00 结束。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]
<b>输出：</b>false
<b>解释：</b>两个事件不存在交集。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>evnet1.length == event2.length == 2.</code></li>
	<li><code>event1[i].length == event2[i].length == 5</code></li>
	<li><code>startTime<sub>1</sub> &lt;= endTime<sub>1</sub></code></li>
	<li><code>startTime<sub>2</sub> &lt;= endTime<sub>2</sub></code></li>
	<li>所有事件的时间都按照&nbsp;<code>HH:MM</code>&nbsp;格式给出</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：字符串比较**

事件 1 的开始时间小于等于事件 2 的结束时间，且事件 1 的结束时间大于等于事件 2 的开始时间，即可判断出事件 1 和事件 2 存在交集。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def haveConflict(self, event1: List[str], event2: List[str]) -> bool:
        return event1[0] <= event2[1] and event1[1] >= event2[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean haveConflict(String[] event1, String[] event2) {
        return event1[0].compareTo(event2[1]) <= 0 && event1[1].compareTo(event2[0]) >= 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool haveConflict(vector<string>& event1, vector<string>& event2) {
        return event1[0] <= event2[1] && event1[1] >= event2[0];
    }
};
```

### **Go**

```go
func haveConflict(event1 []string, event2 []string) bool {
    return event1[0] <= event2[1] && event1[1] >= event2[0]
}
```

### **TypeScript**

```ts
function haveConflict(event1: string[], event2: string[]): boolean {
    return event1[0] <= event2[1] && event1[1] >= event2[0];
}
```

### **...**

```

```

<!-- tabs:end -->
