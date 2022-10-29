# [2446. Determine if Two Events Have Conflict](https://leetcode.com/problems/determine-if-two-events-have-conflict)

[中文文档](/solution/2400-2499/2446.Determine%20if%20Two%20Events%20Have%20Conflict/README.md)

## Description

<p>You are given two arrays of strings that represent two inclusive events that happened <strong>on the same day</strong>, <code>event1</code> and <code>event2</code>, where:</p>

<ul>
	<li><code>event1 = [startTime<sub>1</sub>, endTime<sub>1</sub>]</code> and</li>
	<li><code>event2 = [startTime<sub>2</sub>, endTime<sub>2</sub>]</code>.</li>
</ul>

<p>Event times are valid 24 hours format in the form of <code>HH:MM</code>.</p>

<p>A <strong>conflict</strong> happens when two events have some non-empty intersection (i.e., some moment is common to both events).</p>

<p>Return <code>true</code><em> if there is a conflict between two events. Otherwise, return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> event1 = [&quot;01:15&quot;,&quot;02:00&quot;], event2 = [&quot;02:00&quot;,&quot;03:00&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong> The two events intersect at time 2:00.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> event1 = [&quot;01:00&quot;,&quot;02:00&quot;], event2 = [&quot;01:20&quot;,&quot;03:00&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong> The two events intersect starting from 01:20 to 02:00.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> event1 = [&quot;10:00&quot;,&quot;11:00&quot;], event2 = [&quot;14:00&quot;,&quot;15:00&quot;]
<strong>Output:</strong> false
<strong>Explanation:</strong> The two events do not intersect.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>evnet1.length == event2.length == 2.</code></li>
	<li><code>event1[i].length == event2[i].length == 5</code></li>
	<li><code>startTime<sub>1</sub> &lt;= endTime<sub>1</sub></code></li>
	<li><code>startTime<sub>2</sub> &lt;= endTime<sub>2</sub></code></li>
	<li>All the event times follow the <code>HH:MM</code> format.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def haveConflict(self, event1: List[str], event2: List[str]) -> bool:
        return event1[0] <= event2[1] and event1[1] >= event2[0]
```

### **Java**

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
