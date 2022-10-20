# [2437. Number of Valid Clock Times](https://leetcode.com/problems/number-of-valid-clock-times)

[中文文档](/solution/2400-2499/2437.Number%20of%20Valid%20Clock%20Times/README.md)

## Description

<p>You are given a string of length <code>5</code> called <code>time</code>, representing the current time on a digital clock in the format <code>&quot;hh:mm&quot;</code>. The <strong>earliest</strong> possible time is <code>&quot;00:00&quot;</code> and the <strong>latest</strong> possible time is <code>&quot;23:59&quot;</code>.</p>

<p>In the string <code>time</code>, the digits represented by the <code>?</code>&nbsp;symbol are <strong>unknown</strong>, and must be <strong>replaced</strong> with a digit from <code>0</code> to <code>9</code>.</p>

<p>Return<em> an integer </em><code>answer</code><em>, the number of valid clock times that can be created by replacing every </em><code>?</code><em>&nbsp;with a digit from </em><code>0</code><em> to </em><code>9</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> time = &quot;?5:00&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can replace the ? with either a 0 or 1, producing &quot;05:00&quot; or &quot;15:00&quot;. Note that we cannot replace it with a 2, since the time &quot;25:00&quot; is invalid. In total, we have two choices.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> time = &quot;0?:0?&quot;
<strong>Output:</strong> 100
<strong>Explanation:</strong> Each ? can be replaced by any digit from 0 to 9, so we have 100 total choices.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> time = &quot;??:??&quot;
<strong>Output:</strong> 1440
<strong>Explanation:</strong> There are 24 possible choices for the hours, and 60 possible choices for the minutes. In total, we have 24 * 60 = 1440 choices.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>time</code> is a valid string of length <code>5</code> in the format <code>&quot;hh:mm&quot;</code>.</li>
	<li><code>&quot;00&quot; &lt;= hh &lt;= &quot;23&quot;</code></li>
	<li><code>&quot;00&quot; &lt;= mm &lt;= &quot;59&quot;</code></li>
	<li>Some of the digits might be replaced with <code>&#39;?&#39;</code> and need to be replaced with digits from <code>0</code> to <code>9</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countTime(self, time: str) -> int:
        def check(s, t):
            for a, b in zip(s, t):
                if a != b and b != '?':
                    return 0
            return 1

        return sum(
            check(f'{h:02d}:{m:02d}', time) for h in range(24) for m in range(60)
        )
```

### **Java**

```java
class Solution {
    public int countTime(String time) {
        int ans = 0;
        for (int h = 0; h < 24; ++h) {
            for (int m = 0; m < 60; ++m) {
                String s = String.format("%02d:%02d", h, m);
                int ok = 1;
                for (int i = 0; i < 5; ++i) {
                    if (s.charAt(i) != time.charAt(i) && time.charAt(i) != '?') {
                        ok = 0;
                        break;
                    }
                }
                ans += ok;
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
    int countTime(string time) {
        int ans = 0;
        for (int h = 0; h < 24; ++h) {
            for (int m = 0; m < 60; ++m) {
                char s[20];
                sprintf(s, "%02d:%02d", h, m);
                int ok = 1;
                for (int i = 0; i < 5; ++i) {
                    if (s[i] != time[i] && time[i] != '?') {
                        ok = 0;
                        break;
                    }
                }
                ans += ok;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countTime(time string) int {
	ans := 0
	for h := 0; h < 24; h++ {
		for m := 0; m < 60; m++ {
			s := fmt.Sprintf("%02d:%02d", h, m)
			ok := 1
			for i := 0; i < 5; i++ {
				if s[i] != time[i] && time[i] != '?' {
					ok = 0
					break
				}
			}
			ans += ok
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function countTime(time: string): number {
    let [hh, mm] = time.split(':');
    return count(hh, 24) * count(mm, 60);
}

function count(str: string, limit: number): number {
    let [a, b] = str.split('').map(d => Number(d));
    let ans = 0;
    if (isNaN(a) && isNaN(b)) return limit;
    if (isNaN(a)) {
        for (let i = 0; i <= 9; i++) {
            if (i * 10 + b < limit) ans++;
        }
        return ans;
    }
    if (isNaN(b)) {
        for (let i = 0; i <= 9; i++) {
            if (a * 10 + i < limit) ans++;
        }
        return ans;
    }
    return 1;
}
```

### **...**

```

```

<!-- tabs:end -->
