# [1904. The Number of Full Rounds You Have Played](https://leetcode.com/problems/the-number-of-full-rounds-you-have-played)

[中文文档](/solution/1900-1999/1904.The%20Number%20of%20Full%20Rounds%20You%20Have%20Played/README.md)

## Description

<p>A new online video game has been released, and in this video game, there are <strong>15-minute</strong> rounds scheduled every <strong>quarter-hour</strong> period. This means that at <code>HH:00</code>, <code>HH:15</code>, <code>HH:30</code> and <code>HH:45</code>, a new round starts, where <code>HH</code> represents an integer number from <code>00</code> to <code>23</code>. A <strong>24-hour clock</strong> is used, so the earliest time in the day is <code>00:00</code> and the latest is <code>23:59</code>.</p>

<p>Given two strings <code>startTime</code> and <code>finishTime</code> in the format <code>&quot;HH:MM&quot;</code> representing the exact time you <strong>started</strong> and <strong>finished</strong> playing the game, respectively, calculate the <strong>number of full rounds</strong> that you played during your game session.</p>

<ul>
	<li>For example, if <code>startTime = &quot;05:20&quot;</code> and <code>finishTime = &quot;05:59&quot;</code> this means you played only one full round from <code>05:30</code> to <code>05:45</code>. You did not play the full round from <code>05:15</code> to <code>05:30</code> because you started after the round began, and you did not play the full round from <code>05:45</code> to <code>06:00</code> because you stopped before the round ended.</li>
</ul>

<p>If <code>finishTime</code> is <strong>earlier</strong> than <code>startTime</code>, this means you have played overnight (from <code>startTime</code> to the midnight and from midnight to <code>finishTime</code>).</p>

<p>Return <em>the <strong>number of full rounds</strong> that you have played if you had started playing at </em><code>startTime</code><em> and finished at </em><code>finishTime</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> startTime = &quot;12:01&quot;, finishTime = &quot;12:44&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> You played one full round from 12:15 to 12:30.
You did not play the full round from 12:00 to 12:15 because you started playing at 12:01 after it began.
You did not play the full round from 12:30 to 12:45 because you stopped playing at 12:44 before it ended.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> startTime = &quot;20:00&quot;, finishTime = &quot;06:00&quot;
<strong>Output:</strong> 40
<strong>Explanation:</strong> You played 16 full rounds from 20:00 to 00:00 and 24 full rounds from 00:00 to 06:00.
16 + 24 = 40.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> startTime = &quot;00:00&quot;, finishTime = &quot;23:59&quot;
<strong>Output:</strong> 95
<strong>Explanation:</strong> You played 4 full rounds each hour except for the last hour where you played 3 full rounds.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>startTime</code> and <code>finishTime</code> are in the format <code>HH:MM</code>.</li>
	<li><code>00 &lt;= HH &lt;= 23</code></li>
	<li><code>00 &lt;= MM &lt;= 59</code></li>
	<li><code>startTime</code> and <code>finishTime</code> are not equal.</li>
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

### **JavaScript**

```js
/**
 * @param {string} startTime
 * @param {string} finishTime
 * @return {number}
 */
 var numberOfRounds = function(startTime, finishTime) {
    let m1 = toMinutes(startTime), m2 = toMinutes(finishTime);

    if (m1 > m2) {
        m2 += 24 * 60;
    }

    let ans = Math.floor(m2 / 15) - Math.ceil(m1 / 15);
    return ans < 0 ? 0 : ans;
};

function toMinutes(time) {
    let [h, m] = time.split(':');
    return Number(h) * 60 + Number(m);
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
