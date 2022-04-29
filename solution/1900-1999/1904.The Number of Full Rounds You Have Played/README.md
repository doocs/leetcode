# [1904. 你完成的完整对局数](https://leetcode.cn/problems/the-number-of-full-rounds-you-have-played)

[English Version](/solution/1900-1999/1904.The%20Number%20of%20Full%20Rounds%20You%20Have%20Played/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一款新的在线电子游戏在近期发布，在该电子游戏中，以 <strong>刻钟</strong> 为周期规划若干时长为 <strong>15 分钟</strong> 的游戏对局。这意味着，在 <code>HH:00</code>、<code>HH:15</code>、<code>HH:30</code> 和 <code>HH:45</code> ，将会开始一个新的对局，其中 <code>HH</code> 用一个从 <code>00</code> 到 <code>23</code> 的整数表示。游戏中使用 <strong>24 小时制的时钟</strong> ，所以一天中最早的时间是 <code>00:00</code> ，最晚的时间是 <code>23:59</code> 。</p>

<p>给你两个字符串 <code>startTime</code> 和 <code>finishTime</code> ，均符合 <code>"HH:MM"</code> 格式，分别表示你 <strong>进入</strong> 和 <strong>退出</strong> 游戏的确切时间，请计算在整个游戏会话期间，你完成的 <strong>完整对局的对局数</strong> 。</p>

<ul>
	<li>例如，如果 <code>startTime = "05:20"</code> 且 <code>finishTime = "05:59"</code> ，这意味着你仅仅完成从 <code>05:30</code> 到 <code>05:45</code> 这一个完整对局。而你没有完成从 <code>05:15</code> 到 <code>05:30</code> 的完整对局，因为你是在对局开始后进入的游戏；同时，你也没有完成从 <code>05:45</code> 到 <code>06:00</code> 的完整对局，因为你是在对局结束前退出的游戏。</li>
</ul>

<p>如果 <code>finishTime</code> <strong>早于</strong> <code>startTime</code> ，这表示你玩了个通宵（也就是从 <code>startTime</code> 到午夜，再从午夜到 <code>finishTime</code>）。</p>

<p>假设你是从 <code>startTime</code> 进入游戏，并在 <code>finishTime</code> 退出游戏，请计算并返回你完成的 <strong>完整对局的对局数</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>startTime = "12:01", finishTime = "12:44"
<strong>输出：</strong>1
<strong>解释：</strong>你完成了从 12:15 到 12:30 的一个完整对局。
你没有完成从 12:00 到 12:15 的完整对局，因为你是在对局开始后的 12:01 进入的游戏。
你没有完成从 12:30 到 12:45 的完整对局，因为你是在对局结束前的 12:44 退出的游戏。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>startTime = "20:00", finishTime = "06:00"
<strong>输出：</strong>40
<strong>解释：</strong>你完成了从 20:00 到 00:00 的 16 个完整的对局，以及从 00:00 到 06:00 的 24 个完整的对局。
16 + 24 = 40
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>startTime = "00:00", finishTime = "23:59"
<strong>输出：</strong>95
<strong>解释：</strong>除最后一个小时你只完成了 3 个完整对局外，其余每个小时均完成了 4 场完整对局。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>startTime</code> 和 <code>finishTime</code> 的格式为 <code>HH:MM</code></li>
	<li><code>00 <= HH <= 23</code></li>
	<li><code>00 <= MM <= 59</code></li>
	<li><code>startTime</code> 和 <code>finishTime</code> 不相等</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

转换为“分钟”进行计算。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
