---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0359.Logger%20Rate%20Limiter/README_EN.md
tags:
    - Design
    - Hash Table
    - Data Stream
---

<!-- problem:start -->

# [359. Logger Rate Limiter 🔒](https://leetcode.com/problems/logger-rate-limiter)

[中文文档](/solution/0300-0399/0359.Logger%20Rate%20Limiter/README.md)

## Description

<!-- description:start -->

<p>Design a logger system that receives a stream of messages along with their timestamps. Each <strong>unique</strong> message should only be printed <strong>at most every 10 seconds</strong> (i.e. a message printed at timestamp <code>t</code> will prevent other identical messages from being printed until timestamp <code>t + 10</code>).</p>

<p>All messages will come in chronological order. Several messages may arrive at the same timestamp.</p>

<p>Implement the <code>Logger</code> class:</p>

<ul>
	<li><code>Logger()</code> Initializes the <code>logger</code> object.</li>
	<li><code>bool shouldPrintMessage(int timestamp, string message)</code> Returns <code>true</code> if the <code>message</code> should be printed in the given <code>timestamp</code>, otherwise returns <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Logger&quot;, &quot;shouldPrintMessage&quot;, &quot;shouldPrintMessage&quot;, &quot;shouldPrintMessage&quot;, &quot;shouldPrintMessage&quot;, &quot;shouldPrintMessage&quot;, &quot;shouldPrintMessage&quot;]
[[], [1, &quot;foo&quot;], [2, &quot;bar&quot;], [3, &quot;foo&quot;], [8, &quot;bar&quot;], [10, &quot;foo&quot;], [11, &quot;foo&quot;]]
<strong>Output</strong>
[null, true, true, false, false, false, true]

<strong>Explanation</strong>
Logger logger = new Logger();
logger.shouldPrintMessage(1, &quot;foo&quot;);  // return true, next allowed timestamp for &quot;foo&quot; is 1 + 10 = 11
logger.shouldPrintMessage(2, &quot;bar&quot;);  // return true, next allowed timestamp for &quot;bar&quot; is 2 + 10 = 12
logger.shouldPrintMessage(3, &quot;foo&quot;);  // 3 &lt; 11, return false
logger.shouldPrintMessage(8, &quot;bar&quot;);  // 8 &lt; 12, return false
logger.shouldPrintMessage(10, &quot;foo&quot;); // 10 &lt; 11, return false
logger.shouldPrintMessage(11, &quot;foo&quot;); // 11 &gt;= 11, return true, next allowed timestamp for &quot;foo&quot; is 11 + 10 = 21
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= timestamp &lt;= 10<sup>9</sup></code></li>
	<li>Every <code>timestamp</code> will be passed in non-decreasing order (chronological order).</li>
	<li><code>1 &lt;= message.length &lt;= 30</code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>shouldPrintMessage</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $\textit{ts}$ to store the next available print timestamp for each message. When the `shouldPrintMessage` method is called, we check whether the current timestamp is greater than or equal to the next available print timestamp for the message. If so, we update the next available print timestamp to the current timestamp plus 10 and return `true`; otherwise, we return `false`.

The time complexity is $O(1)$. The space complexity is $O(m)$, where $m$ is the number of distinct messages.

<!-- tabs:start -->

#### Python3

```python
class Logger:

    def __init__(self):
        self.ts = {}

    def shouldPrintMessage(self, timestamp: int, message: str) -> bool:
        t = self.ts.get(message, 0)
        if t > timestamp:
            return False
        self.ts[message] = timestamp + 10
        return True


# Your Logger object will be instantiated and called as such:
# obj = Logger()
# param_1 = obj.shouldPrintMessage(timestamp,message)
```

#### Java

```java
class Logger {
    private Map<String, Integer> ts = new HashMap<>();

    public Logger() {
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        int t = ts.getOrDefault(message, 0);
        if (timestamp < t) {
            return false;
        }
        ts.put(message, timestamp + 10);
        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
```

#### C++

```cpp
class Logger {
public:
    Logger() {
    }

    bool shouldPrintMessage(int timestamp, string message) {
        if (ts.contains(message) && ts[message] > timestamp) {
            return false;
        }
        ts[message] = timestamp + 10;
        return true;
    }

private:
    unordered_map<string, int> ts;
};

/**
 * Your Logger object will be instantiated and called as such:
 * Logger* obj = new Logger();
 * bool param_1 = obj->shouldPrintMessage(timestamp,message);
 */
```

#### Go

```go
type Logger struct {
	ts map[string]int
}

func Constructor() Logger {
	return Logger{ts: make(map[string]int)}
}

func (this *Logger) ShouldPrintMessage(timestamp int, message string) bool {
	if t, ok := this.ts[message]; ok && timestamp < t {
		return false
	}
	this.ts[message] = timestamp + 10
	return true
}

/**
 * Your Logger object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.ShouldPrintMessage(timestamp,message);
 */
```

#### JavaScript

```js
/**
 * Initialize your data structure here.
 */
var Logger = function () {
    this.limiter = {};
};

/**
 * Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity.
 * @param {number} timestamp
 * @param {string} message
 * @return {boolean}
 */
Logger.prototype.shouldPrintMessage = function (timestamp, message) {
    const t = this.limiter[message] || 0;
    if (t > timestamp) {
        return false;
    }
    this.limiter[message] = timestamp + 10;
    return true;
};

/**
 * Your Logger object will be instantiated and called as such:
 * var obj = new Logger()
 * var param_1 = obj.shouldPrintMessage(timestamp,message)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
