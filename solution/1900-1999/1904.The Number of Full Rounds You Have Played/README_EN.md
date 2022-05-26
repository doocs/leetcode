# [1904. The Number of Full Rounds You Have Played](https://leetcode.com/problems/the-number-of-full-rounds-you-have-played)

[中文文档](/solution/1900-1999/1904.The%20Number%20of%20Full%20Rounds%20You%20Have%20Played/README.md)

## Description

<p>You are participating in an online chess tournament. There is a chess round that starts every <code>15</code> minutes. The first round of the day starts at <code>00:00</code>, and after every <code>15</code> minutes, a new round starts.</p>

<ul>
	<li>For example, the second round starts at <code>00:15</code>, the fourth round starts at <code>00:45</code>, and the seventh round starts at <code>01:30</code>.</li>
</ul>

<p>You are given two strings <code>loginTime</code> and <code>logoutTime</code> where:</p>

<ul>
	<li><code>loginTime</code> is the time you will login to the game, and</li>
	<li><code>logoutTime</code> is the time you will logout from the game.</li>
</ul>

<p>If <code>logoutTime</code> is <strong>earlier</strong> than <code>loginTime</code>, this means you have played from <code>loginTime</code> to midnight and from midnight to <code>logoutTime</code>.</p>

<p>Return <em>the number of full chess rounds you have played in the tournament</em>.</p>

<p><strong>Note:</strong>&nbsp;All the given times follow the 24-hour clock. That means the first round of the day starts at <code>00:00</code> and the last round of the day starts at <code>23:45</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> loginTime = &quot;09:31&quot;, logoutTime = &quot;10:14&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> You played one full round from 09:45 to 10:00.
You did not play the full round from 09:30 to 09:45 because you logged in at 09:31 after it began.
You did not play the full round from 10:00 to 10:15 because you logged out at 10:14 before it ended.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> loginTime = &quot;21:30&quot;, logoutTime = &quot;03:00&quot;
<strong>Output:</strong> 22
<strong>Explanation:</strong> You played 10 full rounds from 21:30 to 00:00 and 12 full rounds from 00:00 to 03:00.
10 + 12 = 22.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>loginTime</code> and <code>logoutTime</code> are in the format <code>hh:mm</code>.</li>
	<li><code>00 &lt;= hh &lt;= 23</code></li>
	<li><code>00 &lt;= mm &lt;= 59</code></li>
	<li><code>loginTime</code> and <code>logoutTime</code> are not equal.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfRounds(self, startTime: str, finishTime: str) -> int:
        def get(s: str) -> int:
            return int(s[:2]) * 60 + int(s[3:])

        start, finish = get(startTime), get(finishTime)
        if start > finish:
            finish += 24 * 60
        start, finish = (start + 14) // 15, finish // 15
        return max(0, finish - start)
```

### **Java**

```java
class Solution {
    public int numberOfRounds(String startTime, String finishTime) {
        int start = get(startTime), finish = get(finishTime);
        if (start > finish) {
            finish += 24 * 60;
        }
        start = (start + 14) / 15;
        finish /= 15;
        return Math.max(0, finish - start);
    }

    private int get(String s) {
        return Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3));
    }
}
```

### **TypeScript**

```ts
function numberOfRounds(startTime: string, finishTime: string): number {
    let m1 = toMinutes(startTime),
        m2 = toMinutes(finishTime);
    if (m1 > m2) {
        m2 += 24 * 60;
    }
    let ans = Math.floor(m2 / 15) - Math.ceil(m1 / 15);
    return ans > 0 ? ans : 0;
}

function toMinutes(time: string): number {
    let [h, m] = time.split(':').map(Number);
    return h * 60 + m;
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfRounds(string startTime, string finishTime) {
        int start = get(startTime), finish = get(finishTime);
        if (start > finish) {
            finish += 24 * 60;
        }
        start = (start + 14) / 15;
        finish /= 15;
        return max(0, finish - start);
    }

private:
    int get(string s) {
        int a, b;
        sscanf(s.c_str(), "%d:%d", &a, &b);
        return a * 60 + b;
    }
};
```

### **Go**

```go
func numberOfRounds(startTime string, finishTime string) int {
	start, finish := get(startTime), get(finishTime)
	if start > finish {
		finish += 24 * 60
	}
	start = (start + 14) / 15
	finish /= 15
	if start > finish {
		return 0
	}
	return finish - start

}

func get(s string) int {
	var a, b int
	fmt.Sscanf(s, "%d:%d", &a, &b)
	return a*60 + b
}
```

### **...**

```

```

<!-- tabs:end -->
